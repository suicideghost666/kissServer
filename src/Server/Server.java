package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Server {
    static ServerSocket server;
    static Socket client;
    static BufferedReader in;
    static BufferedWriter out;
    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(4004);
                System.out.println("Server is working!");
                client = server.accept();
                System.out.println("Server and client are connected");
                try {
                    in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    String line = in.readLine();
                    System.out.println("Got a number from client: " + line);
                    //File resulting
                    String result = getString();

                    System.out.println("File result:\n"+result);
                    //Sending to client
                    if (line.equals("2")) {
                        out.write(result + "\n");
                    } else {
                        out.write("EXCEPTION: the gotten number is not 2 (filename from the client side is not text.txt)");
                    }
                    out.newLine();
                    out.flush();
                } finally {
                    client.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("server is closed");
                server.close();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String getString() throws IOException {
        String result = "";
        BufferedReader fileIn = new BufferedReader(new FileReader("C:/programs/Java/KISServer/KISServer/src/text.txt"));
        String s;
        while ((s = fileIn.readLine()) != null) {
            result += s;
        }
            return result;
    }
}