package com.tangyiheng.wj.dao;

import com.tangyiheng.wj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 由于使用了 JPA，无需手动构建 SQL 语句，而只需要按照规范提供方法的名字即可实现对数据库的增删改查。
public interface UserDAO extends JpaRepository<User, Integer> {
    // 通过用户名查询
    User findByUsername(String username);
    // 通过用户名及密码查询
    User getByUsernameAndPassword(String username, String password);
}
