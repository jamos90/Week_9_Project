package controllers;

import db.Seeds;
import models.FootballTeam;
import models.League;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.function.ObjLongConsumer;

import static spark.Spark.get;

public class MainController {
    public static void main(String[] args) {


        Seeds.seedData();
        ManagerController managerController = new ManagerController();

        FootballTeamController footballTeamController = new FootballTeamController();

        MatchReportController matchReportController = new MatchReportController();

        LeagueController leagueController = new LeagueController();



        get("/all", (req,res)-> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

    }, new VelocityTemplateEngine());

    }
}
