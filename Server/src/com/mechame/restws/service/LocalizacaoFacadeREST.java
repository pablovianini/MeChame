package com.mechame.restws.service;

import com.mechame.restws.Localizacao;
import java.util.List;
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

/**
 *
 * @author Pablo
 */
@Path("localizacao")
public class LocalizacaoFacadeREST extends AbstractFacade<Localizacao> {
    @PersistenceContext(unitName = "br.com.mechamePU")
    private EntityManager em;

    public LocalizacaoFacadeREST() {
        super(Localizacao.class);
    }

    @POST
    @Override
    @Consumes("application/json")
    public void create(Localizacao entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public void edit(@PathParam("id") Long id, Localizacao entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Localizacao find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces("application/json")
    public List<Localizacao> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces("application/json")
    public List<Localizacao> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
