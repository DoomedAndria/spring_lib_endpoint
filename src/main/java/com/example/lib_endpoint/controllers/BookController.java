package com.example.lib_endpoint.controllers;

import com.example.lib_endpoint.models.Book;
import com.example.lib_endpoint.utils.BookUtil;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @GetMapping
    public List<Book> getBooks() throws SQLException {
        return BookUtil.getInstance().getBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) throws SQLException {
        return BookUtil.getInstance().getBook(id);
    }

    @PostMapping
    public Book insertBook(@RequestBody Book book) throws SQLException {
        BookUtil.getInstance().insertBook(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) throws SQLException {
        BookUtil.getInstance().updateBook(id, book);
        return getBook(id);
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable Long id) throws SQLException {
        Book book = getBook(id);
        BookUtil.getInstance().deleteBook(id);
        return book;
    }
}
