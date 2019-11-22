package br.gov.seplag.app.gestor.service.impl;

import br.gov.seplag.app.gestor.service.ConteudoAnexoService;
import br.gov.seplag.app.gestor.domain.ConteudoAnexo;
import br.gov.seplag.app.gestor.repository.ConteudoAnexoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link ConteudoAnexo}.
 */
@Service
@Transactional
public class ConteudoAnexoServiceImpl implements ConteudoAnexoService {

    private final Logger log = LoggerFactory.getLogger(ConteudoAnexoServiceImpl.class);

    private final ConteudoAnexoRepository conteudoAnexoRepository;

    public ConteudoAnexoServiceImpl(ConteudoAnexoRepository conteudoAnexoRepository) {
        this.conteudoAnexoRepository = conteudoAnexoRepository;
    }

    /**
     * Save a conteudoAnexo.
     *
     * @param conteudoAnexo the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ConteudoAnexo save(ConteudoAnexo conteudoAnexo) {
        log.debug("Request to save ConteudoAnexo : {}", conteudoAnexo);
        return conteudoAnexoRepository.save(conteudoAnexo);
    }

    /**
     * Get all the conteudoAnexos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConteudoAnexo> findAll() {
        log.debug("Request to get all ConteudoAnexos");
        return conteudoAnexoRepository.findAll();
    }



    /**
    *  Get all the conteudoAnexos where Anexo is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<ConteudoAnexo> findAllWhereAnexoIsNull() {
        log.debug("Request to get all conteudoAnexos where Anexo is null");
        return StreamSupport
            .stream(conteudoAnexoRepository.findAll().spliterator(), false)
            .filter(conteudoAnexo -> conteudoAnexo.getAnexo() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one conteudoAnexo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ConteudoAnexo> findOne(Long id) {
        log.debug("Request to get ConteudoAnexo : {}", id);
        return conteudoAnexoRepository.findById(id);
    }

    /**
     * Delete the conteudoAnexo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConteudoAnexo : {}", id);
        conteudoAnexoRepository.deleteById(id);
    }
}
