package com.ap1.faculdade.faculdade.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ap1.faculdade.faculdade.exception.CursoException;
import com.ap1.faculdade.faculdade.model.Curso;
import com.ap1.faculdade.faculdade.repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public Curso create(Curso curso){
        if(curso.getDtCriacao() == null){
            curso.setDtCriacao(LocalDateTime.now());
        }

        return this.cursoRepository.save(curso);
    }

    public Optional<Curso> getById(long id){
        return this.cursoRepository.findById(id);
    }

    public List<Curso> getAll() {
        return this.cursoRepository.findAll();
    }

    public void saveOrUpdate(Curso item){
        this.cursoRepository.save(item);
    }

    public Curso update(long id, Curso newData) throws CursoException{
        Optional<Curso> oldCurso = this.cursoRepository.findById(id);
        if(oldCurso.isPresent() == false){
            throw new CursoException("Não encontrei o curso a ser atualizado");
        }

        Curso curso = oldCurso.get();
        curso.setNome(newData.getNome());
        curso.setDescricao(newData.getDescricao());
        curso.setDuracao(newData.getDuracao());
        curso.setDtCriacao(LocalDateTime.now());

        this.cursoRepository.save(curso);
        return curso;
    }

    public void delete(long id) throws CursoException {
        Optional<Curso> oldCurso = this.cursoRepository.findById(id);

        if(oldCurso.isPresent() == false){
            throw new CursoException("Não encontrei o curso a ser atualizado");
        }

        this.cursoRepository.delete(oldCurso.get());

        }
}
