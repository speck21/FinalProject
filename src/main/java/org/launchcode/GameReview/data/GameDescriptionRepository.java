package org.launchcode.GameReview.data;

import org.launchcode.GameReview.models.GameDescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDescriptionRepository extends CrudRepository<GameDescription, Integer> {
}
