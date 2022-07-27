package com.he.entity;

import com.he.annotation.Column;
import com.he.annotation.Table;

/**
 * @author 13253
 * @date 2021/7/29 18:40
 * @className Student
 */

@Table(tableName = "student")
public class Student {
    @Column(columnName = "sno")
    private String sno;
    @Column(columnName = "sgender")
    private String sGender;
    @Column(columnName = "sdept")
    private String sDept;
    @Column(columnName = "sage")
    private int sAge;


    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getsGender() {
        return sGender;
    }

    public void setsGender(String sGender) {
        this.sGender = sGender;
    }

    public String getsDept() {
        return sDept;
    }

    public void setsDept(String sDept) {
        this.sDept = sDept;
    }

    public int getsAge() {
        return sAge;
    }

    public void setsAge(int sAge) {
        this.sAge = sAge;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", sGender='" + sGender + '\'' +
                ", sDept='" + sDept + '\'' +
                ", sAge=" + sAge +
                '}';
    }
}
