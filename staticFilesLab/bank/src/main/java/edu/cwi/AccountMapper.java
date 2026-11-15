
package edu.cwi;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface AccountMapper {

    @Insert("""
        INSERT INTO accounts (user_id, account_type, balance)
        VALUES (#{userId}, #{accountType}, #{balance})
        """)

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "account_id")
    void insert(Account account);

    @Select("""
    SELECT * FROM accounts
    WHERE user_id = #{userId}
    """)

    @Results({
        @Result(property = "id", column = "account_id"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "accountType", column = "account_type")
    })
    
    Account selectByUserId(Long userId);
}
