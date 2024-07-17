package org.example.blogproject.Service;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.domain.Comment;
import org.example.blogproject.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    @Transactional
    public Comment saveComment(Comment comment){
        return commentRepository.save(comment);
    }
}
