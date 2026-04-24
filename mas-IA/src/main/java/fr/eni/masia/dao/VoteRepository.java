package fr.eni.masia.dao;

import fr.eni.masia.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByPromptIdAndUserId(Long promptId, Long userId);

    void deleteByPromptIdAndUserId(Long promptId, Long userId);

    Optional<Vote> findByPromptId(Long promptId);
}
