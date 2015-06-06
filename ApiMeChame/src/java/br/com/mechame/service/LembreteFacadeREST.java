/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mechame.service;

import br.com.mechame.Lembrete;
import br.com.mechame.facade.LembreteFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Pablo
 */
@Stateless
@Path("lembrete")
public class LembreteFacadeREST extends LembreteFacade {
    @PersistenceContext(unitName = "ApiMeChamePU")
    private EntityManager em;

    public LembreteFacadeREST() {
        super(Lembrete.class);
    }

    @POST
    @Override
    @Consumes({ MediaType.APPLICATION_JSON })
    public void create(Lembrete entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({ MediaType.APPLICATION_JSON })
    public void edit(Lembrete entity) {
        // { status: true } ou { status: false }
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        // { status: true } ou { status: false }
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Lembrete find(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    @GET
    @Path("usuario/{telefone}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Lembrete> findByTelefone(@PathParam("telefone") Long telefone){
        return super.findByTelefone(telefone);
    }
    
    @GET
    @Override
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Lembrete> findAll() {
        return super.findAll();
    }

    /*@GET
    @Path("{from}/{to}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Lembrete> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }*/

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
