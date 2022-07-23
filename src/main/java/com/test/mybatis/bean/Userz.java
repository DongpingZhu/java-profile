package com.test.mybatis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="t_user")
@Data
@AllArgsConstructor
public class Userz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Transient
    private String email;
}
