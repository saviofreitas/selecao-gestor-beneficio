package br.gov.seplag.app.gestor.service;

import br.gov.seplag.app.gestor.domain.ConteudoAnexo;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ConteudoAnexo}.
 */
public interface ConteudoAnexoService {

    /**
     * Save a conteudoAnexo.
     *
     * @param conteudoAnexo the entity to save.
     * @return the persisted entity.
     */
    ConteudoAnexo save(ConteudoAnexo conteudoAnexo);

    /**
     * Get all the conteudoAnexos.
     *
     * @return the list of entities.
     */
    List<ConteudoAnexo> findAll();
    /**
     * Get all the ConteudoAnexoDTO where Anexo is {@code null}.
     *
     * @return the list of entities.
     */
    List<ConteudoAnexo> findAllWhereAnexoIsNull();


    /**
     * Get the "id" conteudoAnexo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ConteudoAnexo> findOne(Long id);

    /**
     * Delete the "id" conteudoAnexo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
