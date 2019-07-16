package sg.edu.np.s10177991h.madexercise7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Dbhandler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView3);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MainActivity.this,CreateNewUser.class);
                startActivity(intent);
                return false;
            }
        });



    }
    public void onLogin (View v)

    {
        EditText Usernametxt = findViewById(R.id.editText3);
        EditText Passwordtxt = findViewById(R.id.editText4);

        String username = Usernametxt.getText().toString();
        String password = Passwordtxt.getText().toString();

        SharedPreferences prefs = getSharedPreferences("MY_GLOBAL_PREFS", MODE_PRIVATE);
        String username1 = prefs.getString("USER_NAME", "");
        String password1 = prefs.getString("PASSWORD", "");

        if (username.equals(username1) && password.equals(password1)) {

            Toast tt = Toast.makeText(MainActivity.this, "Login Successful.", Toast.LENGTH_LONG);//notification, cannot customize layout
            tt.show();
        }
        else
        {
            Toast tt = Toast.makeText(MainActivity.this,"Login Failed.", Toast.LENGTH_LONG);//notification, cannot customize layout
            tt.show();
        }


    }
}
