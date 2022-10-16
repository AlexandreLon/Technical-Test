package fr.alexandre.user_registry.dto;

import fr.alexandre.user_registry.dao.Role;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

public class UserDTO {
    @ApiModelProperty(notes = "firstname", example = "John", required = true)
    public String firstname;

    @ApiModelProperty(notes = "lastname", example = "Doe", required = true)
    public String lastname;

    @ApiModelProperty(notes = "email", example = "john.doe@admin.com", required = true)
    public String email;

    @ApiModelProperty(notes = "password", example = "P@ss0ord", required = true)
    public String password;

    @ApiModelProperty(notes = "password", example = "P@ss0ord", required = true)
    public String confirmPassword;

    @ApiModelProperty(notes = "nickname", example = "JohnDoe", required = true)
    public String nickname;

    @ApiModelProperty(notes = "address", example = "1 rue de France", required = false)
    public String address;

    @ApiModelProperty(notes = "address", example = "+33123345678", required = false)
    public String phoneNumber;

    @ApiModelProperty(notes = "countryCode", example = "FR", required = true)
    public String countryCode;

    @ApiModelProperty(notes = "birthDate", example = "1990-01-01", required = true)
    public LocalDate birthdate;

    @ApiModelProperty(notes = "role", example = "GUEST", required = true)
    public String role;

    public UserDTO() {
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", nickname='" + nickname + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", birthdate=" + birthdate +
                ", role=" + role +
                '}';
    }
}
