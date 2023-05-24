# 메라키플레이스 백엔드 직무 과제

# 메라키플레이스 백엔드 직무

## 지원자 이혜지

# 프로젝트 셋팅 방법

---

## 개발 환경

```
Spring Boot '2.7.11' version
Gradle Project
Java 11
MySQL 사용
port 8080 포트 사용
```

## 압축 파일로 셋팅

[test.zip](%E1%84%86%E1%85%A6%E1%84%85%E1%85%A1%E1%84%8F%E1%85%B5%E1%84%91%E1%85%B3%E1%86%AF%E1%84%85%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%89%E1%85%B3%20%E1%84%87%E1%85%A2%E1%86%A8%E1%84%8B%E1%85%A6%E1%86%AB%E1%84%83%E1%85%B3%20%E1%84%8C%E1%85%B5%E1%86%A8%E1%84%86%E1%85%AE%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%20ab226f7e02a8406cab9c3640ba150948/test.zip)

해당 알집 파일을 풀고 IntelliJ → File → Project from Existing Sources를 눌러 열어주세요.

<img width="523" alt="image" src="https://github.com/leehyeji319/Hospital_API/assets/50399088/f55174c4-5e96-4899-a794-ae28b0b5abff">


압축을 푼 폴더에서 **build.gradle**을 눌러 **as a Project** 선택해주세요.

application.yml 파일을 설정해주세요.

mysql 설정을 해주세요.

`DBNAME : 데이터베이스명`

`USERNAME : 데이터베이스 유저 네임`

`PASSWORD : 데이터베이스 비밀번호`

```yaml
server:
  port: 8080

  servlet:
    context-path: /
    encoding:
      charset: utf-8
      enabled: true

spring:
  datasource:
			# 여기에 설정해주시면 됩니다.
       url: jdbc:mysql://localhost:3306/{DBNAME}?useUnicode=true&characterEncoding=utf-8
       username: {USERNAME}
       password: {PASSWORD}
       driver-class-name: com.mysql.cj.jdbc.Driver

  output.ansi.enabled: always

  jpa:
    hibernate:
      ddl-auto: update
      use-new-id-generator-mappings: false
    show-sql: false
    properties:
      hibernate:
        format_sql: true

  jackson:
    serialization:
      write-dates-as-timestamps: false
    time-zone: Asia/Seoul

```

## DB 설정

---

먼저 데이터베이스를 생성해주세요.

```sql
create database '데이터베이스명';
```

데이터베이스 생성 후 

현재 `ddl-auto` 가 `update` 로 되어있으므로

- TestApplication.java 파일에서 실행을 하여서 table을 create 하거나
    
    <img width="514" alt="image" src="https://github.com/leehyeji319/Hospital_API/assets/50399088/7ce2b908-29e8-42c3-9771-4a0742be1bbf">

    

혹은 아래의 DDL문을 생성 해 주세요.

- 파일
    
    [ddl.sql](%E1%84%86%E1%85%A6%E1%84%85%E1%85%A1%E1%84%8F%E1%85%B5%E1%84%91%E1%85%B3%E1%86%AF%E1%84%85%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%89%E1%85%B3%20%E1%84%87%E1%85%A2%E1%86%A8%E1%84%8B%E1%85%A6%E1%86%AB%E1%84%83%E1%85%B3%20%E1%84%8C%E1%85%B5%E1%86%A8%E1%84%86%E1%85%AE%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%20ab226f7e02a8406cab9c3640ba150948/ddl.sql)
    
