package fr.eni.masia.prompt.models.dto;


import fr.eni.masia.category.models.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromptWithCategoryDTO {
    private Long id;
    private String title;
    private String content;
    private int score;
    private String createdAt;
    private CategoryDTO category;
    private String userVote;

}

