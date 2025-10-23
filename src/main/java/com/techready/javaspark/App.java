package com.techready.javaspark;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        port(Integer.parseInt(System.getenv().getOrDefault("PORT", "4567")));

        get("/health", (req, res) -> "OK");

        get("/hello", (req, res) -> {
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) name = "World";

            Map<String, Object> model = new HashMap<>();
            model.put("name", name);
            model.put("skills", new String[]{"APIs", "Java", "Teamwork"});

            return new ModelAndView(model, "hello.mustache");
        }, new MustacheTemplateEngine());
    }
}
