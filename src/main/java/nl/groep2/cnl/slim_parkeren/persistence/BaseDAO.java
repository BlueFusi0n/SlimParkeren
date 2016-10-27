package nl.groep2.cnl.slim_parkeren.persistence;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import nl.groep2.cnl.slim_parkeren.model.EntityModel;

public abstract class BaseDAO<T extends EntityModel> extends BasicDAO<T, ObjectId>
{
    public BaseDAO(Class<T> entityClass, Datastore ds){
        super( entityClass, ds );
    }

    public T get(String id){
        return get(new ObjectId(id));
    }
    
    public List<T> getAll(){
        return find().asList();
    }
}
