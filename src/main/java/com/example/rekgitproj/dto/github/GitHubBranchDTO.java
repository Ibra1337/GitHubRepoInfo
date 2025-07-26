package com.example.rekgitproj.dto.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class GitHubBranchDTO {
    private String name;
    private GitCommitRefDTO commit;

    public void setName(String name) { this.name = name; }
    public void setCommit(GitCommitRefDTO commit) { this.commit = commit; }

    public String getSha() {
        return commit != null ? commit.getSha() : null;
    }
}

