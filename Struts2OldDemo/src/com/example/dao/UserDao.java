package com.example.dao;

import com.example.model.User;
import com.example.util.SqlMapClientFactory;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.apache.log4j.Logger;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private static final Logger logger = Logger.getLogger(UserDao.class);
    private SqlMapClient sqlMapClient;
    
    public UserDao() {
        this.sqlMapClient = SqlMapClientFactory.getSqlMapClient();
        logger.debug("UserDao 初始化完成");
    }
    
    public User getUserById(Long id) {
        try {
            logger.debug("根据ID查询用户: " + id);
            User user = (User) sqlMapClient.queryForObject("User.getUserById", id);
            logger.debug("查询结果: " + (user != null ? user.getName() : "null"));
            return user;
        } catch (SQLException e) {
            logger.error("根据ID查询用户失败: " + e.getMessage(), e);
            return null;
        }
    }
    
    public List<User> getAllUsers() {
        try {
            logger.debug("查询所有用户");
            List<User> users = sqlMapClient.queryForList("User.getAllUsers");
            logger.debug("查询到 " + users.size() + " 个用户");
            return users;
        } catch (SQLException e) {
            logger.error("查询所有用户失败: " + e.getMessage(), e);
            return null;
        }
    }
    
    public Long insertUser(User user) {
        try {
            logger.debug("插入用户: " + user.getName());
            Long id = (Long) sqlMapClient.insert("User.insertUser", user);
            logger.debug("用户插入成功，ID: " + id);
            return id;
        } catch (SQLException e) {
            logger.error("插入用户失败: " + e.getMessage(), e);
            return null;
        }
    }
    
    public boolean updateUser(User user) {
        try {
            logger.debug("更新用户: " + user.getId());
            int rows = sqlMapClient.update("User.updateUser", user);
            logger.debug("更新了 " + rows + " 行数据");
            return rows > 0;
        } catch (SQLException e) {
            logger.error("更新用户失败: " + e.getMessage(), e);
            return false;
        }
    }
    
    public boolean deleteUser(Long id) {
        try {
            logger.debug("删除用户: " + id);
            int rows = sqlMapClient.delete("User.deleteUser", id);
            logger.debug("删除了 " + rows + " 行数据");
            return rows > 0;
        } catch (SQLException e) {
            logger.error("删除用户失败: " + e.getMessage(), e);
            return false;
        }
    }
    
    public List<User> getUserByName(String name) {
        try {
            logger.debug("根据姓名查询用户: " + name);
            List<User> users = sqlMapClient.queryForList("User.getUserByName", name);
            logger.debug("查询到 " + users.size() + " 个匹配用户");
            return users;
        } catch (SQLException e) {
            logger.error("根据姓名查询用户失败: " + e.getMessage(), e);
            return null;
        }
    }
    
    public int getUserCount() {
        try {
            logger.debug("统计用户数量");
            Integer count = (Integer) sqlMapClient.queryForObject("User.getUserCount");
            logger.debug("用户数量: " + count);
            return count != null ? count : 0;
        } catch (SQLException e) {
            logger.error("统计用户数量失败: " + e.getMessage(), e);
            return 0;
        }
    }
}