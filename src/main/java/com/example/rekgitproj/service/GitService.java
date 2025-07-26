package com.example.rekgitproj.service;

import com.example.rekgitproj.dto.api.UserRepoDTO;

import java.util.List;

public interface GitService {

    List<UserRepoDTO> GetUsrRepoInfo(String username);
}
