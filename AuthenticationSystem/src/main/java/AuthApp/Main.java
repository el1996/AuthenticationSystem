package AuthApp;

public class Main {
    public static void main(String args[])
    {
       Client client = new Client();
       client.register("lior.mathan@gmail.com", "Lior Mathan", "1234As");
        client.register("Nitzan@gmail.com", "Nitzan Lahav", "987Nl11");
        client.register("Elchanan@gmail.com", "Elchanan Madmon", "El9622");
        client.login("lior.mathan@gmail.com", "1234As");
    }
}
