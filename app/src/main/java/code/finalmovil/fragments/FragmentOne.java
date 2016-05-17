package code.finalmovil.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.sql.SQLException;
import java.util.List;

import code.finalmovil.R;
import code.finalmovil.User;
import code.finalmovil.database.UserDatabaseHelper;
import eventBus.SaveData;
import eventBus.ShowTable;

/**
 * Created by Alberto Mario Camargo Castro on 16-May-16.
 */
public class FragmentOne  extends Fragment {


    EditText firstName, lastName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_one, container, false);

        firstName = (EditText) v.findViewById(R.id.name);
        lastName = (EditText) v.findViewById(R.id.lastName);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onMessageEvent(SaveData event){
        UserDatabaseHelper todoOpenDatabaseHelper = OpenHelperManager.getHelper(getContext(), UserDatabaseHelper.class);
        try {
            Dao<User, Long> userDao = todoOpenDatabaseHelper.getDao();
            userDao.create(new User(firstName.getText().toString(), lastName.getText().toString()));
            firstName.setText("");
            lastName.setText("");

            Toast.makeText(getContext(), "User created", Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post(new ShowTable());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
