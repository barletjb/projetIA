package fr.eni.masia.dao;

import fr.eni.masia.entity.IAModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAModelRepository extends JpaRepository<IAModel,Long> {

    List<IAModel> findByActiveTrue();
}
