package com.tangyiheng.wj.controller;

import com.tangyiheng.wj.entity.Book;
import com.tangyiheng.wj.service.BookService;
import com.tangyiheng.wj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

// Spring 框架的一个注解。
// 它表明这个类是一个控制器，其方法返回的数据应该直接写入响应体，通常是 JSON 或 XML 格式。
@RestController
public class LibraryController {

    @Autowired
    BookService bookService;

    // 定义了一个处理 /api/books URL 上的 HTTP GET 请求的方法。它返回书籍列表。
    @GetMapping("/api/books")
    public List<Book> list() throws Exception {
        return bookService.list();
    }

    // 定义了一个处理 /api/books URL 上的 HTTP POST 请求的方法。它用于添加或更新一本书。
    @PostMapping("/api/books")
    public Book addOrUpdate(@RequestBody Book book) throws Exception {
        bookService.addOrUpdate(book);
        return book;
    }

    // 处理在 /api/delete URL 上删除书籍的 POST 请求。
    @PostMapping("/api/delete")
    public void delete(@RequestBody Book book) throws Exception {
        bookService.deleteById(book.getId());
    }


    // @GetMapping("/api/categories/{cid}/books")：这是一个通过类别获取书籍的方法。
    // 它处理像 /api/categories/1/books 这样的 URL 上的 GET 请求。
    @GetMapping("/api/categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid") int cid) throws Exception {
        if (0 != cid) {
            return bookService.listByCategory(cid);
        } else {
            return list();
        }
    }

    // @GetMapping("/api/search")：一个处理 /api/search URL 上的 HTTP GET 请求的方法，用于在书籍中搜索。
    @GetMapping("/api/search")
    public List<Book> searchResult(@RequestParam("keywords") String keywords) {
        // 关键词为空时查询出所有书籍
        if ("".equals(keywords)) {
            return bookService.list();
        } else {
            return bookService.Search(keywords);
        }
    }

    // 处理文件上传
    @PostMapping("api/covers")
    public String coversUpload(MultipartFile file) throws Exception {
        // 指定了文件将被存储的本地目录。
        // 在生产环境中，最好将文件存储在项目目录之外的位置，并通过静态资源配置或专用文件服务器来处理它们。
        String folder = "/home/tangyiheng/wj/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            // 将上传的文件保存到服务器上指定的位置
            file.transferTo(f);
            // 保存文件后，该方法生成了一个可用于访问文件的 URL
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
