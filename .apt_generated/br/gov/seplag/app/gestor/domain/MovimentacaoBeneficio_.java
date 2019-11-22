package br.gov.seplag.app.gestor.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MovimentacaoBeneficio.class)
public abstract class MovimentacaoBeneficio_ {

	public static volatile SingularAttribute<MovimentacaoBeneficio, Beneficio> beneficio;
	public static volatile SingularAttribute<MovimentacaoBeneficio, Instant> dataTramitacao;
	public static volatile SingularAttribute<MovimentacaoBeneficio, Setor> setorOrigem;
	public static volatile SingularAttribute<MovimentacaoBeneficio, Long> id;
	public static volatile SingularAttribute<MovimentacaoBeneficio, String> responsavel;
	public static volatile SingularAttribute<MovimentacaoBeneficio, Setor> setorDestino;

	public static final String BENEFICIO = "beneficio";
	public static final String DATA_TRAMITACAO = "dataTramitacao";
	public static final String SETOR_ORIGEM = "setorOrigem";
	public static final String ID = "id";
	public static final String RESPONSAVEL = "responsavel";
	public static final String SETOR_DESTINO = "setorDestino";

}

