package models;

public class MatchReport {

    private int id;
    private Fixture fixture;
    private String headline;
    private String blurb;

    public MatchReport(){}

    public MatchReport(Fixture fixture, String headline, String blurb){
        this.fixture = fixture;
        this.headline = headline;
        this.blurb = blurb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }
}
