package fr.eni.masia.service;

import fr.eni.masia.dao.IAModelRepository;
import fr.eni.masia.entity.IAModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IAModelService {

    private final IAModelRepository iaModelRepository;

    public List<IAModel> getActiveModels() {
        return iaModelRepository.findByActiveTrue();
    }

    public IAModel getById(Long id) {
        return iaModelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modèle IA introuvable : " + id));
    }
}
