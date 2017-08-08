package com.example.subhampadhi.remarques;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {
    private EditText mEtTitle;
    private EditText mETContent;
    private String mNoteFileName;
    private Notes mLoadedNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        mEtTitle = (EditText) findViewById(R.id.note_et_title);
        mETContent = (EditText) findViewById(R.id.note_et_content);
        mNoteFileName = getIntent().getStringExtra("NOTE_FILE");
        if (mNoteFileName != null && !mNoteFileName.isEmpty()) {
            mLoadedNotes = utilities.getNoteByName(this, mNoteFileName);
        }

        if (mLoadedNotes != null) {
            mEtTitle.setText(mLoadedNotes.getmTitle());
            mETContent.setText(mLoadedNotes.getmContent());

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_note_save:
                saveNote();
                break;

            case R.id.action_note_delete:
                deleteNote();
                break;
        }

        return true;
    }

    private void saveNote() {
        Notes notes;

        //if note title or content is empty dont save
        if (mEtTitle.getText().toString().trim().isEmpty() || mETContent.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please Enter a title and content", Toast.LENGTH_SHORT).show();
            return;
        }

        // if loaded notes is empty make a new one.. or else get the old data and replac it
        if (mLoadedNotes == null) {

            notes = new Notes(System.currentTimeMillis(), mEtTitle.getText().toString(), mETContent.getText().toString());
        } else {
            notes = new Notes(mLoadedNotes.getmDateTime(), mEtTitle.getText().toString(), mETContent.getText().toString());
        }

        if (utilities.saveNote(this, notes)) {
            Toast.makeText(this, "your note is saved!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Cannot save your Note make sure there is enough space", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    public void deleteNote() {
        if (mLoadedNotes == null) {
            finish();
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setMessage("you are about to delete " + mEtTitle.getText().toString() + "are you sure??")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            utilities.deleteNote(getApplicationContext(), mLoadedNotes.getmDateTime() + utilities.FILE_EXTERNAL);
                            Toast.makeText(getApplicationContext(),
                                    mEtTitle.getText().toString() + "is deleted", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    })
                    .setNegativeButton("no", null)
                    .setCancelable(false);
            dialog.show();


        }

    }
}
