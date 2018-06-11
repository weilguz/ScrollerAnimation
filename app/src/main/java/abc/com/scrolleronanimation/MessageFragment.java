package abc.com.scrolleronanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author weilgu
 * @time 2018/6/11  16:58
 * @desc ${TODD}
 */

public class MessageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_layout, container, false);
        TextView tv = (TextView) inflate.findViewById(R.id.tv_show_content);
        tv.setText("MessageFragment");
        return inflate;
    }
}
