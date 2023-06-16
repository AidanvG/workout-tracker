package com.booleanuk.api.repository;

import com.booleanuk.api.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    List<Exercise> getExercisesByProgramIdAndWorkoutId(int program_id, int workout_id);
}
