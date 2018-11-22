package domain.db;

import java.util.Properties;

public class Secret {

    public static void setPass(Properties properties){
        properties.setProperty("user", "local_r0663332");
        properties.setProperty("password", "RG)EZd;çMbO4sECE");
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode","require");

    }

}
