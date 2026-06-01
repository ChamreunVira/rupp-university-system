package  com.kh.rupp_dev.boukryuniversity.entity;

import java.time.LocalDateTime;

import com.kh.rupp_dev.boukryuniversity.constant.AttendanceStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tbl_attendanc_records")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRecord {
      
      @Id
      @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;

      @ManyToOne(fetch=FetchType.LAZY)
      @JoinColumn(name="student_id")
      private Student student;

      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "session_id")
      private AttendanceSession session;

      private LocalDateTime attendanceTime;

      @Enumerated(EnumType.STRING)
      private AttendanceStatus status;

}