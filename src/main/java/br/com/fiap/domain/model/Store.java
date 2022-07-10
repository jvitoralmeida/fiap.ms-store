package br.com.fiap.domain.model;

import java.util.List;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "store")
public class Store{

    public ObjectId id;
    public String name;
    public String cnpj;
    public Long percent;
    public String urlLogo;

}
