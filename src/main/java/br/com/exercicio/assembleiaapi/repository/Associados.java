/**
 * 
 */
package br.com.exercicio.assembleiaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.exercicio.assembleiaapi.model.Associado;

/**
 * @author aquila.pereira
 *
 */
public interface Associados extends JpaRepository<Associado, String> {

}
