package it.dd.salestaxes.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;
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

		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_UP);

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

			float taxOfItem = 0f;

			if (!hasMatching(name, taxProperties.getExemptedPatterns())) {
				taxOfItem = (priceFreeTax * (taxProperties.getBasicTaxRate() / 100));
				logger.debug("not exempted, tax amount: {}", taxOfItem);
			}

			if (hasMatching(name, taxProperties.getImportedPatterns())) {
				taxOfItem += (priceFreeTax * (taxProperties.getImportedTaxRate() / 100));
				logger.debug("is imported, tax amount: {}", taxOfItem);
			}

			float priceTaxed = Float.parseFloat(df.format(taxOfItem)) + priceFreeTax;
			logger.debug("{} with tax costs: {}", name, priceTaxed);
			receipt.getItems().add(name + " : " + priceTaxed);

			taxTotalAmount += taxOfItem;
			priceTotalAmount += priceTaxed;

		}
		
		receipt.setSalesTaxes(Float.toString(taxTotalAmount));
		receipt.setTotal(Float.toString(priceTotalAmount));
		
		return receipt;
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
