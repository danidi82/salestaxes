package it.dd.salestaxes.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dd.salestaxes.model.PurchaseList;
import it.dd.salestaxes.model.Receipt;
import it.dd.salestaxes.model.TaxProperties;

@Service
public class SimpleReceiptBuilderService implements ReceiptBuilderService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TaxProperties taxProperties;

	@Override
	public Receipt buildReceipt(PurchaseList purchaseList) {

		Receipt receipt = new Receipt();
		receipt.setItems(new LinkedList<String>());

		float taxTotalAmount = 0f;
		float priceTotalAmount = 0f;

		for (String item : purchaseList.getItems()) {
			logger.debug("processing item: {}", item);

			int lastSpace = item.lastIndexOf(" ");
			float priceFreeTax = Float.parseFloat(item.substring(lastSpace));
			String name = item.substring(0, item.lastIndexOf(" ", lastSpace - 1));
			logger.debug("{} tax free costs: {}", name, priceFreeTax);

			float taxOfItem = 0;

			if (!hasMatching(name, taxProperties.getExemptedPatterns())) {
				float basicTax = ((priceFreeTax * taxProperties.getBasicTaxRate()) / 100);
				taxOfItem += roundTax(basicTax);
				logger.debug("not exempted, tax amount: {}", taxOfItem);
			}

			if (hasMatching(name, taxProperties.getImportedPatterns())) {
				float importTax = ((priceFreeTax * taxProperties.getImportedTaxRate()) / 100);
				taxOfItem += roundTax(importTax);
				logger.debug("is imported, tax amount: {}", taxOfItem);
			}
			taxOfItem = roundTax(taxOfItem);

			
		    float priceTaxed =  (float) (taxOfItem + priceFreeTax);
			logger.debug("{} with tax costs: {}", name, priceTaxed);
			receipt.getItems().add(name + ": " + String.format(Locale.US, "%.2f", priceTaxed));

			taxTotalAmount += taxOfItem;
			priceTotalAmount += priceTaxed;

		}
		
		receipt.setSalesTaxes(String.format(Locale.US, "%.2f", taxTotalAmount));
		receipt.setTotal(String.format(Locale.US, "%.2f", priceTotalAmount));
		
		return receipt;
	}

	private float roundTax(float taxOfItem) {
		float taxTruncatedPart = ((int) (taxOfItem*100)/5)*5;
		float taxRoundedPart = ((int)((taxOfItem*100)%5)>0)?5:0;
		return (float)(taxTruncatedPart + taxRoundedPart)/100;
	}

	private boolean hasMatching(String item, Set<String> set) {
		for (String key : set) {
			if (item.contains(key)) {
				return true;
			}
		}
		return false;
	}

}
