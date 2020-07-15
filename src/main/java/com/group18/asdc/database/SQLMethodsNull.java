package com.group18.asdc.database;

import java.sql.Connection;

public class SQLMethodsNull extends SQLMethods {

    public SQLMethodsNull(Connection conn)
    {
        super(conn);
    }

    /**
     * This is a null object
     */
    @Override
    public void cleanup()
    {

    }
}