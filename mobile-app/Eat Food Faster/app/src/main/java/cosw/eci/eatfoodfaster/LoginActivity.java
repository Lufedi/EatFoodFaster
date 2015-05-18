package cosw.eci.eatfoodfaster;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class LoginActivity extends Activity {

    public static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void iniciarSesion(View v){
        Intent i = new Intent(this, IndexActivity.class);
        email = ((EditText)findViewById(R.id.editTextUser)).getText().toString();
        startActivity(i);
        finish();
    }
}
