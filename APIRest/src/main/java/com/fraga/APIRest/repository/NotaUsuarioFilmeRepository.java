package com.fraga.APIRest.repository;

import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.data.model.NotaUsuarioFilme;
import com.fraga.APIRest.data.model.UsuarioFilmeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaUsuarioFilmeRepository extends JpaRepository<NotaUsuarioFilme, UsuarioFilmeId> {

    @Query("SELECT n.nota FROM NotaUsuarioFilme n WHERE n.filme =: filme")
    List<Integer> buscarNotasPorFilme(@Param("filme") Filme filme);
}
