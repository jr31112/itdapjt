-- 기업 정보 테이블 정의
create table company_t(
	cid int primary key auto_increment,
    corpNm varchar(100) not null,
    totPsncnt int,
    busiSize varchar(30),
    yrSalesAmt int,
    corpAddr varchar(500),
    homePg varchar(300),
    busiCont varchar(100),
    logo varchar(100)
);
-- corpNm : 회사명, totPsncnt : 근로자수, busiSize : 기업규모, yrSalesAmt : 연매출액, corpAddr : 회사 주소
-- homePg : 회사 홈페이지, busiCont : 주요 사업내용, logo : 기업 로고

-- 사용자 정보 테이블 정의
create table user_t(
	uid int primary key auto_increment,
    email varchar(50) not null,
    pw varchar(100) not null,
    uname varchar(10) not null,
    major varchar(20),
    auth int not null default 1, -- 1: user, 0: admin
    uimg varchar(100),
    cid int,
    foreign key(cid) references company_t(cid)
);
-- email : id 겸 이메일, pw : 패스워드 (sha1로 변환하여 저장)
-- uname : 이름, major : 전공, auth : 회원 권한, uimg : 회원 사진

-- 채용 공고 테이블 정의
create table wanted_t(
	wid int primary key auto_increment,
    wantedTitle varchar(300) not null,
    active int not null default 0,	-- 0 : 마감, 1 : 진행 중, 2: 오늘 마감, 3: 내일 마감
    startDate date not null,
    endDate date not null,
    process text not null,
    etc text,
    question text,
    vcnt int not null,
    cid int not null,
    foreign key(cid) references company_t(cid) on delete cascade
);
-- wantedTitle : 공고 제목, active : 공고 진행 상태, startDate : 공고 시작일, endDate : 공고 마감일
-- process : 채용 과정, etc : 기타 요구사항, question : 문의, vcnt : 공고 조회수

-- 댓글 정보 테이블 정의
create table comment_t(
	cmid int primary key auto_increment,
    uid int not null,
    wid int not null,
    writer varchar(10) not null,
    content text not null,
    foreign key(uid) references user_t(uid) on delete cascade on update cascade,
    foreign key(wid) references wanted_t(wid) on delete cascade on update cascade
);
-- 회원이나 공고가 변경되면 댓글도 해당 댓글도 변경되도록 on delete cascade, on update cascade 설정
-- uid : fk user_t, wid : fk wanted_t, writer : 작성자 = uname, content : 댓글 내용

-- 스크랩 정보 테이블 정의
create table scrap_t(
	uid int not null,
    wid int not null,
    primary key(uid, wid),
    foreign key(uid) references user_t(uid) on delete cascade on update cascade,
    foreign key(wid) references wanted_t(wid) on delete cascade on update cascade
);
-- 회원이나 공고가 변경되면 댓글도 해당 댓글도 변경되도록 on delete cascade, on update cascade 설정
-- pk : (uid, wid), fk : uid, wid

-- 기술 스택 테이블 정의
create table stack_t(
	sid int primary key auto_increment,
    tname varchar(100) not null,
    cnt int not null default 0
);
-- tname : 기술 스택 명, cnt : 사용자가 선택한 수

-- 내 스택 정보 테이블 정의
create table myStack_t(
	uid int not null,
    sid int not null,
    primary key(uid, sid),
    foreign key(uid) references user_t(uid) on delete cascade on update cascade,
    foreign key(sid) references stack_t(sid) on delete cascade on update cascade
);
-- on delete cascade, on update cascade  / or  No action

-- 자격증 정보 테이블 정의
create table cert_t(
	ctid int primary key auto_increment,
    ctname varchar(100) not null
);
-- ctname : 자격증 명

-- 내 자격증 정보 테이블 정의
create table myCert_t(
	uid int not null,
    ctid int not null,
    primary key(uid, ctid),
    foreign key(uid) references user_t(uid) on delete cascade on update cascade,
    foreign key(ctid) references cert_t(ctid) on delete cascade on update cascade
);

-- 직무 정보 테이블 정의
create table job_t(
	jid int primary key auto_increment,
    `to` varchar(5) not null, -- 0 , 00, 000명 일 경우 숫자로 인식하면 0으로 나오기 때문에 varchar
    jname varchar(200) not null,
    jdetail text,
    jtype varchar(100),
    `require` text,
    extra text,
    place varchar(100),
    wid int not null,
    foreign key(wid) references wanted_t(wid) on delete cascade on update cascade
);
-- jname : 직무 명, to : 모집인원, jdetail : 직무 상세, jtype : 고용형태 (계약직, 정규직, ...)
-- require : 자격요건, extra : 우대사항, place : 근무지, wid : 공고 번호 fk

-- 직무 별 기술 스택 정보 테이블 정의
create table jobStack_t(
	jid int not null,
    sid int not null,
    primary key(jid, sid),
    foreign key(jid) references job_t(jid) on delete cascade on update cascade,
    foreign key(sid) references stack_t(sid) on delete cascade on update cascade
);

-- 직무 별 자격증 정보 테이블 정의
create table jobCert_t(
	jid int not null,
    ctid int not null,
    primary key(jid, ctid),
    foreign key(jid) references job_t(jid) on delete cascade on update cascade,
    foreign key(ctid) references cert_t(ctid) on delete cascade on update cascade
);

-- 기술 스택 추가 요청 테이블 정의
create table reqStack_t(
	rsid int primary key auto_increment,
    tname varchar(100) not null,
    uid int not null,
    foreign key(uid) references user_t(uid)
);
-- 사용자가 탈퇴하고 나가도 요청한건 유지할 의미가 있을 순 있기 때문에 얘는 on delete No action
