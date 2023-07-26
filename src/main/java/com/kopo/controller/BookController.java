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
	 * @GetMapping("/all") // -> mav �������� ���� public String requestAllBookList(Model
	 * model) { List<Book> list = bookservice.getAllBookList();
	 * model.addAttribute("bookList", list); return "books"; }
	 */

	@GetMapping("/all") // -> mav �������� ����
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
		 * �׺���̼� ��, jumbotron, footer bookid name unitprice author description publisher
		 * category unitinstock releaseDate condition <- radiobuttontkd
		 * 
		 */
		return "addBook";
	}
	
	@PostMapping("/add")
	public String submitAddNewBook(@ModelAttribute("NewBook") Book book) {
		bookService.setNewBook(book);
		return "redirect:/books"; // �� �����̷��� * �� ��û�� ���� �������� �̵�
	}
	
	/*
	 1. redirect
	 		- ��û URL�� ���� �並 �̵�
	 		- �̶� �̵��� URL�� �ٽ� ��û�� �õ� -> ���� ��û�� ��ȿ
	 		- ������ �����͸� �Է¹޴� �ý���, ����EB�� ��ȭ�� ����� ��û
	 		
	 2. forward
	 		- ���� ��û URL�� ���� -> ���� �� �������� ǥ��
	 		- ���� ���������� �̵��� URL�� ������ �״�� ���� -> ���� ��û ������ ��ȿ
	 		- ���� ���������� ��������� ����ڴ� �� �� ����
	 		- �ý��� ��ȭ�� ���� �ܼ� ��ȸ, ��� ���
	 */
	
	@ModelAttribute
	public void addAttribute(Model model) {
		model.addAttribute("addTitle", "�ű� ���� ���");
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("bookId", "name", "tPrice", "author","description","publisher", "category", 
				"unitStock", "releaseDate", "condition");
	}
}
