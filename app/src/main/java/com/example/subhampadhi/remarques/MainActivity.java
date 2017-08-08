package com.example.subhampadhi.remarques;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ListView mListViewNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListViewNotes = (ListView) findViewById(R.id.main_listview_notes);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_main_new_note:
                // Start Note activity as new note mode
                Intent newNoteActivity = new Intent(this, NoteActivity.class);
                startActivity(newNoteActivity);

                break;


        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListViewNotes.setAdapter(null);

        ArrayList<Notes> notes = utilities.getAllSavedNotes(this);
        Collections.reverse(notes);

        if (notes == null || notes.size() == 0) {
            Toast.makeText(this, "No Saved notes!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(), R.layout.item_note, notes);
            mListViewNotes.setAdapter(noteAdapter);

            // On clicking any of the new notes opens notes


            mListViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String fileName = ((Notes) mListViewNotes.getItemAtPosition(i)).getmDateTime() + utilities.FILE_EXTERNAL;

                    Intent viewNoteIntent = new Intent(getApplicationContext(), NoteActivity.class);
                    viewNoteIntent.putExtra("NOTE_FILE", fileName);
                    startActivity(viewNoteIntent);
                }
            });


        }


    }
}
