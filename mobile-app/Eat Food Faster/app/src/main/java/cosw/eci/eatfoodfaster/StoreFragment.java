package cosw.eci.eatfoodfaster;


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

/**
 * Created by fercho on 5/7/15.
 */
public class StoreFragment extends Fragment {
    String pc=null;
    ArrayList<String> items = new ArrayList<String>();

    View rootView;

    AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
        @Override
        protected String doInBackground(Void... params) {
            try {
                JSONObject jsonObject = new JSONObject(SplashActivity.localizacion);
                jsonObject = jsonObject.getJSONObject("id");
                pc = jsonObject.getString("idPlazoletaComidas");
            } catch (JSONException e) {

            }

            StringBuilder builder = new StringBuilder();
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("https://eatfoodfaster.herokuapp.com/rest/plazoleta/" + pc + "/");
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

            try {
                JSONArray json = new JSONArray(s);
                for(int i=0;i<json.length();i++){
                    items.add(json.getJSONObject(i).getJSONObject("franquicias").getString("idFranquicia"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        task.execute();
        View rootView = inflater.inflate(R.layout.store_fragment_layout,container, false);
        ListView listView = (ListView)rootView.findViewById(android.R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,items);

        listView.setAdapter(adapter);
        return rootView;
    }

}
