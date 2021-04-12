# nav-quarkus

Nav(News Viewer Application) using Quarkus and Angular

# Documentation

## End to end flow

### Tools and links

- You can use Postman to access API. Installation instructions can be found here:
  https://www.postman.com/downloads/. CURL or any other tool can be use to access API.
- For running application you will need Docker and maven installed on your machine.

  - Docker: https://docs.docker.com/get-docker/

- Github repository: https://github.com/Mufa0/nav-quarkus ( development branch )

- Running application:
  1. Inside root of project run "mvnw install" - it should create .jar file in target folder
  2. For running application run "docker compose up --build". This should build images for postgres and nav-app.
  3. Application is ready to be tested.

### Flow

- Use below described API for one user flow:

  1. Register two users ( one with READER role and one with AUTHOR role)

     1.1 Call get all users to have informations about current users in database

  2. Create one or several articles with author user

     2.1 Call get all articles to have informations about all current articles in database

  3. Like one or several articles with reader role
  4. Create one or more groupes with created articles
  5. You can access user or article audits which will show "logs" of CREATE, UPDATA or DELETE actions on user or article.
  6. For playing with rest of application you can use API described in table below

- Notes:

  - AUTHOR role implicitly contains READER role

  - Content of article is passed as string as i didn't have time to implement it proper way. Proper way proposal: "Frontend pass file as Multipart file to backend. Backend saves it on some hashed location and save location to database as "url". When fetching article we fetch full file and present it to frontend. If frontend is created in way that user can create full structure in html like format we can save whole html file and references to images which can also be uplouded to server and saved to secure location."

  - Audits are created only for User and Article entity. Only GET API is provided to user, creation of articles is done through service layer and it is not shown to user. Only Author can access article audits and only for his own articles.

  - Nothing is really deleted from database. Soft deletion is implemented. Further implementation would have automatic deletion when last article-audit is DELETE action and enough time has passed.

  - In table below you can see which APIs are enabled only for AUTHOR role.

  - Test coverage report can be found in documentation folder. Since time constraint I didn't implement all tests but i created some testing for Mappers, Validations and Integration tests.

  - Liquibase was used as scheme migration tool. Through commits you can see some changelogs but i decide to discard them and put all changes in scheme.sql. When app is deployed to production all migrations would need to go through liquibase.

  - There is separate profile for testing. That profile deletes all data from database so each integration test class have clean start.

  - Internationalization is implemented. If you want to see error messages in Croatian sent url with header Accept-Language = hr

### Tech stack

- Quarkus

- Postgres
- Java 11

### Constants

- baseUrl = http://localhost:8080

### Table for calls

