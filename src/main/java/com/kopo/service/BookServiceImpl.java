package com.kopo.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kopo.domain.Book;
import com.kopo.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	/*
	 @Autowired annotation을 사용하지 않을 경우 setter를 정의해주어야 함.
	 public void setBookRepository (BookRepository bookRepository) {
	 	this.bookRepository = bookRepository;
	 }
	 */
	
	@Override
	public List<Book> getAllBookList() {
		// TODO Auto-generated method stub
		return bookRepository.getAllBookList();
	}

	@Override
	public List<Book> getBookListByCategory(String category) {
		List<Book> booksByCategory = 
				bookRepository.getBookListByCategory(category);
		return booksByCategory;
	}

	@Override
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		Set<Book> bookByFilter = bookRepository.getBookListByFilter(filter);
		return bookByFilter;
	}

	@Override
	public Book getBookById(String bookId) {
		Book bookById = bookRepository.getBookById(bookId);
		return bookById;
	}

	@Override
	public void setNewBook(Book book) {
		bookRepository.setNewBook(book);
	}

	

}
