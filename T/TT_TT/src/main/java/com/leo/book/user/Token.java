package com.leo.book.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
    private String token;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Date createdAt;
    private Date expiresAt;
    private Date validatedAt;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

}
