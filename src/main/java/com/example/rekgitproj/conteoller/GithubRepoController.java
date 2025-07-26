package com.example.rekgitproj.conteoller;


import com.example.rekgitproj.dto.api.UserRepoDTO;
import com.example.rekgitproj.service.GitService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("api/v1/git")
public class GithubRepoController {

    private final GitService gitService;

    @GetMapping("/{username}")
    public ResponseEntity<List<UserRepoDTO>> getUserRepositories(@PathVariable String username) {
        return ResponseEntity.ok(gitService.GetUsrRepoInfo(username));
    }
}
