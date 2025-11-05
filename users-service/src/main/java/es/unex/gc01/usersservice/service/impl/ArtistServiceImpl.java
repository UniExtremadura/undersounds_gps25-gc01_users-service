package es.unex.gc01.usersservice.service.impl;

import es.unex.gc01.usersservice.dto.*;
import es.unex.gc01.usersservice.model.Artist;
import es.unex.gc01.usersservice.model.User;
import es.unex.gc01.usersservice.model.enums.GenreType;
import es.unex.gc01.usersservice.repository.ArtistRepository;
import es.unex.gc01.usersservice.repository.UserRepository;
import es.unex.gc01.usersservice.service.ArtistService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository, UserRepository userRepository) {
        this.artistRepository = artistRepository;
        this.userRepository = userRepository;
    }

    @Override
    public SuccessfulResponseDTO convertToArtist(ArtistFormDTO artistFormDTO, String username) {
        User user = userRepository.findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (artistRepository.findByUserIdAndDeletedFalse(user.getId()).isPresent()) {
            throw new RuntimeException("El usuario ya es artista");
        }

        Artist artist = new Artist();
        artist.setUser(user);
        artist.setArtisticName(artistFormDTO.getArtistName());
        artist.setDescription(artistFormDTO.getDescription());
        artist.setIban(artistFormDTO.getIban());
        artist.setAccountPropietary(artistFormDTO.getAccountPropietary());

        artistRepository.save(artist);

        SuccessfulResponseDTO response = new SuccessfulResponseDTO("f");
        response.setSuccessful("successful_artist_conversion");
        response.setMessage("Usuario convertido a artista exitosamente");
        response.setStatusCode(200);
        response.setTimestamp(java.time.Instant.now().toString());

        return response;
    }

    @Override
    public SentArtistDTO getPublicArtistInfo(String artistUsername) {
        Artist artist = artistRepository.findByUserUsernameAndDeletedFalse(artistUsername)
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        return mapToSentArtistDTO(artist);
    }

    @Override
    public PageSentArtistDTO getArtists(String name, List<String> genres, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Set<GenreType> genreTypes = null;
        if (genres != null && !genres.isEmpty()) {
            genreTypes = genres.stream()
                    .map(GenreType::valueOf)
                    .collect(Collectors.toSet());
        }

        List<Artist> artists = artistRepository.findArtistsWithFilters(name, genreTypes);

        // Paginación manual básica
        int start = page * size;
        int end = Math.min(start + size, artists.size());

        List<SentArtistDTO> content = artists.subList(start, end).stream()
                .map(this::mapToSentArtistDTO)
                .collect(Collectors.toList());

        PageSentArtistDTO pageDTO = new PageSentArtistDTO();
        pageDTO.setContent(content);

        return pageDTO;
    }

    @Override
    public List<SentArtistDTO> getTrendingArtists() {
        return artistRepository.findByIsTrendingTrueAndDeletedFalse().stream()
                .map(this::mapToSentArtistDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArtistPaymentInfoDTO getArtistPaymentInfo(String username) {
        Artist artist = artistRepository.findByUserUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        ArtistPaymentInfoDTO dto = new ArtistPaymentInfoDTO();
        dto.setUsername(username);
        // CORRECCIÓN: Cambié setArtistName por setArtisticName o elimínalo si no existe
        dto.setTotalBalance(1000.0); // Valor de ejemplo
        dto.setThisMonth(150.0); // Valor de ejemplo
        dto.setIban(artist.getIban());
        dto.setAccountPropietary(artist.getAccountPropietary());

        // Aquí puedes añadir la lógica real para balances y pagos
        return dto;
    }

    @Override
    public SuccessfulResponseDTO updateArtist(String username, UpdateArtistDTO updateArtistDTO) {
        Artist artist = artistRepository.findByUserUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        artist.setArtisticName(updateArtistDTO.getArtisticName());
        artist.setDescription(updateArtistDTO.getDescription());
        artist.setIban(updateArtistDTO.getIban());
        // CORRECCIÓN: accountProperty vs accountPropietary
        artist.setAccountPropietary(updateArtistDTO.getAccountProperty());

        // Actualizar géneros
        if (updateArtistDTO.getGenres() != null) {
            Set<GenreType> genreTypes = updateArtistDTO.getGenres().stream()
                    .map(GenreType::valueOf)
                    .collect(Collectors.toSet());
            artist.setGenres(genreTypes);
        }

        artistRepository.save(artist);

        SuccessfulResponseDTO response = new SuccessfulResponseDTO("d");
        response.setSuccessful("successful_artist_update");
        response.setMessage("Artista actualizado correctamente");
        response.setStatusCode(200);
        response.setTimestamp(java.time.Instant.now().toString());

        return response;
    }

    @Override
    public SuccessfulResponseDTO deleteArtist(String username) {
        Artist artist = artistRepository.findByUserUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        artist.setDeleted(true);
        artistRepository.save(artist);

        SuccessfulResponseDTO response = new SuccessfulResponseDTO("d");
        response.setSuccessful("successful_artist_deletion");
        response.setMessage("Artista borrado de forma exitosa");
        response.setStatusCode(200);
        response.setTimestamp(java.time.Instant.now().toString());

        return response;
    }

    @Override
    public SuccessfulResponseDTO restoreArtist(Long artistId, String username) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        // Verificar permisos
        if (!artist.getUser().getUsername().equals(username)) {
            throw new RuntimeException("No tienes permisos para restaurar este artista");
        }

        if (!artist.isDeleted()) {
            throw new RuntimeException("El artista no está eliminado");
        }

        artist.setDeleted(false);
        artistRepository.save(artist);

        SuccessfulResponseDTO response = new SuccessfulResponseDTO("e");
        response.setSuccessful("successful_artist_restoration");
        response.setMessage("Artista restaurado correctamente");
        response.setStatusCode(200);
        response.setTimestamp(java.time.Instant.now().toString());

        return response;
    }

    private SentArtistDTO mapToSentArtistDTO(Artist artist) {
        SentArtistDTO dto = new SentArtistDTO();
        dto.setArtistId(artist.getId());
        dto.setUsername(artist.getUser().getUsername());
        dto.setArtisticName(artist.getArtisticName());
        dto.setDescription(artist.getDescription());
        dto.setTrending(artist.isTrending());
        dto.setVerified(artist.isVerified());
        dto.setMonthlyStreams(0); // Por defecto

        // Mapear géneros básico
        List<Genre> genreDTOs = artist.getGenres().stream()
                .map(genre -> {
                    Genre g = new Genre();
                    g.setId((long) genre.ordinal());
                    g.setType(genre);
                    return g;
                })
                .collect(Collectors.toList());
        dto.setGenres(genreDTOs);

        return dto;
    }
}