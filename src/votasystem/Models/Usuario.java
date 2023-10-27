/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votasystem.Models;

/**
 *
 * @author Usuario
 */
public class Usuario extends Persona{
    
    
    private String correo;
    private String contrasena;
    private String estatus;
    private String rol;

    public Usuario() {
    }
  

    public Usuario(String correo, String contrasena, String estatus, String rol) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.estatus = estatus;
        this.rol = rol;
    }

    public Usuario(String nombres_completos, String apellidos_completos) {
        super(nombres_completos, apellidos_completos);
    }

    
    //Maira,Ibañes,mar@persona.com,1234556,Registrador de votante,activo
     public Usuario(String nombres_completos, String apellidos_completos,String correo, String contrasena, String rol, String estatus) {
        super(nombres_completos, apellidos_completos);
        this.correo = correo;
        this.contrasena = contrasena;
        this.estatus = estatus;
        this.rol = rol;
        
    }
     
      public Usuario(String nombres_completos, String apellidos_completos, String contrasena, String rol, String estatus) {
        super(nombres_completos, apellidos_completos);
        this.correo = correo;
        this.contrasena = contrasena;
        this.estatus = estatus;
        this.rol = rol;
        
    }

    public Usuario(String correo, String contrasena, String estatus, String rol, String nombres_completos, String apellidos_completos, String CUI, String sexo, String fecha_de_nacimiento, String direccion_residencia, String departamento_residencia, String municipio_residencia, String pais_esidencia) {
        super(nombres_completos, apellidos_completos, CUI, sexo, fecha_de_nacimiento, direccion_residencia, departamento_residencia, municipio_residencia, pais_esidencia);
        this.correo = correo;
        this.contrasena = contrasena;
        this.estatus = estatus;
        this.rol = rol;
    }
     
    
  
    

     
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    
    
    

    
    
    
    


    
    
    
}
