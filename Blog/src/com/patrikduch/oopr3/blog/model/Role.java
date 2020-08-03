package com.patrikduch.oopr3.blog.model;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Role")
public class Role {

    // Konstruktory

    public Role() {


    }




    public String getRoleName() {
        return roleName;
    }


    public Role(String roleName) {
        this.roleName = roleName;
    }

    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    private int id;
    @Column(name = "rolename")
    private String roleName;

    //@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
    //        CascadeType.MERGE, CascadeType.REFRESH,  CascadeType.DETACH})

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "MemberRole",
            joinColumns = { @JoinColumn(name = "rid") },
            inverseJoinColumns = { @JoinColumn(name = "mid") }
    )
    private List<User> users = new ArrayList<>();

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
