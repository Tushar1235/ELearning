package com.elearn.journey.start_learning.Repositories;

import com.elearn.journey.start_learning.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
