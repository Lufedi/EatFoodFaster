package cosw.eci.eatfoodfaster;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by jennibarajas on 5/18/15.
 */
public class RegisterActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void crearUsuario(View v) throws JSONException{
        String cor = ((EditText) findViewById(R.id.txtCorreo)).getText().toString();
        String cont = ((EditText) findViewById(R.id.txtPass)).getText().toString();
        String nom = ((EditText) findViewById(R.id.txtNom)).getText().toString();
        String ap = ((EditText) findViewById(R.id.txtApll)).getText().toString();
        String cel = ((EditText) findViewById(R.id.txtCel)).getText().toString();

        JSONObject job = new JSONObject();
        job.put("correoCliente",cor);
        job.put("contrasena",cont);
        job.put("nombre",nom);
        job.put("apellido",ap);
        job.put("celular",cel);

        AsyncTask<JSONObject,Integer,String> clienteAsync = new AsyncTask<JSONObject,Integer,String>(){
            @Override
            protected String doInBackground(JSONObject... params) {
                DefaultHttpClient dhhtpc=new DefaultHttpClient();
                HttpPost postreq=new HttpPost("https://eatfoodfaster.herokuapp.com/rest/clientes/");
                StringEntity se= null;
                try {
                    se = new StringEntity(params[0].toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                se.setContentType("application/json;charset=UTF-8");
                se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
                postreq.setEntity(se);
                String reqResponse="";
                try {
                    HttpResponse httpr=dhhtpc.execute(postreq);
                    reqResponse= EntityUtils.toString(httpr.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(reqResponse.length()==0){
                    Intent in = new Intent(RegisterActivity.this,IndexActivity.class);
                    startActivity(in);
                }
                else{
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(), "error al agregar el Cliente", Toast.LENGTH_LONG );
                    toast.show();
                }
                return reqResponse.length()==0?"Cliente agregado correctamente":"error al agregar el Cliente";
            }
        };
        clienteAsync.execute(job);
    }

    public void loguearse (View mv){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
