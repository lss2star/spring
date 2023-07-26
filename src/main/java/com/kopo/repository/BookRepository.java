package com.kopo.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kopo.domain.Book;

public interface BookRepository {
	// 전체 도서 목록 반환
	List<Book> getAllBookList();
	
	// 매개변수와 동일한 종류의 도서만 반환
	List<Book> getBookListByCategory(String category);
	
	// category & publisher가 동일한 도서만 반환
	// ex URL) localhost:8082/controller/books/filter/bookFilter;publisher=네이버;category=IT전문서
	// 위 url을 입력하면 부합하는 목록만 반환되도록 개발하시오.
	Set<Book> getBookListByFilter(Map<String, List<String>> filter);
	
	Book getBookById(String bookId);
	
	void setNewBook(Book book);
}
