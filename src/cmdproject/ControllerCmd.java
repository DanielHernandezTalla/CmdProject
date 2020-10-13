package cmdproject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class ControllerCmd {
    
    String Archivo;
    
    public ControllerCmd(String archivo){
        this.Archivo = archivo;
    }
    
    public String ViewArchivo(){
        return Archivo;
    }
    
    public void nextFolder(String FileName){
        Cmd cmd = new Cmd (Archivo);
        File[] listado = cmd.viewFolders();
        if(searchFolder(listado, FileName)){
            
            this.Archivo += "\\" + FileName;
            
        }else{
            System.out.println("El sistema no puede encontrar la ruta especifica.\n");
        }               
    }
    
    public void toBackFolder(){
        if(Archivo.equals("C:\\"))System.out.println("It is not posible to return\n");
        else{
            String[] aux = Archivo.split("\\\\");
            Archivo = "";
            for (int i = 0; i < aux.length-1; i++) 
                Archivo += i == aux.length-2? aux[i]+"": aux[i]+"\\";
            
        }
    }
    private boolean searchFolder(File FileList[], String FileName){
        for (int i = 0; i < FileList.length; i++) 
            if(FileList[i].isDirectory() && FileList[i].getName().equals(FileName))
                return true;
        return false;
    }

    private boolean search(File FileList[], String FileName){
        for (int i = 0; i < FileList.length; i++) 
            if(FileList[i].getName().equals(FileName))
                return true;
        return false;
    } 

    public void dir(){
        Cmd cmd = new Cmd(Archivo);
        File[] FileList = cmd.viewFolders();
        for (int i = 0; i < FileList.length; i++) {
            System.out.println(FileList[i].getName());
        }
        System.out.println("");
    }
    
    public void details(){
        Cmd cmd = new Cmd(Archivo);
        File[] FileList = cmd.viewFolders();
        System.out.printf("%-30s %-30s %-30s %10s %10s\n","Nombre", "Fecha de modificacion", "Tipo", "Tamaño", "Oculto");
        for (int i = 0; i < FileList.length; i++) {            
            
            long dat = FileList[i].lastModified();
            Date dates = new Date(dat);
            SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            String date = d.format(dates);
            
            if(FileList[i].isDirectory())
                System.out.printf("%-30s %-30s %-30s %10s %10s\n",FileList[i].getName(), date, "Carpeta de Archivos", (FileList[i].length()/1024)+1, FileList[i].isHidden()?"si":"no");
            else{
                int j = -1;
                j = FileList[i].getName().indexOf(".");
                System.out.printf("%-30s %-30s %-30s %10s %10s\n",FileList[i].getName(), date,  (j == -1) ? "Archivo " : "Archivo " + FileList[i].getName().substring(j), (FileList[i].length()/1024)+1, FileList[i].isHidden()?"si":"no");
            }                
        }
        
        System.out.println("");
    }
    
    public void details(String FileName){
        Cmd cmd = new Cmd(Archivo);
        File[] FileList = cmd.viewFolders();
        if(!details(FileList, FileName))
            System.out.println("El sistema no puede encontrar el archivo especificado.\n");        
    }
    private boolean details(File FileList[], String FileName){
        for (int i = 0; i < FileList.length; i++) 
            if(FileList[i].getName().equals(FileName)){
                
                System.out.printf("%-30s %-30s %-30s %10s %10s %10s\n","Nombre", "Fecha de modificacion", "Tipo", "Tamaño", "Oculto", "Lectura");
                
                long dat = FileList[i].lastModified();
                Date dates = new Date(dat);
                SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
                String date = d.format(dates);

                if(FileList[i].isDirectory())
                    System.out.printf("%-30s %-30s %-30s %10s %10s %10s\n\n",FileList[i].getName(), date, "Carpeta de Archivos", (FileList[i].length()/1024)+1, FileList[i].isHidden()?"si":"no", FileList[i].canWrite()? "Si": "No");
                else{
                    int j = -1;
                    j = FileList[i].getName().indexOf(".");
                    System.out.printf("%-30s %-30s %-30s %10s %10s %10s\n\n",FileList[i].getName(), date,  (j == -1) ? "Archivo " : "Archivo " + FileList[i].getName().substring(j), (FileList[i].length()/1024)+1, FileList[i].isHidden()?"si":"no", FileList[i].canWrite()? "Si": "No");
                }
                return true;
            }                
        return false;
    }
    public void delete(String FileName){
        Cmd cmd = new Cmd (Archivo);
        File[] listado = cmd.viewFolders();
        if(!delete(listado, FileName))
            System.out.println("El sistema no puede encontrar el archivo especificado.\n");
    }
    
    private boolean delete(File FileList[], String FileName){
        for (int i = 0; i < FileList.length; i++) 
            if(FileList[i].getName().equals(FileName)){
                FileList[i].delete();
                return true;
            }                
        return false;
    }
    
    public void rename(String FileName, String NewFileName){
        Cmd cmd = new Cmd (Archivo);
        File[] listado = cmd.viewFolders();
        if(search(listado, NewFileName))
            System.out.println("Archivo existente, no se puede renombrar.\n");
        else if(!rename(listado, FileName, NewFileName))
            System.out.println("El sistema no puede encontrar el archivo especificado.\n");        
    }
    
    private boolean rename(File FileList[], String FileName, String NewFileName){
        for (int i = 0; i < FileList.length; i++)
            if(FileList[i].getName().equals(FileName)){
                File file = new File(Archivo + "//" + NewFileName);
                FileList[i].renameTo(file);
                return true;
            }                        
        return false;
    }
    
    public void newFile(String FileName){
        
        Cmd cmd = new Cmd (Archivo);
        File[] listado = cmd.viewFolders();
        if(search(listado, FileName))
            System.out.println("Archivo existente.\n");
        else{
            try {
                File file = new File(Archivo + "\\" + FileName);
                file.createNewFile();                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }        
    }
    
    public void newDirectory(String FileName){
        
        try {
            File file = new File(Archivo + "\\" + FileName);
            if(file.isDirectory())
                System.out.println("Ese directorio ya existe.");
            else{
                file.mkdirs();
            }                 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
