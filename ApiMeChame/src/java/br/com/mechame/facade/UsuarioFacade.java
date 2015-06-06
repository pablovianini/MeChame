/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mechame.facade;

import br.com.mechame.Usuario;
import javax.persistence.EntityManager;

/**
 *
 * @author Pablo
 */
public abstract class UsuarioFacade extends AbstractFacade<Usuario> {

    public UsuarioFacade(Class<Usuario> entityClass) {
        super(entityClass);
    }
    
    public Usuario findByTelefone (long telefone){
        Usuario usuario = new Usuario();
        try {
            usuario = (Usuario) getEntityManager()
                    .createNamedQuery("Usuario.findByTelefone")
                    .setParameter("telefone", telefone)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return usuario;
    }
    
    public Usuario login (Usuario usuario){
         try{
            usuario = (Usuario) getEntityManager()
                    .createNamedQuery("Usuario.login")
                    .setParameter("telefone", usuario.getTelefone())
                    .setParameter("senha", usuario.getSenha())
                    .getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
        }
        return usuario;
    }
    
}
