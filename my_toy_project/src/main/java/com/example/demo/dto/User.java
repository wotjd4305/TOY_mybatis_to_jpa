package com.example.demo.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private int userId;

    private int empNum;
    private String name;
    private String password;
    private String department;
    private String image;


}
