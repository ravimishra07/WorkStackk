package com.ravimishra.workstack;
 public class Constants {
public static  final String ROW_ID="id";
  public static final String GOAL="goal";
  public static final String GOAL_TYPE="goalType";
  public static final String GVALUE="value";


  public static final String DB_NAME="WORKSTACK_DB";
  public static final String TB_NAME="WORKSTACK_TB";
  public static final int DB_VERSION='1';

  public static final String CREATE_TABLE="CREATE TABLE WORKSTACK_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,goal TEXT NOT NULL,goalType TEXT NOT NULL,value INTEGER);";
}

