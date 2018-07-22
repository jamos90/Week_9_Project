package controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import db.DBHelper;
import db.DBLeague;
import models.FootballTeam;
import models.League;
import models.Manager;
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
            List<League> leages = DBHelper.getAll(League.class);
            model.put("leagues", leages);
            List<FootballTeam> footballTeamList = DBHelper.getAll(FootballTeam.class);
            model.put("teamList", footballTeamList);

            List<Manager> managers = DBHelper.getAll(Manager.class);
            model.put("manager", managers);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //new team

        get("/footballteam/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<League> leagues = DBHelper.getAll(League.class);
            model.put("leagues", leagues);

            List<Manager> managers = DBHelper.getAll(Manager.class);
            model.put("managers", managers);

            model.put("template", "templates/footballteams/create.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        //post new team
        post("/footballteams", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            //getting league id
            int leagueId = Integer.parseInt(req.queryParams("league"));

            //find league by id
            League league = DBHelper.find(leagueId, League.class);

            //Set the other class requirements
            int managerId = Integer.parseInt(req.queryParams("manager"));
            Manager manager = DBHelper.find(managerId, Manager.class);


            String name = req.queryParams("name");

            String teamlogo = req.queryParams("team_logo");

            String location = req.queryParams("location");

            FootballTeam newFootballTeam = new FootballTeam(name,manager,league,teamlogo,location);

            DBHelper.save(newFootballTeam);

            league.addToTeams(newFootballTeam);

            res.redirect("/footballteams");

            return null;
        }, new VelocityTemplateEngine());


        //view for a team
        get("/footballteams/:id", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            int footballTeamId = Integer.parseInt(req.params(":id"));
            FootballTeam footballTeam = DBHelper.find(footballTeamId, FootballTeam.class);
//            int leagueId = Integer.parseInt(req.queryParams("league"));
            //find league by id
             List<League> league = DBHelper.getAll(League.class);
//            int managerId = Integer.parseInt(req.queryParams("manager"));
//            Manager manager = DBHelper.find(managerId, Manager.class);
            List<Manager> manager = DBHelper.getAll(Manager.class);
            model.put("leauge", league);
            model.put("manage", manager);
            model.put("footballTeam", footballTeam);
            model.put("template", "templates/footballteams/view.vtl");
            return new ModelAndView(model,"templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get("/footballteams/:id/edit",(req,res)->{
            Map<String,Object> model = new HashMap<>();

            int footballTeamId = Integer.parseInt(req.params(":id"));

            FootballTeam selectedFootballTeam = DBHelper.find(footballTeamId,FootballTeam.class);
            model.put("selectedTeam", selectedFootballTeam);

            List<League> leagues = DBHelper.getAll(League.class);
            List<Manager> managers = DBHelper.getAll(Manager.class);

            model.put("footballTeam", selectedFootballTeam);
            model.put("leagues", leagues);
            model.put("managers", managers);
            model.put("template", "templates/footballteams/edit.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/footballteams/:id/update", (req,res) -> {
                    int leagueId = Integer.parseInt(req.queryParams("league"));
                    League league = DBHelper.find(leagueId, League.class);

                    int managerId = Integer.parseInt(req.queryParams("manager"));

                    Manager manager = DBHelper.find(managerId, Manager.class);
                    String name = req.queryParams("name");

                    String logo = req.queryParams("logo");

                    String location = req.queryParams("location");

                    FootballTeam updatedFootballTeam = new FootballTeam(name, manager, league, logo, location);
                    int teamId = Integer.parseInt(req.params(":id"));
                    updatedFootballTeam.setId(teamId);
                    DBHelper.update(updatedFootballTeam);
                    res.redirect("/footballteams");
            return null;
        }, new VelocityTemplateEngine());

        post("/footballteams/:id/delete", (req,res)->{
            int id = Integer.parseInt(req.params(":id"));
            FootballTeam teamToDelete = DBHelper.find(id, FootballTeam.class);
            League teamsLeague = teamToDelete.getLeague();
            teamsLeague.removeTeams(teamToDelete);
            DBHelper.update(teamsLeague);
            DBHelper.delete(teamToDelete);
            DBHelper.update(teamsLeague);
            res.redirect("/footballteams");
            return null;
                }, new VelocityTemplateEngine());







    }
}


