package com.booleanuk.api.controller;

import com.booleanuk.api.model.Program;
import com.booleanuk.api.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/programs")
public class ProgramController {

    @Autowired
    private ProgramRepository programRepository;

    @GetMapping
    public List<Program> getAllPrograms() {
        return this.programRepository.findAll();
    }

    @GetMapping("/{programId}")
    public ResponseEntity<Program> getProgramById(@PathVariable int programId) {
        Program programToFind = this.programRepository.findById(programId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "This program id could not be found."));
        return ResponseEntity.ok(programToFind);
    }

    @PostMapping
    public ResponseEntity<Program> createProgram(@RequestBody Program program) {
        return new ResponseEntity<Program>(this.programRepository.save(program), HttpStatus.CREATED);
    }

    @PutMapping("/{programId}")
    public ResponseEntity<Program> updateProgram(@PathVariable int programId, @RequestBody Program program) {
      Program programToUpdate = this.programRepository.findById(programId).orElseThrow(() -> new ResponseStatusException(
              HttpStatus.NOT_FOUND, "Could not update this program, program id could not be found."));

      if (program.getName() != null) programToUpdate.setName(program.getName());
      if (program.getSplit() != null) programToUpdate.setSplit(program.getSplit());

      return new ResponseEntity<Program>(this.programRepository.save(programToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{programId}")
    public ResponseEntity<Program> deleteProgram(@PathVariable int programId) {
        Program programToDelete = this.programRepository.findById(programId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Could not delete this program, program id could not be found."));
        this.programRepository.delete(programToDelete);
        return ResponseEntity.ok(programToDelete);
    }
}
