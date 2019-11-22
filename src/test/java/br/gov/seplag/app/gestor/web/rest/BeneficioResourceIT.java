package br.gov.seplag.app.gestor.web.rest;

import br.gov.seplag.app.gestor.GestorBeneficioApp;
import br.gov.seplag.app.gestor.domain.Beneficio;
import br.gov.seplag.app.gestor.repository.BeneficioRepository;
import br.gov.seplag.app.gestor.service.BeneficioService;
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

import br.gov.seplag.app.gestor.domain.enumeration.SituacaoBeneficio;
/**
 * Integration tests for the {@link BeneficioResource} REST controller.
 */
@SpringBootTest(classes = GestorBeneficioApp.class)
public class BeneficioResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATA_CRIACAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_CRIACAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATA_ULTIMA_MOVIMENTACAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_ULTIMA_MOVIMENTACAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final SituacaoBeneficio DEFAULT_SITUACAO = SituacaoBeneficio.PENDENTE;
    private static final SituacaoBeneficio UPDATED_SITUACAO = SituacaoBeneficio.INDEFERIDO;

    @Autowired
    private BeneficioRepository beneficioRepository;

    @Autowired
    private BeneficioService beneficioService;

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

    private MockMvc restBeneficioMockMvc;

    private Beneficio beneficio;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BeneficioResource beneficioResource = new BeneficioResource(beneficioService);
        this.restBeneficioMockMvc = MockMvcBuilders.standaloneSetup(beneficioResource)
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
    public static Beneficio createEntity(EntityManager em) {
        Beneficio beneficio = new Beneficio()
            .descricao(DEFAULT_DESCRICAO)
            .dataCriacao(DEFAULT_DATA_CRIACAO)
            .dataUltimaMovimentacao(DEFAULT_DATA_ULTIMA_MOVIMENTACAO)
            .situacao(DEFAULT_SITUACAO);
        return beneficio;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Beneficio createUpdatedEntity(EntityManager em) {
        Beneficio beneficio = new Beneficio()
            .descricao(UPDATED_DESCRICAO)
            .dataCriacao(UPDATED_DATA_CRIACAO)
            .dataUltimaMovimentacao(UPDATED_DATA_ULTIMA_MOVIMENTACAO)
            .situacao(UPDATED_SITUACAO);
        return beneficio;
    }

    @BeforeEach
    public void initTest() {
        beneficio = createEntity(em);
    }

    @Test
    @Transactional
    public void createBeneficio() throws Exception {
        int databaseSizeBeforeCreate = beneficioRepository.findAll().size();

        // Create the Beneficio
        restBeneficioMockMvc.perform(post("/api/beneficios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(beneficio)))
            .andExpect(status().isCreated());

        // Validate the Beneficio in the database
        List<Beneficio> beneficioList = beneficioRepository.findAll();
        assertThat(beneficioList).hasSize(databaseSizeBeforeCreate + 1);
        Beneficio testBeneficio = beneficioList.get(beneficioList.size() - 1);
        assertThat(testBeneficio.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testBeneficio.getDataCriacao()).isEqualTo(DEFAULT_DATA_CRIACAO);
        assertThat(testBeneficio.getDataUltimaMovimentacao()).isEqualTo(DEFAULT_DATA_ULTIMA_MOVIMENTACAO);
        assertThat(testBeneficio.getSituacao()).isEqualTo(DEFAULT_SITUACAO);
    }

    @Test
    @Transactional
    public void createBeneficioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = beneficioRepository.findAll().size();

        // Create the Beneficio with an existing ID
        beneficio.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBeneficioMockMvc.perform(post("/api/beneficios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(beneficio)))
            .andExpect(status().isBadRequest());

        // Validate the Beneficio in the database
        List<Beneficio> beneficioList = beneficioRepository.findAll();
        assertThat(beneficioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescricaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = beneficioRepository.findAll().size();
        // set the field null
        beneficio.setDescricao(null);

        // Create the Beneficio, which fails.

        restBeneficioMockMvc.perform(post("/api/beneficios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(beneficio)))
            .andExpect(status().isBadRequest());

        List<Beneficio> beneficioList = beneficioRepository.findAll();
        assertThat(beneficioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataCriacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = beneficioRepository.findAll().size();
        // set the field null
        beneficio.setDataCriacao(null);

        // Create the Beneficio, which fails.

        restBeneficioMockMvc.perform(post("/api/beneficios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(beneficio)))
            .andExpect(status().isBadRequest());

        List<Beneficio> beneficioList = beneficioRepository.findAll();
        assertThat(beneficioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataUltimaMovimentacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = beneficioRepository.findAll().size();
        // set the field null
        beneficio.setDataUltimaMovimentacao(null);

        // Create the Beneficio, which fails.

        restBeneficioMockMvc.perform(post("/api/beneficios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(beneficio)))
            .andExpect(status().isBadRequest());

        List<Beneficio> beneficioList = beneficioRepository.findAll();
        assertThat(beneficioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSituacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = beneficioRepository.findAll().size();
        // set the field null
        beneficio.setSituacao(null);

        // Create the Beneficio, which fails.

        restBeneficioMockMvc.perform(post("/api/beneficios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(beneficio)))
            .andExpect(status().isBadRequest());

        List<Beneficio> beneficioList = beneficioRepository.findAll();
        assertThat(beneficioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBeneficios() throws Exception {
        // Initialize the database
        beneficioRepository.saveAndFlush(beneficio);

        // Get all the beneficioList
        restBeneficioMockMvc.perform(get("/api/beneficios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(beneficio.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)))
            .andExpect(jsonPath("$.[*].dataCriacao").value(hasItem(DEFAULT_DATA_CRIACAO.toString())))
            .andExpect(jsonPath("$.[*].dataUltimaMovimentacao").value(hasItem(DEFAULT_DATA_ULTIMA_MOVIMENTACAO.toString())))
            .andExpect(jsonPath("$.[*].situacao").value(hasItem(DEFAULT_SITUACAO.toString())));
    }
    
    @Test
    @Transactional
    public void getBeneficio() throws Exception {
        // Initialize the database
        beneficioRepository.saveAndFlush(beneficio);

        // Get the beneficio
        restBeneficioMockMvc.perform(get("/api/beneficios/{id}", beneficio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(beneficio.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO))
            .andExpect(jsonPath("$.dataCriacao").value(DEFAULT_DATA_CRIACAO.toString()))
            .andExpect(jsonPath("$.dataUltimaMovimentacao").value(DEFAULT_DATA_ULTIMA_MOVIMENTACAO.toString()))
            .andExpect(jsonPath("$.situacao").value(DEFAULT_SITUACAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBeneficio() throws Exception {
        // Get the beneficio
        restBeneficioMockMvc.perform(get("/api/beneficios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBeneficio() throws Exception {
        // Initialize the database
        beneficioService.save(beneficio);

        int databaseSizeBeforeUpdate = beneficioRepository.findAll().size();

        // Update the beneficio
        Beneficio updatedBeneficio = beneficioRepository.findById(beneficio.getId()).get();
        // Disconnect from session so that the updates on updatedBeneficio are not directly saved in db
        em.detach(updatedBeneficio);
        updatedBeneficio
            .descricao(UPDATED_DESCRICAO)
            .dataCriacao(UPDATED_DATA_CRIACAO)
            .dataUltimaMovimentacao(UPDATED_DATA_ULTIMA_MOVIMENTACAO)
            .situacao(UPDATED_SITUACAO);

        restBeneficioMockMvc.perform(put("/api/beneficios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBeneficio)))
            .andExpect(status().isOk());

        // Validate the Beneficio in the database
        List<Beneficio> beneficioList = beneficioRepository.findAll();
        assertThat(beneficioList).hasSize(databaseSizeBeforeUpdate);
        Beneficio testBeneficio = beneficioList.get(beneficioList.size() - 1);
        assertThat(testBeneficio.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testBeneficio.getDataCriacao()).isEqualTo(UPDATED_DATA_CRIACAO);
        assertThat(testBeneficio.getDataUltimaMovimentacao()).isEqualTo(UPDATED_DATA_ULTIMA_MOVIMENTACAO);
        assertThat(testBeneficio.getSituacao()).isEqualTo(UPDATED_SITUACAO);
    }

    @Test
    @Transactional
    public void updateNonExistingBeneficio() throws Exception {
        int databaseSizeBeforeUpdate = beneficioRepository.findAll().size();

        // Create the Beneficio

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBeneficioMockMvc.perform(put("/api/beneficios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(beneficio)))
            .andExpect(status().isBadRequest());

        // Validate the Beneficio in the database
        List<Beneficio> beneficioList = beneficioRepository.findAll();
        assertThat(beneficioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBeneficio() throws Exception {
        // Initialize the database
        beneficioService.save(beneficio);

        int databaseSizeBeforeDelete = beneficioRepository.findAll().size();

        // Delete the beneficio
        restBeneficioMockMvc.perform(delete("/api/beneficios/{id}", beneficio.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Beneficio> beneficioList = beneficioRepository.findAll();
        assertThat(beneficioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
