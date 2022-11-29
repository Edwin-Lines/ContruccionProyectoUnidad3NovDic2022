/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import com.mycompany.frontend.view;

import unidad_3.backend.Empleado;
import unidad_3.backend.Exceptions.EstructuraException;
import unidad_3.backend.Exceptions.LlaveSinValorException;
import unidad_3.backend.LectorConvertorJSON;
import unidad_3.backend.ModificadorArchivosJSON;

/**
 *
 * @author betoh
 */
public class Intermediario {
      private static String nombreArchivo;
      private static String directorio;
      LectorConvertorJSON lector= new LectorConvertorJSON();
      ArrayList<Empleado> empleados= new ArrayList();
      private static view nView;

    
      public Intermediario(String nombreArchivo, String directorio) {
         this.nombreArchivo = nombreArchivo;
         this.directorio = directorio;
         envioArrayList();
      }

      public Intermediario(){
      }
      
      public void updateJSON(Empleado empleados){
            String dir=directorio+nombreArchivo;
            ModificadorArchivosJSON modificador= new ModificadorArchivosJSON(dir);
            if(modificador.modificarRegistro(empleados)){
                  closeView();
                  envioArrayList();
            }else{
                  JOptionPane.showMessageDialog(nView, "No es encuentra ningun dato con ese ID");
            }
            
      }

      public void envioArrayList(){
         try {
               empleados=lector.lecturaArchivo(nombreArchivo, directorio);
               nView= new view(empleados);
               nView.setVisible(true);
               
         } catch (EstructuraException ex) {
               Logger.getLogger(Intermediario.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
               Logger.getLogger(Intermediario.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ParseException ex) {
               Logger.getLogger(Intermediario.class.getName()).log(Level.SEVERE, null, ex);
         } catch (LlaveSinValorException ex) {
               Logger.getLogger(Intermediario.class.getName()).log(Level.SEVERE, null, ex);
         }
      }

      public void closeView() {
            nView.setVisible(false);
            nView.dispose();
      }
      
      

      

   public static void main(String[] args) {
      new Intermediario("estructuraCorrecta.json", "nov_dic2022/files/");
   }
}
