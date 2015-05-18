package cosw.eci.eatfoodfaster;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import cosw.eci.eatfoodfaster.Polling.CustomAlarm;
import cosw.eci.eatfoodfaster.Polling.TimeAlarm;


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


    public static AlarmManager alarmManager;
    public static PendingIntent pendingIntent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search,container, false);


        inputSearch = (EditText) rootView.findViewById(R.id.searchView1);



        //CustomAlarm customAlarm  =  new CustomAlarm();
        alarmManager = (AlarmManager)rootView.getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent =  new Intent(rootView.getContext() , TimeAlarm.class);
        pendingIntent =  PendingIntent.getBroadcast(rootView.getContext() , 0 ,  intent , 0);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP , 60000,60000,pendingIntent);



        // Adding items to listview
        //agregando el listener al boton de buscar
        searchButton = (ImageButton) rootView.findViewById(R.id.imageButton2);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchExpr;
                EditText t = (EditText) rootView.findViewById(R.id.searchView1);
                searchExpr = t.getText().toString();
                Toast.makeText(getActivity(),
                        "ImageButton is clicked! searching for " + searchExpr, Toast.LENGTH_SHORT).show();
                logica = new Logica(rootView, getActivity());
                logica.execute(new String[]{searchExpr});
            }


        });
        //
        return rootView;
    }


}

class Logica extends AsyncTask<String, Void , String> {
    ArrayList<Product> products;
    View v ;
    FragmentActivity fa;
    public Logica(View v ,FragmentActivity fa ){
        this.v = v;
        this.fa = fa;
        products =  new ArrayList<>();

    }

    String URI = "http://eatfoodfaster.herokuapp.com/rest/productos/";

    public String readResourceContent(String producto) {

        String ciudad = "" , cc = "";
        try{
            JSONObject jsonObject =  new JSONObject(SplashActivity.localizacion);
            jsonObject =  jsonObject.getJSONObject("id");
            ciudad =  jsonObject.getString("ciudad") + "/";
            cc = jsonObject.getString("idPlazoletaComidas")+"/";
        }catch(JSONException e){

        }
        
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URI + cc  +ciudad + producto);
        System.out.println("buscando en  " + URI + cc  +ciudad + producto);
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
        Product p;
        res = this.readResourceContent(params[0]);
        try {
            JSONObject jsonObject;
            JSONArray jsonArray = new JSONArray(res);

            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject =  jsonArray.getJSONObject(i);
                p =  new Product(jsonObject.getJSONObject("id").getString("idProductos"),
                                 jsonObject.getJSONObject("id").getInt("sucursalesIdSucursales"),
                                 jsonObject.getJSONObject("sucursales").getJSONObject("franquicias").getString("idFranquicia") ,
                                 jsonObject.getString("descripcion"),
                                 Double.parseDouble(jsonObject.getString("precio")),
                                 jsonObject.getString("urlImagen"));

                System.out.println("objeto con " + jsonArray.getJSONObject(i).getString("descripcion"));
                products.add(p);
            }
        } catch (JSONException e) {
        }
        return "succes";
    }

    @Override
    protected void onPostExecute(String result){
        System.out.println("vamos a sincronizar " );
        ProductoAdapter productAdapter =  new ProductoAdapter(fa,products);
        ListView lv = (ListView) v.findViewById(R.id.listview_search);
        lv.clearChoices();
        lv.setAdapter(productAdapter);

    }
}


class ProductoAdapter extends ArrayAdapter<Product> {

    public ProductoAdapter(Context context, ArrayList<Product> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Product product = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listtwo_searchresults, parent, false);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.product_name);
        TextView price = (TextView) convertView.findViewById(R.id.product_mrpvalue);
        TextView franquicia  = (TextView) convertView.findViewById(R.id.product_mrp);
        Button button = (Button)convertView.findViewById(R.id.add_cart);
        button.setTag(position);
        //ImageView image  = (ImageView)convertView.findViewById(R.id.productImage);


        // Populate the data into the template view using the data object
        name.setText(product.getDescripcion());
        price.setText(product.getPrecio() + "");
        franquicia.setText(product.getFranquicia());
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Integer index = (Integer) v.getTag();
                                          ShoppingCartActivity.agregarAlCarrito(getItem(index));
                                          Toast.makeText(getContext(),"agregado al carrito: "+getItem(index).getDescripcion(),Toast.LENGTH_SHORT).show();
                                      }
                                    }
                                );


            // try {
            // System.out.println("looking for " + product.getUrlImagen());
            //Drawable drawable = this.LoadImageFromWebOperations(product.getUrlImagen());
            //image.setBackground(drawable);
        final View row =  convertView;
        AsyncTask<String, Void, Bitmap> task = new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                String urldisplay = params[0];
                Bitmap mIcon11 = null;
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    mIcon11 = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                return mIcon11;
            }
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                ((ImageView) row.findViewById(R.id.productImage)).setImageBitmap(bitmap);
            }
        };
        task.execute(product.getUrlImagen());

        /*}catch(Exception e){
            image.setImageResource(R.drawable.cart_low);
            System.out.println("por defecto");
        }*/


        // Return the completed view to render on screen
        return convertView;
    }


    public static Drawable LoadImageFromWebOperations(String url) {

        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
    /*public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }*/

}





