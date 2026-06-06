package com.kh.rupp_dev.boukryuniversity.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "class_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID courseId;

    @Column(nullable = false, length = 100)
    private String courseName;

    @Column(nullable = false)
    private List<DayOfWeek> allowedDay;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private Integer deviceMinute;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longtitude;

    @Column(nullable = false)
    private Double radiusMeters;
}
