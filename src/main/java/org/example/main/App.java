package org.example.main;

//import org.example.util.LocalSession;
//import com.snowflake.snowpark_java.*;
//import com.snowflake.snowpark_java.types.*;

import org.example.udft.CustomerCategorizeUDTFHandler;

public class App {
    /**
     * A simple stored procedure which creates a 2x2 DataFrame, prints it
     * to the console, and returns the row count.
     * @param session A Snowflake Session
     * @return The count of the DataFrame
     */
    //public static Long run(Session session) {
        
        // // See: https://docs.snowflake.com/developer-guide/snowpark/reference/java/com/snowflake/snowpark_java/FileOperation.html
        // Map<String, String> options = new HashMap<>();
        // options.put("AUTO_COMPRESS", "FALSE");
        // options.put("OVERWRITE", "TRUE");
        // session.sql("create stage if not exists drools_tests").show();
        // session.file().put("customers.csv","@drools_tests",options);
        // var schema = getCustomerSchema();
        // DataFrame customersDf = session.read().schema(schema).csv("@drool_tests/customers.cvs");
        
        // session.sql("DROP FUNCTION IF EXISTS droolsCategorizer(NUMBER,NUMBER,NUMBER,NUMBER)").show();
        // var droolsCategorizer = session.udtf().registerPermanent("droolsCategorizer", new CustomerCategorizeUDTFHandler(),"@drools_tests");
        // customersDf.join(droolsCategorizer, col("id"),col("age"),col("gender"),col("numberOfOrders")).show();
        //return Long.valueOf(0);
    //}

    /**
     * Main entrypoint. Runs the stored procedure locally for development.
     * @param args
     */
    public static void main(String[] args) {
        var udtf = new CustomerCategorizeUDTFHandler();
        //Session session = LocalSession.getSession(false);
        //App.run(null);
        System.out.println("Done");
    }
}
