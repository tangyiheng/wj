package com.tangyiheng.wj.service;

import com.tangyiheng.wj.dao.BookDAO;
import com.tangyiheng.wj.entity.Book;
import com.tangyiheng.wj.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

// 标记这个类是一个服务层组件。
// 在Spring的依赖注入容器中，这个类的实例将被作为服务进行管理。
@Service
public class BookService {

    // 使用 @Autowired 注解自动注入 BookDAO 类型的依赖
    @Autowired
    BookDAO bookDAO;

    @Autowired
    CategoryService categoryService;

    // 获取所有的书籍记录
    public List<Book> list() {
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return bookDAO.findAll(sort);
    }

    // 添加或更新书籍记录
    public void addOrUpdate(Book book) {
        bookDAO.save(book);
    }

    // 根据ID删除书籍记录
    public void deleteById(int id) {
        bookDAO.deleteById(id);
    }

    // 获取特定分类下的所有书籍
    public List<Book> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return bookDAO.findAllByCategory(category);
    }

    // 根据标题或作者模糊查询书籍记录
    public List<Book> Search(String keywords) {
        return bookDAO.findAllByTitleLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');
    }
}
