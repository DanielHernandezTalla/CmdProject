package cmdproject;

import java.io.File;


public class Cmd {
    
    private String Archivo;
    
    public Cmd(){
        
    }
    
    public Cmd(String archivo){
        this.Archivo = archivo;
    }
    
    public File[] viewFolders(){
        File carpeta = new File(Archivo);
        File[] listado = carpeta.listFiles();        
        return listado;
    }
    
}
