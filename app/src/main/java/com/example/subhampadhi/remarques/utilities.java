package com.example.subhampadhi.remarques;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by subhampadhi on 7/28/17.
 */

public class utilities {

    public static final String FILE_EXTERNAL = ".bin";


    public static boolean saveNote(Context context, Notes notes) {
        String fileName = String.valueOf(notes.getmDateTime()) + FILE_EXTERNAL;
        FileOutputStream fos;
        ObjectOutputStream oos;
// serialising to binary
        try {
            fos = context.openFileOutput(fileName, context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(notes);
            oos.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }


        return true;
    }

    public static ArrayList<Notes> getAllSavedNotes(Context context) {
        ArrayList<Notes> notes = new ArrayList<>();


        File filesDir = context.getFilesDir();
        ArrayList<String> noteFiles = new ArrayList<>();
        // check if file ends with .bin then add to ArrayList<string>

        for (String file : filesDir.list()) {
            if (file.endsWith(FILE_EXTERNAL)) {
                noteFiles.add(file);
            }
        }

        //Deserialisation back from files saved

        FileInputStream fis;
        ObjectInputStream ois;

        for (int i = 0; i < noteFiles.size(); i++) {
            try {
                fis = context.openFileInput(noteFiles.get(i));
                ois = new ObjectInputStream(fis);

                notes.add((Notes) ois.readObject());
                fis.close();
                ois.close();


            } catch (IOException | ClassNotFoundException e) {

                e.printStackTrace();
                return null;
            }
        }

        return notes;
    }

    public static Notes getNoteByName(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        if (file.exists()) {
            FileInputStream fis;
            ObjectInputStream ois;

            Notes notes;
            try {
                fis = context.openFileInput(fileName);
                ois = new ObjectInputStream(fis);
                notes = (Notes) ois.readObject();
                fis.close();
                ois.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
            return notes;
        }
        return null;
    }

    public static void deleteNote(Context context, String fileName) {
        File dir = context.getFilesDir();
        File file = new File(dir, fileName);

        if (file.exists()) {
            file.delete();
        }
    }
}
