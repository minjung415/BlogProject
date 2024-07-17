package org.example.blogproject.Service;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.domain.Post;
import org.example.blogproject.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Set<Post> postList() {
        Set<Post> posts = postRepository.findAllByTemporaryIsFalse();
        return posts;
    }
    @Transactional(readOnly = true)
    public Post getPost(Long id){
        return postRepository.findById(id).orElse(null);
    }
}
