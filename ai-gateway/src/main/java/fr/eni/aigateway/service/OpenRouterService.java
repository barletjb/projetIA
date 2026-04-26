package fr.eni.aigateway.service;

import fr.eni.aigateway.model.IARequestDTO;
import fr.eni.aigateway.model.IAResponseDTO;
import fr.eni.aigateway.model.RequestModelDTO;
import fr.eni.aigateway.model.ResponseModelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OpenRouterService {

    private final RestClient openRouterRestClient;


    private static final String GPT = "openai/gpt-oss-120b:free";
    public static final String LING = "inclusionai/ling-2.6-1t:free";
    private static final String TENCENT = "tencent/hy3-preview:free";


    public List<IAResponseDTO> sendPrompt(IARequestDTO request) {

        List<IAResponseDTO> listResults = new ArrayList<>();

        for (String model : request.getModels()) {
            try {
                RequestModelDTO requestModel = new RequestModelDTO(
                        model,
                        List.of(new RequestModelDTO.Message("user", request.getPrompt()))
                );

                ResponseModelDTO response = openRouterRestClient
                        .post()
                        .uri("/chat/completions")
                        .body(requestModel)
                        .retrieve()
                        .body(ResponseModelDTO.class);

                String content = Optional.ofNullable(response)
                        .map(ResponseModelDTO::getChoices)
                        .filter(choices -> !choices.isEmpty())
                        .map(choices -> choices.get(0))
                        .map(ResponseModelDTO.Choice::getMessage)
                        .map(ResponseModelDTO.Message::getContent)
                        .orElse("Réponse vide ou invalide");

                listResults.add(new IAResponseDTO(model, content));

            } catch (HttpClientErrorException e) {
                listResults.add(new IAResponseDTO(model, "Indisponible : " + e.getStatusCode()));
            }
        }

        return listResults;
    }
}
