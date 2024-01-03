package com.tangyiheng.wj.service;

import com.tangyiheng.wj.dao.CategoryDAO;
import com.tangyiheng.wj.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> list() {
        // 对查询的结果做排序
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
        // 使用Sort类提供的公共方法或构造函数来创建Sort对象
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }

    public Category get(int id) {
        Category c = categoryDAO.findById(id).orElse(null);
        return c;
    }
}
