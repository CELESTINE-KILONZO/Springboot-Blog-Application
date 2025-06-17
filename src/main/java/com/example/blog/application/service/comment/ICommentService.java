package com.example.blog.application.service.comment;

import com.example.blog.application.dto.CommentDto;

import java.util.List;

public interface ICommentService {
    CommentDto createComment(CommentDto commentDto);
    List<CommentDto> getAllComments();
    CommentDto getCommentById(Long id);
    CommentDto updateComment(Long id, CommentDto commentDto);
    boolean deleteComment(Long id);
}
