package com.fooddelivery.data.utilities.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

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

    public <T> T fromJson(String s, Type t) {
        return mGson.fromJson(s, t);
    }

    public <T> ArrayList<T> fromJsonArray(String s, Class<T> c) {
        ObjectMapper objectMapper = new ObjectMapper();
        // this flag indicates that mapper will be ignore unknown properties
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ArrayList<T> navigation = null;
        try {
            navigation = objectMapper.readValue(
                    s, objectMapper.getTypeFactory().constructCollectionType(List.class, c));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return navigation;
    }

    String toJson(Object o) {
        return mGson.toJson(o);
    }
}
