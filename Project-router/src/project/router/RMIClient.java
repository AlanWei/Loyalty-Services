/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.router;

import com.google.gson.Gson;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 *
 * @author alanwei
 */
public class RMIClient {

    public static void main(String args[]) {
        /* Basic camel setup */
        // create default context  
        CamelContext camel = new DefaultCamelContext();

        // register ActiveMQ as the JMS handler  
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        camel.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        /* End basic setup */
        // create message producer  
        ProducerTemplate producer = camel.createProducerTemplate();
        
        String notification = "hello world";
        
        producer.sendBody("jms:queue:notification-list", notification);
    }
}
