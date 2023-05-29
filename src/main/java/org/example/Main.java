package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class Main {

    static Storage storage;


    public static void main(String[] args) throws IOException {
        storage = new Storage("data.txt");
        StorageController controller = new StorageController(storage);
        Scanner in = new Scanner(System.in);
        while (true){
            String str = in.nextLine();
            int index = str.indexOf(" ");
            if(index==-1){
                index = str.length();
            }
            String command = str.substring(0,index);
            switch (command) {
                case "GET" -> {
                    controller.getOnID(str);
                    controller.get(str);
                }
                case "CREATE" -> controller.addVal(str);
                case "QUIT" -> controller.quit(str);
                case "UPDATE" -> controller.updateStr(str);
                case "DELETE" -> controller.deleteEl(str);
                case "SAVE" -> controller.saveFile(str);
                default -> System.out.println("command not found");
            }
        }
    }
}