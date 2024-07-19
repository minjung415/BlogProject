package org.example.blogproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.Service.PostService;
import org.example.blogproject.Service.UserService;
import org.example.blogproject.domain.Post;
import org.example.blogproject.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;
import java.util.Set;

@Controller
@RequestMapping("/minlog")
@RequiredArgsConstructor
public class MainController {
    private final PostService postService;
    private final UserService userService;
    @GetMapping
    public String home(Model model){
        Set<Post> posts = postService.postList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
        model.addAttribute("posts", posts);
        model.addAttribute("dateformatter", formatter);
        return "main";
    }
    @GetMapping("/profile/{username}")
    public String page(@PathVariable("username") String username, Model model){
        User user = userService.getUser(username);
//        Set<Post> posts = postService.postList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
        model.addAttribute("user", user);
//        model.addAttribute("posts", posts);
        model.addAttribute("dateformatter", formatter);
        return "profile";
    }
    @GetMapping("/mypage")
    public String mypage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User user = userService.getUser(userDetails.getUsername());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
        model.addAttribute("user",user);
        model.addAttribute("dateformatter", formatter);
        return "mypage";
    }
}
