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
import android.widget.ArrayAdapter;

public class ShoppingCartFragment extends ListFragment{

    private static ArrayList<Product> shoppingCart = new ArrayList<>();
    private ArrayAdapter adapter;

    static{
        for(int i =0; i<10;i++){
            shoppingCart.add(new Product(("producto"+i),"franquicia",(10000+i),"http://web-images.chacha.com/images/Quiz/1397/which-pizza-topping-are-you-jul-17-2013-1-600x400.jpg"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
        final ProductAdapter adapter = new ProductAdapter(getActivity(), R.layout.row_layout, shoppingCart);
        setListAdapter(adapter);
        return inflater.inflate(R.layout.shopping_cart_layout, container, false);
    }


}