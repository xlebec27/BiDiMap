import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        GUI gui = new GUI();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose collection ");
        System.out.println("1. TreeMap");
        System.out.println("2. BidiMap");
        String line = scanner.nextLine();
        switch (line) {
            case "1" -> gui.TreeMapInterface();
            case "2" -> gui.bidiMapInterface();
        }
    }
}
