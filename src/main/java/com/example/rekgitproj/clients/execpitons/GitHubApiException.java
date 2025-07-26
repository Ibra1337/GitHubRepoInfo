package com.example.rekgitproj.clients.execpitons;


import lombok.Getter;

/**
 * Custom exception for errors returned by the GitHub API.
 */
public class GitHubApiException extends RuntimeException {
    @Getter
    private final int statusCode;
    private final String message;

    public GitHubApiException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
