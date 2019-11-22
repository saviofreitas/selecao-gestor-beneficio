package br.gov.seplag.app.gestor.web.rest;

import br.gov.seplag.app.gestor.domain.ConteudoAnexo;
import br.gov.seplag.app.gestor.service.ConteudoAnexoService;
import br.gov.seplag.app.gestor.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link br.gov.seplag.app.gestor.domain.ConteudoAnexo}.
 */
@RestController
@RequestMapping("/api")
public class ConteudoAnexoResource {

    private final Logger log = LoggerFactory.getLogger(ConteudoAnexoResource.class);

    private static final String ENTITY_NAME = "conteudoAnexo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConteudoAnexoService conteudoAnexoService;

    public ConteudoAnexoResource(ConteudoAnexoService conteudoAnexoService) {
        this.conteudoAnexoService = conteudoAnexoService;
    }

    /**
     * {@code POST  /conteudo-anexos} : Create a new conteudoAnexo.
     *
     * @param conteudoAnexo the conteudoAnexo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new conteudoAnexo, or with status {@code 400 (Bad Request)} if the conteudoAnexo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/conteudo-anexos")
    public ResponseEntity<ConteudoAnexo> createConteudoAnexo(@Valid @RequestBody ConteudoAnexo conteudoAnexo) throws URISyntaxException {
        log.debug("REST request to save ConteudoAnexo : {}", conteudoAnexo);
        if (conteudoAnexo.getId() != null) {
            throw new BadRequestAlertException("A new conteudoAnexo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConteudoAnexo result = conteudoAnexoService.save(conteudoAnexo);
        return ResponseEntity.created(new URI("/api/conteudo-anexos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /conteudo-anexos} : Updates an existing conteudoAnexo.
     *
     * @param conteudoAnexo the conteudoAnexo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated conteudoAnexo,
     * or with status {@code 400 (Bad Request)} if the conteudoAnexo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the conteudoAnexo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/conteudo-anexos")
    public ResponseEntity<ConteudoAnexo> updateConteudoAnexo(@Valid @RequestBody ConteudoAnexo conteudoAnexo) throws URISyntaxException {
        log.debug("REST request to update ConteudoAnexo : {}", conteudoAnexo);
        if (conteudoAnexo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConteudoAnexo result = conteudoAnexoService.save(conteudoAnexo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, conteudoAnexo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /conteudo-anexos} : get all the conteudoAnexos.
     *

     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of conteudoAnexos in body.
     */
    @GetMapping("/conteudo-anexos")
    public List<ConteudoAnexo> getAllConteudoAnexos(@RequestParam(required = false) String filter) {
        if ("anexo-is-null".equals(filter)) {
            log.debug("REST request to get all ConteudoAnexos where anexo is null");
            return conteudoAnexoService.findAllWhereAnexoIsNull();
        }
        log.debug("REST request to get all ConteudoAnexos");
        return conteudoAnexoService.findAll();
    }

    /**
     * {@code GET  /conteudo-anexos/:id} : get the "id" conteudoAnexo.
     *
     * @param id the id of the conteudoAnexo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the conteudoAnexo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/conteudo-anexos/{id}")
    public ResponseEntity<ConteudoAnexo> getConteudoAnexo(@PathVariable Long id) {
        log.debug("REST request to get ConteudoAnexo : {}", id);
        Optional<ConteudoAnexo> conteudoAnexo = conteudoAnexoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(conteudoAnexo);
    }

    /**
     * {@code DELETE  /conteudo-anexos/:id} : delete the "id" conteudoAnexo.
     *
     * @param id the id of the conteudoAnexo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/conteudo-anexos/{id}")
    public ResponseEntity<Void> deleteConteudoAnexo(@PathVariable Long id) {
        log.debug("REST request to delete ConteudoAnexo : {}", id);
        conteudoAnexoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
