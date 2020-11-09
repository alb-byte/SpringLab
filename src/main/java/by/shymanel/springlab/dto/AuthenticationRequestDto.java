package by.shymanel.springlab.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AuthenticationRequestDto {
    @NotBlank(message = "Введите username")
    @Size(min=4, max=255,message = "Username должен содержать от 5 до 255 символов")
    private String username;
    @NotBlank(message = "Введите пароль")
    @Size(min=4, max=255,message = "Пароль должен содержать от 5 до 255 символов")
    private String password;
}