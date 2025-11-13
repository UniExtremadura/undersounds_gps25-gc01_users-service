package es.unex.gc01.usersservice.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SentArtistDTO {
    private Long artistId;
    private String username;
    private String artisticName;
    private String description;
    private boolean isTrending;
    private boolean verified;
    private List<SocialMediaLinks> socialMediaLinks;
    private Integer monthlyStreams;
    private List<Genre> genres;
    private Integer followerCount;


}