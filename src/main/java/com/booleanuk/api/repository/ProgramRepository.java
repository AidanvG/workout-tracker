package com.booleanuk.api.repository;

import com.booleanuk.api.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Integer> {
}
