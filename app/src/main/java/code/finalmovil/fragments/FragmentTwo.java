package code.finalmovil.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import code.finalmovil.R;
import eventBus.SaveData;

/**
 * Created by Alberto Mario Camargo Castro on 16-May-16.
 */
public class FragmentTwo extends Fragment  implements View.OnClickListener {
    Button submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_two, container, false);
        submit = (Button) v.findViewById(R.id.button);

        submit.setOnClickListener(this);

        return v;
    }




    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new SaveData());
    }
}