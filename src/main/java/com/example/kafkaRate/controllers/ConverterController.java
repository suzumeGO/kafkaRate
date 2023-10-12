package com.example.kafkaRate.controllers;

import com.example.kafkaRate.services.CurrencyService;
import com.example.kafkaRate.DTO.CurrencyRateDTO;
import com.example.kafkaRate.kafka.ValutesListener;
import com.example.kafkaRate.model.CurrencyRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/")
public class ConverterController {

    private final ValutesListener listener;
    private final CurrencyService currencyService;

    @Autowired
    public ConverterController(ValutesListener listener, CurrencyService currencyService) {
        this.listener = listener;
        this.currencyService = currencyService;
    }

    @GetMapping
    public String showConverterForm() {
        return "converter";
    }

    @PostMapping
    public String exchange(@ModelAttribute("currencyRate") CurrencyRate currencyRate) {
        currencyRate.setValue(currencyService.getExchangeRate(
                new CurrencyRateDTO(
                currencyRate.getFirstCurrency(),
                currencyRate.getAmount(),
                currencyRate.getSecondCurrency())));
        return "converter";
    }
    @ModelAttribute(name = "currencyRate")
    public CurrencyRate currencyRate() {
        return new CurrencyRate();
    }
    @ModelAttribute
    public void addValutesToModel(Model model) {
        model.addAttribute("valutes", listener.getValutes());
    }

}
