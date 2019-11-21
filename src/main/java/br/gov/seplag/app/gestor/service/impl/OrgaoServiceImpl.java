package br.gov.seplag.app.gestor.service.impl;

import br.gov.seplag.app.gestor.service.OrgaoService;
import br.gov.seplag.app.gestor.domain.Orgao;
import br.gov.seplag.app.gestor.repository.OrgaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Orgao}.
 */
@Service
@Transactional
public class OrgaoServiceImpl implements OrgaoService {

    private final Logger log = LoggerFactory.getLogger(OrgaoServiceImpl.class);

    private final OrgaoRepository orgaoRepository;

    public OrgaoServiceImpl(OrgaoRepository orgaoRepository) {
        this.orgaoRepository = orgaoRepository;
    }

    /**
     * Save a orgao.
     *
     * @param orgao the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Orgao save(Orgao orgao) {
        log.debug("Request to save Orgao : {}", orgao);
        return orgaoRepository.save(orgao);
    }

    /**
     * Get all the orgaos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Orgao> findAll() {
        log.debug("Request to get all Orgaos");
        return orgaoRepository.findAll();
    }


    /**
     * Get one orgao by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Orgao> findOne(Long id) {
        log.debug("Request to get Orgao : {}", id);
        return orgaoRepository.findById(id);
    }

    /**
     * Delete the orgao by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Orgao : {}", id);
        orgaoRepository.deleteById(id);
    }
}
