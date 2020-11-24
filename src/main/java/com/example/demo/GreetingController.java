package com.example.demo;

import com.example.demo.models.Post;
import com.example.demo.repo.PostRepository;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class GreetingController {

    @Autowired
    private PostRepository postRepository;


    @GetMapping("/greeting")
    public String greeting(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "greeting";
    }
    @GetMapping("/features")
    public String features(Model model){
        model.addAttribute("name", "Фичи");
        return "features";
    }
    @GetMapping("/contacts")
    public String contacts(Model model){
        model.addAttribute("name", "Контакты");
        return "contacts";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        model.addAttribute("name", "Контакты");
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        Post post = new Post(title,anons,full_text);
        postRepository.save(post);
        return "redirect:/greeting";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id,Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/greeting";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res :: add);
        model.addAttribute("post", res);
        return "blog-details";
    }





































}