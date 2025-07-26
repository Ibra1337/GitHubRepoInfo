package com.example.rekgitproj;

import com.example.rekgitproj.dto.api.UserRepoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GithubIntegrationTest {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void shouldReturnNonForkedRepositoriesWithBranchesAndCommits() {
        // given
        String username = "octocat";
        String url = "http://localhost:" + port + "/api/v1/git/" + username;

        // when
        ResponseEntity<List<UserRepoDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<UserRepoDTO> body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body).allMatch(repo -> !repo.getBranches().isEmpty());
        assertThat(body).allSatisfy(repo -> {
            assertThat(repo.getRepositoryName()).isNotBlank();
            assertThat(repo.getOwnerLogin()).isNotBlank();
            repo.getBranches().forEach(branch -> {
                assertThat(branch.getName()).isNotBlank();
                assertThat(branch.getLastCommitSha()).isNotBlank();
            });
        });
    }
}
