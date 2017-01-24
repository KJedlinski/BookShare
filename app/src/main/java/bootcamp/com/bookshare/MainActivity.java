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

import bootcamp.com.bookshare.DbCommunication.User;
import bootcamp.com.bookshare.DbCommunication.UserDAO;
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
        boolean passwdOk = false;
        String login = loginField.getText().toString();
        String passwd = passwordField.getText().toString();

        Pattern loginPattern = Pattern.compile(".{5,10}+");
        Pattern passwdPattern = Pattern.compile(".{3,8}+");
        Matcher loginMatcher = loginPattern.matcher(login);
        Matcher passwdMatcher = passwdPattern.matcher(passwd);

        if (!loginMatcher.matches()) {
            loginField.setText("");
            passwordField.setText("");
            Toast.makeText(this, "Login musi mieć od 5 do 10 znaków!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!passwdMatcher.matches()) {
            loginField.setText("");
            passwordField.setText("");
            Toast.makeText(this, "Hasło musi mieć od 3 do 8 znaków!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            User user = new User(login,null,passwd,null);
            passwdOk = performAuthorization(user);
            if (rememberUser.isChecked() && passwdOk) saveLoginData(login, passwd);
            if (passwdOk) {
                Intent menuActivity = new Intent(this, Menu.class);
                startActivity(menuActivity);
            }
        }
    }

    public boolean performAuthorization(User user) {

        UserDAO.authorizeUser(user, this);
        if (!user.isAuthorizedLogin()){
            loginField.setText("");
            passwordField.setText("");
            Toast.makeText(this, "Użytkownik o podanym loginie nie istnieje!", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!user.isAuthorizedPasswd()){
            loginField.setText("");
            passwordField.setText("");
            Toast.makeText(this, "Wpisałeś błędne hasło. Spróbuj ponownie.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
