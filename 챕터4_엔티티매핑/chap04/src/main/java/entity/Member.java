package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="MEMBER")
public class Member {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME" , nullable = false, length = 10)
    private String username;

    private Integer age;
    public Member() {}
    public Member(String id, String username, Integer age) {
        this.id = id;
        this.username = username;

    }

//    2021-06-19 추가 source
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public RoleType getRoleType() {
        return roleType;
    }

    public enum RoleType {
       ADMIN,USER
    }


    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
