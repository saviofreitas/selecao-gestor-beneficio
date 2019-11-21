package br.gov.seplag.app.gestor.service;

import br.gov.seplag.app.gestor.domain.CategoriaAnexo;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link CategoriaAnexo}.
 */
public interface CategoriaAnexoService {

    /**
     * Save a categoriaAnexo.
     *
     * @param categoriaAnexo the entity to save.
     * @return the persisted entity.
     */
    CategoriaAnexo save(CategoriaAnexo categoriaAnexo);

    /**
     * Get all the categoriaAnexos.
     *
     * @return the list of entities.
     */
    List<CategoriaAnexo> findAll();


    /**
     * Get the "id" categoriaAnexo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategoriaAnexo> findOne(Long id);

    /**
     * Delete the "id" categoriaAnexo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
