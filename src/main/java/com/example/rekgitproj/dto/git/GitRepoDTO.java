package com.example.rekgitproj.dto.git;

import com.example.rekgitproj.dto.github.GitHubOwnerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GitRepoDTO {
    private String name;
    private Boolean fork;
    private String owner;
}
