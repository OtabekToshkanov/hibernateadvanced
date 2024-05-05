package uz.smartup.academy.hibernateadvanced.dto;

public class CourseDTO {
    private int id;
    private String title;
    private String description;
    private int price;
    private int instructorId;

    public CourseDTO(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.price = builder.price;
        this.instructorId = builder.instructorId;
    }

    public CourseDTO() {
    }

    public static class Builder {
        private int id;
        private String title;
        private String description;
        private int price;
        private int instructorId;

        public Builder id(int id) {
            this.id = id;
            return this;
        }


        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(int price) {
            this.price = price;
            return this;
        }

        public Builder instructorId(int instructorId) {
            this.instructorId = instructorId;
            return this;
        }

        public CourseDTO build() {
            return new CourseDTO(this);
        }
    }

    public void setId(int id) {this.id = id; }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", instructorId=" + instructorId +
                '}';
    }
}
