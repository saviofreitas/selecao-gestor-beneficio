package br.gov.seplag.app.gestor.service.impl;

import br.gov.seplag.app.gestor.service.BeneficioService;
import br.gov.seplag.app.gestor.domain.Beneficio;
import br.gov.seplag.app.gestor.repository.BeneficioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Beneficio}.
 */
@Service
@Transactional
public class BeneficioServiceImpl implements BeneficioService {

    private final Logger log = LoggerFactory.getLogger(BeneficioServiceImpl.class);

    private final BeneficioRepository beneficioRepository;

    public BeneficioServiceImpl(BeneficioRepository beneficioRepository) {
        this.beneficioRepository = beneficioRepository;
    }

    /**
     * Save a beneficio.
     *
     * @param beneficio the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Beneficio save(Beneficio beneficio) {
        log.debug("Request to save Beneficio : {}", beneficio);
        return beneficioRepository.save(beneficio);
    }

    /**
     * Get all the beneficios.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Beneficio> findAll() {
        log.debug("Request to get all Beneficios");
        return beneficioRepository.findAll();
    }


    /**
     * Get one beneficio by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Beneficio> findOne(Long id) {
        log.debug("Request to get Beneficio : {}", id);
        return beneficioRepository.findById(id);
    }

    /**
     * Delete the beneficio by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Beneficio : {}", id);
        beneficioRepository.deleteById(id);
    }
}
