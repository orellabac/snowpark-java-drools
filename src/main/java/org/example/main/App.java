package org.example.main;

import com.snowflake.snowpark_java.*;


import java.util.HashMap;
import java.util.Map;


import org.example.udft.CustomerCategorizedUDTFHandler1;


public class App {

    /**
     * Main entrypoint. Runs the stored procedure locally for development.
     * @param args
     */
    public static void main(String[] args) {
        var udtf = new CustomerCategorizedUDTFHandler1();
        Session session = LocalSession.getLocalSession();
        session.addDependency("/Users/mrojas/snowpark-java-drools/target/snowpark-java-drools-0.0.1-FAT.jar");
        var drools_classify = session.udtf().registerTemporary("drools_classify", udtf);
        var customers = session.table("customers");
        
   
        Map<String, Column> drools_args = new HashMap<>();
        drools_args.put("ARG1",customers.col("\"id\""));
        drools_args.put("ARG2",customers.col("\"age\""));
        drools_args.put("ARG3",customers.col("\"gender\""));
        drools_args.put("ARG4",customers.col("\"numberOfOrders\""));
        var res = customers.join(drools_classify, drools_args);
        res.show();
        System.out.println("Done");
    }
}
