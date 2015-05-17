package cosw.eci.eatfoodfaster;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by fercho on 5/7/15.
 */
public class FoodFragment extends Fragment {

    View rootView;

    // List view
    private ListView lv;

    // Listview Adapter
    ArrayAdapter<String> adapter;

    // Search EditText
    EditText inputSearch;

    //Button
    ImageButton imgButton ;

    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;


    ImageButton  searchButton;

    Logica logica ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search,container, false);
        // Listview Data
        String products[] = {"Dell Inspiron", "HTC One X", "HTC Wildfire S", "HTC Sense", "HTC Sensation XE",
                "iPhone 4S", "Samsung Galaxy Note 800",
                "Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro"};


        inputSearch = (EditText) rootView.findViewById(R.id.searchView1);




        // Adding items to listview



        //agregando el listener al boton de buscar
        searchButton = (ImageButton) rootView.findViewById(R.id.imageButton2);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchExpr ;
                EditText t =  (EditText) rootView.findViewById(R.id.searchView1);
                searchExpr = t.getText().toString();
                Toast.makeText( getActivity(),
                        "ImageButton is clicked! searching for " + searchExpr, Toast.LENGTH_SHORT).show();
                logica =  new Logica(rootView , getActivity());
                logica.execute(new String[]{searchExpr});


            }


        });
        //





        return rootView;
    }


}

class Logica extends AsyncTask<String, Void , String> {
    String[] productos;
    View v ;
    FragmentActivity fa;
    public Logica(View v ,FragmentActivity fa ){
        this.v = v;
        this.fa = fa;

    }

    String URI = "http://eatfoodfaster.herokuapp.com/rest/productos/";

    public String readResourceContent(String producto) {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URI + producto);
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
        String res;
        res = this.readResourceContent(params[0]);
        try {
            JSONObject jsonObject;
            JSONArray jsonArray = new JSONArray(res);
            productos =  new String[res.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                System.out.println("objeto con " + jsonArray.getJSONObject(i).getString("descripcion" ));
                productos[i] = jsonArray.getJSONObject(i).getString("descripcion");
            }
        } catch (JSONException e) {
        }
        return "succes";
    }

    @Override
    protected void onPostExecute(String result){
        ListView lv = (ListView) v.findViewById(R.id.listview_search);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(fa,R.layout.listtwo_searchresults, R.id.product_name , productos);
        lv.setAdapter(adapter);
    }

}
