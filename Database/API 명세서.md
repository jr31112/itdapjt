# API 명세

## 회원가입

1. `POST /api/signUp` : 새로운 회원의 정보를 DB에 저장

   - Parameter : User

     ```json
     { "email" : "string",
     	"pw" : "string",
     	"uname" : "string",
     	"auth" : 0,
     	"major" : "string",
     	"uimg" : "string" }
     ```

     회원가입 시 **email, pw, uname** 만 입력하여 회원가입하도록 한다.

   - return : 회원가입 성공 유무를 결과로 반환. state 결과 success/fail로 확인

     ```json
   { "email" : "string",
       "msg" : "string",
     "state" : "string" }
     
   ```
     
   ex)
     
   ```json
     { "email" : "admin@it.da",
       "msg" : "성공적으로 회원가입이 완료되었습니다. ",
       "state" : "success" }
     ```
     
     

2. `GET /api/emailCheck/{email}` : 입력한 email이 사용가능 한지 중복 체크

   - Parameter : String

   - return : 중복 유무에 대한 결과 반환.  state 결과 success/fail로 확인

     ```json
{ "msg" : "string",
       "state" : "string" }
```
     
     ex) 
     
     ```json
     {"msg" : "사용할 수 있는 email입니다.", "state" : "success" }
     ```



-----------

## 로그인

1. `POST /api/login` : 사용자의 정보(email, pw)가 일치하면 로그인

   - Parameter : User의 email, pw

     ```json
  { "email" : "string",
       "pw" : "string",
    "uname" : "string",
       "auth" : 0,
    "major" : "string",
       "uimg" : "string" }
  ```
   
  로그인시 **email, pw ** 만 입력하여 로그인 한다.
   
- return : 로그인 성공 유무를 결과로 반환. state 결과 success/fail로 확인
   
  ```json
   { "email" : "string",
   "auth" : 0,
    "msg" : "string",
   "state" : "string" }
   ```
  
   ex)
  
   ```json
  { "email" : "admin@it.da",
       "auth" : 0,
    "msg" : "이메일 또는 비밀번호가 일치하지 않습니다.",
       "state" : "fail" }
     ```

-------------



## 공고

1. `GET /api/getWantedByRecent` : 최신 공고 목록을 요청 받아 응답한다.

   * Parameter : {}

   * return : 최신 공고 목록 20개 반환.  state 결과 success/fail로 확인

     ```json
     { ""}
     ```

2. `GET /api/getWantedByCloseEnd` :  마감순 공고 목록을 요청 받아 응답한다.

   * Parameter : {}
   * return : 최신 공고 목록 20개 반환.  state 결과 success/fail로 확인

3. `GET /api/getWantedByView` : 조회순 공고 목록을 요청 받아 응답한다.

   * Parameter : {}
   * return : 최신 공고 목록 20개 반환.  state 결과 success/fail로 확인

4. `GET /api/getWantedByScrap` : 스크랩 공고 목록을 요청 받아 응답한다.

   * Parameter : {}
   * return : 최신 공고 목록 20개 반환.  state 결과 success/fail로 확인

5. `GET /api/getWantedByViewAndScrap` : 스크랩 + 마감순 공고 목록을 요청 받아 응답한다.

   * Parameter : {}
   * return : 최신 공고 목록 20개 반환.  state 결과 success/fail로 확인

6. `GET /api/getWantedByStack` : 기술스택별 공고 목록을 요청 받아 응답한다.

   * Parameter : {}
   * return : 최신 공고 목록 20개 반환.  state 결과 success/fail로 확인

7. `GET /api/SearchWantedByCompany/{name}` : 특정 기업명을 포함한 공고 목록을 요청 받아 응답한다.

   * Parameter : String

   * return : 검색된 모든 목록 반환.  state 결과 success/fail로 확인

     ```json
     // List<Wanted> active
     [
         
     ]
     
     // List<Wanted> inactive
     [
         
     ]
     ```

     

8. `GET /api/SearchWantedByStack/{name}` : 기술스택을 포함한 공고 목록을 요청 받아 응답한다.

   * Parameter : String
   * return : 검색된 모든 목록 반환.  state 결과 success/fail로 확인

