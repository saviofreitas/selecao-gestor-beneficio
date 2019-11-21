package br.gov.seplag.app.gestor.service;

import br.gov.seplag.app.gestor.domain.Beneficio;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Beneficio}.
 */
public interface BeneficioService {

    /**
     * Save a beneficio.
     *
     * @param beneficio the entity to save.
     * @return the persisted entity.
     */
    Beneficio save(Beneficio beneficio);

    /**
     * Get all the beneficios.
     *
     * @return the list of entities.
     */
    List<Beneficio> findAll();


    /**
     * Get the "id" beneficio.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Beneficio> findOne(Long id);

    /**
     * Delete the "id" beneficio.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
