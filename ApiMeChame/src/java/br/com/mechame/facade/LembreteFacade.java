/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mechame.facade;

import br.com.mechame.Lembrete;
import java.util.List;

/**
 *
 * @author Pablo
 */
public abstract class LembreteFacade extends AbstractFacade<Lembrete> {
    
    public LembreteFacade(Class<Lembrete> entityClass) {
        super(entityClass);
    }
    
    public List<Lembrete> findByTelefone (long telefone){
        List<Lembrete> lista = null;
        try {
            lista = getEntityManager()
                    .createNamedQuery("Lembrete.findByTelefone")
                    .setParameter("telefone", telefone)
                    .getResultList();
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return lista;
    }
}
