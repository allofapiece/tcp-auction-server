package com.ock.au.controller;

import com.ock.au.entity.Lot;
import com.ock.au.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("lots")
public class LotController {

    @Autowired
    private LotService lotService;


    @RequestMapping(value = "add")
    @ResponseBody
    public void addLot(@RequestParam(defaultValue = "0") Lot lot) {
        lotService.create(lot);
        System.out.println(lot);
    }
}
