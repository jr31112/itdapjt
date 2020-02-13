# 개발 환경 설정

## 프로그램 설치

* Java 13.0.2 (JDK)
  * [Java SE Development Kit 13.0.2 download](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html)
* [Eclipse Photon (4.8)](https://www.eclipse.org/downloads/packages/release/photon/r)
  * [Eclipse IDE for Java EE Developers download](https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-jee-photon-R-win32-x86_64.zip)
* MySQL
  * [MySQL 워크벤치 download](https://dev.mysql.com/downloads/file/?id=492814)
  * [설치 가이드](https://dog-developers.tistory.com/20)

## Java Eclipse 환경설정

1. 기본 위치 (ex. C:\Program Files\Java\jdk-13.0.2)  경로 복사
2. 환경변수 설정
   1.  윈도우 시스템 환경 변수 편집
   2.  시스템변수 새로 만들기
   3.  변수이름 `JAVA_HOME`, 변수값: `복붙(ex. C:\Program Files\Java\jdk-13.0.2) ` 
   4.  시스템변수 path  새로 만들기 
   5.  `%JAVA_HOME%\bin` 엔터 후 맨위로 올리기

# Java Spring 기본 흐름

> Java Eclipse 에서 프로젝트 생성부터 Java Spring(WAS) 구축 후 서버 구동까지 범위 포함
>
> 이 예제에서는 회원가입을 구현해보았다.

## 프로젝트 생성 및 설정

* 프로젝트 생성

  * new > Maven project > next  > `quickstart` 선택 > next > 

    `Group Id` : `com.ssafy.itda` (이 프로젝트의 기본이 되는 주소)  

    `Artifack Id` : `itda_test`  프로젝트명이다.  > finish

* 프로젝트 설정

  * `itda_test` 오른쪽 > `Properties` > `Project facets` > 링크 클릭 > `java` 1.8 Version 체크 확인 > apply 
  * `Java Build Path` > `JRE System Library` > `Workspace default JRE` 체크 > Finish > apply
  * `Java compiler` > `Use default compliance settings` 체크해제 > `Generated .class files compatibility`  1.8  확인 > apply > close

* pom 설정

  * [pom.xml 소스보기](./java_setting_files/project_setting/pom.xml)
  * `pom.xml` 설정 파일 덮어쓰기
    * Java Spring 쓸 때 기본적으로 많이 쓰는 것들
    * `<dependency>` : 내가 쓸 라이브러리 추가하는 공간
    * 저장, bulid 하면 `Maven Dependencies` 폴더 생성
  * 기본적으로 `src/main/java/com.ssafy.itda/itda_test/`에는 `App.java` 파일 하나만 있다. 이 파일은 손대지 않는 파일이다.



* 프로젝트 에러 해결 방법

  * 여기까지 수행하면 project error 가 발생한다.

  * `pom.xml`

    ```xml
    <!-- 수정 전 -->
    <artifactId>itda_test</artifactId>
    
    <!-- 수정 후 -->
    <artifactId>itda_test2</artifactId>
    ```

    `pom.xml` 파일에서 `artifactId`와 프로젝트 명을 수정한다. (같은 이름으로)

    그 후 다시 수정 전으로 돌려주면 error가 해결된다.

* **cf! 프로젝트 삭제시 꼭 체크! 체크안하면 보이는 곳에서만 없어짐**

## 개요

* 프로젝트 기본 개발 설정

  * `src/main/java/com.ssafy.itda.itda_test` 위치에 `SSAFYApplication.java`, `SwaggerConfig.java` 파일 복사하기

    * [SSAFYApplication.java 소스보기](./java_setting_files/src_main_java_package/SSAFYApplication.java)
    * [SwaggerConfig.java 소스보기](./java_setting_files/src_main_java_package/SwaggerConfig.java)

  * `src/main/java` 우클릭 > New > package > `com.ssafy.itda.itda_test.controller`(dao, help, model, service) 만들기

  * `src` > `main` > `resources` 폴더 만들기 > 파일들 복붙

    * [resources 소스보기](./java_setting_files/src_main_resource)

    * `resources` 폴더의 `application.properties` 

      ```
      <!-- 변경 전-->
      spring.datasource.url=jdbc:mysql://192.168.xx.xx:3306/...
      
      <!-- 변경 후-->
      spring.datasource.url=jdbc:mysql://localhost:3306/...
      
      ```

      주소 변경

* 폴더 설명

  * `controller` : 클라이언트에서 API 요청 시 해당 api 주소와 매핑되어 실행하게 될 함수들 정의 부분
  * `model` : DB table과 동일한 구조(=dto)
  * `help` : 클라이언트에게 API 호출 시 반환해 줄 데이터 구조(=Response)
  * `service` : controller에서 호출하여 처리해 줄 함수 정의
    * Interface와 그 Interface를 상속 받아 함수를 구현할 클래스가 있다.
    * ex) `IUserService.java` : 함수 정의 / `UserServiceImpl.java` : 함수 구현
  * `dao` : `src/main/java/resources/mapper` 에 있는 xml과 매핑되어 JPA를 실행할 함수 정의
    * = JPA Repository (참고한 블로그에서 말하는 부분)
    * [JPA(Java Persistent API)란? 블로그 링크](https://blog.woniper.net/255)

* 흐름

  * 요청 => `Client` -> `controller` -> `service` -> `dao` -> `xml` -> `Database Server`
  * 반환 => `dao` -> `service` -> `controller` ->`help` -> `Client` 

## #Model

* model -> new -> class -> 모델 명 ( 앞에만 대문자, ex. User)
* ex) 모델명 User -> Interface -> Serializable -> finish

1. Field 선언 -> private '타입명' '변수명'
2. 상단 바에서 `Source` -> Generate Constructor from Superclass
3. `Source` -> Generate Constructor using field
4. `Source` -> Generate Getters and Setters -> Select All -> Generate
5. `Source` -> Generate toString() -> Generate

## Controller

* controller -> new -> class -> XxxController -> finish

  기본 세팅은.... 복붙 ㅎㅎ ^^

1. 각 기능별 함수 구현

2. Response에 대한 결과 값만 잘 맞춰서 주면 됩니다!

   ```java
   return new ResponseEntity<returnResult>('return 결과 변수 명', HttpStatus.OK);
   ```

   emailCheck 예시)

   ```java
   @ApiOperation(value = " email 중복을 확인한다.", response = UserResult.class)
   @RequestMapping(value = "/emailCheck/{eamil}", method = RequestMethod.GET)
   public ResponseEntity<UserResult> signUp(@PathVariable String email) throws Exception {
       logger.info("1-2-------------emailCheck-------------------------" + new Date());
       logger.info("1-2-------------emailCheck-------------------------" + email);
   
       // 'email'이라는 email 을 가지고 있는 객체 user에 저장
       User user = userService.emailCheck(email);
       // 새로운 UserResult 생성, 반환객체
       UserResult ur = new UserResult();
       if (user != null) {
           ur.setMsg("중복된 아이디입니다.");
           ur.setState("fail");
       } else {
           ur.setMsg("사용가능한 아이디입니다.");
           ur.setState("success");
       }
       return new ResponseEntity<UserResult>(ur, HttpStatus.OK);
   }
   ```

   * `RequestMethod`가 `GET`일 때 `@PathVariable`
   * `POST`일 때 `@RequestBody`
   * 주의) `user`는 객체이므로 `if (user)`가 아닌 `if (user != null)`로 써줘야한다.

