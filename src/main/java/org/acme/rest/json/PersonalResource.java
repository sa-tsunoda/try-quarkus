package org.acme.rest.json;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/personal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonalResource {

	Map<Integer, PersonalDataReturn> inMemoryDatastore = new ConcurrentHashMap<>();

	@GET
	@Path("/{id}")
    public PersonalDataReturn index(@PathParam(value = "id") Integer id) {
        return inMemoryDatastore.get(id);
    }
    
	@GET
    public Collection<PersonalDataReturn> index() {
        return inMemoryDatastore.values();
    }
    
	@POST
    public PersonalDataReturn save(PersonalDataBody body) {
    	Integer id = inMemoryDatastore.size() + 1;
    	PersonalDataReturn response = new PersonalDataReturn(id,body.sei,body.mei);
    	inMemoryDatastore.put(id, response);
        return response;
    }
}
