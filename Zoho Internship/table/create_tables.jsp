<%@page import="project.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%
try{
	Connection con=ConnectionProvider.getcon();
	Statement st=con.createStatement();
	String q1="create table users(name varchar(100),email varchar(100) primary key,mobileNumber bigInt,password varchar(100),address varchar(500),city varchar(100),state varchar(100),country varchar(100),Type varchar(100))";
	String q2="create table product(id int NOT NULL auto_increment unique key,name varchar(500),category varchar(200),price int,active varchar(10),supplier_ID int)auto_increment=1000;";
	String q3="create table cart(email varchar(100),prod_id int,quantity int,price int,total int,address varchar(100),city varchar(100),state varchar(100),country varchar(100),mobileNumber bigint,orderDate varchar(10),deliveryDate varchar(10),paymentMode varchar(100),transactionId varchar(100),status varchar(100));";
	String q4="create table orderlist(consumer_id int,email varchar(50),product_name varchar(50),Category varchar(50),price int,quantity int,sub_total int,order_date date,delivery_date date,paymentMode varchar(20),status varchar(20));";
	String q5="create table message(id int AUTO_INCREMENT,customerId int,about varchar(10),body varchar(1000),Primary Key(id));";
	//System.out.println(q1);
	//st.execute(q1);
	//System.out.println(q2);
	//st.execute(q2);
	//System.out.println(q3);
	//st.execute(q3);
	//System.out.println(q4);
	//st.execute(q4);
	System.out.println(q5);
	st.execute(q5);
	System.out.println("Table Created");
	con.close();
}
catch(Exception e){
	System.out.println(e);
}
%>
