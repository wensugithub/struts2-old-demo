package com.example.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.example.dao.UserDao;
import com.example.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    
    // 添加日志记录器
    private static final Logger logger = Logger.getLogger(UserAction.class);
    
    private List<User> userList;
    private String message;
    private UserDao userDao;
    
    // 用于接收参数的属性
    private Long userId;
    private User user;
    private String searchName;
    
    public UserAction() {
        this.userDao = new UserDao();
        logger.debug("UserAction 初始化完成");
    }
    
    public String list() {
    	logger.info("========== 开始处理用户列表请求 ==========");
        try {
        	// 使用 iBATIS 从数据库获取数据
            userList = userDao.getAllUsers();
            if (userList != null) {
            	 message = "成功获取用户列表，共 " + userList.size() + " 条记录";
                 logger.info(message);
                 // 记录详细的用户信息（DEBUG级别）
                 if (logger.isDebugEnabled()) {
                     for (User user : userList) {
                         logger.debug("用户信息: " + user.toString());
                     }
                 }
            } else {
            	 message = "获取用户列表失败";
            	 logger.warn(message);
            	 addActionError(message);
            	 return ERROR;
            }
            logger.info("========== 用户列表请求处理完成 ==========");
            return SUCCESS;
        } catch (Exception e) {
            message = "获取用户列表时发生错误: " + e.getMessage();
            logger.error(message, e);
            addActionError(message);
            logger.error("========== 用户列表请求处理失败 ==========");
            return ERROR;
        } 
    }

    public String search() {
        logger.info("========== 开始处理用户搜索请求 ==========");
        logger.debug("搜索关键词: " + searchName);
        
        try {
            if (searchName != null && !searchName.trim().isEmpty()) {
                userList = userDao.getUserByName(searchName.trim());
                
                if (userList != null) {
                    message = "找到 " + userList.size() + " 个匹配用户";
                    logger.info(message);
                } else {
                    message = "搜索用户失败";
                    logger.warn(message);
                }
            } else {
                message = "请输入搜索关键词";
                logger.warn(message);
            }
            
            logger.info("========== 用户搜索请求处理完成 ==========");
            return SUCCESS;
            
        } catch (Exception e) {
            message = "搜索用户时发生错误: " + e.getMessage();
            logger.error(message, e);
            addActionError(message);
            return ERROR;
        }
    }
    
    
    // Getters and Setters
    public List<User> getUserList() {
        return userList;
    }
    
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getSearchName() {
        return searchName;
    }
    
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
}