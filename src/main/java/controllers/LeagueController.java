package controllers;

import db.DBHelper;
import db.DBLeague;
import models.Fixture;
import models.League;
import models.Team;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
