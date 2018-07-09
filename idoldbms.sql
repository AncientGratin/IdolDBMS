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

