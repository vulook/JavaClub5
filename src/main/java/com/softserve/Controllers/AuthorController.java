package com.softserve.Controllers;

import com.softserve.entity.Author;
import com.softserve.entity.Book;
import com.softserve.services.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/author")
public class AuthorController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorService authorService;

    // show all authors
    @GetMapping("/list")
    public String listAuthors(Model theModel) {
        LOG.debug("Show Books handler method");
        List<Author> theAuthors = authorService.findAll();
        theModel.addAttribute("authors", theAuthors);
        return "list-authors";
    }

    // updates author from form
    @PostMapping("/saveAuthor")
    public String saveAuthor(@ModelAttribute("author") Author theAuthor) {
        LOG.debug("Save Book handler method");
        authorService.update(theAuthor);
        return "redirect:/author/list";
    }

    // show update form
    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("authorID") long theId,
                                    Model theModel) {
        LOG.debug("Update Book handler method");
        Author theAuthor = authorService.read(theId);
        theModel.addAttribute("author", theAuthor);
        return "author-form";
    }

    //deletes author by id
    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable long id) {
        LOG.debug("Delete Book handler method");
        authorService.delete(id);
        return "redirect:/author/list";
    }
}