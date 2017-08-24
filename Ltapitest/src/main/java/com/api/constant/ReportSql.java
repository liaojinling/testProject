package com.api.constant;

/**
 * Created by Administrator on 2017/7/21.
 */

public class ReportSql {
    public static final String QUERY = "select * from report where projectname=? and casename=? and tag=?";
    public static final String INSERT = "INSERT INTO report (`projectname`, `casename`, `description`, `expected`, `actual`, `result`, `tag`) VALUES (?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE = "UPDATE `report` SET expected=?,actual=?,result=? WHERE projectname=? and casename=? and tag=?";
}