- ddl문
    
    ```sql
    
    -- merakiplaceS.department definition
    
    CREATE TABLE `department` (
      `id` bigint NOT NULL AUTO_INCREMENT,
      `department_name` varchar(255) DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    
    -- merakiplaceS.hospital definition
    
    CREATE TABLE `hospital` (
      `id` bigint NOT NULL AUTO_INCREMENT,
      `hospital_name` varchar(255) DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    
    -- merakiplaceS.patient definition
    
    CREATE TABLE `patient` (
      `id` bigint NOT NULL AUTO_INCREMENT,
      `name` varchar(255) DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    
    -- merakiplaceS.doctor definition
    
    CREATE TABLE `doctor` (
      `id` bigint NOT NULL AUTO_INCREMENT,
      `doctor_name` varchar(255) DEFAULT NULL,
      `non_benefit` varchar(255) DEFAULT NULL,
      `hospital_id` bigint DEFAULT NULL,
      PRIMARY KEY (`id`),
      KEY `FKds7ws3yyj4c5wj35fpefpeny0` (`hospital_id`),
      CONSTRAINT `FKds7ws3yyj4c5wj35fpefpeny0` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    
    -- merakiplaceS.doctor_department definition
    
    CREATE TABLE `doctor_department` (
      `id` bigint NOT NULL AUTO_INCREMENT,
      `department_id` bigint DEFAULT NULL,
      `doctor_id` bigint DEFAULT NULL,
      PRIMARY KEY (`id`),
      KEY `FKckfmw76jmj9qxrgo4e109xg62` (`department_id`),
      KEY `FKeiyikn6jbglrmkr5r384rx2xm` (`doctor_id`),
      CONSTRAINT `FKckfmw76jmj9qxrgo4e109xg62` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE CASCADE,
      CONSTRAINT `FKeiyikn6jbglrmkr5r384rx2xm` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    
    -- merakiplaceS.reservation definition
    
    CREATE TABLE `reservation` (
      `id` bigint NOT NULL AUTO_INCREMENT,
      `expired_date_time` datetime(6) DEFAULT NULL,
      `reservation_date_time` datetime(6) DEFAULT NULL,
      `status` varchar(255) DEFAULT NULL,
      `doctor_id` bigint DEFAULT NULL,
      `patient_id` bigint DEFAULT NULL,
      PRIMARY KEY (`id`),
      KEY `FKmh96fg24x7cdhex42l051fmhp` (`doctor_id`),
      KEY `FKrrjvkskqqxgliwmqgbl3ijc4n` (`patient_id`),
      CONSTRAINT `FKmh96fg24x7cdhex42l051fmhp` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE,
      CONSTRAINT `FKrrjvkskqqxgliwmqgbl3ijc4n` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    
    -- merakiplaceS.business_hours definition
    
    CREATE TABLE `business_hours` (
      `id` bigint NOT NULL AUTO_INCREMENT,
      `closing_time` time DEFAULT NULL,
      `days` varchar(255) DEFAULT NULL,
      `lunch_end_time` time DEFAULT NULL,
      `lunch_start_time` time DEFAULT NULL,
      `opening_time` time DEFAULT NULL,
      `doctor_id` bigint DEFAULT NULL,
      PRIMARY KEY (`id`),
      KEY `FKoi6d7wr11d8xcf82n749dmm6` (`doctor_id`),
      CONSTRAINT `FKoi6d7wr11d8xcf82n749dmm6` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    ```
    

데이터베이스 생성을 마쳤습니다.

## 생성된 ERD

<img width="683" alt="image" src="https://github.com/leehyeji319/Hospital_API/assets/50399088/45680853-a149-4b2f-9a7e-8bd7ab7293e9">

- 간단한 ERD 설명
    
    **병원 테이블**
    
    - 병원은 여러명의 의사를 데리고 있다. → 일대다 관계
    
    **의사 테이블**
    
    - 의사는 진료과 개수를 여러개 들고 있을 수 있다. → 일대다 관계
    - 의사는 예약을 받을 수 있고, 여러개의 예약 진료를 가질 수 있기 때문에 Reservation 테이블 → 일대다 관계
    - 의사는 여러개의 영업 시간을 가지고 있다 → 일대다관계
    
    **환자 테이블**
    
    - 환자는 여러개의 예약을 할 수 있으므로 Reservation 테이블과 → 일대다 관계
    
    **예약 테이블**
    
    - Redis에 저장한 해시값을 저장해야하므로, Redis_hash_id값을 가지고 있어야한다.
    - 그리고 해당 예약의 상태를 알 수 있는 enumType의 컬럼을 넣었다.
        - STATUS 컬럼 - NONE, APPROVED, EXPIRED
    - 일종의 중계테이블이다.
    - 1:N N:1 관계이다.
    
    **의사진료과목 테이블**
    
    - 의사는 여러개의 진료과목을 갖을 수 있다.
    - 진료 과목 한개는 여러명의 의사가 있을 수 있다.
    - 의사 - 진료 간의 중계테이블이다.
    - 1 : N N : 1 관계
    
    **진료시간 테이블**
    
    - 의사마다 요일당 다른 영업시간을 가지고 있다.
    - N : 1 관계
    
    가정한것
    
    - 의사의 비급여는 한개밖에 가지지 못합니다.
    - 공휴일은 상관없이, 지정된 요일엔 모두 영업합니다.

## Dummy Data 셋팅

---

기본적인 dummyData를 insert하겠습니다.

- 파일
    
    [dummy.sql](%E1%84%86%E1%85%A6%E1%84%85%E1%85%A1%E1%84%8F%E1%85%B5%E1%84%91%E1%85%B3%E1%86%AF%E1%84%85%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%89%E1%85%B3%20%E1%84%87%E1%85%A2%E1%86%A8%E1%84%8B%E1%85%A6%E1%86%AB%E1%84%83%E1%85%B3%20%E1%84%8C%E1%85%B5%E1%86%A8%E1%84%86%E1%85%AE%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%20ab226f7e02a8406cab9c3640ba150948/dummy.sql)
    
- 더미데이터
    
    ```sql
    use 데이터베이스명;
    
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
    -- 손웅래
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
    -- 선재원
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
    ```
    

# API 명세서

---

api 명세서는 죄송하지만 notion링크를 첨부하겠습니다.

[API 명세서](https://www.notion.so/0fab39a44fb9497c8763cca894ce3487)



## 다양한 고민을 하며 썼던 노션입니다.


[노션페이지](https://tropical-krypton-b8a.notion.site/a31e5ae6119a4b4396ee15355a49415c)