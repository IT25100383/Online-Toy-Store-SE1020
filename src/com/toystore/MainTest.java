import com.toystore.toy.model.toy;
import com.toystore.toy.service.ToyService;

public class MainTest {

    public static void main(String[] args) {

        ToyService service = new ToyService();

        // CREATE
        service.addToy(new toy("T01", "Toy Car", 10.5, 5, 20));
        service.addToy(new toy("T02", "Teddy Bear", 12.0, 3, 15));
        service.addToy(new toy("T03", "Lego Set", 25.0, 8, 10));

        // READ
        System.out.println("Before Sorting:");
        service.viewToys();

        // SORT
        service.sortToys();

        // UPDATE
        service.updateToy("T01", 15.0, 30);

        // DELETE
        service.deleteToy("T02");

        System.out.println("\nAfter Update & Delete:");
        service.viewToys();

        System.out.println("\nAfter Update & Delete:");
        service.viewToys();
    }
}
