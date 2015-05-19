package cosw.eci.eatfoodfaster;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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


public class LoginActivity extends Activity {

    public static String email;
    public static String contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void iniciarSesion(View v) {
        email = ((EditText) findViewById(R.id.editTextUser)).getText().toString();
        contra = ((EditText) findViewById(R.id.editTextPasswd)).getText().toString();

        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                StringBuilder builder = new StringBuilder();
                HttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet("https://eatfoodfaster.herokuapp.com/rest/clientes/" + email + "/");
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
                    Log.e(PedidoFragment.class.toString(),
                            "GET request failed" + e.getLocalizedMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(PedidoFragment.class.toString(),
                            "GET request failed" + e.getLocalizedMessage());
                }
                return builder.toString();
            }

            @Override
            protected void onPostExecute(String s) {
                String co=null;
                String ps=null;
                try{
                    JSONObject json = new JSONObject(s);
                    co = json.getString("correoCliente");
                    ps = json.getString("contrasena");
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(email.equals(co) && contra.equals(ps)){
                    Intent i = new Intent(LoginActivity.this, IndexActivity.class);
                    startActivity(i);
                }
                else{
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(), "usuario y/o contraseña incorrectos", Toast.LENGTH_LONG );
                    toast.show();
                }
            }
        };
        task.execute();
    }
    public void registrarse (View mv){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
