package br.com.mechame.server;

import br.com.mechame.server.Localizacao;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2015-04-18T23:34:29")
@StaticMetamodel(Lembrete.class)
public class Lembrete_ { 

    public static volatile SingularAttribute<Lembrete, Long> id;
    public static volatile CollectionAttribute<Lembrete, Localizacao> localizacaoCollection;
    public static volatile SingularAttribute<Lembrete, String> titulo;
    public static volatile SingularAttribute<Lembrete, Boolean> ativo;
    public static volatile SingularAttribute<Lembrete, Boolean> precisao;
    public static volatile SingularAttribute<Lembrete, Integer> diasSemana;
    public static volatile SingularAttribute<Lembrete, Double> raio;

}