package com.tangyiheng.wj.dao;

import com.tangyiheng.wj.entity.Book;
import com.tangyiheng.wj.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 定义了一个接口 BookDAO，它继承了Spring Data JPA的 JpaRepository 接口。
// 这个 JpaRepository 接口是一个泛型接口，需要两个参数：实体类和该实体类主键字段的类型。
// Book 是实体类，而其主键 id 的类型是 Integer。
public interface BookDAO extends JpaRepository<Book, Integer> {
    /**
     * 使用Spring Data JPA的方法命名约定。
     * 此方法的功能是查找所有属于特定分类 (Category) 的书籍。
     */
    List<Book> findAllByCategory(Category category);

    /**
     * 遵循Spring Data JPA的命名规则。
     * 这个方法用于根据标题或作者进行模糊搜索。
     * keyword1 用于匹配 title 字段，而 keyword2 用于匹配 author 字段。
     */
    List<Book> findAllByTitleLikeOrAuthorLike(String keyword1, String keyword2);
}

// Spring Data JPA的一个主要优点是它可以根据方法名自动生成查询逻辑。
// 这意味着你不需要编写具体的SQL查询或JPQL（Java Persistence Query Language）查询；
// 只需遵循一定的命名约定，Spring Data JPA会为你处理剩下的工作。