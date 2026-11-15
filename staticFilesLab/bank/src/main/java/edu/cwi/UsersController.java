
package edu.cwi;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;

public class UsersController implements CrudHandler {

    public User authenticateUser(Context ctx, String username, String password) {
        try (SqlSession session =
                MyBatisConfig.getSqlSessionFactory().openSession()) {

            UserMapper mapper = session.getMapper(UserMapper.class);

            User user = mapper.selectByUsername(username);

            if (user != null) {
                if (BCrypt.checkpw(password, user.getPassword())) {
                    return user;
                }
            }

            return null;
        }
    }

    @Override
    public void getOne(Context ctx, String resourceId) {
        try (SqlSession session =
                MyBatisConfig.getSqlSessionFactory().openSession()) {

            UserMapper mapper = session.getMapper(UserMapper.class);

            User user = mapper.selectById(Long.valueOf(resourceId));

            if (user != null) {
                user.setPassword(null);
                ctx.json(user);
            } else {
                ctx.status(404);
            }
        }
    }

    @Override
    public void getAll(Context ctx) {
        try (SqlSession session =
                MyBatisConfig.getSqlSessionFactory().openSession()) {

            UserMapper mapper = session.getMapper(UserMapper.class);

            List<User> users = mapper.selectAll();

            for (User user : users) {
                user.setPassword(null);
            }

            ctx.json(users);
        }
    }

    @Override
    public void create(Context ctx) {
        User user = ctx.bodyAsClass(User.class);

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        try (SqlSession session =
                MyBatisConfig.getSqlSessionFactory().openSession()) {

            try {
                UserMapper userMapper = session.getMapper(UserMapper.class);
                AccountMapper accountMapper = session.getMapper(AccountMapper.class);
                TransactionMapper transactionMapper = session.getMapper(TransactionMapper.class);

                userMapper.insert(user);

                Account account = new Account();
                account.setUserId(user.getId());
                account.setAccountType("CHECKING");
                account.setBalance(new java.math.BigDecimal("100.00"));

                accountMapper.insert(account);

                Transaction transaction = new Transaction();
                transaction.setAccountId(account.getId());
                transaction.setTransactionType("DEPOSIT");
                transaction.setAmount(new java.math.BigDecimal("100.00"));
                transaction.setDescription("Initial account deposit");

                transactionMapper.insert(transaction);

                session.commit();
                user.setPassword(null);
                ctx.status(201).json(user);

            } catch (Exception e) {
                session.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(Context ctx, String resourceId) {
        try (SqlSession session =
                MyBatisConfig.getSqlSessionFactory().openSession()) {

            UserMapper mapper = session.getMapper(UserMapper.class);

            User user = ctx.bodyAsClass(User.class);
            user.setId(Long.valueOf(resourceId));

            mapper.update(user);

            session.commit();

            ctx.json(user);
        }
    }

    @Override
    public void delete(Context ctx, String resourceId) {
        try (SqlSession session =
                MyBatisConfig.getSqlSessionFactory().openSession()) {

            UserMapper mapper = session.getMapper(UserMapper.class);

            mapper.delete(Long.valueOf(resourceId));

            session.commit();

            ctx.status(204);
        }
    }
}
