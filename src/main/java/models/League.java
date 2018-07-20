package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "leagues")
public class League {
    private int id;
    private String name;
    private LeagueType leagueType;
    private List<Team> teams;
    private List<Fixture> fixtures;
    private String region;

    public League() {
    }

    public League(String name, LeagueType leagueType, String region) {
        this.name = name;
        this.leagueType = leagueType;
        this.teams = new ArrayList<Team>();
        this.fixtures = new ArrayList<Fixture>();
        this.region = region;
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

    @Column(name = "league_type")
    public LeagueType getLeagueType() {
        return leagueType;
    }

    public void setLeagueType(LeagueType leagueType) {
        this.leagueType = leagueType;
    }

    public String returnLeagueTypeFromEnum() {
        return this.leagueType.getLeagueType();
    }

    @OneToMany(mappedBy = "league", fetch = FetchType.LAZY)
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @OneToMany(mappedBy = "league", fetch = FetchType.LAZY)
    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int teamCount() {
        return this.teams.size();
    }

    public void addToTeams(Team team) {
        this.teams.add(team);
    }

    public void removeTeams(Team team) {
        this.teams.remove(team);
    }

    public void clearTeams() {
        this.teams.clear();
    }

    public boolean leagueContainsTeam(Team team) {
        if (this.teams.contains(team)) {
            return true;
        } else return false;
    }

    public int fixtureCount() {
        return this.fixtures.size();
    }

    public void addToFixtures(Fixture fixture) {
        this.fixtures.add(fixture);
    }

    public void removeFromFixtures(Fixture fixture) {
        this.fixtures.remove(fixture);
    }

    public void clearFixtures() {
        this.fixtures.clear();
    }

    public boolean leagueContiansFixture(Fixture fixture) {
        if (this.fixtures.contains(fixture)) {
            return true;
        } else return false;
    }

    public List<List<Fixture>> fixtureGenerator(Boolean reverseFixtures, List<Team> teams, League league) {
        //THIS ESTABLISHES IF WE NEED A GHOST
        //Set integer variable called number of teams = teams.size()
        int numberofTeams = teams.size();

        //ghost = false
        Boolean ghost = false;

        // if number of teams is odd incrment no of teams by 1 and set ghost to true
        if (teams.size() % 2 != 0) {
            ghost = true;
            numberofTeams += 1;
        }
        //ESTABLISH HOW MANY ROUNDS OF FIXTURES THERE WILL BE
        //Set int number of weeks = teams.size - 1
        int numberOfWeeks = (teams.size() - 1);

        //ESTABLISH HOW MANY MATCHES A WEE THERE WILL BE
        // Set matches per week = number of teams / 2
        int matchesPerWeek = (teams.size() / 2);

        //SET UP AN EMPTY FIXTURE LIST
        //List<<List> Fixtures> = new List<List<Fixture>>
        List<List<Fixture>> fixturesList = new ArrayList<List<Fixture>>();


        //GENERATE A LIST OF FIXTURES THAT STILL NEED TO BE REFACTORED TO MEET OUR NEEDS(WHOLE BLOCK)

        //GENERATES THE NUMBER OF ROUNDS OF FIXTURES
        //Initiate for loop stating at week = 0, week < number of weeks, week++
        for (int week = 0; week < numberOfWeeks; week++) {
            List<Fixture> roundOfFixtures = new ArrayList<Fixture>();

            for (int match = 0; match < matchesPerWeek; match++) {
                int home = (week + match) % (numberofTeams - 1);
                int away = ((numberofTeams - 1) - match + week) % (numberofTeams - 1);
                if (match == 0) {
                    away = numberofTeams - 1;
                }
                Fixture fixture = new Fixture((week + 1), league);
                fixture.addTeamsToFixture(this.teams.get(home), this.teams.get(away));
                roundOfFixtures.add(fixture);
            }
            fixturesList.add(roundOfFixtures);
        }

        //MAKES AN EMPTY LIST OF FIXTURES THAT WE CAN ADD FIXTURES TO
        // Set list of fixtures called roundofFixtures = List<Fixture>

        //GENERATES THE CORRECT AMOUNT OF FIXTURES PER ROUND
        // Iiterrate nested for loop set match to 0 then while match is less than matches per week increment match by 1


        //LINES BELOW
        //SETS INTERGER OF HOME TEAM TO USE AS AN INDEX FOR THE TEAMS ARRAY
        //Set integer home equal to the remainder (week + match) devided by (number of teams -1 ) this is where we set

        //SETS INTERGER OF AWAY TEAM TO USE AS AN INDEX FOR THE TEAMS ARRAY
        // Set integer away equal to the remainder of (number of teams - 1 - match + week) devided by number of teams -1

        //
        // If match is equal 0 set away is equal number of teams - 1

        //  Create new fixture with home and away teams retrieved from the array of teams. Using the integers home and away.

        //Add the fixture above to the main list of round of fixtures

        //WE KNOW HAVE A LIST OF FIXTURES AND WE WANT TO MAKE SURE THAT THE SAME TEAM ISN'T PLAYING HOME TWO OR THREE WEEKS IN A ROW.
        //Make a new list of list of fixtures(as above)
        List<List<Fixture>> filteredFixtures = new ArrayList<List<Fixture>>();
        //Creating two integer variables, even and odd. Even will be 0 and odd will be the no of teams devided by 2.
        int even = 0;
        int odd = numberofTeams / 2;
        //Start a for loop to get weeks for the list of lists with the aim of evening out a team playing away all the time. i Starts at 0, while i is less than our list of list of fixtures , i is incremented by          one.
        for (int i = 0; i < fixturesList.size(); i++) {
            //If i is divisible by 2 with no remainder (ie even), then get the list of weekly fixtures positioned at the even integer index from the fixtureList List of Lists
            if (i % 2 == 0) {
                filteredFixtures.add(fixturesList.get(even++));
            }

            //ELse i is not divisible by 2 with no remainder (ie odd), then get the list of weekly fixtures positioned at the odd integer index from the fixtureList List of Lists
            else filteredFixtures.add(fixturesList.get(odd++));
            //OutSide of the loop. Set fixtures list to filteredFixtures then return fixturesList.
        }

        //SO THE LAST ITEM IN THE TEAM ARRAY IS NOT ALWAYS SET AS THE AWAY TEAM
        //Initiate for loop stating at week = 0, week < number of weeks, week++
        for (int week = 0; week < numberOfWeeks; week++) {

            for (int match = 0; match < matchesPerWeek; match++) {
                //if week is odd set a new fixture which reassings the zeroth position in the weekly fixtures array.
                if (week % 2 != 0) {
                    Fixture flippedFixture = fixturesList.get(week).get(match);
                    Collections.reverse(flippedFixture.getTeams());
                }
            }
            // Reassing the fixturesList
        }
        return fixturesList;


    }

    public void getFixtureFromListOfListOfFixturesAndAddToFixtureList(League league) {
        List<List<Fixture>> returnedWeeklyFixtures = fixtureGenerator(false, league.getTeams(), league);

        int matchesPerWeek = (teams.size() / 2);
        int numberOfWeeks = (teams.size() - 1);

//        FOR EACH WEEK, GO INTO THE LIST OF LISTS AND ACCESS THE LIST FOR THAT WEEK. THEN, TAKE EACH FIXTURE FROM THAT LIST ONE BY ONE AND ADD IT TO THE FIXTURE ATTRIBUTE OF THE LEAGUE CLASS.

        for (int week = 0; week < numberOfWeeks; week++) {

            for (int match = 0; match < matchesPerWeek; match++) {
                List<Fixture> weeklyFixtures = returnedWeeklyFixtures.get(week);
                Fixture retrievedFixture = weeklyFixtures.get(match);
                this.fixtures.add(retrievedFixture);
            }

        }
    }
}



