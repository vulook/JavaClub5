package com.softserve.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.softserve.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.entity.Book;

import javax.servlet.http.HttpServletRequest;


@Controller
//@RequestMapping("/book")
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping("/book/list")
    public String listBooks(Model theModel) {
        LOG.debug("Show Books handler method");
        List<Book> theBooks = bookService.findAll();
        theModel.addAttribute("books", theBooks);
        return "list-books";
    }

    @RequestMapping("/books")
    public String show(Model theModel) {
        LOG.debug("Show Books handler method");
        List<Book> theBooks = bookService.findAll();
        theModel.addAttribute("books", theBooks);
        return "all-books";
    }

    @RequestMapping("/books/my")
    public String showMy(Model theModel) {
        LOG.debug("Show Books handler method");
        List<Book> theBooks = bookService.findBookByUser("reading");
        List<Book> theBooks1 = bookService.findBookByUser("read");
        theModel.addAttribute("reading", theBooks);
        theModel.addAttribute("books", theBooks1);
        return "user-books";
    }

    @GetMapping("/book/showForm")
    public String showFormForAdd(Model theModel) {
        LOG.debug("Inside show book-form handler method");
        Book theBook = new Book();
        theModel.addAttribute("book", theBook);
        return "book-form";
    }

    @PostMapping("/book/saveBook")
    public String saveBook(@ModelAttribute("book") Book theBook) {
        LOG.debug("Save Book handler method");
        bookService.create(theBook);
        return "redirect:/book/list";
    }

    @GetMapping("books/info")
    public String showBookInfo(@RequestParam("bookID") long theId,
                               Model theModel) {
        LOG.debug("Update Book handler method");
        Book theBook = bookService.findByID(theId);
        theModel.addAttribute("book", theBook);
        return "book-info";
    }

    @GetMapping("/book/updateForm")
    public String showFormForUpdate(@RequestParam("bookID") long theId,
                                    Model theModel) {
        LOG.debug("Update Book handler method");
        Book theBook = bookService.findByID(theId);
        theModel.addAttribute("book", theBook);
        return "book-form";

    }

    @GetMapping("/books/filter")
    public String showFilters(Model theModel) {
        LOG.debug("Update Book handler method");
        String bookSearch = "";
        String authorSearch = "";
        theModel.addAttribute("name", bookSearch);
        theModel.addAttribute("author", authorSearch);
//        theModel.addAttribute("book", theBook);
        return "filter";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        LOG.debug("Delete Book handler method");
        bookService.delete(id);
        return "redirect:/book/list";
    }

    @GetMapping("/book/delete-copy/{id}")
    public String deleteOneBook(@PathVariable long id) {
        LOG.debug("Delete Book handler method");
        bookService.deleteCopy(id);
        return "redirect:/book/list";
    }

    @GetMapping("/books/showBooks")
    public String applyFilter(@RequestParam("bookName") String bookName, @RequestParam("Author") String author, @RequestParam("popular") String popular, @RequestParam("start") String start, @RequestParam("end") String end,@RequestParam("available") String available, Model theModel) {
        LOG.debug("Show Books handler method");
//        String author = request.getParameter("bookName");
        List<Book> allFilters = new ArrayList<>();
        if (popular.equals("nothing")) {
            List<Book> nameFilter = bookService.findBookByName(bookName);
            List<Book> authorFilter = bookService.findBookByAuthor(author);
            for (Book b : nameFilter) {
                for (Book book : authorFilter) {
                    if (b.equals(book)) allFilters.add(book);
                }
            }
        } else if (popular.equals("popular")) {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
            allFilters = bookService.findPopular(startDate, endDate);

        } else {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
            allFilters = bookService.findUnpopular(startDate, endDate);
        }
        if(available.equals("true")){
            allFilters = allFilters.stream().filter(x->x.getCount()>0).collect(Collectors.toList());
        }
        theModel.addAttribute("books", allFilters);
        return "all-books";
    }
}