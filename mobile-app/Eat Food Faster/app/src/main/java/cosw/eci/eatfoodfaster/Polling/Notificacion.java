package cosw.eci.eatfoodfaster.Polling;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cosw.eci.eatfoodfaster.FoodFragment;
import cosw.eci.eatfoodfaster.Product;
import cosw.eci.eatfoodfaster.R;
import cosw.eci.eatfoodfaster.SplashActivity;

/**
 * Created by luisfediaz on 18/05/15.
 */
public class Notificacion {



    public void verificarEstadoPedido(int idPedido){

    }


}



class LogicaNotificacion extends AsyncTask<String, Void , String> {
    Context context;
    public LogicaNotificacion(Context context){
        this.context =  context;
    }

    String URI = "http://eatfoodfaster.herokuapp.com/rest/pedidos/estado/";

    public String readResourceContent(String idPedido) {

        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URI + idPedido);
        System.out.println("Consultado estado del pedido " + URI + idPedido);
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
            System.out.println("error en el GET" );
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error en el GET" );
        }
        return builder.toString();
    }


    public void consultarProducto(String nombre) {

    }

    public void consultarProducto(String nombre, String plazoleta) {

    }




    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... params) {
        String estado =  this.readResourceContent(params[0]);

        try{
            JSONObject jsonObject =  new JSONObject(estado);
            estado =  jsonObject.getString("estado");
            if(estado.equals(EstadoPedido.NOTIFICADOACLIENTE)){
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Pedido listo");
                alertDialog.setMessage("Su pedido esta  listo, puede pasar a caja y recogerlo");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                            //detener el polling
                    }
                });
                alertDialog.setIcon(R.drawable.icon);
                alertDialog.show();

                pedidoRecibido();
            }
        }catch(JSONException e){

        }
        return "succes";
    }

    public void pedidoRecibido(){

        try {
        //construir un objeto jSON
        JSONObject jso=new JSONObject();
        jso.put("estado",EstadoPedido.RECIBIDOPORCLIENTE);

        DefaultHttpClient dhhtpc=new DefaultHttpClient();
        HttpPost postreq=new HttpPost("https://LAURLDELRECURSO");
//agregar la versión textual del documento jSON a la petición
        StringEntity se=new StringEntity(jso.toString());
        se.setContentType("application/json;charset=UTF-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
        postreq.setEntity(se);
//ejecutar la petición
        HttpResponse httpr=dhhtpc.execute(postreq);
//Para obtener la respuesta:
        String reqResponse= EntityUtils.toString
                (httpr.getEntity());
        }catch(Exception e){

        }

        detenerAlarma();
    }

    private void detenerAlarma(){
        AlarmManager alarmMgr = FoodFragment.alarmManager;
        PendingIntent alarmIntent = FoodFragment.pendingIntent;
        // If the alarm has been set, cancel it.
        if (alarmMgr!= null) {
            alarmMgr.cancel(alarmIntent);
        }
    }
    @Override
    protected void onPostExecute(String result){
        //cambiar el estado del pedido a recibido por el cliente


    }
}
