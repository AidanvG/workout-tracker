package com.booleanuk.api.controller;

import com.booleanuk.api.model.Program;
import com.booleanuk.api.model.Workout;
import com.booleanuk.api.repository.ProgramRepository;
import com.booleanuk.api.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/programs/{programId}/workouts")
public class WorkoutController {
    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private ProgramRepository programRepository;

    @GetMapping
    public List<Workout> getAllWorkouts(@PathVariable int programId) {
        return this.workoutRepository.getWorkoutsByProgramId(programId);
    }

    @GetMapping("/{workoutId}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable int workoutId) {
        Workout workoutToFind = this.workoutRepository.findById(workoutId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "This workout id could not be found."));
        return ResponseEntity.ok(workoutToFind);
    }

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@PathVariable int programId, @RequestBody Workout workout) {
        Program programToFind = this.programRepository.findById(programId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "This program id could not be found."));
        workout.setProgram(programToFind);
        return new ResponseEntity<Workout>(this.workoutRepository.save(workout), HttpStatus.CREATED);
    }

    @PutMapping("/{workoutId}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable int workoutId, @RequestBody Workout workout) {
        Workout workoutToUpdate = this.workoutRepository.findById(workoutId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Could not update this workout, workout id could not be found."));

        if (workout.getName() != null) workoutToUpdate.setName(workout.getName());
        if (workout.getTargetMuscles() != null) workoutToUpdate.setTargetMuscles(workout.getTargetMuscles());

        return new ResponseEntity<Workout>(this.workoutRepository.save(workoutToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity<Workout> deleteWorkout(@PathVariable int workoutId) {
        Workout workoutToDelete = this.workoutRepository.findById(workoutId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Could not delete this workout, workout id could not be found."));
        this.workoutRepository.delete(workoutToDelete);
        return ResponseEntity.ok(workoutToDelete);
    }
}
