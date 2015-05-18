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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by fercho on 5/18/15.
 */
public class PedidoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pedido,container, false);
        ListView listView = (ListView)rootView.findViewById(android.R.id.list);
        ArrayAdapter<Integer> adapter = new PedidoAdapter(getActivity(),R.layout.pedido_row,BuyActivity.pedidos);
        listView.setAdapter(adapter);
        return rootView;
    }
}

class PedidoAdapter extends ArrayAdapter<Integer>{

    public PedidoAdapter(Context context, int resource, List<Integer> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        row = inflater.inflate(R.layout.pedido_row, parent, false);
        final View finalRow = row;
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                StringBuilder builder = new StringBuilder();
                HttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet("http://eatfoodfaster.herokuapp.com/rest/pedidos/"+getItem(position));
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
                try{
                    JSONObject json = new JSONObject(s);
                    ((TextView) finalRow.findViewById(R.id.idPedido)).append(" "+json.getInt("idPedidos"));
                    ((TextView) finalRow.findViewById(R.id.estadoPedido)).append(" "+json.getString("estadoPedido"));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        task.execute();
        return row;
    }
}
