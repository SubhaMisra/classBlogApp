package in.practice.day1.SpringBoot.proj.service.serviceImpl;

import in.practice.day1.SpringBoot.proj.Exception.ResourceNotFoundException;
import in.practice.day1.SpringBoot.proj.dto.PostDto;
import in.practice.day1.SpringBoot.proj.model.Post;
import in.practice.day1.SpringBoot.proj.repository.PostRepository;
import in.practice.day1.SpringBoot.proj.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {


    @Autowired
    private PostRepository postRepository;

    @Override
    public ResponseEntity<?> createPost(PostDto postDto) {

       try {
           Post post = mapToEntity(postDto);
           Post newPost = postRepository.save(post);

           System.out.println("=============================");
           PostDto postResponse = mapToDTO(newPost);
           return new ResponseEntity<>(postDto, HttpStatus.CREATED);
       } catch (Exception e) {
           throw new ResourceNotFoundException("Post can't be created");
       }

    }


    private PostDto mapToDTO(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }


    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable=PageRequest.of(pageNo,pageSize, sort);
        Page<Post> pagePost= postRepository.findAll(pageable);
        List<Post> posts = pagePost.getContent();

        //      List<PostDto> collect = posts.stream().map(PostServiceImpl::mapToDto).collect(Collectors.toList());
        List<PostDto> dtos = posts.stream().map(p->mapToDTO(p)).collect(Collectors.toList());

        return dtos;
    }


    @Override
    public PostDto getPostById(long id) {

        Optional<Post> byId = postRepository.findById(id);
        Post post = byId.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);

    }

    @Override
    public PostDto getPostByTitle(String title) {

        Optional<Post> byTitle = postRepository.findByTitle(title);
        Post post = byTitle.orElseThrow(() -> new ResourceNotFoundException("Post", "title", title));
        return mapToDTO(post);
    }



    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }



    @Override
    public void deletePostByTitle(String title) {

        Post post = postRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("Post", "title", title));

        postRepository.delete(post);
    }


}


