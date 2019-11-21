package br.gov.seplag.app.gestor.web.rest;

import br.gov.seplag.app.gestor.domain.MovimentacaoBeneficio;
import br.gov.seplag.app.gestor.service.MovimentacaoBeneficioService;
import br.gov.seplag.app.gestor.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link br.gov.seplag.app.gestor.domain.MovimentacaoBeneficio}.
 */
@RestController
@RequestMapping("/api")
public class MovimentacaoBeneficioResource {

    private final Logger log = LoggerFactory.getLogger(MovimentacaoBeneficioResource.class);

    private static final String ENTITY_NAME = "movimentacaoBeneficio";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MovimentacaoBeneficioService movimentacaoBeneficioService;

    public MovimentacaoBeneficioResource(MovimentacaoBeneficioService movimentacaoBeneficioService) {
        this.movimentacaoBeneficioService = movimentacaoBeneficioService;
    }

    /**
     * {@code POST  /movimentacao-beneficios} : Create a new movimentacaoBeneficio.
     *
     * @param movimentacaoBeneficio the movimentacaoBeneficio to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new movimentacaoBeneficio, or with status {@code 400 (Bad Request)} if the movimentacaoBeneficio has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/movimentacao-beneficios")
    public ResponseEntity<MovimentacaoBeneficio> createMovimentacaoBeneficio(@Valid @RequestBody MovimentacaoBeneficio movimentacaoBeneficio) throws URISyntaxException {
        log.debug("REST request to save MovimentacaoBeneficio : {}", movimentacaoBeneficio);
        if (movimentacaoBeneficio.getId() != null) {
            throw new BadRequestAlertException("A new movimentacaoBeneficio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MovimentacaoBeneficio result = movimentacaoBeneficioService.save(movimentacaoBeneficio);
        return ResponseEntity.created(new URI("/api/movimentacao-beneficios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /movimentacao-beneficios} : Updates an existing movimentacaoBeneficio.
     *
     * @param movimentacaoBeneficio the movimentacaoBeneficio to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated movimentacaoBeneficio,
     * or with status {@code 400 (Bad Request)} if the movimentacaoBeneficio is not valid,
     * or with status {@code 500 (Internal Server Error)} if the movimentacaoBeneficio couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/movimentacao-beneficios")
    public ResponseEntity<MovimentacaoBeneficio> updateMovimentacaoBeneficio(@Valid @RequestBody MovimentacaoBeneficio movimentacaoBeneficio) throws URISyntaxException {
        log.debug("REST request to update MovimentacaoBeneficio : {}", movimentacaoBeneficio);
        if (movimentacaoBeneficio.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MovimentacaoBeneficio result = movimentacaoBeneficioService.save(movimentacaoBeneficio);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, movimentacaoBeneficio.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /movimentacao-beneficios} : get all the movimentacaoBeneficios.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of movimentacaoBeneficios in body.
     */
    @GetMapping("/movimentacao-beneficios")
    public ResponseEntity<List<MovimentacaoBeneficio>> getAllMovimentacaoBeneficios(Pageable pageable) {
        log.debug("REST request to get a page of MovimentacaoBeneficios");
        Page<MovimentacaoBeneficio> page = movimentacaoBeneficioService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /movimentacao-beneficios/:id} : get the "id" movimentacaoBeneficio.
     *
     * @param id the id of the movimentacaoBeneficio to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the movimentacaoBeneficio, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/movimentacao-beneficios/{id}")
    public ResponseEntity<MovimentacaoBeneficio> getMovimentacaoBeneficio(@PathVariable Long id) {
        log.debug("REST request to get MovimentacaoBeneficio : {}", id);
        Optional<MovimentacaoBeneficio> movimentacaoBeneficio = movimentacaoBeneficioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(movimentacaoBeneficio);
    }

    /**
     * {@code DELETE  /movimentacao-beneficios/:id} : delete the "id" movimentacaoBeneficio.
     *
     * @param id the id of the movimentacaoBeneficio to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/movimentacao-beneficios/{id}")
    public ResponseEntity<Void> deleteMovimentacaoBeneficio(@PathVariable Long id) {
        log.debug("REST request to delete MovimentacaoBeneficio : {}", id);
        movimentacaoBeneficioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
