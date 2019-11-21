package br.gov.seplag.app.gestor.service.impl;

import br.gov.seplag.app.gestor.service.ServidorService;
import br.gov.seplag.app.gestor.domain.Servidor;
import br.gov.seplag.app.gestor.repository.ServidorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Servidor}.
 */
@Service
@Transactional
public class ServidorServiceImpl implements ServidorService {

    private final Logger log = LoggerFactory.getLogger(ServidorServiceImpl.class);

    private final ServidorRepository servidorRepository;

    public ServidorServiceImpl(ServidorRepository servidorRepository) {
        this.servidorRepository = servidorRepository;
    }

    /**
     * Save a servidor.
     *
     * @param servidor the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Servidor save(Servidor servidor) {
        log.debug("Request to save Servidor : {}", servidor);
        return servidorRepository.save(servidor);
    }

    /**
     * Get all the servidors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Servidor> findAll(Pageable pageable) {
        log.debug("Request to get all Servidors");
        return servidorRepository.findAll(pageable);
    }


    /**
     * Get one servidor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Servidor> findOne(Long id) {
        log.debug("Request to get Servidor : {}", id);
        return servidorRepository.findById(id);
    }

    /**
     * Delete the servidor by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Servidor : {}", id);
        servidorRepository.deleteById(id);
    }
}
