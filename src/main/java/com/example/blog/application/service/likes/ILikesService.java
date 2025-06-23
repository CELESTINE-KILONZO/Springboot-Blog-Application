package com.example.blog.application.service.likes;

import com.example.blog.application.dto.LikesDto;

import java.util.List;

public interface ILikesService {
    LikesDto createLike(LikesDto likeDto);
    List<LikesDto> getAllLikes();
    LikesDto getLikeById(Long id);
    boolean deleteLike(Long id);
}
