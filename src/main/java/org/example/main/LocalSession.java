package org.example.main;

import com.snowflake.snowpark_java.Session;

import java.util.HashMap;
import java.util.Map;

public class LocalSession {

    public static Session getLocalSession() {
        try {
            return createSessionFromSNOWSQLEnvVars();
        } catch (NullPointerException e) {
            System.out.println("ERROR: Environment variable for Snowflake Connection not found. Please set the SNOWSQL_* environment variables.");
            e.printStackTrace();
            return null;
        }
    }

    private static Session createSessionFromSNOWSQLEnvVars() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("URL", getEnv("SNOWSQL_ACCOUNT") + ".snowflakecomputing.com");
        configMap.put("USER", getEnv("SNOWSQL_USER"));
        configMap.put("PASSWORD", getEnv("SNOWSQL_PWD"));
        configMap.put("DB", getEnv("SNOWSQL_DATABASE"));
        configMap.put("SCHEMA", getEnv("SNOWSQL_SCHEMA"));
        configMap.put("WAREHOUSE", getEnv("SNOWSQL_WAREHOUSE"));

        return Session.builder().configs(configMap).create();
    }

    private static String getEnv(String key) {
        String value = System.getenv(key);
        if (value == null) {
            throw new NullPointerException(String.format("Environment variable, %s, not found", key));
        }
        return value;
    }
}
