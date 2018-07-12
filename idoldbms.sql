/* 아이돌 테이블 생성 */
create table idol_tb(
	idol_id number(7) primary key,
	idol_name varchar(20) not null,
	age number(3),
	height number(3),
	weight number(3),
	birth_month number(2),
	birth_date number(2),
	blood_type varchar(2),
	bust number(3),
	waist number(3),
	hip number(3),
	hobby varchar(20),
	hometown varchar(20),
	image_color varchar(20),
	company varchar(20),
	primary_hand varchar(10),
	speciality varchar(20),
	like_food varchar(20),
	dislike_food varchar(20),
	virtue varchar(20),
	charm varchar(20),
	dream varchar(20),
	strong_subject varchar(20),
	cook varchar(20),
	first_person varchar(30),
	personal_icon bfile,
	face bfile
);

/* 아이돌 테이블 삭제 */
drop table idol_tb;

/* 아이돌 테이블 보기 */
select * from idol_tb;

/* 아이돌 일련번호를 위한 시퀀스 */
create sequence idol_seq
	start with 1
	increment by 1
	maxvalue 1000000
	nocycle;
	
/* 아이돌 일련번호를 위한 시퀀스가 생성되었는지 확인 */
select idol_seq.nextval from dual;
select idol_seq.currval from dual;

/* 임의의 아이돌 정보 추가 */
insert into idol_tb
	(idol_id, idol_name, age, height, weight, birth_month, birth_date, blood_type, bust, waist, hip, hobby, hometown, image_color, company)
	values (idol_seq.nextval, '아마미 하루카', 17, 158, 46, 4, 3, 'O', 83, 56, 82, '과자 굽기', '카나가와', '빨간색', '765 프로덕션');
insert into idol_tb
	(idol_id, idol_name, age, height, weight, birth_month, birth_date, blood_type, bust, waist, hip, hobby, hometown, image_color, company)
	values (idol_seq.nextval, '키사라기 치하야', 16, 162, 41, 2, 25, 'A', 72, 55, 78, '음악감상, 트레이닝', '도쿄', '파란색', '765 프로덕션');
insert into idol_tb
	(idol_id, idol_name, age, height, weight, birth_month, birth_date, blood_type, bust, waist, hip, hobby, hometown, image_color, company)
	values (idol_seq.nextval, '타카츠키 야요이', 14, 145, 37, 3, 25, 'O', 74, 54, 78, '오델로, 야구', '사이타마', '오렌지', '765 프로덕션');
insert into idol_tb
	(idol_id, idol_name, age, height, weight, birth_month, birth_date, blood_type, bust, waist, hip, hobby, hometown, image_color, company)
	values (idol_seq.nextval, '아키즈키 리츠코', 19, 156, 43, 6, 23, 'A', 85, 57, 85, '자격증 취득', '도쿄', '녹색', '765 프로덕션');
insert into idol_tb
	(idol_id, idol_name, age, height, weight, birth_month, birth_date, blood_type, bust, waist, hip, hobby, hometown, image_color, company)
	values (idol_seq.nextval, '미우라 아즈사', 21, 168, 48, 7, 19, 'O', 91, 59, 86, '개와 산책하기', '치바', '보라색', '765 프로덕션');
insert into idol_tb
	(idol_id, idol_name, age, height, weight, birth_month, birth_date, blood_type, bust, waist, hip, hobby, hometown, image_color, company)
	values (idol_seq.nextval, '미나세 이오리', 15, 153, 40, 5, 5, 'AB', 77, 54, 79, '해외여행, 맛집 순회', '도쿄', '분홍색', '765 프로덕션');
insert into idol_tb
	(idol_id, idol_name, age, height, weight, birth_month, birth_date, blood_type, bust, waist, hip, hobby, hometown, image_color, company)
	values (idol_seq.nextval, '키쿠치 마코토', 17, 159, 44, 8, 29, 'O', 75, 57, 78, '스포츠', '시즈오카', '검은색', '765 프로덕션');
insert into idol_tb
	(idol_id, idol_name, age, height, weight, birth_month, birth_date, blood_type, bust, waist, hip, hobby, hometown, image_color, company)
	values (idol_seq.nextval, '후타미 아미', 13, 158, 42, 5, 22, 'B', 78, 55, 77, '메일', '도쿄', '노란색', '765 프로덕션');
insert into idol_tb
	(idol_id, idol_name, age, height, weight, birth_month, birth_date, blood_type, bust, waist, hip, hobby, hometown, image_color, company)
	values (idol_seq.nextval, '후타미 마미', 13, 158, 42, 5, 22, 'B', 78, 55, 77, '메일, 분재', '도쿄', '노란색', '765 프로덕션');
insert into idol_tb
	(idol_id, idol_name, age, height, weight, birth_month, birth_date, blood_type, bust, waist, hip, hobby, hometown, image_color, company)
	values (idol_seq.nextval, '호시이 미키', 15, 161, 45, 11, 23, 'B', 86, 55, 83, '네일아트', '카나가와', '연녹색', '765 프로덕션');
