# API 명세

# 회원(User)

* API

  ![api_user](./imgs/api_user.PNG)

## 1. 회원가입(C) 

1. `POST /api/signUp` : 새로운 회원의 정보를 DB에 저장한다.

   - Parameter : User

     ```json
     {
       "email": "string",	//id 겸 이메일
       "pw": "string",		//비밀번호
       "uname": "string"		//이름
     }
     ```
   
  * 필수 :  `email`, `pw`, `uname` 만 입력받아 회원가입 한다.
   
- Return : UserResult
   
  ```json
   {
       "msg": "string",		//결과 정보
       "state": "string"		//상태 (success / fail)
     }
     ```
     
     
   
2. `GET /api/emailCheck/{email}` : 입력한 email이 사용가능 한지 중복 체크

   - Parameter : String

   - Return : `email` 중복 유무에 대한 결과를 반환한다.

     ```json
{
       "msg": "string",		
       "state": "string"		
     }
     ```

## 2. 로그인(R)

* `POST /api/login` : 로그인을 시도한다.

  * Parameter : User

    ```json
    {
      "email": "string",
      "pw": "string"
    }
    ```

    * 필수 : `email`, `pw`

  * Return : UserResult

    ```json
    {
      "msg": "string",		
      "state": "string"		
    }
    ```

    

## 3. 회원 탈퇴(D)

* `DELETE /api/deleteUser/{uid}` : 해당 uid를 가진 회원의 정보를 삭제한다.

  * Parameter : integer

  * Return : UserResult

    ```json
    {
      "msg": "string",		
      "state": "string"	
    }
    ```

    

## 4. 회원 목록(R)

* `GET /api/getAllUsers` : 전체 회원의 목록을 확인한다.

  * Parameter : {}

  * Return : 전체 회원의 목록 `List` 를 반환

    ```json
    [
      {
        "uid": 1,
        "email": "admin@it.da",
        "uname": "어드민",
        "auth": 0,
        "major": null,
        "uimg": null,
        "cid": 0				//취업한 기업id
      },
      {
    	...
      }
    ]
    ```

    

## 5. 회원 상세(R)

* `GET /api/getUser/{uid}` : uid에 해당하는 회원의 정보를 반환한다.

  * Parameter : {}

  * Return : UserResult

    ```json
    {
      "msg": "string",		
      "state": "string"
    }
    ```

    

## 6. 회원 정보 수정(U)

* `UPDATE /api/updateUser` : 회원의 정보를 수정한다.

  - Parameter : User

    ```json
    {
      "auth": 0,
      "cid": 0,
      "email": "string",
      "major": "string",
      "pw": "string",
      "uid": 0,
      "uimg": "string",
      "uname": "string"
    }
    ```

    - 필수 : `pw`, `uname`
    - `uid`, `email`, `auth`는 변경이 불가능하다.
    - `pw`, `major`, `uimg`, `uname` 변경 가능

  - Return : UserResult

    ```json
    {
      "msg": "string",		
      "state": "string"
    }
    ```

    

## 7. 토큰 정보 확인

* `POST /api/info` : 토큰이 유효한지 검사한다.

## 8. 회원 권한 수정(U)

* `UPDATE /api/updatePermission/{uid}` : uid에 해당하는 회원의 권한을 수정한다.
  * Parameter : User
    * 필수 : `uid`, `auth`
  * return : UserResult

--------------------

# 기업(Company)

* API

  ![api_company](./imgs/api_company.PNG)

## 1. 기업 생성(C)

## 2. 기업 수정(R)

## 3. 기업 삭제(D)



# 직무(Job)

* API

  ![api_job](./imgs/api_job.PNG)

## 1. 직무 생성(C)

## 2. 직무 수정(R)

## 3. 직무 삭제(D)

# 댓글(Comment)

* API

  ![api_comment](./imgs/api_comment.PNG)

## 1. 댓글 생성(C)

> 댓글 생성은 회원만 가능하다.

* `POST /api/createComment` 

  * Parameter : Comment

    ```json
    
    ```

    

## 2. 댓글 수정(R)

> 댓글 수정은 글 작성자만 가능하다.

## 3. 댓글 삭제(D)

> 댓글 삭제는 글 작성자 + 관리자만 가능하다.

## 공고

1. `GET /api/getWantedByRecent` : 최신 공고 목록을 요청 받아 응답한다.

   * Parameter : {}

   * return : 최신 공고 목록 최대 20개 반환

     * 기업로고, 기업명, 공고 제목, D-day을 출력한다.
     * 추후 기술스택 추가 필요 ######

     ```json
     [
         { "wid" : 1,
     	  "wantedTitle" : "2020년 IBK기업은행 신입행원 채용공고",
           "cid" : 100,
           "corpNm" : "IBK기업은행",
           "logo" : "",
           "endDate" : ""
         },
         {
             ...
         }
     ]
     ```

