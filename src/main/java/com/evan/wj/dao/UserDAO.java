package com.evan.wj.dao;

import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username,String password);

}
/*这里关键的地方在于方法的名字。由于使用了 JPA，无需手动构建 SQL 语句，而只需要按照规范提供方法的名字即可实现对数据库的增删改查。

如 findByUsername，就是通过 username 字段查询到对应的行，并返回给 User 类。

这里我们构建了两个方法，一个是通过用户名查询，一个是通过用户名及密码查询。*/