## Service

* service -> new -> Interface -> 인터페이스명 (IXxxService) -> finish

1. 사용할 함수를 **선언**만

2. 규칙 : 'return 타입' '함수 명'('파라미터')

   emailCheck 예제)

   ```java
   package com.ssafy.itda.itda_test.service;
   
   import com.ssafy.itda.itda_test.model.User;
   
   public interface IUserService {
   	void signUp(User u);
   	void emailCheck(String email);
   }
   ```

   

* service -> new -> class -> 클래스명(XxxServiceImpl) -> Interface -> IXxxService -> finish

1. public class ... 위에 `@Service` 를 작성 ( 어노테이션 )

2. [dao 먼저 정의하기](#dao)

3. #service autowired

   ```java
   @Autowired
   private XxxDao xxxDao;
   ```

4. 함수 구현 

   ```java
   @Override
   public void 함수명(파라미터){
       xxxDao.함수명(파라미터);
   }
   ```

   * emailCheck 예시)

     ```java
     public void signUp(User u) {
         // TODO Auto-generated method stub
         userDao.signUp(u);
     }
     
     @Override
     public User emailCheck(String email) {
         return userDao.emailCheck(email);
     }
     ```

     * `signUp()`은 반환 결과가 없다. DB에서 insert
     * `emailCheck()`는 반환결과가 User 객체이다. DB에서 select

## #Dao

* dao -> new -> class -> 클래스명 (XxxDao) -> finish

1. public class ... 위에 `@Repository` 를 치고 ctrl + 스페이스바로 import

