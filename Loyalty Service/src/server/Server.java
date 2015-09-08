/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import filters.CorsFilter;
import filters.DebugFilter;
import filters.ExceptionHandler;
import java.net.URI;
import java.net.URISyntaxException;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import static org.glassfish.jersey.server.model.Parameter.Source.URI;
import org.slf4j.bridge.SLF4JBridgeHandler;
import resources.CouponListResource;
import resources.CouponResource;
import resources.CustomerResource;
import resources.TotalPointsResource;
import resources.TransactionListResource;
import resources.TransactionResource;
import resources.UnUsedPointsResource;

/**
 *
 * @author alanwei
 */
public class Server {
    public static void main(String args[]) throws URISyntaxException {
        // configure the unified logger
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        // create a web service conguration
        ResourceConfig config = new ResourceConfig();
        // add a debug filter that prints request details
        config.register(CorsFilter.class);
        config.register(DebugFilter.class);
        // add a more functional exception handler
        config.register(ExceptionHandler.class);
        
        // add our resource classes
        config.register(CustomerResource.class);
        config.register(TotalPointsResource.class);
        config.register(UnUsedPointsResource.class);
        config.register(TransactionListResource.class);
        config.register(TransactionResource.class);
        config.register(CouponListResource.class);
        config.register(CouponResource.class);
        config.register(DeclarativeLinkingFeature.class);
        
        // define the URI that the server will use
        URI baseUri = new URI("http://localhost:8081/");
        // start the server (via the built−in Java HTTP server)
        JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("Service Ready on http://localhost:8081/");
    }
}
