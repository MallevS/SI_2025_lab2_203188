package si_lab2_203188;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test { 
    @Test
    void testEveryStatement() {
        // Test Case 1: Condition is true (price > 300)
        Item item = new Item("Item1", 1, 350, 0); // price > 300
        String cardNumber = "1234567890123456";
        assertEquals(350, SILab2.checkCart(List.of(item), cardNumber));

        // Test Case 2: Condition is false (price <= 300, discount = 0, quantity <= 10)
        Item item2 = new Item("Item2", 1, 200, 0); // price <= 300
        assertEquals(200, SILab2.checkCart(List.of(item2), cardNumber));
    }

    @Test
    void testMultipleCondition() {
        String cardNumber = "1234567890123456";

        // Test all combinations of A, B, C
        Item item1 = new Item("Item1", 10, 350, 0.15);  // A = T, B = T, C = T
        assertEquals(350, SILab2.checkCart(List.of(item1), cardNumber));

        Item item2 = new Item("Item2", 10, 350, 0.05); // A = T, B = T, C = F
        assertEquals(350, SILab2.checkCart(List.of(item2), cardNumber));

        Item item3 = new Item("Item3", 0, 350, 0.15); // A = T, B = F, C = T
        assertEquals(350, SILab2.checkCart(List.of(item3), cardNumber));

        Item item4 = new Item("Item4", 0, 350, 0.05); // A = T, B = F, C = F
        assertEquals(350, SILab2.checkCart(List.of(item4), cardNumber));

        Item item5 = new Item("Item5", 10, 200, 0.15); // A = F, B = T, C = T
        assertEquals(200, SILab2.checkCart(List.of(item5), cardNumber));

        Item item6 = new Item("Item6", 10, 200, 0.05); // A = F, B = T, C = F
        assertEquals(200, SILab2.checkCart(List.of(item6), cardNumber));

        Item item7 = new Item("Item7", 0, 200, 0.15); // A = F, B = F, C = T
        assertEquals(200, SILab2.checkCart(List.of(item7), cardNumber));

        Item item8 = new Item("Item8", 0, 200, 0.05); // A = F, B = F, C = F
        assertEquals(200, SILab2.checkCart(List.of(item8), cardNumber));
    }
}
