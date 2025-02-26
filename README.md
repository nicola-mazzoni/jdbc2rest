# Jdbc2Rest

Jdbc2Rest allows you to access any jdbc compliant database in REST mode. A simple server that accepts queries and returns data in json format.


## Getting Started

* put the jar with the specific driver for your db in your classpath (or pom...)
* configure Jdbc2RestConfiguration.json

```json
{
	"datasource": {
		"driver": "com.ibm.as400.access.AS400JDBCDriver",
		"url": "jdbc:as400://10.0.0.251;naming=system;libraries=WLFDAT",
		"username": "WNICOLA",
		"password": "MPS2022",
		"queryTimeout": 60,
		"maxRecordReturned": 100
	},
	"server": {
		"port": 8080
	},
	"users": [
		{
			"name": "Test User 1",
			"token": "OgFYDQB6xSjXQuObfuJ2MYYzXRX0pviKwcExmAXqUBgYdeE9B1IBLIkgTlH4JU3j",
			"auth": [
				"SELECT",
				"INSERT",
				"UPDATE"
			]
		},
		{
			"name": "Test User 2",
			"token": "gupbvk6j6G9Djo0gBCdMxGjL4sphUB3qeDst0MDWKy4OpOJTUiHFuCmm9jmU2iKh",
			"auth": [
				"SELECT"
			]
		}
	]
}

```

* run project

* test with curl

```bash
curl -k -X POST -H "Content-Type: application/json" -d '{"sql":"SELECT * FROM MYTABLE", "token"  : "OgFYDQB6xSjXQuObfuJ2MYYzXRX0pviKwcExmAXqUBgYdeE9B1IBLIkgTlH4JU3j"}' http://localhost:8080/jdbc2rest/v1
```


only SQL and TOKEN are required parameters.
I also added limit and offset to make pagination easier, especially for dbms (IBM db2) which don't have LIMIT, OFFSET keywords in SQL

```json
{
	"token"  : "OgFYDQB6xSjXQuObfuJ2MYYzXRX0pviKwcExmAXqUBgYdeE9B1IBLIkgTlH4JU3j",
	"sql"    : "SELECT * FROM MYTABLE",
	"limit"  : 20,
	"offset" : 100
}
```


## Exampled of returned data
 
```json
{
	"records": [
		{
			"ID": 1,
			"PRODUCT_DES": "Description test"
			"PRODUCT_PRICE": 123.45
		},
		{
			"ID": 2,
			"PRODUCT_DES": "Description test 2"
			"PRODUCT_PRICE": null
		}
	]
}
```




### Next Steps
* better logging and exceptions
* fine grained security token
* xml e csv return data
* complete REST to SQL sintax passing json object
* 	POST->INSERT
* 	PUT->UPDATE
* 	DELETE->DELETE


**Support**

Feel free to contact me for professional support, suggestions, tweaks, bugs and anything you can think of.


-----------------

Nicola Mazzoni (nmazzoni(AT)apache.org)
