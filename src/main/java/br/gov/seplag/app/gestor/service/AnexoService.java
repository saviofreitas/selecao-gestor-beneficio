package br.gov.seplag.app.gestor.service;

import br.gov.seplag.app.gestor.domain.Anexo;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Anexo}.
 */
public interface AnexoService {

    /**
     * Save a anexo.
     *
     * @param anexo the entity to save.
     * @return the persisted entity.
     */
    Anexo save(Anexo anexo);

    /**
     * Get all the anexos.
     *
     * @return the list of entities.
     */
    List<Anexo> findAll();


    /**
     * Get the "id" anexo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Anexo> findOne(Long id);

    /**
     * Delete the "id" anexo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
    Optional<Anexo> findOneById(Long id);
}
