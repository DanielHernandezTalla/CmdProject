package cmdproject;

import java.io.File;
import java.util.Scanner;

public class CmdProject {

    public static void main(String[] args) {
                       
        ControllerCmd cmd = new ControllerCmd("C:\\");
        Scanner sc = new Scanner(System.in);
        String var = "";
        
        do{
            System.out.print(cmd.Archivo + " ");
            var = sc.nextLine();
            
            switch(var){
                case "dir":
                    cmd.dir();
                    break;
                case "SOCOPRO":
                    cmd.nextFolder(var);
                    break;
                case "end":
                    System.out.println("...");
                    break;
                default:
                    System.out.println("\"" + var + "\"" + " no se reconoce como un comando interno.");
                    break;
            }
            
            
        }while(!var.equals("end"));
        
        /*
        cmd.dir();
        
        cmd.nextFolder("SOCOPR");
        System.out.println("");
        cmd.dir();*/
        
        
        
        
    }
}
