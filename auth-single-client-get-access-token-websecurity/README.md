#### request:
```
POST /oauth/token?grant_type=client_credentials HTTP/1.1
Host: localhost:8100
Authorization: Basic YTph
```
using postman Authorization as Basic Auth and username as "a" and password as "a"

#### reseponse:
```
{
    "access_token": "b794b3b1-b6ff-49a3-a7e2-eb554fd560e8",
    "token_type": "bearer",
    "expires_in": 43198,
    "scope": "all"
}
```