| Description                        | METHOD | API                          | Request                           | Response                                                           | Auhtorization                                                            |
| ---------------------------------- | ------ | ---------------------------- | --------------------------------- | ------------------------------------------------------------------ | ------------------------------------------------------------------------ | --- |
| User registration                  | POST   | /users/register              | [UserReqest](#userrequest)        | [UserResponse](#userresponse)                                      | None                                                                     |
| Article creation                   | POST   | /articles                    | [ArticleRequest](#articlerequest) | [ArticleResponse](#articleresponse)                                | Basic auth with username and password provided for user with role AUTHOR |
| Article liking                     | PUT    | /user/article/{id}           | None                              | [UserResponse](#userresponse)                                      | Basic auth with username and password provided for user with role AUTHOR |
| Group creation                     | POST   | /groups                      | [GroupRequest](#grouprequest)     | [GroupResponse](#groupresponse)                                    | Basic auth with username and password provided for user with any role    |
| -                                  | -      | -                            | -                                 | -                                                                  | -                                                                        |
| Fetch user profile                 | GET    | /users/user                  | None                              | [UserResponse](#userresponse)                                      | Basic auth with username and password provided                           |
| Fetch all users                    | GET    | /users                       | None                              | List\<[UserResponse](#userreponse)>                                | Basic auth with username and password provided                           |
| Delete user                        | DELETE | /users                       | None                              | None                                                               | Basic auth with username and password provided                           |
| -                                  | -      | -                            | -                                 | -                                                                  | -                                                                        |
| Get all audits for user            | GET    | /user-audits/user            | None                              | List<\[UserAuditResponse](#userauditresponse)>                     | Basic auth with username and password provided                           |
| Get specific audit for user        | GET    | /user-audits/{id}            | None                              | [UserAuditResponse](#userauditresponse)                            | Basic auth with username and password provided                           |
| -                                  | -      | -                            | -                                 | -                                                                  | -                                                                        |
| Get all articles                   | GET    | /articles                    | None                              | [ArticleResponse](#articleresponse)                                | Basic auth with username and password provided                           |
| Get all articles for current user  | GET    | /articles/user/{id}          | None                              | List\<[ArticleResponse](#articleresponse)>                         | Basic auth with username and password provided for author                |
| Get specific article               | GET    | /articles/{id}               | None                              | [ArticleResponse](#articleresponse)                                | Basic auth with username and password provided                           |
| Update article                     | PUT    | /article/{id}                | [ArticleRequest](#articlerequest) | [ArticleResponse](#articleresponse)                                | Basic auth with username and password provided for user with role AUTHOR |     |
| Delete article                     | DELETE | /articles/{id}               | None                              | None                                                               | Basic auth with username and password provided for user with role AUTHOR |
| -                                  | -      | -                            | -                                 | -                                                                  | -                                                                        |
| Get all article audits for user    | GET    | /article-audits/user         | None                              | List\<[GroupedArticleAuditResponse](#groupedarticleauditresponse)> | Basic auth with username and password provided for user with role AUTHOR |
| Get specific article audit         | GET    | /article-audits/{id}         | None                              | [ArticleAuditResponse](#articleauditresponse)                      | Basic auth with username and password provided for user with role AUTHOR |
| Get all article audits for article | GET    | /article-audits/article/{id} | None                              | List\<[ArticleAuditResponse](#articleauditresponse)>               | Basic auth with username and password provided for user with role AUTHOR |
| -                                  | -      | -                            | -                                 | -                                                                  | -                                                                        |
| Update group                       | PUT    | /groups/{id}                 | [GroupRequest](#grouprequest)     | [GroupResponse](#groupresponse)                                    | Basic auth with username and password provided for user                  |
| Get all groups for user            | GET    | /groups/user                 | None                              | List\<[GroupResponse](#groupresponse)>                             | Basic auth with username and password provided for user                  |
| Get specific group                 | GET    | /groups/{id}                 | None                              | [GroupResponse](#groupresponse)                                    | Basic auth with username and password provided for user                  |
| Delete group                       | DELETE | /groups/{id}                 | None                              | None                                                               | Basic auth with username and password provided for user                  |

### UserRequest

```json
{
    "name":"" (Not Blank),
    "lastname":"" (Not Blank),
    "email":"" (Email , Unique),
    "password":""(Not Blank),
    "role":"" (If blank will put to READER)
}
```

### UserResponse

```json
{

    "id": ,
    "name": "",
    "lastname": "",
    "email": "",
    "articles": [],
    "role": "",
    "groups": [],
    "likedArticles": []
}
```

### ArticleRequest

```json
{
    "title":"" (Not Blank),
    "content":"" (Not Blank),
}
```

### ArticleResponse

```json
{

    "id": ,
    "title": "",
    "content": "",
    "author": ,
    "numberOfLikes":

}
```

### GroupRequest

```json
{
    "name":"", (Not blank)
    "articles":[]
}
```

### GroupResponse

```json
{
    "id": ,
    "name": "",
    "articles": [
        {
            "id": ,
            "title": "",
            "content": "",
            "author": ,
            "numberOfLikes":
        }
    ]
}
```

### UserAuditResponse

```json

[
    {
        "id": ,
        "action":"" ,
        "timestamp": "",
        "commiterEmail":"" ,
        "message": ""
    }
]


```

### ArticleAuditResponse

```json

[
    {
        "id": ,
        "action":"" ,
        "timestamp": "",
        "articleId":,
        "commiterEmail":"" ,
        "message": ""
    }
]


```

### GroupedArticleAuditResponse

```json

[
    {
        "articleId": ,
        "audits":[]
    }
]


```
