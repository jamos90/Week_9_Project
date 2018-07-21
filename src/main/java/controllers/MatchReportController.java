package controllers;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import db.DBHelper;
import models.Fixture;
import models.MatchReport;
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

            List<MatchReport> matchreports = DBHelper.getAll(MatchReport.class);
            model.put("matchreports", matchreports);

            List<MatchReport> matchreports = DBHelper.getAll(MatchReport.class);
            model.put("matchreports", matchreports);

            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);
    }
}
