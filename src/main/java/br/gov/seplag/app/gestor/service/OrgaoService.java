package br.gov.seplag.app.gestor.service;

import br.gov.seplag.app.gestor.domain.Orgao;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Orgao}.
 */
public interface OrgaoService {

    /**
     * Save a orgao.
     *
     * @param orgao the entity to save.
     * @return the persisted entity.
     */
    Orgao save(Orgao orgao);

    /**
     * Get all the orgaos.
     *
     * @return the list of entities.
     */
    List<Orgao> findAll();


    /**
     * Get the "id" orgao.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Orgao> findOne(Long id);

    /**
     * Delete the "id" orgao.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
