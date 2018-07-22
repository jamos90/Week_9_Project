package controllers;

import db.DBHelper;
import models.Fixture;
import models.League;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



        }, new VelocityTemplateEngine());


        }
}
