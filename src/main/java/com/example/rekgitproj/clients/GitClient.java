package com.example.rekgitproj.clients;


import com.example.rekgitproj.clients.execpitons.GitHubApiException;
import com.example.rekgitproj.dto.git.GitBranchDTO;
import com.example.rekgitproj.dto.git.GitRepoDTO;
import com.example.rekgitproj.dto.github.GitHubRepoDTO;

import java.util.List;

public interface GitClient {

    /**
     * Fetches all repositories of a given user.
     *
     * @param username GitHub username
     * @return List of repository DTOs
     * @throws GitHubApiException if GitHub returns an error
     */
    List<GitRepoDTO> getUserRepos(String username);

    /**
     * Fetches branches for a specific repository.
     *
     * @param userLogin GitHub user login
     * @param repo      Repository details
     * @return List of branches
     */
    List<GitBranchDTO> getBranches(String userLogin, GitRepoDTO repo);
}