2. String ns = "" ->  src/main/java/resources에서 `mapper` 의 xxx.xml 파일과 매핑. 동일하게

   **xml에서 <mapper namespace ="~~"** 여기 뒤에는 점이없다.

   dao에서는 ns = "~~**.**" 뒤에 점을 붙인다!

3. ```java
   @Autowired
   private SqlSession sqlSession;
   ```

   이 sqlSession이라는 객체를 통해서 Mybatis로 DB로 sql문을 사용할 수 있게 해줍니다.

4. 함수 정의

   ```java
   public void 함수명(파라미터){
       sqlSession.[insert|selectOne|selectList|delete|update](ns + "함수명", (파라미터))
   }
   ```

5. [다시 Service 로 가기](#service-autowired)

   * emailCheck 예시)

     ```java
     public void signUp(User u) {
         sqlSession.insert(ns + "signUp", u);
     }
     
     public User emailCheck(String email) {
         return sqlSession.selectOne(ns + "emailCheck",  email);
     }
     ```

     

## XML

* src/main/resources/mapper/mybatis-config 수정

  * 맵퍼 부분에 아래와 같이 일치시켜준다.

    ```xml
    <mappers>
        <mapper resource="mapper/user.xml"/>
        <mapper resource="mapper/wanted.xml"/>
        <mapper resource="mapper/company.xml"/>        
        <mapper resource="mapper/job.xml"/>        
    </mappers>
    ```

    

* src/main/java/resources/mapper 위치에 있는 xxx.xml 파일에서 sql문 정의

  ```xml
  <[select|insert|update|delete] id="함수명"
   (parameterType="Model의 경로 or java.lang.(String|Integer ...)")
   (resultType="Model의 경로 or java.lang.(String|Integer ...)")>
   	SQL 문
   </[select|insert|update|delete]>
  ```

  SQL 문 안에 모델의 데이터는 `#{ 변수명(소문자) }` 으로 작성

  * emailCheck 예시)

    ```java
    <!-- sha1 : sha1이라는 보안기법에 의해 pw 자동 변환 -->
    <insert id="signUp" 
        parameterType="com.ssafy.itda.itda_test.model.User">
        INSERT INTO USER_T(EMAIL, PW, UNAME)
        VALUES ( #{email}, sha1(#{pw}), #{uname} ) 
    </insert>
    
    <select id="emailCheck"
        parameterType="String"
        resultType="com.ssafy.itda.itda_test.model.User">
        SELECT *
        FROM USER_T
        WHERE EMAIL = #{email}
    </select>
    ```

    * `insert` 문은 리턴이 없다. 

## Help

* 응답 결과를 반환 해줄 클래스

* help -> new -> class -> 클래스명(XxxResult) -> finish

