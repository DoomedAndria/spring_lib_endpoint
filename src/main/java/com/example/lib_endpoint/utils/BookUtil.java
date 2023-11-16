package com.example.lib_endpoint.utils;

import com.example.lib_endpoint.DB_Connector;
import com.example.lib_endpoint.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookUtil {
    private static BookUtil bu;
    private static final Statement statement = DB_Connector.getStatement();

    private BookUtil() {

    }

    public static BookUtil getInstance() {
        return bu == null ? bu = new BookUtil() : bu;
    }

    public List<Book> getBooks() throws SQLException {
        String sql = "SELECT * from books";
        ResultSet rs = statement.executeQuery(sql);
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            String author = rs.getString("author");
            Integer year = rs.getInt("year");
            Integer pages = rs.getInt("pages");
            books.add(new Book(id, name, author, year, pages));
        }
        return books;
    }

    public Book getBook(Long id) throws SQLException {
        String sql = "SELECT * from books WHERE id = " + id;
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            Long id1 = rs.getLong("id");
            String name = rs.getString("name");
            String author = rs.getString("author");
            Integer year = rs.getInt("year");
            Integer pages = rs.getInt("pages");
            return new Book(id1, name, author, year, pages);
        }
        return null;
    }

    public void insertBook(Book book) throws SQLException {
        String sql = String.format("INSERT INTO books (name, author, year, pages)VALUES('%s','%s',%d,%d)"
                , book.getName(), book.getAuthor(), book.getYear(), book.getPages());
        statement.executeUpdate(sql);
    }

    public void updateBook(Long id, Book book) throws SQLException {
        String sql = String.format("UPDATE books SET name = '%s', author = '%s', year = %d, pages = %d WHERE id = %d "
                , book.getName(), book.getAuthor(), book.getYear(), book.getPages(), id);
        statement.executeUpdate(sql);
    }

    public void deleteBook(Long id) throws SQLException {
        String sql = "DELETE FROM books WHERE id=" + id;
        statement.executeUpdate(sql);
    }
}
