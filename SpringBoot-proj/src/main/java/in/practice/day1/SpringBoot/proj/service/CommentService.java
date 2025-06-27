package in.practice.day1.SpringBoot.proj.service;



import in.practice.day1.SpringBoot.proj.dto.CommentDto;

import java.util.List;

public interface CommentService {
    public CommentDto createComment(long postId, CommentDto commentDto);

    void deleteComment(long commentId);

    List<CommentDto> getCommentsByPostId(long postId);

    List<CommentDto> getAllComments();
}
