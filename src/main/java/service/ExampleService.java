package service;

import business.entity.ExampleEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("exampleService")
public class ExampleService {
    @GET
    @Path("exampleEndpoint")
    @Produces(MediaType.APPLICATION_JSON)
    public ExampleEntity exampleEndpoint() {
        ExampleEntity exampleEntity = new ExampleEntity();
        exampleEntity.exampleProperty = "example";
        return exampleEntity;
    }
}
