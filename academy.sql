use `academyuz`;

CREATE TABLE `instructor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name`varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name`varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_name`varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `course_fee` int DEFAULT NULL,
  `course_format` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `instructor_id` int NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(`instructor_id`) REFERENCES `instructor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

