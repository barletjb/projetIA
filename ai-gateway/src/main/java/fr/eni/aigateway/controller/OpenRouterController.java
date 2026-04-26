package fr.eni.aigateway.controller;

import fr.eni.aigateway.model.IARequestDTO;
import fr.eni.aigateway.model.IAResponseDTO;
import fr.eni.aigateway.service.OpenRouterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ia")
public class OpenRouterController {

    private final OpenRouterService groqService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<IAResponseDTO> gptRequest(@Valid @RequestBody IARequestDTO requestDTO) {

        return groqService.sendPrompt(requestDTO);
    }

}
