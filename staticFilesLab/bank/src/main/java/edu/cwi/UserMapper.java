
package edu.cwi;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Select("SELECT * FROM users")
    @Results({
        @Result(property = "id", column = "user_id"),
        @Result(property = "firstName", column = "first_name"),
        @Result(property = "lastName", column = "last_name")
    })
    List<User> selectAll();

    @Select("SELECT * FROM users WHERE user_id = #{id}")
    @Results({
        @Result(property = "id", column = "user_id"),
        @Result(property = "firstName", column = "first_name"),
        @Result(property = "lastName", column = "last_name")
    })
    User selectById(Long id);

    @Select("SELECT * FROM users WHERE username = #{username}")
    @Results({
        @Result(property = "id", column = "user_id"),
        @Result(property = "firstName", column = "first_name"),
        @Result(property = "lastName", column = "last_name")
    })
    User selectByUsername(String username);

    @Insert("""
        INSERT INTO users
        (username, password, email, first_name, last_name, role)
        VALUES
        (#{username}, #{password}, #{email}, #{firstName}, #{lastName}, #{role})
        """)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "user_id")
    void insert(User user);

    @Update("""
        UPDATE users
        SET username = #{username},
            email = #{email},
            first_name = #{firstName},
            last_name = #{lastName},
            role = #{role}
        WHERE user_id = #{id}
        """)
    void update(User user);

    @Delete("DELETE FROM users WHERE user_id = #{id}")
    void delete(Long id);
}
