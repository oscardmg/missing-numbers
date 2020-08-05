package com.example.numbers.services;

import com.example.numbers.dto.ParamsRequest;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.numbers.util.Util;

/**
 * Servicio que usa el controlador
 */
@Service
public class NumberService {

    /**
     * Metodo que usa el controlador para buscar los faltantes
     *
     * @param paramsRequest
     * @return
     * @throws Exception
     */
    public Integer[] find(ParamsRequest paramsRequest) throws Exception {

        validateParams(paramsRequest);

        Map mapA = Util.getMapFromString(paramsRequest.nList);
        Map mapB = Util.getMapFromString(paramsRequest.mList);



        return findDif(mapB, mapA);

    }


    /**
     * Metodo que recibe los dos objetos map y realiza la comparacion,
     * retorn un array con las diferencias
     * @param mapB
     * @param mapA
     * @return
     */
    private Integer[] findDif(Map mapB, Map mapA) {
        Integer[] result = null;
        List<Integer> missing = new ArrayList();

        /**
         * Recorrer los elementos que tien el mapB
         * Se busca el elemento que esta en mapB en mapA
         * y si se encuentra se compara las cantidaddes, si
         * esta es diferente hay faltante
         *
         * si el elemento de mapB no esta en mapA entonces
         * hay faltante
         *
         */
        mapB.forEach((k, v) -> {
            if(mapA.get(k) != null) {
                if(mapA.get(k) != v) {
                    int dif = (int) v - (int) mapA.get(k);
                    insertMissing(missing, (int) k,dif);
                }
            } else {
                insertMissing(missing, (int) k,(int)v);
            }
        });

        if(missing.size() > 0) {
            result = new Integer[missing.size()];

            int i = 0;
            for (Integer k : missing) {
                result[i] = k;
                i++;
            }
        }
        return result;
    }

    /**
     * Este metodo recibe la lista de faltantes, el valor en k y la cantidad de veces que
     * lo debe insertar.
     * @param missing
     * @param k
     * @param count
     */
    private void insertMissing(List missing, int k, int count) {
        for(int i=1; i <= count; i++ ) {
            missing.add(k);
        }
    }


    /**
     * Este metodo valida que si se reciba la informacion en los campos
     * obligatorios
     * valida que si se pueda crear un array con cada lista
     * valida que la segunda lista  tenga igual o menos elementos
     *
     * @param paramsRequest
     * @throws Exception
     */
    private void validateParams(ParamsRequest paramsRequest) throws Exception{

        if(paramsRequest.mList == null || paramsRequest.mList.trim().length() == 0) {
            throw new Exception("Debe ingresar los valores para la primer lista");
        }

        if(paramsRequest.nList == null || paramsRequest.nList.trim().length() == 0) {
            throw new Exception("Debe ingresar los valores para la segunda lista");
        }

        paramsRequest.mListInt = Util.getIntArrayFromString(paramsRequest.mList);

        if(paramsRequest.mListInt.length == 0) {
            throw new Exception("No fue posible crear el array con los valores que ingreso en la primer lista");
        }

        paramsRequest.nListInt = Util.getIntArrayFromString(paramsRequest.nList);

        if(paramsRequest.nListInt.length == 0) {
            throw new Exception("No fue posible crear el array con los valores que ingreso en la segunda lista");
        }

        if(paramsRequest.nListInt.length > paramsRequest.mListInt.length) {
            throw new Exception("La segunda lista no puede tener mas elementos que la primera");
        }

    }

    /**
     * Esta funcion agrupa el array y lo devuelve en un string concatenado con espacio
     * @param array
     * @return
     */
    public String getResumeAndOrder(Integer[] array) {
        Map<Integer, Integer> mapTemp = new LinkedHashMap<>();
        Arrays.sort(array);
        for (Integer value : array) {
            mapTemp.put(value, value);
        }

        return mapTemp.entrySet().stream().map(entry ->
                entry.getValue().toString())
                .collect(Collectors.joining(" "));
    }

    /**
     * Esta funcion busca el maximo y minimo, luego reailza una resta y devuelve
     * un string mostrando la diferencia y si es mayor o menor que 100
     * @param text
     * @return
     */
    public String getDifMaxAndMin(String text) {
        Integer[] array = Util.getIntArrayFromString(text);
        Integer min = null, max = null, dif;
        for (Integer value: array) {
            if(min == null) {
                min = value;
                max = value;
            }
            if(value < min) {
                min = value;
            }
            if(value > max) {
                max = value;
            }
        }
        dif = max - min;
        if(dif <= 100) {
            return "Diferencia: "+ dif +" menor que 100";
        }else {
            return "Diferencia: "+ dif +" mayor que 100";
        }
    }
}
