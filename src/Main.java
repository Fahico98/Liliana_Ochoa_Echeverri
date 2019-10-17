
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
   
   public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("*** BIENVENIDO ***\n");
      
      buclePrincipal:
      while(true){
         System.out.print("Número de estudiantes del grupo: ");
         int n = Integer.parseInt(br.readLine());
         String[] vecIdentif = new String[n];
         String[] vecNombres = new String[n];
         float[][] matrizNotas = new float[n][6];

         System.out.println("");

         int k = 0;

         while(k < n){
            System.out.println("Datos del estudiante número " + (k + 1) + ".");
            System.out.print("Nombre: ");
            String nombre = br.readLine();
            System.out.print("Identificación: ");
            String id = new String(br.readLine());
            float[] notas = new float[5];
            for (int i = 0; i < 5; i++) {
               System.out.print("Nota " + (i + 1) + ": ");
               float nota = Float.parseFloat(br.readLine());
               notas[i] = nota;
            }
            Estudiante estudiante = new Estudiante(id, nombre);
            vecIdentif[k] = estudiante.getId();
            vecNombres[k] = estudiante.getNombre();
            float sum = 0;
            for (int i = 0; i < 5; i++) {
               sum += notas[i];
               matrizNotas[k][i] = notas[i];
            }
            matrizNotas[k][5] = sum/5;
            k++;
            System.out.println("");
         }

         while(true){
            System.out.println("OPCIONES DEL PROGRAMA:");
            System.out.print(
               "  1. Mostrar listado con identificación y nota definitiva de todos los estudiantes.\n" +
               "  2. Mostrar listado con nombres y nota definitiva de los estudiantes que ganaron.\n" +
               "  3. Mostrar listado con identificación y nota definitiva de los estudiantes que perdieron.\n" +
               "  4. Mostrar la nota promedio del grupo.\n" +
               "  5. Buscar estudiante por identificación.\n" +
               "  6. Cambiar de grupo.\n" +
               "  7. Salir.\n\n-> "
            );
            String opcion = br.readLine();
            System.out.println("");
            switch(opcion){
               case "1":
               case "1.":
                  for (int i = 0; i < n; i++) {
                     System.out.println(
                        "Identificación: " +
                        vecIdentif[i] +
                        ", Nota definitiva: " +
                        String.format("%.2f", matrizNotas[i][5]) +
                        "."
                     );
                  }
                  break;
               case "2":
               case "2.":
                  for (int i = 0; i < n; i++) {
                     if(matrizNotas[i][5] >= 3.0){
                        System.out.println(
                           "Nombre: " + 
                           vecNombres[i] +
                           ", Nota definitiva: " +
                           String.format("%.2f", matrizNotas[i][5]) +
                           "."
                        );
                     }
                  }
                  break;
               case "3":
               case "3.":
                  for (int i = 0; i < n; i++) {
                     if(matrizNotas[i][5] < 3.0){
                        System.out.println(
                           "Identificación: " +
                           vecIdentif[i] +
                           ", Nota definitiva: " +
                           String.format("%.2f", matrizNotas[i][5]) +
                           "."
                        );
                     }
                  }
                  break;
               case "4":
               case "4.":
                  float acumulado = 0;
                  for (int i = 0; i < n; i++) {
                     acumulado += matrizNotas[i][5];
                  }
                  float promedio = acumulado/n;
                  System.out.println("Promedio: " + String.format("%.2f", promedio) + ".");
                  break;
               case "5":
               case "5.":
                  System.out.print("Identificación: ");
                  String id = br.readLine();
                  boolean encontrado = false;
                  int index = 0;
                  for (int i = 0; i < n; i++) {
                     if(id.compareTo(vecIdentif[i]) == 0){
                        encontrado = true;
                        index = i;
                        break;
                     }
                  }
                  if(encontrado){
                     System.out.println(
                        "Nombre: " + 
                        vecNombres[index] +
                        ", Nota definitiva: " +
                        String.format("%.2f", matrizNotas[index][5]) +
                        "."
                     );
                  }else{
                     System.out.println("No se encontró ningún estudiante con la identificación ingresada...");
                  }
                  break;
               case "6":
               case "6.":
                  continue buclePrincipal;
               case "7":
               case "7.":
                  System.exit(0);
               default:
                  System.out.println("Opción no valida. Por favor intentelo de nuevo...");
            }
            System.out.println("");
         }
      }
   }
}
