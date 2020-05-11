package com.regino.dao;

import com.regino.domain.QueryVo;
import com.regino.domain.User;

import java.util.List;

public interface UserMapper {

    // 返回主键，方式一
    public void save1(User user);

    // 返回主键，方式二
    public void save2(User user);

    // if 条件判断
    public List<User> findByIdAndUsernameIf(User user);

    // set 更新
    public void updateIf(User user);

    // foreach标签，普通list集合
    public List<User> findByList(List<Integer> ids);

    // foreach标签，普通array数组
    public List<User> findByArray(Integer [] ids);

    // foreach标签，实体属性list集合
    public List<User> findByQueryVo(QueryVo queryVo);
}