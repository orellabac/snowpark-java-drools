
CREATE STAGE IF NOT EXISTS drools_tests;

PUT file://target/snowpark-java-drools-0.0.1-FAT.jar @drools_tests AUTO_COMPRESS=FALSE OVERWRITE=TRUE;

PUT file://customers.csv @drools_tests AUTO_COMPRESS=FALSE OVERWRITE=TRUE;

create or replace file format my_csv_format 
    type = 'csv' 
    field_delimiter = ',' 
    record_delimiter = '\n'
    parse_header = true ;

create or replace file format my_csv_format_read
    type = 'csv' 
    field_delimiter = ',' 
    record_delimiter = '\n'
    skip_header = 1;


create or replace table customers
  using template (
    select array_agg(object_construct(*))
      from table(
        infer_schema(
            location=>'@drools_tests/customers.csv',
            file_format=>'my_csv_format'
    )));


copy into customers from @drools_tests/customers.csv
FILE_FORMAT = (FORMAT_NAME = 'my_csv_format_read');

CREATE OR REPLACE FUNCTION DROOLS_CLASIFY(id INT, age INT, gender STRING, numberOfOrders INTEGER)
  RETURNS TABLE(CustomerType INTEGER)
  LANGUAGE JAVA
  RUNTIME_VERSION = '11'
  IMPORTS = ('@drools_tests/snowpark-java-drools-0.0.1-FAT.jar')
  HANDLER = 'org.example.udft.CustomerCategorizeUDTFHandler'
  PACKAGES = ('com.snowflake:snowpark:latest');

SELECT C.*,D.* FROM CUSTOMERS C, 
TABLE(DROOLS_CLASIFY(C."ID",C."AGE",C."GENDER",C."NUMBEROFORDERS")) D;