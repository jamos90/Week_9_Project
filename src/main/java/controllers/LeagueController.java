package controllers;

import db.DBHelper;
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

        post("/leagues/:id/delete", (req,res)->{
            int leagueToDeleteId = Integer.parseInt(req.params("id"));
            League leagueToDelete = DBHelper.find(leagueToDeleteId, League.class);
            DBHelper.delete(leagueToDelete);
            res.redirect("/leagues");
            return null;
        }, new VelocityTemplateEngine());


        }


}
