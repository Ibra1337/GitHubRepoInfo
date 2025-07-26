package com.example.rekgitproj.dto.api;

import lombok.Getter;

import java.util.List;

@Getter
public class UserRepoDTO {

    private String repositoryName;
    private String ownerLogin;
    private List<UserBranchDTO> branches;

    public UserRepoDTO() {}

    public UserRepoDTO(String repositoryName, String ownerLogin, List<UserBranchDTO> branches) {
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public void setBranches(List<UserBranchDTO> branches) {
        this.branches = branches;
    }
}
