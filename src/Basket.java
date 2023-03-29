import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Basket implements Serializable {
    private static final long SerialversionUID = 1L;
    private String[] goods;
    private int[] prices;
    private int[] quantities;

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
        try (PrintWriter out = new PrintWriter(textFile);) {
            for (String g : goods)
                out.print(g + " ");
            out.println();
            for (int p : prices)
                out.print(p + " ");
            out.println();
            for (int q : quantities)
                out.print(q + " ");
            out.println();
        }
    }

    public static Basket loadFromTxtFile(File textFile) throws IOException {
        Basket basket = new Basket();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {
            String goodsString = bufferedReader.readLine().trim();
            String pricesString = bufferedReader.readLine().trim();
            String quantitiesString = bufferedReader.readLine().trim();

            basket.goods = goodsString.split(" ");
            basket.prices = Arrays.stream(pricesString.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
            basket.quantities = Arrays.stream(quantitiesString.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return basket;
    }

    public void saveBin(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Basket loadFromBinFile(File file) {
        Basket basket = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            basket = (Basket) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }
}


