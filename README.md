# README

## Execute it

Just download the code, run clean install and then execute one of the following lines (step 2).
```
1. mvn clean install
2a. mvn spring-boot:run
2b. java -jar ./target/prices-0.0.1-SNAPSHOT.jar
```


If you use a different `java -version` just change the version inside the `pom.xml`:
- `<java.version>17</java.version>`

#### PRICES TABLE

```
BRAND_ID  START_DATE             END_DATE             PRICE_LIST  PRODUCT_ID  PRIORITY  PRICE    CURR
-----------------------------------------------------------------------------------------------------
1         2020-06-14-00.00.00    2020-12-31-23.59.59  1           35455       0         35.50    EUR
1         2020-06-14-15.00.00    2020-06-14-18.30.00  2           35455       1         25.45    EUR
1         2020-06-15-00.00.00    2020-06-15-11.00.00  3           35455       1         30.50    EUR
1         2020-06-15-16.00.00    2020-12-31-23.59.59  4           35455       1         38.95    EUR
```
