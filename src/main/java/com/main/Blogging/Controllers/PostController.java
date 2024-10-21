package com.main.Blogging.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.main.Blogging.Config.AppConstants;
import com.main.Blogging.Payloads.ApiResponse;
import com.main.Blogging.Payloads.PostDto;
import com.main.Blogging.Payloads.PostResponse;
import com.main.Blogging.Services.FileService;
import com.main.Blogging.Services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("user/{userid}/category/{categoryid}")
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto,
                                           @PathVariable Integer userid,
                                           @PathVariable Integer categoryid) {
        PostDto createdPost = postService.createPost(postDto, userid, categoryid);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("category/{categoryid}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryid) {
        List<PostDto> post = this.postService.getPostByCategory(categoryid);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("user/{userid}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable int userid) {
        List<PostDto> post = this.postService.getPostByUser(userid);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable int id) {
        postService.deletePost(id);
        String message = String.format("Post with id %s deleted successfully", id);
        return new ResponseEntity<>(new ApiResponse(message, true), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable int id) {
        PostDto post = this.postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable int id) {
        PostDto post = this.postService.updatePost(postDto, id);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword) {
        List<PostDto> postDtos = this.postService.searchPosts(keyword);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    // Post Image Upload
    @PostMapping("image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable Integer postId) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    // Method to serve files
    @GetMapping(value = "image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response) throws IOException {

        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        org.springframework.util.StreamUtils.copy(resource, response.getOutputStream());
    }
}
