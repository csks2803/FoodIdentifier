package com.fooddelivery.data.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fooddelivery.data.DataBundleKeys;
import com.fooddelivery.data.storage.dao.ProductDao;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import javax.inject.Inject;


/**
 * Class for create and update dataBase
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final int DB_VERSION = 1;

    private Context mContext;
    private Dao<ProductDao, Integer> productList = null;

    @Inject
    public DataBaseHelper(Context context, String dbName) {
        super(context, dbName, null, DB_VERSION);

        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            Log.i(DataBundleKeys.TAG, "onCreate");

            TableUtils.createTable(connectionSource, ProductDao.class);
        } catch (SQLException e) {
            Log.e(DataBundleKeys.TAG, "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            Log.i(DataBundleKeys.TAG, "onUpgrade");

            TableUtils.dropTable(connectionSource, ProductDao.class, true);

            onCreate(sqLiteDatabase, connectionSource);

        } catch (SQLException e) {
            Log.e(DataBundleKeys.TAG, "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    Dao<ProductDao, Integer> getProductListDao() {
        if (null == productList) {
            try {
                productList = getDao(ProductDao.class);
            } catch (SQLException e) {
                Log.e(DataBundleKeys.TAG, "exception" + e);
            }
        }
        return productList;
    }

}
