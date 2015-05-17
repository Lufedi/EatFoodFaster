package cosw.eci.eatfoodfaster;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class SplashActivity extends Activity implements LocationListener {

    private static int SPLASH_TIME_OUT = 10000;
    private static String localizacion="";
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


    }

    @Override
    public void onLocationChanged(Location location) {
        double lat=location.getLatitude();
        double longt=location.getLongitude();



        String latitud=decimalToGMS(lat,"LATITUD");
        String longitud=decimalToGMS(longt,"LONGITUD");

        String resultado=longitud+latitud;

        if(at.getStatus()==AsyncTask.Status.PENDING) {
            at.execute(new String[]{resultado});
        }
        if(localizacion!="" && !localizacion.contains("404 Not Found")){
            locationManager.removeUpdates(this);

        }

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

       // System.out.println("Convierte Decimales a Grados Minutos Y segundos");


        Grados=(int)(Decimal); //Parte Entera del Decimal

        //System.out.println("Los Grados Son  "+Grados);
        Min= (Decimal-Grados); //Se le resta el resultado para Obtener los Min
        Minutos=Math.abs((int)(Min*60)); //El Anterior se Multiplica por 60 y se obtienen los Minutos
        //System.out.println("Los Minutos   "+Minutos);
        Min1=Math.abs(Min*60); //Se hace el Anterior Proceso con Los Minutos
        seg=Math.abs((Min1-Minutos));
        segundos=(int)(seg*60);
        //System.out.println("Los segundos son    "+segundos);
        //Nota:Los Valores que Tienen Valor Absoluto, se hizo para que el Menos
        //De la Operacion no afectara el Resultado.
        int signo     = (Decimal < 0) ? -1 : 1;
        String direccion = (tipo == "LATITUD") ?
                ((signo > 0) ? "N" : "S") :
                ((signo > 0) ? "E" : "W");
        if (Grados< 0)Grados=Grados*-1;
        //System.out.println("El resultado es  "+Grados+"°" +" "+Minutos+"'"+" "+segundos+"''"+" "+direccion);
        resultado=Grados+"/"+Minutos+"/"+segundos+"/"+direccion+"/";
        return resultado;
    }

    AsyncTask<String, Integer, String> at = new AsyncTask<String, Integer, String>() {
        @Override
        protected void onPreExecute() {
            //lo que se hace antes de crar el hilo secundario. En este caso, no es
            //necesario hacer nada.
        }
        @Override
        protected String doInBackground(String... params) {
            //En este método irá lo que se realizará en el hilo secundario.
            //En este caso, se debe realizar la petición HTTP, esperar por la respuesta,
            //y retornarla. Lo retornado será manejado por el método onPostExecute.
            return readResourceContent(params[0]);
        }

        public String readResourceContent(String resultado) {
            StringBuilder builder = new StringBuilder();
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("https://eatfoodfaster.herokuapp.com/rest/plazoleta/"+resultado);
            try {
                HttpResponse response = client.execute(httpGet);
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();

            }
            return builder.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            //En este método se procesa el resultado del hilo secundario.
            //En este caso, se debe actualizar la vista con el resultado del servicio Web.
            //Tenga en cuenta que para esto deben tener la referencia de la Actividad
            //para poder hacer el ‘findViewById‘

            if(result.contains("404 Not Found")){
                Toast toast;
                toast = Toast.makeText(getApplicationContext(), "Usted no se encuentra en ningun centro comercial :( lo siento T_T", Toast.LENGTH_LONG );
                toast.show();
            }
            else{
                Toast toast;
                toast = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG );
                toast.show();

                localizacion=result;
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }
    };


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast toast;
        toast = Toast.makeText(getApplicationContext(), "Geolocalizador activado :D", Toast.LENGTH_LONG );
        toast.show();


    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast toast;
        toast = Toast.makeText(getApplicationContext(), "Encienda su geolocalizador por favor :(", Toast.LENGTH_LONG );
        toast.show();

    }
}
