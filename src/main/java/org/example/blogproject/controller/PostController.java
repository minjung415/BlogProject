package org.example.blogproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blogproject.Service.PostService;
import org.example.blogproject.Service.UserService;
import org.example.blogproject.domain.Post;
import org.example.blogproject.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/minlog")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/postform")
    public String postform(){
        return "postform";
    }
    @PostMapping("/post")
    public String post(@ModelAttribute("post")Post post, BindingResult result){
        if (result.hasErrors()){
            return "/minlog/postform";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        log.info(":::::username :: " + userDetails.getUsername());
        User user = userService.getUser(userDetails.getUsername());
        post.setUser(user);

        postService.savePost(post);

        return "redirect:/minlog";
    }
}
