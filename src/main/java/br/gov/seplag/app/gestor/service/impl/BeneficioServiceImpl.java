package br.gov.seplag.app.gestor.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.seplag.app.gestor.domain.Beneficio;
import br.gov.seplag.app.gestor.domain.MovimentacaoBeneficio;
import br.gov.seplag.app.gestor.domain.Setor;
import br.gov.seplag.app.gestor.repository.BeneficioRepository;
import br.gov.seplag.app.gestor.service.BeneficioService;
import br.gov.seplag.app.gestor.service.MovimentacaoBeneficioService;
import br.gov.seplag.app.gestor.service.SetorService;

/**
 * Service Implementation for managing {@link Beneficio}.
 */
@Service
@Transactional
public class BeneficioServiceImpl implements BeneficioService {

    private final Logger log = LoggerFactory.getLogger(BeneficioServiceImpl.class);

    private final BeneficioRepository beneficioRepository;
    
    private final MovimentacaoBeneficioService movimentacaoService;
    
    private final SetorService setorService;

    public BeneficioServiceImpl(BeneficioRepository beneficioRepository, MovimentacaoBeneficioService movimentacaoService, SetorService setorService) {
        this.beneficioRepository = beneficioRepository;
        this.movimentacaoService = movimentacaoService;
        this.setorService = setorService;
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
        boolean inclusao = beneficio.getId() == null; 
        if(inclusao) {
        	beneficio.setDataCriacao(Instant.now());
        }
        beneficio.setDataUltimaMovimentacao(Instant.now());
        beneficio = beneficioRepository.save(beneficio);
        
        registrarMovimentacao(beneficio, inclusao);
        
        return beneficio;
    }
    
    private void registrarMovimentacao(Beneficio beneficio, boolean inclusao) {
		if(inclusao) {
	        Optional<Setor> setorCriacao = setorService.findOne(1l);
	        Optional<Setor> gestorCprev = setorService.findOne(2l);

			MovimentacaoBeneficio movimentacao = new MovimentacaoBeneficio();
	        movimentacao.setSetorOrigem(setorCriacao.get());
	        movimentacao.setSetorDestino(gestorCprev.get());
	        movimentacao.setDataTramitacao(Instant.now());
	        movimentacao.setBeneficio(beneficio);
	        movimentacao.setResponsavel("Usuário Logado");

	        movimentacaoService.save(movimentacao);
		}
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
