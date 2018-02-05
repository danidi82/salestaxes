package it.dd.salestaxes.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.dd.salestaxes.model.PurchaseList;
import it.dd.salestaxes.model.Receipt;
import it.dd.salestaxes.service.ReceiptBuilderService;

@RestController
public class Controller {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
    @Autowired
    ReceiptBuilderService receiptBuilder;
    
    @PostMapping("/")
    public String index(@RequestBody PurchaseList purchaseList) {

    	logger.info("received purchase list: {}", purchaseList);
    	Receipt receipt = receiptBuilder.buildReceipt(purchaseList);
    	
    	logger.info("return receipt: {}", receipt);
        return receipt.toString();
    }

}