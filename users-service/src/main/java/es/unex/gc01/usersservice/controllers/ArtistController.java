package es.unex.gc01.usersservice.controllers;


import es.unex.gc01.usersservice.dto.*;
import es.unex.gc01.usersservice.service.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    // Información pública de artista por username
    @GetMapping("/public/{artistUserName}")
    public ResponseEntity<?> getPublicArtistInfo(@PathVariable String artistUserName) {
        try {
            SentArtistDTO artist = artistService.getPublicArtistInfo(artistUserName);
            return ResponseEntity.ok(artist);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "Not Found",
                    "Artista " + artistUserName + " no encontrado",
                    404,
                    Instant.now().toString()
            );
            return ResponseEntity.status(404).body(error);
        }
    }

    // Búsqueda de artistas con filtros
    @GetMapping("/public")
    public ResponseEntity<?> getArtists(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<String> genres,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try {
            PageSentArtistDTO artists = artistService.getArtists(name, genres, page, size);
            return ResponseEntity.ok(artists);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "Not Found",
                    "No hay artistas que coinciden con la selección de búsqueda",
                    404,
                    Instant.now().toString()
            );
            return ResponseEntity.status(404).body(error);
        }
    }

    // Artistas en tendencia
    @GetMapping("/public/trending")
    public ResponseEntity<?> getTrendingArtists() {
        try {
            List<SentArtistDTO> artists = artistService.getTrendingArtists();
            return ResponseEntity.ok(artists);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "Not Found",
                    "No existe por el momento ningún artista en tendencia",
                    404,
                    Instant.now().toString()
            );
            return ResponseEntity.status(404).body(error);
        }
    }

    // Información de pagos del artista
    @GetMapping("/payment-info")
    public ResponseEntity<?> getArtistPaymentInfo(@RequestHeader("username") String username) {
        try {
            ArtistPaymentInfoDTO paymentInfo = artistService.getArtistPaymentInfo(username);
            return ResponseEntity.ok(paymentInfo);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "unauthorized",
                    "No tienes permisos para obtener la información financiera del artista",
                    401,
                    Instant.now().toString()
            );
            return ResponseEntity.status(401).body(error);
        }
    }

    // Convertir usuario en artista
    @PostMapping
    public ResponseEntity<?> convertToArtist(@RequestBody ArtistFormDTO artistFormDTO,
                                             @RequestHeader("username") String username) {
        try {
            SuccessfulResponseDTO response = artistService.convertToArtist(artistFormDTO, username);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "Conflict",
                    e.getMessage(),
                    409,
                    Instant.now().toString()
            );
            return ResponseEntity.status(409).body(error);
        }
    }

    // Actualizar artista
    @PatchMapping
    public ResponseEntity<?> updateArtist(@RequestBody UpdateArtistDTO updateArtistDTO,
                                          @RequestHeader("username") String username) {
        try {
            SuccessfulResponseDTO response = artistService.updateArtist(username, updateArtistDTO);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "unauthorized",
                    "No tienes permitido actualizar a un artista",
                    401,
                    Instant.now().toString()
            );
            return ResponseEntity.status(401).body(error);
        }
    }

    // Eliminar artista (borrado lógico)
    @DeleteMapping
    public ResponseEntity<?> deleteArtist(@RequestHeader("username") String username) {
        try {
            SuccessfulResponseDTO response = artistService.deleteArtist(username);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "Bad Request",
                    "Artista ya borrado o operación inválida",
                    400,
                    Instant.now().toString()
            );
            return ResponseEntity.status(400).body(error);
        }
    }

    // Restaurar artista eliminado
    @PatchMapping("/{id}/restauracion")
    public ResponseEntity<?> restoreArtist(@PathVariable Long id,
                                           @RequestHeader("username") String username) {
        try {
            SuccessfulResponseDTO response = artistService.restoreArtist(id, username);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "unauthorized",
                    "No tienes permitido restaurar a un artista",
                    401,
                    Instant.now().toString()
            );
            return ResponseEntity.status(401).body(error);
        }
    }
}