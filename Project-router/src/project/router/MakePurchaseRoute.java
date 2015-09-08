/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.router;

import domain.Coupon;
import domain.Sale;
import domain.Transaction;
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

/**
 *
 * @author alanwei
 */
public class MakePurchaseRoute {

    public static void main(String args[]) throws Exception {
        /* Basic camel setup */
        // create default context  
        CamelContext camel = new DefaultCamelContext();

        // register ActiveMQ as the JMS handler  
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        camel.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        /* End basic setup */
        /* Create the routes */
        // Email
        camel.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("imaps://outlook.office365.com?username=weich364@student.otago.ac.nz"
                        //+ "&password=" + getPassword("Enter your E-Mail password")
                        + "&password=" + "u3xzdn4nwxps"
                        + "&searchTerm.subject=Vend:SaleUpdate"
                        + "&debugMode=false") // set to true if you want to see the authentication details  
                        .convertBodyTo(String.class)
                        .to("jms:queue:vend-new-sale");
            }
        });

        // get id, customer_id, price
        camel.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("jms:queue:vend-new-sale")
                        .setHeader("id").jsonpath("$.id")
                        .setHeader("customer_id").jsonpath("$.customer_id")
                        .setHeader("points").jsonpath("$.totals.total_price")
                        .multicast()
                        .to("jms:queue:vend-new-transaction", "jms:queue:vend-new-sale", "websocket://localhost:9091/productvend?sendToAll=true", "websocket://localhost:9091/couponput?sendToAll=true");
            }
        });

        camel.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("websocket://localhost:9091/couponput")
                        .setProperty("coupon_id").jsonpath("$.coupon_id")
                        .setProperty("customer_id").jsonpath("$.customer_id")
                        .removeHeaders("*")
                        .setBody(constant(null))
                        .setHeader(Exchange.HTTP_METHOD, constant("PUT"))
                        //.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                        //.marshal().json(JsonLibrary.Gson)
                        .recipientList(simple("http4://localhost:8081/customers/${exchangeProperty.customer_id}/coupons/${exchangeProperty.coupon_id}"));
            }
        });

        // delete coupon
        camel.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("websocket://localhost:9091/coupondelete")
                        .setProperty("product_id").body()
                        .setHeader("Authorization", constant("Basic d2VpY2gzNjQ6b2xkZmxvY2s1Nw=="))
                        .removeHeaders("*", "Authorization")
                        .setBody(constant(null))
                        .setHeader(Exchange.HTTP_METHOD, constant("DELETE"))
                        .recipientList(simple("https://info323.vendhq.com/api/products/${exchangeProperty.product_id}"));
            }
        });

        // WS - rmi
        camel.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("websocket://localhost:9091/productvend")
                        .setProperty("product_id").body()
                        .setHeader("Authorization", constant("Basic d2VpY2gzNjQ6b2xkZmxvY2s1Nw=="))
                        .removeHeaders("*", "Authorization", "customer_id")
                        .setBody(constant(null))
                        .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                        .recipientList(simple("https://info323.vendhq.com/api/products/${exchangeProperty.product_id}"))
                        .to("jms:queue:get-product-detail");

                from("jms:queue:get-product-detail")
                        .convertBodyTo(String.class)
                        .to("jms:queue:test1")
                        .to("websocket://localhost:9091/validate?sendToAll=true");

                from("websocket://localhost:9091/validate")
                        .convertBodyTo(String.class)
                        .to("rmi://localhost:1099/social?remoteInterfaces=social.ISocialService&method=postNotification");
            }
        });

        // RPC
        camel.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("jms:queue:vend-new-sale")
                        .unmarshal().json(JsonLibrary.Gson, Sale.class)
                        .to("cxf:http://localhost:9001/sale?serviceClass=sale.ISaleService&defaultOperationName=newSale");
            }
        });

        // convert transaction
        camel.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("jms:queue:vend-new-transaction")
                        .bean(Transaction.class, "createTransaction(${headers.id}, ${headers.points}, ${headers.customer_id})")
                        .to("jms:object-new-transaction");
            }
        });

        // REST
        camel.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("jms:object-new-transaction")
                        .setProperty("customer_id").header("customer_id")
                        .removeHeaders("*") // remove headers to stop them being sent to the service  
                        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                        .marshal().json(JsonLibrary.Gson)
                        .recipientList(simple("http4://localhost:8081/customers/${exchangeProperty.customer_id}/transactions"))
                        .to("jms:queue:http-response");  // HTTP response ends up in this queue
            }
        });

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
                        .to("jms:queue:coupon-record")
                        .to("jms:queue:product-detail");

                from("jms:queue:product-detail")
                        .convertBodyTo(String.class)
                        .to("websocket://localhost:9091/couponvend?sendToAll=true");
            }
        });


        /* Start the router */
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
