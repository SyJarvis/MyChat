package com.taihua.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtils {
    // 数据库操作工具类
    private static DruidDataSource ds;
    private static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();
    static {
        // 拿到字节输入流
        Properties properties = new Properties();
//        InputStream is = DbUtils.class.getResourceAsStream("/database.properties");
        InputStream is = DbUtils.class.getResourceAsStream("/database.properties");
        try{

            properties.load(is);
            ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            ds.setTestWhileIdle(true);
            ds.setTimeBetweenEvictionRunsMillis(20000);
            System.out.println(ds.getRemoveAbandonedTimeout());
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection connection = THREAD_LOCAL.get();
        try{
            if (connection==null){
                connection = ds.getConnection();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return connection;
    }

    // 开启事务
    public static void begin(){
        Connection connection = null;
        try{
            connection = getConnection();
            // 关闭自动提交
            connection.setAutoCommit(false);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // 提交事务
    public static void commit(){
        Connection connection = null;
        try{
            connection = getConnection();
            connection.setAutoCommit(false);
            connection.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeAll(connection, null, null);
        }
    }

    // 回滚事务
    public static void rollback(){
        Connection connection  = null;
        try{
            connection = getConnection();
            connection.rollback();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeAll(connection,null,null);
        }
    }

    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet){
        try{
            if (resultSet!=null){
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
                THREAD_LOCAL.remove();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
