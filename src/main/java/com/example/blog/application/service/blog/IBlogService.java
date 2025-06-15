package com.example.blog.application.service.blog;

import com.example.blog.application.dto.BlogUserCatDto;
import com.example.blog.application.model.Blogs;

import java.util.List;

public interface IBlogService {
    BlogUserCatDto CreatBlog(BlogUserCatDto blogUserCatDto);
    List<BlogUserCatDto> getAllBlogs();
    BlogUserCatDto getBlogById(Long id);
    BlogUserCatDto saveBlog(Blogs blog);
    List<BlogUserCatDto> getBlogsByTitle(String title);
    List<BlogUserCatDto> getBlogsByAuthor(String author);
    List<BlogUserCatDto> getBlogsByCategory(String categoryName);

}
