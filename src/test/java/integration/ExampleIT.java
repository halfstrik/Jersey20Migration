package integration;

import business.entity.ExampleEntity;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import launcher.Launcher;
import org.eclipse.jetty.server.ServerConnector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client c = Client.create(clientConfig);
        WebResource r = c.resource("http://localhost:8080").path("rest/exampleService/exampleEndpoint");
        ExampleEntity actual = r.accept(MediaType.APPLICATION_JSON).get(ExampleEntity.class);
        assertEquals("example",actual.exampleProperty);
    }
}
