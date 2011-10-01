package fileio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DaintreeFiles {
    
    public static final int USER = 1;
    public static final int BOOK = 2;
    public static final int MUSIC = 3;
    
    /**
     * @param args
     */
    
    public static void loadFile(String path){
        try {
            BufferedReader input = new BufferedReader(new FileReader(path));
            String next = "";
            int index = 1;
            int dataType = -1;
            while((next = input.readLine()) != null){
                if(next.equals("#books")){
                    dataType = BOOK;
                }else if(next.equals("#music")){
                    dataType = MUSIC;
                }else if(next.equals("#users")){
                    dataType = USER;
                }else if(!next.startsWith("#") && (!next.equals(""))){
                    switch(dataType){
                    case BOOK:{
                        System.out.println(index + ". BOOK - " + next);
                        break;
                    }
                    case MUSIC:{
                        System.out.println(index + ". MUSIC - " + next);
                        break;
                    }
                    case USER:{
                        System.out.println(index + ". USER - " + next);
                        break;
                    }
                    default:{
                        // Do Nothing
                    }
                    }
                    index++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (Exception e){
            System.out.println("An error occured while reading the file.");
        }
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
