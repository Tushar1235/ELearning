package com.elearn.journey.start_learning.Repositories;

import com.elearn.journey.start_learning.DTO.VideoDto;
import com.elearn.journey.start_learning.Entities.User;
import com.elearn.journey.start_learning.Entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    Optional<Video> findByTitle(String title);
}
