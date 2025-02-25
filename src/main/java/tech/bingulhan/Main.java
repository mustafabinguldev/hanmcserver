package tech.bingulhan;

import tech.bingulhan.server.MinecraftServer;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args){
        MinecraftServer server = new MinecraftServer(25565);

        try {
            server.start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}