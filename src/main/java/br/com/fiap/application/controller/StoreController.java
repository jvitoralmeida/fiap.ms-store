package br.com.fiap.application.controller;

import java.net.URI;
import java.util.List;


import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.domain.repository.StoreRepository;
import org.bson.types.ObjectId;

import br.com.fiap.domain.model.Store;

@Path("/store")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoreController {

    @Inject
    StoreRepository repository;

    @GET
    public List<Store> listAll() {
        return repository.listAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"user", "admin"})
    public Store findById(String id) {
        return repository.findById(new ObjectId(id));
    }

    @POST
    public Response create(Store store) {
        repository.persist(store);
        return Response.created(URI.create("/store/" + store.id)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"user", "admin"})
    public void update(Store store) {
        repository.update(store);
    }

    @DELETE
    @Path("/{id}")
    public void delete(String id) {
        var storeOptional = repository.findByIdOptional(new ObjectId(id));

        var store = storeOptional.orElseThrow(() -> new RuntimeException("Store not found"));
        repository.delete(store);
    }

    @GET
    @Path("/search/{name}")
    public List<Store> search(String name) {
        return repository.findByName(name);
    }
}
