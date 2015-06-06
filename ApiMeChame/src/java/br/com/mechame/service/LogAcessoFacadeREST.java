/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mechame.service;

import br.com.mechame.facade.AbstractFacade;
import br.com.mechame.LogAcesso;
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
@Path("logacesso")
public class LogAcessoFacadeREST extends AbstractFacade<LogAcesso> {
    @PersistenceContext(unitName = "ApiMeChamePU")
    private EntityManager em;

    public LogAcessoFacadeREST() {
        super(LogAcesso.class);
    }

    @POST
    @Override
    @Consumes({ MediaType.APPLICATION_JSON })
    public void create(LogAcesso entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({ MediaType.APPLICATION_JSON })
    public void edit(LogAcesso entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public LogAcesso find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({ MediaType.APPLICATION_JSON })
    public List<LogAcesso> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<LogAcesso> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

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
