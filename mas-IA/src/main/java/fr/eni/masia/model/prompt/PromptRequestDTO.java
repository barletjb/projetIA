package fr.eni.masia.model.prompt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PromptRequestDTO {

    @NotBlank(message = "Le prompt ne peut pas être vide")
    private String prompt;

    @NotEmpty(message = "Sélectionne au moins une IA")
    private List<Long> modelIds;
}
