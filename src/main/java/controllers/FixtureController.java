package controllers;

import db.DBHelper;
import models.Fixture;
import models.FootballTeam;
import models.League;
import models.MatchReport;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;

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

            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);
    }
}
