package integration;

import business.entity.ExampleEntity;
import launcher.Launcher;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;

public class ExampleIT {
    Server server;

    @Before
    public void setUp()
            throws Exception {
        server = Launcher.startAndReturnServer(8080);
    }

    @After
    public void tearDown()
            throws Exception {
        server.stop();
    }

    @Test
    public void ExampleServiceExampleEndpoint_withServerRunning_expectedToReturnExampleEntity() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(JacksonJsonProvider.class);
        Client c = ClientBuilder.newClient(clientConfig);
        WebTarget t = c.target("http://localhost:8080").path("rest/exampleService/exampleEndpoint");
        ExampleEntity actual = t.request(MediaType.APPLICATION_JSON).get(ExampleEntity.class);
        assertEquals("example",actual.exampleProperty);
    }
}