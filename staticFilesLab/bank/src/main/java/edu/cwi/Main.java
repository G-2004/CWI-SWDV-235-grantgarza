package edu.cwi;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import static io.javalin.apibuilder.ApiBuilder.*;
import io.javalin.rendering.template.JavalinJte;
import java.util.List;
import java.util.Map;

import io.javalin.http.UnauthorizedResponse;

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
            path("account", List.of(AppRole.CUSTOMER), () -> {
                get(ctx -> {
                    String userId = ctx.cookieStore().get("user-id");

                    UserAccount account = AuthController.getUser(userId)
                        .orElseThrow(UnauthorizedResponse::new);

                    ctx.render(
                        "jte/accountPage.jte",
                        Map.of(
                            "title", "Account",
                            "account", account
                        )
                    );
                });
            });
        });

        config.routes.beforeMatched(ctx -> {
            if (ctx.routeRoles().isEmpty()) {
                return;
            }

            String role = ctx.cookieStore().get("user-role");

            if (role == null) {
                throw new UnauthorizedResponse();
            }

            AppRole appRole = AppRole.valueOf(role);

            if (!ctx.routeRoles().contains(appRole)) {
                throw new UnauthorizedResponse();
            }
        });

        config.routes.post("/account", AuthController::authenticate);

        config.routes.get("/logout", AuthController::logout);

        config.routes.get("/index", ctx -> {
            ctx.redirect("/");
        });
        }).start(7070);
    }
}