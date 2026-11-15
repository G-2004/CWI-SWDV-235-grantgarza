
package edu.cwi;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class MyBatisConfig {

    private static final HikariDataSource dataSource;

    private static final SqlSessionFactory sqlSessionFactory;

    static {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/online_bank");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("password");
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSource = new HikariDataSource(hikariConfig);

        Environment environment = new Environment(
            "development",
            new JdbcTransactionFactory(),
            dataSource
        );

        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);
        configuration.addMapper(AccountMapper.class);
        configuration.addMapper(TransactionMapper.class);

        sqlSessionFactory = new SqlSessionFactoryBuilder()
            .build(configuration);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
