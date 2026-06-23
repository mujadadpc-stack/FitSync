package com.example.javafx;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonLoader {
    public static ArrayList<User> loadUsers() {
        try
        {
            Gson gson = new Gson();
            FileReader reader = new FileReader("C:/Users/Mujadad/Desktop/Projects/JavaFX/user.json");
            Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
            ArrayList<User> users = gson.fromJson(reader, userListType);
            reader.close();
            return users;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public static User findUser(String username)
    {
        ArrayList<User> users = loadUsers();
        for(User u : users)
        {
            if(u.getUsername().equals(username))
                return u;
        }
        return null;
    }
    public static boolean usernameTaken(String username)
    {
        ArrayList<User> users = loadUsers();
        for(User u : users)
        {
            if(u.getUsername().equals(username))
                return true;
        }
        return false;
    }
    public static void saveUsers(ArrayList<User> users)
    {
        try(FileWriter writer = new FileWriter("user.json"))
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(users,writer);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void updateCalories(String username, double calories)
    {
        ArrayList<User> users = loadUsers();

        for(User u : users)
        {
            if(u.getUsername().equals(username))
            {
                u.setCalories(calories);
                break;
            }
        }
        saveUsers(users);
    }
    public static void updateSteps(String username,int steps)
    {
        ArrayList<User> users = loadUsers();
        for(User u : users)
        {
            if(u.getUsername().equals(username))
            {
                u.setStepsTaken(steps);
                break;
            }
        }
        saveUsers(users);
    }
    public static void resetProgress(String username)
    {
        ArrayList<User> users = loadUsers();
        for(User u : users)
        {
            if(u.getUsername().equals(username))
            {
                u.resetProgress();
                break;
            }
        }
        saveUsers(users);
    }
}
