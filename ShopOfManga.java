import java.util.ArrayList;
import java.util.Scanner;

public class ShopOfManga {
    public static void main(String[] args) {
        ArrayList<Manga> stock = new ArrayList<>();
        stock.add(new Manga("Made in Abyss", 9.99));
        stock.add(new Manga("Naruto", 5.99));
        stock.add(new Manga("Attack on Titan", 12.99));
        stock.add(new Manga("Naruto Shippuden", 15.99));
        stock.add(new Manga("One Piece", 10.99));
        stock.add(new Manga("RE: Zero Starting life in Another World", 14.99));
        stock.add(new Manga("Demon Slayer", 12.99));
        stock.add(new Manga("My Hero Academia", 11.99));
        stock.add(new Manga("Tokyo Revengers", 13.99));
        stock.add(new Manga("Jujutsu Kaisen", 9.99));
        stock.add(new Manga("Chainsaw Man", 14.99));

        Customer customer = new Customer(50);
        Scanner kb = new Scanner(System.in);
        String input;

        do {
            System.out.println("\n--- MANGA SHOP --- (money: " + customer.getMoney() + "$)");

            if (stock.isEmpty()) {
                System.out.println("Sold out! Nothing left to buy.");
            }
            for (Manga m : stock) {
                System.out.println("- " + m);
            }
            System.out.print("Type the full title of the manga you want (or 'exit'): ");

            input = kb.nextLine().trim();

            if (!input.equalsIgnoreCase("exit")) {
                int index = -1;
                for (int i = 0; i < stock.size(); i++) {
                    if (stock.get(i).getTitle().equalsIgnoreCase(input)) {
                        index = i;
                        break;
                    }
                }

                if (index == -1) {
                    System.out.println("That manga is not in the shop.");
                } else if (customer.buy(stock.get(index))) {
                    stock.remove(index);
                    System.out.println("Purchase done!");
                } else {
                    System.out.println("Not enough money.");
                }
            }
        } while (!input.equalsIgnoreCase("exit"));

        System.out.println("\nYour inventory:");
        customer.showInventory();
        kb.close();
    }
}