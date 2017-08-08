package com.example.subhampadhi.remarques;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by subhampadhi on 7/31/17.
 */

public class NoteAdapter extends ArrayAdapter<Notes> {
    public NoteAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Notes> notes) {
        super(context, resource, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_note, null);
        }

        Notes notes = getItem(position);
        if (notes != null) {
            TextView title = convertView.findViewById(R.id.list_note_title);
            TextView date = convertView.findViewById(R.id.list_note_date);
            TextView content = convertView.findViewById(R.id.list_note_content);
            title.setText(notes.getmTitle());
            date.setText(notes.getDateTimeFormatted(getContext()));
            if (notes.getmContent().length() > 40) {
                content.setText(notes.getmContent().substring(0, 40));
            } else {
                content.setText(notes.getmContent());
            }
        }
        return convertView;
    }
}
