package practikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import java.util.List;
import org.junit.Assert;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;




@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
//"black bun"
    Bun bunBlack;
    @Mock
    Ingredient ingredientSauce;
    @Mock
    Ingredient ingredientFilling;


    @Test
    public void addIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(ingredientFilling);
        System.out.println("burger " + burger);
        System.out.println("ingredientFilling " + ingredientFilling);
        assertEquals("Добавлен 1 элемент в бургер", 1, burger.ingredients.size());
    }
    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(ingredientSauce);
        burger.removeIngredient(0);
        //     assertTrue(burger.ingredients.isEmpty());
        assertEquals("Удален элемент из бургера",0, burger.ingredients.size());
    }

    @Test
    public void getPriceTest() {
        Burger burger = new Burger();
        burger.setBuns(bunBlack);
        burger.addIngredient(ingredientFilling);
        burger.addIngredient(ingredientSauce);
        Mockito.when(bunBlack.getPrice()).thenReturn(100f);
        Mockito.when(ingredientFilling.getPrice()).thenReturn(200f);
        Mockito.when(ingredientSauce.getPrice()).thenReturn(200f);
        Assert.assertEquals(burger.getPrice(), 600, 0.1);
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        List<Ingredient> ingredient = new ArrayList<>();
        ingredient.add(ingredientFilling);
        ingredient.add(ingredientSauce);
        burger.moveIngredient(0, 1);
        Assert.assertEquals(burger.ingredients, ingredient);
    }

    @Test
    public void getReceiptTest() {
        Burger burger = new Burger();
        burger.setBuns(bunBlack);
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        Mockito.when(bunBlack.getName()).thenReturn("black bun");
        Mockito.when(ingredientSauce.getName()).thenReturn("hot sauce");
        Mockito.when(ingredientFilling.getName()).thenReturn("cutlet");
        Mockito.when(ingredientSauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredientFilling.getType()).thenReturn(IngredientType.FILLING);
        String newGetReceipt = "(==== black bun ====)\n" +
                "= sauce hot sauce =\n" +
                "= filling cutlet =\n" +
                "(==== black bun ====)\n" +
                "\n" +
                "Price: 0,000000" + "\n";
        String actualReceipt = burger.getReceipt();

        Assert.assertEquals(newGetReceipt.replace("\r\n", "\n"), actualReceipt.replace("\r\n", "\n"));
    }
}