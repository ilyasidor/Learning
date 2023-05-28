package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class StorageController {

    private Storage storage;
    private int count;

    private final String[] commands=new String[]{
            "GET",//0
            "CREATE",//1
            "QUIT",//2
            "UPDATE",//3
            "DELETE",//4
            "SAVE"//5
    };

    public StorageController(Storage storage) throws IOException {
        this.storage = storage;
        updateLastCount();
    }

    public void quit(String message){
        if(message.equals(commands[2])){
            System.exit(0);
        }
    }
    public void addVal(String message){
        String command = getCommand(message);
        String value = getValue(message);
        if(command.equals(commands[1])){
            saveValue(value);
            System.out.println("String saved with id: "+(count-1));
        }
    }
    public void getOnID(String message) {
        String command = getCommand(message);
        String value = getValue(message);
        if(command.contains(commands[0])&&chekID(value)){
            System.out.println(storage.getValue(Integer.parseInt(value)));
        }
    }
    public void get(String message){
        if(message.replace(" ","").equals("GET")){
            System.out.println(storage.getStringData());
        }
    }
    public void updateStr(String message) {
        String command = getCommand(message);
        String value = getValue(message);
        String arg = message.replace(command+" ","").replace(value+" ","");

        if(command.contains(commands[3])){
            int id = Integer.parseInt(value);
            storage.updateValue(id, arg);
            System.out.println("String with id = "+id+" updated");
        }
    }
    public void deleteEl(String message) {
        String command = getCommand(message);
        String value = getValue(message);

        if(command.contains(commands[4])){
            int id = Integer.parseInt(value);
            storage.removeElement(id);
            System.out.println("String with id = "+id+" deleted");
        }
    }
    public void saveFile(String message) throws FileNotFoundException {
        String command = getCommand(message);

        if(command.contains(commands[5])){
            String dataSTR = storage.getStringData();

            PrintWriter in = new PrintWriter("data.txt");
            in.println(dataSTR);
            in.close();
        }
    }




    private void saveValue(String value){

        updateLastCount();
        storage.addValue(count++,value);
    }
    private void updateLastCount(){
        storage.data.forEach((key, value1) ->{
            //+System.out.println(key+"|"+value);
            count=key+1;
        });
    }
    private boolean chekID(String value){
        boolean isNum;
        int num = 0;
        try {
            num = Integer.parseInt(value);
            isNum = true;
        }catch (NumberFormatException exception){
            isNum = false;
        }
        return isNum && num <= storage.lenght();
    }
    private String getCommand(String message){
        int index = message.indexOf(" ");
        if(index==-1){
            index=message.length();
        }
        String command = message.substring(0,index);
        if(!commandInList(command)){
            System.out.println("COMMAND NOT FIND");
        }
        return command;
    }
    private boolean commandInList(String command){
        boolean inList=false;
        for(String value:commands){
            if(value.equals(command)){
                inList = true;
            }
        }
        return inList;
    }
    private String getValue(String message) {
        if(message.contains(" ")){
            int index = message.indexOf(" ");

            String command = message.substring(0,index+1);


            //System.out.println(command);

            String value = message.replace(command,"");
            int indexSecond = value.indexOf(" ");
            if(indexSecond==-1)
            {
                indexSecond=value.length();
            }
            value = value.substring(0,indexSecond);

            if(isNumeric(value))
            {
                if(storage.getValue(Integer.parseInt(value))==null){
                    System.out.println("ELEMENT NOT FOUND");
                }
                return value;
            }
            else
            {
                return message.replace(command,"");
            }
        }
        else
        {
            return "";
        }
    }
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
