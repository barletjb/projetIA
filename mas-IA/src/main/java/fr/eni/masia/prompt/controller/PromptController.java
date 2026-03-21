package fr.eni.masia.prompt.controller;

import fr.eni.masia.prompt.models.dto.CreatePromptDTO;
import fr.eni.masia.prompt.models.dto.PromptWithCategoryDTO;
import fr.eni.masia.prompt.models.dto.UpdatePromptDTO;
import fr.eni.masia.prompt.service.PromptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prompts")
@RequiredArgsConstructor
public class PromptController {

    private final PromptService promptService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PromptWithCategoryDTO> findAll() {

        return promptService.findAllSortedByScore(null);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PromptWithCategoryDTO findOne(@PathVariable Long id) {

        return promptService.findById(id, null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PromptWithCategoryDTO create(@Valid @RequestBody CreatePromptDTO dto) {

        return promptService.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PromptWithCategoryDTO update(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePromptDTO dto
    ) {
        return promptService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        promptService.delete(id);
    }

    @PostMapping("/{id}/upvote")
    @ResponseStatus(HttpStatus.OK)
    public PromptWithCategoryDTO upvote(@PathVariable Long id) {

        return promptService.applyVote(id, "up");
    }

    @PostMapping("/{id}/downvote")
    @ResponseStatus(HttpStatus.OK)
    public PromptWithCategoryDTO downvote(@PathVariable Long id) {

        return promptService.applyVote(id, "down");
    }
}