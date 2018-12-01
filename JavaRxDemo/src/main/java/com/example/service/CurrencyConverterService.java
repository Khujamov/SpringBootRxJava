package com.example.service;

import java.util.Collection;

import com.example.domain.dto.CurrencyRatesDTO;

import rx.Observable;

public interface CurrencyConverterService {
	/**
	 * Fetches currency rates for a given set of currencies against a base
	 * currency, which is Euro.
	 * 
	 * @param currencies
	 *            Currencies for which base needs to be computed.
	 * @return Set of currencies with their rate against the base currency which
	 *         is Euro.
	 */
	Observable<CurrencyRatesDTO> getCurrencyRates(Collection<String> currencies);

}
