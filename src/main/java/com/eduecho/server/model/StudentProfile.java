package com.eduecho.server.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_profiles")
@Getter
@Setter
@NoArgsConstructor
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;
    private String schoolName;

    @Column(nullable = false, columnDefinition = "integer default 10")        // required
    private int coinBalance = 10;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false, unique = true)
    private Student student;
}
