package com.wei.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Author: weizz
 * @Date: 2019/5/21 10:34
 * @Description:
 * @Version:1.0
 */
@Data
public class User implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

}
