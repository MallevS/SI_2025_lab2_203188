# Лабораториска вежба 2 - Системско инженерство 2025
Стојан Малев (203188)  
---

## 2. Control Flow Graph (CFG) за функцијата `checkCart`

![CFG Дијаграм](https://github.com/user-attachments/assets/afe280e2-07df-4023-9406-9fd12ebf7e9d)

CFG вклучува:
- Почетен јазол (entry node)
- Јазол за јамка за итерација низ ставките во кошничката
- Јазли за условни проверки:
  - Главна OR-состојба `(price > 300 || discount > 0 || quantity > 10)`
  - Проверки за нула (null checks)
- Рабови кои го претставуваат текот на извршување
- Завршен јазол (exit node)

---

## 3. Пресметка на цикломатска сложеност

Според формулата на McCabe: CC = E - N + 2
Каде:
- E = Број на рабови
- N = Број на јазли

Алтернативен начин:
CC = Број на точки на одлука + 1

За функцијата `checkCart`:
- 3 точки на одлука (цена, попуст, количина)
- **CC = 3 + 1 = 4**

---

## 4. Стратегија за Every-Statement Coverage

| Тест случај | Опис             | Покриени изрази       |
|-------------|------------------|-----------------------|
| 1           | Празна кошничка (`items = null`) | Код за обработка на исклучоци |
| 2           | Валидна ставка со сите вредности под праг | Јамка, else гранка |
| 3           | Ставка со цена > 300 | If гранка |
| 4           | Ставка со попуст > 0 | If гранка |
| 5           | Ставка со количина > 10 | If гранка |
| 6           | Мешани ставки во кошничка | Повеќе патеки |
| 7           | Невалиден број на картичка | Валидациски проверки |

**Цел**: 100% покриеност на кодот.

---

## 5. Multiple-Condition Coverage

Тестирање на сите 8 комбинации од условот `(price > 300 || discount > 0 || quantity > 10)`:

| Случај | Цена | Попуст | Количина | Очекувана патека |
|--------|-------|---------|-----------|------------------|
| 1      | 500   | 5       | 15        | If-гранка (TTT)  |
| 2      | 500   | 5       | 5         | If-гранка (TTF)  |
| 3      | 500   | 0       | 15        | If-гранка (TFT)  |
| 4      | 500   | 0       | 5         | If-гранка (TFF)  |
| 5      | 200   | 5       | 15        | If-гранка (FTT)  |
| 6      | 200   | 5       | 5         | If-гранка (FTF)  |
| 7      | 200   | 0       | 15        | If-гранка (FFT)  |
| 8      | 200   | 0       | 5         | Else-гранка (FFF)|

---

## Имплементација на тестови
Тестовите се пишуваат со JUnit. Пример:

```java
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

