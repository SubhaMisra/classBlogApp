package in.practice.day1.SpringBoot.proj.controller;

import in.practice.day1.SpringBoot.proj.dto.PostDto;
import in.practice.day1.SpringBoot.proj.service.IPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private IPostService iPostService;

    // CRUD -> Create retrieve Update Delete


    @PostMapping(value = "/createPost")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto){

         System.out.println("this is a creating post.");
          System.out.println("this is a creating post.");
          return iPostService.createPost(postDto);

//        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // get all posts rest api
    //http://localhost:8080/list?pageNo=0&pageSize=5&sortBy=title&sortDir=Desc4
    @GetMapping(value = "/list")
    public List<PostDto> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false)  int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ){
        System.out.println("this is a used to getting all the post in a list.");
        System.out.println("this is a used to getting all the post in a list.");
        return iPostService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    // get post by id
    @GetMapping("/get/findId/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(iPostService.getPostById(id));
    }

    @GetMapping("/get/findTitle/{title}")
    public ResponseEntity<PostDto> getPostByTitle(@PathVariable(name = "title") String title) {
       PostDto dto = iPostService.getPostByTitle(title);

       return ResponseEntity.ok(dto);
    }

    // update post by id rest api
    @PutMapping("/update/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id){

        PostDto postResponse = iPostService.updatePost(postDto, id);
        System.out.println("Avinash");
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }


    // delete post rest api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){

        iPostService.deletePostById(id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/title/{id}")
    public ResponseEntity<String> deletePostByTitle(@PathVariable(name = "title") String title){

        iPostService.deletePostByTitle(title);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }
}








