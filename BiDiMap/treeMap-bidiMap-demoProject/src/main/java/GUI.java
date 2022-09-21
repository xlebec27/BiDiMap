import cs.vsu.ru.treeMap_bidiMap.BidiMap;
import cs.vsu.ru.treeMap_bidiMap.TreeMap;

import java.util.Scanner;

public class GUI {

    Scanner scanner = new Scanner(System.in);

    public void demoInterfaceTreeMapInteger(TreeMap<Integer, String> treeMap){
        printOptionsTreeMap();
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> {
                System.out.println("Enter key:");
                int key = scanner.nextInt();
                System.out.println("Enter value:");
                scanner.nextLine();
                String value = scanner.nextLine();
                treeMap.put(key, value);
            }
            case 2 -> {
                System.out.println("");
                int key2 = scanner.nextInt();
                treeMap.remove(key2);
            }

            case 3 -> {
                System.out.println("Enter key:");
                int key3 = scanner.nextInt();
                if(treeMap.containsKey(key3)){
                    System.out.println("Key " + key3 + " found");
                } else {
                    System.out.println("Key does not found");
                }
            }

            case 4 -> System.out.println(treeMap.toString());

            case 5 -> System.exit(0);
        }
        demoInterfaceTreeMapInteger(treeMap);
    }

    public void demoInterfaceTreeMapString(TreeMap<String, Double> treeMap){
        printOptionsTreeMap();
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> {
                System.out.println("Enter key:");
                scanner.nextLine();
                String key = scanner.nextLine();
                System.out.println("Enter value:");
                double value = scanner.nextDouble();
                treeMap.put(key, value);
            }
            case 2 -> {
                System.out.println("Enter key");
                scanner.nextLine();
                String key2 = scanner.nextLine();
                treeMap.remove(key2);
            }

            case 3 -> {
                System.out.println("Enter key:");
                scanner.nextLine();
                String key3 = scanner.nextLine();
                if(treeMap.containsKey(key3)){
                    System.out.println("Key " + key3 + " found");
                } else {
                    System.out.println("Key does not found");
                }
            }

            case 4 -> System.out.println(treeMap.toString());

            case 5 -> System.exit(0);
        }
        demoInterfaceTreeMapString(treeMap);
    }

    public void printOptionsTreeMap(){
        System.out.println("Choose option:\n1. Add\n2. Delete\n3. Search\n4. Print collection\n5. Exit");
    }

    public void TreeMapInterface() {
        printTypes();
        int type = scanner.nextInt();
        switch (type){
            case 1 -> demoInterfaceTreeMapInteger(new TreeMap<>());
            case 2 -> demoInterfaceTreeMapString(new TreeMap<>());
        }
    }

    public void bidiMapInterface() {
        printTypes();
        int type = scanner.nextInt();
        switch (type){
            case 1 -> demoInterfaceBidiMapInteger(new BidiMap<>());
            case 2 -> demoInterfaceBidiMapString(new BidiMap<>());
        }
    }

    private <K> void demoInterfaceBidiMapInteger(BidiMap<Integer, String> bidiMap) {
        printOptionsBidiMap();
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> {
                System.out.println("Enter key:");
                int key = scanner.nextInt();
                System.out.println("Enter value:");
                scanner.nextLine();
                String value = scanner.nextLine();
                bidiMap.put(key, value);
            }
            case 2 -> {
                System.out.println("Enter key:");
                int key2 = scanner.nextInt();
                bidiMap.removeByKey(key2);
            }
            case 3 -> {
                System.out.println("Enter value:");
                scanner.nextLine();
                String key2 = scanner.nextLine();
                bidiMap.removeByValue(key2);
            }
            case 4 -> {
                System.out.println("Enter key:");
                int key3 = scanner.nextInt();
                if(bidiMap.containsKey(key3)){
                    System.out.println("Key " + key3 + " found");
                } else {
                    System.out.println("Key does not found");
                }
            }
            case 5 -> {
                System.out.println("Enter value:");
                scanner.nextLine();
                String key3 = scanner.nextLine();
                if(bidiMap.containsValue(key3)){
                    System.out.println("Value " + key3 + " found");
                } else {
                    System.out.println("Value does not found");
                }
            }
            case 6 -> System.out.println(bidiMap.toString());

            case 7 -> System.exit(0);
        }
        demoInterfaceBidiMapInteger(bidiMap);
    }

    private <K> void demoInterfaceBidiMapString(BidiMap<String, Double> bidiMap) {
        printOptionsBidiMap();
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> {
                System.out.println("Enter key:");
                scanner.nextLine();
                String key = scanner.nextLine();
                System.out.println("Enter value:");
                double value = scanner.nextDouble();
                bidiMap.put(key, value);
            }
            case 2 -> {
                System.out.println("Enter key:");
                scanner.nextLine();
                String key2 = scanner.nextLine();
                bidiMap.removeByKey(key2);
            }
            case 3 -> {
                System.out.println("Enter value:");
                double key2 = scanner.nextDouble();
                bidiMap.removeByValue(key2);
            }
            case 4 -> {
                System.out.println("Enter key:");
                scanner.nextLine();
                String key3 = scanner.nextLine();
                if(bidiMap.containsKey(key3)){
                    System.out.println("Key " + key3 + " found");
                } else {
                    System.out.println("Key does not found");
                }
            }
            case 5 -> {
                System.out.println("Enter value:");
                double key3 = scanner.nextDouble();
                if(bidiMap.containsValue(key3)){
                    System.out.println("Value " + key3 + " found");
                } else {
                    System.out.println("Value does not found");
                }
            }
            case 6 -> System.out.println(bidiMap.toString());

            case 7 -> System.exit(0);
        }
        demoInterfaceBidiMapString(bidiMap);
    }

    private void printTypes(){
        System.out.println("Choose types:\n1. Integer, String\n2. String, Double");
    }

    public void printOptionsBidiMap(){
        System.out.println("Choose option:\n1. Add\n2. Delete by Key\n3. Delete by Value\n4. Search Key\n" +
                "5. Search Value\n6. Print collection\n7. Exit");
    }
}
