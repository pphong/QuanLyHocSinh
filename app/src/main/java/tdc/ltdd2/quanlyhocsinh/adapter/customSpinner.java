package tdc.ltdd2.quanlyhocsinh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import tdc.ltdd2.quanlyhocsinh.R;
import tdc.ltdd2.quanlyhocsinh.model.SpinnerItem;


public class customSpinner extends ArrayAdapter<SpinnerItem> {

    public customSpinner(@NonNull Context context, ArrayList<SpinnerItem> customList) {
        super(context, 0, customList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    public View customView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_item, parent, false);
        }
        SpinnerItem items = getItem(position);
        ImageView spinnerImage = convertView.findViewById(R.id.ivSpinnerImg);
        TextView spinnerName = convertView.findViewById(R.id.tvSpinnerContent);
        if (items != null) {
            spinnerImage.setImageResource(items.getClassImage());
            spinnerName.setText(items.getClassName());
        }
        return convertView;
    }
}
