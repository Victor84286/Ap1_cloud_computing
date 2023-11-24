package com.ap1.faculdade.faculdade.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ap1.faculdade.faculdade.exception.AlunoException;
import com.ap1.faculdade.faculdade.model.Aluno;
import com.ap1.faculdade.faculdade.service.AlunoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")
@CrossOrigin
class AlunoController {

    @Autowired
    AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll() {
        try {
            List<Aluno> items = new ArrayList<Aluno>();

            alunoService.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Aluno> getById(@PathVariable("id") long id) {
        Optional<Aluno> existingItemOptional = alunoService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{id}")
    public ResponseEntity<Aluno> create(@PathVariable("id") long idCurso, @Valid @RequestBody Aluno item) throws AlunoException{
        Aluno savedItem = alunoService.save(idCurso, item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Aluno> update(@PathVariable("id") long id, @Valid @RequestBody Aluno item) throws AlunoException {
        return new ResponseEntity<>(alunoService.update(id, item), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) throws AlunoException {
        alunoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}