package controllers;

import db.DBFixture;
import db.DBHelper;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class FixtureController {

    {
        this.setUpEndpoints();
    }

    private void setUpEndpoints() {
        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

        //READ
        get("/fixtures", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/fixtures/index.vtl");

            List<Fixture> fixtures = DBHelper.getAll(Fixture.class);
            model.put("fixtures", fixtures);

            List<League> leagues = DBHelper.getAll(League.class);
            model.put("leagues", leagues);

            List<MatchReport> matchReports = DBHelper.getAll(MatchReport.class);
            model.put("matchreports", matchReports);

            List<FootballTeam> footballTeams = DBHelper.getAll(FootballTeam.class);
            model.put("footballteams", footballTeams);

            DBFixture.sorFixturesByPWeek();


            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);


        //UPDATE
        post("/fixtures/:id", (req, res) -> {
            String homegoals = (req.queryParams("homegoals"));
            String awaygoals = (req.queryParams("awaygoals"));



            int fixtureId = Integer.parseInt(req.params(":id"));
            Fixture fixture = DBHelper.find(fixtureId, Fixture.class);
            fixture.setHomeGoals(homegoals);
            fixture.setAwayGoals(awaygoals);
            DBHelper.update(fixture);
            DBFixture.sorFixturesByPWeek();

            FootballTeam homeTeam = (FootballTeam)fixture.returnHomeTeam();
            FootballTeam awayTeam = (FootballTeam)fixture.returnAwayTeam();
            homeTeam.setGoalsScored(Integer.parseInt(homegoals));
            awayTeam.setGoalsScored(Integer.parseInt(awaygoals));
            homeTeam.setGoalsConceded(Integer.parseInt(awaygoals));
            awayTeam.setGoalsConceded(Integer.parseInt(homegoals));
            fixture.inputGoalsToGenerateResult(Integer.parseInt(homegoals), Integer.parseInt(awaygoals));
            fixture.updateGamesPlayed(homegoals,awaygoals);


            DBHelper.update(homeTeam);
            DBHelper.update(awayTeam);

            League league = homeTeam.getLeague();
            DBHelper.update(league);

            res.redirect("/fixtures");
            return null;
        }, velocityTemplateEngine);
    }

}

