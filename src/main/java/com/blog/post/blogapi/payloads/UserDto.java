package com.blog.post.blogapi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private int id;
    @NotEmpty()
    @Size(min=4,message = "Name must be of 4 characters!!")
    private String name;
    @Email(message = "Email address is not valid !! ")
    private String email;
    @NotEmpty
    @Size(min=4, max=10,message = "Password must be between 4 to 10 characters long !!")
    private String password;
    @NotEmpty
    private String about;
}
