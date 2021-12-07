package cl.ucn.disc.dsm.kdawson.directorio;


import android.os.AsyncTask;
import android.util.AndroidException;

import java.io.IOException;
import java.io.InputStreamReader;

/*
* The viewmodel of funcionario.
*
* @author Kevin Dawson D
*
* */
public final class FuncionarioViewModel extends AndroidViewModel {

    /*
     * the list of {@link funcionario}
     *
     * */
    private MutableLiveData<List<Funcionario>> funcionarios;


    /*
     * the constructor
     *
     * @param application to use
     * */
    public FuncionarioViewModel(@NonNull Apllication application) {
        super(application);
    }


    public LiveData<List<Funcionario>> getFuncionarios() {

        //lazy load

        if (this.funcionarios == null) {

            this.funcionarios = new MutableLiveData<>();
            this.loadFuncionarios();

        }
        return this.funcionarios;


    }

    /*
    *  Read the funcionarios from funcionarios.json
    *
    *
    * */
    private void loadFuncionarios(){

        // Run in the background
        AsyncTask.execute(() {

            List<Funcionario> theFuncionarios;
            // rEAD THE FUNCIONARIOS.JSON
            try (final InputStream is = super.getApplication().getAssets().open(fileName="funcionarios.json")){

                // get the type of list<Funcionarios> with reflection
                final Type funcionariosListType = new TypeToken<List<Funcionario>>(){}.getType();

                // the reader
                final Reader reader = new InputStreamReader(is);

                // The json to object convert
                final Gson gson = new GsonBuilder().create();

                // Google Gson Black magic
                theFuncionarios=gson.fromJson(reader, funcionariosListType);


            } catch (IOException e){
                e.printStackTrace();
                return;
            }

            this.funcionarios.postValue(theFuncionarios);
        }) ;


    }
}
























}
