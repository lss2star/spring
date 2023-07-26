package com.kopo.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kopo.domain.Book;
import com.kopo.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;

	// @RequestMapping(value = "/books", method=RequestMethod.GET)
	// @RequestMapping
	@GetMapping
	public String requestBookList(Model model) {
		List<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList", list);
		return "books";
	}

	// @RequestMapping("/all")
	/*
	 * @GetMapping("/all") // -> mav 형식으로 변경 public String requestAllBookList(Model
	 * model) { List<Book> list = bookservice.getAllBookList();
	 * model.addAttribute("bookList", list); return "books"; }
	 */

	@GetMapping("/all") // -> mav 형식으로 변경
	public ModelAndView requestAllBooks() {
		ModelAndView mav = new ModelAndView();
		List<Book> list = bookService.getAllBookList();
		mav.addObject("bookList", list);
		mav.setViewName("books");
		return mav;
	}

	/*
	 * @GetMapping("/exam08/{category}/publisher/{publisher}") public String
	 * requestMethod(@PathVariable String category,
	 */

	@GetMapping("/{category}")
	public String requestBooksByCategory(@PathVariable("category") String bookCategory, Model model) {
		List<Book> booksByCategory = bookService.getBookListByCategory(bookCategory);
		model.addAttribute("bookList", booksByCategory);
		return "books";
	}

	@GetMapping("/filter/{bookFilter}")
	public String requestBooksByFilter(@MatrixVariable(pathVar = "bookFilter") Map<String, List<String>> bookFilter,
			Model model) {
		Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);
		model.addAttribute("bookList", booksByFilter);
		return "books";
	}

	@GetMapping("/book")
	public String requestBookById(@RequestParam("id") String bookId, Model model) {
		Book bookById = bookService.getBookById(bookId);
		model.addAttribute("book", bookById);
		return "book";
	}

	@GetMapping("/add")
	// public String requestAddBookForm(Book book) {
	public String requestAddBookForm(@ModelAttribute("NewBook") Book book) {

		/*
		 * 네비게이션 바, jumbotron, footer bookid name unitprice author description publisher
		 * category unitinstock releaseDate condition <- radiobuttontkd
		 * 
		 */
		return "addBook";
	}
	
	@PostMapping("/add")
	public String submitAddNewBook(@ModelAttribute("NewBook") Book book) {
		bookService.setNewBook(book);
		return "redirect:/books"; // 뷰 리다이렉션 * 웹 요청에 따라서 뷰페이지 이동
	}
	
	/*
	 1. redirect
	 		- 요청 URL을 응답 뷰를 이동
	 		- 이때 이동할 URL에 다시 요청을 시도 -> 최초 요청은 무효
	 		- 폼에서 데이터를 입력받는 시스템, 세선EB에 변화가 생기는 요청
	 		
	 2. forward
	 		- 최초 요청 URL을 유지 -> 응답 뷰 페이지를 표현
	 		- 현재 페이지에서 이동할 URL로 정보가 그대로 전달 -> 최초 요청 정보가 유효
	 		- 실제 웹페이지가 변경됐지만 사용자는 알 수 없음
	 		- 시스템 변화가 없는 단순 조회, 등등 사용
	 */
	
	@ModelAttribute
	public void addAttribute(Model model) {
		model.addAttribute("addTitle", "신규 도서 등록");
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("bookId", "name", "tPrice", "author","description","publisher", "category", 
				"unitStock", "releaseDate", "condition");
	}
}
