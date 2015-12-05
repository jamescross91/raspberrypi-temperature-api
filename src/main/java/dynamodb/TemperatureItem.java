package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.UUID;

/**
 * Created by jamescross91 on 05/12/2015.
 */

@DynamoDBTable(tableName = DynamoAttributes.TABLE_NAME)
public class TemperatureItem {
    @DynamoDBHashKey(attributeName = "id")
    private String id;

    @DynamoDBAttribute(attributeName = DynamoAttributes.TEMPERATURE_ATTRIBUTE_NAME)
    private double temperature;

    @DynamoDBAttribute(attributeName = DynamoAttributes.SENSOR_ADDRESS_ATTRIBUTE_NAME)
    private String sensorAddress;

    @DynamoDBAttribute(attributeName = DynamoAttributes.RECEIVE_TIME_ATTRIBUTE_NAME)
    private long receiveTime;

    public TemperatureItem(double temperature, String sensorAddress) {
        this.id = UUID.randomUUID().toString();
        this.temperature = temperature;
        this.sensorAddress = sensorAddress;
        this.receiveTime = System.currentTimeMillis() / 1000;
    }

    public TemperatureItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getSensorAddress() {
        return sensorAddress;
    }

    public void setSensorAddress(String sensorAddress) {
        this.sensorAddress = sensorAddress;
    }

    public long getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(long receiveTime) {
        this.receiveTime = receiveTime;
    }
}
