import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static ClientLog clientLog = new ClientLog();
    static Scanner scanner = new java.util.Scanner(System.in);
    static String[] products = {"Хлеб", "Молоко", "Гречка"};
    static int[] prices = {14, 50, 80};
    static File saveFile = new File("basket.txt");

    public static void main(String[] args) throws IOException {

        Basket basket = null;
        if (saveFile.exists()) {
            basket = Basket.loadFromTxtFile(saveFile);
        } else {
            basket = new Basket(products, prices);
        }

        while (true) {
            showPrice();
            System.out.println("Выберете товар и количество через пробел или введите 'end' ");
            String input = scanner.nextLine();

            if ("end".equals(input)) {
                break;
            }
            String[] parts = input.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);
            clientLog.log(productNumber + 1, productCount);
            basket.addToCart(productNumber, productCount);
            basket.saveTxt(saveFile);
        }
        basket.printCart();
        clientLog.exportAsCSV(new File ("log.csv"));
    }

    private static void showPrice() {
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i] + " " + prices[i]);
        }
    }


}
