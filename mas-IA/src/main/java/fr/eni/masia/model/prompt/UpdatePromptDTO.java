package fr.eni.masia.model.prompt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePromptDTO {

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Long categoryId;
}
