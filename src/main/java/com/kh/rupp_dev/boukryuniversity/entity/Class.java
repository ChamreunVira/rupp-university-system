package com.kh.rupp_dev.boukryuniversity.entity;

import com.kh.rupp_dev.boukryuniversity.audit.AuditListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tbl_class")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class Class extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "class_id")
    private UUID id;

    @Column(name = "class_name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Column(name = "generation", nullable = false)
    private Integer generation;

}
