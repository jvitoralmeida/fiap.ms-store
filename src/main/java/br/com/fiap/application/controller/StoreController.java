package br.com.fiap.application.controller;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

import br.com.fiap.domain.model.Store;

@Path("/store")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoreController {

    @GET
    public List<Store> listAll() {
        return Store.listAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"user", "admin"})
    public Store findById(String id) {
        return Store.findById(new ObjectId(id));
    }

    @POST
    public Response create(Store store) {
        store.persist();
        return Response.created(URI.create("/store/" + store.id)).build();
    }

    @PUT
    @Path("/{id}")
    public void update(String id, Store store) {
        store.update();
    }

    @DELETE
    @Path("/{id}")
    public void delete(String id) {
        var storeOptional = Store.findByIdOptional(id);

        var store = storeOptional.orElseThrow(() -> new RuntimeException("Store not found"));
        store.delete();
    }

    @GET
    @Path("/search/{name}")
    public List<Store> search(String name) {
        return Store.findByName(name);
    }

    @GET
    @Path("/count")
    public Long count() {
        return Store.count();
    }
}
