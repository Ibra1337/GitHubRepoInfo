# GitHub Repository Info API

This Spring Boot application provides a RESTful API to retrieve **non-forked GitHub repositories** for a given user, including **branch names** and their **last commit SHA**.

---

##  Features

- Retrieve **non-forked** repositories for a given GitHub username
  - Return for each repository:
    - Repository **name**
    - Owner **login**
    - **Branch name** and last **commit SHA**
-  Return `404 Not Found` with a clear message when the user does not exist
-  Uses [GitHub REST API v3](https://docs.github.com/en/rest)

---

##  API Endpoint

### `GET /api/v1/git/{username}`

**Path Parameter:**

- `username` — GitHub login of the user

**Response Example (200 OK):**

```json
[
  {
    "repositoryName": "Hello-World",
    "ownerLogin": "octocat",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "6dcb09b..."
      }
    ]
  }
]
```

**Error Example (404 Not Found):**

```json
{
  "status": 404,
  "message": "user with given username not found"
}
```

---

## How to Run the Application

### Prerequisites:

- Java 21
- Maven 3.9+
- Internet connection (GitHub API access)

### Running with Maven:

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

After the app starts, access the API at:

```
http://localhost:8080/api/v1/git/{username}
```

---

## Integration Test

This application includes an integration test that:

- Fetches real data from GitHub
- Asserts correctness of the structure (repository name, branches, commit SHA)

---

