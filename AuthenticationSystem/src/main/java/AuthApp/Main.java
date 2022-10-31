package AuthApp;

import java.io.IOException;
import java.util.regex.Pattern;

public class Main {
    public static void main(String args[]) throws IOException {

        Client client = new Client();
        client.register("lior.mathan@gmail.com", "Lior Mathan", "1234As");
        client.register("Nitzan@gmail.com", "Nitzan Lahav", "987Nl11");
        client.register("Elchanan@gmail.com", "Elchanan Madmon", "El9622");
        client.login("lior.mathan@gmail.com", "1234As");

        client.updateName("Elchanan@gmail.com", "nana");
        client.updateEmail("Nitzan@gmail.com", "Nitzan1@gmail.com");
        client.updatePassword("lior.mathan@gmail.com", "148opsL");


    }
}
