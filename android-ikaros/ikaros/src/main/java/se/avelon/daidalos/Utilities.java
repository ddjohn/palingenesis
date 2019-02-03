package se.avelon.daidalos;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class Utilities {

    public static String float2String(float[] floats) {
        String msg = "";
        for(float f : floats) {
            msg += String.format("%.4f", f) + " ";
        }
        return msg;
    }

    public static void spinner(View view, int res, ArrayList<String> list) {
        Spinner spinner = (Spinner)view.findViewById(res);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
