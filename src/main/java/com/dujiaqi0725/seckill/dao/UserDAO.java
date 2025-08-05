package com.dujiaqi0725.seckill.dao;

import com.dujiaqi0725.seckill.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDAO {

    int add(UserDO userDO);

    UserDO selectById(@Param("id") Long id);

    UserDO selectByUsername(@Param("username") String username);

    List<UserDO> selectByIds(@Param("ids")List<Long> ids);

    int update(UserDO userDO);

}
