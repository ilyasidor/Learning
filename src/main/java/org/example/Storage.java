package org.example;

import java.io.*;
import java.util.HashMap;

public class Storage {
    private HashMap<Integer,String > data = new HashMap<>();

    public Storage(String pathToUpload) throws IOException {

        String uploadData = uploadDataOutFile(pathToUpload);
        loadDataToHashMap(uploadData);
    }

    public HashMap<Integer, String> getData(){
        return data;
    }
    private void loadDataToHashMap(String uploadData){
        String[] lines = uploadData.split("\n");
        //System.out.println(uploadData);
        for (String line:lines){

            int index = line.indexOf("|");
            if(index!=-1){
                int key = Integer.parseInt(line.substring(0,index));
                String value = line.replace(key+"|","");

                data.put(key,value);
            }
        }
    }
    private String uploadDataOutFile(String pathToUpload) throws IOException {
        StringBuilder uploadData= new StringBuilder();
        FileReader out = new FileReader(pathToUpload);
        int c;
        while((c=out.read())!=-1){
            uploadData.append((char) c);
        }
        return uploadData.toString();
    }
    public void addValue(int key, String value){
        data.put(key,value);
    }
    public String getValue(int key){
        return data.get(key);
    }
    public int lenght(){
        return data.size();
    }
    public void updateValue(int key,String message){
        data.replace(key,message);
    }
    public void removeElement(int key){
        data.entrySet()
                .removeIf(entry -> entry.getKey().equals(key));
    }
    public String getStringData(){
        final String[] dataSTR = {""};
        data.forEach((key, value) ->{
            //System.out.println(key+"|"+value);
            dataSTR[0] +=key+"|"+value+"\n";
        });
        return dataSTR[0];
    }

}
