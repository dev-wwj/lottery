package com.scrop.minterface;

/**
 * Created by Scrop on 2017/7/18.
 */

public interface PropertiesControl {
    public boolean getBoolean(String key);
    public boolean getBoolean(String key, boolean defVal);
    public int getInteger(String key);
    public int getInteger(String key, int defVal);
    public short getShort(String key);
    public short getShort(String key, int defVal);
    public long getLong(String key);
    public long getLong(String key, int defVal);
    public String getString(String key);
    public String getString(String key, String defVal);

}
