package dynamodb;

import cfg.AwsCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

/**
 * Created by jamescross91 on 05/12/2015.
 */

public class TemperatureDAO {

    public void save(TemperatureItem temperatureItem) {
        AmazonDynamoDBClient client = getClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        mapper.save(temperatureItem);
    }

    private AmazonDynamoDBClient getClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(AwsCredentials.get());
        client.setRegion(Region.getRegion(Regions.EU_WEST_1));
        return client;
    }
}
