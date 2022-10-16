package fr.alexandre.user_questioner.dao;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class User {

    @Id
    public String id;

    public String firstname;

    public String lastname;

    public String email;

    public String password;

    public String nickname;

    public String address;

    public String phoneNumber;

    public String countryCode;

    public Role role;

    public LocalDate birthdate;

    public User(String firstname, String lastname, LocalDate birthdate, String email, String password, String nickname, String address, String phoneNumber, String countryCode, Role role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
        this.role = role;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", role=" + role +
                ", birthdate=" + birthdate +
                '}';
    }
}
