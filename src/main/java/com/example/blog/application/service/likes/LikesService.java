package com.example.blog.application.service.likes;

import com.example.blog.application.dto.LikesDto;
import com.example.blog.application.model.Blogs;
import com.example.blog.application.model.Likes;
import com.example.blog.application.model.Users;
import com.example.blog.application.repository.BlogRepository;
import com.example.blog.application.repository.LikesRepository;
import com.example.blog.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikesService implements ILikesService {

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public LikesDto createLike(LikesDto likeDto) {
        Likes like = new Likes();

        Optional<Users> userOpt = userRepository.findById(likeDto.getUser_id());
        Optional<Blogs> blogOpt = blogRepository.findById(likeDto.getBlog_id());

        if (userOpt.isPresent() && blogOpt.isPresent()) {
            like.setUsers(userOpt.get());
            like.setBlogs(blogOpt.get());
        } else {
            throw new RuntimeException("User or Blog not found");
        }

        Likes saved = likesRepository.save(like);
        return mapToDto(saved);
    }

    @Override
    public List<LikesDto> getAllLikes() {
        return likesRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LikesDto getLikeById(Long id) {
        return likesRepository.findById(id)
                .map(this::mapToDto)
                .orElse(null);
    }

    @Override
    public boolean deleteLike(Long id) {
        if (likesRepository.existsById(id)) {
            likesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private LikesDto mapToDto(Likes like) {
        LikesDto dto = new LikesDto();
        dto.setLike_id(like.getLike_id());
        dto.setLiked_at(like.getLiked_at());

        if (like.getUsers() != null) {
            dto.setUser_id(like.getUsers().getUser_id());
            dto.setUsername(like.getUsers().getUsername());
            dto.setEmail(like.getUsers().getEmail());
        }

        if (like.getBlogs() != null) {
            dto.setBlog_id(like.getBlogs().getBlog_id());
            dto.setTitle(like.getBlogs().getTitle());
        }

        return dto;
    }
}

