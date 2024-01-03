package com.tangyiheng.wj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    // 一个JPA注解，表示存在一个多对一的关系。
    @ManyToOne
    // 一个JPA注解，指定了该字段是通过哪个列进行连接的。
    @JoinColumn(name="cid")
    private Category category;

    String cover;
    String title;
    String author;
    String date;
    String press;
    String abs;
}
