package com.hoya.chapter05.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name="member")
public class Member {

    @Id
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USER_NAME")
    private String userName;

    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;


    public Member(long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", team id=" + team.getId() +
                '}';
    }
}
