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

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class Canje extends AppCompatActivity {

    private TextView txt_punt_total;
    private TextView txt_desc_canje;
    private TextView txt_codigo;

    private RadioGroup rg_premios;
    private RadioButton rb_pre1;
    private RadioButton rb_pre2;
    private RadioButton rb_pre3;
    private RadioButton rb_pre4;
    private RadioButton rb_pre5;

    private Button btn_canje;
    private Button btn_cancel;
    private Button btn_guardar;

    private int puntosAcumulados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canje);

        txt_punt_total=findViewById(R.id.txt_tot_pt);
        txt_desc_canje=findViewById(R.id.txt_des_canje);
        txt_codigo=findViewById(R.id.txt_codigo);

        rg_premios=findViewById(R.id.opc_pre);
        rb_pre1=findViewById(R.id.radio_pre1);
        rb_pre2=findViewById(R.id.radio_pre2);
        rb_pre3=findViewById(R.id.radio_pre3);
        rb_pre4=findViewById(R.id.radio_pre4);
        rb_pre5=findViewById(R.id.radio_pre5);

        btn_canje=findViewById(R.id.btn_canje);
        btn_cancel=findViewById(R.id.btn_cancelar);
        btn_guardar=findViewById(R.id.btn_guardar);

        Intent i=getIntent();
        puntosAcumulados=i.getExtras().getInt("Puntos");

        refrescar();
    }

    public void refrescar(){

        txt_punt_total.setText("Puntos acumulados: "+puntosAcumulados);

        btn_canje.setVisibility(View.VISIBLE);
        btn_guardar.setVisibility(View.INVISIBLE);
        txt_desc_canje.setVisibility(View.VISIBLE);

        btn_canje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt_desc_canje.setVisibility(View.VISIBLE);

                if(rb_pre1.isChecked()){

                    if(puntosAcumulados>=20){
                        puntosAcumulados-=20;
                        txt_punt_total.setText("Puntos acumulados: "+puntosAcumulados);
                        txt_desc_canje.setText("Tu código para reclamar el lapicero es:");
                        txt_codigo.setVisibility(View.VISIBLE);
                        txt_codigo.setText(generarCodigo());
                        btn_guardar.setVisibility(View.VISIBLE);
                        btn_canje.setVisibility(View.INVISIBLE);
                    }
                    else{
                        txt_desc_canje.setText("No tienes suficientes puntos para canjear el lapicero");
                        txt_codigo.setVisibility(View.INVISIBLE);
                    }
                }
                else if(rb_pre2.isChecked()){

                    if(puntosAcumulados>=30){
                        puntosAcumulados-=30;
                        txt_punt_total.setText("Puntos acumulados: "+puntosAcumulados);
                        txt_desc_canje.setText("Tu código para reclamar el cuaderno es:");
                        txt_codigo.setVisibility(View.VISIBLE);
                        txt_codigo.setText(generarCodigo());
                        btn_guardar.setVisibility(View.VISIBLE);
                        btn_canje.setVisibility(View.INVISIBLE);
                    }
                    else{
                        txt_desc_canje.setText("No tienes suficientes puntos para canjear el cuaderno");
                        txt_codigo.setVisibility(View.INVISIBLE);
                    }
                }
                else if(rb_pre3.isChecked()){

                    if(puntosAcumulados>=40){
                        puntosAcumulados-=40;
                        txt_punt_total.setText("Puntos acumulados: "+puntosAcumulados);
                        txt_desc_canje.setText("Tu código para reclamar la libreta es:");
                        txt_codigo.setVisibility(View.VISIBLE);
                        txt_codigo.setText(generarCodigo());
                        btn_guardar.setVisibility(View.VISIBLE);
                        btn_canje.setVisibility(View.INVISIBLE);
                    }
                    else{
                        txt_desc_canje.setText("No tienes suficientes puntos para canjear la libreta");
                        txt_codigo.setVisibility(View.INVISIBLE);
                    }
                }
                else if(rb_pre4.isChecked()){

                    if(puntosAcumulados>=80){
                        puntosAcumulados-=80;
                        txt_punt_total.setText("Puntos acumulados: "+puntosAcumulados);
                        txt_desc_canje.setText("Tu código para reclamar la camiseta es:");
                        txt_codigo.setVisibility(View.VISIBLE);
                        txt_codigo.setText(generarCodigo());
                        btn_guardar.setVisibility(View.VISIBLE);
                        btn_canje.setVisibility(View.INVISIBLE);
                    }
                    else{
                        txt_desc_canje.setText("No tienes suficientes puntos para canjear la camiseta");
                        txt_codigo.setVisibility(View.INVISIBLE);
                    }
                }else{
                    if(puntosAcumulados>=100){
                        puntosAcumulados-=100;
                        txt_punt_total.setText("Puntos acumulados: "+puntosAcumulados);
                        txt_desc_canje.setText("Tu código para reclamar el saco es:");
                        txt_codigo.setVisibility(View.VISIBLE);
                        txt_codigo.setText(generarCodigo());
                        btn_guardar.setVisibility(View.VISIBLE);
                        btn_canje.setVisibility(View.INVISIBLE);
                    }
                    else{
                        txt_desc_canje.setText("No tienes suficientes puntos para canjear el saco");
                        txt_codigo.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                refrescar();
                txt_desc_canje.setVisibility(View.INVISIBLE);
                txt_codigo.setText("Guardaste el código y puedes ir a la tienda Icesi a reclamar tu premio");
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(Canje.this, MapsActivity.class);
                i.putExtra("Puntos",puntosAcumulados);
                startActivity(i);
                Canje.this.finish();

            }
        });

        //txt_desc_canje.setVisibility(View.INVISIBLE);
        //txt_codigo.setText(View.INVISIBLE);

    }

    public String generarCodigo(){

        SecureRandom random = new SecureRandom();
        String text = new BigInteger(100, random).toString(32);
        return text;
    }
}
