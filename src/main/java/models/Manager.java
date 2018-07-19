package models;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
