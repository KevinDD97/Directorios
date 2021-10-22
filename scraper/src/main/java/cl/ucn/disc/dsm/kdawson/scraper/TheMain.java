package cl.ucn.disc.dsm.kdawson.scraper;

/*
* Main class to scraper the directorio telefonico Ucn
*
* @author Kevin Dawson D.
*
* */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jdk.internal.org.jline.utils.Log;

@Slf4j

public final class TheMain {

    /*
    * The Jsson
    * */

    private static final Gson GSON =new GsonBuilder().setPrettyPrinting().create();
    /*
    * The starting point
    *
    * @param args to use
    * */

    public static void main(String []args) throws IOException {

        // Define el Typo
        Type type=new TypeToken<List<Funcionario>>(){}.getType();

        // Load the File

        // The list of Funcionario
        List<Funcionario> funcionarios = new ArrayList<>();

        int start = 0;
        int end = 30000;

        Random r= new Random();

        Log.debug(" Starting the Scrapping from {} to {}..", start, end);
        for (int id = strart; id <= end; id++) {

            Thread.sleep(250);

            //Wait FOR...
            Thread.sleep(50 + r.nextint(bound:350));

            log.debug("Retriving Funiconario id: {}.", id);


            // Connect and get the Document
            Document doc = Jsoup.connect("http://admision01.ucn.cl/directoriotelefonicoemail/fichagenerica/?cod=" + id).get();

            // Scrapping
            String nombre = doc.getElementById("lblNombre").text();
            String cargo = doc.getElementById("lblCargo").text();
            String unidad = doc.getElementById("lblUnidad").text();
            String email = doc.getElementById("lblEmail").text();
            String telefono = doc.getElementById("lblTelefono").text();
            String oficina = doc.getElementById("lblOficina").text();
            String direccion = doc.getElementById("lblDireccion").text();


            // Skip if date not found
            if (nombre.length() <= 1) {
                Log.warn("No data found for id: {}.", id);
                continue;
            }
            // Call the constructor
            Funcionario f = Funcionario.builder()

                    .id(id)
                    .nombre(nombre)
                    .cargo(cargo)
                    .unidad(unidad)
                    .email(email)
                    .telefono(telefono)
                    .oficina(oficina)
                    .direccion(direccion)
                    .build();

            // Add the Funcionario into the list
            funcionarios.add(funcionario);

            // save by 25
            if (funcionarios.size() % 25 == 0) {

                // Write the List of Funcionario in JSON format
                FileUtils.writeStringToFile(new File("Funcionarios.json"), GSON.toJson(funcionarios)
                        , StandardCharsets.UTF_8);

            }

            log.debug("Done.");

        }
    }
}