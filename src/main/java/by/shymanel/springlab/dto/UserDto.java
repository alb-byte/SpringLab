package by.shymanel.springlab.dto;


import by.shymanel.springlab.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    @NotBlank(message = "Введите username")
    @Size(min=5, max=255,message = "Username должен содержать от 5 до 255 символов")
    private String username;
    @NotBlank(message = "Введите имя")
    @Size(min=3, max=50,message = "Имя должно содержать от 5 до 50 символов")
    private String firstName;
    @NotBlank(message = "Введите фамилию")
    @Size(min=3, max=50,message = "Фамилия должна содержать от 5 до 50 символов")
    private String lastName;
    @NotBlank(message = "Введите email")
    @Email(message = "Неверный формат электроной почты")
    private String email;
    @NotBlank(message = "Введите пароль")
    @Size(min=5, max=255,message = "Пароль должен содержать от 5 до 255 символов")
    private String password;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
