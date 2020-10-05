package cmdproject;

import java.io.File;

public class ControllerCmd {
    
    String Archivo;
    
    public ControllerCmd(String archivo){
        this.Archivo = archivo;
    }
    
    public void nextFolder(String FileName){
        Cmd cmd = new Cmd (Archivo);
        File[] listado = cmd.viewFolders();
        if(searchFolder(listado, FileName)){
            
            this.Archivo += "\\" + FileName;
            
        }else{
            System.out.println("El sistema no puede encontrar la ruta especifica.");
        }
        
        
    }
    
    private boolean searchFolder(File FileList[], String FileName){
        for (int i = 0; i < FileList.length; i++) 
            if(FileList[i].isDirectory() && FileList[i].getName().equals(FileName))
                return true;
        return false;
    }
    
    public void dir(){
        Cmd cmd = new Cmd(Archivo);
        File[] FileList = cmd.viewFolders();
        for (int i = 0; i < FileList.length; i++) {
            System.out.println("    " + FileList[i].getName());
        }
        System.out.println("");
    }
}
