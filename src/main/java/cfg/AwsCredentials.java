package cfg;

import com.amazonaws.auth.BasicAWSCredentials;

/**
 * Created by jamescross91 on 01/12/2015.
 */

public class AwsCredentials {
    public static BasicAWSCredentials get() {
        String aws_access_key_id = ReadProperties.getProperty("aws_access_key_id");
        String aws_secret_access_key = ReadProperties.getProperty("aws_secret_access_key");

        return new BasicAWSCredentials(aws_access_key_id, aws_secret_access_key);
    }
}
