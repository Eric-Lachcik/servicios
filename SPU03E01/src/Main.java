import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static int client = 0;

    public static void main(String[] args) {
        try {
            System.out.println("Creando socket stream servidor");
            ServerSocket serverSocket = new ServerSocket(1518);

            System.out.println("Servidor activo");

            while (true) {
                Socket newSocket = serverSocket.accept();
                client++;
                System.out.println("Numero de clientes recibido: " + client);

                OutputStream os = newSocket.getOutputStream();
                String mens = "Eres el cliente numero: " + client + "\n";
                os.write(mens.getBytes());
                newSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}