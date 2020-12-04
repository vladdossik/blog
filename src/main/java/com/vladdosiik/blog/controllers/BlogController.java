package com.vladdosiik.blog.controllers;

import com.vladdosiik.blog.models.Post;
import com.vladdosiik.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {
@Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Post>posts=postRepository.findAll();
        model.addAttribute("posts",posts);
        return "blog-Main";
    }
    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog_add";
    }
    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,@RequestParam String anons,@RequestParam String full_text, Model model){
        Post post=new Post(title,anons,full_text);
postRepository.save(post);
        return "redirect:/blog";
    }
    @GetMapping("/blog/{id}")
    public String blogDetailes(@PathVariable(value="id")long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post= postRepository.findById(id);
        ArrayList<Post>res=new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "blog_details";
    }
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value="id")long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post= postRepository.findById(id);
        ArrayList<Post>res=new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "blog-edit";
    }
}
