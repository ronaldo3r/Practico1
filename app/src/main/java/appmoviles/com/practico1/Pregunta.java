package appmoviles.com.practico1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class Pregunta extends AppCompatActivity {

    //Vista
    private TextView txtPuntos;
    private TextView txtPregunta;
    private TextView txtDesResp;
    private TextView txtPtGana;
    private TextView txtPtTot;

    private int puntosAcumulados;

    private Button btn_resp;
    private Button btn_refres;
    private Button btn_cancel;

    private RadioGroup opciones;
    private RadioButton opc1;
    private RadioButton opc2;
    private RadioButton opc3;
    private RadioButton opc4;

    //Pregunta

    private int num1;
    private int num2;
    private String[] operadores;
    private int[] opcionesResp;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);

        //Vista
        txtPuntos = findViewById(R.id.txt_puntos);
        txtPregunta = findViewById(R.id.txt_preg);
        txtDesResp = findViewById(R.id.txt_des_resp);
        txtPtGana= findViewById(R.id.txt_pt_gan);
        txtPtTot=findViewById(R.id.txt_tot_pt);

        btn_resp = findViewById(R.id.btn_resp);
        btn_refres = findViewById(R.id.btn_refrescar);
        btn_cancel = findViewById(R.id.btn_cancelar);

        opciones = findViewById(R.id.opc_resp);
        opc1 = findViewById(R.id.radio_opc1);
        opc2 = findViewById(R.id.radio_opc2);
        opc3 = findViewById(R.id.radio_opc3);
        opc4 = findViewById(R.id.radio_opc4);

        //Operadores
        operadores = new String[4];
        operadores[0] = "+";
        operadores[1] = "-";
        operadores[2] = "*";
        operadores[3] = "/";

        refrescar();

    }

    public void refrescar(){

        //Opciones
        opcionesResp = new int[4];
        opcionesResp[0] = -999999;
        opcionesResp[1] = -999999;
        opcionesResp[2] = -999999;
        opcionesResp[3] = -999999;

        generarPreguntaFacil();

        btn_resp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtDesResp.setVisibility(View.VISIBLE);
                txtPtGana.setVisibility(View.VISIBLE);

                if(opc1.isChecked()){
                    if(opc1.getText().equals(""+opcionesResp[0])){

                        txtDesResp.setText("Respuesta Correcta");
                        txtDesResp.setTextColor(Color.GREEN);
                        txtPtGana.setText("Ganaste 1 punto");
                        puntosAcumulados+=1;
                        txtPtTot.setText("Puntos acumulados: "+puntosAcumulados);
                    }else{
                        txtDesResp.setText("Respuesta Incorrecta");
                        txtDesResp.setTextColor(Color.RED);
                        txtPtGana.setText("No ganaste puntos");
                        txtPtTot.setText("Puntos acumulados: "+puntosAcumulados);
                    }
                }
                else if(opc2.isChecked()){
                    if(opc2.getText().equals(""+opcionesResp[0])){

                        txtDesResp.setText("Respuesta Correcta");
                        txtDesResp.setTextColor(Color.GREEN);
                        txtPtGana.setText("Ganaste 1 punto");
                        puntosAcumulados+=1;
                        txtPtTot.setText("Puntos acumulados: "+puntosAcumulados);
                    }else{
                        txtDesResp.setText("Respuesta Incorrecta");
                        txtDesResp.setTextColor(Color.RED);
                        txtPtGana.setText("No ganaste puntos");
                        txtPtTot.setText("Puntos acumulados: "+puntosAcumulados);
                    }
                }
                else if(opc3.isChecked()){
                    if(opc3.getText().equals(""+opcionesResp[0])){

                        txtDesResp.setText("Respuesta Correcta");
                        txtDesResp.setTextColor(Color.GREEN);
                        txtPtGana.setText("Ganaste 1 punto");
                        puntosAcumulados+=1;
                        txtPtTot.setText("Puntos acumulados: "+puntosAcumulados);
                    }else{
                        txtDesResp.setText("Respuesta Incorrecta");
                        txtDesResp.setTextColor(Color.RED);
                        txtPtGana.setText("No ganaste puntos");
                        txtPtTot.setText("Puntos acumulados: "+puntosAcumulados);
                    }
                }
                else{
                    if(opc4.isChecked()){
                        if(opc4.getText().equals(""+opcionesResp[0])){

                            txtDesResp.setText("Respuesta Correcta");
                            txtDesResp.setTextColor(Color.GREEN);
                            txtPtGana.setText("Ganaste 1 punto");
                            puntosAcumulados+=1;
                            txtPtTot.setText("Puntos acumulados: "+puntosAcumulados);
                        }else{
                            txtDesResp.setText("Respuesta Incorrecta");
                            txtDesResp.setTextColor(Color.RED);
                            txtPtGana.setText("No ganaste puntos");
                            txtPtTot.setText("Puntos acumulados: "+puntosAcumulados);
                        }
                    }
                }
            }
        });

        btn_refres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                refrescar();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Pregunta.this, MapsActivity.class);
                startActivity(i);
            }
        });
    }

    public void generarPreguntaFacil() {

        Random aleatorio = new Random();
        int operador = aleatorio.nextInt(4);

        String ope = operadores[operador];


        txtPuntos.setText("Para ganar 1 puntos responde la siguiente pregunta:");

        num1 = (int) aleatorio.nextInt(10);
        num2 = (int) aleatorio.nextInt(10);

        if (ope.equals("+")) {

            int resp = num1 + num2;
            opcionesResp[0] = resp;

            txtPregunta.setText(num1 + " " + ope + " " + num2);

            int i = 1;
            while (i < opcionesResp.length) {

                int opcion = aleatorio.nextInt(19);

                if (i == 2) {

                    if (opcionesResp[0] != opcion && opcionesResp[1] != opcion) {
                        opcionesResp[i] = opcion;
                        i++;
                    }

                } else if (i == 3) {

                    if (opcionesResp[0] != opcion && opcionesResp[1] != opcion && opcionesResp[2] != opcion) {
                        opcionesResp[i] = opcion;
                        i++;
                    }

                } else {
                    if (opcion != resp) {
                        opcionesResp[i] = opcion;
                        i++;
                    }

                }
            }

        } else if (ope.equals("-")) {

            int resp = 0;
            if (num1 >= num2) {
                resp = num1 - num2;
                txtPregunta.setText(num1 + " " + ope + " " + num2);
            } else {
                resp = num2 - num1;
                txtPregunta.setText(num2 + " " + ope + " " + num1);
            }
            opcionesResp[0] = resp;

            int i = 1;
            while (i < opcionesResp.length) {

                int opcion = (int) aleatorio.nextInt(10);

                if (i == 2) {

                    if (opcionesResp[0] != opcion && opcionesResp[1] != opcion) {
                        opcionesResp[i] = opcion;
                        i++;
                    }

                } else if (i == 3) {

                    if (opcionesResp[0] != opcion && opcionesResp[1] != opcion && opcionesResp[2] != opcion) {
                        opcionesResp[i] = opcion;
                        i++;
                    }

                } else {
                    if (opcion != resp) {
                        opcionesResp[i] = opcion;
                        i++;
                    }

                }
            }

        } else if (ope.equals("*")) {

            int resp = num1 * num2;
            opcionesResp[0] = resp;

            txtPregunta.setText(num1 + " " + ope + " " + num2);

            int i = 1;
            while (i < opcionesResp.length) {

                int opcion = aleatorio.nextInt(81);

                if (i == 2) {

                    if (opcionesResp[0] != opcion && opcionesResp[1] != opcion) {
                        opcionesResp[i] = opcion;
                        i++;
                    }

                } else if (i == 3) {

                    if (opcionesResp[0] != opcion && opcionesResp[1] != opcion && opcionesResp[2] != opcion) {
                        opcionesResp[i] = opcion;
                        i++;
                    }

                } else {
                    if (opcion != resp) {
                        opcionesResp[i] = opcion;
                        i++;
                    }

                }
            }

        } else {

            boolean a = false;

            int resp = 0;

            while (!a) {

                if (num1 >= num2) {
                    if (num1 % num2 == 0) {
                        resp = num1 / num2;
                        a = true;
                        txtPregunta.setText(num1 + " " + ope + " " + num2);
                    }
                } else {
                    if (num2 % num1 == 0) {
                        resp = num2 / num1;
                        a = true;
                        txtPregunta.setText(num2 + " " + ope + " " + num1);
                    }
                }

                num1 = (int) aleatorio.nextInt(10);
                num2 = (int) aleatorio.nextInt(10);
            }
            opcionesResp[0] = resp;

            int i = 1;
            while (i < opcionesResp.length) {

                int opcion = (int) aleatorio.nextInt(10);

                if (i == 2) {

                    if (opcionesResp[0] != opcion && opcionesResp[1] != opcion) {
                        opcionesResp[i] = opcion;
                        i++;
                    }

                } else if (i == 3) {

                    if (opcionesResp[0] != opcion && opcionesResp[1] != opcion && opcionesResp[2] != opcion) {
                        opcionesResp[i] = opcion;
                        i++;
                    }

                } else {
                    if (opcion != resp) {
                        opcionesResp[i] = opcion;
                        i++;
                    }

                }
            }
        }


        agregarOpcARadBtn();

    }

    public void agregarOpcARadBtn() {


        int k = 0;
        Random aleatorio = new Random();
        int res = aleatorio.nextInt(4);

        if (res == 0) {
            opc1.setText(""+opcionesResp[0]);
            opc2.setText(""+opcionesResp[1]);
            opc3.setText(""+opcionesResp[2]);
            opc4.setText(""+opcionesResp[3]);

        } else if (res == 1) {
            opc2.setText(""+opcionesResp[0]);
            opc1.setText(""+opcionesResp[1]);
            opc3.setText(""+opcionesResp[2]);
            opc4.setText(""+opcionesResp[3]);
        } else if (res == 2) {
            opc3.setText(""+opcionesResp[0]);
            opc1.setText(""+opcionesResp[1]);
            opc2.setText(""+opcionesResp[2]);
            opc4.setText(""+opcionesResp[3]);
        } else {
            opc4.setText(""+opcionesResp[0]);
            opc1.setText(""+opcionesResp[1]);
            opc2.setText(""+opcionesResp[2]);
            opc3.setText(""+opcionesResp[3]);

        }
    }


}
