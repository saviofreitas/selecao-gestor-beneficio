package br.gov.seplag.app.gestor.service.impl;

import br.gov.seplag.app.gestor.service.AnexoService;
import br.gov.seplag.app.gestor.domain.Anexo;
import br.gov.seplag.app.gestor.repository.AnexoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Anexo}.
 */
@Service
@Transactional
public class AnexoServiceImpl implements AnexoService {

    private final Logger log = LoggerFactory.getLogger(AnexoServiceImpl.class);

    private final AnexoRepository anexoRepository;

    public AnexoServiceImpl(AnexoRepository anexoRepository) {
        this.anexoRepository = anexoRepository;
    }

    /**
     * Save a anexo.
     *
     * @param anexo the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Anexo save(Anexo anexo) {
        log.debug("Request to save Anexo : {}", anexo);
        return anexoRepository.save(anexo);
    }

    /**
     * Get all the anexos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Anexo> findAll() {
        log.debug("Request to get all Anexos");
        return anexoRepository.findAll();
    }


    /**
     * Get one anexo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Anexo> findOne(Long id) {
        log.debug("Request to get Anexo : {}", id);
        return anexoRepository.findById(id);
    }

    /**
     * Delete the anexo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Anexo : {}", id);
        anexoRepository.deleteById(id);
    }
}
