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
        List<Exercise> exercises = this.exerciseRepository.getExercisesByProgramIdAndWorkoutId(programId, workoutId);
        if (exercises.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No exercises found for this workout.");
        return exercises;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable("programId") int programId,
                                                    @PathVariable("workoutId") int workoutId,
                                                    @PathVariable("id") int id) {
        Exercise exerciseToFind = this.exerciseRepository.getExerciseByProgramIdAndWorkoutIdAndId(programId, workoutId, id);
        if (exerciseToFind == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This exercise could not be found.");
        return ResponseEntity.ok(exerciseToFind);
    }

    @GetMapping("/{id}/progress")
    public ResponseEntity<String> getExerciseProgress(@PathVariable("programId") int programId,
                                                      @PathVariable("workoutId") int workoutId,
                                                      @PathVariable("id") int id) {
        Exercise exerciseToFind = this.exerciseRepository.getExerciseByProgramIdAndWorkoutIdAndId(programId, workoutId, id);
        if (exerciseToFind == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This exercise could not be found.");
        int progressNum = exerciseToFind.getCurrentWeight() - exerciseToFind.getStartingWeight();
        String progressStr = "You have increased your weight in this exercise by: " + Integer.toString(progressNum) + "kg!";
        return ResponseEntity.ok(progressStr);
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@PathVariable("programId") int programId,
                                                   @PathVariable("workoutId") int workoutId,
                                                   @RequestBody Exercise exercise) {

        Workout workoutToFind = this.workoutRepository.getWorkoutByProgramIdAndId(programId, workoutId);
        if(workoutToFind == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This workout could not be found.");

        exercise.setProgram(workoutToFind.getProgram());
        exercise.setWorkout(workoutToFind);

        return new ResponseEntity<Exercise>(this.exerciseRepository.save(exercise), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable int id, @RequestBody Exercise exercise) {
        Exercise exerciseToUpdate = this.exerciseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Could not update this exercise, exercise id could not be found."));

        if (exercise.getName() != null) exerciseToUpdate.setName(exercise.getName());
        if (exercise.getSets() != null) exerciseToUpdate.setSets(exercise.getSets());
        if (exercise.getReps() != null) exerciseToUpdate.setReps(exercise.getReps());
        if (exercise.getCurrentWeight() != null) exerciseToUpdate.setCurrentWeight(exercise.getCurrentWeight());
        if (exercise.getStartingWeight() != null) exerciseToUpdate.setStartingWeight(exercise.getStartingWeight());

        return new ResponseEntity<Exercise>(this.exerciseRepository.save(exerciseToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Exercise> deleteExercise(@PathVariable int id) {
        Exercise exerciseToDelete = this.exerciseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Could not delete this exercise, exercise id could not be found."));
        this.exerciseRepository.delete(exerciseToDelete);
        return ResponseEntity.ok(exerciseToDelete);
    }
}
