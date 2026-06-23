package com.example.javafx;

public class User {
    private String username;
    private String password;
    private int age;
    private String gender;
    private double weight;
    private double heightincm;
    private double calories;
    private int stepsTaken;

    public User(String username,String password,int age,String gender,double weight,int heightincm)
    {
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.heightincm = heightincm;
    }
    public String getUsername()
    {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    public double calculateBMI()
    {
        double h = heightincm/100.0;
        return (weight / (h*h));
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeightincm() {
        return heightincm;
    }

    public String getGender() {
        return gender;
    }

    public double getCalories() {
            return calories;
    }
    public int getStepsTaken()
    {
        return stepsTaken;
    }
    public void setCalories(double calories)
    {
        this.calories += calories;
    }
    public void setStepsTaken(int stepsTaken)
    {
        this.stepsTaken += stepsTaken;
    }
    public void resetProgress()
    {
        this.calories = 0;
        this.stepsTaken = 0;
    }

}
