package cosw.eci.eatfoodfaster;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class BuyActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        ((TextView)findViewById(R.id.textViewTotalBuy)).append("  "+ShoppingCartActivity.getTotalCarrito());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void swapNombre(View v){
        RadioButton mastercard = (RadioButton)findViewById(R.id.radioButtonMastercard);
        RadioButton visa = (RadioButton)findViewById(R.id.radioButtonVisa);
        if(v.getId() == R.id.radioButtonMastercard){
            visa.setChecked(false);
            mastercard.setChecked(true);
        }else{
            mastercard.setChecked(false);
            visa.setChecked(true);
        }
    }

    public void swapTipo(View v){
        RadioButton debito = (RadioButton)findViewById(R.id.radioButtonDebito);
        RadioButton credito = (RadioButton)findViewById(R.id.radioButtonCredito);
        if(v.getId() == R.id.radioButtonDebito){
            credito.setChecked(false);
            debito.setChecked(true);
        }else{
            debito.setChecked(false);
            credito.setChecked(true);
        }
    }

    public void comprarCarrito(View v){
        Toast.makeText(getApplicationContext(),"comprando",Toast.LENGTH_LONG).show();
    }
}
