package org.example.udft;
import java.util.stream.Stream;

import org.example.configuration.DroolsConfig;
import org.example.model.CustomerRequest;
import org.example.service.CustomerCategorizeService;

import com.snowflake.snowpark_java.Row;
import com.snowflake.snowpark_java.types.StructType;
import com.snowflake.snowpark_java.types.StructField;
import com.snowflake.snowpark_java.types.DataTypes;
import com.snowflake.snowpark_java.types.IntegerType;
import com.snowflake.snowpark_java.udtf.*;



public class CustomerCategorizedUDTFHandler1 implements JavaUDTF4<Long, Integer, String, Integer>  {

    private transient CustomerCategorizeService service=null;
    public CustomerCategorizedUDTFHandler1() {
      
    }

    private void setupService() {
        var config = new DroolsConfig();
        var container = config.kieContainer();
        this.service = new CustomerCategorizeService(container);
    }

    @Override
    public Stream<Row> endPartition() {
        return Stream.empty();
    }

    static final StructType my_outputSchema = StructType.create(
            new StructField("customer_id", DataTypes.IntegerType)
    );
    @Override
    public StructType outputSchema() {
        return my_outputSchema;
    }

    @Override
    public Stream<Row> process(Long ARG1, Integer ARG2, String ARG3, Integer ARG4) {
        if (this.service == null) setupService();
        var customerRequest = new CustomerRequest(ARG1, ARG2, ARG3, ARG4);
        var response = this.service.getCustomerType(customerRequest);
        return Stream.of(Row.create(response.customerType));        
    }
    
}
