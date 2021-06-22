package com.achmadqomarudin.animatedbottombar.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.achmadqomarudin.animatedbottombar.R;
import com.achmadqomarudin.animatedbottombar.adapter.TransaccionesAdapter;
import com.achmadqomarudin.animatedbottombar.database.ConnectionHelper;
import com.achmadqomarudin.animatedbottombar.database.dbTransacciones;
import com.achmadqomarudin.animatedbottombar.models.Transaccion;

import androidx.appcompat.app.AppCompatActivity;
/**
 * A simple {@link Fragment} subclass.
 */
public class ControlFragment extends Fragment {

    private SimpleDateFormat dateFormat;
    private String date;
    EditText etPlannedDate, eText, TxtImporte, TxtNotas;
    TextView ingresosTxt, gastosTxt, balanceTxt;
    TimePickerDialog picker;
    Spinner s_Tipo, s_Cuenta, s_Categoria;
    ListView lst;
    public ArrayList<Transaccion> listaTransaccion;
    public Double totalIngreso=0.0, totalGastos=0.0, totalBalance=0.0;


    public ControlFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        listaTransaccion = new ArrayList<Transaccion>();
        View vista = inflater.inflate(R.layout.fragment_control, container, false);

        ConnectionHelper connHelper = new ConnectionHelper(this.getContext());//Conexion a la abase de datos
        SQLiteDatabase database = connHelper.getWritableDatabase();

