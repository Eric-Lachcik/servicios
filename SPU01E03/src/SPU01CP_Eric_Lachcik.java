import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.InputStream;


public class SPU01CP_Eric_Lachcik {
    public static void main(String[] args) {
        try {
            ProcessBuilder procesoHijo1Builder = new ProcessBuilder("cmd.exe", "/c", "dir");
            Process procesoHijo1 = procesoHijo1Builder.start();


            ProcessBuilder procesoHijo2Builder = new ProcessBuilder("powershell.exe", "-Command", "ForEach ($line in Get-Content -ReadCount 0) { $line -replace 'd', 'D' }");
            Process procesoHijo2 = procesoHijo2Builder.start();


            try (InputStream salidaHijo1 = procesoHijo1.getInputStream();
                 OutputStream entradaHijo2 = procesoHijo2.getOutputStream()) {

                byte[] buffer = new byte[1024];
                int bytesLeidos;
                while ((bytesLeidos = salidaHijo1.read(buffer)) != -1) {
                    entradaHijo2.write(buffer, 0, bytesLeidos);
                }
                entradaHijo2.flush();
            }


            try (BufferedReader lector = new BufferedReader(new InputStreamReader(procesoHijo2.getInputStream()))) {
                String linea;
                System.out.println("Salida del proceso: ");
                while ((linea = lector.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            procesoHijo1.waitFor();
            procesoHijo2.waitFor();

        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
