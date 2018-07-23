package controllers;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import db.DBHelper;
import models.*;
import models.MatchReport;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class MatchReportController {

    public MatchReportController() {
        this.setUpEndpoints();
    }

    private void setUpEndpoints() {
        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

        //READ
        get("/matchreports", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/matchreports/index.vtl");

            List<MatchReport> matchreports = DBHelper.getAll(MatchReport.class);
            model.put("matchreports", matchreports);

            List<Fixture> fixtures = DBHelper.getAll(Fixture.class);
            model.put("fixtures", fixtures);

            List<FootballTeam> footballTeams = DBHelper.getAll(FootballTeam.class);
            model.put("footballteams", footballTeams);

            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);



        //CREATE
        get("/:id/matchreports/new", (req, res) -> {

            int fixtureId = Integer.parseInt(req.params(":id"));
            Fixture fixture = DBHelper.find(fixtureId, Fixture.class);

            HashMap<String, Object> model = new HashMap<>();

            model.put("fixture", fixture);

            List<MatchReport> matchReports = DBHelper.getAll(MatchReport.class);


            model.put("matchreports", matchReports);
            model.put("template", "templates/matchreports/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);



        post("/:id/matchreports", (req, res) -> {

            int fixtureId = Integer.parseInt(req.params(":id"));
            Fixture fixture = DBHelper.find(fixtureId, Fixture.class);

            String headline = req.queryParams("headline");

            String blurb = req.queryParams("blurb");

            String picture = req.queryParams("picture");

            MatchReport newMatchReport = new MatchReport( fixture, headline, blurb, picture);
            DBHelper.save(newMatchReport);

            res.redirect("/matchreports");
            return null;
        }, velocityTemplateEngine);




        //UPDATE
        get("/matchreports/:id/edit", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<MatchReport> matchreports = DBHelper.getAll(MatchReport.class);

            int matchreportId = Integer.parseInt(req.params(":id"));
            MatchReport matchreport = DBHelper.find(matchreportId, MatchReport.class);

            model.put("matchreport", matchreport);
            model.put("template", "templates/matchreports/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);

        post("/matchreports/:id", (req, res) -> {
            String headline = (req.queryParams("headline"));
            String blurb = (req.queryParams("blurb"));

            int matchreportId = Integer.parseInt(req.params(":id"));
            MatchReport matchreport = DBHelper.find(matchreportId, MatchReport.class);
            matchreport.setHeadline(headline);
            matchreport.setBlurb(blurb);
            DBHelper.update(matchreport);

            res.redirect("/matchreports");
            return null;
        }, velocityTemplateEngine);
    }
}
