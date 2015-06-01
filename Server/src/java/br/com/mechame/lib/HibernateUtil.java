/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mechame.lib;

/**
 *
 * @author Pablo
 */
import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;

public final class HibernateUtil{
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final Session session = sessionFactory.openSession();
    
    private static SessionFactory buildSessionFactory()
    {
        try
        {
            SessionFactory s = new AnnotationConfiguration()
                    .configure().buildSessionFactory();
            return s;
        }
        catch (HibernateException ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static void persistObject(Object object){
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        shutdown();
    }
  
    private static void shutdown() {
        sessionFactory.close();
    }
}
