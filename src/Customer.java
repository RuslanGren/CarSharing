package carsharing;

public class Customer extends BaseEntity {
    private Integer rented_car_id;

    public Customer(int id, String name, Integer rented_car_id) {
        super(id, name);
        this.rented_car_id = rented_car_id;
    }

    public Integer getRented_car_id() {
        return rented_car_id;
    }

    public void setRented_car_id(Integer rented_car_id) {
        this.rented_car_id = rented_car_id;
    }
}
