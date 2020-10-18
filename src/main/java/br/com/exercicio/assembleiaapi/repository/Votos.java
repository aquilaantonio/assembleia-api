package br.com.exercicio.assembleiaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.exercicio.assembleiaapi.model.Voto;

public interface Votos extends JpaRepository<Voto, Long> {

}
