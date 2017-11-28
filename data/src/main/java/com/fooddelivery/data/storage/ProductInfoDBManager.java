package com.fooddelivery.data.storage;

import android.util.Log;

import com.fooddelivery.data.DataBundleKeys;
import com.fooddelivery.data.storage.dao.ProductDao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

/**
 * Class for main operation in database.
 */
@Singleton
class ProductInfoDBManager {
    private final DataBaseHelper mHelper;

    ProductInfoDBManager(DataBaseHelper helper) {
        mHelper = helper;
    }

    List<ProductDao> getAllProductList() {
        List<ProductDao> ProductList = null;
        try {
            ProductList = mHelper.getProductListDao().queryForAll();

            Collections.reverse(ProductList);
        } catch (SQLException e) {
            Log.e(DataBundleKeys.TAG, "exception" + e);
        }
        return ProductList;
    }

    void createProduct(ProductDao Product) {
        try {
            mHelper.getProductListDao().create(Product);
        } catch (SQLException e) {
            Log.e(DataBundleKeys.TAG, "exception" + e);
        }
    }

    public ProductDao getProduct(String id) {
        try {
            QueryBuilder<ProductDao, Integer> queryBuilder = mHelper.getProductListDao().queryBuilder();
            return queryBuilder.where().eq("id", id).queryForFirst();
        } catch (SQLException e) {
            Log.e(DataBundleKeys.TAG, "exception" + e);
        }
        return null;
    }

    public void updateProductList(ProductDao Product) {
        try {
            mHelper.getProductListDao().update(Product);
        } catch (SQLException e) {
            Log.e(DataBundleKeys.TAG, "exception" + e);
        }
    }
}
