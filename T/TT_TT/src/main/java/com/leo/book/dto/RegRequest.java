package com.leo.book.dto;

import com.leo.book.user.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegRequest {
    @Column(unique = true, nullable = false)
    @Email
    private String email;
    @Column(nullable = false)
    @Length(min = 8)
    private String password;
    private String firstName;
    private String lastName;
}
