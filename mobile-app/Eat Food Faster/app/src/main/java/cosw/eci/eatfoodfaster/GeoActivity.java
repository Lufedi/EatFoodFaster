package cosw.eci.eatfoodfaster;

import android.os.Bundle;
import android.view.Menu;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class GeoActivity extends Activity implements LocationListener{





    @Override
    public void onLocationChanged(Location location) {
        double lat=location.getLatitude();
        double longt=location.getLongitude();
        TextView tvlat=(TextView)findViewById(R.id.lat);
        TextView tvlong=(TextView)findViewById(R.id.lon);
        tvlat.setText(lat+"");
        tvlong.setText(longt+"");

        TextView tvlatgms=(TextView)findViewById(R.id.gmsLat);
        TextView tvlonggms=(TextView)findViewById(R.id.gmsLon);

        String latitud=decimalToGMS(lat,"LATITUD");
        String longitud=decimalToGMS(longt,"LONGITUD");

        tvlatgms.setText(latitud+"");
        tvlonggms.setText(longitud+"");




    }

    /**
     *
     * @param Decimal: El valor decimal
     * @param tipo: El tipo es o LATITUD O LONGITUD
     */

    String decimalToGMS(double Decimal, String tipo){

        double Min,seg,Min1;
        int Grados, Minutos,segundos;
        String resultado;

        System.out.println("Convierte Decimales a Grados Minutos Y segundos");


        Grados=(int)(Decimal); //Parte Entera del Decimal

        System.out.println("Los Grados Son  "+Grados);
        Min= (Decimal-Grados); //Se le resta el resultado para Obtener los Min
        Minutos=Math.abs((int)(Min*60)); //El Anterior se Multiplica por 60 y se obtienen los Minutos
        System.out.println("Los Minutos   "+Minutos);
        Min1=Math.abs(Min*60); //Se hace el Anterior Proceso con Los Minutos
        seg=Math.abs((Min1-Minutos));
        segundos=(int)(seg*60);
        System.out.println("Los segundos son    "+segundos);
        //Nota:Los Valores que Tienen Valor Absoluto, se hizo para que el Menos
        //De la Operacion no afectara el Resultado.
        int signo     = (Decimal < 0) ? -1 : 1;
        String direccion = (tipo == "LATITUD") ?
                ((signo > 0) ? "N" : "S") :
                ((signo > 0) ? "E" : "W");
        if (Grados< 0)Grados=Grados*-1;
        System.out.println("El resultado es  "+Grados+"°" +" "+Minutos+"'"+" "+segundos+"''"+" "+direccion);
        resultado="El resultado es  "+Grados+"°" +" "+Minutos+"'"+" "+segundos+"''"+" "+direccion;
        return resultado;
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        TextView tvlat=(TextView)findViewById(R.id.lat);
        TextView tvlong=(TextView)findViewById(R.id.lon);
        tvlat.setText("0-0-0");
        tvlong.setText("0-0-0");


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

}