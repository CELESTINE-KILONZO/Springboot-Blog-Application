package com.example.blog.application.controller;

import com.example.blog.application.dto.LikesDto;
import com.example.blog.application.response.ApiResponse;
import com.example.blog.application.service.likes.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.blog.application.utils.exceptions.ApiResponseUtils.REQUEST_SUCCESS_MESSAGE;
import static com.example.blog.application.utils.exceptions.ApiResponseUtils.REQUEST_ERROR_MESSAGE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createLike(@RequestBody LikesDto likesDto) {
        try {
            LikesDto created = likesService.createLike(likesDto);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, created));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllLikes() {
        try {
            List<LikesDto> likes = likesService.getAllLikes();
            if (likes.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "No likes found"));
            }
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, likes));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getLikeById(@PathVariable Long id) {
        try {
            LikesDto like = likesService.getLikeById(id);
            if (like == null) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "Like not found"));
            }
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, like));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteLike(@PathVariable Long id) {
        try {
            boolean deleted = likesService.deleteLike(id);
            if (!deleted) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "Like not found for deletion"));
            }
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "Like deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }
}

