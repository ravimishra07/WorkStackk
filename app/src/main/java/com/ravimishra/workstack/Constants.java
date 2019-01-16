package com.ravimishra.workstack;
 public class Constants {
static  final String ROW_ID="id";
static final String GOAL="goal";
static final String GOAL_TYPE="goalType";
static final String GVALUE="value";


static final String DB_NAME="WORKSTACK_DB";
static final String TB_NAME="WORKSTACK_TB";
static final int DB_VERSION='1';

static final String CREATE_TABLE="CREATE TABLE WORKSTACK_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,goal TEXT NOT NULL,goalType TEXT NOT NULL,value INTEGER);";
}

