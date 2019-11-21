package br.gov.seplag.app.gestor.web.rest;

import br.gov.seplag.app.gestor.GestorBeneficioApp;
import br.gov.seplag.app.gestor.domain.MovimentacaoBeneficio;
import br.gov.seplag.app.gestor.repository.MovimentacaoBeneficioRepository;
import br.gov.seplag.app.gestor.service.MovimentacaoBeneficioService;
import br.gov.seplag.app.gestor.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static br.gov.seplag.app.gestor.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MovimentacaoBeneficioResource} REST controller.
 */
@SpringBootTest(classes = GestorBeneficioApp.class)
public class MovimentacaoBeneficioResourceIT {

    private static final Instant DEFAULT_DATA_TRAMITACAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_TRAMITACAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_RESPONSAVEL = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSAVEL = "BBBBBBBBBB";

    @Autowired
    private MovimentacaoBeneficioRepository movimentacaoBeneficioRepository;

    @Autowired
    private MovimentacaoBeneficioService movimentacaoBeneficioService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restMovimentacaoBeneficioMockMvc;

    private MovimentacaoBeneficio movimentacaoBeneficio;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MovimentacaoBeneficioResource movimentacaoBeneficioResource = new MovimentacaoBeneficioResource(movimentacaoBeneficioService);
        this.restMovimentacaoBeneficioMockMvc = MockMvcBuilders.standaloneSetup(movimentacaoBeneficioResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MovimentacaoBeneficio createEntity(EntityManager em) {
        MovimentacaoBeneficio movimentacaoBeneficio = new MovimentacaoBeneficio()
            .dataTramitacao(DEFAULT_DATA_TRAMITACAO)
            .responsavel(DEFAULT_RESPONSAVEL);
        return movimentacaoBeneficio;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MovimentacaoBeneficio createUpdatedEntity(EntityManager em) {
        MovimentacaoBeneficio movimentacaoBeneficio = new MovimentacaoBeneficio()
            .dataTramitacao(UPDATED_DATA_TRAMITACAO)
            .responsavel(UPDATED_RESPONSAVEL);
        return movimentacaoBeneficio;
    }

    @BeforeEach
    public void initTest() {
        movimentacaoBeneficio = createEntity(em);
    }

    @Test
    @Transactional
    public void createMovimentacaoBeneficio() throws Exception {
        int databaseSizeBeforeCreate = movimentacaoBeneficioRepository.findAll().size();

        // Create the MovimentacaoBeneficio
        restMovimentacaoBeneficioMockMvc.perform(post("/api/movimentacao-beneficios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movimentacaoBeneficio)))
            .andExpect(status().isCreated());

        // Validate the MovimentacaoBeneficio in the database
        List<MovimentacaoBeneficio> movimentacaoBeneficioList = movimentacaoBeneficioRepository.findAll();
        assertThat(movimentacaoBeneficioList).hasSize(databaseSizeBeforeCreate + 1);
        MovimentacaoBeneficio testMovimentacaoBeneficio = movimentacaoBeneficioList.get(movimentacaoBeneficioList.size() - 1);
        assertThat(testMovimentacaoBeneficio.getDataTramitacao()).isEqualTo(DEFAULT_DATA_TRAMITACAO);
        assertThat(testMovimentacaoBeneficio.getResponsavel()).isEqualTo(DEFAULT_RESPONSAVEL);
    }

    @Test
    @Transactional
    public void createMovimentacaoBeneficioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = movimentacaoBeneficioRepository.findAll().size();

        // Create the MovimentacaoBeneficio with an existing ID
        movimentacaoBeneficio.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMovimentacaoBeneficioMockMvc.perform(post("/api/movimentacao-beneficios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movimentacaoBeneficio)))
            .andExpect(status().isBadRequest());

        // Validate the MovimentacaoBeneficio in the database
        List<MovimentacaoBeneficio> movimentacaoBeneficioList = movimentacaoBeneficioRepository.findAll();
        assertThat(movimentacaoBeneficioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDataTramitacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = movimentacaoBeneficioRepository.findAll().size();
        // set the field null
        movimentacaoBeneficio.setDataTramitacao(null);

        // Create the MovimentacaoBeneficio, which fails.

        restMovimentacaoBeneficioMockMvc.perform(post("/api/movimentacao-beneficios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movimentacaoBeneficio)))
            .andExpect(status().isBadRequest());

        List<MovimentacaoBeneficio> movimentacaoBeneficioList = movimentacaoBeneficioRepository.findAll();
        assertThat(movimentacaoBeneficioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMovimentacaoBeneficios() throws Exception {
        // Initialize the database
        movimentacaoBeneficioRepository.saveAndFlush(movimentacaoBeneficio);

        // Get all the movimentacaoBeneficioList
        restMovimentacaoBeneficioMockMvc.perform(get("/api/movimentacao-beneficios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(movimentacaoBeneficio.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataTramitacao").value(hasItem(DEFAULT_DATA_TRAMITACAO.toString())))
            .andExpect(jsonPath("$.[*].responsavel").value(hasItem(DEFAULT_RESPONSAVEL)));
    }
    
    @Test
    @Transactional
    public void getMovimentacaoBeneficio() throws Exception {
        // Initialize the database
        movimentacaoBeneficioRepository.saveAndFlush(movimentacaoBeneficio);

        // Get the movimentacaoBeneficio
        restMovimentacaoBeneficioMockMvc.perform(get("/api/movimentacao-beneficios/{id}", movimentacaoBeneficio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(movimentacaoBeneficio.getId().intValue()))
            .andExpect(jsonPath("$.dataTramitacao").value(DEFAULT_DATA_TRAMITACAO.toString()))
            .andExpect(jsonPath("$.responsavel").value(DEFAULT_RESPONSAVEL));
    }

    @Test
    @Transactional
    public void getNonExistingMovimentacaoBeneficio() throws Exception {
        // Get the movimentacaoBeneficio
        restMovimentacaoBeneficioMockMvc.perform(get("/api/movimentacao-beneficios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMovimentacaoBeneficio() throws Exception {
        // Initialize the database
        movimentacaoBeneficioService.save(movimentacaoBeneficio);

        int databaseSizeBeforeUpdate = movimentacaoBeneficioRepository.findAll().size();

        // Update the movimentacaoBeneficio
        MovimentacaoBeneficio updatedMovimentacaoBeneficio = movimentacaoBeneficioRepository.findById(movimentacaoBeneficio.getId()).get();
        // Disconnect from session so that the updates on updatedMovimentacaoBeneficio are not directly saved in db
        em.detach(updatedMovimentacaoBeneficio);
        updatedMovimentacaoBeneficio
            .dataTramitacao(UPDATED_DATA_TRAMITACAO)
            .responsavel(UPDATED_RESPONSAVEL);

        restMovimentacaoBeneficioMockMvc.perform(put("/api/movimentacao-beneficios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMovimentacaoBeneficio)))
            .andExpect(status().isOk());

        // Validate the MovimentacaoBeneficio in the database
        List<MovimentacaoBeneficio> movimentacaoBeneficioList = movimentacaoBeneficioRepository.findAll();
        assertThat(movimentacaoBeneficioList).hasSize(databaseSizeBeforeUpdate);
        MovimentacaoBeneficio testMovimentacaoBeneficio = movimentacaoBeneficioList.get(movimentacaoBeneficioList.size() - 1);
        assertThat(testMovimentacaoBeneficio.getDataTramitacao()).isEqualTo(UPDATED_DATA_TRAMITACAO);
        assertThat(testMovimentacaoBeneficio.getResponsavel()).isEqualTo(UPDATED_RESPONSAVEL);
    }

    @Test
    @Transactional
    public void updateNonExistingMovimentacaoBeneficio() throws Exception {
        int databaseSizeBeforeUpdate = movimentacaoBeneficioRepository.findAll().size();

        // Create the MovimentacaoBeneficio

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMovimentacaoBeneficioMockMvc.perform(put("/api/movimentacao-beneficios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movimentacaoBeneficio)))
            .andExpect(status().isBadRequest());

        // Validate the MovimentacaoBeneficio in the database
        List<MovimentacaoBeneficio> movimentacaoBeneficioList = movimentacaoBeneficioRepository.findAll();
        assertThat(movimentacaoBeneficioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMovimentacaoBeneficio() throws Exception {
        // Initialize the database
        movimentacaoBeneficioService.save(movimentacaoBeneficio);

        int databaseSizeBeforeDelete = movimentacaoBeneficioRepository.findAll().size();

        // Delete the movimentacaoBeneficio
        restMovimentacaoBeneficioMockMvc.perform(delete("/api/movimentacao-beneficios/{id}", movimentacaoBeneficio.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MovimentacaoBeneficio> movimentacaoBeneficioList = movimentacaoBeneficioRepository.findAll();
        assertThat(movimentacaoBeneficioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
