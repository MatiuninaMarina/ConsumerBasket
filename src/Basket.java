import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Basket implements Serializable {
    private static final long SerialversionUID = 1L;
    private String[] goods;
    private int[] prices;
    private int[] quantities;

    public String[] getGoods() {
        return goods;
    }

    public int[] getPrices() {
        return prices;
    }

    public int[] getQuantities() {
        return quantities;
    }

    public void setGoods(String[] goods) {
        this.goods = goods;
    }

    public void setPrices(int[] prices) {
        this.prices = prices;
    }

    public void setQuantities(int[] quantities) {
        this.quantities = quantities;
    }

    public Basket() {
    }

    public Basket(String[] goods, int[] prices) {
        this.goods = goods;
        this.prices = prices;
        this.quantities = new int[goods.length];
    }

    public void addToCart(int productNum, int amount) {
        quantities[productNum] += amount;
    }

    public void printCart() {
        int totalPrice = 0;
        System.out.println("Список покупок: ");
        for (int i = 0; i < goods.length; i++) {
            if (quantities[i] > 0) {
                int currentPrices = prices[i] * quantities[i];
                totalPrice += currentPrices;
                System.out.println(goods[i] + " " + prices[i] + " " + quantities[i] + " " + currentPrices);
            }
        }
        System.out.println("Итого " + totalPrice);
    }

    public void saveTxt(File textFile) throws IOException {
        File file;
        try (PrintWriter out = new PrintWriter(textFile)) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            String basket = objectMapper.writeValueAsString(this);
            out.print(basket);

        }

//        {
//            "goods": ["Хлеб", "Гречка"],
//            "prices": [100,200],
//            "quantities: [2,2]
//        }
    }

    public static Basket loadFromTxtFile(File textFile) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {
            String basketString = bufferedReader.readLine().trim();
            ObjectMapper objectMapper = new ObjectMapper();
            Basket basket = objectMapper.readValue(basketString, Basket.class);
            return basket;

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}

