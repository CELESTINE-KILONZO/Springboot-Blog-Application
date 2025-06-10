package com.example.blog.application.service.blog;

import com.example.blog.application.dto.BlogUserCatDto;
import com.example.blog.application.model.Blogs;
import com.example.blog.application.model.Users;
import com.example.blog.application.repository.BlogRepository;
import com.example.blog.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository ;
    private final UserRepository userRepository;

    public Blogs createBlogs(BlogUserCatDto  blogUserCatDto) {

        Users users = userRepository.findByEmail(blogUserCatDto.getEmail());
    }


}
