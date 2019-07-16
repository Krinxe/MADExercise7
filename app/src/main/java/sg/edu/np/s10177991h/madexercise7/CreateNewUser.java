package sg.edu.np.s10177991h.madexercise7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateNewUser extends AppCompatActivity
{
    Button button;
    Dbhandler dbhandler;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_user);
        dbhandler = new Dbhandler(this,null,null,1);
        button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() { // VERY IMPORTANT!!
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CreateNewUser.this,MainActivity.class);
                startActivity(intent);
            }
        });




    }
    public void onCreateNewUser (View v) {
        EditText Usernametxt = findViewById(R.id.editText3);
        EditText Passwordtxt = findViewById(R.id.editText4);

        String username = Usernametxt.getText().toString();
        String password = Passwordtxt.getText().toString();

        Pattern passpattern = Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*+=])(?=.*[a-zA-Z]).{1,}$");
        Pattern userpattern = Pattern.compile("^[a-zA-Z0-9]{6,12}$");

        Matcher pwmatch = passpattern.matcher(password);
        Matcher usermatch = userpattern.matcher(username);

        if (pwmatch.matches() == true && usermatch.matches() == true) {

            //save data of User Name
            SharedPreferences.Editor editor = getSharedPreferences("MY_GLOBAL_PREFS", MODE_PRIVATE).edit();
            editor.putString("USER_NAME", username);
            editor.putString("PASSWORD", password);
            editor.apply();

            Account a = new Account(username, password);
            dbhandler.addAccount(a);
            Toast tt = Toast.makeText(CreateNewUser.this, "New user has been created successfully.", Toast.LENGTH_LONG);//notification, cannot customize layout
            tt.show();
        } else {
            Toast tt = Toast.makeText(CreateNewUser.this, "Invalid User Creation. Please Try Again.", Toast.LENGTH_LONG);//notification, cannot customize layout
            tt.show();
        }
    }





}
