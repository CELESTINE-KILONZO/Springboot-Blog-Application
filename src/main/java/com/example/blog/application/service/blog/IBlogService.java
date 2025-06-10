//package com.example.blog.application.service.blog;
//
//public interface IBlogService {
//}
package com.example.blog.application.service;

import com.example.blog.application.dto.BlogUserCatDto;
import com.example.blog.application.model.Blogs;
import com.example.blog.application.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogService {

    private final BlogRepository blogRepository;

    public List<BlogUserCatDto> getAllBlogs() {
        List<Blogs> blogs = blogRepository.findAll();
        return blogs.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public BlogUserCatDto getBlogById(Long id) {
        Optional<Blogs> blog = blogRepository.findById(id);
        return blog.map(this::mapToDto).orElse(null);
    }

    public BlogUserCatDto saveBlog(Blogs blog) {
        Blogs savedBlog = blogRepository.save(blog);
        return mapToDto(savedBlog);
    }

    public List<BlogUserCatDto> getBlogsByTitle(String title) {
        List<Blogs> blogs = blogRepository.findByTitle(title);
        return blogs.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<BlogUserCatDto> getBlogsByAuthor(String author) {
        List<Blogs> blogs = blogRepository.findByAuthor(author);
        return blogs.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<BlogUserCatDto> getBlogsByCategory(String categoryName) {
        List<Blogs> blogs = blogRepository.findAll()
                .stream()
                .filter(b -> b.getCategories() != null && categoryName.equalsIgnoreCase(b.getCategories().getName()))
                .collect(Collectors.toList());
        return blogs.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // Helper method to convert entity to DTO
    private BlogUserCatDto mapToDto(Blogs blog) {
        BlogUserCatDto dto = new BlogUserCatDto();
        dto.setBlog_id(blog.getBlog_id());
        dto.setTitle(blog.getTitle());
        dto.setDescription(blog.getDescription());
        dto.setContent(blog.getContent());
        dto.setCreated(blog.getCreated());
        dto.setUpdated(blog.getUpdated());

        if (blog.getUsers() != null) {
            dto.setUser_id(blog.getUsers().getUser_id());
            dto.setUsername(blog.getUsers().getUsername());
            dto.setEmail(blog.getUsers().getEmail());
        }

        if (blog.getCategories() != null) {
            dto.setCategory_id(blog.getCategories().getCategory_id());
            dto.setCategory_name(blog.getCategories().getName());
        }

        return dto;
    }
}

