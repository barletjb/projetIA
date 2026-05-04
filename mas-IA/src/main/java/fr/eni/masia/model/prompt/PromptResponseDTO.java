package fr.eni.masia.model.prompt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PromptResponseDTO {
    private String model;
    private String result;
}
