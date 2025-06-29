package com.example.blog.application.controller;

import com.example.blog.application.dto.CommentDto;
import com.example.blog.application.response.ApiResponse;
import com.example.blog.application.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.blog.application.utils.exceptions.ApiResponseUtils.REQUEST_ERROR_MESSAGE;
import static com.example.blog.application.utils.exceptions.ApiResponseUtils.REQUEST_SUCCESS_MESSAGE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createComment(@RequestBody CommentDto commentDto) {
        try {
            CommentDto createdComment = commentService.createComment(commentDto);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, createdComment));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllComments() {
        try {
            List<CommentDto> comments = commentService.getAllComments();
            if (comments.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "No comments found"));
            }
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, comments));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCommentById(@PathVariable Long id) {
        try {
            CommentDto comment = commentService.getCommentById(id);
            if (comment == null) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "Comment not found"));
            }
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, comment));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        try {
            CommentDto updated = commentService.updateComment(id, commentDto);
            if (updated == null) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "Comment not found for update"));
            }
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long id) {
        try {
            boolean deleted = commentService.deleteComment(id);
            if (!deleted) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "Comment not found for deletion"));
            }
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "Comment deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }
}