insert into idol_tb
	(idol_id, idol_name, age, height, weight, birth_month, birth_date, blood_type, bust, waist, hip, hobby, hometown, image_color, company)
	values (idol_seq.nextval, '시죠 타카네', 18, 169, 49, 1, 21, 'B', 90, 62, 92, '천체 관즉, 역사', '교토?', '연지색', '765 프로덕션');
insert into idol_tb
	(idol_id, idol_name, age, height, weight, birth_month, birth_date, blood_type, bust, waist, hip, hobby, hometown, image_color, company)
	values (idol_seq.nextval, '하기와라 유키호', 17, 155, 42, 12, 24, 'A', 81, 56, 81, '시쓰기, 다과, 블로그', '도쿄', '흰색', '765 프로덕션');
insert into idol_tb
	(idol_id, idol_name, age, height, weight, birth_month, birth_date, blood_type, bust, waist, hip, hobby, speciality, hometown, image_color, company)
	values (idol_seq.nextval, '가나하 히비키', 	16, 152, 41, 10, 10, 'A', 83, 56, 80, '애완동물 기르기', '가사 전반', '오키나와', '하늘색', '765 프로덕션');
insert into idol_tb
	(idol_id, idol_name, age, birth_month, birth_date, blood_type, height, bust, waist, hip, hobby, speciality, like_food, dislike_food, virtue, charm, dream, strong_subject, cook, first_person, image_color, company)
	values (idol_seq.nextval, '코사카 호노카', 16, 8, 3, 'O', 157, 78, 58, 82, '수영, 스티커 수집', '돈 줍기', '딸기, 빵', '피망', '노력가, 솔직함', '밝은 미소!', '꽃집 주인', '체육, 미술', '아게만쥬', '와타시(私), 호노카', '주황색', '오토노키자카 학원');
insert into idol_tb
	(idol_id, idol_name, age, birth_month, birth_date, blood_type, height, bust, waist, hip, hobby, speciality, like_food, dislike_food, virtue, charm, dream, strong_subject, cook, first_person, image_color, company)
	values (idol_seq.nextval, '아야세 에리', 17, 10, 21, 'B', 162, 88, 60, 84, '퀼트나 악세사리 제작', '러시아어, 옷 만들기', '초콜릿', '우메보시, 김', '인기 많음, 자존심', '맺고 끊음이 확실', '화학, 영어', '아나운서', '보르시치', '私, 에리, 에리치카', '하늘색', '오토노키자카 학원');
insert into idol_tb
	(idol_id, idol_name, age, birth_month, birth_date, blood_type, height, bust, waist, hip, hobby, speciality, like_food, dislike_food, virtue, charm, dream, strong_subject, cook, first_person, image_color, company)
	values (idol_seq.nextval, '미나미 코토리', 16, 9, 12, 'O', 159, 80, 58, 80, '과자 만글기', '유연함', '치즈케이크, 마카롱', '마늘', '치유계', '상냥해 보이는 눈', '유치원 선생님', '영어, 국어', '고기감자조림', '와타시, 코토리', '회색, 하얀색', '오토노키자카 학원');
insert into idol_tb
	(idol_id, idol_name, age, birth_month, birth_date, blood_type, height, bust, waist, hip, hobby, speciality, like_food, dislike_food, virtue, charm, dream, strong_subject, cook, first_person, image_color, company)
	values (idol_seq.nextval, '소노다 우미', 16, 3, 15, 'A', 159, 78, 58, 80, '독서, 서예', '일본무용, 검도', '호노카네 집의 만쥬', '탄산음료', '의지, 집중력', '검은 머리', '무술가', '고전문학, 한문', '만두, 볶음밥', '와타시(私)', '파란색', '오토노키자카 학원');
	

/* 아이돌 정보를 전부 삭제 */
delete idol_tb;

/* 유닛 테이블 생성 */
create table unit_tb(
	unit_id number(7) primary key,
	unit_name varchar2(20) unique not null,
	company varchar2(20)
);

/* 유닛 테이블 삭제 */
drop table unit_tb;

/* 유닛 테이블 보기 */
select * from unit_tb;

/* 유닛 일련번호를 위한 시퀀스 */
create sequence unit_seq
	start with 1
	increment by 1
	maxvalue 1000000
	nocycle;
	
/* 유닛 일련번호를 위한 시퀀스가 생성되었는지 확인 */
select unit_seq.nextval from dual;
select unit_seq.currval from dual;

/* 임의의 유닛 정보 추가 */
insert into unit_tb values (unit_seq.nextval, '류구 코마치', '765 프로덕션');
insert into unit_tb values (unit_seq.nextval, '프로젝트 페어리', '961 프로덕션');
insert into unit_tb values (unit_seq.nextval, 'Printemps', '오토노키자카 학원');
insert into unit_tb values (unit_seq.nextval, 'BiBi', '오토노키자카 학원');
insert into unit_tb values (unit_seq.nextval, 'Lily White', '오토노키자카 학원');

