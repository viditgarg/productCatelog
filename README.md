Solution for building RESTful service that implments a check out counter for an online retail store that scans products and generates an itemized bill.

==============

Here is how one can build and run the application.

step 1 - got to the project folder where pom.xml file is located. assuming you have maven configured n your desktop.
run these commands to build and run the server..

1. mvn clean package   
2. java -jar target/checkout-counter-0.0.1-SNAPSHOT.jar

now the application can be tested using postman or browser

-- using postman select these

method type : GET
url : http://localhost:8080/rest/api/products

since I have implemented with basic authentication so one has to provide user name and password

username: user
password: welcome


--- using webbrowser

url : http://localhost:8080/rest/api/products
username: user
password: welcome

------------------------------

result will show 5 products having product category 'Category A', 'Category B' and 'other' which are having tax levy of 10%, 20% and 0 % respectively.
items costs will be displayed, tax will be displayed and then total costs will be displayed.

