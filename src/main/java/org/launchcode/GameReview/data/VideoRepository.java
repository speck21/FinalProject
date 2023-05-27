package org.launchcode.GameReview.data;

import org.launchcode.GameReview.models.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends CrudRepository<Video,Integer> {

}
