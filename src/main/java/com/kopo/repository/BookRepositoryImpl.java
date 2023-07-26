package com.kopo.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.kopo.domain.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {
	// �۽ý��Ͻ� ���� - ������ ������ ����
	private List<Book> listOfBooks = new ArrayList<Book>();

	public BookRepositoryImpl() {
		Book book1 = new Book("9791198320902", "���� �� ���丮", 35000);
		book1.setAuthor("����");
		book1.setDescription("/ ��ź�ҳ�� ���� 10�ֳ�, ���Ǽ� �� ���� �Ⱓ\r\n" + "/ �츮�� �˰� �ִ� ��ź�ҳ��, �� �ʸ��� �̾߱⸦ ���ϴ�");
		book1.setPublisher("����Ʈ�������θ�Ʈ");
		book1.setCategory("�������̾߱�");
		book1.setUnitStock("100");
		book1.setReleaseDate("2023�� 07�� 09��");
		book1.setCondition("�ű�");

		Book book2 = new Book("9791192836188", "���� ������ ���� ����", 17500);
		book2.setAuthor("���ù�");
		book2.setDescription(
				"������ ������ ���� ���Ρ��� �������ġ���������۾�������� �� �ι��� �о��� ���� ��� �۰� ���ù��� ������ ����� �� ù å�̴�. ���ùο��� ������ �ڱذ� ������ ������ �� �����̷�, �ΰ��� ��ȸ�� ���翡 ���� ������ �������� ������ ��� ���Ӱ� �ؼ����ߴ�. ���а� �ι����� �������뼷�ϴ� �̾߱Ⱑ ��������ϴ�.");
		book2.setPublisher("������");
		book2.setCategory("�ι�����");
		book2.setUnitStock("140");
		book2.setReleaseDate("2023�� 06�� 23��");
		book2.setCondition("�ű�");

		Book book3 = new Book("9791168473690", "���̳��� ����ħ", 7200);
		book3.setAuthor("���̳�");
		book3.setDescription(
				"/2000����� ��ǥ�� ���� �ֿ����� �۵�. ���ڵ��� �ڹ������� ���� �������� ����, ����å�� �۱��� ���Դ� �����̳��� ����ħ���� ���� ���� �������� ���ڵ��� �����Ѵ�. ���� �Ǻ��� ������ ������ Ȯ���� ���� �ֱ� ������ �߰��� �����Ͽ���. ���� �Ⱓ������ �߰��� ���ϵ� �۵��� ������ ������ ���� ǥ���Ͽ���.");
		book3.setPublisher("���̿�");
		book3.setCategory("�ڱ����");
		book3.setUnitStock("70");
		book3.setReleaseDate("2023�� 03�� 02��");
		book3.setCondition("�ű�");

		listOfBooks.add(book1);
		listOfBooks.add(book2);
		listOfBooks.add(book3);
	}

	@Override
	public List<Book> getAllBookList() {
		// TODO Auto-generated method stub
		return listOfBooks;
	}

	@Override
	public List<Book> getBookListByCategory(String category) {
		List<Book> booksByCategory = new ArrayList<Book>();
		for (int i = 0; i < listOfBooks.size(); i++) {
			Book book = listOfBooks.get(i);
			if (category.equalsIgnoreCase(book.getCategory()))
				booksByCategory.add(book);
		}
		return booksByCategory;
	}

	@Override
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		Set<Book> bookskByPublisher = new HashSet<Book>();
		Set<Book> bookskByCategory = new HashSet<Book>();
		
		Set<String> booksByFilter = filter.keySet();
		
		// publisher ���� �۾�
		if (booksByFilter.contains("publisher")) {
			for (int j = 0; j < filter.get("publisher").size(); j++) {
				String publisherName = filter.get("publisher").get(j);
				for (int i = 0; i < listOfBooks.size(); i++) {
					Book book = listOfBooks.get(i);
					
					if (publisherName.equalsIgnoreCase(book.getPublisher()))
						bookskByPublisher.add(book);
				}
			}
		}
		if (booksByFilter.contains("category")) {
			for (int i = 0; i < filter.get("category").size(); i++) {
				String category = filter.get("category").get(i);
				List<Book> list = getBookListByCategory(category); // ����� ���� �޼��� Ȱ��
				bookskByCategory.addAll(list);
			}
		}
		bookskByCategory.retainAll(bookskByPublisher);
		return bookskByCategory;
	}

	@Override
	public Book getBookById(String bookId) {
		Book bookInfo = null;
		for (int i = 0; i < listOfBooks.size(); i++) {
			Book book = listOfBooks.get(i);
			if (book != null && book.getBookId() != null && book.getBookId().equals(bookId)) {
				bookInfo = book;
				break;
			}
		}
		if (bookInfo == null) 
			throw new IllegalArgumentException("������ ID�� ã�� ���� �����ϴ� : " + bookId);
		
		return bookInfo;
	}

	@Override
	public void setNewBook(Book book) {
		listOfBooks.add(book);
	}

	
}
