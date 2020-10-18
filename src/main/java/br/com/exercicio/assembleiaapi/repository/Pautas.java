package br.com.exercicio.assembleiaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.exercicio.assembleiaapi.model.Pauta;

public interface Pautas extends JpaRepository<Pauta, Long> {

}
