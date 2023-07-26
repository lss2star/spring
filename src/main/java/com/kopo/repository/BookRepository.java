package com.kopo.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kopo.domain.Book;

public interface BookRepository {
	// ��ü ���� ��� ��ȯ
	List<Book> getAllBookList();
	
	// �Ű������� ������ ������ ������ ��ȯ
	List<Book> getBookListByCategory(String category);
	
	// category & publisher�� ������ ������ ��ȯ
	// ex URL) localhost:8082/controller/books/filter/bookFilter;publisher=���̹�;category=IT������
	// �� url�� �Է��ϸ� �����ϴ� ��ϸ� ��ȯ�ǵ��� �����Ͻÿ�.
	Set<Book> getBookListByFilter(Map<String, List<String>> filter);
	
	Book getBookById(String bookId);
	
	void setNewBook(Book book);
}
