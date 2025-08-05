package practikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class BurgerParamTest {
    private Burger burger;
    private Ingredient ingredientSauce;
    private Ingredient ingredientFilling;
    private final String name;
    private final float price;

    public BurgerParamTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Before
    public void createNewInstance() {
        ingredientSauce = new Ingredient(IngredientType.SAUCE, "sour cream", 200.0f);
        ingredientFilling = new Ingredient(IngredientType.FILLING, "dinosaur", 200.0f);
        burger = new Burger();
    }

    @Parameterized.Parameters(name = "{0} : price = {1}")
    public static Object[][] getBunData() {
        return new Object[][]{
                {"black bun", 100.0f},
                {"white bun", 200.0f}
        };
    }

    @Test
    public void getPrice() {
        Bun bun = new Bun(name, price);
        burger.setBuns(bun);
        System.out.println("1} " + bun.getName());
        burger.addIngredient(ingredientSauce);
        System.out.println("2} ingredientSauce " + ingredientSauce.getName() + " " + burger.getPrice());
        burger.addIngredient(ingredientFilling);
        System.out.println("3} ingredientFilling " + ingredientFilling.getName() + " " + burger.getPrice());
        float expected = bun.price * 2 + ingredientSauce.price + ingredientFilling.price;
        float actual = burger.getPrice();
        System.out.println("expected: " + expected + ", actual: " + actual);
        assertEquals("Incorrect values burger price", expected, actual, 0);
    }
}
