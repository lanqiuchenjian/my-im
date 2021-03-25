package com.myim.server.dao.ext.mapper;

import com.myim.server.dao.gen.domain.ImMessage;
import com.myim.server.dao.gen.domain.ImMessageExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ImMessageExtMapper {
    @DeleteProvider(type=ImMessageExtSqlProvider.class, method="deleteByExample")
    int deleteByExample(ImMessageExample example);
}