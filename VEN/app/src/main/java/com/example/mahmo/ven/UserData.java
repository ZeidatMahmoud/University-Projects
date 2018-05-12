package com.example.mahmo.ven;

/**
 * Created by mahmo on 12/28/2017.
 */

public class UserData implements Ven {
    private String name ;
    private String  password ;
    private static int noOfUser =0 ;


    public UserData(String name, String password ) {
        this.name = name;
        this.password = password;
        //noOfUser++ ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static int getNoOfUser() {
        return noOfUser;
    }

    public static void setNoOfUser(int noOfUser) {
        UserData.noOfUser = noOfUser;
    }

}
