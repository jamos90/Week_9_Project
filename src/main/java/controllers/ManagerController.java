package controllers;

import db.DBHelper;
import models.Manager;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class ManagerController {


    public ManagerController() {
        this.setUpEndpoints();
    }

    private void setUpEndpoints() {
        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

        //CREATE
        get("/managers/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Manager> managers = DBHelper.getAll(Manager.class);
            model.put("managers", managers);
            model.put("template", "templates/managers/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);



        post("/managers", (req, res) -> {
            String name = req.queryParams("name");

            String email = req.queryParams("email");

            String phone = req.queryParams("phone");

            int managerId = Integer.parseInt(req.queryParams("manager"));
            Manager manager = DBHelper.find(managerId, Manager.class);

            Manager newManager = new Manager(name, phone, email);
            DBHelper.save(newManager);

            res.redirect("/managers");
            return null;
        }, velocityTemplateEngine);


        //READ



        //UPDATE


        //DELETE


    }




}
