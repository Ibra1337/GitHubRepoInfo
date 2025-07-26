package com.example.rekgitproj.dto.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubRepoDTO {
    private String name;
    private Boolean fork;
    private GitHubOwnerDTO owner;
}
