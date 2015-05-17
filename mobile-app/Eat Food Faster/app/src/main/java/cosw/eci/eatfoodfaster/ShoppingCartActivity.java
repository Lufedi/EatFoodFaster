package cosw.eci.eatfoodfaster;

/**
 * Created by fercho on 5/13/15.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShoppingCartActivity extends ActionBarActivity{

    private static ArrayList<Product> shoppingCart = new ArrayList<>();
    private ArrayAdapter adapter;

    static{
        for(int i =0; i<10;i++){
            shoppingCart.add(new Product(("producto"+i),"franquicia",(10000+i),"http://web-images.chacha.com/images/Quiz/1397/which-pizza-topping-are-you-jul-17-2013-1-600x400.jpg"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart_layout);
        ListView listView = (ListView)findViewById(android.R.id.list);
        adapter = new ProductAdapter(this,R.layout.row_layout,shoppingCart);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shopping_cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

}