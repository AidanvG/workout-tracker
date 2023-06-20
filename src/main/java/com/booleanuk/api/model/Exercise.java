package com.booleanuk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sets")
    private Integer sets;

    @Column(name = "reps")
    private Integer reps;

    @Column(name = "current_weight")
    private Integer currentWeight;

    @Column(name = "starting_weight")
    private Integer startingWeight;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    @JsonIgnore
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    @JsonIgnore
    private Program program;

    public Exercise() {
        super();
    }

    public Exercise(String name, Integer sets, Integer reps, Integer currentWeight, Integer startingWeight, Workout workout, Program program) {
        this.setName(name);
        this.setSets(sets);
        this.setReps(reps);
        this.setCurrentWeight(currentWeight);
        this.setStartingWeight(startingWeight);
        this.setWorkout(workout);
        this.setProgram(program);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Integer currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Integer getStartingWeight() {
        return startingWeight;
    }

    public void setStartingWeight(Integer startingWeight) {
        this.startingWeight = startingWeight;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
