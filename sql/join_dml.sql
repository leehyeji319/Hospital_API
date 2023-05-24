select * from doctor;
select * from department;
select * from doctor_department;


-- 의사 이름, 병원이름, 진료과, 비급여진료과목 영업시간
-- 손웅래, 메라키병원, 정형외과, 
select * from
doctor d
join hospital h
on d.hospital_id = h.id;

select 
do.doctor_name, 
h.hospital_name, 
de.department_name, 
do.non_benefit,
bh.days, 
bh.opening_time, 
bh.closing_time, 
bh.lunch_start_time, 
bh.lunch_end_time from
doctor do
join doctor_department dd
on do.id = dd.doctor_id
join department de
on dd.department_id = de.id
join hospital h
on do.hospital_id = h.id
join business_hours bh
on do.id = bh.doctor_id
;

SELECT 
	do.doctor_name,
  CONCAT(do.doctor_name, ' ', h.hospital_name, ' ', de.department_name, ' ', ifnull(do.non_benefit, '')) AS full_name,
  bh.days,
  CONCAT(bh.opening_time, ' - ', bh.closing_time) AS business_hours,
  CONCAT(bh.lunch_start_time, ' - ', bh.lunch_end_time) AS lunch_hours
FROM
  doctor do
  JOIN doctor_department dd ON do.id = dd.doctor_id
  JOIN department de ON dd.department_id = de.id
  JOIN hospital h ON do.hospital_id = h.id
  JOIN business_hours bh ON do.id = bh.doctor_id; 
-- where full_name like '혜지';

SELECT distinct
  d.doctor_name
FROM
  doctor d
  JOIN doctor_department dd ON d.id = dd.doctor_id
  JOIN department de ON dd.department_id = de.id
  JOIN hospital h ON d.hospital_id = h.id
WHERE
  CONCAT(d.doctor_name, ' ', h.hospital_name, ' ', de.department_name, ' ', IFNULL(d.non_benefit, '')) LIKE '%과%';



-- ------------
SELECT 
  CONCAT(do.doctor_name, ' ', h.hospital_name, ' ', de.department_name) AS full_name,
  do.non_benefit,
  bh.days,
  CONCAT(bh.opening_time, ' - ', bh.closing_time) AS business_hours,
  CONCAT(bh.lunch_start_time, ' - ', bh.lunch_end_time) AS lunch_hours
FROM
  doctor do
  JOIN doctor_department dd ON do.id = dd.doctor_id
  JOIN department de ON dd.department_id = de.id
  JOIN hospital h ON do.hospital_id = h.id
  JOIN business_hours bh ON do.id = bh.doctor_id
WHERE MATCH (full_name, do.non_benefit) AGAINST ('손웅래');

-- ---
ALTER TABLE doctor ADD FULLTEXT INDEX idx_full_name (doctor_name);
ALTER TABLE hospital ADD FULLTEXT INDEX idx_hospital_name (hospital_name);
ALTER TABLE department ADD FULLTEXT INDEX idx_department_name (department_name);

SELECT 
  CONCAT(do.doctor_name, ' ', h.hospital_name, ' ', de.department_name) AS full_name,
  do.non_benefit,
  bh.days,
  CONCAT(bh.opening_time, ' - ', bh.closing_time) AS business_hours,
  CONCAT(bh.lunch_start_time, ' - ', bh.lunch_end_time) AS lunch_hours
FROM
  doctor do
  JOIN doctor_department dd ON do.id = dd.doctor_id
  JOIN department de ON dd.department_id = de.id
  JOIN hospital h ON do.hospital_id = h.id
  JOIN business_hours bh ON do.id = bh.doctor_id
WHERE MATCH (do.doctor_name, h.hospital_name, de.department_name) AGAINST (' ');



