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
  CONCAT(d.doctor_name, ' ', h.hospital_name, ' ', de.department_name, ' ', IFNULL(d.non_benefit, '')) LIKE '%%';


-- 시간 검색
SELECT 
	do.doctor_name,
  bh.days,
  CONCAT(bh.opening_time, ' - ', bh.closing_time) AS business_hours,
  CONCAT(bh.lunch_start_time, ' - ', bh.lunch_end_time) AS lunch_hours
FROM
  doctor do
  JOIN business_hours bh ON do.id = bh.doctor_id;


-- 시간 between 검색
SELECT 
    doctor_name,
    days,
    business_hours,
    lunch_hours
FROM (
    SELECT 
        doctor_name,
        days,
        CONCAT(opening_time, ' - ', closing_time) AS business_hours,
        CONCAT(lunch_start_time, ' - ', lunch_end_time) AS lunch_hours
    FROM
        doctor
    JOIN
        business_hours ON doctor.id = business_hours.doctor_id
) AS subquery
WHERE
    '20:00' BETWEEN SUBSTRING_INDEX(business_hours, ' - ', 1) AND SUBSTRING_INDEX(business_hours, ' - ', -1);

SELECT 
    doctor_name
FROM (
    SELECT 
        doctor_name,
        days,
        CONCAT(opening_time, ' - ', closing_time) AS business_hours,
        CONCAT(lunch_start_time, ' - ', lunch_end_time) AS lunch_hours
    FROM
        doctor
    JOIN
        business_hours ON doctor.id = business_hours.doctor_id
) AS subquery
WHERE
    '22:50' BETWEEN SUBSTRING_INDEX(business_hours, ' - ', 1) AND SUBSTRING_INDEX(business_hours, ' - ', -1)
    AND days = 'MON';
    
    
SELECT 
    doctor_name
FROM (
    SELECT 
        doctor_name,
        days,
        CONCAT(opening_time, ' - ', closing_time) AS business_hours,
        CONCAT(lunch_start_time, ' - ', lunch_end_time) AS lunch_hours
    FROM
        doctor
    JOIN
        business_hours ON doctor.id = business_hours.doctor_id
) AS subquery
WHERE
    '22:50' BETWEEN SUBSTRING_INDEX(business_hours, ' - ', 1) AND SUBSTRING_INDEX(business_hours, ' - ', -1)
    AND days = 'MON'
    AND '22:50' BETWEEN SUBSTRING_INDEX(lunch_hours, ' - ', 1) AND SUBSTRING_INDEX(lunch_hours, ' - ', -1);


SELECT d.doctor_name FROM (    SELECT         d.doctor_name,         bh.days,         CONCAT(bh.opening_time, ' - ', bh.closing_time) AS business_hours,         CONCAT(bh.lunch_start_time, ' - ', bh.lunch_end_time) AS lunch_hours     FROM         doctor d     JOIN         business_hours bh ON d.id = bh.doctor_id ) AS subquery WHERE     'MON' BETWEEN SUBSTRING_INDEX(subquery.business_hours, ' - ', 1) AND SUBSTRING_INDEX(subquery.business_hours, ' - ', -1)     AND subquery.days = '11:10';


SELECT doctor_name FROM (    SELECT doctor_name, days, CONCAT(opening_time, ' - ', closing_time) AS business_hours, CONCAT(lunch_start_time, ' - ', lunch_end_time) AS lunch_hours    FROM doctor    JOIN business_hours ON doctor.id = business_hours.doctor_id ) AS subquery WHERE    '11:10' BETWEEN SUBSTRING_INDEX(business_hours, ' - ', 1) AND SUBSTRING_INDEX(business_hours, ' - ', -1)    AND days = 'MON';



SELECT d.doctor_name FROM (    SELECT         d.doctor_name,         bh.days,         CONCAT(bh.opening_time, ' - ', bh.closing_time) AS business_hours,         CONCAT(bh.lunch_start_time, ' - ', bh.lunch_end_time) AS lunch_hours     FROM         doctor d     JOIN         business_hours bh ON d.id = bh.doctor_id ) AS subquery WHERE     'MON' BETWEEN SUBSTRING_INDEX(subquery.business_hours, ' - ', 1) AND SUBSTRING_INDEX(subquery.business_hours, ' - ', -1)     AND subquery.days = '11:10'     AND 'MON' BETWEEN SUBSTRING_INDEX(subquery.lunch_hours, ' - ', 1) AND SUBSTRING_INDEX(subquery.lunch_hours, ' - ', -1);
