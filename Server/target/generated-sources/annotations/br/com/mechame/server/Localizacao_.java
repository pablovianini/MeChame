package br.com.mechame.server;

import br.com.mechame.server.Lembrete;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2015-04-18T23:34:29")
@StaticMetamodel(Localizacao.class)
public class Localizacao_ { 

    public static volatile SingularAttribute<Localizacao, Long> id;
    public static volatile SingularAttribute<Localizacao, Lembrete> idLembrete;
    public static volatile SingularAttribute<Localizacao, Double> longitude;
    public static volatile SingularAttribute<Localizacao, Double> latitude;

}