package dev.xdark.clientapi.config;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

import java.io.File;
import java.io.InputStream;

@SidedApi(Side.BOTH)
public interface Config {

    void setDefaults();

    void save();

    void load();

    void load(InputStream inputStream);

    File getFile();

    void set(String path, Object value);

    Object get(String path);

    Object get(String path, Object obj);

    String getString(String path);

    String getString(String path, String string);

    Integer getInteger(String path, int integer);

    Integer getInteger(String path);

    Double getDouble(String path);

    Double getDouble(String path, double d0uble);

    Boolean getBoolean(String path);

    Boolean getBoolean(String path, boolean bool);


}
