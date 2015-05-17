package cosw.eci.eatfoodfaster;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fercho on 5/14/15.
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    Context context;
    int layoutResourceId;
    List<Product> data = null;

    public ProductAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.context = context;
        this.data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        Button button = (Button)row.findViewById(R.id.buttonDeleteSC);
        button.setTag(position);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer index = (Integer) v.getTag();
                Toast.makeText(context,"deleted "+data.get(index).getIdProducto(),Toast.LENGTH_SHORT).show();
                data.remove(index.intValue());
                notifyDataSetChanged();
            }
        });
        ((TextView) row.findViewById(R.id.nombreProd)).setText(data.get(position).getIdProducto());
        ((TextView) row.findViewById(R.id.precioProd)).setText(data.get(position).getPrecio()+"");
        final View finalRow = row;
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
                ((ImageView) finalRow.findViewById(R.id.iconProd)).setImageBitmap(bitmap);
            }
        };
        task.execute(data.get(position).getUrlImagen());
        return row;
    }
}