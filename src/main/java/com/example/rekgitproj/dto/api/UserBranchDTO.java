package com.example.rekgitproj.dto.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserBranchDTO {

    private String name;
    private String lastCommitSha;



}
