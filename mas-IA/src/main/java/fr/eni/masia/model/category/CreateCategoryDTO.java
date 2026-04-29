package fr.eni.masia.model.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCategoryDTO {

    @NotBlank
    @Size(max = 100)
    private String name;
}
