package nl.groep2.cnl.slim_parkeren.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class EntityModel
{
    @Id
    protected ObjectId id;

    public ObjectId getId(){
        return id;
    }

    public void setId(ObjectId id){
        this.id = id;
    }
}
