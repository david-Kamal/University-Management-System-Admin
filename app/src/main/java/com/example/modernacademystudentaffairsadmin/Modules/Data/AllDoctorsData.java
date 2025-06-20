package com.example.modernacademystudentaffairsadmin.Modules.Data;

public class AllDoctorsData
{
    private int id;
    private String name, regrade;

    public AllDoctorsData(int id, String name, String regrade)
    {
        this.id = id;
        this.name = name;
        this.regrade = regrade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegrade() {
        return regrade;
    }
}
