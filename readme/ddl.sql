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