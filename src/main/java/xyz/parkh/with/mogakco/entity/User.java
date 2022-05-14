package xyz.parkh.with.mogakco.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity(name="user")
public class User {

    @Id
    String userNum;
    String userId;
    String guildId;
    String userName;
    String userType;
    String wantTime;
    String wantCnt;
}
