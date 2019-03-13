package appmoviles.com.practico1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_CODE = 11;
    private GoogleMap mapa;
    private LocationManager manager;

    private Marker inicio;
    private LatLng actual;

    private boolean first;

    //Saman

    private LatLng arrIzqS;
    private LatLng arrDerS;
    private LatLng abaDerS;
    private LatLng abaIzqS;

    //Edificio C

    private LatLng arrIzqC;
    private LatLng arrDerC;
    private LatLng abaDerC;
    private LatLng abaIzqC;

    //Biblioteca

    private LatLng arrIzqB;
    private LatLng arrDerB;
    private LatLng abaDerB;
    private LatLng abaIzqB;

    //Botones
    private Button btn_preg_facil;
    private Button btn_preg_dificil;
    private Button btn_canje;

    private boolean estoyUbi;

    //Poligonos
    private Polygon polB;
    private Polygon polS;
    private Polygon polC;

    //Prueba
    private Polygon polP;

    private LatLng arrIzqP;
    private LatLng arrDerP;
    private LatLng abaDerP;
    private LatLng abaIzqP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        btn_canje=findViewById(R.id.btn_cupon);
        btn_preg_facil=findViewById(R.id.btn_preg_facil);
        btn_preg_dificil=findViewById(R.id.btn_preg_dificil);

        //Saman

        arrIzqS= new LatLng(3.341912,-76.530593);
        arrDerS= new LatLng(3.341889,-76.530347);
        abaDerS= new LatLng(3.341682,-76.530341);
        abaIzqS= new LatLng(3.341707,-76.530604);

        //EdificioC
        arrIzqC= new LatLng(3.341248,-76.530422);
        arrDerC= new LatLng(3.341241,-76.529864);
        abaDerC= new LatLng(3.341045,-76.529848);
        abaIzqC= new LatLng(3.341064,-76.530449);

        //Biblioteca
        arrIzqB= new LatLng(3.341934,-76.530084);
        arrDerB= new LatLng(3.341948,-76.529789);
        abaDerB= new LatLng(3.341650,-76.529789);
        abaIzqB= new LatLng(3.341664,-76.530095);

        //Prueba
        arrIzqP= new LatLng(3.342644,-76.530561);
        arrDerP= new LatLng(3.342655,-76.529987);
        abaDerP= new LatLng(3.342462,-76.529993);
        abaIzqP= new LatLng(3.342419,-76.530636);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, REQUEST_CODE);

        polB= mapa.addPolygon(new PolygonOptions().add(arrIzqB,abaIzqB,abaDerB,arrDerB));
        polS= mapa.addPolygon(new PolygonOptions().add(arrIzqS,abaIzqS,abaDerS,arrDerS));
        polC= mapa.addPolygon(new PolygonOptions().add(arrIzqC,abaIzqC,abaDerC,arrDerC));

        polP= mapa.addPolygon(new PolygonOptions().add(arrIzqP,abaIzqP,abaDerP,arrDerP));


        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.e(">>>", "LAT: " + location.getLatitude() + " , LONG: " + location.getLongitude());

                if (first==false) {
                    inicio = mapa.addMarker(new MarkerOptions()
                            .position(new LatLng(location.getLatitude(), location.getLongitude()))
                            .title("Mi posición actual").icon(BitmapDescriptorFactory.fromResource(R.drawable.iconperson))
                    );
                    mapa.moveCamera(CameraUpdateFactory
                            .newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 18));
                    first=true;
                }else{

                    inicio.remove();
                    inicio = mapa.addMarker(new MarkerOptions()
                            .position(new LatLng(location.getLatitude(), location.getLongitude()))
                            .title("Mi posición actual").icon(BitmapDescriptorFactory.fromResource(R.drawable.iconperson))
                    );
                    mapa.moveCamera(CameraUpdateFactory
                            .newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 18));

                    Toast.makeText(getApplicationContext(), "Ubicación actual actualizada", Toast.LENGTH_LONG).show();

                }

                estoyUbi=estoyEnZona();

                if(estoyUbi){

                    btn_preg_facil.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(MapsActivity.this, Pregunta.class);
                            startActivity(i);
                        }
                    });

                    btn_preg_dificil.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent i = new Intent(MapsActivity.this, PreguntaDif.class);
                            startActivity(i);
                        }
                    });

                    btn_canje.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent i = new Intent(MapsActivity.this, Canje.class);
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });

    }

    public boolean estoyEnZona(){

        boolean estoy=false;

        //Saman
        actual=inicio.getPosition();

        if((actual.latitude<=arrIzqS.latitude)&&(actual.latitude>=abaDerS.latitude)){
            if((actual.longitude>=arrIzqS.longitude)&&(actual.longitude<=abaDerS.longitude)){

                btn_preg_dificil.setVisibility(View.VISIBLE);

                estoy=true;

            }
        }

        //Edificio C

        if((actual.latitude<=arrIzqC.latitude)&&(actual.latitude>=abaDerC.latitude)){
            if((actual.longitude>=arrIzqC.longitude)&&(actual.longitude<=abaDerC.longitude)){


                btn_preg_facil.setVisibility(View.VISIBLE);

                estoy=true;
            }
        }

        //Biblioteca

        if((actual.latitude<=arrIzqB.latitude)&&(actual.latitude>=abaDerB.latitude)){
            if((actual.longitude>=arrIzqB.longitude)&&(actual.longitude<=abaDerB.longitude)){


                btn_canje.setVisibility(View.VISIBLE);

                estoy=true;
            }
        }

        //Prueba

        if((actual.latitude<=arrIzqP.latitude)&&(actual.latitude>=abaDerP.latitude)){
            if((actual.longitude>=arrIzqP.longitude)&&(actual.longitude<=abaDerP.longitude)){


                btn_preg_dificil.setVisibility(View.VISIBLE);

                estoy=true;
            }
        }

        return estoy;

    }
}
