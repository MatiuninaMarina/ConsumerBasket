import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {
    private List productNumbers = new ArrayList();
    private List amounts = new ArrayList();

    public void log(int productNum, int amount) {
        productNumbers.add(productNum);
        amounts.add(amount);

    }

    public void exportAsCSV(File txtFile) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(txtFile)) {
            out.println("productNum,amount");
            for (int i=0; i < productNumbers.size(); i++) {
                out.println(productNumbers.get(i) + "," + amounts.get (i));
            }

        }
    }
}
