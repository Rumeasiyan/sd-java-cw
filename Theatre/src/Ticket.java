public class Ticket {
    private final int row;
    private final int seat;
    private final double price;

    private final Person person;


    //constructor
    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public void print(){
        System.out.println("Ticket buyer name: " + person.getName()); //buyer name
        System.out.println("Ticket buyer surname: " + person.getSurname()); //buyer surname
        System.out.println("Ticket buyer email: " + person.getEmail()); //buyer email
        System.out.println("Ticket row number: " + row); //ticket row number
        System.out.println("Ticket seat number: " + seat); //ticket seat number
        System.out.println("Ticket price: $" + price ); //ticket price
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }
}

