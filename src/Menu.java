package carsharing;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final CompanyDAO companyDAO = new CompanyDAOImpl();
    private final CarDAO carDAO = new CarDaoImpl();
    private final Scanner scanner = new Scanner(System.in);

    public Menu() {
        try {
            Database.init();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
                case ("1") -> printListOfCompanies();
                case ("2") -> addCompany();
                case ("0") -> flag = false;
                default -> System.out.println("Unknown command. Try again");
            }
        }
    }

    private void printListOfCompanies() {
        try {
            List<Company> listOfCompanies = companyDAO.getAll();
            if (listOfCompanies.isEmpty()) {
                System.out.println("The company list is empty!");
            } else {
                while (true) {
                    listOfCompanies = companyDAO.getAll();
                    System.out.println("Choose a company:");
                    listOfCompanies.forEach(company -> System.out.println(company.getId() + ". " + company.getName()));
                    System.out.println("0. Back");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (id == 0) {
                        break;
                    }
                    if (listOfCompanies.stream().anyMatch(company -> company.getId() == id)) {
                        Company currentCompany = companyDAO.get(id);
                        menuOfCompany(currentCompany.getName(), currentCompany.getId());
                        break;
                    } else {
                        System.out.println("Wrong choose. Try again");
                    }
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private void menuOfCompany(String companyName, int companyId) {
        boolean flag = true;
        while (flag) {
            System.out.printf("'%s' company", companyName);
            System.out.println("\n1. Car list");
            System.out.println("2. Create a car");
            System.out.println("0. Back");
            String action = scanner.nextLine();
            try {
                List<Car> listOfCars = carDAO.getByCompanyId(companyId);
                switch (action) {
                    case ("1") -> {
                        if (listOfCars.isEmpty()) {
                            System.out.println("The car list is empty!");
                        } else {
                            listOfCars.forEach(car -> System.out.println(listOfCars.indexOf(car) + 1 + ". " + car.getName()));
                        }
                    }
                    case ("2") -> {
                        System.out.println("Enter the car name:");
                        String carName = scanner.nextLine();
                        carDAO.insert(new Car(0, carName, companyId));
                        System.out.println("The car was added!");
                    }
                    case ("0") -> flag = false;
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private void addCompany() {
        System.out.println("Enter the company name:");
        String name = scanner.nextLine();
        try {
            companyDAO.insert(new Company(0, name));
            System.out.println("The company was created!");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}