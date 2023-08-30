package com.example.xyt;

public class EmptyClassroom {

        public String getLanguage(String feature){
            String result;
            switch(feature){
                case "人文楼":
                    result ="人文楼已满";break;
                case "南校区":
                    result ="南校区已满";break;
                case "工学馆":
                    result ="工学馆已满";break;
                case "图书馆":
                    result ="图书馆已满";break;
                default:
                    result="you are me";
            }
            return  result;
        }
    }

