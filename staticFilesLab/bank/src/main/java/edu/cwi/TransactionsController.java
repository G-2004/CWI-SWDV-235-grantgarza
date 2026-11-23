
package edu.cwi;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;

public class TransactionsController implements CrudHandler {

    public void createForAccount(Context ctx, String accountId) {

        String userId = ctx.cookieStore().get("user-id");
        String transactionType = ctx.formParam("transactionType");
        String amountParam = ctx.formParam("amount");
        String description = ctx.formParam("description");

        try {
            Long parsedAccountId = Long.valueOf(accountId);
            BigDecimal amount = new BigDecimal(amountParam);

            if (userId == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
                ctx.status(400).result("Invalid transaction.");
                return;
            }

            if (!"DEPOSIT".equals(transactionType)
                    && !"WITHDRAWAL".equals(transactionType)) {
                ctx.status(400).result("Invalid transaction type.");
                return;
            }

            try (SqlSession session =
                    MyBatisConfig.getSqlSessionFactory().openSession()) {

                UserMapper userMapper = session.getMapper(UserMapper.class);
                AccountMapper accountMapper = session.getMapper(AccountMapper.class);
                TransactionMapper transactionMapper =
                        session.getMapper(TransactionMapper.class);

                User user = userMapper.selectById(Long.valueOf(userId));
                Account account = accountMapper.selectById(parsedAccountId);

                if (user == null || account == null
                        || !account.getUserId().equals(user.getId())) {
                    ctx.status(403).result("Account not found or access denied.");
                    return;
                }

                Transaction transaction = new Transaction();
                transaction.setAccountId(account.getId());
                transaction.setTransactionType(transactionType);
                transaction.setAmount(amount);
                transaction.setDescription(description);

                transactionMapper.insert(transaction);

                if ("DEPOSIT".equals(transactionType)) {
                    account.setBalance(account.getBalance().add(amount));
                } else {
                    account.setBalance(account.getBalance().subtract(amount));
                }

                accountMapper.updateBalance(account);

                session.commit();
            }

            ctx.redirect("/account/" + accountId);

        } catch (NumberFormatException e) {
            ctx.status(400).result("Invalid account ID or amount.");
        }
    }

    @Override
    public void getAll(Context ctx) {
        ctx.status(405).result("Method not supported.");
    }

    @Override
    public void getOne(Context ctx, String resourceId) {
        ctx.status(405).result("Method not supported.");
    }

    @Override
    public void create(Context ctx) {
        ctx.status(405).result("Use the account transaction route.");
    }

    @Override
    public void update(Context ctx, String resourceId) {
        ctx.status(405).result("Method not supported.");
    }

    @Override
    public void delete(Context ctx, String resourceId) {
        ctx.status(405).result("Method not supported.");
    }
}
