package com.booleanuk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "program")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "split")
    private Integer split;

    @OneToMany(mappedBy = "program")
    @JsonIgnore
    private List<Workout> workouts;

    public Program() {
        super();
    }

    public Program(String name, Integer split) {
        super();
        this.setName(name);
        this.setSplit(split);
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

    public Integer getSplit() {
        return split;
    }

    public void setSplit(Integer split) {
        this.split = split;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }
}
