package com.yw.yw.dao;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamDao {
    private long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    private String myVar;
}