package integration;

import business.entity.ExampleEntity;
import launcher.Launcher;
import org.eclipse.jetty.server.ServerConnector;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;

public class ExampleIT {
    ServerConnector connector;

    @Before
    public void setUp()
            throws Exception {
        connector = Launcher.readConfigStartServerReturnConnector(8080);
    }

    @After
    public void tearDown()
            throws Exception {
        connector.shutdown();
    }

    @Test
    public void ExampleServiceExampleEndpoint_withServerRunning_expectedToReturnExampleEntity() {
        Client c = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        WebTarget t = c.target("http://localhost:8080").path("rest/exampleService/exampleEndpoint");
        ExampleEntity actual = t.request(MediaType.APPLICATION_JSON).get(ExampleEntity.class);
        assertEquals("example",actual.exampleProperty);
    }
}
