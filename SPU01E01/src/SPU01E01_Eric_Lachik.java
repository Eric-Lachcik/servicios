import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SPU01E01_Eric_Lachik {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "dir");

            pb.inheritIO();

            Process proceso = pb.start();

            InputStream inputStream = proceso.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            System.out.println("Salida del comando 'ls': ");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = proceso.waitFor();
            System.out.println("Proceso terminado con código de salida: " + exitCode);
        } catch (IOException e) {
            System.err.println("Excepción de E/S: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("El proceso fue interrumpido: " + e.getMessage());
        }
    }
}
