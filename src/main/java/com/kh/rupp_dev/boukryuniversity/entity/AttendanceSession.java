package com.kh.rupp_dev.boukryuniversity.entity;

import java.time.LocalDateTime;

import com.kh.rupp_dev.boukryuniversity.constant.SessionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_attandance_session")
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceSession {
      
      @Id
      @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Integer id;

      @ManyToOne(fetch=FetchType.LAZY)
      @JoinColumn(name="class_id")
      private Class clazz;

      @Column( nullable = false )
      private LocalDateTime startTime;

      @Column( nullable = false )
      private LocalDateTime endTime;

      @Column(nullable = false, unique = true)
      private String qrToken;

      @Enumerated(EnumType.STRING)
      @Column(nullable = false)
      private SessionStatus status;

}
