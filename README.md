# Snowpark Java Example using Drools

This is a sample Java application that uses the Snowpark client library and the Drools rule engine.

I got inspired by a [blog post](https://medium.com/codex/spring-boot-with-drools-engine-7119774c559f) that used the drools lib.

And decided to give it a try with Snowpark.

So the provided set of rules, written in the Drools rule language, categorizes customers based on attributes such as age, number of orders, and gender. Customers are classified into different categories such as kids, senior citizens, suspended, or general, depending on their specific attributes as evaluated by the rules. Each rule specifies conditions to trigger and actions to take when a customer matches those conditions.

## Usage

```sql
SELECT C.*,D.* FROM CUSTOMERS C, 
TABLE(DROOLS_CLASIFY(C."ID",C."AGE",C."GENDER",C."NUMBEROFORDERS")) D;
```


### Build the application

You can build the application using Maven:

Just run `mvn clean package` from the root directory.

### Deploy the contents

A sql script is included that creates the stage, uploasd some data and creates an UDTF.

To deploy the contents, run the following command from the root directory:
```
snowsql -a myaccount -u myuser -s schema -d mydb -r role -w warehouse --prompt -f deploy.sql
```

## Docs

Some good links for Snowpark Java Development:

- [Snowpark JavaDoc](https://docs.snowflake.com/en/developer-guide/snowpark/reference/java/index.html)
- [Developer Guide](https://docs.snowflake.com/developer-guide/snowpark/java/index.html)
- [Writing Stored Procedures](https://docs.snowflake.com/en/sql-reference/stored-procedures-java.html)
