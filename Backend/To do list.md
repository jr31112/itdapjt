## To do list

### 공고

#### CUD 

#### C ->기업 정보/공고/직무 따로

1. company_t  -> Company param

   if (cid가 있으면) cid를 쓴다.

   else(cid 없으면) company insert

   ```sql
   insert into company_t(corpNm, totPsncnt, busiSize, yrSalesAmt, corpAddr, homePg, busiCont, logo) values ('','','','','','','','');
   ```

   `createCompany`

2. wanted_t -> Wanted param

   (1번에서 사용한 cid를 저장)

   if(1번에서 cid를 받아왔으면) 그거 쓰고

   else(1번에서 입력한 comany insert의 cid를 받아와서) 쓰기

   ```sql
   insert into wanted_t(wantedTitle, startDate, endDate, process, etc, question, cid) values('','','','','','',0);
   ```

   `createWanted`

3. job_t -> Job param

   2번에서 입력한 wid를 사용

   ```sql
   insert into job_t(jname, `to`, jdetail, jtype, `require`, extra, place, wid) values ('','','','','','','',0);
   ```



요까지 필수!

-----------------------



1. jobstack_t (얘는 우선순위 낮추기)

   직무번호 jid, 스택번호 sid를 입력하는데 

   스택리스트에서 각 스택의 번호를 select 해서 입력에 넣어야합니다.....ㅠ

   ```sql
   insert into jobstack_t(jid, sid) values(0,0);
   ```



#### D

- company가 없어지는 경우 : delete from company_t where cid = #{cid}
  - `deleteCompany`
- wanted가 없어지는 경우 : delete from wanted_t where wid = #{wid}
  - `deleteWanted`
- job이 없어지는 경우 : delete from job_t where jid = #{jid}
- 직무별 기술스택을 지울 경우 : delte from jobstack_t where jid = #{jid} and sid = #{sid} -> Jobstack 모델 별도



#### U

- 공고 내용만 수정 : wanted 에서 수정하기 -> Wanted 가 param
  * `updateWanted`
- 직무 내용만 수정 : job_t 수정 -> Job 이 param
- 기업 정보 수정 : company_t 수정 -> Company 가 param
  - `updateCompany`







### 스크랩 - R 만 필요 -> 내 스크랩 목록

uid, wid

user가 wanted 를 스크랩



### 댓글 - CRUD

cmid - 자동

uid - > 입력한 사람의 uid

wid -> 어느 공고에서

writer -> user의 uname

content -> 내용

