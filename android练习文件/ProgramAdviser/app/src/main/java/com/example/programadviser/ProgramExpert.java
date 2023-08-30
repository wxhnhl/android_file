package com.example.programadviser;

public class ProgramExpert {
    public String getLanguage(String feature){
        String result;
        switch(feature){
            case "fast":
                result ="C/C++";break;
            case "easy":
                result ="Python";break;
            case "new":
                result ="Kotlin";break;
            case "00":
                result ="Java";break;
             default:
                 result="you are me";
        }
        return  result;
    }
}
