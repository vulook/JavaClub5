package com.softserve.Controllers;

import java.util.List;

import com.softserve.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.entity.Book;


@Controller
@RequestMapping("/book")
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public String listBooks(Model theModel) {
        LOG.debug("Show Books handler method");
        List<Book> theBooks = bookService.findAll();
        theModel.addAttribute("books", theBooks);
        return "list-books";
    }
    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        LOG.debug("Inside show book-form handler method");
        Book theBook = new Book();
        theModel.addAttribute("book", theBook);
        return "book-form";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book theBook) {
        LOG.debug("Save Book handler method");
        bookService.create(theBook);
        return "redirect:/book/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("bookID") long theId,
                                    Model theModel) {
        LOG.debug("Update Book handler method");
        Book theBook = bookService.findByID(theId);
        theModel.addAttribute("book", theBook);
        return "book-form";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable long id)  {
        LOG.debug("Delete Book handler method");
        bookService.delete(id);
        return "redirect:/book/list";
    }
    @GetMapping("/delete-copy/{id}")
    public String deleteOneBook(@PathVariable long id)  {
        LOG.debug("Delete Book handler method");
        bookService.deleteCopy(id);
        return "redirect:/book/list";
    }
}