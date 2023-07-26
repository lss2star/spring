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
	// 퍼시스턴스 계층 - 도서의 정보를 관리
	private List<Book> listOfBooks = new ArrayList<Book>();

	public BookRepositoryImpl() {
		Book book1 = new Book("9791198320902", "비욘드 더 스토리", 35000);
		book1.setAuthor("강명석");
		book1.setDescription("/ 방탄소년단 데뷔 10주년, 오피셜 북 최초 출간\r\n" + "/ 우리가 알고 있는 방탄소년단, 그 너머의 이야기를 말하다");
		book1.setPublisher("빅히트엔터테인먼트");
		book1.setCategory("연예인이야기");
		book1.setUnitStock("100");
		book1.setReleaseDate("2023년 07월 09일");
		book1.setCondition("신규");

		Book book2 = new Book("9791192836188", "문과 남자의 과학 공부", 17500);
		book2.setAuthor("유시민");
		book2.setDescription(
				"『문과 남자의 과학 공부』는 역사ㆍ정치ㆍ경제ㆍ글쓰기ㆍ여행 등 인문학 분야의 글을 써온 작가 유시민이 과학을 소재로 쓴 첫 책이다. 유시민에게 “지적 자극과 정서적 감동을 준 과학이론, 인간과 사회와 역사에 대한 생각을 교정해준 정보를 골라 새롭게 해석”했다. 과학과 인문학이 교차ㆍ통섭하는 이야기가 흥미진진하다.");
		book2.setPublisher("돌베개");
		book2.setCategory("인문교양");
		book2.setUnitStock("140");
		book2.setReleaseDate("2023년 06월 23일");
		book2.setCondition("신규");

		Book book3 = new Book("9791168473690", "세이노의 가르침", 7200);
		book3.setAuthor("세이노");
		book3.setDescription(
				"/2000년부터 발표된 그의 주옥같은 글들. 독자들이 자발적으로 만든 제본서는 물론, 전자책과 앱까지 나왔던 《세이노의 가르침》이 드디어 전국 서점에서 독자들을 마주한다. 여러 판본을 모으고 저자의 확인을 거쳐 최근 생각을 추가로 수록하였다. 정식 출간본에만 추가로 수록된 글들은 목차와 본문에 별도 표시하였다.");
		book3.setPublisher("데이원");
		book3.setCategory("자기관리");
		book3.setUnitStock("70");
		book3.setReleaseDate("2023년 03월 02일");
		book3.setCondition("신규");

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
		
		// publisher 검출 작업
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
				List<Book> list = getBookListByCategory(category); // 만들어 놓은 메서드 활용
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
			throw new IllegalArgumentException("도서의 ID를 찾을 수가 없습니다 : " + bookId);
		
		return bookInfo;
	}

	@Override
	public void setNewBook(Book book) {
		listOfBooks.add(book);
	}

	
}
