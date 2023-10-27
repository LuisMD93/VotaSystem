package votasystem.Helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import votasystem.Models.Elecciones;
import votasystem.Models.Persona;
import votasystem.Models.Usuario;
import votasystem.Models.Votante;

/**
 *
 * @author Usuario
 */
public  class Helper {
    
    private static String ARCHIVO_Votante = "C:/Electoral/votantes.txt";
    private static String ARCHIVO_Usuarios = "C:/Electoral/usuarios.txt";
    private static String ARCHIVO_elecciones  = "C:/Electoral/elecciones.txt";
    private static String ARCHIVO_candidatos  = "C:/Electoral/candidatos.txt";
    private static String ARCHIVO__FECHA_LIMITE  = "C:/Electoral/limiteInscripcion.txt";
     private static String ARCHIVO_Votaciones = "C:/Electoral/votaciones.txt";
    
    private static BufferedWriter Fescribe;
    private static BufferedReader leer;
    private static FileReader Fleer;
    public static Date date = new Date();
    
    //Sets de caracteres disponibles con los que construir contraseñas
    private static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz"; 
    private static final String NUMEROS = "0123456789";
    
    
     public static Boolean AdminExists(){
        
        try
        {
           
           Fleer = new FileReader(ARCHIVO_Usuarios);
           leer = new BufferedReader(Fleer);
            String linea="";
            while((linea=leer.readLine())!=null)
            {
                if (linea.indexOf("Administrador")!=-1)
                {
                   return true;
                }
            }              
        }
         
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
         return false;
    }
    
    
    public static  void CrearAdmin(){
         
         Usuario usuario = new Usuario();
         
         usuario.setContrasena(JOptionPane.showInputDialog(null,"Sistema de Votaciones "
                + "                                  \n\n  Ingrese una contraseña para el usuario Admin "));
         
         
         usuario.setNombres_completos(JOptionPane.showInputDialog(null,"Ingrese nombre de admin"));
         usuario.setApellidos_completos(JOptionPane.showInputDialog(null,"Ingrese apellido de admin"));
         usuario.setCorreo(JOptionPane.showInputDialog(null,"Ingrese correo de admin"));
         usuario.setRol("Administrador - Registradores de votantes - Votante - Auditor");
         usuario.setEstatus("Activo");
         
         try{
                Fescribe =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_Usuarios,true)));
            
                //guarda en la base de datos los archivos 
                Fescribe.write(usuario.getNombres_completos()+","+usuario.getApellidos_completos()+","+
                           usuario.getCorreo()+","+usuario.getContrasena()+","+usuario.getRol()+","+usuario.getEstatus()+"\n");  
            
                   
                System.out.println("El producto ha sido insertado en la base de datos");
                Fescribe.close();//cerrar el fichero y que los datos se queden escritos
                
            }catch(Exception ex){
            System.out.println(ex.getMessage());
        }   
    }
    
    
    public static  void CrearUser(){
         
         Usuario usuario = new Usuario();
         String rols;
            
         usuario.setNombres_completos(JOptionPane.showInputDialog(null,"Ingrese nombre de usuario"));
         usuario.setApellidos_completos(JOptionPane.showInputDialog(null,"Ingrese apellido de usuario"));
         usuario.setCorreo(JOptionPane.showInputDialog(null,"Ingrese correo de usuario"));
         JOptionPane.showMessageDialog(null,"Ingrese roles de usuario");

         rols = JOptionPane.showInputDialog(null,"Ingrese los roles que desea asignar a este usuario seguido de un (-)"
                 + "\nAdministrador -\n Registradores de votantes -\n Votante\n Auditor");
         usuario.setRol(rols);
         usuario.setContrasena(JOptionPane.showInputDialog(null," Ingrese una contraseña para el usuario"));
         usuario.setEstatus("Activo");
         
         try{
                Fescribe =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_Usuarios,true)));
            
                //guarda en la base de datos los archivos 
                Fescribe.write(usuario.getNombres_completos()+","+usuario.getApellidos_completos()+","+
                           usuario.getCorreo()+","+usuario.getContrasena()+","+usuario.getRol()+","+usuario.getEstatus()+"\n");            
                    
                System.out.println("datos han sido insertado en la base de datos");
                Fescribe.close();//cerrar el fichero y que los datos se queden escritos
                
            }catch(Exception ex){
            System.out.println(ex.getMessage());
        }   
    }
    
    private static void CrearCandidato(){
        
        Usuario usuario = new Usuario();
        
         
         
         usuario.setNombres_completos(JOptionPane.showInputDialog(null,"Ingrese nombre del candidato "));
         usuario.setApellidos_completos(JOptionPane.showInputDialog(null,"Ingrese apellido del candidato"));
         usuario.setContrasena(JOptionPane.showInputDialog(null,"Formacion del candidato"));
         usuario.setRol(JOptionPane.showInputDialog(null,"Ingrese la experiencial profesional"));
         usuario.setEstatus(String.valueOf((int)Math.floor(Math.random()*10+1)));
         
         try{
                Fescribe =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_candidatos,true)));
            
                //guarda en la base de datos los archivos 
                Fescribe.write(usuario.getNombres_completos()+","+usuario.getApellidos_completos()+","+
                         usuario.getContrasena()+","+usuario.getRol()+","+usuario.getEstatus()+"\n");  
            
                   
                System.out.println("El producto ha sido insertado en la base de datos");
                Fescribe.close();//cerrar el fichero y que los datos se queden escritos
                
            }catch(Exception ex){
            System.out.println(ex.getMessage());
        }   
    }
    
    
    public static void  AsignarCandidatosEleccion() throws IOException{
        
         Elecciones dataEle[] =  GetElecciones();
         Usuario[] data = GetCandidatos();//OBTENEMOS LOS CANDIDATOS
         int[] dataSigandos=GetAsignados();//para evitar mostrar los candidatos que fueron asignados
         String info = "";
         String infoEle = "";
        
         
         
           for(int i = 0; i <dataEle.length; i++) {
            
            infoEle+= "\n\n Titulo : "+dataEle[i].getTituloEleccion()+" - "+ "Codigo: "+dataEle[i].getCodigoEleccion()+"\n";
           }
           
           int codEleccion =Integer.parseInt(JOptionPane.showInputDialog(null,"elecciones disponibles"+infoEle+"\n\nPorfavor ingrese el codigo de la elccion a configurar"));
           
             infoEle = "";
             for(int i = 0; i <dataEle.length; i++) {
            
               if (dataEle[i].getCodigoEleccion()==codEleccion) {
                   
                   infoEle = dataEle[i].getTituloEleccion();
               }
           }
             
           if(infoEle.equals("")) {
            
                 JOptionPane.showMessageDialog(null,"El codigo de eleccion no existe");
                 System.exit(0);
           }else{
                try {
                   
                  info="";           
                     String asignado="";
                   for (int k = 0; k < dataSigandos.length; k++) {
                          asignado+=   dataSigandos[k]+"-"; 
                          System.err.println(""+asignado);
                    }
                        for (int i = 0; i <data.length; i++) {
                           
                            if (asignado.contains(data[i].getEstatus())) {
                                
                           }else{
                             info+= "Nombre: "+data[i].getNombres_completos()+" - "+ "Codigo: "+data[i].getEstatus()+"\n";                                                           
                          }
                        }
                        
                   
                  
        
               } catch (NullPointerException e) {
                             
               }
                
                String condCandidato = JOptionPane.showInputDialog(null,infoEle+"\n\nCandidatos disponibles\n\n"+info+"\n\nIngrese el codigo del candidato para asignar eleccion");

                   
                try {
                  Fescribe =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_Votaciones,true)));
                for (int i = 0; i <data.length; i++) {
            
                 if(data[i].getEstatus().equals(condCandidato)){
                     
                     Fescribe.write(data[i].getNombres_completos()+","+data[i].getEstatus()+","+infoEle+","+0+"\n");
                     Fescribe.close();
                    }
                  }
               
                
                 
               } catch (Exception e) {
               }
           }   
        //
       
    }
    
    public static Usuario[] BuscarUsuario() throws FileNotFoundException, IOException{
        
        
          Fleer = new FileReader(ARCHIVO_Usuarios);
          leer = new BufferedReader(Fleer);
          Usuario usu = new Usuario();
          
          Usuario[] data = new Usuario[NumLineas(1)];
          int posElemento=0;//controlar las posiciones de cada elemento
          String lineaUsuarios;
          String correo = JOptionPane.showInputDialog(null,"Ingrese el correo a buscar para reiniciar contraseña");
          try {
            
               while((lineaUsuarios=leer.readLine()) !=null){
                 if(lineaUsuarios.split(",")[2].equalsIgnoreCase(correo)){  
                    String nuevaClave = JOptionPane.showInputDialog(null,"Ingrese su nueva contraeña");
                     
                     usu.setNombres_completos(lineaUsuarios.split(",")[0]);
                     usu.setApellidos_completos(lineaUsuarios.split(",")[1]);
                     usu.setCorreo(lineaUsuarios.split(",")[2]);
                     usu.setContrasena(nuevaClave);
                     usu.setRol(lineaUsuarios.split(",")[4]);
                     usu.setEstatus(lineaUsuarios.split(",")[5]);
                    
                    data[posElemento] = usu;//(String correo, String contrasena, String estatus, String rol);
                   
                }else{
                   data[posElemento] = new Usuario(lineaUsuarios.split(",")[0],lineaUsuarios.split(",")[1],lineaUsuarios.split(",")[2],
                                                   lineaUsuarios.split(",")[3],lineaUsuarios.split(",")[4],lineaUsuarios.split(",")[5]);
                   //String correo, String contrasena, String estatus, String rol
                }
                 
                  posElemento++;
              }
              
          }catch(ArrayIndexOutOfBoundsException e) {
            }
           
             
          
              
              new PrintWriter(ARCHIVO_Usuarios).write("");
 
        
          return data;
    }  
   
    public static Votante[] BuscarVotante() throws FileNotFoundException, IOException{
        
        
          Fleer = new FileReader(ARCHIVO_Votante);
          leer = new BufferedReader(Fleer);
          Votante votante = new Votante();
          
          Votante[] data = new Votante[NumLineas(0)];
          int posElemento=0;//controlar las posiciones de cada elemento
          String lineaVotantes;
          String correo = JOptionPane.showInputDialog(null,"Ingrese el correo a buscar para reiniciar contraseña");
          try {
            
          while(( lineaVotantes=leer.readLine()) !=null){
                 if( lineaVotantes.split(",")[3].equalsIgnoreCase(correo)){  
                    String nuevaClave = JOptionPane.showInputDialog(null,"Ingrese su nueva contraeña");
                     
                    votante.setNombres_completos(lineaVotantes.split(",")[0]);
                    votante.setApellidos_completos(lineaVotantes.split(",")[1]);
                    votante.setCUI(lineaVotantes.split(",")[2]);
                    votante.setCorreo(lineaVotantes.split(",")[3]);
                    votante.setSexo(lineaVotantes.split(",")[4]);
                    votante.setFecha_de_nacimiento(lineaVotantes.split(",")[5]);
                    votante.setDireccion_residencia(lineaVotantes.split(",")[6]);
                    votante.setDepartamento_residencia(lineaVotantes.split(",")[7]);
                    votante.setMunicipio_residencia(lineaVotantes.split(",")[8]);
                    votante.setPais_esidencia(lineaVotantes.split(",")[9]);
                    votante.setContrasena(nuevaClave);
                    votante.setRol(lineaVotantes.split(",")[11]);
                    votante.setEstatus(lineaVotantes.split(",")[12]);
                    
                    data[posElemento] = votante;
                   
                }else{
                   data[posElemento] = new Votante(lineaVotantes.split(",")[0],lineaVotantes.split(",")[1],lineaVotantes.split(",")[2],
                                                   lineaVotantes.split(",")[3],lineaVotantes.split(",")[4],lineaVotantes.split(",")[5]
                                                   ,lineaVotantes.split(",")[6],lineaVotantes.split(",")[7],lineaVotantes.split(",")[8]
                                                   ,lineaVotantes.split(",")[9],lineaVotantes.split(",")[10],lineaVotantes.split(",")[11],
                                                   lineaVotantes.split(",")[12]);
                  
                }
             
                 
                  posElemento++;
              }
              
          }catch(ArrayIndexOutOfBoundsException e) {
            }
           
             
          
              
              new PrintWriter(ARCHIVO_Votante).write("");
 
        
          return data;
    }  
     
    public static Votante[] BuscarVotanteFellecimiento() throws FileNotFoundException, IOException{
        
        
          Fleer = new FileReader(ARCHIVO_Votante);
          leer = new BufferedReader(Fleer);
          Votante votante = new Votante();
          
          Votante[] data = new Votante[NumLineas(0)];
          int posElemento=0;//controlar las posiciones de cada elemento
          String lineaVotantes;
          String correo = JOptionPane.showInputDialog(null,"Ingrese el correo a buscar Del votante fellecido");
          try {
            
          while(( lineaVotantes=leer.readLine()) !=null){
                 if( lineaVotantes.split(",")[3].equalsIgnoreCase(correo)){  
                   
                     
                    votante.setNombres_completos(lineaVotantes.split(",")[0]);
                    votante.setApellidos_completos(lineaVotantes.split(",")[1]);
                    votante.setCUI(lineaVotantes.split(",")[2]);
                    votante.setCorreo(lineaVotantes.split(",")[3]);
                    votante.setSexo(lineaVotantes.split(",")[4]);
                    votante.setFecha_de_nacimiento(lineaVotantes.split(",")[5]);
                    votante.setDireccion_residencia(lineaVotantes.split(",")[6]);
                    votante.setDepartamento_residencia(lineaVotantes.split(",")[7]);
                    votante.setMunicipio_residencia(lineaVotantes.split(",")[8]);
                    votante.setPais_esidencia(lineaVotantes.split(",")[9]);
                    votante.setContrasena(lineaVotantes.split(",")[10]);
                    votante.setRol(lineaVotantes.split(",")[11]);
                    votante.setEstatus("Fallecido");
                    
                    data[posElemento] = votante;
                   
                }else{
                   data[posElemento] = new Votante(lineaVotantes.split(",")[0],lineaVotantes.split(",")[1],lineaVotantes.split(",")[2],
                                                   lineaVotantes.split(",")[3],lineaVotantes.split(",")[4],lineaVotantes.split(",")[5]
                                                   ,lineaVotantes.split(",")[6],lineaVotantes.split(",")[7],lineaVotantes.split(",")[8]
                                                   ,lineaVotantes.split(",")[9],lineaVotantes.split(",")[10],lineaVotantes.split(",")[11],
                                                   lineaVotantes.split(",")[12]);
                  
                }
             
                 
                  posElemento++;
              }
              
          }catch(ArrayIndexOutOfBoundsException e) {
            }
           
             
          
              
              new PrintWriter(ARCHIVO_Votante).write("");
 
        
          return data;
    }  
     
    public static  Usuario[] ObtenerRol() throws FileNotFoundException, IOException{
        
        
          Usuario[] data = null;
          int posElemento=0;//controlar las posiciones de cada elemento
          String lineaUsuarios;
          String correo = JOptionPane.showInputDialog(null,"Ingrese su correo para iniciar");
          System.err.println(correo);
          if(correo.contains("votante.com")){
                          
                     Fleer = new FileReader(ARCHIVO_Votante);
                     leer = new BufferedReader(Fleer);
                     Usuario usu = new Usuario();      
                     data = new Usuario[NumLineas(0)];          
              
            try {
                 while((lineaUsuarios=leer.readLine()) !=null){
                     System.out.println(lineaUsuarios.split(",")[3]);
                 if(lineaUsuarios.split(",")[3].equalsIgnoreCase(correo)){  
                                      
                     usu.setRol(lineaUsuarios.split(",")[11]);
                                  
                    data[posElemento] = usu;//(String correo, String contrasena, String estatus, String rol);
                   
                }else{
                   data[posElemento] = usu;
                   //String correo, String con.trasena, String estatus, String rol
                }
                 
                  posElemento++;
              }
         } catch (ArrayIndexOutOfBoundsException  e) {
         }
        }else{
                   Fleer = new FileReader(ARCHIVO_Usuarios);
                     leer = new BufferedReader(Fleer);
                     Usuario usu = new Usuario();      
                     data = new Usuario[NumLineas(1)];          
              
            try {
                 while((lineaUsuarios=leer.readLine()) !=null){
                
                 if(lineaUsuarios.split(",")[2].equalsIgnoreCase(correo)){  
                                      
                     usu.setRol(lineaUsuarios.split(",")[4]);
                                  
                    data[posElemento] = usu;//(String correo, String contrasena, String estatus, String rol);
                   
                }else{
                   data[posElemento] = usu;
                   //String correo, String con.trasena, String estatus, String rol
                }
                 
                  posElemento++;
              }
         } catch (ArrayIndexOutOfBoundsException  e) {
         }
        }          
           
         
          return data;
    }
   
    private static Elecciones ObtenerFechaLmiteIncripcionVotante() throws IOException{
        
       
        try {
            Fleer = new FileReader(ARCHIVO__FECHA_LIMITE);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
            leer = new BufferedReader(Fleer);
            Elecciones ele = new Elecciones(); 
             String lineaFechaLimite;
            try {
                 while((lineaFechaLimite=leer.readLine()) !=null){
                
                    ele.setFechaInicio(lineaFechaLimite.split(",")[0]);
                    ele.setFechaFin(lineaFechaLimite.split(",")[1]);
                    ele.setHoraInicio(lineaFechaLimite.split(",")[2]);
                    ele.setHorafin(lineaFechaLimite.split(",")[3]);
                }
         } catch (ArrayIndexOutOfBoundsException  e) {
         }
                   
          return ele;
    }
    
   
    private static Elecciones ObtenerFechaLmite() throws IOException{
        
       
        try {
            Fleer = new FileReader(ARCHIVO__FECHA_LIMITE);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
            leer = new BufferedReader(Fleer);
            Elecciones ele = new Elecciones(); 
             String lineaFechaLimite;
            try {
                 while((lineaFechaLimite=leer.readLine()) !=null){
                
                    ele.setFechaInicio(lineaFechaLimite.split(",")[0]);
                    ele.setFechaFin(lineaFechaLimite.split(",")[1]);
                    ele.setHoraInicio(lineaFechaLimite.split(",")[2]);
                    ele.setHorafin(lineaFechaLimite.split(",")[3]);
                }
         } catch (ArrayIndexOutOfBoundsException  e) {
         }
                   
          return ele;
    }
    
    private static Elecciones[] ObtenerFechasElecciones() throws FileNotFoundException, IOException{
        
          Fleer = new FileReader(ARCHIVO_elecciones);
          leer = new BufferedReader(Fleer);
          Elecciones elecciones = new Elecciones();
          
          Elecciones[] data = new Elecciones[NumLineas(2)];
          String lineaElecciones;
          int posElemento=0;
            try {
                 while((lineaElecciones=leer.readLine()) !=null){
                       
                       /*
                        String tituloEleccion, String proposito, String descripcion, int codigoEleccion,
                        String fechaInicio, String fechaFin, String horaInicio, String horafin
                       */
                       elecciones.setTituloEleccion(lineaElecciones.split(",")[0]);
                       elecciones.setProposito(lineaElecciones.split(",")[1]);
                       elecciones.setDescripcion(lineaElecciones.split(",")[2]);
                       elecciones.setCodigoEleccion(Integer.parseInt(lineaElecciones.split(",")[3]));
                       elecciones.setFechaInicio(lineaElecciones.split(",")[4]);
                       elecciones.setFechaFin(lineaElecciones.split(",")[5]);
                       elecciones.setHoraInicio(lineaElecciones.split(",")[6]);
                       elecciones.setHorafin(lineaElecciones.split(",")[7]);
                       
                       data[posElemento] = elecciones;;                                
                   posElemento++;
                 }
         } catch (ArrayIndexOutOfBoundsException  e) {
         }
                   
          return data;
    }
    
    private static Usuario[] TempUsuarioModificar() throws FileNotFoundException, IOException{
        
          Fleer = new FileReader(ARCHIVO_Usuarios);
          leer = new BufferedReader(Fleer);
          Usuario usu = new Usuario();
          
          Usuario[] data = new Usuario[NumLineas(1)];
          int posElemento=0;//controlar las posiciones de cada elemento
          String lineaUsuarios;
          String correo = JOptionPane.showInputDialog(null,"Ingrese el correo del usuario a modificar");
          try {
            
               while((lineaUsuarios=leer.readLine()) !=null){
                 if(lineaUsuarios.split(",")[2].equalsIgnoreCase(correo)){  
                    
                    //Maira,Ibañes,mar@persona.com,luisX,Registrador de votante,Activo 
                    String nuevoNombre = JOptionPane.showInputDialog(null,"Ingrese su nuevo nombre");
                    String nuevoApellid = JOptionPane.showInputDialog(null,"Ingrese su nuevo apellido");
                    String nuevoCorreo = JOptionPane.showInputDialog(null,"Ingrese su nuevo correo");
                    String nuevaClave = JOptionPane.showInputDialog(null,"Ingrese su nueva contraeña");
                    JOptionPane.showMessageDialog(null, "Asignar nuevos Roles");
                    String nuevoRoles = JOptionPane.showInputDialog(null,"En caso de ser varios roles en el siguiente"
                            + " \n formato Administrador - Registradores de votantes - Votante - Auditor");
                    int nuevoEstado  = JOptionPane.showConfirmDialog(null,"Desea DesHabilidatar este usuario? ");
                    
                    
                                      
                     usu.setNombres_completos(nuevoNombre);
                     usu.setApellidos_completos(nuevoApellid);
                     usu.setCorreo(nuevoCorreo);
                     usu.setContrasena(nuevaClave);
                     usu.setRol(nuevoRoles);
                     
                     // 0=yes, 1=no, 2=cancel
                     if (nuevoEstado==0) {
                         
                         usu.setEstatus("Desabilidato");
                     }else if(nuevoEstado==1 || nuevoEstado==1){
                         
                          usu.setEstatus("Activo");               
                     }
                    
                    data[posElemento] = usu;//(String correo, String contrasena, String estatus, String rol);
                   
                }else{
                   data[posElemento] = new Usuario(lineaUsuarios.split(",")[0],lineaUsuarios.split(",")[1],lineaUsuarios.split(",")[2],
                                                   lineaUsuarios.split(",")[3],lineaUsuarios.split(",")[4],lineaUsuarios.split(",")[5]);
                   //String correo, String contrasena, String estatus, String rol
                }
                 
                  posElemento++;
              }
              
          }catch(ArrayIndexOutOfBoundsException e) {
            }
           
             
          
              
              new PrintWriter(ARCHIVO_Usuarios).write("");
 
        
          return data;
    }

    private static Votante[] TempVotanteModificar() throws FileNotFoundException, IOException{
        
          Fleer = new FileReader(ARCHIVO_Votante);
          leer = new BufferedReader(Fleer);
          Votante votante = new Votante();
          
          Votante[] data = new Votante[NumLineas(0)];
          int posElemento=0;//controlar las posiciones de cada elemento
          String lineaVotantes;
          String correo = JOptionPane.showInputDialog(null,"Ingrese el correo del votante a modificar");
          try {
            
               while((lineaVotantes=leer.readLine()) !=null){
                 if(lineaVotantes.split(",")[3].equalsIgnoreCase(correo)){  
                    
                    /* modificarr la dirección incluyendo país,
                    departamento, municipio y la dirección en sí, así como el correo electrónico de los votantes */
                    
                    votante.setNombres_completos(lineaVotantes.split(",")[0]);
                    votante.setApellidos_completos(lineaVotantes.split(",")[1]);
                    votante.setCUI(lineaVotantes.split(",")[2]);
                    String nuevoCorreo = JOptionPane.showInputDialog(null,"Ingrese el nuevo correo del votante");
                    votante.setCorreo(nuevoCorreo);
                    votante.setSexo(lineaVotantes.split(",")[4]);
                    votante.setFecha_de_nacimiento(lineaVotantes.split(",")[5]);
                    votante.setDireccion_residencia(JOptionPane.showInputDialog(null,"Ingrese la nueva direccion del votante"));
                    votante.setDepartamento_residencia(JOptionPane.showInputDialog(null, "Ingrese el nuevo departamento del votante"));
                    votante.setMunicipio_residencia(JOptionPane.showInputDialog(null, "Ingrese el nuevo municipio del votante"));
                    votante.setPais_esidencia(lineaVotantes.split(",")[9]);
                    votante.setContrasena(lineaVotantes.split(",")[10]);
                    votante.setRol(lineaVotantes.split(",")[11]);
                    votante.setEstatus(lineaVotantes.split(",")[12]);
                    
                    data[posElemento] = votante;
                   
                }else{
                   data[posElemento] = new Votante(lineaVotantes.split(",")[0],lineaVotantes.split(",")[1],lineaVotantes.split(",")[2],
                                                   lineaVotantes.split(",")[3],lineaVotantes.split(",")[4],lineaVotantes.split(",")[5]
                                                   ,lineaVotantes.split(",")[6],lineaVotantes.split(",")[7],lineaVotantes.split(",")[8]
                                                   ,lineaVotantes.split(",")[9],lineaVotantes.split(",")[10],lineaVotantes.split(",")[11],
                                                   lineaVotantes.split(",")[12]);
                }
                 
                  posElemento++;
              }
              
          }catch(ArrayIndexOutOfBoundsException e) {
          }
                     
              new PrintWriter(ARCHIVO_Votante).write("");
 
        
          return data;
        
    }
    
    public static void CrearVotante(){
       
        
        
                
            Votante personaVotante = new Votante();
         
            /*
                  n nombres completos, apellidos completos,
                 CUI, sexo, fecha de nacimiento, dirección de residencia, departamento de residencia,
                 municipio de residencia, país de residencia
            */
                 personaVotante.setNombres_completos(JOptionPane.showInputDialog(null,"Ingrese nombre del votante"));
                 
                 personaVotante.setApellidos_completos(JOptionPane.showInputDialog(null,"Ingrese el  apellido del votante"));
                 personaVotante.setCUI(JOptionPane.showInputDialog(null,"Ingrese el CUI del votante"));
                 personaVotante.setSexo(JOptionPane.showInputDialog(null,"El genero del votante"));
                 personaVotante.setFecha_de_nacimiento(JOptionPane.showInputDialog(null,"Ingrese la fecha de nacimiento del votante dia/mes/año"));
                 
        if(edadMayor(personaVotante.getFecha_de_nacimiento())){//VALIDAMOS QUE SEA MAYOR DE EDAD
                     
                 personaVotante.setDireccion_residencia(JOptionPane.showInputDialog(null,"Ingrese la direccion de residencia del votante "));
                 personaVotante.setDepartamento_residencia(JOptionPane.showInputDialog(null,"Ingrese el  departamento de residencia del votante"));
                 personaVotante.setMunicipio_residencia(JOptionPane.showInputDialog(null,"Ingrese el  el Municipio de residencia del votante"));
                 personaVotante.setPais_esidencia(JOptionPane.showInputDialog(null,"Ingrese el  pais de residencia del votante"));
                 personaVotante.setContrasena(GenerarContrasena());
                 personaVotante.setRol("Votante");
                 personaVotante.setEstatus("Activo");
                 personaVotante.setCorreo(personaVotante.getNombres_completos().trim()+"@votante.com");
                 
         try{
                Fescribe =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_Votante,true)));
            
                //guarda en la base de datos los archivos 
                 Fescribe.write(personaVotante.getNombres_completos()+","+personaVotante.getApellidos_completos()+","+personaVotante.getCUI()+","+personaVotante.getCorreo()+","+personaVotante.getSexo()+","+
                                personaVotante.getFecha_de_nacimiento()+","+personaVotante.getDireccion_residencia()+","+
                                personaVotante.getDepartamento_residencia()+","+personaVotante.getMunicipio_residencia()+","+personaVotante.getPais_esidencia()+","+
                                personaVotante.getContrasena()+","+personaVotante.getRol()+","+personaVotante.getEstatus()+"\n");            
                    
                JOptionPane.showMessageDialog(null,"datos han sido insertado en la base de datos");
                Fescribe.close();//cerrar el fichero y que los datos se queden escritos
                
            }catch(Exception ex){
               System.out.println(ex.getMessage());
           }  
        }else{
            
             JOptionPane.showMessageDialog(null,"No puedes registrar menores de edad");
        }
    }
    
    public static void CrearEleccion(){
       
                
            Elecciones datosEleccion = new Elecciones();       
         
                 datosEleccion.setTituloEleccion(JOptionPane.showInputDialog(null,"Ingrese titulo de la Eleccion"));    
                 datosEleccion.setProposito(JOptionPane.showInputDialog(null,"Ingrese el  proposito  de la Eleccion"));
                 datosEleccion.setDescripcion(JOptionPane.showInputDialog(null,"Ingrese la descripcion  de la Eleccion"));
                 datosEleccion.setCodigoEleccion((int)(Math.random()*(75-25+1)+25));//generamos el codigo de la ellecion de manera aleatoria
                 datosEleccion.setFechaInicio(JOptionPane.showInputDialog(null,"Ingrese la fecha de inicio de la Eleccion en formato dia/mes/año"));
                 datosEleccion.setFechaFin(JOptionPane.showInputDialog(null,"Ingrese la fecha de finalizacion de la Eleccion en formato dia/mes/año"));
                 datosEleccion.setHoraInicio(JOptionPane.showInputDialog(null,"Ingrese la hora inicio de la Eleccion en formato HH/mm:ss "));
                 datosEleccion.setHorafin(JOptionPane.showInputDialog(null,"Ingrese la hora fin de la Eleccion en formato HH/mm:ss"));

                 
         try{
                Fescribe =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_elecciones,true)));
            
                //guarda en la base de datos los archivos 
                 Fescribe.write(datosEleccion.getTituloEleccion()+","+datosEleccion.getProposito()+","+
                                datosEleccion.getDescripcion()+","+
                                datosEleccion.getCodigoEleccion()+","+datosEleccion.getFechaInicio()+","+
                                datosEleccion.getFechaFin()+","+
                                datosEleccion.getHoraInicio()+","+datosEleccion.getHorafin()+"\n");            
                    
                JOptionPane.showMessageDialog(null,"datos han sido insertado en la base de datos");
                Fescribe.close();//cerrar el fichero y que los datos se queden escritos
                
            }catch(Exception ex){
               System.out.println(ex.getMessage());
           }  
       
    }
    
    public static void TiempoInscripcionActualizar(){
         
         
         Elecciones datosEleccion = new Elecciones();       
         
                 
                 datosEleccion.setFechaInicio(JOptionPane.showInputDialog(null,"Ingrese la fecha de inicio de la Eleccion en formato dia/mes/año"));
                 datosEleccion.setFechaFin(JOptionPane.showInputDialog(null,"Ingrese la fecha de finalizacion de la Eleccion en formato dia/mes/año"));
                 datosEleccion.setHoraInicio(JOptionPane.showInputDialog(null,"Ingrese la hora inicio de la Eleccion en formato HH/mm:ss"));
                 datosEleccion.setHorafin(JOptionPane.showInputDialog(null,"Ingrese la hora fin de la Eleccion en formato HH/mm:ss"));

                 
         try{
                new PrintWriter(ARCHIVO__FECHA_LIMITE).write("");
                Fescribe =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO__FECHA_LIMITE,true)));
            
                //guarda en la base de datos los archivos 
                 Fescribe.write(datosEleccion.getFechaInicio()+","+
                                datosEleccion.getFechaFin()+","+
                                datosEleccion.getHoraInicio()+","+
                                datosEleccion.getHorafin()+"\n");            
                    
                JOptionPane.showMessageDialog(null,"datos han sido insertado en la base de datos"+datosEleccion.getHorafin());
                Fescribe.close();//cerrar el fichero y que los datos se queden escritos
                
            }catch(Exception ex){
               System.out.println(ex.getMessage());
           }  
     }
     
   
    private static Elecciones[]  ObtenerElecciones() throws FileNotFoundException, IOException, ParseException{
        
          Fleer = new FileReader(ARCHIVO_elecciones);
          leer = new BufferedReader(Fleer);
          
          
          Elecciones[] data = new Elecciones[NumLineas(2)];
          int posElemento=0;//controlar las posiciones de cada elemento
          String lineaElecciones;
       
          try {
            
               while((lineaElecciones=leer.readLine()) !=null){
               
                   data[posElemento] = new Elecciones(lineaElecciones.split(",")[0],lineaElecciones.split(",")[1],lineaElecciones.split(",")[2],
                                                      Integer.parseInt(lineaElecciones.split(",")[3]),lineaElecciones.split(",")[4],lineaElecciones.split(",")[5],
                                                      lineaElecciones.split(",")[6],lineaElecciones.split(",")[7]);                       
                  posElemento++;
              }
               
             
            new PrintWriter(ARCHIVO_elecciones).write("");
          }catch(ArrayIndexOutOfBoundsException e) {
          }
          
       
           return data;
    }

    private static Usuario[] ObtenerCandidatos() throws FileNotFoundException, IOException{
        
        
          Fleer = new FileReader(ARCHIVO_candidatos);
          leer = new BufferedReader(Fleer);
          
          
          Usuario[] data = new Usuario[NumLineas(3)];
          int posElemento=0;//controlar las posiciones de cada elemento
          Usuario usuario = new Usuario();
          String lineaElecciones;
          String codigoCandidato = JOptionPane.showInputDialog(null,"Ingrese el codigo del candidato el cual desea modificar los datos");
          try {
            
               while((lineaElecciones=leer.readLine()) !=null){
                   if(lineaElecciones.split(",")[4].equals(codigoCandidato)){
                    /*                     
                          nombre del candidato 
                          apellido del candidato
                          Formacion del candidato
                          experiencial profesional
                          String.valueOf((int)Math.floor(Math.random()*10+1)) GENERAR CODIGO ID
                   */
                   
                   
                    
                    usuario.setNombres_completos(JOptionPane.showInputDialog(null,"Ingrese nuevo nombre del candidato "));
                    usuario.setApellidos_completos(JOptionPane.showInputDialog(null,"Ingrese nuevo apellido del candidato"));
                    usuario.setContrasena(JOptionPane.showInputDialog(null,"nueva Formacion del candidato"));
                    usuario.setRol(JOptionPane.showInputDialog(null,"Ingrese la nueva experiencial profesional"));
                    usuario.setEstatus(lineaElecciones.split(",")[4]);
               
                    data[posElemento] = usuario;
                   
                  }else{
                          /*
                          lineaElecciones.split(",")[0],
                          usuario.setApellidos_completos(lineaElecciones.split(",")[1]);
                          usuario.setContrasena(lineaElecciones.split(",")[2]);
                          usuario.setRol(lineaElecciones.split(",")[3]);
                          usuario.setEstatus(lineaElecciones.split(",")[4]);*/
                   
                          data[posElemento] = new Usuario(lineaElecciones.split(",")[0],
                                                          lineaElecciones.split(",")[1],
                                                          lineaElecciones.split(",")[2],
                                                          lineaElecciones.split(",")[3],
                                                          lineaElecciones.split(",")[4]
                                                             );
                }
                 
                  posElemento++;
              }
              
             
          new PrintWriter(ARCHIVO_candidatos).write("");
          }catch(ArrayIndexOutOfBoundsException e) {
          }
          
       
           return data;
    }
    
    private static  Usuario[] GetCandidatos() throws FileNotFoundException, IOException{
        
          Fleer = new FileReader(ARCHIVO_candidatos);
          leer = new BufferedReader(Fleer);
          
          
          Usuario[] data = new Usuario[NumLineas(3)];
          int posElemento=0;//controlar las posiciones de cada elemento
          String lineaElecciones;
          try {
            
               while((lineaElecciones=leer.readLine()) !=null){
                
                   
                 data[posElemento] = new Usuario(lineaElecciones.split(",")[0],
                                                          lineaElecciones.split(",")[1],
                                                          lineaElecciones.split(",")[2],
                                                          lineaElecciones.split(",")[3],
                                                          lineaElecciones.split(",")[4]
                                                             );
                 
                  posElemento++;
              }
              
             
             }catch(ArrayIndexOutOfBoundsException e) {
          }
           
           return data;
    

        
    }
    
    public static int[] GetAsignados() throws FileNotFoundException, IOException{
       
        Fleer = new FileReader(ARCHIVO_Votaciones);
        leer = new BufferedReader(Fleer);
          
          
          int[] data = new int[NumLineas(4)];
          int posElemento=0;//controlar las posiciones de cada elemento
          String lineaElecciones;
          try {
            
               while((lineaElecciones=leer.readLine()) !=null){
                
                 
                   data[posElemento] = Integer.parseInt(lineaElecciones.split(",")[1]);
                  
                                                   
                  posElemento++;
              }
              
             
             }catch(ArrayIndexOutOfBoundsException e) {
          }
                
           return data;
    

    }
    
    private static  Elecciones[]GetElecciones() throws FileNotFoundException, IOException{
               
          Fleer = new FileReader(ARCHIVO_elecciones);
          leer = new BufferedReader(Fleer);
          
          
          Elecciones[] data = new Elecciones[NumLineas(2)];
          int posElemento=0;//controlar las posiciones de cada elemento
          String lineaElecciones;
       
          try {
            
               while((lineaElecciones=leer.readLine()) !=null){
               
                   data[posElemento] = new Elecciones(lineaElecciones.split(",")[0],lineaElecciones.split(",")[1],lineaElecciones.split(",")[2],
                                                      Integer.parseInt(lineaElecciones.split(",")[3]),lineaElecciones.split(",")[4],lineaElecciones.split(",")[5],
                                                      lineaElecciones.split(",")[6],lineaElecciones.split(",")[7]);                       
                  posElemento++;
              }
               
             
          }catch(ArrayIndexOutOfBoundsException e) {
          }
          
       
           return data;
    }
    
    private static Usuario[] ListaCandidatos() throws FileNotFoundException, IOException{
        
          Fleer = new FileReader(ARCHIVO_candidatos);
          leer = new BufferedReader(Fleer);
          
          
          Usuario[] data = new Usuario[NumLineas(3)];
          int posElemento=0;//controlar las posiciones de cada elemento
          String lineaElecciones;
          try {
            
               while((lineaElecciones=leer.readLine()) !=null){
                
                   
                 data[posElemento] = new Usuario(lineaElecciones.split(",")[0],
                                                          lineaElecciones.split(",")[1],
                                                          lineaElecciones.split(",")[2],
                                                          lineaElecciones.split(",")[3],
                                                          lineaElecciones.split(",")[4]
                                                             );
                 
                  posElemento++;
              }
              
             
             }catch(ArrayIndexOutOfBoundsException e) {
          }
           
           new PrintWriter(ARCHIVO_candidatos).write("");
           return data;
    
    }
    
    public static String Login (){
        
        try {
            
             Usuario[] data = ObtenerRol() ; 
             Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_Usuarios,true)));
                 
               if(data[0].getRol()!=null){             
                  return data[0].getRol(); 
               }
           
            
        } catch (IOException e) {
        }
         
           return "undefine";
        }
    
    
    private static void ModificarContrasena() throws IOException{
            
          Usuario[] data = BuscarUsuario();    
          Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_Usuarios,true)));
          
          
   
          for (int i = 0; i < data.length; i++) {
                 Fescribe.write(data[i].getNombres_completos()+","+data[i].getApellidos_completos()+","+data[i].getCorreo()+","+
                                data[i].getContrasena()+","+
                                data[i].getRol()+","+data[i].getEstatus()+"\n");         
          }
          
          JOptionPane.showMessageDialog(null,"contraseña cambiada");
          Fescribe.close();//cerrar el fichero y que los datos se queden escritos
         
        
     }
    
    private static void ModificarContrasenaVotante() throws IOException{
            
          Votante[] data = BuscarVotante();    
          Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_Votante,true)));
          
          
   
          for (int i = 0; i < data.length; i++) {
                   Fescribe.write(
                                data[i].getNombres_completos()+","+
                                data[i].getApellidos_completos()+","+
                                data[i].getCUI()+","+
                                data[i].getCorreo()+","+
                                data[i].getSexo()+","+
                                data[i].getFecha_de_nacimiento()+","+
                                data[i].getDireccion_residencia()+","+
                                data[i].getDepartamento_residencia()+","+
                                data[i].getMunicipio_residencia()+","+
                                data[i].getPais_esidencia()+","+
                                data[i].getContrasena()+","+
                                data[i].getRol()+","+
                                data[i].getEstatus()
                                +"\n");              
          }
          
          JOptionPane.showMessageDialog(null,"Contraseña del votante actualizada");
          Fescribe.close();//cerrar el fichero y que los datos se queden escritos
         
        
     }

    public  static void EliminarEleccion() throws IOException, FileNotFoundException{
        
             
        try {
            
            
            Elecciones[] data = ObtenerElecciones();
            Date fechaActual = new Date(); 
            Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_elecciones,true)));
            int codigoEleccion = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese El codigo de la eleccion"));
            
            /*
            String tituloEleccion, String proposito, String descripcion,
            int codigoEleccion, String fechaInicio,
            String fechaFin, String horaInicio, String horafin
            */
                                 
       
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                 
            System.err.println(fechaActual.before(formato.parse("25/10/2023")));
            for (int i = 0; i < data.length; i++) {
               
                if(data[i].getCodigoEleccion()==codigoEleccion && fechaActual.before(formato.parse(data[i].getFechaInicio()))){
                        System.err.println(
                                data[i].getTituloEleccion()+","+
                                data[i].getProposito()+","+
                                data[i].getDescripcion()+","+
                                data[i].getCodigoEleccion()+","+
                                data[i].getFechaInicio()+","+
                                data[i].getFechaFin()+","+
                                data[i].getHoraInicio()+","+
                                data[i].getHorafin()
                                +"\n");
               }else{
                    
                    Fescribe.write(
                                data[i].getTituloEleccion()+","+
                                data[i].getProposito()+","+
                                data[i].getDescripcion()+","+
                                data[i].getCodigoEleccion()+","+
                                data[i].getFechaInicio()+","+
                                data[i].getFechaFin()+","+
                                data[i].getHoraInicio()+","+
                                data[i].getHorafin()
                                +"\n");
                }
            }       

            
            
            Fescribe.close();//cerrar el fichero y que los datos se queden escritos
            //JOptionPane.showMessageDialog(null,"Eleccion eliminada");
            //}
     
        }catch(NullPointerException b){
            System.out.println(b.getCause());
            
        
        }catch (ParseException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }catch(ArrayIndexOutOfBoundsException x){
            
        }
  }     
    
    public static boolean FechaLimiteConfiguracionEleccion() throws IOException{
        try {
            
            
            Elecciones[] data = ObtenerFechasElecciones();
            Date fechaActual = new Date(); 
            Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_elecciones,true)));
                                    
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            int codigoEleccion = Integer.parseInt(JOptionPane.showInputDialog(null,""));
     
                 
            for (int i = 0; i < data.length; i++) {
               
                if(data[i].getCodigoEleccion()==codigoEleccion && fechaActual.before(formato.parse(data[i].getFechaInicio()))){
                      
                      return true;
               }
            }       

            
            
            Fescribe.close();//cerrar el fichero y que los datos se queden escritos
            //JOptionPane.showMessageDialog(null,"Eleccion eliminada");
            //}
     
        }catch(NullPointerException b){
            System.out.println(b.getCause());
            
        
        }catch (ParseException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }catch(ArrayIndexOutOfBoundsException x){
            
        }
        
        return false;
    }
    
    private static void VotanteFellecido() throws IOException {
       
          Votante[] data = BuscarVotanteFellecimiento();    
          Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_Votante,true)));
          
          
   
          for (int i = 0; i < data.length; i++) {
                   Fescribe.write(
                                data[i].getNombres_completos()+","+
                                data[i].getApellidos_completos()+","+
                                data[i].getCUI()+","+
                                data[i].getCorreo()+","+
                                data[i].getSexo()+","+
                                data[i].getFecha_de_nacimiento()+","+
                                data[i].getDireccion_residencia()+","+
                                data[i].getDepartamento_residencia()+","+
                                data[i].getMunicipio_residencia()+","+
                                data[i].getPais_esidencia()+","+
                                data[i].getContrasena()+","+
                                data[i].getRol()+","+
                                data[i].getEstatus()
                                +"\n");              
          }
          
          JOptionPane.showMessageDialog(null,"Fellecimiento registrado");
          Fescribe.close();//cerrar el fichero y que los datos se queden escritos
         
    }
    
    private static void EliminarCandidato() throws IOException{
         try {
            
            
            Usuario[] data = ListaCandidatos();
            Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_candidatos,true)));
            int codigoCandidato = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese El codigo del candidato a eliminar"));
            
            /*
                    nombre del candidato
                    apellido del candidato
                    Formacion del candidato
                    experiencial profesional
                    Id generado de manera automatica          
            */
            
         
            for (int i = 0; i < data.length; i++) {
               
                if(Integer.parseInt(data[i].getEstatus())==codigoCandidato){
                        System.err.println(
                                data[i].getNombres_completos()+","+
                                data[i].getApellidos_completos()+","+
                                data[i].getContrasena()+","+
                                data[i].getRol()+","+
                                data[i].getEstatus()
                                +"\n");
               }else{
                    
                    Fescribe.write(
                                data[i].getNombres_completos()+","+
                                data[i].getApellidos_completos()+","+
                                data[i].getContrasena()+","+
                                data[i].getRol()+","+
                                data[i].getEstatus()
                                +"\n");
                }
            }       

            
            
            Fescribe.close();//cerrar el fichero y que los datos se queden escritos
            //JOptionPane.showMessageDialog(null,"Eleccion eliminada");
            //}
     
        }catch(NullPointerException b){
            System.out.println(b.getCause());
            
        
        }catch(ArrayIndexOutOfBoundsException x){
            
        }
    }
    private static int NumLineas(int document) throws IOException{
        
       int lineas=0;
        
       if(document==1){
             lineas = (int) Files.lines(Paths.get(ARCHIVO_Usuarios)).count();
             return lineas;
       }
       
        if(document==2){
             lineas = (int) Files.lines(Paths.get(ARCHIVO_elecciones)).count();
             return lineas;
       }
        
        if(document==3){
             lineas = (int) Files.lines(Paths.get(ARCHIVO_candidatos)).count();
             return lineas;
       }
        
        if(document==4){
             lineas = (int) Files.lines(Paths.get(ARCHIVO_Votaciones)).count();
             return lineas;
       }
       
       lineas = (int) Files.lines(Paths.get(ARCHIVO_Votante)).count();
       return lineas;
        
    }
     
    private static Boolean edadMayor(String year){
         
        int confir = 0;
              
        String sSubCadena = year.substring(6,10);
           
        ZoneId timeZone = ZoneId.systemDefault();
        LocalDate getLocalDate = date.toInstant().atZone(timeZone).toLocalDate();
       
        
        confir = getLocalDate.getYear() - Integer.parseInt(sSubCadena);
    
        if(confir>=18){
            
            return true;
        }
        
        return false;
     }
     
    private  static boolean FechaLimite(){
        
            boolean respuesta = false;
            Date fechaActual = new Date();          
            try {
                System.err.println(fechaActual);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:");
            Date fecha1,fecha2;
            
            fecha1 = formato.parse(ObtenerFechaLmiteIncripcionVotante().getFechaInicio()+" "+ObtenerFechaLmiteIncripcionVotante().getHoraInicio());
            fecha2 = formato.parse(ObtenerFechaLmiteIncripcionVotante().getFechaFin()+" "+ObtenerFechaLmiteIncripcionVotante().getHoraInicio());
                
            if(fecha1.before(fechaActual) && fecha2.after(fechaActual) ){
               return respuesta = true;
            }
            
            } catch (ParseException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            }
          
         return respuesta;
    }
    
    private static String GenerarContrasena(){
        
      
	/**
	 * Genera una contraseña aleatoria con 8 caracteres de longitud.
	 * La contraseña se compone de letras mayúsculas, minúsculas y números.
	 *
	 * @return Cadena String con la contraseña generada aleatoriamente.
	 */
 
		StringBuilder password = new StringBuilder();
		Random azar = new Random();
 
		for (int i = 0; i < 16; i++) {
 
			//Generamos un random entre 0 y 2 para elegir entre uno de los tres sets disponibles
			switch(azar.nextInt(3)) {
			case 0: //Mayúsculas
				password.append(MAYUSCULAS.charAt(azar.nextInt(MAYUSCULAS.length())));
				break;
			case 1: //Minúsculas
				password.append(MINUSCULAS.charAt(azar.nextInt(MINUSCULAS.length())));
				break;
			case 2: //Números
				password.append(NUMEROS.charAt(azar.nextInt(NUMEROS.length())));
			}
		}
		//Contraseña generada, la retornamos
       return password.toString();
	
    
    }
    
    public static  String  TiposRoles(String cadena){
        
          String [] vect = cadena.split("-");
          String roles="";
          int opc = 0;
          String rol = "";
          
          
          if (vect.length>1) {
               JOptionPane.showMessageDialog(null,"El sistema detecto que posees varios usuarios que rol deseas ejecutar");
               
               for (int i = 0; i <vect.length; i++) {
                 System.err.println(i+1+". "+vect[i]);
                  roles+= i+1+". "+vect[i]+"\n";
               }
               
              opc = Integer.parseInt(JOptionPane.showInputDialog(null,roles));
                
               for (int i = 0; i <vect.length; i++) {
                  if(opc==i+1){
                      
                      System.err.println(opc +" selected "+  vect[i]);
                      rol =  vect[i];
                  }
               }          
          }else{
            
              return cadena;
           }
                                    
          return rol;
           
     }

    private static void ModificarUsuarios() {
       
        try {
            
           Usuario[] data = TempUsuarioModificar();    
          Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_Usuarios,true)));
          
          
   
          for (int i = 0; i < data.length; i++) {
                 Fescribe.write(data[i].getNombres_completos()+","+data[i].getApellidos_completos()+","+data[i].getCorreo()+","+
                                data[i].getContrasena()+","+
                                data[i].getRol()+","+data[i].getEstatus()+"\n");         
          }
          
          
          Fescribe.close();//cerrar el fichero y que los datos se queden escritos
         
            
        } catch (IOException e) {
        }
    }
    
    private static void ModificarVotante() {
       
        try {
            
            Votante[] data = TempVotanteModificar();    
            Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_Votante,true)));
          

           
          for (int i = 0; i < data.length; i++) {
                          Fescribe.write(
                                data[i].getNombres_completos()+","+
                                data[i].getApellidos_completos()+","+
                                data[i].getCUI()+","+
                                data[i].getCorreo()+","+
                                data[i].getSexo()+","+
                                data[i].getFecha_de_nacimiento()+","+
                                data[i].getDireccion_residencia()+","+
                                data[i].getDepartamento_residencia()+","+
                                data[i].getMunicipio_residencia()+","+
                                data[i].getPais_esidencia()+","+
                                data[i].getContrasena()+","+
                                data[i].getRol()+","+
                                data[i].getEstatus()
                                +"\n");         
          }
          JOptionPane.showMessageDialog(null,"Datos del votante actualizado");
          
          Fescribe.close();//cerrar el fichero y que los datos se queden escritos
         
            
        } catch (IOException e) {
        }
    }

    private static void  ModificarCandidato() throws IOException{
        
        
        try{
            Usuario[] data = ObtenerCandidatos();
            Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_candidatos,true)));
    
            /*
            
                   usuario.setNombres_completos(lineaElecciones.split(",")[0]);
                    usuario.setApellidos_completos(lineaElecciones.split(",")[1]);
                    usuario.setContrasena(lineaElecciones.split(",")[2]);
                    usuario.setRol(lineaElecciones.split(",")[3]);
                    usuario.setEstatus(lineaElecciones.split(",")[4]);
            */
                 
            for (int i = 0; i < data.length; i++) {
               
                        Fescribe.write(
                                data[i].getNombres_completos()+","+
                                data[i].getApellidos_completos()+","+
                                data[i].getContrasena()+","+
                                data[i].getRol()+","+
                                data[i].getEstatus()
                                +"\n");
               }
              
            
            Fescribe.close();//cerrar el fichero y que los datos se queden escritos
            //JOptionPane.showMessageDialog(null,"Eleccion eliminada");
            //}
     
          }catch(NullPointerException b){
            System.out.println(b.getCause());
            
        
          }catch(ArrayIndexOutOfBoundsException x){
            
          }

    }
    
    public static void MenuPrincipal(String opc) {
        
        if(opc.contains("Administrador")){
           int opcSesion = Integer.parseInt(JOptionPane.showInputDialog(null,"Que opcion desea realizaR? SEÑOR(A) ADMINISTRADOR"
                    + "                        \n1. Gestion Usuarios"
                    + "                        \n2. Gestion Elecciones"));
            
           if(opcSesion==1){
                 Menu();   
           }else if(opcSesion==2){
                MenuAdminitrarEleciones();
           }else{
               JOptionPane.showMessageDialog(null,"POR OPCION NO VALIDA POR SEGURIDAD SE CERRARA!! EL SISTEMA");
               System.exit(0);
           }
                        
          }
          if(opc.contains("Votante")){
              try {
               JOptionPane.showMessageDialog(null,"Vamos a votar");
               String data[]= VotarEleccion();;
               String info="";
                
        
                for (int i = 0; i <data.length; i++) {
                    
                    info +=""+i+" ."+data[i]+" \n";
                    
                  
                }
                  JOptionPane.showMessageDialog(null,"Que eleccion desea votar \n"+info);
            } catch (IOException ex) {
                Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
           
          if(opc.contains("Registrador de votantes")){
              
               JOptionPane.showMessageDialog(null,"Gestionar votantes");
               MenuVotante();
          } 
          
          if(opc.contains("Auditor")){
               JOptionPane.showMessageDialog(null,"Auditor asegurarse de que el proceso sea limpio y transparente");
          }
    }

    public static String[] VotarEleccion() throws FileNotFoundException, IOException{
        
          Fleer = new FileReader(ARCHIVO_Votaciones);
          leer = new BufferedReader(Fleer);
          
          
          String[] data = new String[NumLineas(4)];
          int posElemento=0;//controlar las posiciones de cada elemento
          String lineaElecciones;
       
          try {
            
               while((lineaElecciones=leer.readLine()) !=null){
               
                   data[posElemento] = lineaElecciones.split(",")[0];
                   data[posElemento]= lineaElecciones.split(",")[2];
                                                     ;                      
                  posElemento++;
                
              }
               
             
          }catch(ArrayIndexOutOfBoundsException e) {
          }
          
         return data;

    }
    
    public static  void Menu(){
        
        int menu = 0;
        
            
                menu = Integer.parseInt(JOptionPane.showInputDialog(null,"Registro de Usuarios "
                + "                                  \n\n 1. Crear Usuario del sistema"
                + "                                    \n 2. Modificar Usuario del sistema"
                + "                                    \n 3. Riniciar contraseña"
                + "                                    \n 3. Riniciar contraseña"
                + "                                    \n5. Salir")); 
                
                
             if(menu==1){
           
                 CrearUser();
                 Menu();
             }
               
            if(menu==2){
                 ModificarUsuarios();
                 Menu();
            }
                
            if(menu==3){
            
            try {
                ModificarContrasena();
                Menu();
            } catch (IOException ex) {
                Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
   
            if (menu==4) {
                
                MenuPrincipal(TiposRoles(Login()));
                
            }
                    

}

    
    public static  void MenuAdminitrarEleciones(){
        
        int menu = 0;
        
            
                menu = Integer.parseInt(JOptionPane.showInputDialog(null,"Sistema de Votaciones" 
                + "                                    \n Administracion de elecciones"
                + "                                  \n\n 1. Gestionar Elecciones"
                + "                                    \n 2. Gestionar Candidatos"
                + "                                    \n 3. Configurar opciones de elecciones"
                + "                                    \n 4. Salir")); 
                
                
             if(menu==1){
           
                MenuGestionElecciones();  
                MenuAdminitrarEleciones();
             }
               
             if(menu==2){
               
                 MenuGestionarCandidatos();
                 MenuGestionElecciones();
             }
                
            if(menu==3){
            
              MenuCofigurarElecciones();
              MenuGestionElecciones();
            }
   
            if (menu==4) {
                
                MenuPrincipal(TiposRoles(Login()));
                
            }
    }      
    
    public static void MenuVotante(){
        
        int menu = 0;
        
            
                menu = Integer.parseInt(JOptionPane.showInputDialog(null,"Registro de Votantes "
                + "                                  \n\n 1. Agragar Votante"
                + "                                    \n 2. Modificar Votante"
                + "                                    \n 3. Reiniciar contraseña del votante"
                + "                                    \n 4. Registrar fallecimiento del votante"
                + "                                    \n 5. Salir ")); 
                
                
             if(menu==1){
                 if (FechaLimite()) {
                       CrearVotante();
                       MenuVotante();
                 }
                 JOptionPane.showMessageDialog(null,"AUN NO ES LA FECHA DE REGISTRO");
                 MenuVotante();
             }
               
            if(menu==2){
                 ModificarVotante();
                 MenuVotante();
            }
                
            if(menu==3){
                                          
               try {
                   ModificarContrasenaVotante();
                   MenuVotante();
               
               }catch (IOException ex) {
                 Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
   
           
            if(menu==4){
                
                try {
                   VotanteFellecido();
                   MenuVotante();
               
               }catch (IOException ex) {
                 Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
            
            if(menu==5) {
               MenuPrincipal(TiposRoles(Login()));                      
            }
    }
        
    public static void MenuGestionElecciones(){
         
         int menu = Integer.parseInt(JOptionPane.showInputDialog(null,"Administracion de elecciones"
                + "                                  \n\n 1. Crear Eleccion"
                + "                                    \n 2. Definir Tiempo de inscripcion de votante"
                + "                                    \n 3. Eliminar Eleccion "
                + "                                    \n 4. volver"
                + "                                    \n 4. salir"));
                 
                
                
             if(menu==1){
           
                 CrearEleccion();
                 MenuGestionElecciones();
             }
               
            if(menu==2){
                 
                 TiempoInscripcionActualizar();
                 MenuGestionElecciones();
            }
                
            if(menu==3){
                                          
               try {
                   EliminarEleccion();
                   MenuGestionElecciones();
               
               }catch (IOException ex) {
                 Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
   
           
            if(menu==4){
                
              MenuAdminitrarEleciones();
            }
            
            if(menu==5) {
               MenuPrincipal(TiposRoles(Login()));  
               System.exit(0);
            }
         
     }
    

    public static void MenuGestionarCandidatos(){
        
         int menu = Integer.parseInt(JOptionPane.showInputDialog(null,"Administracion de elecciones"
                + "                                  \n\n 1. Crear candidato"
                + "                                    \n 2. Modificar datos del candidato"
                + "                                    \n 3. Eliminar Candidato "
                + "                                    \n 4. volver "
                + "                                    \n 5. Salir ")); 
                
                
             if(menu==1){
           
                 CrearCandidato();
                 MenuGestionarCandidatos();
             }
               
            if(menu==2){
                 
             try {
                 ModificarCandidato();
                 MenuGestionarCandidatos();
             } catch (IOException ex) {
                 Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
             }
            }
                
            if(menu==3){
                                          
               try {
                   EliminarCandidato();
                   MenuGestionarCandidatos();
               
               }catch (IOException ex) {
                 Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
   
           
            if(menu==4){
                
              MenuAdminitrarEleciones();
            }
            
            if(menu==5) {
               MenuPrincipal(TiposRoles(Login()));  
               System.exit(0);
            }
    }


    public static  void MenuCofigurarElecciones(){
        
        int menu = 0;
        
            
                menu = Integer.parseInt(JOptionPane.showInputDialog(null,"Sistema de Votaciones" 
                + "                                    \n Administracion de elecciones"
                + "                                  \n\n 1. Asiganar candidato a eleccion"
                + "                                    \n 2. Gestionar Candidatos"
                + "                                    \n 3. Configurar opciones de elecciones"
                + "                                    \n 4. Salir")); 
                
                
             if(menu==1){
           
            try {
                if(FechaLimiteConfiguracionEleccion()){
                    AsignarCandidatosEleccion();
                    MenuCofigurarElecciones();
                }
                JOptionPane.showMessageDialog(null,"La eleccion ingresa se encuentra en curso...");
                MenuCofigurarElecciones();
            } catch (IOException ex) {
                Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            }
             }
               
             if(menu==2){
               
                 
                 MenuCofigurarElecciones();
             }
                
            if(menu==3){
            
              MenuCofigurarElecciones();
            }
   
            if (menu==4) {
                
                MenuPrincipal(TiposRoles(Login()));
                
            }
    }      
}
