package com.ravimishra.workstack;

public class Data {
    int id;
    String goal;
    String goalType;
    int value;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Data(int id, String goal, String goalType, int value) {

        this.id = id;
        this.goal = goal;
        this.goalType = goalType;
        this.value = value;
    }
}
