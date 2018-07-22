package controllers;

import db.DBHelper;
import db.DBLeague;
import models.Fixture;
import models.League;
import models.LeagueType;
import models.Team;
import org.apache.velocity.runtime.log.LogSystem;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

import static java.util.EnumSet.allOf;
import static spark.Spark.get;
import static spark.Spark.post;

public class LeagueController {
    public LeagueController(){
        this.setUpEndPoints();
        }

        private void setUpEndPoints(){

        get("/leagues", (req,res) ->{
            Map<String, Object> model = new HashMap<>();
            List<League> leagues = DBHelper.getAll(League.class);
            model.put("leagues", leagues);
            List<Fixture> fixtures = DBHelper.getAll(Fixture.class);
            model.put("fixtures", fixtures);
            List<Team> teams = DBHelper.getAll(Team.class);
            model.put("teams", teams);
            model.put("template", "templates/leagues/index.vtl");
            return new ModelAndView(model,"templates/layout.vtl");
        }, new VelocityTemplateEngine());

            get("/leagues/new", (req,res)->{
                Map<String, Object> model = new HashMap<>();
                model.put("template","templates/leagues/new.vtl");

                EnumSet leagueTypes =  EnumSet.allOf(LeagueType.class);
                model.put("leagueTypes", leagueTypes);

                return new ModelAndView(model, "templates/layout.vtl");

            }, new VelocityTemplateEngine());

            post("/leagues", (req,res) ->{
                String name = req.queryParams("name");
                LeagueType leagueType =  LeagueType.valueOf(req.queryParams("league_type"));
                String region = req.queryParams("region");
                League leagueToSave = new League(name, LeagueType.valueOf(req.queryParams("league_type")),region);
                DBHelper.save(leagueToSave);

                res.redirect("/leagues");
                return null;

            }, new VelocityTemplateEngine());

        get("/leagues/:id", (req,res) ->{
            Map<String, Object> model = new HashMap<>();
            int leagueId = Integer.parseInt(req.params(":id"));

            League league = DBHelper.find(leagueId, League.class);
            model.put("league", league);

            List<Fixture> fixtures = DBLeague.getFixturesForLeague(league);
            model.put("fixtures", fixtures);

            List<Team> teams = DBLeague.getTeamsForALeaugue(league);
            model.put("teams", teams);

            model.put("template", "templates/leagues/view.vtl");

            return new ModelAndView(model,"templates/layout.vtl");


        }, new VelocityTemplateEngine());


        get("/leagues/:id/edit", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            int leagueId = Integer.parseInt(req.params(":id"));
            League leagueToEdit = DBHelper.find(leagueId,League.class);
            model.put("league", leagueToEdit);

            List<Team> teams = DBLeague.getTeamsForALeaugue(leagueToEdit);
            model.put("leaguesTeams", teams);

           List<Fixture> leaguesFixtures = DBLeague.getFixturesForLeague(leagueToEdit);
           model.put("leaguesFixtures", leaguesFixtures);

           model.put("template", "/templates/leagues/edit.vtl");
           return new ModelAndView(model,"templates/layout.vtl");

        }, new VelocityTemplateEngine());

        post("/leagues/:id/delete", (req,res)->{
            int leagueToDeleteId = Integer.parseInt(req.params("id"));
            League leagueToDelete = DBHelper.find(leagueToDeleteId, League.class);
            List<Team> teams = DBLeague.getTeamsForALeaugue(leagueToDelete);
            List<Fixture> fixtures = DBLeague.getFixturesForLeague(leagueToDelete);
            DBHelper.delete(leagueToDelete);
            DBHelper.update(teams);
            DBHelper.update(fixtures);

            res.redirect("/leagues");
            return null;
        }, new VelocityTemplateEngine());




        }


}
