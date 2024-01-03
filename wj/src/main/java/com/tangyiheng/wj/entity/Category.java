package com.tangyiheng.wj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

// 一个Lombok库的注解，它为类提供了getter和setter方法，以及toString, equals, 和 hashCode 方法。
@Data
// 一个JPA（Java Persistence API）注解，它标记这个类是一个实体类，即它与数据库中的表相对应。
@Entity
// 一个JPA注解，用于指定这个实体类映射到数据库中哪个表。
@Table(name = "category")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Category {
    // 一个JPA注解，用于指示哪个字段是实体的主键。
    @Id
    // 一个JPA注解，指定主键的生成策略。在这种情况下，使用的是数据库自动生成键值（自动增长型）。
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 一个JPA注解，指定这个字段映射到数据库表的哪一列。
    @Column(name = "id")
    int id;

    String name;
}
