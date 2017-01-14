package bootcamp.com.bookshare;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Menu extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        fragmentManager = getFragmentManager();
    }

    @OnClick(R.id.logoutButton)
    public void logOUT(){
        Intent logout = new Intent(this, MainActivity.class);
        startActivity(logout);
    }

    @OnClick(R.id.myBooksButton)
    public void onMyBooksButtonClick(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new MyBooks()).commit();
    }

    @OnClick(R.id.libraryButton)
    public void onLibraryButtonClick(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new Library()).commit();
    }

    @OnClick(R.id.allUsersButton)
    public void  onAllUsersButtonClick(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new Users());
        fragmentTransaction.commit();
    }

}
