package models;

import javax.persistence.*;

@Entity
@Table(name = "managers")

public class Manager {

    private int id;
    private String name;
    private int phoneNo;
    private String email;
    private Team team;

public Manager(){}

public Manager(String name, int phoneNo, String email){
    this.name = name;
    this.phoneNo = phoneNo;
    this.email = email;
}

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "phone_number")
    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToOne(mappedBy = "manager", fetch = FetchType.LAZY)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
