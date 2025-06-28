package com.example.blog.application.controller;

import com.example.blog.application.dto.BlogDto;
import com.example.blog.application.model.Blogs;
import com.example.blog.application.response.ApiResponse;
import com.example.blog.application.service.blog.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.blog.application.utils.exceptions.ApiResponseUtils.REQUEST_ERROR_MESSAGE;
import static com.example.blog.application.utils.exceptions.ApiResponseUtils.REQUEST_SUCCESS_MESSAGE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> CreatBlog(@RequestBody BlogDto blogUserCatDto) {
        try {
            BlogDto createBlog = blogService.CreatBlog(blogUserCatDto);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, createBlog));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }

    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllBlogs() {
        try {
            List<Blogs> blogs = blogService.getAllBlogs();
            if (blogs.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "Blogs not found"));
            }
            List<BlogDto> convertedBlogs = blogService.getConvertedBlogs(blogs);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, convertedBlogs));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));

        }

    }

    @GetMapping("/id")
    public ResponseEntity<ApiResponse> getBlogById() {
        try {
            List<Blogs> blogs = blogService.getBlogById(Long blog_Id);
            if (blogs.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "Blog not found"));
            }
            List<BlogDto> convertedBlogs = blogService.getConvertedBlogs(blogs);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, convertedBlogs));
        }
        catch (Exception e ){
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }


        }
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveBlog(@RequestBody BlogDto BlogUserCatDto){
    try{
       BlogDto saveBlog = BlogService.saveBlog(blogUserCatDto)
        return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, saveBlog));
    }
    catch(Exception e)
    {
        return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
    }

    }

    @GetMapping("/title")
public ResponseEntity<ApiResponse> getBlogsByTitle(){
    try{
        List<Blogs> blogs = blogService.getBlogByTitle();

    }
    }