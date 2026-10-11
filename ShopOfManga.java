import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ShopOfManga {
    public static void playCoinGame(Customer customer, Scanner kb) {
        kb.useLocale(Locale.US);
        Coin coin = new Coin();

            System.out.println("\nYour current balance is: $" + customer.getMoney());

            System.out.println("How do you want to bet?");
            System.out.println("1. Manual amount");
            System.out.println("2. Percentage of balance");
            System.out.println("0. Exit the game");
            System.out.print("Choose an option (1 or 2 or 0): ");
            int  betType = kb.nextInt();

            while (betType != 1 && betType != 2 && betType != 0) {
                System.out.println("Invalid option. Please choose 1 for Manual amount or 2 for Percentage of balance or 0 to exit.");
                System.out.print("Choose an option (1 or 2 or 0): ");
                betType = kb.nextInt();
            }
            if (betType == 0) {
                kb.nextLine(); // Consume the newline character
                System.out.println("Returning to the shop...");
                return;
            }
            double betAmount = 0;

            if (betType == 1) {
                System.out.print("Input your bet amount: ");
                betAmount = kb.nextDouble();
            } else {
                System.out.print("Input the percentage of your balance to bet (1-100): ");
                double percentage = kb.nextDouble();
                betAmount = (percentage / 100) * customer.getMoney();
            }

            while (betAmount <= 0 || betAmount > customer.getMoney()) {
                System.out.println("Invalid bet. You must input a bet larger than 0 and up to your balance.");
                System.out.print("Input your bet again: ");
                betAmount = kb.nextDouble();
            }
            customer.setBet(betAmount);

            System.out.print("Choose (1 -> Heads, 2 -> Tails): ");
            int choice = kb.nextInt();

            String result = coin.flip();
            System.out.println("The coin landed on: " + result);

            String customerChoice = (choice == 1) ? "Heads" : "Tails";

            if (customerChoice.equalsIgnoreCase(result)) {
                System.out.println("Peak! you win $" + customer.getBet());
                customer.setMoney(customer.getMoney() + customer.getBet());
            }
            else {
                System.out.println("Twin... you lost xd $" + customer.getBet());
                customer.setMoney(customer.getMoney() - customer.getBet());
            }
            if (customer.getMoney() == 0) {
                System.out.println("\nYou are broke twin, lmao, it happens, your lose this time. Game over.");
            }
            kb.nextLine(); // Consume the newline character left by nextInt() or nextDouble()
        }

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

            System.out.print("Type the full title of the manga, 'gamble' to play the coin game, or 'exit' to leave: ");
            input = kb.nextLine().trim();

            if (input.equalsIgnoreCase("gamble")) {
                playCoinGame(customer, kb);
            } else if (!input.equalsIgnoreCase("exit")) {
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