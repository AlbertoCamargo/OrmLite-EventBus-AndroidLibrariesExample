package code.finalmovil;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import code.finalmovil.database.UserDatabaseHelper;
import code.finalmovil.fragments.FragmentOne;
import code.finalmovil.fragments.FragmentThree;
import code.finalmovil.fragments.FragmentTwo;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // get fragment manager
        FragmentManager fm = getSupportFragmentManager();


        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.HolderFragment2, new FragmentOne());
        ft.add(R.id.HolderFragment3, new FragmentTwo());
        ft.add(R.id.HolderFragment1, new FragmentThree());

        ft.commit();
      

    }



    private void testOutOrmLiteDatabase() throws SQLException {
        UserDatabaseHelper todoOpenDatabaseHelper = OpenHelperManager.getHelper(this, UserDatabaseHelper.class);

        Dao<User, Long> userDao = todoOpenDatabaseHelper.getDao();


        userDao.create(new User("Todo Example 1", "Todo Example 1 Description"));
        userDao.create(new User("Todo Example 2", "Todo Example 2 Description"));
        userDao.create(new User("Todo Example 3", "Todo Example 3 Description"));

        List<User> users = userDao.queryForAll();
        for (int i = 0; i < users.size() ; i++) {
            Log.d("DEBUGG", String.valueOf(users.get(i).getFirstName()));
        }
    }
}
