package br.gov.seplag.app.gestor.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Servidor.
 */
@Entity
@Table(name = "servidor")
public class Servidor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "cpf", nullable = false)
    private String cpf;

    @NotNull
    @Column(name = "matricula", nullable = false)
    private Integer matricula;

    @OneToMany(mappedBy = "servidor")
    private Set<Beneficio> beneficios = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("servidors")
    private Orgao orgao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Servidor nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Servidor cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public Servidor matricula(Integer matricula) {
        this.matricula = matricula;
        return this;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Set<Beneficio> getBeneficios() {
        return beneficios;
    }

    public Servidor beneficios(Set<Beneficio> beneficios) {
        this.beneficios = beneficios;
        return this;
    }

    public Servidor addBeneficios(Beneficio beneficio) {
        this.beneficios.add(beneficio);
        beneficio.setServidor(this);
        return this;
    }

    public Servidor removeBeneficios(Beneficio beneficio) {
        this.beneficios.remove(beneficio);
        beneficio.setServidor(null);
        return this;
    }

    public void setBeneficios(Set<Beneficio> beneficios) {
        this.beneficios = beneficios;
    }

    public Orgao getOrgao() {
        return orgao;
    }

    public Servidor orgao(Orgao orgao) {
        this.orgao = orgao;
        return this;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Servidor)) {
            return false;
        }
        return id != null && id.equals(((Servidor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Servidor{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", matricula=" + getMatricula() +
            "}";
    }
}
