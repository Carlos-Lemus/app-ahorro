package com.achmadqomarudin.animatedbottombar.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.achmadqomarudin.animatedbottombar.R;

import androidx.appcompat.app.AppCompatActivity;
/**
 * A simple {@link Fragment} subclass.
 */
public class ControlFragment extends Fragment {

    private SimpleDateFormat dateFormat;
    private String date;
    EditText etPlannedDate, eText, TxtImporte, TxtNotas;
    TimePickerDialog picker;
    Spinner s_Tipo, s_Cuenta, s_Categoria;


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

        View vista = inflater.inflate(R.layout.fragment_control, container, false);

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
                        modalAdd.dismiss();
                    }
                });

                modalAdd.show();
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
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                etPlannedDate.setText(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
}
