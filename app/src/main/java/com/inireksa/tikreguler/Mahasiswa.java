package com.inireksa.tikreguler;

/**
 * Created by IniReksa on 7/27/2017.
 */

public class Mahasiswa {
    //private variables
    int _id;
    String _name;
    String _npm;

    // Empty constructor
    public Mahasiswa(){

    }
    // constructor
    public Mahasiswa(int id, String name, String _npm){
        this._id = id;
        this._name = name;
        this._npm = _npm;
    }

    // constructor
    public Mahasiswa(String name, String _npm){
        this._name = name;
        this._npm = _npm;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting npm
    public String getnpm(){
        return this._npm;
    }

    // setting npm
    public void setnpm(String npm){
        this._npm = npm;
    }
}
