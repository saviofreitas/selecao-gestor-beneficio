package br.gov.seplag.app.gestor.domain;

import br.gov.seplag.app.gestor.domain.enumeration.SituacaoBeneficio;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Beneficio.class)
public abstract class Beneficio_ {

	public static volatile SetAttribute<Beneficio, MovimentacaoBeneficio> movimentacoes;
	public static volatile SingularAttribute<Beneficio, Servidor> servidor;
	public static volatile SingularAttribute<Beneficio, SituacaoBeneficio> situacao;
	public static volatile SingularAttribute<Beneficio, Instant> dataUltimaMovimentacao;
	public static volatile SetAttribute<Beneficio, Anexo> anexos;
	public static volatile SingularAttribute<Beneficio, Long> id;
	public static volatile SingularAttribute<Beneficio, Instant> dataCriacao;
	public static volatile SingularAttribute<Beneficio, String> descricao;

	public static final String MOVIMENTACOES = "movimentacoes";
	public static final String SERVIDOR = "servidor";
	public static final String SITUACAO = "situacao";
	public static final String DATA_ULTIMA_MOVIMENTACAO = "dataUltimaMovimentacao";
	public static final String ANEXOS = "anexos";
	public static final String ID = "id";
	public static final String DATA_CRIACAO = "dataCriacao";
	public static final String DESCRICAO = "descricao";

}

