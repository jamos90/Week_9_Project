package controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import db.DBHelper;
import db.DBLeague;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.EnumSet;
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

    private void setUpEndPoints() {
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
        post("/footballteams", (req, res) -> {
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

            FootballTeam newFootballTeam = new FootballTeam(name, manager, league, teamlogo, location);

            league.addToTeams(newFootballTeam);

            DBLeague.getTeamsForALeaugue(league);

            league.addToTeams(newFootballTeam);
            DBHelper.update(league);

            DBHelper.save(newFootballTeam);
            DBHelper.update(league)

            ;


            res.redirect("/footballteams");

            return null;
        }, new VelocityTemplateEngine());

        //CREATE TEAM FOR LEAGUE

        get("/:id/teams/new", (req,res)->{

            int leagueId = Integer.parseInt(req.params(":id"));
            League league = DBHelper.find(leagueId, League.class);

            Map<String, Object> model = new HashMap<>();
            model.put("template","templates/footballteams/createForLeague.vtl");
            model.put("league", league);
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        post("/:id/footballteams", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            //getting league id
            int leagueId = Integer.parseInt(req.params("id"));

            //find league by id
            League league = DBHelper.find(leagueId, League.class);


            String name = req.queryParams("name");

            String teamlogo = req.queryParams("team_logo");

            String location = req.queryParams("location");

            String managerName = req.queryParams("mgrname");
            String managerEmail = req.queryParams("email");
            String managerPhone = req.queryParams("phone");

            Manager newManager = new Manager(managerName, managerEmail, managerPhone);
            DBHelper.save(newManager);

            FootballTeam newFootballTeam = new FootballTeam(name, newManager, league, teamlogo, location);
            DBHelper.save(newFootballTeam);

            league.addToTeams(newFootballTeam);
            DBHelper.update(league);

            res.redirect("/footballteams");

            return null;
        }, new VelocityTemplateEngine());





        //view for a team
        get("/footballteams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int footballTeamId = Integer.parseInt(req.params(":id"));
            FootballTeam footballTeam = DBHelper.find(footballTeamId, FootballTeam.class);
//            int leagueId = Integer.parseInt(req.queryParams("league"));
            //find league by id
            List<League> league = DBHelper.getAll(League.class);
//            int managerId = Integer.parseInt(req.queryParams("manager"));
//            Manager manager = DBHelper.find(managerId, Manager.class);
            List<Manager> manager = DBHelper.getAll(Manager.class);
            model.put("league", league);
            model.put("manager", manager);
            model.put("footballTeam", footballTeam);
            model.put("template", "templates/footballteams/view.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get("/footballteams/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            int footballTeamId = Integer.parseInt(req.params(":id"));

            FootballTeam selectedFootballTeam = DBHelper.find(footballTeamId, FootballTeam.class);
            model.put("selectedTeam", selectedFootballTeam);

            List<League> leagues = DBHelper.getAll(League.class);
            List<Manager> managers = DBHelper.getAll(Manager.class);

            model.put("footballTeam", selectedFootballTeam);
            model.put("leagues", leagues);
            model.put("managers", managers);
            model.put("template", "templates/footballteams/edit.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/footballteams/:id/update", (req, res) -> {
            int leagueId = Integer.parseInt(req.queryParams("league"));
            League league = DBHelper.find(leagueId, League.class);


            String name = req.queryParams("name");

            String logo = req.queryParams("logo");

            int points = Integer.parseInt(req.queryParams("points"));

            String location = req.queryParams("location");

            int footballTeamId = Integer.parseInt(req.params(":id"));
            FootballTeam footballTeam = DBHelper.find(footballTeamId, FootballTeam.class);
            footballTeam.setName(name);
            footballTeam.setTeamLogo(logo);
            footballTeam.setLocation(location);
            footballTeam.setPoints(points);
            DBHelper.update(footballTeam);

            res.redirect("/footballteams");
            return null;
        }, new VelocityTemplateEngine());

        post("/footballteams/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            FootballTeam teamToDelete = DBHelper.find(id, FootballTeam.class);

//            League teamsLeague = teamToDelete.getLeague();
//            teamsLeague.removeTeams(teamToDelete);
//
//            DBHelper.update(teamsLeague);
            DBHelper.delete(teamToDelete);

            res.redirect("/footballteams");
            return null;
        }, new VelocityTemplateEngine());


    }
}


