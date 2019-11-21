package br.gov.seplag.app.gestor.service;

import br.gov.seplag.app.gestor.domain.MovimentacaoBeneficio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link MovimentacaoBeneficio}.
 */
public interface MovimentacaoBeneficioService {

    /**
     * Save a movimentacaoBeneficio.
     *
     * @param movimentacaoBeneficio the entity to save.
     * @return the persisted entity.
     */
    MovimentacaoBeneficio save(MovimentacaoBeneficio movimentacaoBeneficio);

    /**
     * Get all the movimentacaoBeneficios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MovimentacaoBeneficio> findAll(Pageable pageable);


    /**
     * Get the "id" movimentacaoBeneficio.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MovimentacaoBeneficio> findOne(Long id);

    /**
     * Delete the "id" movimentacaoBeneficio.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
