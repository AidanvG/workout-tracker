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
    private int sets;


    @Column(name = "reps")
    private int reps;

    @Column(name = "current_weight")
    private int currentWeight;

    @Column(name = "starting_weight")
    private int startingWeight;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    @JsonIgnore
    private Workout workout;

    public Exercise() {
        super();
    }

    public Exercise(String name, int sets, int reps, int currentWeight, int startingWeight, Workout workout) {
        this.setName(name);
        this.setSets(sets);
        this.setReps(reps);
        this.setCurrentWeight(currentWeight);
        this.setStartingWeight(startingWeight);
        this.setWorkout(workout);
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

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public int getStartingWeight() {
        return startingWeight;
    }

    public void setStartingWeight(int startingWeight) {
        this.startingWeight = startingWeight;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}
