package com.example.libraryapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BookRepository {
    private BookDao bookDao;
    private LiveData<List<Book>> books;

    BookRepository(Application application) {
        BookDatabase database = BookDatabase.getDatabase(application);
        bookDao = database.bookDao();
        books = bookDao.findAll();
    }

    LiveData<List<Book>> findAllBooks() {
        return books;
    }

    void insert(Book book) {
        BookDatabase.databaseWriteExecutor.execute(() -> {
            bookDao.insert(book);
        });
    }

    void update(Book book) {
        BookDatabase.databaseWriteExecutor.execute(() -> {
            bookDao.update(book);
        });
    }

    void delete(Book book) {
        BookDatabase.databaseWriteExecutor.execute(() -> {
            bookDao.delete(book);
        });
    }
}
