package org.launchcode.GameReview.data;
import org.launchcode.GameReview.models.GameTitle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameTitleRepository extends CrudRepository<GameTitle, Integer> {
}
