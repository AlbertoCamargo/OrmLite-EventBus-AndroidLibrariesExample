package code.finalmovil.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import org.greenrobot.eventbus.EventBus;

import java.sql.SQLException;
import java.util.List;

import code.finalmovil.R;
import code.finalmovil.User;
import code.finalmovil.database.UserDatabaseHelper;
import eventBus.SaveData;
import eventBus.ShowTable;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Alberto Mario Camargo Castro on 16-May-16.
 */
public class FragmentThree  extends Fragment {

    TableLayout tl;
    View v;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v =  inflater.inflate(R.layout.fragment_three, container, false);

        setHeader();


        try {
            addToTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return v;
    }

    private void setHeader() {
        tl = (TableLayout) v.findViewById(R.id.main_table);
        TableRow tr_head = new TableRow(getContext());
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));


        TextView label_date2 = new TextView(getContext());
        label_date2.setText("id");
        label_date2.setTextColor(Color.WHITE);
        label_date2.setPadding(5, 5, 5, 5);
        tr_head.addView(label_date2);// add the column to the table row here


        TextView label_date = new TextView(getContext());
        label_date.setText("First name");
        label_date.setTextColor(Color.WHITE);
        label_date.setPadding(5, 5, 5, 5);
        tr_head.addView(label_date);// add the column to the table row here

        TextView label_weight_kg = new TextView(getContext());
        //label_weight_kg.setId(60);// define id that must be unique
        label_weight_kg.setText("Last Name"); // set the text for the header
        label_weight_kg.setTextColor(Color.WHITE); // set the color
        label_weight_kg.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_weight_kg); // add the column to the table row here

        tl.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
    }


    private void addToTable() throws SQLException {


        UserDatabaseHelper todoOpenDatabaseHelper = OpenHelperManager.getHelper(getContext(), UserDatabaseHelper.class);

        Dao<User, Long> userDao = todoOpenDatabaseHelper.getDao();

        List<User> users = userDao.queryForAll();
        for (int i = 0; i < users.size() ; i++) {
            TableRow tr = new TableRow(getContext());

            if (i % 2 != 0) tr.setBackgroundColor(Color.GRAY);
            tr.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView labelId = new TextView(getContext());
            labelId.setText(users.get(i).getId().toString());
            labelId.setPadding(2, 0, 5, 0);
            labelId.setTextColor(Color.BLACK);
            tr.addView(labelId);

            TextView labelFirstName = new TextView(getContext());
            labelFirstName.setText(users.get(i).getFirstName());
            labelFirstName.setTextColor(Color.BLACK);
            tr.addView(labelFirstName);

            TextView labelLastName = new TextView(getContext());
            labelLastName.setText(users.get(i).getLastName());
            labelLastName.setTextColor(Color.BLACK);
            tr.addView(labelLastName);


            tl.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));


        }


    }



    @Subscribe
    public void onMessageEvent(ShowTable event) {
        try {
            tl.removeAllViews();
            setHeader();
            addToTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}