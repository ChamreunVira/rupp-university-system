package com.kh.rupp_dev.boukryuniversity.entity;

import com.kh.rupp_dev.boukryuniversity.audit.AuditListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tbl_department")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    @Column(name = "department_name", nullable = false, unique = true)
    private String name;

    @Column(name = "department_thumbnail")
    private String thumbnail;

    @Column(name = "department_code", nullable = false, unique = true)
    private String code;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "department")
    private List<Subject> subjects;
}