2. `GET /api/getWantedByCloseEnd` :  마감순 공고 목록을 요청 받아 응답한다.

   * Parameter : {}

   * return : 마감순 공고 목록 20개 반환

     ```json
     [
         { "wid" : 1,
           "cid" : 100,
           "corpNm" : "IBK기업은행",
           "endDate" : "2020-03-21 18:00:00" // 마감일
         },
         {
             ...
         }
     ]
     ```

   조회수*, 스크랩수*, 기술스택

3. `GET /api/getWantedByView` : 조회순 공고 목록을 요청 받아 응답한다.

   * Parameter : {}

   * return : 조회순 공고 목록 20개 반환

     ```json
     [
         { "wid" : 1,
           "cid" : 100,
           "corpNm" : "IBK기업은행",
     	  "vcnt" : 14300 // 조회수
         },
         {
             ...
         }
     ]
     ```

     

4. `GET /api/getWantedByScrap` : 스크랩 공고 목록을 요청 받아 응답한다.

   * Parameter : {}

   * return : 스크랩순 공고 목록 20개 반환

     ```json
     [
         { "wid" : 1,
           "cid" : 100,
           "corpNm" : "IBK기업은행",
     	  "scrapCnt" : 14300 // 스크랩수
         },
         {
             ...
         }
     ]
     ```

     

5. `GET /api/getWantedByViewAndScrap` : 스크랩 + 마감순 공고 목록을 요청 받아 응답한다.

   * Parameter : {}

   * return : 1)마감순, 2)스크랩순으로 공고 목록 20개 반환.

     ```json
     [
         { "wid" : 1,
           "cid" : 100,
           "corpNm" : "IBK기업은행",
           "endDate" : "2020-03-21 18:00:00", // 마감일
     	  "scrapCnt" : 14300 // 스크랩수
         },
         {
             ...
         }
     ]
     ```

     

6. `GET /api/getWantedByStack` : 기술스택별 공고 목록을 요청 받아 응답한다.

   * Parameter : {}
   * return : 최신 공고 목록 20개 반환 ################## 
   * **보류** 추천방식?! 조회순, 스크랩순, 싸피취업인순, 마감일순
   * **스크랩수 공고테이블에 필드 추가할지 말지???** ###########

7. `GET /api/SearchWantedByCompany/{name}` : 특정 기업명을 포함한 공고 목록을 요청 받아 응답한다.

   * Parameter : String

   * return : 검색된 모든 목록 반환

     로고 / 회사명, 종료일, 직무 / 조회수 / 스크랩수 

     ```json
     // List<Wanted> active
     [
         { "wid" : 1,
           "cid" : 100,
           "corpNm" : "IBK기업은행",
           "endDate" : "2020-03-21 18:00:00", // 마감일
           "vcnt" : 14300, // 조회수
     	  "scrapCnt" : 27, // 스크랩수,
           "logo" : "", // 로고
           "jobs" : ["IT인프라", "보안"], // 직무
           "stacks" : ["MySQL", "JAVA"]
         },
         {
             ...
         }
     ]
     
     // List<Wanted> inactive
     [
         ...
     ]
     ```

   

8. `GET /api/SearchWantedByStack/{name}` : 기술스택을 포함한 공고 목록을 요청 받아 응답한다.

   * Parameter : String
   * return : 검색된 모든 목록 반환

9. `GET /api/SearchWantedByID/{wid}` : 특정 공고 상세 정보 반환

   * Parameter : Integer

   * return : 특정 공고 반환. 

     * 추후 기술스택, 자격증, 스크랩 추가 ##############
     * 댓글 논의 필요 ##############

     ```json
     { "wid" : 1,
       "wantedTitle" : "2020년 IBK기업은행 신입행원 채용공고",
       "active" : 0,
       "startDate" : "2020-03-06 09:00:00",
       "endDate" : "2020-03-21 18:00:00",
       "process" : "",
       "etc" : "",
       "question" : "",
       "vcnt" : 1001,
       "cid" : 100,
       "corpNm" : "",
       "totPsncnt" : 0,
       "busiSize" : "",
       "yrSalesAmt" : 0,
       "corpAddr" : "",
       "homePg" : "",
       "busiCont" : "",
       "logo" : "",
       "jobs" : [
           {
               "jid" : 101,
               "to" : "00",
               "jname" : "웹 프론트엔드 개발",
               "jdetail" : "",
               "jtype" : "",
               "require" : "",
               "extra" : "",
               "place" : "",
               
           },
           {
               ...
           }
       ]
     }
     
     ```

     