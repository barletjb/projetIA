package fr.eni.aigateway.service;

import fr.eni.aigateway.model.GroqRequestDTO;
import fr.eni.aigateway.model.GroqResponseDTO;
import fr.eni.aigateway.model.IARequestDTO;
import fr.eni.aigateway.model.IAResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GroqService {

    private final RestClient groqRestClient;

    public IAResponseDTO sendPrompt(IARequestDTO request) {

        GroqRequestDTO groqRequest = new GroqRequestDTO(
                "llama-3.3-70b-versatile",
                List.of(new GroqRequestDTO.Message("user", request.getPrompt()))
        );

        GroqResponseDTO groqResponse = groqRestClient
                .post()
                .uri("/chat/completions")
                .body(groqRequest)
                .retrieve()
                .body(GroqResponseDTO.class);

        String result = groqResponse.getChoices().get(0).getMessage().getContent();

        return new IAResponseDTO(result);
    }
}
