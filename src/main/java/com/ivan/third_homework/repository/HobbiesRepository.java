package com.ivan.third_homework.repository;

import com.ivan.third_homework.entity.Hobbies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbiesRepository extends JpaRepository<Hobbies, Long> {
}
