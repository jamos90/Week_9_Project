package controllers;

import db.DBHelper;
import db.DBLeague;
import models.FootballTeam;
import models.League;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class FootballTeamController {

    public FootballTeamController() {
        this.setUpEndPoints();

    }

    //Get index list of teams//

    private  void  setUpEndPoints() {
        get("/footballteams", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/footballteams/index.vtl");
            List<FootballTeam> footballTeamList = DBHelper.getAll(FootballTeam.class);
            model.put("teamList", footballTeamList);
            return new ModelAndView(model, "templates/index.vtl");
        }, new VelocityTemplateEngine());


        get("/footballteams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<League> leagues = DBHelper.getAll(League.class);
            model.put("leagues", leagues);
            model.put("templates", "templates/footballteams/create.vtl");
            return new ModelAndView(model, "templates/index.vtl");
        }, new VelocityTemplateEngine());

        post("/footballteams", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            //getting league id
            int leagueId = Integer.parseInt(req.queryParams("league"));
            //find league by id
            League league = DBHelper.find(leagueId, League.class);
            //Set the other class requirements
            String name = req.queryParams("name");
            return null;


        }, new VelocityTemplateEngine());


    }
}


