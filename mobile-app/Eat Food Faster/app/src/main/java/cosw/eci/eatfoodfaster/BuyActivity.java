package cosw.eci.eatfoodfaster;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BuyActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        ((TextView)findViewById(R.id.textViewTotalBuy)).append("  "+ShoppingCartActivity.getTotalCarrito()+" "+LoginActivity.email);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void swapNombre(View v){
        RadioButton mastercard = (RadioButton)findViewById(R.id.radioButtonMastercard);
        RadioButton visa = (RadioButton)findViewById(R.id.radioButtonVisa);
        if(v.getId() == R.id.radioButtonMastercard){
            visa.setChecked(false);
            mastercard.setChecked(true);
        }else{
            mastercard.setChecked(false);
            visa.setChecked(true);
        }
    }

    public void swapTipo(View v){
        RadioButton debito = (RadioButton)findViewById(R.id.radioButtonDebito);
        RadioButton credito = (RadioButton)findViewById(R.id.radioButtonCredito);
        if(v.getId() == R.id.radioButtonDebito){
            credito.setChecked(false);
            debito.setChecked(true);
        }else{
            debito.setChecked(false);
            credito.setChecked(true);
        }
    }

    public void comprarCarrito(View v){
        RadioButton mastercard = (RadioButton)findViewById(R.id.radioButtonMastercard);
        RadioButton visa = (RadioButton)findViewById(R.id.radioButtonVisa);
        RadioButton debito = (RadioButton)findViewById(R.id.radioButtonDebito);
        RadioButton credito = (RadioButton)findViewById(R.id.radioButtonCredito);
        EditText numTarjeta = (EditText)findViewById(R.id.editTextNumTarjeta);
        EditText codSeguridad = (EditText)findViewById(R.id.editTextCodSeguridad);
        TextView mensaje = (TextView)findViewById(R.id.textViewTotalBuy);
        JSONObject json = new JSONObject();
        try {
            json.put("numeroTarjeta",numTarjeta.getText().toString());
            json.put("nombreTarjeta",(visa.isChecked())?"Visa":"Mastecard");
            json.put("tipoPago",(debito.isChecked())?"debito":"credito");
            json.put("codigoSeguridad",Integer.parseInt(codSeguridad.getText().toString()));
            json.put("correoUsuario",LoginActivity.email);
            JSONArray idProductos = new JSONArray();
            for (int i =0; i<ShoppingCartActivity.shoppingCart.size();i++){
                JSONObject prod = new JSONObject();
                prod.put("idProductos",ShoppingCartActivity.shoppingCart.get(i).getIdProducto());
                prod.put("sucursalesIdSucursales",ShoppingCartActivity.shoppingCart.get(i).getIdSucursal());
                idProductos.put(prod);
            }
            json.put("idProductos",idProductos);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        AsyncTask<JSONObject,Integer,String> task = new AsyncTask<JSONObject, Integer, String>() {
            @Override
            protected String doInBackground(JSONObject... params) {
                String reqResponse = null;
                try{
                    DefaultHttpClient dhhtpc=new DefaultHttpClient();
                    HttpPost postreq=new HttpPost("https://eatfoodfaster.herokuapp.com/rest/pagos/");
                    StringEntity se=new StringEntity(params[0].toString());
                    se.setContentType("application/json;charset=UTF-8");
                    se.setContentEncoding(new
                            BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
                    postreq.setEntity(se);
                    HttpResponse httpr=dhhtpc.execute(postreq);
                    reqResponse= EntityUtils.toString(httpr.getEntity());
                }catch (Exception e){
                    e.printStackTrace();
                }
                return reqResponse;
            }

            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
        };
        task.execute(json);
    }
}
