package cl.ucn.disc.dsm.kdawson.scraper;

/*
* Main class to scraper the directorio telefonico Ucn
*
* @author Kevin Dawson D.
*
* */

import com.google.common.reflect.TypeToken;
import com.google.firestore.v1.Document;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
        String data= FileUtils.readFileToString(new File(pathname:"funcionarios.json") StandardCharsets.UTF_8);

        // The list of Funcionario
        List<Funcionario> funcionarios = GSON.fromJson(data,type);

        int start = funcionarios.get(funcionarios.size()-1).getId();
        int end =30000;

        Log.debug(" Starting the Scrapping from {} to {}..", start, end);
        for (int id = start; id <=  end; id++) {

            Thread.sleeps(250);

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

            //skip if data not found
            if(nombre.length() <= 1){
                log.warn("No data found for id: {}.",id);
                continue;
            }

            // Skip if date not found
            if (nombre.length() <= 1) {
                Log.warn("No data found for id: {}.", id);
                continue;
            }

            log.info("Funcionario id: {} founded: {}.", id,nombre);
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

                Log.debug("Writing {} Funcionarios to file..", funcionarios.size());

                // Write the List of Funcionario in JSON format
                FileUtils.writeStringToFile(new File("Funcionarios.json"), GSON.toJson(funcionarios)
                        , StandardCharsets.UTF_8);

            }

            log.debug("Done.");

        }
    }
}