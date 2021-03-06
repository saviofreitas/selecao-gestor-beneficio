package br.gov.seplag.app.gestor.domain;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.gov.seplag.app.gestor.domain.enumeration.SituacaoBeneficio;

/**
 * A Beneficio.
 */
@Entity
@Table(name = "beneficio")
public class Beneficio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "data_criacao", nullable = false)
    private Instant dataCriacao;

    @Column(name = "data_ultima_movimentacao", nullable = false)
    private Instant dataUltimaMovimentacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false)
    private SituacaoBeneficio situacao = SituacaoBeneficio.PENDENTE;

    @OneToMany(mappedBy = "beneficio", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Anexo> anexos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("beneficios")
    private Servidor servidor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Beneficio descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Beneficio dataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
        return this;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Instant getDataUltimaMovimentacao() {
        return dataUltimaMovimentacao;
    }

    public Beneficio dataUltimaMovimentacao(Instant dataUltimaMovimentacao) {
        this.dataUltimaMovimentacao = dataUltimaMovimentacao;
        return this;
    }

    public void setDataUltimaMovimentacao(Instant dataUltimaMovimentacao) {
        this.dataUltimaMovimentacao = dataUltimaMovimentacao;
    }

    public SituacaoBeneficio getSituacao() {
        return situacao;
    }

    public Beneficio situacao(SituacaoBeneficio situacao) {
        this.situacao = situacao;
        return this;
    }

    public void setSituacao(SituacaoBeneficio situacao) {
        this.situacao = situacao;
    }

    public Set<Anexo> getAnexos() {
        return anexos;
    }

    public Beneficio anexos(Set<Anexo> anexos) {
        this.anexos = anexos;
        return this;
    }

    public Beneficio addAnexos(Anexo anexo) {
        this.anexos.add(anexo);
        anexo.setBeneficio(this);
        return this;
    }

    public Beneficio removeAnexos(Anexo anexo) {
        this.anexos.remove(anexo);
        anexo.setBeneficio(null);
        return this;
    }

    public void setAnexos(Set<Anexo> anexos) {
        this.anexos = anexos;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public Beneficio servidor(Servidor servidor) {
        this.servidor = servidor;
        return this;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Beneficio)) {
            return false;
        }
        return id != null && id.equals(((Beneficio) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Beneficio{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", dataCriacao='" + getDataCriacao() + "'" +
            ", dataUltimaMovimentacao='" + getDataUltimaMovimentacao() + "'" +
            ", situacao='" + getSituacao() + "'" +
            "}";
    }
}
