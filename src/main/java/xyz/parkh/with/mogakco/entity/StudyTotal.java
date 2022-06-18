package xyz.parkh.with.mogakco.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name="studyTotal")
public class StudyTotal {
    @Id
    String no;
    Integer studyTime;
    LocalDate date;
    String todaySuccess;
    String weekSuccessCnt;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userNum")
    User user;
}
