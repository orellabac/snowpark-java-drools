package org.example.udft;

import java.util.stream.Stream;

import org.example.configuration.DroolsConfig;
import org.example.model.CustomerRequest;
import org.example.model.CustomerType;
import org.example.service.CustomerCategorizeService;

/**
 * Customer Categorization UDTF (see https://docs.snowflake.com/en/developer-guide/udf/java/udf-java-tabular-functions)
 * This is the actual categorization UDTF,
 * it will leverage the Drools Rule
 * @author mauricio.rojas
 */
public class CustomerCategorizeUDTFHandler {

    private CustomerCategorizeService service;

    public CustomerCategorizeUDTFHandler() {
        var config = new DroolsConfig();
        var container = config.kieContainer();
        this.service = new CustomerCategorizeService(container);
    }

    public static Class getOutputClass() {
      return CustomerType.class;
    }

    public Stream<CustomerType> process(Long id, Integer age, String gender, Integer numberOfOrders) {
        var customerRequest = new CustomerRequest(id, age, gender, numberOfOrders);
        var response = this.service.getCustomerType(customerRequest);
        return Stream.of(response);
    }

}
