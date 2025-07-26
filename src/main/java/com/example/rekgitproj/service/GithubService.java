package com.example.rekgitproj.service;

import com.example.rekgitproj.clients.GitClient;
import com.example.rekgitproj.dto.api.UserBranchDTO;
import com.example.rekgitproj.dto.api.UserRepoDTO;
import com.example.rekgitproj.dto.git.GitRepoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class GithubService implements GitService {

    private final GitClient gitClient;


    /**
     * Returns non-forked repositories of a user with branch and commit information.
     *
     * @param username GitHub username
     * @return List of enriched repository DTOs
     */
    @Override
    public List<UserRepoDTO> GetUsrRepoInfo(String username){

        List<GitRepoDTO> userRepos = gitClient.getUserRepos(username);

        List<UserRepoDTO> response = new LinkedList<>();
        userRepos.stream()
                .filter(r -> !r.getFork())
                .toList();

        for (var repo : userRepos){

            var branches = gitClient.getBranches(username,repo);

            UserRepoDTO userRepo = new UserRepoDTO(repo.getName() , repo.getOwner() , branches.stream().map(b -> new UserBranchDTO(b.getName(), b.getCommit()) ).toList());
            response.add(userRepo);

        }
        return response;
    }
}
