package com.example.util;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import org.apache.log4j.Logger;
import java.io.Reader;
import java.io.IOException;

public class SqlMapClientFactory {
    private static final Logger logger = Logger.getLogger(SqlMapClientFactory.class);
    private static SqlMapClient sqlMapClient;
    
    static {
        try {
            logger.info("开始初始化 iBATIS SqlMapClient...");
            
            String resource = "sql-map-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
            
            logger.info("iBATIS SqlMapClient 初始化成功");
            
            // 初始化数据库
            initializeDatabase();
            
        } catch (IOException e) {
            logger.error("读取 iBATIS 配置文件失败: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("初始化 iBATIS SqlMapClient 失败: " + e.getMessage(), e);
        }
    }
    
    private static void initializeDatabase() {
        try {
            logger.debug("开始初始化数据库...");
            
            // 创建用户表
            sqlMapClient.update("User.createUserTable");
            logger.debug("用户表创建成功");
            
            // 清空现有数据（如果有）
            sqlMapClient.update("User.deleteAllUsers");
            logger.debug("清空用户表数据");
            
            // 插入示例数据
            sqlMapClient.update("User.insertSampleData");
            logger.debug("示例数据插入成功");
            
            // 验证数据
            int userCount = (Integer) sqlMapClient.queryForObject("User.getUserCount");
            logger.info("数据库初始化完成，当前用户数量: " + userCount);
            
        } catch (Exception e) {
            logger.error("数据库初始化失败: " + e.getMessage(), e);
        }
    }
    
    public static SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
}