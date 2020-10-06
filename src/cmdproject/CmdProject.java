package cmdproject;

import java.io.File;
import java.util.Scanner;

public class CmdProject {

    static enum Options{
        undefined,
        vacio,
        dir,
        cd,
        end,
        clear,
        toBack
    }
    
    public static void main(String[] args) {
                       
        ControllerCmd cmd = new ControllerCmd("C:\\");
        Scanner sc = new Scanner(System.in);
        String var = "";               
        String aux = "";
        do{
            System.out.print(cmd.Archivo + " ");
            var = sc.nextLine();
            
            Options op = command(var);
            
            switch(op){
                case vacio:                    
                    break;
                case dir:
                    cmd.dir();
                    break;
                case cd:
                    aux = var.split(" ")[1];
                    cmd.nextFolder(aux);
                    aux = "";
                    break;
                case end:
                    System.out.println("...");
                    break;
                case clear:
                    for (int i = 0; i < 10000; i++) {
                        System.out.println("\n");
                    }                    
                    break;
                case toBack:
                    cmd.toBackFolder();
                    break;                        
                default:
                    System.out.println("\"" + var + "\"" + " no se reconoce como un comando interno.");
                    break;
            }
            
            
        }while(!var.equals("end"));
        
    }
    
    static Options command(String cad){
        
        String command[] = cad.split(" ");
        
        if(command.length == 1 && command[0].equals(""))return Options.vacio;
        
        if(command.length == 1  && command[0].equals("dir"))return Options.dir;
        
        if(command.length == 2  && command[0].equals("cd"))return Options.cd;
        
        if(command.length == 1  && command[0].equals(".."))return Options.toBack;
        
        if(command.length == 1  && command[0].equals("end"))return Options.end;
        
        if(command.length == 1  && command[0].equals("clear"))return Options.clear;
                
        return Options.undefined;
    }
}
