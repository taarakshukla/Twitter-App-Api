
# TWITTER APP API

- Designed and implemented a RESTful API to facilitate user authentication (login/signup), posting tweets, and creating comments
- Utilized H2 database for persistent data storage
- Ensured robust performance and scalability to handle multiple user interactions simultaneously.


## DETAILS

- This is a RESTful API designed as a part of OOP project using SpringBoot
- Base URL: http://127.0.0.1:8080
- Database used: H2 database
- IDE used: Intellij IDEA
- Postman application used to test the API


## SIGNUP

Endpoint to register a new user. If the account already exists, relevant error should be returned

    URL: /signup

#### Method: POST

Request Body:

        -email <str>
        -name <str>
        -password <str>

Response: One of the following
- Account Creation Successful
- Forbidden, Account already exists

## LOGIN

Endpoint to login an existing user. If the user does not exist or the credentials are incorrect, relevant error is thrown

    URL: /login

#### Method: POST

Request Body:

        -email <str> 
        -password <str>

Response: One of the following Messages
- Login Successful
- Username/Password Incorrect
- User does not exist

## USER DETAIL

Endpoint to retrieve details about a user. Relevant error is returned if the user does not exist

    URL: /user

    Query Parameter: userID

#### Method: GET 

Response Body: One of-

        -name <str>
        -userID <int>
        -email <str>

- User does not exist

## USER FEED

Endpoint to retrieve all posts by all users in reverse chronological order of creation

    URL: /

#### Method: GET

(All posts by all users sorted in the order of creation, the latest at the top) 

Response Body: 

        -posts <object>
        -postID <int> 
        -postBody <string> 
        -date <date> 
        -comments <object>
            -commentID <int> 
            -commentBody <string> 
            -commentCreator <Object>
                -userID <int> 
                -name <str>

## CREATING A POST

### Endpoints related to post 

    URL: /post

### To create a new post: 
- Return relevant error if the user does not exist 

#### Method: POST

Request Body: 

        -postBody<string> 
        -userID<int>

Response: One of -
- Post created successfully
- User does not exist


### To retrieve an existing post

- Relevant error needs to be returned if the post does not exist 

#### Method: GET

    Query Parameter: postID

(All the details about a specific post)

Response Body: One of - 

        <objects>
            -postID <int> 
            -postBody <string> 
            -date<date> 
            -comments <object>
                -commentID<int> 
                -commentBody<string> 
                -commentCreator<Object>
                    -userID<int> 
                    -name<str>

- Post does not exist

### To edit an existing post
- Relevant error needs to be returned if the post does not exist 

#### Method: PATCH

Request Body:

        postBody<str>
        postID<int> 

Response Body: One of -

- Post edited successfully
- Post does not exist 


### To delete a post
- Relevant error needs to be returned if the post does not exist 

#### Method: DELETE

    Query Parameter: postID<int>

Response: One of -

- Post deleted
- Post does not exist


## CREATING A COMMENT ON A POST

### Endpoints related to comments on a post 
    URL: /comment

### To create a new comment 
- Relevant error needs to be returned if the post does not exist 

#### Method: POST

Request Body: 

        -commentBody<string>
        -postID<int>
        -userID<int> 

Response: One of - 
- Comment created successfully
- User does not exist
- Post does not exist

### To retrieve an existing comment
- Relevant error needs to be returned if the comment does not exist

#### Method: GET

Request Param:

    commentID<int>

Response Body: One of - 

        <object>
            -commentID<int> 
            -commentBody<string>
            -commentCreator<Object> 
                -userID<int>
                -name<str>

- Comment does not exist

### To edit an existing comment
- Relevant error needs to be returned if the comment does not exist

#### Method: PATCH

Request Body:

        commentBody<str>
        commentID<int> 

Response Body: One of - 
- Comment edited successfully
- Comment does not exist


### To delete an existing comment
- Relevant error needs to be returned if the comment does not exist

#### Method: DELETE

    Query Parameter: commentID<int>

Response Body: One of - 
- Comment deleted
- Comment does not exist

## ALL USERS

Endpoint to show details about all the existing users

    URL: /users

#### Method: GET 

Response:

        <Object>
            -name <str> 
            -userID <int> 
            -email <str>