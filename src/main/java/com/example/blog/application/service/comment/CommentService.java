package com.example.blog.application.service.comment;

import com.example.blog.application.dto.CategoryDto;
import com.example.blog.application.dto.CommentDto;
import com.example.blog.application.model.Blogs;
import com.example.blog.application.model.Comments;
import com.example.blog.application.model.Users;
import com.example.blog.application.repository.BlogRepository;
import com.example.blog.application.repository.CommentsRepository;
import com.example.blog.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CommentService implements ICommentService {


    private final CommentsRepository commentsRepository;

    private final UserRepository userRepository;

    private final BlogRepository blogRepository;

    private final ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto dto) {
        Comments comment = new Comments();
        comment.setContent(dto.getContent());
        comment.setCreated_at(LocalDateTime.now());

        Users user = userRepository.findById(dto.getUser_id()).orElse(null);
        Blogs blog = blogRepository.findById(dto.getBlog_id()).orElse(null);

        comment.setUsers(user);
        comment.setBlogs(blog);

        Comments saved = commentsRepository.save(comment);
        return mapToDto(saved);
    }

    @Override
    public List<CommentDto> getAllComments() {
        return commentsRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long id) {
        return commentsRepository.findById(id)
                .map(this::mapToDto)
                .orElse(null);
    }

    @Override
    public CommentDto updateComment(Long id, CommentDto dto) {
        Optional<Comments> optional = commentsRepository.findById(id);
        if (optional.isPresent()) {
            Comments comment = optional.get();
            comment.setContent(dto.getContent());
            Comments updated = commentsRepository.save(comment);
            return mapToDto(updated);
        }
        return null;
    }

    @Override
    public boolean deleteComment(Long id) {
        if (commentsRepository.existsById(id)) {
            commentsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private CommentDto mapToDto(Comments comment) {

        return modelMapper.map(comment, CommentDto.class);
    }
}
