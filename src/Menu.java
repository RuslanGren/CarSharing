package carsharing;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final CompanyDAO companyDAO = new CompanyDAOImpl();
    private final CarDAO carDAO = new CarDaoImpl();
    private final CustomerDAO customerDAO = new CustomerDAOImpl();
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
            System.out.println("2. Log in as a customer");
            System.out.println("3. Create a customer");
            System.out.println("0. Exit");
            String action = scanner.nextLine();
            switch (action) {
                case ("1") -> logManager();
                case ("2") -> logCustomer();
                case ("3") -> addCustomer();
                case ("0") -> flag = false;
                default -> System.out.println("Unknown command. Try again");
            }
        }
    }

    private void addCustomer() {
        System.out.println("Enter the customer name:");
        String name = scanner.nextLine();
        customerDAO.insert(new Customer(0, name, null));
        System.out.println("The customer was added!");
    }

    private void logManager() {
        boolean flag = true;
        while (flag) {
            System.out.println("1. Company list");
            System.out.println("2. Create a company");
            System.out.println("0. Back");
            String action = scanner.nextLine();
            switch (action) {
                case ("1") -> menuOfCompany();
                case ("2") -> addCompany();
                case ("0") -> flag = false;
                default -> System.out.println("Unknown command. Try again");
            }
        }
    }

    private void logCustomer() {
        List<Customer> listOfCustomers = customerDAO.getAll();
        Customer customer = printListAndChooseOption(listOfCustomers, "customer");
        if (customer != null) {
            boolean flag = true;
            while(flag) {
                System.out.println("1. Rent a car");
                System.out.println("2. Return a rented car");
                System.out.println("3. My rented car");
                System.out.println("0. Back");
                String action = scanner.nextLine();
                switch (action) {
                    case ("1") -> {
                        if (customer.getRented_car_id() != null) {
                            System.out.println("You've already rented a car!");
                            break;
                        }
                        List<Company> listOfCompanies = companyDAO.getAll();
                        Company company = printListAndChooseOption(listOfCompanies, "company");
                        if (company == null) {
                            break;
                        }
                        List<Car> listOfCars = carDAO.getNotRentedCars(company.getId());
                        Car car = printListAndChooseOption(listOfCars, "car");
                        if (car == null) {
                            break;
                        }
                        customer.setRented_car_id(car.getId());
                        System.out.println("You rented '" + car.getName() + "'");
                        customerDAO.rentCar(customer);
                    }
                    case ("2") -> {
                        if (customer.getRented_car_id() == null) {
                            System.out.println("You didn't rent a car!");
                        } else {
                            customer.setRented_car_id(null);
                            customerDAO.returnCar(customer.getId());
                            System.out.println("You've returned a rented car!");
                        }
                    }
                    case ("3") -> {
                        if (customer.getRented_car_id() == null) {
                            System.out.println("You didn't rent a car!");
                        } else {
                            Car customerCar = carDAO.get(customer.getRented_car_id()); // get customer car's by customer_rented_car_id
                            System.out.println("Your rented car:");
                            System.out.println(customerCar.getName());
                            System.out.println("Company:");
                            System.out.println(companyDAO.get(customerCar.getCompany_id()).getName()); // get company name by company_id from customer car
                        }
                    }
                    case ("0") -> flag = false;
                    default -> System.out.println("Unknown command. Try again");
                }
            }
        }
    }

    private void addCompany() {
        System.out.println("Enter the company name:");
        String name = scanner.nextLine();
        companyDAO.insert(new Company(0, name));
        System.out.println("The company was created!");
    }

    private void menuOfCompany() {
        List<Company> listOfCompanies = companyDAO.getAll();
        Company company = printListAndChooseOption(listOfCompanies, "company");
        if (company == null) {
            return;
        }
        boolean flag = true;
        while (flag) {
            System.out.printf("'%s' company", company.getName());
            System.out.println("\n1. Car list");
            System.out.println("2. Create a car");
            System.out.println("0. Back");
            String action = scanner.nextLine();
            List<Car> listOfCars = carDAO.getByCompanyId(company.getId());
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
                    carDAO.insert(new Car(0, carName, company.getId()));
                    System.out.println("The car was added!");
                }
                case ("0") -> flag = false;
            }
        }
    }

    private <T extends BaseEntity> T printListAndChooseOption(List<T> list, String listName) {
        if (list.isEmpty()) {
            System.out.println("The " + listName + " list is empty!");
        } else {
            while (true) {
                System.out.println("Choose a " + listName + ":");
                list.forEach(entity -> System.out.println(list.indexOf(entity) + 1 + ". " + entity.getName()));
                System.out.println("0. Back");
                int id = scanner.nextInt();
                scanner.nextLine();
                if (id == 0) {
                    break;
                }
                T selectedEntity = list.stream().filter(entity -> entity.getId() == id).findFirst().orElse(null);
                if (selectedEntity != null) {
                    return selectedEntity;
                } else {
                    System.out.println("Wrong choice. Try again");
                }
            }
        }
        return null;
    }

}