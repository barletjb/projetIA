package fr.eni.masia.service;

import fr.eni.masia.dao.CategoryRepository;
import fr.eni.masia.dao.PromptRepository;
import fr.eni.masia.dao.VoteRepository;
import fr.eni.masia.entity.Category;
import fr.eni.masia.entity.Prompt;
import fr.eni.masia.entity.Vote;
import fr.eni.masia.model.category.CategoryDTO;
import fr.eni.masia.model.prompt.CreatePromptDTO;
import fr.eni.masia.model.prompt.PromptWithCategoryDTO;
import fr.eni.masia.model.prompt.UpdatePromptDTO;
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
        int delta = voteType == Vote.VoteType.UP ? 1 : -1;

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