package com.tangyiheng.wj.dao;

import com.tangyiheng.wj.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
