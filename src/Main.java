public class Main {

    public static void main(String[] args) {
        String dbName = "carsharing";
        Menu menu = new Menu(new CarSharingDB(dbName));
        menu.start();
    }
}