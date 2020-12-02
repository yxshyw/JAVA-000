package com.yw.yw.mapper;

import java.util.List;

import com.yw.yw.dao.ExamDao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface ExamMapper {
    @Insert("insert into exam(my_var) value(#{myVar})")
    public long insertExam(@Param("myVar") String myVar);

    @Select("select id,gmt_create,gmt_modified,my_var from exam")
    @Results(
        id = "find",
        value = {
            @Result(id=true,column = "gmt_create",property = "gmtCreate"),
            @Result(column = "gmt_create",property = "gmtCreate",jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "gmt_modified",property = "gmtModified",jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "my_var",property = "myVar"),
        }
    )
    public List<ExamDao> findAall();
}