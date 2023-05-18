package org.launchcode.GameReview.data;

import org.launchcode.GameReview.models.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
