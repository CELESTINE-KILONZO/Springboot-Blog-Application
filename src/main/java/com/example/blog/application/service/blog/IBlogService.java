package com.example.blog.application.service.blog;

import com.example.blog.application.dto.BlogDto;
import com.example.blog.application.model.Blogs;

import java.util.List;

public interface IBlogService {
    BlogDto CreatBlog(BlogDto blogUserCatDto);
    List<Blogs> getAllBlogs();
    BlogDto getBlogById(Long id);
    BlogDto saveBlog(Blogs blog);
    List<BlogDto> getBlogsByTitle(String title);
    List<BlogDto> getBlogsByAuthor(String author);
    List<BlogDto> getBlogsByCategory(String categoryName);

}
