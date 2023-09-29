package com.ap1.faculdade.faculdade.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ap1.faculdade.faculdade.model.Aluno;
import com.ap1.faculdade.faculdade.model.Curso;
import com.ap1.faculdade.faculdade.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository commentRepository;

    @Autowired CursoService cursoService;

    public List<Aluno> findAll() {
        return this.commentRepository.findAll();
    }

    public Optional<Aluno> findById(long id) {
        return this.commentRepository.findById(id);
    }

    public Aluno update(long id, Aluno newData) throws Exception{
        Optional<Aluno> opComment = this.commentRepository.findById(id);

        if(opComment.isPresent() == false)
            throw new Exception("Comentario nao encontrado");

        Aluno comment = opComment.get();
        comment.setNome(newData.getNome());

        this.commentRepository.save(comment);
        return comment;

    }

    public Aluno save(long idCurso, Aluno item) throws Exception {
        Optional<Curso> opCurso = this.cursoService.getById(idCurso);

        if(opCurso.isPresent() == false)
            throw new Exception("Curso não encontrado");

        if(item.getDtMatricula() == null){
            item.setDtMatricula(LocalDateTime.now());
        }

        Curso curso = opCurso.get();
        item.setCurso(curso);

        this.commentRepository.save(item);

        return item;
    }

    public void delete(long id) throws Exception {
        Optional<Aluno> oldComment = this.commentRepository.findById(id);
        if(oldComment.isPresent() == false){
            throw new Exception("Não encontrei o Comment a ser deletado");
        }

        this.commentRepository.delete(oldComment.get());

        }

}
