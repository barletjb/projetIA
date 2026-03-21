package fr.eni.masia.prompt.repository;

import fr.eni.masia.prompt.models.entity.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromptRepository extends JpaRepository<Prompt, Long> {

    @Query("SELECT p FROM Prompt p JOIN FETCH p.category JOIN FETCH p.author ORDER BY p.score DESC")
    List<Prompt> findAllSortedByScore();
}