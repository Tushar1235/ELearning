package com.elearn.journey.start_learning.Repositories;

import com.elearn.journey.start_learning.Entities.Category;
import com.elearn.journey.start_learning.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByTitle(String title);
}
