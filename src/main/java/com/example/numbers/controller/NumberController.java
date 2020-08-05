package com.example.numbers.controller;

import com.example.numbers.dto.ParamsRequest;
import com.example.numbers.services.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/numbers")
public class NumberController {

    @Autowired
    NumberService numberService;

    @PostMapping("/find")
    public String find(
            @RequestBody ParamsRequest paramsRequest
            )  {
        String result = "";
        try {
            Integer[] difArray = numberService.find(paramsRequest);

            result = numberService.getResumeAndOrder(difArray);

            result += " , " + numberService.getDifMaxAndMin(paramsRequest.nList);

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

        return result;
    }

}
