package edu.cwi;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.apibuilder.ApiBuilder.*;

public class App {
    public static void main(String[] args) {
        var transactionsController = new TransactionsController();

        var app = Javalin.create(config -> {
            config.fileRenderer(new JavalinJte());

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
        }).start(7979);
    }
}