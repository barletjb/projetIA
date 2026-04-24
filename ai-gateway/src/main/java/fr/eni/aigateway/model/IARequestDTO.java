package fr.eni.aigateway.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IARequestDTO {

    @NotBlank(message = "Le prompt ne peut pas être vide")
    private String prompt;
}
