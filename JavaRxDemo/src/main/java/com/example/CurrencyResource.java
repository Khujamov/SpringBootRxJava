package com.example;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.CurrencyConverter;
import com.example.service.CurrencyConverterService;

import rx.Observable;

@RestController
@RequestMapping("/api/currencyconverter")
public class CurrencyResource {
	private static final Logger log = LoggerFactory.getLogger(CurrencyConverter.class);

	private final CurrencyConverterService currencyConverterService;

	public CurrencyResource(CurrencyConverterService currencyConverterService) {
		super();
		this.currencyConverterService = currencyConverterService;
	}

	@GetMapping(value = "/rates", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public Observable<?> getCurrencyRates(@RequestParam("symbol") Set<String> currencyRates) {
		log.debug("Retrieving currency rates.");
		return currencyConverterService.getCurrencyRates(currencyRates);
	}
}
