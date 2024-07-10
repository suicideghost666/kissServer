package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket client;
    private static BufferedReader in;
    private static BufferedWriter out;
    public static void main(String[] args) {
        String filename = args[1];
        try {
            try{
                client = new Socket("localhost",4004);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                String res = "0";
                if (filename.equals("text.txt")) {
                    res = "2";
                }
                out.write(res + "\n");
                out.flush();
                //File output
                BufferedReader file = new BufferedReader(new FileReader("C:/programs/Java/KISServer/KISServer/src/"+filename));
                String fileResult = parseString(file);
                System.out.println("File result:\n"+fileResult);
                //Server parsing
                String serverResult;
                String response = "";
                while ((serverResult = in.readLine()) != null) {
                    response += serverResult;
                }
                System.out.println("Server result:\n"+response);

            } finally {
                client.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static String parseString(BufferedReader file) throws IOException {
        String s;
        String result = "";
        while ((s = file.readLine()) != null) {
                result += s;
        }
        return result;
    }
}
