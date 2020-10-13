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
        toBack,
        help,
        details,
        delete,
        rename,
        NewFile,
        NewDirectory,      
        info
    }
    //Este es un comentario
    int variable = 0;
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
                case details:
                    cmd.details();
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
                case help:
                    System.out.printf("%-10s Acceder a un directorio.\n", Options.cd);
                    System.out.printf("%-10s Limpia la consola.\n", Options.clear);
                    System.out.printf("%-10s Muestra archivos y directorios de un directorio.\n", Options.dir);
                    System.out.printf("%-10s Finaliza el progama.\n", Options.end);
                    System.out.printf("%-10s Muestra cada comando y para que sirve cada uno de estos.\n", Options.help);
                    System.out.printf("%-10s Regresa un directorio atras.\n", "...");
                    break;
                case delete:
                    cmd.delete(var.split(" ")[1]);
                    break;
                case rename:
                    cmd.rename(var.split(" ")[1], var.split(" ")[2]);
                    break;
                case NewFile:
                    cmd.newFile(var.split(" ")[1]);
                    break;
                case NewDirectory:
                    cmd.newDirectory(var.split(" ")[1]);
                    break;
                case info:
                    cmd.details(var.split(" ")[1]);
                    break;
                default:
                    System.out.println("\"" + var + "\"" + " no se reconoce como un comando interno.\n");
                    break;
            }
            
            
        }while(!var.equals("end"));
        
    }
    
    static Options command(String cad){
        
        String command[] = cad.split(" ");
        
        if(command.length == 1 && command[0].equals(""))return Options.vacio;
        
        if(command.length == 1  && command[0].equals("dir"))return Options.dir;
        
        if(command.length == 1  && command[0].equals("details"))return Options.details;
        
        if(command.length == 2  && command[0].equals("cd"))return Options.cd;
        
        if(command.length == 1  && command[0].equals(".."))return Options.toBack;
        
        if(command.length == 1  && command[0].equals("end"))return Options.end;
        
        if(command.length == 1  && command[0].equals("clear"))return Options.clear;
        
        if(command.length == 1  && command[0].equals("help"))return Options.help;
        
        if(command.length == 2  && command[0].equals("delete"))return Options.delete;
        
        if(command.length == 3  && command[0].equals("rename"))return Options.rename;
        
        if(command.length == 2  && command[0].equals("new"))return Options.NewFile;
        
        if(command.length == 2  && command[0].equals("newDirectory"))return Options.NewDirectory;
        
        if(command.length == 2  && command[0].equals("info"))return Options.info;
                
        return Options.undefined;
    }
}
