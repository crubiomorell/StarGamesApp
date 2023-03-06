package com.example.juegos.puntuaciones;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper {
    private static final int VERSION = 14;
    private SQLiteDatabase mwDB;
    private SQLiteDatabase mrDB;
    private static final String NOMBRE_DB = "game_scores";
    private static final String TABLA_LIGHTSOUT = "data_lightsout_table";
    private static final String TABLA_2048 = "data_2048_table";
    public static final String ID = "_id";
    public static final String NOMBRE = "nombre";
    public static final String PUNTUACION = "puntuacion";

    public static final String DIFICULTAD = "dificultad";

    private static final String LIGHTSOUT_CREATE_QUERY = "CREATE TABLE " + TABLA_LIGHTSOUT + " (" +
            ID + " INTEGER PRIMARY KEY, " +
            NOMBRE + " TEXT, "+
            DIFICULTAD + " TEXT, "+
            PUNTUACION + " TEXT );";

    private static final String CREATE_QUERY_2048 = "CREATE TABLE " + TABLA_2048 + " (" +
            ID + " INTEGER PRIMARY KEY, " +
            NOMBRE + " TEXT, "+ PUNTUACION + " INTEGER );";



    public DataBase(Context context) {
        super(context, NOMBRE_DB, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LIGHTSOUT_CREATE_QUERY);
        db.execSQL(CREATE_QUERY_2048);
    }

    public long newScore2048(String nombre, String puntuacion) {
        long newId = 0;
        String table;
            table = TABLA_2048;
            ContentValues values = new ContentValues();
            values.put(NOMBRE, nombre);
            values.put(PUNTUACION, puntuacion);
            try {
                if (mwDB == null) {
                    mwDB = getWritableDatabase();
                }
                newId = mwDB.insert(table, null, values);
            } catch (Exception e) {
                Log.d(TAG, "INSERT EXCEPTION! " + e);
            }
            return newId;
    }
    public long newScoreLightsOut(String nombre, String puntuacion, String dificultad){
        long newId = 0;
        String table;
            table = TABLA_LIGHTSOUT;

            ContentValues values = new ContentValues();
            values.put(NOMBRE, nombre);
            values.put(DIFICULTAD, dificultad);
            values.put(PUNTUACION, puntuacion);
            try {
                if (mwDB == null) {
                    mwDB = getWritableDatabase();
                }
                newId = mwDB.insert(table, null, values);
            } catch (Exception e) {
                Log.d(TAG, "INSERT EXCEPTION! " + e);
            }
            return newId;

    }
    @SuppressLint("Range")
    public Puntuaciones2048 query2048(int index) {
        String query2048;
        query2048 = "SELECT  * FROM " + TABLA_2048 +
                        " ORDER BY " + PUNTUACION + " DESC " +
                        "LIMIT " + index + ",1";

        Cursor cursor2048 = null;
        Puntuaciones2048 entry2048 = new Puntuaciones2048();

        try {
            if (mrDB == null) {
                mrDB = getReadableDatabase();
            }
            cursor2048 = mrDB.rawQuery(query2048, null);
            cursor2048.moveToFirst();
            entry2048.setId(cursor2048.getInt(cursor2048.getColumnIndex(ID)));
            entry2048.setNombreJugador(cursor2048.getString(cursor2048.getColumnIndex(NOMBRE)));
            entry2048.setPuntuacion(cursor2048.getString(cursor2048.getColumnIndex(PUNTUACION)));
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e);
        } finally {
            cursor2048.close();
            return entry2048;
        }

    }

    @SuppressLint("Range")
    public PuntuacionesLightsOut queryLightsOut(int index) {
        String queryLightsOut;
        queryLightsOut = "SELECT  * FROM " + TABLA_LIGHTSOUT +
                " ORDER BY " + PUNTUACION + " ASC " +
                "LIMIT " + index + ",1";
        Cursor cursor = null;
        PuntuacionesLightsOut entryLightsOut = new PuntuacionesLightsOut();

        try {
            if (mrDB == null) {
                mrDB = getReadableDatabase();
            }
            cursor = mrDB.rawQuery(queryLightsOut, null);
            cursor.moveToFirst();
            entryLightsOut.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            entryLightsOut.setNombreJugador(cursor.getString(cursor.getColumnIndex(NOMBRE)));
            entryLightsOut.setDificultad(cursor.getString(cursor.getColumnIndex(DIFICULTAD)));
            entryLightsOut.setPuntuacion(cursor.getString(cursor.getColumnIndex(PUNTUACION)));
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e);
        } finally {
            cursor.close();
            return entryLightsOut;
        }


    }
    public long count(String game) {
        String table;
        if (game.equals("LightsOut")) {
            table = TABLA_LIGHTSOUT;
        } else {
            table = TABLA_2048;
        }
        if (mrDB == null) {
            mrDB = getReadableDatabase();
        }
        return DatabaseUtils.queryNumEntries(mrDB, table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j) {
        Log.w(DataBase.class.getName(),
                "Upgrading database from version " + i + " to "
                        + j + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_LIGHTSOUT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_2048);
        onCreate(db);
    }
}
