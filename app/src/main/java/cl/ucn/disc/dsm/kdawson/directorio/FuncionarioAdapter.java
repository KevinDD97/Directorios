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

    /*
    * add all the {@link Funcionario} into the Adapter
    *
    * @param theFuncionarios to add
    * */
    public void setFuncionarios(List<Funcionario> theFuncionarios){
        this.funcionarios.addAll(theFuncionarios);
    }

    /*
    * Return a convertview with a holder
    *
    * */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // The holder
        ViewHolder holder;

        // inflate only the rows visibles
        if(convertView == null){

            convertView= this.theInflater.inflate(R.layout.row_funcionario, parent, false);

            // construct the ViewHolder
            holder=new ViewHolder(convertView);

            // save into the convertView
            convertView.setTag(holder);

        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        // assign the values

        final Funcionario funcionario = this.getItem(position);
        //final Funcionario funcionario = this.funcionarios.get(position);
        holder.nombre.setText(funcionario.getNombre());
        holder.email.setText(funcionario.getEmail());
        // TODO: set all the attributes into the holder

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