        if(database != null){
            Toast.makeText(this.getContext(), "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this.getContext(), "ERROR AL CREAR BASE DE DATOS", Toast.LENGTH_LONG).show();
        }

        lst = vista.findViewById(R.id.listViewTransacciones);

        try{
            llenarListView();
            cargarIngresos();
            cargarGastos();
            totalBalance = totalIngreso - totalGastos;
            } catch (Exception e)
        {
            Toast.makeText(this.getContext(), "ERROR AL MOSTRAR BASE DE DATOS", Toast.LENGTH_LONG).show();
        }

        ingresosTxt = vista.findViewById(R.id.ingresos_txt);
        ingresosTxt.setText(totalIngreso.toString());

        gastosTxt = vista.findViewById(R.id.gastos_txt);
        gastosTxt.setText(totalGastos.toString());

        balanceTxt = vista.findViewById(R.id.balance_txt);
        balanceTxt.setText(totalBalance.toString());



        //Para el modal
       Dialog modalAdd = new Dialog(this.getContext());
       modalAdd.setContentView(R.layout.agregar_transaccion);

        //**************** obtengo la fecha para el label de control************************
        TextView fecha = (TextView) vista.findViewById(R.id.lbl_fecha);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(Calendar.getInstance().getTime());
        fecha.setText(date);


        //******** Boton agregar**************************************
        Button btnAgregar = (Button) vista.findViewById(R.id.agregar_btn);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

    //************************************* ESTO ES DENTRO DEL MODAL**********************************************

                //Declaro los inputs
                etPlannedDate = (EditText) modalAdd.findViewById(R.id.fecha_txt);
                eText=(EditText) modalAdd.findViewById(R.id.hora_txt);

                //estos son los que vas a usar
                TxtImporte = (EditText) modalAdd.findViewById(R.id.importe_txt);
                TxtNotas = (EditText)modalAdd.findViewById(R.id.notas_txt);

                s_Tipo = (Spinner) modalAdd.findViewById(R.id.spinner_tipo);
                s_Cuenta = (Spinner) modalAdd.findViewById(R.id.spinner_cuenta);
                s_Categoria = (Spinner) modalAdd.findViewById(R.id.spinner_categoria);

                //************************ SELECTOR DE FECHA********************************

                etPlannedDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View modalAdd) {

                        switch (modalAdd.getId()) {
                            case R.id.fecha_txt:
                                showDatePickerDialog();
                                break;

                        }
                     }
                });
       //******************************************* SELECTOR DE HORA ****************************************

                eText.setInputType(InputType.TYPE_NULL);
                eText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar cldr = Calendar.getInstance();
                        int hour = cldr.get(Calendar.HOUR_OF_DAY);
                        int minutes = cldr.get(Calendar.MINUTE);
                        // time picker dialog
                        picker = new TimePickerDialog(getContext(),
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                        eText.setText(sHour + ":" + sMinute);
                                    }
                                }, hour, minutes, true);
                        picker.show();
                    }
                });

                // ********************************************** BOTON GUARDAR *************************************
                Button btnGuardar = (Button)  modalAdd.findViewById(R.id.guardar_btn);
                btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //********************* AQUI VALIDAS LAS ENTRADAS Y GUARDAS EN LA DB**********************************
                        dbTransacciones dbTran = new dbTransacciones(btnGuardar.getContext());
                        try{
                            long id = dbTran.insertarTransaccion(s_Tipo.getSelectedItem().toString(),etPlannedDate.getText().toString(), s_Cuenta.getSelectedItem().toString(), s_Categoria.getSelectedItem().toString(), Double.parseDouble(TxtImporte.getText().toString()), TxtNotas.getText().toString());
                            if(id>0){
                                Toast.makeText(btnGuardar.getContext(), "Registro guardado", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(btnAgregar.getContext(), "Registro NO GUARDADO", Toast.LENGTH_LONG).show();
                            }
                            modalAdd.dismiss();
                        }
                        catch (Exception e){
                            Toast.makeText(btnAgregar.getContext(), "Ingrese todos los campos", Toast.LENGTH_LONG).show();
                        }

                        llenarListView();
                        totalBalance = 0.0;
                        totalIngreso = 0.0;
                        totalGastos = 0.0;
                        cargarIngresos();
                        cargarGastos();
                        totalBalance = totalIngreso - totalGastos;
                        ingresosTxt.setText(totalIngreso.toString());
                        gastosTxt.setText(totalGastos.toString());
                        balanceTxt.setText(totalBalance.toString());
                    }
                });

                modalAdd.show();
            }
        });

        lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SQLiteDatabase database = connHelper.getWritableDatabase();

                Transaccion t = listaTransaccion.get(position);
                int dato = t.idTransaccion;

                int resp = database.delete(ConnectionHelper.TABLA_TRANSACCIONES, "idTran="+dato, null );

                if(resp!= 0){
                    Toast.makeText(getContext(), "Eliminado", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(), "No se pudo eliminar", Toast.LENGTH_LONG).show();
                }
                database.close();

                llenarListView();
                totalBalance = 0.0;
                totalIngreso = 0.0;
                totalGastos = 0.0;
                cargarIngresos();
                cargarGastos();
                totalBalance = totalIngreso - totalGastos;
                ingresosTxt.setText(totalIngreso.toString());
                gastosTxt.setText(totalGastos.toString());
                balanceTxt.setText(totalBalance.toString());

                return false;
            }
        });


        // Inflate the layout for this fragment
        return vista;
    }

    //******************************* METODO PARA EL SELECTOR DE FECHA ********************************************
    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "/" + (month+1) + "/" + year;
                etPlannedDate.setText(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private void llenarListView(){
        ConnectionHelper connHelper = new ConnectionHelper(getContext());//Conexion a la abase de datos
        SQLiteDatabase database = connHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM "+ConnectionHelper.TABLA_TRANSACCIONES, null);
        ArrayList<Transaccion> lista = new ArrayList<Transaccion>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("idTran"));
            String tipo = cursor.getString(cursor.getColumnIndex("tipoTran"));
            String fecha = cursor.getString(cursor.getColumnIndex("fechaTran"));
            String cuenta = cursor.getString(cursor.getColumnIndex("cuentaTran"));
            String categoria = cursor.getString(cursor.getColumnIndex("categoriaTran"));
            Double monto = cursor.getDouble(cursor.getColumnIndex("importeTran"));
            String nota = cursor.getString(cursor.getColumnIndex("notaTran"));

            Transaccion transaccion = new Transaccion(id, tipo, fecha, cuenta, categoria, monto, nota);
            lista.add(transaccion);
        }

        cursor.close();

        TransaccionesAdapter adapter = new TransaccionesAdapter(lista, getContext());
        lst.setAdapter(adapter);
    }

    private void cargarIngresos()
    {
        ConnectionHelper connHelper = new ConnectionHelper(getContext());//Conexion a la abase de datos
        SQLiteDatabase database = connHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM "+ConnectionHelper.TABLA_TRANSACCIONES+" WHERE tipoTran = 'Ingreso'", null);
        ArrayList<Double> ingresos = new ArrayList<Double>();
        while (cursor.moveToNext()){
            double ingresoU = cursor.getDouble(cursor.getColumnIndex("importeTran"));
            ingresos.add(ingresoU);
        }

        database.close();

        for (int i =0; i<ingresos.size(); i++){
            totalIngreso += ingresos.get(i);
        }

    }

    private void cargarGastos() {
        ConnectionHelper connHelper = new ConnectionHelper(getContext());//Conexion a la abase de datos
        SQLiteDatabase database = connHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + ConnectionHelper.TABLA_TRANSACCIONES + " WHERE tipoTran = 'Gasto'", null);
        ArrayList<Double> gastos = new ArrayList<Double>();
        while (cursor.moveToNext()) {
            double gastosU = cursor.getDouble(cursor.getColumnIndex("importeTran"));
            gastos.add(gastosU);
        }

        database.close();

        for (int i = 0; i < gastos.size(); i++) {
            totalGastos += gastos.get(i);
        }
    }

}
