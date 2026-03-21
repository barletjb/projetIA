package fr.eni.masia.prompt.service;

import fr.eni.masia.category.models.dto.CategoryDTO;
import fr.eni.masia.category.models.entity.Category;
import fr.eni.masia.category.repository.CategoryRepository;
import fr.eni.masia.prompt.models.dto.CreatePromptDTO;
import fr.eni.masia.prompt.models.dto.PromptWithCategoryDTO;
import fr.eni.masia.prompt.models.dto.UpdatePromptDTO;
import fr.eni.masia.prompt.models.entity.Prompt;
import fr.eni.masia.prompt.repository.PromptRepository;
import fr.eni.masia.vote.models.entity.Vote;
import fr.eni.masia.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PromptService {

    private final PromptRepository promptRepository;
    private final VoteRepository voteRepository;
    private final CategoryRepository categoryRepository;

    // ----------------------------------------------------------------
    // Lecture
    // ----------------------------------------------------------------

    @Transactional(readOnly = true)
    public List<PromptWithCategoryDTO> findAllSortedByScore(Long currentUserId) {
        return promptRepository.findAllSortedByScore()
                .stream()
                .map(prompt -> {
                    Vote vote = currentUserId != null
                            ? voteRepository.findByPromptIdAndUserId(prompt.getId(), currentUserId).orElse(null)
                            : null;
                    return toDto(prompt, vote);
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public PromptWithCategoryDTO findById(Long id, Long currentUserId) {
        Prompt prompt = getOrThrow(id);
        Vote vote = currentUserId != null
                ? voteRepository.findByPromptIdAndUserId(id, currentUserId).orElse(null)
                : null;
        return toDto(prompt, vote);
    }

    // ----------------------------------------------------------------
    // Écriture
    // ----------------------------------------------------------------

    @Transactional
    public PromptWithCategoryDTO create(CreatePromptDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        Prompt prompt = Prompt.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .category(category)
                .build();

        return toDto(promptRepository.save(prompt), null);
    }

    @Transactional
    public PromptWithCategoryDTO update(Long id, UpdatePromptDTO dto) {
        Prompt prompt = getOrThrow(id);

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        prompt.setTitle(dto.getTitle());
        prompt.setContent(dto.getContent());
        prompt.setCategory(category);

        return toDto(promptRepository.save(prompt), null);
    }

    @Transactional
    public void delete(Long id) {
        Prompt prompt = getOrThrow(id);
        promptRepository.delete(prompt);
    }

    // ----------------------------------------------------------------
    // Votes
    // ----------------------------------------------------------------

    @Transactional
    public PromptWithCategoryDTO applyVote(Long promptId, String type) {
        Prompt prompt = getOrThrow(promptId);

        Vote.VoteType voteType = Vote.VoteType.valueOf(type);
        int delta = voteType == Vote.VoteType.up ? 1 : -1;

        Optional<Vote> existingOpt = voteRepository.findByPromptId(promptId);

        if (existingOpt.isEmpty()) {
            // Premier vote
            prompt.setScore(prompt.getScore() + delta);
            voteRepository.save(Vote.builder()
                    .prompt(prompt)
                    .type(voteType)
                    .build());

        } else {
            Vote existing = existingOpt.get();
            if (existing.getType() == voteType) {
                // Même vote → annulation
                prompt.setScore(prompt.getScore() - delta);
                voteRepository.delete(existing);
            } else {
                // Vote opposé → changement
                prompt.setScore(prompt.getScore() + delta * 2);
                existing.setType(voteType);
                voteRepository.save(existing);
            }
        }

        Vote updatedVote = voteRepository.findByPromptId(promptId).orElse(null);
        return toDto(promptRepository.save(prompt), updatedVote);
    }

    // ----------------------------------------------------------------
    // Helpers privés
    // ----------------------------------------------------------------

    private Prompt getOrThrow(Long id) {
        return promptRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prompt not found"));
    }

    private PromptWithCategoryDTO toDto(Prompt prompt, Vote vote) {
        String userVote = vote != null ? vote.getType().name() : null;

        return PromptWithCategoryDTO.builder()
                .id(prompt.getId())
                .title(prompt.getTitle())
                .content(prompt.getContent())
                .score(prompt.getScore())
                .createdAt(prompt.getCreatedAt().toString())
                .category(new CategoryDTO(
                        prompt.getCategory().getId(),
                        prompt.getCategory().getName()
                ))
                .userVote(userVote)
                .build();
    }
}