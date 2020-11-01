package com.example.admin.fragment8;

public class Replacement {
    String group;
    String year;
    String month;
    String day;
    String number;
    String old_name;
    String old_teacher1;
    String old_teacher2;
    String old_study;
    String new_name;
    String new_teacher1;
    String new_teacher2;
    String new_study;

    public Replacement(String group, String year, String month,
                       String day, String number, String old_name,
                       String old_teacher1, String old_teacher2,
                       String old_study, String new_name, String new_teacher1,
                       String new_teacher2, String new_study) {
        this.group = group;
        this.year = year;
        this.month = month;
        this.day = day;
        this.number = number;
        this.old_name = old_name;
        this.old_teacher1 = old_teacher1;
        this.old_teacher2 = old_teacher2;
        this.old_study = old_study;
        this.new_name = new_name;
        this.new_teacher1 = new_teacher1;
        this.new_teacher2 = new_teacher2;
        this.new_study = new_study;
    }
}
