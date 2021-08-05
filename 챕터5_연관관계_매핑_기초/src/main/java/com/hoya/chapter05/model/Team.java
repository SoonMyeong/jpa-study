package com.hoya.chapter05.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name="team")
public class Team {
    @Id
    @Column(name="TEAM_ID")
    private Long id;
    private String name;


    @OneToMany(mappedBy ="team")
    private List<Member> members = new ArrayList<>();
    public Team() {

    }
    public Team(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", members size=" + members.size() +
                '}';
    }
}
