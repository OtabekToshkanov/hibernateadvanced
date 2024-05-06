package smartup.academy.dto;

public class ReviewDTO {
    /*`id` int NOT NULL auto_increment,
	`rating` int null check(`rating`>=1 and `rating`<=10),
    `comment` varchar(256) not null,
    `course_id` int not null,
	`student_id` int not null,
    primary key (`id`),
    foreign key(`course_id`) references `course` (`id`),
    foreign key(`student_id`) references `student` (`id`)*/
    public int id;

    public int rating;

    public String userName;

    public String comment;

    public int course_id;

    public int student_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
}
