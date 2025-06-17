package com.example.blog.application.service.comment;

import com.example.blog.application.dto.CommentDto;
import com.example.blog.application.model.Blogs;
import com.example.blog.application.model.Comments;
import com.example.blog.application.model.Users;
import com.example.blog.application.repository.BlogRepository;
import com.example.blog.application.repository.CommentsReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentsReposiory commentsReposiory;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public CommentDto createComment(CommentDto dto) {
        Comments comment = new Comments();
        comment.setContent(dto.getContent());
        comment.setCreated_at(LocalDateTime.now());

        Users user = usersRepository.findById(dto.getUser_id()).orElse(null);
        Blogs blog = blogRepository.findById(dto.getBlog_id()).orElse(null);

        comment.setUsers(user);
        comment.setBlogs(blog);

        Comments saved = commentsReposiory.save(comment);
        return mapToDto(saved);
    }

    @Override
    public List<CommentDto> getAllComments() {
        return commentsReposiory.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long id) {
        return commentsReposiory.findById(id)
                .map(this::mapToDto)
                .orElse(null);
    }

    @Override
    public CommentDto updateComment(Long id, CommentDto dto) {
        Optional<Comments> optional = commentsReposiory.findById(id);
        if (optional.isPresent()) {
            Comments comment = optional.get();
            comment.setContent(dto.getContent());
            Comments updated = commentsReposiory.save(comment);
            return mapToDto(updated);
        }
        return null;
    }

    @Override
    public boolean deleteComment(Long id) {
        if (commentsReposiory.existsById(id)) {
            commentsReposiory.deleteById(id);
            return true;
        }
        return false;
    }

    private CommentDto mapToDto(Comments comment) {
        CommentDto dto = new CommentDto();
        dto.setComment_id(comment.getComment_id());
        dto.setContent(comment.getContent());
        dto.setCreated_at(comment.getCreated_at());

        if (comment.getUsers() != null) {
            dto.setUser_id(comment.getUsers().getUser_id());
            dto.setUsername(comment.getUsers().getUsername());
            dto.setEmail(comment.getUsers().getEmail());
        }

        if (comment.getBlogs() != null) {
            dto.setBlog_id(comment.getBlogs().getBlog_id());
            dto.setTitle(comment.getBlogs().getTitle());
            dto.setAuthor(comment.getBlogs().getAuthor());
        }

        return dto;
    }
}

