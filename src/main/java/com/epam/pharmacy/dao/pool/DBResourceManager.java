package com.epam.pharmacy.dao.pool;

import java.util.ResourceBundle;

public class DBResourceManager {
    private static final String PROPERTIES_FILE = "dbresources";
    private ResourceBundle bundle;

    DBResourceManager(){
        bundle = ResourceBundle.getBundle(PROPERTIES_FILE);
    }

    public String getValue(String key){
        return bundle.getString(key);
    }

}
