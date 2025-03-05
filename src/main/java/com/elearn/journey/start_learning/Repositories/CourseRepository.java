package com.elearn.journey.start_learning.Repositories;

import com.elearn.journey.start_learning.Entities.Course;
import com.elearn.journey.start_learning.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByTitle(String title);
}
