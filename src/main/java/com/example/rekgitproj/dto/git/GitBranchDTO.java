package com.example.rekgitproj.dto.git;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GitBranchDTO {
    private String name;
    private String  commit;
}
