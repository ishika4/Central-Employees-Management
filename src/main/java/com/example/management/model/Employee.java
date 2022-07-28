package com.example.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private Long age = 0L;

    private String location;

    private String email;

    private Long salary;

    private String domain;

    private Integer isActive;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false )
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt", nullable = false, updatable = false)
    private Date updatedAt;

}
