package com.group18.asdc.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface ISQLMethods {

    public void cleanup();

    public Object insertQuery(String sqlQuery, ArrayList<Object> values) throws SQLException;

    public ArrayList<HashMap<String, Object>> selectQuery(String sqlQuery, ArrayList<Object> values)
            throws SQLException;

    public Integer updateQuery(String sqlQuery, ArrayList updateValueList, ArrayList criteriaValueList)
            throws SQLException;

    public Integer deleteQuery(String sqlQuery, ArrayList criteriaList) throws SQLException;

    public Integer multipleInsertQuery(String sqlQuery, ArrayList<ArrayList> valuesList) throws SQLException;

}