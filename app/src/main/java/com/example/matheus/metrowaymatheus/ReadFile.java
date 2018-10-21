package com.example.matheus.metrowaymatheus;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile {


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String readAllLine(String path, Context c){
        String result = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(c.getAssets().open(path)));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                result += "\n" + mLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public String readAll(String path, Context c){
        String result = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(c.getAssets().open(path)));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                result += "\n" + mLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}

