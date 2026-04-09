package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static List<Registro> registros = new ArrayList<>() {
    };


    /**
     * Debes crear una colección de 100 registros (genérala con un for o conStream.
     * generate que cree objetos Registro poniendo valores aleatorios de temperatura
     * y humedad,
     * y la fecha le añada un minuto a cada registro)
     */
    public static void initRegistro() {
        LocalDateTime hoy = LocalDateTime.now();

        for (int i = 0; i < 100; i++) {

            double temperatura = Math.random() * 45;
            double humedad = Math.random() * 70;
            registros.add(new Registro(
                    hoy.plusMinutes(1),
                    temperatura,
                    humedad
            ));
        }

    }




    public static void main() {

        //initRegistro
        initRegistro();

        System.out.println("****************************************");
        // 1. Filtrar los registros de temperatura que sean mayores a 25 grados,
        //     la humedad sea menor a 70
        //     y la fecha sea anterior a la fecha actual, y mostrarlos.
        registros.stream()
                .filter(registro -> registro.getTemperatura() > 25
                        && registro.getHumedad() < 70)
                .forEach(System.out::println);



        System.out.println("****************************************");
    /*
    2. Encontrar el registro con la temperatura más alta y mostrar el registro completo.
     */
        registros.stream()
                .max(Comparator.comparing(Registro::getTemperatura))
                .ifPresent(System.out::println);



        System.out.println("****************************************");
    /*
    3. Obtener una lista con las fechas/horas de todas las tomas de datos.
     */
        registros.stream()
                .map(Registro::getFechaHora)
                .forEach(System.out::println);


        System.out.println("****************************************");

    /*
    4. Incrementar en 5 unidades la humedad de todos los registros y mostrar las temperaturas,humedades y fechas/horas actualizadas.
     */
        registros.stream().map(registro -> {
            registro.setHumedad(registro.getHumedad() + 5);
            return registro;
        }).forEach(registro -> {
            System.out.println("humedad : " + registro.getHumedad() + " " +
                    "fecha: " + registro.getFechaHora());
        });

        System.out.println("****************************************");
    /*
    5. Encontrar el registro con la temperatura más baja que tenga una humedad mayor a
    80 y mostrar la temperatura, humedad y fecha.
     */
    registros.stream()
            .filter(registro -> registro.getHumedad() >= 0)
            .min(Comparator.comparing(Registro::getTemperatura))
            .ifPresent(registro -> {
                System.out.println("temperatura : " + registro.getTemperatura() + " " +
                                    "humedad : " + registro.getHumedad() + " " +
                                    "fecha: " + registro.getFechaHora().toLocalDate());
            });


        System.out.println("****************************************");
        /*

        6. Verificar si algún registro tiene una temperatura mayor a 30 grados,
        humedad mayor a 90 y la fecha es de hoy. Mostrar un mensaje indicando
        si hay algún registro que cumple esta condición o no.
                 */
        registros.stream()
                .anyMatch(registro -> registro.getHumedad() > 90 &&
                      registro.getTemperatura() > 30 &&
                      registro.getFechaHora().toLocalDate().equals(LocalDateTime.now().toLocalDate())
                );


        System.out.println("****************************************");

        /*
        7. Muestra 10 registros saltándote los 5 primeros.
         */
        registros.stream()
                .skip(5)
                .limit(10)
                .forEach(System.out::println);


        System.out.println("****************************************");

        /*
        8. Muestra los registros ordenados por fecha (sorted(Comparator))
         */
        registros.stream()
                .sorted(Comparator.comparing(Registro::getFechaHora))
                .forEach(System.out::println);



        System.out.println("****************************************");

        /*
        9. Cuenta los registros que tengan temperatura mayor a 35 grados (count()).
         */
        long counttemperatura = registros.stream()
                .filter(registro -> registro.getTemperatura() > 35)
                .count();

        System.out.println("los registros que tengan temperatura mayor a 35 grados --> " + counttemperatura);

        System.out.println("****************************************");

        /*
        10. Calcular la temperatura promedio
        de todos los registros (transformarlo en Stream<Double>
        y llamar a average()).
        */
       registros.stream()
                .mapToDouble(Registro::getTemperatura)
                .average().ifPresent(System.out::println);
    }
}
