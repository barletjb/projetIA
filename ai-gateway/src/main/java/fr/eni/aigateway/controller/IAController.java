package fr.eni.aigateway.controller;

import fr.eni.aigateway.model.IARequestDTO;
import fr.eni.aigateway.model.IAResponseDTO;
import fr.eni.aigateway.service.GroqService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ia")
public class IAController {

    private final GroqService groqService;

    @PostMapping("/groq")
    @ResponseStatus(HttpStatus.OK)
    public IAResponseDTO groqRequest(@Valid @RequestBody IARequestDTO requestDTO) {

        return groqService.sendPrompt(requestDTO);
    }

}
