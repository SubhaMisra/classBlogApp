package in.practice.day1.SpringBoot.proj.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
    private long id;
    @Size(max = 2,message = "Size can't be more than 2 characters")
    private String title;
    @Size(min = 3,message = "Size can't be less than 3 characters")
    private String description;
    @NotNull(message = "Content can't be null")
    private String content;
}
