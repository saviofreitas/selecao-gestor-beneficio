package br.gov.seplag.app.gestor.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A MovimentacaoBeneficio.
 */
@Entity
@Table(name = "movimentacao_beneficio")
public class MovimentacaoBeneficio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data_tramitacao", nullable = false)
    private Instant dataTramitacao;

    @Column(name = "responsavel")
    private String responsavel;

    @ManyToOne
    @JsonIgnoreProperties("movimentacaoBeneficios")
    private Beneficio beneficio;

    @ManyToOne
    @JsonIgnoreProperties("movimentacaoBeneficios")
    private Setor setorOrigem;

    @ManyToOne
    @JsonIgnoreProperties("movimentacaoBeneficios")
    private Setor setorDestino;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDataTramitacao() {
        return dataTramitacao;
    }

    public MovimentacaoBeneficio dataTramitacao(Instant dataTramitacao) {
        this.dataTramitacao = dataTramitacao;
        return this;
    }

    public void setDataTramitacao(Instant dataTramitacao) {
        this.dataTramitacao = dataTramitacao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public MovimentacaoBeneficio responsavel(String responsavel) {
        this.responsavel = responsavel;
        return this;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Beneficio getBeneficio() {
        return beneficio;
    }

    public MovimentacaoBeneficio beneficio(Beneficio beneficio) {
        this.beneficio = beneficio;
        return this;
    }

    public void setBeneficio(Beneficio beneficio) {
        this.beneficio = beneficio;
    }

    public Setor getSetorOrigem() {
        return setorOrigem;
    }

    public MovimentacaoBeneficio setorOrigem(Setor setor) {
        this.setorOrigem = setor;
        return this;
    }

    public void setSetorOrigem(Setor setor) {
        this.setorOrigem = setor;
    }

    public Setor getSetorDestino() {
        return setorDestino;
    }

    public MovimentacaoBeneficio setorDestino(Setor setor) {
        this.setorDestino = setor;
        return this;
    }

    public void setSetorDestino(Setor setor) {
        this.setorDestino = setor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MovimentacaoBeneficio)) {
            return false;
        }
        return id != null && id.equals(((MovimentacaoBeneficio) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MovimentacaoBeneficio{" +
            "id=" + getId() +
            ", dataTramitacao='" + getDataTramitacao() + "'" +
            ", responsavel='" + getResponsavel() + "'" +
            "}";
    }
}
