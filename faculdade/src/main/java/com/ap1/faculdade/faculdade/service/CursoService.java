package com.ap1.faculdade.faculdade.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ap1.faculdade.faculdade.model.Curso;
import com.ap1.faculdade.faculdade.repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository postRepository;

    public Curso create(Curso post){
        if(post.getDtCriacao() == null){
            post.setDtCriacao(LocalDateTime.now());
        }

        return this.postRepository.save(post);
    }

    public Optional<Curso> getById(long id){
        return this.postRepository.findById(id);
    }

    public List<Curso> getAll() {
        return this.postRepository.findAll();
    }

    public void saveOrUpdate(Curso item){
        this.postRepository.save(item);
    }

    public Curso update(long id, Curso newData) throws Exception{
        Optional<Curso> oldPost = this.postRepository.findById(id);
        if(oldPost.isPresent() == false){
            throw new Exception("Não encontrei o post a ser atualizado");
        }

        Curso post = oldPost.get();
        post.setDescricao(newData.getDescricao());
        post.setNome(newData.getNome());
        post.setDuracao(newData.getDuracao());

        this.postRepository.save(post);
        return post;
    }

    public void delete(long id) throws Exception {
        Optional<Curso> oldPost = this.postRepository.findById(id);
        if(oldPost.isPresent() == false){
            throw new Exception("Não encontrei o post a ser atualizado");
        }

        this.postRepository.delete(oldPost.get());

        }
}
