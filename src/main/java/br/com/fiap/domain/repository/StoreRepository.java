package br.com.fiap.domain.repository;

import br.com.fiap.domain.model.Store;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StoreRepository implements PanacheMongoRepository<Store> {

    public List<Store> findByName(String name) {
        return find("name",name).list();
    }
}
