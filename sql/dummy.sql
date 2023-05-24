use merakiplaceS;

-- select * from department;
-- select * from business_hours;
-- select * from hospital;
-- select * from doctor;
-- select * from doctor_department;
-- select * from patient;

-- 환자 정보 dummy date insert
insert into patient(name) values ('강주영');
insert into patient(name) values ('이민우');
insert into patient(name) values ('오종석');
insert into patient(name) values ('변준우');
insert into patient(name) values ('최지연');
insert into patient(name) values ('황수정');
insert into patient(name) values ('고진석');

-- 진료 과목 dummy data insert
insert into department(department_name) values ('정형외과');
insert into department(department_name) values ('내과');
insert into department(department_name) values ('한의사');
insert into department(department_name) values ('일반의');
insert into department(department_name) values ('이비인후과');
insert into department(department_name) values ('산부인과');
insert into department(department_name) values ('외과');
insert into department(department_name) values ('소아과');
insert into department(department_name) values ('신경외과');

-- 병원 정보 dummy data insert
insert into hospital(hospital_name) values('메라키병원');
insert into hospital(hospital_name) values('싸피병원');
insert into hospital(hospital_name) values('서울병원');
insert into hospital(hospital_name) values('역삼병원');

-- 의사 정보 dummy data insert 
insert into doctor(doctor_name, non_benefit, hospital_id) values ('손웅래', null, 1);
insert into doctor(doctor_name, non_benefit, hospital_id) values ('선재원', '다이어트약', 1);
insert into doctor(doctor_name, non_benefit, hospital_id) values ('손흥민', '다이어트약', 1);
insert into doctor(doctor_name, non_benefit, hospital_id) values ('이혜지', '비염약', 2);
insert into doctor(doctor_name, non_benefit, hospital_id) values ('나닥터', '비염약', 2);
insert into doctor(doctor_name, non_benefit, hospital_id) values ('이재용', null, 3);

-- 영업 시간 dummy data insert
-- 손웅래 병원 
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'MON', '09:00', '19:00', '11:00', '12:00', 1
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'TUE', '09:00', '19:00', '11:00', '12:00', 1
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'WED', '09:00', '19:00', '11:00', '12:00', 1
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'THU', '09:00', '19:00', '11:00', '12:00', 1
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'FRI', '09:00', '19:00', '11:00', '12:00', 1
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'SAT', null, null, null, null, 1
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'SUN', null, null, null, null, 1
);
-- 선재원 병원
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'MON', '08:00', '17:00', '12:00', '1:00', 2
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'TUE', '08:00', '17:00', '12:00', '1:00', 2
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'WED', '08:00', '17:00', '12:00', '1:00', 2
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'THU', '08:00', '17:00', '12:00', '1:00', 2
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'FRI', '08:00', '17:00', '12:00', '1:00', 2
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'SAT', '08:00', '13:00', null, null, 2
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'SUN', null, null, null, null, 2
);

-- 손흥민 
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'MON', '08:00', '17:00', '12:00', '1:00', 3
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'TUE', '08:00', '17:00', '12:00', '1:00', 3
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'WED', '08:00', '17:00', '12:00', '1:00', 3
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'THU', '09:00', '19:00', '12:00', '1:00', 3
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'FRI', '08:00', '17:00', '12:00', '1:00', 3
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'SAT', '08:00', '14:00', null, null, 3
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'SUN', '08:00', '14:00', null, null, 3
);

-- 이혜지
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'MON', '09:00', '14:00', '12:00', '1:00', 4
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'TUE', '09:00', '14:00', '12:00', '1:00', 4
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'WED', '09:00', '14:00', '12:00', '1:00', 4
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'THU', '09:00', '14:00', '12:00', '1:00', 4
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'FRI', '09:00', '14:00', '12:00', '1:00', 4
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'SAT', '09:00', '14:00', '12:00', '1:00', 4
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'SUN', '09:00', '14:00', '12:00', '1:00', 4
);

-- 나닥터
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'MON', '09:00', '20:00', '12:00', '1:00', 5
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'TUE', '09:00', '20:00', '12:00', '1:00', 5
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'WED', '09:00', '20:00', '12:00', '1:00', 5
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'THU', null, null, null, null, 5
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'FRI', null, null, null, null, 5
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'SAT', '09:00', '20:00', '12:00', '1:00', 5
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'SUN', '09:00', '20:00', '12:00', '1:00', 5
);

-- 이재용
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'MON', '09:00', '18:00', '12:00', '1:00', 6
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'TUE', null, null, null, null, 6
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'WED', null, null, null, null, 6
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'THU', null, null, null, null, 6
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'FRI', null, null, null, null, 6
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'SAT', null, null, null, null, 6
);
insert into business_hours(
days, opening_time, closing_time, lunch_start_time, lunch_end_time, doctor_id) values (
'SUN', null, null, null, null, 6
);


-- 진료 과목 매핑
-- 손웅래
insert into doctor_department(department_id, doctor_id) values (1, 1);
insert into doctor_department(department_id, doctor_id) values (2, 1);
insert into doctor_department(department_id, doctor_id) values (4, 1);

-- 선재원
insert into doctor_department(department_id, doctor_id) values (3, 2);
insert into doctor_department(department_id, doctor_id) values (4, 2);

-- 손흥민
insert into doctor_department(department_id, doctor_id) values (1, 3);
insert into doctor_department(department_id, doctor_id) values (4, 3);
insert into doctor_department(department_id, doctor_id) values (5, 3);
insert into doctor_department(department_id, doctor_id) values (7, 3);

-- 이혜지
insert into doctor_department(department_id, doctor_id) values (8, 4);
insert into doctor_department(department_id, doctor_id) values (9, 4);

-- 나닥터
insert into doctor_department(department_id, doctor_id) values (2, 5);
insert into doctor_department(department_id, doctor_id) values (6, 5);

-- 이재용
insert into doctor_department(department_id, doctor_id) values (6, 6);
