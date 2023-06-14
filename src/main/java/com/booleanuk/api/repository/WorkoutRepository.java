package com.booleanuk.api.repository;

import com.booleanuk.api.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
    List<Workout> getWorkoutsByProgramId(int program_id);
}
