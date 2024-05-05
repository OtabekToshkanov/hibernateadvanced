package uz.smartup.academy.hibernateadvanced.dto;

import jakarta.persistence.Column;

public class StudentDTO {
    private int id;

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public StudentDTO() {};

    public StudentDTO(Builder builder) {
        this.id = builder.id;
        this.userName = builder.userName;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
    }

    static class Builder {
        private int id;

        private String userName;

        private String firstName;
        private String lastName;
        private String email;

        private String password;

        public StudentDTO.Builder id(int id) {
            this.id = id;
            return this;
        }

        public StudentDTO.Builder userName(String userName) {
            this.userName = userName;
            return this;
        }


        public StudentDTO.Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public StudentDTO.Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public StudentDTO.Builder password(String password) {
            this.password = password;
            return this;
        }

        public StudentDTO.Builder email(String email) {
            this.email = email;
            return this;
        }


        public StudentDTO build() {
            return new StudentDTO(this);
        }
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
