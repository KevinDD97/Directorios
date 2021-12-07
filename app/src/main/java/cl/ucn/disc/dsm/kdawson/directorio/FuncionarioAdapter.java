package cl.ucn.disc.dsm.kdawson.directorio;

/**
 *
 * The Adapter of Funcionario.
 *
 * @author Kevin Dawson D
 *
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;/

public final class FuncionarioAdapter extends BaseAdapter {

    private final List<Funcionario> funcionarios=new ArrayList<>();

    /*
    * The Inflater
    * */

    private LayoutInflater theInflater;


    /*
    * Constructor of the FuncionarioAdapter.
    * @param context to use
    * */
    public FuncionarioAdapter(Context context){

        // get the inflater
        this.theInflater = LayoutInflater.from(context);


    }

    /*
    * @return the size of the list of {@link Funcionario}.
    * */

    @Override
    public int getCount() {
        return this.funcionarios.size();
    }

    /*
    *Return the {@link Funcionario} at the position i in the list
    *@param i
    *@return the {@link Funcionario}

    * */
    @Override
    public Funcionario getItem(int position) {
        return this.funcionarios.get(position);
    }



    /*
    *
    *@param position to get
    *@return
    *
    * */

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    /*
     * The ViewHolder
     *
     *
     **/


    private static class ViewHolder{

        // TODO: Add all attributes!

        TextView nombre;
        TextView email;

        ViewHolder(View view){

            this.nombre =view.findViewById(R.id.rf_tv_nombre);
            this.email=view.findViewById(R.id.rf_tv_email);



        }
    }
}
