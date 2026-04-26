package fr.eni.aigateway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IAResponseDTO {

    private String model;
    private String result;
}
