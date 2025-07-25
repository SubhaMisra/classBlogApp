package in.practice.day1.SpringBoot.proj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long id;

    private String name;

    private String email;

    private String body;
}
