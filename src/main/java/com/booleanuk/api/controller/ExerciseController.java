package com.booleanuk.api.controller;

import com.booleanuk.api.model.Exercise;
import com.booleanuk.api.model.Program;
import com.booleanuk.api.model.Workout;
import com.booleanuk.api.repository.ExerciseRepository;
import com.booleanuk.api.repository.ProgramRepository;
import com.booleanuk.api.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/programs/{programId}/workouts/{workoutId}/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private ProgramRepository programRepository;

    @GetMapping
    public List<Exercise> getAllExercises(@PathVariable("programId") int programId, @PathVariable("workoutId") int workoutId) {
        return this.exerciseRepository.getExercisesByProgramIdAndWorkoutId(programId, workoutId);
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable int exerciseId) {
        Exercise exerciseToFind = this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "This exercise id could not be found."));
        return ResponseEntity.ok(exerciseToFind);
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(
            @PathVariable("programId") int programId,
            @PathVariable("workoutId") int workoutId,
            @RequestBody Exercise exercise) {

        Program programToFind = this.programRepository.findById(programId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "This program id could not be found."));
        Workout workoutToFind = this.workoutRepository.findById(workoutId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "This workout id could not be found."));

        exercise.setWorkout(workoutToFind);

        return new ResponseEntity<Exercise>(this.exerciseRepository.save(exercise), HttpStatus.CREATED);
    }

    @PutMapping("/{exerciseId}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable int exerciseId, @RequestBody Exercise exercise) {
        Exercise exerciseToUpdate = this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Could not update this exercise, exercise id could not be found."));

        if (exercise.getName() != null) exerciseToUpdate.setName(exercise.getName());
        if (exercise.getSets() != null) exerciseToUpdate.setSets(exercise.getSets());
        if (exercise.getReps() != null) exerciseToUpdate.setReps(exercise.getReps());
        if (exercise.getCurrentWeight() != null) exerciseToUpdate.setCurrentWeight(exercise.getCurrentWeight());
        if (exercise.getStartingWeight() != null) exerciseToUpdate.setStartingWeight(exercise.getStartingWeight());

        return new ResponseEntity<Exercise>(this.exerciseRepository.save(exerciseToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<Exercise> deleteExercise(@PathVariable int exerciseId) {
        Exercise exerciseToDelete = this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Could not delete this exercise, exercise id could not be found."));
        this.exerciseRepository.delete(exerciseToDelete);
        return ResponseEntity.ok(exerciseToDelete);
    }
}
