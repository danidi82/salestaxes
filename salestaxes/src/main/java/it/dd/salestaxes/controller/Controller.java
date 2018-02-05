package it.dd.salestaxes.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.dd.salestaxes.model.PurchaseList;
import it.dd.salestaxes.model.Sales;

@RestController
public class Controller {

	@Autowired
	private Sales sales;
	
	
    @PostMapping("/")
    public String index(@RequestBody PurchaseList purchaseList) {
        return purchaseList.toString();
    }

}