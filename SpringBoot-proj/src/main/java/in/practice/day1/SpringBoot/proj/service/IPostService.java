package in.practice.day1.SpringBoot.proj.service;

import in.practice.day1.SpringBoot.proj.dto.PostDto;
import in.practice.day1.SpringBoot.proj.model.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPostService {

        ResponseEntity<?> createPost(PostDto postDto);

    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);

    PostDto getPostByTitle(String title);

    void deletePostByTitle(String title);
}
