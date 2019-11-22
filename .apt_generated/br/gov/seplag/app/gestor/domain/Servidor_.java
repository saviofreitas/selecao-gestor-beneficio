package br.gov.seplag.app.gestor.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Servidor.class)
public abstract class Servidor_ {

	public static volatile SingularAttribute<Servidor, Orgao> orgao;
	public static volatile SingularAttribute<Servidor, String> cpf;
	public static volatile SingularAttribute<Servidor, Integer> matricula;
	public static volatile SingularAttribute<Servidor, String> nome;
	public static volatile SingularAttribute<Servidor, Long> id;
	public static volatile SetAttribute<Servidor, Beneficio> beneficios;

	public static final String ORGAO = "orgao";
	public static final String CPF = "cpf";
	public static final String MATRICULA = "matricula";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String BENEFICIOS = "beneficios";

}

