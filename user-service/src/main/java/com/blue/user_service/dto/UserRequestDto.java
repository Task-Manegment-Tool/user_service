package com.blue.user_service.dto;

//import com.blue.user_service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private String role;
}
