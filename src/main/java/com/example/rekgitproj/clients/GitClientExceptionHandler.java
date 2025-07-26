package com.example.rekgitproj.clients;


import com.example.rekgitproj.clients.execpitons.GitHubApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GitClientExceptionHandler
{

    @ExceptionHandler(GitHubApiException.class)
    public ResponseEntity<Map<String, String>> handleGitHubApiException(GitHubApiException ex) {
        Map<String, String> err = new HashMap<>();
        err.put("status", String.valueOf(ex.getStatusCode()));

        if (ex.getStatusCode() == 404) {
            err.put("message", "User with given username not found");
        } else {
            err.put("message", ex.getMessage());
        }

        return ResponseEntity.status(ex.getStatusCode()).body(err);
    }
}