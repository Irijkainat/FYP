package com.FYP.HealthApp.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    private String username;
    private String email;
    private String password;
    private String role;
}
