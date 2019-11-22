package br.gov.seplag.app.gestor.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConteudoAnexo.class)
public abstract class ConteudoAnexo_ {

	public static volatile SingularAttribute<ConteudoAnexo, byte[]> data;
	public static volatile SingularAttribute<ConteudoAnexo, Anexo> anexo;
	public static volatile SingularAttribute<ConteudoAnexo, Long> id;
	public static volatile SingularAttribute<ConteudoAnexo, String> dataContentType;

	public static final String DATA = "data";
	public static final String ANEXO = "anexo";
	public static final String ID = "id";
	public static final String DATA_CONTENT_TYPE = "dataContentType";

}

