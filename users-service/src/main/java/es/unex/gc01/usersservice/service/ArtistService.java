package es.unex.gc01.usersservice.service;


import es.unex.gc01.usersservice.dto.*;

import java.util.List;

public interface ArtistService {
    SuccessfulResponseDTO convertToArtist(ArtistFormDTO artistFormDTO, String username);
    SentArtistDTO getPublicArtistInfo(String artistUsername);
    PageSentArtistDTO getArtists(String name, List<String> genres, int page, int size);
    List<SentArtistDTO> getTrendingArtists();
    ArtistPaymentInfoDTO getArtistPaymentInfo(String username);
    SuccessfulResponseDTO updateArtist(String username, UpdateArtistDTO updateArtistDTO);
    SuccessfulResponseDTO deleteArtist(String username);
    SuccessfulResponseDTO restoreArtist(Long artistId, String username);
}