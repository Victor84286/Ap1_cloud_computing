package com.ap1.faculdade.faculdade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ap1.faculdade.faculdade.model.Curso;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> { }
