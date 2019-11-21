package br.gov.seplag.app.gestor.service;

import br.gov.seplag.app.gestor.domain.Servidor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Servidor}.
 */
public interface ServidorService {

    /**
     * Save a servidor.
     *
     * @param servidor the entity to save.
     * @return the persisted entity.
     */
    Servidor save(Servidor servidor);

    /**
     * Get all the servidors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Servidor> findAll(Pageable pageable);


    /**
     * Get the "id" servidor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Servidor> findOne(Long id);

    /**
     * Delete the "id" servidor.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
