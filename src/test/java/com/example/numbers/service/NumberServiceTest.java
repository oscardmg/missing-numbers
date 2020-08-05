package com.example.numbers.service;




import com.example.numbers.dto.ParamsRequest;
import com.example.numbers.services.NumberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NumberServiceTest {

    @Autowired
    NumberService numberService;



    @DisplayName("Test numberService method")
    @Test
    void find() throws Exception{
        ParamsRequest p = new ParamsRequest();
        Integer[] expected = {204,205,206};
        p.nList = "203 204 205 206 207 208 203 204 205 206";
        p.mList = "203 204 204 205 206 207 205 208 203 206 205 206 204";

        Integer[] result = numberService.find(p);

        assertTrue(Arrays.equals(expected, result));

        p.nList = "";
        p.mList = "";

        assertThrows(Exception.class, () -> {
            Integer[] result1 = numberService.find(p);
        });

        assertThrows(Exception.class, () -> {
            Integer[] result1 = numberService.find(p);
        });

        p.nList = "203 204 205 206 207 208 203 204 205 206";
        p.mList = "203 204 204 205 206 207 205 208 203";

        assertThrows(Exception.class, () -> {
            Integer[] result1 = numberService.find(p);
        });



    }



}
