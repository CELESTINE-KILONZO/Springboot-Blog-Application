package com.example.blog.application.service.blog;

import com.example.blog.application.dto.BlogDto;
import com.example.blog.application.model.Blogs;
import com.example.blog.application.model.Categories;
import com.example.blog.application.model.Users;
import com.example.blog.application.repository.BlogRepository;
import com.example.blog.application.repository.CategoriesRepository;
import com.example.blog.application.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogService implements IBlogService{

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final CategoriesRepository categoriesRepository;

    @Override
    public BlogDto CreatBlog(BlogDto blogUserCatDto) {
        Blogs blogs = mapToEntity(blogUserCatDto);
        blogRepository.save(blogs);
        return mapToDto(blogs);
    }

    @Override
    public List<BlogDto> getAllBlogs() {
        List<Blogs> blogs = blogRepository.findAll();
        return blogs.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BlogDto getBlogById(Long id) {
        Optional<Blogs> blog = blogRepository.findById(id);
        return blog.map(this::mapToDto).orElse(null);
    }

    @Override
    public BlogDto saveBlog(Blogs blog) {
        Blogs savedBlog = blogRepository.save(blog);
        return mapToDto(savedBlog);
    }

    @Override
    public List<BlogDto> getBlogsByTitle(String title) {
        List<Blogs> blogs = blogRepository.findByTitle(title);
        return blogs.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<BlogDto> getBlogsByAuthor(String author) {
        List<Blogs> blogs = blogRepository.findByAuthor(author);
        return blogs.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<BlogDto> getBlogsByCategory(String categoryName) {
        List<Blogs> blogs = blogRepository.findAll()
                .stream()
                .filter(b -> b.getCategories() != null && categoryName.equalsIgnoreCase(b.getCategories().getCategory_name()))
                .collect(Collectors.toList());
        return blogs.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // Helper method to convert entity to DTO
    private BlogDto mapToDto(Blogs blog) {
        BlogDto dto = new BlogDto();
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
            dto.setCategory_name(blog.getCategories().getCategory_name());
        }

        return dto;

    }
    private Blogs mapToEntity(BlogDto dto) {
        Blogs blog = new Blogs();
        blog.setTitle(dto.getTitle());
        blog.setDescription(dto.getDescription());
        blog.setContent(dto.getContent());
        blog.setCreated(LocalDateTime.now());
        blog.setUpdated(LocalDateTime.now());

        Users users = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        blog.setUsers(users);

        Categories category = categoriesRepository.findById(dto.getCategory_id())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        blog.setCategories(category);

        return blog;
    }

}
