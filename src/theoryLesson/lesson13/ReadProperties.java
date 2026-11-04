package theoryLesson.lesson13;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class ReadProperties {
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        // 重点：从当前类所在包去读取资源
        InputStream is = ReadProperties.class.getResourceAsStream("database.properties");
        props.load(is);

        Set<String> propertyNames = props.stringPropertyNames();
        for(String pname : propertyNames) {
            String value = props.getProperty(pname);
            System.out.println(pname + "=" + value);
        }
        is.close();
    }
}
