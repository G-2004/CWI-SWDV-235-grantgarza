
package edu.cwi;

import io.javalin.http.Context;
import org.apache.ibatis.session.SqlSession;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AuthController {

    public static Optional<UserAccount> getUser(String id) {

        try (SqlSession session =
                MyBatisConfig.getSqlSessionFactory().openSession()) {

            UserMapper userMapper = session.getMapper(UserMapper.class);
            AccountMapper accountMapper = session.getMapper(AccountMapper.class);
            TransactionMapper transactionMapper =
                    session.getMapper(TransactionMapper.class);

            User user = userMapper.selectById(Long.valueOf(id));

            if (user == null) {
                return Optional.empty();
            }

            Account account = accountMapper.selectByUserId(user.getId());

            if (account == null) {
                return Optional.empty();
            }

            List<Transaction> records =
                    transactionMapper.selectByAccountId(account.getId());

            Map<String, String> transactions = new LinkedHashMap<>();

            for (Transaction transaction : records) {
                String description = transaction.getDescription();

                if (description == null) {
                    description = transaction.getTransactionType()
                            + " $" + transaction.getAmount();
                }

                transactions.put(
                        String.valueOf(transaction.getId()),
                        description
                );
            }

            UserAccount userAccount = new UserAccount(
                    String.valueOf(user.getId()),
                    user.getFirstName() + " " + user.getLastName(),
                    AppRole.valueOf(user.getRole()),
                    account.getBalance().doubleValue(),
                    transactions
            );

            return Optional.of(userAccount);
        }
    }

    public static void authenticate(Context ctx) {

        UsersController usersController = new UsersController();

        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        User user = usersController.authenticateUser(ctx, username, password);

        if (user != null) {
            ctx.cookieStore().set("user-id", String.valueOf(user.getId()));
            ctx.cookieStore().set("user-role", user.getRole());
            ctx.redirect("/account");
        } else {
            ctx.cookieStore().clear();
            ctx.redirect("/unauthorized.html");
        }
    }

    public static void logout(Context ctx) {
        ctx.cookieStore().clear();
        ctx.redirect("/");
    }
}
