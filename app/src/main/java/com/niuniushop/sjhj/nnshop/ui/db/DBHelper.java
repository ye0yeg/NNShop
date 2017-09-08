package com.niuniushop.sjhj.nnshop.ui.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/9/8.
 */

class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, MySql.DATABASE_NAME, null, MySql.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MySql.createProductTable);
        db.execSQL(MySql.createUserTable);
        db.execSQL(MySql.createCollectTable);
        db.execSQL(MySql.createShoppingTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
