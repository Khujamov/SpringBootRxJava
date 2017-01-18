package com.example;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.domain.dto.CurrencyRatesDTO;
import com.example.service.CurrencyConverter;
import com.example.service.CurrencyConverterService;

@Controller
@RequestMapping("/api/currencyconverter")
public class CurrencyResource {
	private static final Logger log = LoggerFactory.getLogger(CurrencyConverter.class);

	@Autowired
	private CurrencyConverterService currencyConverterService;

	@RequestMapping(value = "/rates", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public DeferredResult<ResponseEntity<CurrencyRatesDTO>> getCurrencyRates(
			@RequestParam("symbol") Set<String> currencyRates) {
		DeferredResult<ResponseEntity<CurrencyRatesDTO>> deferredResult = new DeferredResult<ResponseEntity<CurrencyRatesDTO>>();
		log.debug("Retrieving currency rates.");
		currencyConverterService.getCurrencyRates(currencyRates).subscribe(
				sub -> deferredResult.setResult(new ResponseEntity<CurrencyRatesDTO>(sub, HttpStatus.OK)),
				e -> deferredResult.setErrorResult(e));
		return deferredResult;
	}
}
