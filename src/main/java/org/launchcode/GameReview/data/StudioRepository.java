package org.launchcode.GameReview.data;

import org.launchcode.GameReview.models.Studio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends CrudRepository<Studio, Integer> {
}
