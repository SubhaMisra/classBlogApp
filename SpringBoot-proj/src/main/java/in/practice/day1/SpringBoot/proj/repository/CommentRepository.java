package in.practice.day1.SpringBoot.proj.repository;


import in.practice.day1.SpringBoot.proj.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPostId(long postId);
}
