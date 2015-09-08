/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.xml.ws.Endpoint;

/**
 *
 * @author alanwei
 */
public class Server {

    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Starting Sale Web Service ...");
        Endpoint.publish("http://localhost:9001/sale", new SaleServiceImpl());
        System.out.println("... Ready.");
    }
}