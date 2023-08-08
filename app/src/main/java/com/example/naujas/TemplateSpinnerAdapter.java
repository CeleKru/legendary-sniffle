package com.example.naujas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.content.Context;
import java.util.List;
import android.widget.ArrayAdapter;

public class TemplateSpinnerAdapter extends ArrayAdapter<Template> implements SpinnerAdapter {

    private Context context;
    private List<Template> templates;

    public TemplateSpinnerAdapter(Context context, List<Template> templates) {
        super(context, 0, templates);
        this.context = context;
        this.templates = templates;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.template_spinner_item, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.spinnerItemTextView);
        textView.setText(templates.get(position).getSubject());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.template_spinner_item_dropdown, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.spinnerItemTextViewDropdown);
        textView.setText(templates.get(position).getSubject());
        return convertView;
    }
}


