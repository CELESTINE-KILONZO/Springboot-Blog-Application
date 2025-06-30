package com.example.blog.application.service.blog;

import com.example.blog.application.dto.BlogDto;
import com.example.blog.application.model.Blogs;
import com.example.blog.application.repository.BlogRepository;
import com.example.blog.application.repository.CategoriesRepository;
import com.example.blog.application.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        return convertToDto(blogs);
    }

    @Override
    public List<Blogs> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public BlogDto getBlogById(Long id) {
        Optional<Blogs> blog = blogRepository.findById(id);
        return blog.map(this::convertToDto).orElse(null);
    }

    @Override
    public BlogDto saveBlog(Blogs blog) {
        Blogs savedBlog = blogRepository.save(blog);
        return convertToDto(savedBlog);
    }

    @Override
    public List<BlogDto> getBlogsByTitle(String title) {
        List<Blogs> blogs = blogRepository.findByTitle(title);
        return blogs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<BlogDto> getBlogsByAuthor(String author) {
        List<Blogs> blogs = blogRepository.findByAuthor(author);
        return blogs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // In your BlogService.java, update the getBlogsByCategory method:

    @Override
    public List<BlogDto> getBlogsByCategory(String categoryName) {
        // If using the first solution:
        // List<Blogs> blogs = blogRepository.findByCategoriesCategoryName(categoryName);

        // If using the @Query solution:
        List<Blogs> blogs = blogRepository.findBlogsByCategoryName(categoryName);

        return blogs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public BlogDto convertToDto(Blogs blogs) {
        return modelMapper.map(blogs,BlogDto.class);
    }

    public Blogs mapToEntity(BlogDto dto) {
        return modelMapper.map(dto,Blogs.class);
    }

    public List<BlogDto> getConvertedBlogs(List<Blogs> blogs) {
        return blogs.stream().map(this::convertToDto).toList();
    }
}