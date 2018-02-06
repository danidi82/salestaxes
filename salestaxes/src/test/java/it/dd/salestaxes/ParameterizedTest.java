package it.dd.salestaxes;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import it.dd.salestaxes.controller.Controller;
import it.dd.salestaxes.model.PurchaseList;
import it.dd.salestaxes.model.Receipt;

@ContextConfiguration(classes=SalestaxesApplication.class)
@RunWith(Parameterized.class)
public class ParameterizedTest {

	@ClassRule
	public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

	@Rule
	public final SpringMethodRule springMethodRule = new SpringMethodRule();

	@Parameter(0)
	public PurchaseList input;

	@Parameter(1)
	public Receipt output;

	@Autowired
	Controller controller;

	@Parameters
	public static Collection<Object[]> data() throws IllegalArgumentException, IOException {
		
		PurchaseList input1 = new PurchaseList();
		input1.setItems(new LinkedList<String>());
		input1.getItems().add("1 book at 12.49");
		input1.getItems().add("1 music CD at 14.99");
		input1.getItems().add("1 chocolate bar at 0.85");

		Receipt output1 = new Receipt();
		output1.setItems(new LinkedList<String>());
		output1.getItems().add("1 book: 12.49");
		output1.getItems().add("1 music CD: 16.49");
		output1.getItems().add("1 chocolate bar: 0.85");
		output1.setSalesTaxes("1.50");
		output1.setTotal("29.83");

		PurchaseList input2 = new PurchaseList();
		input2.setItems(new LinkedList<String>());
		input2.getItems().add("1 imported box of chocolates at 10.00");
		input2.getItems().add("1 imported bottle of perfume at 47.50");

		Receipt output2 = new Receipt();
		output2.setItems(new LinkedList<String>());
		output2.getItems().add("1 imported box of chocolates: 10.50");
		output2.getItems().add("1 imported bottle of perfume: 54.65");
		output2.setSalesTaxes("7.65");
		output2.setTotal("65.15");

		PurchaseList input3 = new PurchaseList();
		input3.setItems(new LinkedList<String>());
		input3.getItems().add("1 imported bottle of perfume at 27.99");
		input3.getItems().add("1 bottle of perfume at 18.99");				
		input3.getItems().add("1 packet of headache pills at 9.75");
		input3.getItems().add("1 box of imported chocolates at 11.25");		
		
		Receipt output3 = new Receipt();
		output3.setItems(new LinkedList<String>());
		output3.getItems().add("1 imported bottle of perfume: 32.19");
		output3.getItems().add("1 bottle of perfume: 20.89");
		output3.getItems().add("1 packet of headache pills: 9.75");
		output3.getItems().add("1 imported box of chocolates: 11.85");
		output3.setSalesTaxes("6.70");
		output3.setTotal("74.68");
				
		
		Object[][] data = new Object[][] { 
			{ input1, output1 },
			{ input2, output2 },
			{ input3, output3 }};
		return Arrays.asList(data);
	}

	@Test
	public void testMultiplyException() {
		assertThat(output).isEqualToComparingFieldByField(controller.index(input));
	}
	
}