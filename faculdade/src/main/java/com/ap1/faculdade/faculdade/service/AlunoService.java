package com.ap1.faculdade.faculdade.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ap1.faculdade.faculdade.exception.AlunoException;
import com.ap1.faculdade.faculdade.model.Aluno;
import com.ap1.faculdade.faculdade.model.Curso;
import com.ap1.faculdade.faculdade.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired CursoService cursoService;

    public List<Aluno> findAll() {
        return this.alunoRepository.findAll();
    }

    public Optional<Aluno> findById(long id) {
        return this.alunoRepository.findById(id);
    }

    public Aluno update(long id, Aluno newData) throws AlunoException{
        Optional<Aluno> opAluno = this.alunoRepository.findById(id);

        if(opAluno.isPresent() == false)
            throw new AlunoException("aluno não encontrado");

        Aluno aluno = opAluno.get();
        aluno.setNome(newData.getNome());

        this.alunoRepository.save(aluno);
        return aluno;

    }

    public Aluno save(long idCurso, Aluno item) throws AlunoException {
        Optional<Curso> opCurso = this.cursoService.getById(idCurso);

        if(opCurso.isPresent() == false)
            throw new AlunoException("Curso não encontrado");

        if(item.getDtMatricula() == null){
            item.setDtMatricula(LocalDateTime.now());
        }

        Curso curso = opCurso.get();
        item.setCurso(curso);

        this.alunoRepository.save(item);

        return item;
    }

    public void delete(long id) throws AlunoException {
        Optional<Aluno> Aluno = this.alunoRepository.findById(id);
        if(Aluno.isPresent() == false){
            throw new AlunoException("Não encontrei o aluno a ser deletado");
        }

        this.alunoRepository.delete(Aluno.get());

        }

}
