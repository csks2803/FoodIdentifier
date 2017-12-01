package com.foodidentifier.data.utilities.mapper;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodidentifier.data.DataBundleKeys;
import com.foodidentifier.data.model.ProductEntityModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.foodidentifier.data.DataBundleKeys.TAG;

/**
 * Class for mapping Json object
 */
@Singleton
public class JsonMapper {
    private Gson mGson;

    @Inject
    public JsonMapper() {
        this.mGson = new Gson();
    }

    public <T> T fromJson(String s, Class<T> c) {
        return mGson.fromJson(s, c);
    }

    public <T> List<T> fromJsonArray(String json, final Class<T> classType) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<T> list = null;
        try {
            list = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, classType));
        } catch (IOException e) {
            Log.e(DataBundleKeys.TAG, e.toString());
        }
        return list;
    }

    String toJson(Object o) {
        return mGson.toJson(o);
    }
}
