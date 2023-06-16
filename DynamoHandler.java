package com.muni.dynamo.component;

import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

@Component
public class DynamoHandler {


    private Table table;
	
	
	public String apply(Employee employee) {
		initDbClient();
		Item item = new Item().withPrimaryKey("eid",employee.getId()).withString("firstname", employee.getFirstname())
		.withString("lastname", employee.getLastname()).withInt("age", employee.getAge()).withString("address", employee.getAddress());
		table.putItem(item);
		return "Done....!";
	}
	
	private void initDbClient()
	{
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDB docClient = new DynamoDB(client);
	    this.table  =docClient.getTable("Music");
		
	}

}
