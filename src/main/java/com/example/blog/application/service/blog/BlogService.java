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
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

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
            return modelMapper.map(blog,BlogDto.class)  ;
    }

    private Blogs mapToEntity(BlogDto dto) {
      return modelMapper.map(dto,Blogs.class)  ;  }

}
