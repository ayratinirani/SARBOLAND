package ir.ounegh.vardast;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by aseme on 13/12/2017.
 */

public class AboutFragment extends Fragment {
    TextView tvd;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about,
                container, false);
        tvd=view.findViewById(R.id.fullscreen_content);
        tvd.setText(VrdstApp.BASEURL);
        return view;
    }

}
