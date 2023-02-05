package com.example.img.outils;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public abstract class Serializer {

    public static void serialize(String fileName, Object object, Context context) {
        try {
            FileOutputStream file = context.openFileOutput(fileName, context.MODE_PRIVATE);
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(file);
            oos.writeObject(object);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            //Erreur de sérialisation
            e.printStackTrace();
        }
    }

    /**
     * Désérialisation d'un objet
     * @param fileName
     * @param context
     * @return
     */
    public static Object deSerialize(String fileName, Context context) {
        try {
            FileInputStream file = context.openFileInput(fileName);
            ObjectInputStream ois;
            try {
                ois = new ObjectInputStream(file);
                try {
                    Object object = ois.readObject();
                    ois.close();
                    return object;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }catch (StreamCorruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
