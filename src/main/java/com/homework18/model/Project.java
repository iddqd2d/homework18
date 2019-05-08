package com.homework18.model;


import com.homework18.annotation.InjectRandomInt;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class Project {
    private Integer id;

    private String name;

    @InjectRandomInt(min = 1000, max = 10000)
    private int balance;

}
