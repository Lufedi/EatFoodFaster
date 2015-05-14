package cosw.eci.eatfoodfaster;

/**
 * Created by fercho on 5/13/15.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class ShoppingCartFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
        for(int i=0;i<30;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("nombre", "Country : " + i);
            hm.put("precio", ""+i);
            aList.add(hm);
        }
        String[] from = {"nombre","precio"};
        int[] to = {R.id.nombre, R.id.precio};
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.row_layout, from, to);
        setListAdapter(adapter);
        return inflater.inflate(R.layout.shopping_cart_layout, container, false);
    }
}