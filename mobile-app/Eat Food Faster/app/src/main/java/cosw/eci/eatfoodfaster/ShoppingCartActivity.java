package cosw.eci.eatfoodfaster;

/**
 * Created by fercho on 5/13/15.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingCartActivity extends ActionBarActivity{

    private static ArrayList<Product> shoppingCart = new ArrayList<>();

    public static void agregarAlCarrito(Product product){
        shoppingCart.add(product);
    }

    public static double getTotalCarrito(){
        double total = 0;
        for (int i=0;i<shoppingCart.size();i++)total+=shoppingCart.get(i).getPrecio();
        return total;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ListView listView = (ListView)findViewById(android.R.id.list);
        ArrayAdapter<Product> adapter = new ProductAdapter(this,R.layout.row_layout,shoppingCart);
        listView.setAdapter(adapter);
        listView.invalidateViews();
        calcularTotal();
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                calcularTotal();
            }
        });
    }

    public void calcularTotal(){
        double total = 0;
        for (int i=0;i<shoppingCart.size();i++)total+=shoppingCart.get(i).getPrecio();
        ((TextView)findViewById(R.id.textViewPrice)).setText(total + "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shopping_cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_shopping_cart_checkin){
            if(shoppingCart.isEmpty()){
                Toast.makeText(getApplicationContext(),"No hay productos en el carrito de compras",Toast.LENGTH_LONG).show();
                return true;
            }else{
                Intent i = new Intent(this, BuyActivity.class);
                startActivity(i);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


}