package com.example.img.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.img.outils.MesOutils;
import com.example.img.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {

    //Propriétés
    private String nomBase = "bdCoach.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    /**
     * Constructor
     * @param context
     */
    public AccesLocal(Context context) {
        accesBD = new MySQLiteOpenHelper(context, nomBase, null, versionBase);
    }

    /**
     * Ajout d'un profil dans la base de données
     * @param profile
     */
    public void ajout(Profile profile) {
        bd = accesBD.getWritableDatabase();
        String req = "insert into profile (datemesure, poids, taille, age, sexe) values ";
                req += "(\"" + profile.getDateMesure() + "\", "+profile.getPoids()+ ", " +
                        profile.getTaille() + ", "+ profile.getAge() + ", "+
                        profile.getSexe() +")";
        bd.execSQL(req);
    }

    /**
     * Récupération du dernier profile
     * @return
     */
    public Profile recupDernier() {
        bd = accesBD.getReadableDatabase();
        Profile profile = null;
        String req = "select * from Profile";
        Cursor cursor = bd.rawQuery(req, null);
        cursor.moveToLast();
        if (!cursor.isAfterLast()) {
            Date date = MesOutils.convertStringToDate(cursor.getString(0));
            Integer poids = cursor.getInt(1);
            Integer taille = cursor.getInt(2);
            Integer age = cursor.getInt(3);
            Integer sexe = cursor.getInt(4);
            profile = new Profile(date, poids, taille, age, sexe);
        }
        cursor.close();
        return profile;
    }
}
