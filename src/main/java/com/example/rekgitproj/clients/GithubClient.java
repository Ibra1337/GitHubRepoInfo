package com.example.rekgitproj.clients;

import com.example.rekgitproj.clients.execpitons.GitHubApiException;
import com.example.rekgitproj.dto.git.GitBranchDTO;
import com.example.rekgitproj.dto.git.GitRepoDTO;
import com.example.rekgitproj.dto.github.GitHubBranchDTO;
import com.example.rekgitproj.dto.github.GitHubRepoDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

/**
 * {@code GithubClient} is a REST client responsible for communicating with the GitHub API.
 * It fetches repositories and branch information for a given GitHub username.
 *
 * <p>This implementation uses Spring's {@link RestClient} to consume GitHub's public REST API.</p>
 */
@Component
@AllArgsConstructor
public class GithubClient implements GitClient {

    private final RestClient restClient;


    /**
     * Fetches all repositories for the given GitHub username.
     *
     * <p>Note: Forked repositories are included in the response; filtering should be done in the service layer.</p>
     *
     * @param username the GitHub username
     * @return a list of {@link GitRepoDTO} containing basic repo information
     * @throws GitHubApiException if GitHub returns an error (e.g. user not found)
     */
    @Override
    public List<GitRepoDTO> getUserRepos(String username) {
        try {
            ResponseEntity<List<GitHubRepoDTO>> response = restClient.get()
                    .uri(String.format("https://api.github.com/users/%s/repos", username))
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<>() {});

            List<GitHubRepoDTO> repos = response.getBody() != null ? response.getBody() : Collections.emptyList();

            return repos.stream()
                    .map(r -> new GitRepoDTO(
                            r.getName(),
                            r.getFork(),
                            r.getOwner() != null ? r.getOwner().getLogin() : null
                    ))
                    .toList();

        } catch (HttpClientErrorException e) {
            throw new GitHubApiException(e.getStatusCode().value(), e.getStatusText());
        }
    }

    /**
     * Fetches all branches for a specific GitHub repository.
     *
     * @param userLogin the owner (username) of the repository
     * @param repo the repository for which to fetch branches
     * @return a list of {@link GitBranchDTO} containing branch name and last commit SHA
     */
    @Override
    public List<GitBranchDTO> getBranches(String userLogin, GitRepoDTO repo) {
        var uri = String.format("https://api.github.com/repos/%s/%s/branches", userLogin, repo.getName());
        ResponseEntity<List<GitHubBranchDTO>> response = restClient.get()
                .uri(uri)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});

        List<GitHubBranchDTO> repoBranches = response.getBody();

        return repoBranches.stream()
                .map(b -> new GitBranchDTO(b.getName(), b.getCommit().getSha()))
                .toList();
    }
}
