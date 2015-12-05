package cfg;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jamescross91 on 05/11/2015.
 */

public class ReadProperties {
    private static Properties props = null;

    //Load static properties from the properties file
    private static void load() {
        try {
            InputStream inputStream = ReadProperties.class.getResourceAsStream("/config.txt");

            props = new Properties();
            props.load(inputStream);

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String resolveValueWithEnvVars(String value) {
        if (null == value) {
            return null;
        }

        Pattern p = Pattern.compile("\\$\\{(\\w+)\\}|\\$(\\w+)");
        Matcher m = p.matcher(value);
        StringBuffer sb = new StringBuffer();

        while (m.find()) {
            String envVarName = null == m.group(1) ? m.group(2) : m.group(1);
            String envVarValue = System.getenv(envVarName);
            m.appendReplacement(sb,
                    null == envVarValue ? "" : Matcher.quoteReplacement(envVarValue));
        }

        m.appendTail(sb);
        return sb.toString();
    }

    //Go get the property we want
    public static String getProperty(String property) {
        if (props == null)
            load();

        return (props.getProperty(ReadProperties.resolveValueWithEnvVars(property)));
    }
}
