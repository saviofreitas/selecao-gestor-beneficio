package br.gov.seplag.app.gestor.service.impl;

import br.gov.seplag.app.gestor.service.MovimentacaoBeneficioService;
import br.gov.seplag.app.gestor.domain.MovimentacaoBeneficio;
import br.gov.seplag.app.gestor.repository.MovimentacaoBeneficioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MovimentacaoBeneficio}.
 */
@Service
@Transactional
public class MovimentacaoBeneficioServiceImpl implements MovimentacaoBeneficioService {

    private final Logger log = LoggerFactory.getLogger(MovimentacaoBeneficioServiceImpl.class);

    private final MovimentacaoBeneficioRepository movimentacaoBeneficioRepository;

    public MovimentacaoBeneficioServiceImpl(MovimentacaoBeneficioRepository movimentacaoBeneficioRepository) {
        this.movimentacaoBeneficioRepository = movimentacaoBeneficioRepository;
    }

    /**
     * Save a movimentacaoBeneficio.
     *
     * @param movimentacaoBeneficio the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MovimentacaoBeneficio save(MovimentacaoBeneficio movimentacaoBeneficio) {
        log.debug("Request to save MovimentacaoBeneficio : {}", movimentacaoBeneficio);
        return movimentacaoBeneficioRepository.save(movimentacaoBeneficio);
    }

    /**
     * Get all the movimentacaoBeneficios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MovimentacaoBeneficio> findAll(Pageable pageable) {
        log.debug("Request to get all MovimentacaoBeneficios");
        return movimentacaoBeneficioRepository.findAll(pageable);
    }


    /**
     * Get one movimentacaoBeneficio by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MovimentacaoBeneficio> findOne(Long id) {
        log.debug("Request to get MovimentacaoBeneficio : {}", id);
        return movimentacaoBeneficioRepository.findById(id);
    }

    /**
     * Delete the movimentacaoBeneficio by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MovimentacaoBeneficio : {}", id);
        movimentacaoBeneficioRepository.deleteById(id);
    }
}
