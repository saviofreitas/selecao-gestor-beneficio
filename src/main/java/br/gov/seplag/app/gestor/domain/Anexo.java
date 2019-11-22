package br.gov.seplag.app.gestor.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Anexo.
 */
@Entity
@Table(name = "anexo")
public class Anexo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotNull
    @Column(name = "tamanho", nullable = false)
    private Long tamanho;

    @Column(name = "mime_type")
    private String mimeType;

    @OneToOne
    @JoinColumn(unique = true)
    private ConteudoAnexo conteudoAnexo;

    @ManyToOne
    @JsonIgnoreProperties("anexos")
    private CategoriaAnexo categoria;

    @ManyToOne
    @JsonIgnoreProperties("anexos")
    private Beneficio beneficio;

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

    public Anexo descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public Anexo tamanho(Long tamanho) {
        this.tamanho = tamanho;
        return this;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public String getMimeType() {
        return mimeType;
    }

    public Anexo mimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public ConteudoAnexo getConteudoAnexo() {
        return conteudoAnexo;
    }

    public Anexo conteudoAnexo(ConteudoAnexo conteudoAnexo) {
        this.conteudoAnexo = conteudoAnexo;
        return this;
    }

    public void setConteudoAnexo(ConteudoAnexo conteudoAnexo) {
        this.conteudoAnexo = conteudoAnexo;
    }

    public CategoriaAnexo getCategoria() {
        return categoria;
    }

    public Anexo categoria(CategoriaAnexo categoriaAnexo) {
        this.categoria = categoriaAnexo;
        return this;
    }

    public void setCategoria(CategoriaAnexo categoriaAnexo) {
        this.categoria = categoriaAnexo;
    }

    public Beneficio getBeneficio() {
        return beneficio;
    }

    public Anexo beneficio(Beneficio beneficio) {
        this.beneficio = beneficio;
        return this;
    }

    public void setBeneficio(Beneficio beneficio) {
        this.beneficio = beneficio;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Anexo)) {
            return false;
        }
        return id != null && id.equals(((Anexo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Anexo{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", tamanho=" + getTamanho() +
            ", mimeType='" + getMimeType() + "'" +
            "}";
    }
}
