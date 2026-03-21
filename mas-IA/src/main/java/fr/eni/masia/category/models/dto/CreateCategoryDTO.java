package fr.eni.masia.category.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCategoryDTO {

    @NotBlank
    private String name;
}
