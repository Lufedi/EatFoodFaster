package cosw.eci.eatfoodfaster;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.List;

/**
 * Created by fercho on 5/7/15.
 */
public class StoreFragment extends Fragment {
    public static String pc;
    public static ArrayList<String> items=new ArrayList<String>();

    AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
        @Override
        protected String doInBackground(Void... params) {
            StringBuilder builder = new StringBuilder();
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("https://eatfoodfaster.herokuapp.com/rest/plazoleta/" + StoreFragment.pc + "/");
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
                        "GET request failed"+e.getLocalizedMessage());
            }
            return builder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray json = new JSONArray(s);
                for(int i=0;i<json.length();i++){
                    StoreFragment.items.add(json.getJSONObject(i).getJSONObject("franquicias").getString("idFranquicia"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try{
            JSONObject jsonObject =  new JSONObject(SplashActivity.localizacion);
            jsonObject =  jsonObject.getJSONObject("id");
            StoreFragment.pc = jsonObject.getString("idPlazoletaComidas");
        }catch(JSONException e){

        }
        task.execute();
        View rootView = inflater.inflate(R.layout.store_fragment_layout, container, false);
        ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.franquicias_row, items);
        listView.setAdapter(adapter);
        return rootView;
    }
}
 class FranquiciaAdapter extends ArrayAdapter<String>{
     public FranquiciaAdapter(Context context, int resource, List<String> objects) {
         super(context, resource, objects);

     }

     @Override
     public View getView(final int position, View convertView, ViewGroup parent) {
         View row = convertView;
         LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
         row = inflater.inflate(R.layout.franquicias_row, parent, false);
         ((TextView)row.findViewById(R.id.fran)).append(" "+getItem(position));
         return row;
     }
  }


