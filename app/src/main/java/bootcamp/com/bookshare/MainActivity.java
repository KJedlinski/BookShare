package bootcamp.com.bookshare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.loginField)
    EditText loginField;
    @BindView(R.id.passwordField)
    EditText passwordField;
    @BindView(R.id.rememberLoginData)
    CheckBox rememberUser;

    private SharedPreferences loginData;
    private SharedPreferences.Editor dataEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loginData = getSharedPreferences("loginData", Activity.MODE_PRIVATE);
        dataEditor = loginData.edit();

        if (loginData.getBoolean("Saved", false)) {
            loginField.setText(loginData.getString("Login", ""));
            passwordField.setText(loginData.getString("Password", ""));
        }

    }

    @OnClick(R.id.button)
    public void onLoginButtonClick() {
        logIN();
    }

    private void logIN() {

        //TODO implement login with database and password validation
        String login = loginField.getText().toString();
        String passwd = passwordField.getText().toString();

        Pattern loginPattern = Pattern.compile(".{5,10}+");
        Pattern passwdPattern = Pattern.compile(".{3,8}+");
        Matcher loginMatcher = loginPattern.matcher(login);
        Matcher passwdMatcher = passwdPattern.matcher(passwd);

        if (loginMatcher.matches() && passwdMatcher.matches()) {
            Intent menuActivity = new Intent(this, Menu.class);
            startActivity(menuActivity);
        } else {
            loginField.setText("");
            passwordField.setText("");
            Toast.makeText(this, "Niepoprawny login lub hasło!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rememberUser.isChecked()) saveLoginData(login, passwd);
    }

    public void saveLoginData(String l, String p) {
        dataEditor = loginData.edit();
        dataEditor.putString("Login", l);
        dataEditor.putString("Password", p);
        dataEditor.putBoolean("Saved", true);
        dataEditor.commit();
        Toast.makeText(this, "Dane logowania zapisane!", Toast.LENGTH_SHORT).show();
    }

    public void registerUser(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
