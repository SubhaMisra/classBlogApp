package in.practice.day1.SpringBoot.proj.repository;

import in.practice.day1.SpringBoot.proj.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    Optional<Post> findByTitle(String title);
}
