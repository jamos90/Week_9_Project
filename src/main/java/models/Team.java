package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teams")

@Inheritance(strategy = InheritanceType.JOINED)


@DiscriminatorColumn(name= "sport_type")

public abstract class Team {
    private int id;
    private String name;
    private Manager manager;
    private League league;
    private int points;
    private String teamLogo;
    private String homePitch;
    private String location;
    private List<Fixture> fixtures;

    public Team(){}

    public Team(String name, Manager manager, League league, String teamLogo, String location){
        this.name = name;
        this.manager = manager;
        this.league = league;
        this.points = 0;
        this.teamLogo = teamLogo;
        this.location = location;
        this.fixtures = new ArrayList<Fixture>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable =false)
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    @Column(name = "points")
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Column(name = "team_logo")
    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    @Column(name = "home_pitch")
    public String getHomePitch() {
        return homePitch;
    }

    public void setHomePitch(String homePitch) {
        this.homePitch = homePitch;
    }

    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(name = "teams_fixtures",
            joinColumns = {@JoinColumn(name = "teams_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "fixtures_id", nullable = false, updatable = false)})
    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }
}