/* 유닛 활동 테이블 생성 */
create table unit_activity_tb(
	id number(7) primary key,
	idol_id number(7) constraint idol_id_fk references idol_tb,
	unit_id number(7) constraint unit_id_fk references unit_tb,
	join_date date not null,
	leave_date date
);

/* 유닛 활동 일련번호를 위한 시퀀스 */
create sequence unit_activity_seq
	start with 1
	increment by 1
	maxvalue 1000000
	nocycle;

/* 임의의 유닛 활동 정보 추가 */
insert into unit_activity_tb values(
	unit_activity_seq.nextval,
	(select idol_id from idol_tb where idol_name='미우라 아즈사'),
	(select unit_id from unit_tb where unit_name='류구 코마치'),
	(select to_date('2011-02-24', 'YYYY-MM-DD') from dual),
	null
);
insert into unit_activity_tb values(
	unit_activity_seq.nextval,
	(select idol_id from idol_tb where idol_name='미나세 이오리'),
	(select unit_id from unit_tb where unit_name='류구 코마치'),
	(select to_date('2011-02-24', 'YYYY-MM-DD') from dual),
	null
);
insert into unit_activity_tb values(
	unit_activity_seq.nextval,
	(select idol_id from idol_tb where idol_name='후타미 아미'),
	(select unit_id from unit_tb where unit_name='류구 코마치'),
	(select to_date('2011-02-24', 'YYYY-MM-DD') from dual),
	null
);
insert into unit_activity_tb values(
	unit_activity_seq.nextval,
	(select idol_id from idol_tb where idol_name='가나하 히비키'),
	(select unit_id from unit_tb where unit_name='프로젝트 페어리'),
	(select to_date('2009-02-19', 'YYYY-MM-DD') from dual),
	(select to_date('2011-12-23', 'YYYY-MM-DD') from dual)
);
insert into unit_activity_tb values(
	unit_activity_seq.nextval,
	(select idol_id from idol_tb where idol_name='시죠 타카네'),
	(select unit_id from unit_tb where unit_name='프로젝트 페어리'),
	(select to_date('2009-02-19', 'YYYY-MM-DD') from dual),
	(select to_date('2011-12-23', 'YYYY-MM-DD') from dual)
);
insert into unit_activity_tb values(
	unit_activity_seq.nextval,
	(select idol_id from idol_tb where idol_name='호시이 미키'),
	(select unit_id from unit_tb where unit_name='프로젝트 페어리'),
	(select to_date('2009-02-19', 'YYYY-MM-DD') from dual),
	(select to_date('2011-12-23', 'YYYY-MM-DD') from dual)
);

/* 유닛 활동 전체보기 */
select * from unit_activity_tb;

/* 아이돌 일련번호로 그 아이돌이 현재 소속되어 있는 유닛을 검색 */
/* 테스트할 때는 id를 항상 재확인할 것 */
select * from unit_tb where unit_id in(select unit_id from unit_activity_tb where idol_id=84 and leave_date is null);

/* 아이돌 일련번호로 그 아이돌이 이전에 소속되어 있었던 유닛을 검색 */
/* 테스트할 때는 id를 항상 재확인할 것 */
select * from unit_tb where unit_id in(select unit_id from unit_activity_tb where idol_id=89 and leave_date is not null);

/* 유닛 일련번호로 현재 소속되어 있는 멤버들을 검색 */
/* 테스트할 때는 id를 항상 재확인할 것 */
select * from idol_tb where idol_id in(select idol_id from unit_activity_tb where unit_id=20 and leave_date is null);

/* 유닛 일련번호로 이전에 소속되어 있었던 멤버들을 검색 */
/* 테스트할 때는 id를 항상 재확인할 것 */
select * from idol_tb where idol_id in(select idol_id from unit_activity_tb where unit_id=21 and leave_date is not null);

/* 그룹 테이블 생성 */
create table group_tb(
	group_id number(7) primary key,
	group_name varchar2(20) unique not null,
	company varchar2(20)
);

/* 그룹 테이블 제거 */
drop table group_tb;

/* 그룹 일련번호를 위한 시퀀스 생성 */
create sequence group_seq
	start with 1
	increment by 1
	maxvalue 1000000
	nocycle;
	
/* 임의의 그룹 데이터 추가 */
insert into group_tb values (group_seq.nextval, 'μ`s', '오토노키자카 학원');

/* 그룹 테이블 전체 보기 */
select * from group_tb;

/* 그룹 활동 테이블 생성 */
create table group_activity_tb(
	id number(7) primary key,
	idol_id number(7) constraint idol_id_fk references idol_tb,
	group_id number(7) constraint group_id_fk references group_tb,
	join_date date not null,
	leave_date date
);

/* 그룹 활동 테이블 제거 */
drop table group_activity_tb;

/* 그룹 활동 일련번호를 위한 시퀀스 생성 */
create sequence group_activity_seq
	start with 1
	increment by 1
	maxvalue 1000000
	nocycle;

/* 그룹 활동 테이블 전체 보기 */
select * from group_activity_tb;

/* 57 */
