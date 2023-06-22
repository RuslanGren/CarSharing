import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    private final CarSharingDB db;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(CarSharingDB db) {
        this.db = db;
    }
    public void start() {
        boolean flag = true;
        while (flag) {
            System.out.println("1. Log in as a manager");
            System.out.println("0. Exit");
            String action = scanner.nextLine();
            switch (action) {
                case ("1") -> logManager();
                case ("0") -> flag = false;
                default -> System.out.println("Unknown command. Try again");
            }
        }
    }

    private void logManager() {
        boolean flag = true;
        while (flag) {
            System.out.println("1. Company list");
            System.out.println("2. Create a company");
            System.out.println("0. Back");
            String action = scanner.nextLine();
            switch (action) {
                case ("1") -> companyList();
                case ("2") -> addCompany();
                case ("0") -> flag = false;
                default -> System.out.println("Unknown command. Try again");
            }
        }
    }

    private void companyList() {
        HashMap<String, String> mapOfCompanies = db.getMapCompanies();
        if (mapOfCompanies.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            while (true) {
                System.out.println("Choose a company:");
                mapOfCompanies.forEach((id, name) -> System.out.println(id + ". " + name));
                System.out.println("0. Back");
                String key = scanner.nextLine();
                if (key.equals("0")) {
                    break;
                }
                if (mapOfCompanies.containsKey(key)) {
                    carCompany(mapOfCompanies.get(key), Integer.parseInt(key));
                    break;
                } else {
                    System.out.println("Wrong choose. Try again");
                }
            }
        }
    }

    private void carCompany(String companyName, int companyId) {
        boolean flag = true;
        while (flag) {
            System.out.printf("'%s' company", companyName);
            System.out.println("\n1. Car list");
            System.out.println("2. Create a car");
            System.out.println("0. Back");
            String action = scanner.nextLine();
            switch (action) {
                case ("1") -> {
                    ArrayList<String> listOfCars = db.getListOfCars(companyId);
                    if (listOfCars.isEmpty()) {
                        System.out.println("The car list is empty!");
                    } else {
                        listOfCars.forEach(car -> System.out.println(listOfCars.indexOf(car) + 1 + ". " + car));
                    }
                }
                case ("2") -> {
                    System.out.println("Enter the car name:");
                    String carName = scanner.nextLine();
                    db.addCar(carName, companyId);
                    System.out.println("The car was added!");
                }
                case ("0") -> flag = false;
            }
        }
    }

    private void addCompany() {
        System.out.println("Enter the company name:");
        String name = scanner.nextLine();
        db.addCompany(name);
        System.out.println("The company was created!");
    }

}