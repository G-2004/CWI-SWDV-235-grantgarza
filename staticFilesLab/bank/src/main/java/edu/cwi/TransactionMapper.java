
package edu.cwi;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface TransactionMapper {

    @Insert("""
        INSERT INTO transactions
        (account_id, transaction_type, amount, description)
        VALUES
        (#{accountId}, #{transactionType}, #{amount}, #{description})
        """)

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "transaction_id")
    void insert(Transaction transaction);

    @Select("""
    SELECT * FROM transactions
    WHERE account_id = #{accountId}
    ORDER BY created_at DESC
    """)
    
    @Results({
        @Result(property = "id", column = "transaction_id"),
        @Result(property = "accountId", column = "account_id"),
        @Result(property = "transactionType", column = "transaction_type"),
        @Result(property = "createdAt", column = "created_at")
    })
    List<Transaction> selectByAccountId(Long accountId);
}
