
import domain.Coupon;
import javax.jms.ConnectionFactory;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.auth.BasicScheme;

public class router {

    public static void main(String[] args) throws Exception {

        /*
         * Basic camel setup
         */
        // create default context  
        CamelContext camel = new DefaultCamelContext();

        // register ActiveMQ as the JMS handler  
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        camel.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        /*
         * End basic setup
         */

        /*
         * Create the routes
         */
        camel.addRoutes(new RouteBuilder() {

            @Override
            public void configure() {
                // logging
                from("websocket://localhost:9091/email")
                        // remove all headers except Authorization  
                        .setHeader("Authorization", constant("Basic d2VpY2gzNjQ6b2xkZmxvY2s1Nw=="))
                        .removeHeaders("*", "Authorization")
                        .setProperty("customer_email").body()
                        .setBody(constant(null))
                        // send to Vend
                        .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                        .recipientList(simple("https://info323.vendhq.com/api/customers?email=${exchangeProperty.customer_email}"))
                        // put Vend's reponse in a queue
                        .to("jms:queue:customer-detail");

                from("jms:queue:customer-detail")
                        .convertBodyTo(String.class)
                        .to("websocket://localhost:9091/email?sendToAll=true");

                // create coupon on REST
                from("websocket://localhost:9091/coupon")
                        .removeHeaders("*")
                        .setHeader("customer_id").jsonpath("$.customerID")
                        .setHeader("id").jsonpath("$.id")
                        .setHeader("points").jsonpath("$.points")
                        .setHeader("used").jsonpath("$.used")
                        .to("jms:queue:create-coupon");

                from("jms:queue:create-coupon")
                        .bean(Coupon.class, "createCoupon(${headers.id}, ${headers.points})")
                        .to("jms:queue:coupon-object");

                from("jms:queue:coupon-object")
                        .setProperty("customer_id").header("customer_id")
                        .removeHeaders("*")
                        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                        .setHeader(Exchange.CONTENT_TYPE, constant("text/xml"))
                        .recipientList(simple("http4://localhost:8081/customers/${exchangeProperty.customer_id}/coupons"))
                        .to("jms:queue:create-coupon-response"); // HTTP response ends up in this queue

                // create product on Vend
                from("websocket://localhost:9091/couponvend")
                        .setHeader("Authorization", constant("Basic d2VpY2gzNjQ6b2xkZmxvY2s1Nw=="))
                        .removeHeaders("*", "Authorization")
                        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                        .setHeader(Exchange.CONTENT_TYPE, constant("applicatopn/json"))
                        .to("https://info323.vendhq.com/api/products")
                        .to("jms:queue:product-record")
                        .to("jms:queue:product-detail");
                
                from("jms:queue:product-detail")
                        .convertBodyTo(String.class)
                        .to("websocket://localhost:9091/couponvend?sendToAll=true");
            }
        });

        /*
         * Start the router
         */
        // turn exchange tracing on or off (false is off)  
        camel.setTracing(false);

        // start routing  
        System.out.println("Starting router...");

        camel.start();

        System.out.println("... ready.");

    }

    public static String getPassword(String prompt) {
        JPasswordField txtPasswd = new JPasswordField();
        int resp = JOptionPane.showConfirmDialog(null, txtPasswd, prompt,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resp == JOptionPane.OK_OPTION) {
            String password = new String(txtPasswd.getPassword());
            return password;
        }
        return null;
    }

}
