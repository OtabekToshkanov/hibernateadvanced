package uz.smartup.academy.hibernateadvanced.dto;

import jakarta.persistence.Column;

public class ReviewDTO {
    private int id;
    private int rating;
    private String comment;

    public ReviewDTO() {}

    public ReviewDTO(Builder builder) {
        this.id = builder.id;
        this.comment = builder.comment;
        this.rating = builder.rating;
    }

    static class Builder {
        private int id;

        private String comment;

        private int rating ;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public ReviewDTO build() {
            return new ReviewDTO(this);
        }
    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

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
}
