package com.bluesky.netty.ssl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class FoxClientNoClientAuth {
    public static void main(String[] args) throws Exception {
        //c:\_tmp\foxclienttrust.keystore
        String clientTrustKeyStoreFile = "/Users/test/data/caNettyTest/cChat.jks";
        String clientTrustKeyStorePwd = "cNetty";

        KeyStore clientTrustKeyStore = KeyStore.getInstance("JKS");
        clientTrustKeyStore.load(new FileInputStream(clientTrustKeyStoreFile), clientTrustKeyStorePwd.toCharArray());

        //TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(clientTrustKeyStore);

        SSLContext sslContext = SSLContext.getInstance("TLSv1");
        sslContext.init(null, tmf.getTrustManagers(), null);

        SSLSocketFactory socketFactory = sslContext.getSocketFactory();
        Socket socket = socketFactory.createSocket("localhost", 11123);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        send("hello", out);
        send("exit", out);
        receive(in);
        socket.close();
    }

    public static void send(String s, PrintWriter out) throws IOException {
        System.out.println("Sending: " + s);
        out.println(s);
    }

    public static void receive(BufferedReader in) throws IOException {
        String s;
        while ((s = in.readLine()) != null) {
            System.out.println("Reveived: " + s);
        }
    }
}
