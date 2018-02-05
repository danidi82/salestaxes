package it.dd.salestaxes.service;

import it.dd.salestaxes.model.PurchaseList;
import it.dd.salestaxes.model.Receipt;

public interface ReceiptBuilderService {

	Receipt buildReceipt(PurchaseList purchaseList);
}
