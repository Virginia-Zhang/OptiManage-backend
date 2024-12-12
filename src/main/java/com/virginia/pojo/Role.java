package com.virginia.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
    * Role Entity
    */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    private Integer id;

    private String role;

    private String roleName;

    private static final long serialVersionUID = 1L;
}