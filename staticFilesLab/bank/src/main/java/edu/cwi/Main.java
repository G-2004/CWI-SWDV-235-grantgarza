package edu.cwi;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import static io.javalin.apibuilder.ApiBuilder.*;
import io.javalin.rendering.template.JavalinJte;

public class Main {
    public static void main(String[] args) {

        var transactionsController = new TransactionsController();

        var app = Javalin.create(config -> {
            config.fileRenderer(new JavalinJte());
            config.staticFiles.add("/public", Location.CLASSPATH);

        config.routes.apiBuilder(() -> {
            path("transactions", () -> {
                crud("{id}", transactionsController);

                post("update", ctx ->
                    transactionsController.update(ctx, ctx.formParam("id"))
                );

                post("delete", ctx ->
                    transactionsController.delete(ctx, ctx.formParam("id"))
                );
            });
        });

        config.routes.post("/account", ctx -> {
            ctx.redirect("/account.html");
        });

        config.routes.get("/logout", ctx -> {
            ctx.redirect("/");
        });

        config.routes.get("/index", ctx -> {
            ctx.redirect("/");
        });
        }).start(7070);
    }
}