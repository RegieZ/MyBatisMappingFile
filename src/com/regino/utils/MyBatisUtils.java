package com.regino.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtils {

    private static SqlSessionFactory sqlSessionFactory = null;

    // 在静态代码块中（1.加载核心配置文件 2.构建工厂对象）
    static {
        try {
            // 1.加载核心配置文件
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            // 2.构建工厂对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 提供获取sqlSession的静态方法
    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    // 提供提交事务和释放资源方法
    public static void close(SqlSession sqlSession){
        // 提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }
}