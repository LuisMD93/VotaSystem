package votasystem.Models;

/**
 *
 * @author Usuario
 */
public class Votante extends  Usuario{

    public Votante() {
    }

    
    public Votante(String nombres_completos, String apellidos_completos,String CUI, String correo, String sexo,String fechaNac,String direccion,String departamento,String municipio_residencia,String pais_esidencia,String contrasena, String rol, String estatus) {
        this.setNombres_completos(nombres_completos);
        this.setApellidos_completos(apellidos_completos);
        this.setCUI(CUI);
        this.setCorreo(correo);
        this.setSexo(sexo);
        this.setFecha_de_nacimiento(fechaNac);
        this.setDireccion_residencia(direccion);
        this.setDepartamento_residencia(departamento);
        this.setMunicipio_residencia(municipio_residencia);
        this.setPais_esidencia(pais_esidencia);
        this.setContrasena(contrasena);
        this.setRol(rol);
        this.setEstatus(estatus);
    }

  

    
    
    
    

  
    
    
    
    
    
    
    
    
}
