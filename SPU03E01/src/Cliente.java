import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        try {
            System.out.println("Creando socket stream client ");
            Socket clientSocket = new Socket("localhost", 1518);

            System.out.println("Conexion establecida");

            InputStream is = clientSocket.getInputStream();
            byte[] mens = new byte[25];
            int bytes = is.read(mens);
            System.out.println("Mensaje: " + new String(mens, 0, bytes));

            System.out.println("Cerrando socket stream client ");
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
