package com.tangyiheng.wj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity // 表示这是一个实体类
@Table(name = "user") // 表示对应的表名是 user
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"}) // 忽略无需json化的属性
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String username;
    String password;
}
