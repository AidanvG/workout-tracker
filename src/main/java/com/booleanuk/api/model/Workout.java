package com.booleanuk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "target_muscles")
    private String targetMuscles;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    @JsonIgnore
    private Program program;

    @OneToMany(mappedBy = "workout")
    @JsonIgnore
    private List<Exercise> exercises;

    public Workout() {
        super();
    }

    public Workout(String name, String targetMuscles, Program program) {
        super();
        this.setName(name);
        this.setTargetMuscles(targetMuscles);
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

    public String getTargetMuscles() {
        return targetMuscles;
    }

    public void setTargetMuscles(String targetMuscles) {
        this.targetMuscles = targetMuscles;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
