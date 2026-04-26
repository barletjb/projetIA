package fr.eni.masia.controller;

import fr.eni.masia.entity.IAModel;
import fr.eni.masia.service.IAModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/ia-models")
public class IAModelController {

    private final IAModelService iaModelService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<IAModel> getActiveModels() {
        return iaModelService.getActiveModels();
    }
}
