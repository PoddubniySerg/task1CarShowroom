public class Manager {
    private static final long RECEIVE_CAR_TIME = 1000;
    private static final long SELL_CAR_TIME = 1000;

    private final CarDealer carDealer;

    public Manager(CarDealer carDealer) {
        this.carDealer = carDealer;
    }

    public synchronized void receiveCar() {
        try {
            System.out.println("Производитель " + Thread.currentThread().getName() + " выпустил 1 авто");
            Thread.sleep(RECEIVE_CAR_TIME);
            this.carDealer.getCarList().add(new Car());
            System.out.println("Новый автомобиль доставлен в салон");
            notify();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (this.carDealer.getCarList().isEmpty()) {
                System.out.println("Машин нет");
                wait();
            }
            Thread.sleep(SELL_CAR_TIME);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.carDealer.getCarList().remove(0);
    }
}
