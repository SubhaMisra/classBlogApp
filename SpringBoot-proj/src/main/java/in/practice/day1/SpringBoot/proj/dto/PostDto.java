package in.practice.day1.SpringBoot.proj.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
    private long id;
    @Size(max = 2,message = "Size can't be more than 2 characters")
    private String title;
    private String description;
    private String content;
}
