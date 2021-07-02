package com.hoya.chapter05.model;


import lombok.Data;

import javax.persistence.*;

@Data
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

}
