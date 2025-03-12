package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;
    /**
     * 
     * @param args
     * @author Daniel Montenegro Gil
     * @Version 1.1
     */
    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    System.out.println("\nOpcion invalida, intenta nuevamente.");
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];

    }

    /**
     * Descripcion: Este metodo se encarga de solicitar los datos de cada una de las referencias que ingreso
     * el usuario, se establece automatica mente el valor y unidad de cada una en 0
     * Se pregunta por el valor y unidad de cada producto y se almacena en el arreglo previamente establecido
     * pre: se declaran las variables de unidad y precio
     * pos: se actualiza el precio y unidad segun la posicion del indice "i"
     */
    public static void solicitarDatos(){
            double precio=0.0;
            int unidad=0;
            for (int i = 0; i < precios.length && i < unidades.length; i++){
                System.out.println("Digite los datos del objeto referencia " + (i+1));
            
                do{
                System.out.println("Digite el precio del objeto referencia "+(i+1)+":");
                precio = reader.nextDouble();
                }while(precio<0); 
                precios[i]=precio;
            
                do{
                System.out.println("Digite la cantidad de unidades del objeto referencia "+(i+1)+":" );
                unidad=reader.nextInt();
                }while(unidad<0); 
                
                unidades[i]=unidad;
            }
        }

    /**
     * Descripcion: Este metodo se encarga de calcular las unidades totales vendidas
     * inicializando una variable "totalUnidades" en 0 donde se ira actualizando constantemente
     * con la ayuda del "for" que es utilizado para evaluar la cantidad de unidades por producto
     * pre: se inicializa la variable "totalUnidades"
     * pos: se actualiza la variable "totalUnidades" segun el for 
     * @return el valor total de unidades
     */
    public static int calcularTotalUnidadesVendidas(){
        int totalUnidades=0;
        for (int i=0; i<unidades.length; i++){
            totalUnidades += unidades[i];
        }
        return totalUnidades;

    }

    /**
     * Descripcion: Este metodo calcula el promedio total de los productos donde inicializa 
     * una variable "promedio" en 0 para realizar la suma total, donde
     * se multiplica la cantidad de unidades y el valor de ese producto segun el indice de "i".
     * Despues esta suma es dividida por la cantidad total de unidades del arreglo
     * todo esto es evaluado por el for que se encarga de revisar cada producto segun sus precios y unidades
     * pre: se inicializa la variable "promedio" en 0
     * pre: se inicializa la variable "sumTotal" en 0
     * pos: se actualiza la variable "sumTotal" segun la cantidad de unidades y el precio de los productos ingresados por el usuario 
     * y que posteriormente fueron evaluados en el for
     * pos: se usa la variable ya actualizada "sumTotal" para luego ser dividida por la cantidad de unidades totales
     * todo esto dentro de la variable promedio
     * pos: se actualiza la variable promedio en la operacion anterior
     * @return el promedio calculado
     */
    public static double calcularPrecioPromedio(){
        double promedio = 0;
        double sumTotal = 0;
        for (int i=0; i<unidades.length && i<precios.length;i++){
            sumTotal = sumTotal + (unidades[i]*precios[i]);
            promedio = sumTotal/unidades.length;
        }
        return promedio;

    }

    /**
     * Descripcion: Este metodo se encarga calcular las ventas totales del dia
     * segun el metodo anterior podemos realizar el mismo apartado de sumas totales sin la parte de division
     * para obtener solo la suma total
     * pre: se inicializa la variable "ventasTotales" en 0
     * pos: se actualiza la variable "ventasTotales" 
     * @return la suma total de todos los productos
     */
    public static double calcularVentasTotales(){
        double ventasTotales = 0;
        for (int i=0; i<unidades.length && i<precios.length;i++){
            ventasTotales = ventasTotales + (unidades[i]*precios[i]);
        }                         
        return ventasTotales;

    }
    /**
     * Descripcion: Antes de inicializar el metodo, se le pregunta al usuario cual es el limite a establecer
     * una vez inicializado, el metodo se encarga de revisar por producto cual de estos supera el limite
     * pre: se inicializa la variable "superaLimite" en 0
     * pre: se evalua cada valor de el array precios con un condicional para ver que valor supera el limite
     * pos: el valor que supere el limite sera sumado a la variable "superaLimite"
     * pos: al acabar la secuencia del for se retorna la variable "superaLimite"
     * @param limite
     * @return retorna la cantidad de productos que sobrepasan el limite
     */
    public static int consultarReferenciasSobreLimite(double limite){
        int superaLimite=0;
        for (int i=0; i<precios.length; i++){
            if (precios[i]>limite){
                superaLimite=superaLimite+1;
            }else{
                System.out.println("No hubo ningun producto que rebasara el limite");
            }
        }
        return superaLimite;

    }

}