* [Model](#model)과 동일한 방식으로 구현

응답 결과에 담을 변수들을 정의

# 유저 인증

> JWT Token

# 소셜 로그인

> Fire base

# Saramin API

> 외부 API 사용 방법(요청, JSON 응답)
>
> 검색 조건
>

* [[링크] 사람인 채용정보 API](https://oapi.saramin.co.kr/)

* 채용 공고 API URL

  * 요청 변수

    * 본 프로젝트에서 사용하는 변수만을 포함한다.

    | 변수명       | 값                                                         | 설명                                            |
    | ------------ | ---------------------------------------------------------- | ----------------------------------------------- |
    | access-key   | -                                                          | **(필수)** api key                              |
    | count        | 110                                                        | 검색 결과 수<br />10 (default)<br />110(최대값) |
    | job_type     | 1(정규직)<br />4(인턴직)<br />11(인턴직 - 정규직 전환가능) | 근무형태                                        |
    | job_category | 4(IT·인터넷)                                               | 직종                                            |
    | sort         | pd                                                         | 게시일 역순(default)                            |
    | start        | 1, 2, ...                                                  | 검색 결과의 페이지 번호                         |

  * 출력 결과

    | 변수명                | 설명                                                         |
    | --------------------- | ------------------------------------------------------------ |
    | count                 | 공고 개수                                                    |
    | start                 | 검색 결과의 페이지 번호                                      |
    | id                    | 채용 공고 id                                                 |
    | active                | 공고 진행 여부<br />1: 진행중, 0: 마감                       |
    | opening-timestamp     | 접수 시작일 Unix timestamp<br />MySQL에서 Unixtime -> Date 형식으로 변환시 `FROM_UNIXTIME()`사용하여 DB에 저장한다. |
    | expiration-timestamp  | 마감일 Unix timestamp<br />opening-timesatamp와 마찬가지로 Date 형식으로 변환 후 DB에 저장 |
    | name                  | 기업명<br />DB에 채용공고를 입력할 때 DB에 해당 기업명이 존재하는 경우 기업ID와 함께 채용공고를 입력<br />만약 기업명이 존재하지 않는 경우 기업을 먼저 입력 후 채용공고 입력 |
    | name@href             | 기업정보 페이지**(공개되어 있는 경우)**                      |
    | title                 | 공고 제목                                                    |
    | experience-level@code | 경력코드<br />1: 신입, 2: 경력, 3: 신입/경력, 0: 경력무관<br />**경력코드가 2인 채용공고는 DB에 넣지 않는다.** |

    

# Spring 자동 실행 scheduler

> 공고 자동 업데이트
>
> 공고 시작일, 마감일에 따른 진행중/마감 공고 업데이트

* Spring Scheduler

  * spring에서 주기적인 작업이 있을 때 `Quartz` 또는 `Scheduled` Annotation을 사용해 관리할 수 있다.
  * 본 프로젝트에서는 `Spring`에서 자체적으로 제공하는  `Scheduled Annotation`을 사용하였다.
  * 설정 간단

  

* 사용법

  * schedule 기능 켜기

    * 스프링 부트가 실행될 메인 함수 파일(자바 설정 관련 클래스)에 `@EnableScheduling` 추가

    ```java
    // SSAFYApplication.java
    import org.springframework.scheduling.annotation.EnableScheduling;
    
    @EnableScheduling
    ```

  * 실행주기

    ```
    (cron = "* * * * * *")
    초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-7) 
    ```

    * 만약 `cron="0 0 0/5 * * *"`라면 매일 0시부터 5시간마다 실행한다는 의미이다.

    * [[링크] cron 표현식 쉽게 만들기](http://www.cronmaker.com/)

    * cf) `scheduler` 기본 설정에 `cron`, `fixedDelay`, `fixedRate` 등의 속성이 있다.

      * `fiexedDalay` : milliseconds 단위. 이전 작업 끝난 시점부터 고정된 시간 설정

      * `fixedRate` : milliseconds 단위. 이전 작업이 수행되기 시작한 시점부터 고정된 시간 설정

        

  * `Service` 에 scheduler 구현

    * `API Call`이 왔을 때 실행되는 `Controller`가 아닌 `Service`측에서 구현해야 한다.
    * `Service`는 `Dao`가 DB에서 받아온 데이터를 전달받아 가공한다.
    * `Controller`는 시스템에 들어오는 요청, 응답을 담당한다.

    ```java
    // WantedServiceImpl
    import org.springframework.scheduling.annotation.Scheduled;
    //...
    	@Scheduled(cron="0 0 0/1 * * *")
    	public void schedulerCheckDate() {
    		System.out.println("Scheduler Check Expire!!");
    	}
    }
    ```

# 공고 생성

> 원하는 값 추출(공고 제외 조건)
>
> Flow chart

* Flow Chart
* 

# 공고 검색

> 검색어와 일치하는 기업명, 공고명, 기술스택명을 포함하는 공고 리스트 제공

## MySQL - LIke



## MySQL - Full Text Search

* [[링크] MySQL Document - Full Text Restrictions](https://dev.mysql.com/doc/refman/8.0/en/fulltext-restrictions.html)

* 버전

  * MySQL 5.5 부터 사용 가능하다.
  * `isam(5.5 이상)` , `innodb(5.6 이상)` 엔진에서 사용 가능하다.

* 사용법

  * full-text index 생성

    ```mysql
    ALTER TABLE `기존 테이블명` 
    ADD FULLTEXT INDEX `인덱스명` (`컬럼명`) VISIBLE;
    ```

  * 최소 검색 글자수 설정 my.cnf ##### 아직!

    * 기본 설정 확인

      ```mysql
      show variables like '%ft_min%';
      -- ft_min_word_len = 4
      -- innodb_ft_min_token_size = 3
      ```

      * 두글자 이상 검색만 가능하다.

      * 기술스택 `C`를 검색하기 위해 한글자 검색도 허용해야 한다.

    * `my.cnf`  (mysql 위치/etc/my.cnf) 수정

      ```mysql
      ft_min_word_len = 2
      innodb_ft_min_token_size = 2
      ```

    

  * 인덱스 재설정 작업 ###???

    ```mysql
    -- innodb
    OPTIMIZE TABLE 테이블명;
    ```

  * sql

    ```mysql
    SELECT * FROM 테이블명
    WHERE MATCH(컬럼명) AGAINST('검색어1 검색어2')
    ```

    * `or` 검색
    * 일치하는 검색어가 많은 순으로 정렬된다.
    * 공백 검색시 검색결과가 없다. 

## 검색 sql

