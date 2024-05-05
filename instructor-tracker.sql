CREATE DATABASE  IF NOT EXISTS `instructor_tracker`;
USE `instructor_tracker`;

CREATE TABLE `instructor_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `youtube_channel`varchar(145) DEFAULT NULL,
  `hobby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `instructor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name`varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `instructor_detail_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`instructor_detail_id`) REFERENCES `instructor_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `description` varchar(1000),
  `price` int not null,
  `instructor_id` int NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `review` (
	`id` int NOT NULL auto_increment,
    `rating` int not null check (`rating` >= 1 and `rating` <= 10),
    `comment` varchar(256) not null,
    `course_id` int not null,
    `student_id` int not null,
    primary key (`id`),
    foreign key(`course_id`) references `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `student` (
	`id` int NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255) NOT NULL,
    `last_name` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `course_student` (
	`course_id` int NOT NULL,
    `student_id` int NOT NULL,
    PRIMARY KEY (`course_id`, `student_id`),
	FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
	FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;