package com.regino.service;

import com.regino.dao.UserMapper;
import com.regino.domain.QueryVo;
import com.regino.domain.User;
import com.regino.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserMapperTest {

    private SqlSession sqlSession = null;

    // 此方法在测试方法执行之前，执行
    @Before
    public void before() {
        // 获取sqlSession对象
        sqlSession = MyBatisUtils.openSession();// 此方法必须线程内独享....
    }

    // 此方法在测试地方法执行之后，执行
    @After
    public void after() {
        // 关闭sqlSession
        MyBatisUtils.close(sqlSession);
    }

    // 返回主键
    @Test
    public void test01() throws Exception {
        // 获取代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setUsername("regino");
        user.setBirthday(new Date());
        user.setAddress("hk");
        user.setSex("男");
        // 方式一
        // userMapper.save1(user);
        userMapper.save2(user);

        System.out.println("新增时，主键返回：" + user.getId());
    }

    // if判断
    @Test
    public void test02() throws Exception {
        // 获取代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 拼接条件
        User param = new User();
        // param.setId(41);
        // param.setUsername("老王");

        List<User> list = userMapper.findByIdAndUsernameIf(param);

        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void test03()throws Exception{
        // 获取代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 设置更新内容
        User user = new User();
        user.setId(56);
        user.setUsername("set");

        userMapper.updateIf(user);
    }

    //  foreach标签
    @Test
    public void test04()throws Exception{
        // 获取代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 普通list集合
       /* List ids = new ArrayList();
        ids.add(41);
        ids.add(46);
        List list = userMapper.findByList(ids);*/

       // 普通array数组
      /* Integer[] ids = {41,46,49};
        List<User> list = userMapper.findByArray(ids);*/

        // 实体属性list集合
        List ids = new ArrayList();
        ids.add(41);
        ids.add(46);
        QueryVo queryVo = new QueryVo();
        queryVo.setIds(ids);
        List<User> list = userMapper.findByQueryVo(queryVo);
        System.out.println(list);
    }
}