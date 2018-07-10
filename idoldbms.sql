/* 아이돌 테이블 생성 */
create table idol_tb(
	id number(7) primary key,
	name varchar(20) not null,
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
	voice_actor varchar(20),
	company varchar(20),
	primary_hand varchar(10),
	speciality varchar(20),
	like_food varchar(20),
	dislick_food varchar(20),
	virtue varchar(20),
	charm varchar(20),
	dream varchar(20),
	subject_good_at varchar(20),
	cook varchar(20),
	first_person varchar(10),
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
	(id, name, age, height, weight, birth_month, birth_date, blood_type, bust, waist, hip, hobby, hometown, image_color, company)
	values (idol_seq.nextval, '아마미 하루카', 17, 158, 46, 4, 3, 'O', 83, 56, 82, '과자 굽기', '카나가와', '빨간색', '765 프로덕션');
insert into idol_tb
	(id, name, age, height, weight, birth_month, birth_date, blood_type, bust, waist, hip, hobby, hometown, image_color, company)
	values (idol_seq.nextval, '하기와라 유키호', 17, 155, 42, 12, 24, 'A', 81, 56, 81, '시쓰기, 다과, 블로그', '도쿄', '흰색', '765 프로덕션');
	

/* test */
