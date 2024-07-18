package org.example.blogproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blogproject.Service.CommentService;
import org.example.blogproject.Service.PostService;
import org.example.blogproject.Service.UserService;
import org.example.blogproject.domain.Comment;
import org.example.blogproject.domain.Post;
import org.example.blogproject.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/minlog")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

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
    @GetMapping("/viewpost/{id}")
    public String viewpost(@PathVariable("id") Long id, Model model){
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
        model.addAttribute("dateformatter", formatter);
        return "viewpost";
    }
    @PostMapping("/viewpost/{id}/comment")
    public String comment(@PathVariable("id") Long id, @ModelAttribute("comment")Comment comment, RedirectAttributes redirectAttributes){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        log.info(":::::username :: " + userDetails.getUsername());
        User user = userService.getUser(userDetails.getUsername());
        comment.setUser(user);
        comment.setPost(postService.getPost(id));
        commentService.saveComment(comment);
//        redirectAttributes.addAttribute("id", id);
        return "redirect:/minlog/viewpost/" + id;
    }
    @GetMapping("/mypage/{id}/delete")
    public String deletepost(@PathVariable("id") Long id){
        postService.deletePost(id);
        return "redirect:/minlog/mypage";
    }
}
