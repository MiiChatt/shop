import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ShopOfManga {
    public static void playCoinGame(Customer customer, Scanner kb) {
        kb.useLocale(Locale.US);
        Coin coin = new Coin();

        System.out.println("\nYour current balance is: " + customer.getMoney() + " EUR");

        System.out.println("How do you want to bet?");
        System.out.println("1. Manual amount");
        System.out.println("2. Percentage of balance");
        System.out.println("0. Exit the game");
        System.out.print("Choose an option (1 or 2 or 0): ");
        int betType = kb.nextInt();

        while (betType != 1 && betType != 2 && betType != 0) {
            System.err.println("Invalid option. Please choose 1 for Manual amount or 2 for Percentage of balance or 0 to exit.");
            System.out.print("Choose an option (1 or 2 or 0): ");
            betType = kb.nextInt();
        }
        if (betType == 0) {
            kb.nextLine();
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
            System.err.println("Invalid bet. You must input a bet larger than 0 and up to your balance.");
            System.out.print("Input your bet again: ");
            betAmount = kb.nextDouble();
        }
        customer.setBet(betAmount);
        System.out.print("Choose (1 -> Heads, 2 -> Tails): ");
        int choice = kb.nextInt();
        kb.nextLine();

        String result = coin.flip();
        System.out.println("The coin landed on: " + result);

        String customerChoice = (choice == 1) ? "Heads" : "Tails";

        if (customerChoice.equalsIgnoreCase(result)) {
            System.out.println("Peak! you win $" + customer.getBet());
            customer.setMoney(customer.getMoney() + customer.getBet());
        } else {
            System.out.println("Twin... you lost xd $" + customer.getBet());
            customer.setMoney(customer.getMoney() - customer.getBet());
        }
        if (customer.getMoney() == 0) {
            System.out.println("\nYou are broke twin, lmao, it happens, your lose this time. Game over.");
        }
    }

    public static void playDiceGame(Customer customer, Scanner kb) {
        kb.useLocale(Locale.US);
        Dice dice = new Dice();

        boolean playing = true;
        while(playing) {
            System.out.println("\n ---Dice game---");
            System.out.println("1. Roll the dice.");
            System.out.println("2. Exit.");
            System.out.print("Choose an option: ");
            int option = kb.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Input the amount to bet: ");
                    double betAmount = kb.nextDouble();

                    while(betAmount <= 0 || betAmount > customer.getMoney()) {
                    System.err.println("Invalid bet amount. Input a bet adecuate to your money and bigger than zero.");
                    System.out.println("Input your bet again: ");
                    betAmount = kb.nextDouble();
                    }

                    int playerRoll = dice.roll();
                    System.out.println("You rolled: " + playerRoll);

                    int houseRoll = dice.roll();
                    System.out.println("The house rolled: " + houseRoll);

                    if(playerRoll > houseRoll) {
                        System.out.println("You win EUR" + betAmount);
                        customer.setMoney(customer.getMoney() + betAmount);
                    }
                    else if(houseRoll > playerRoll) {
                        System.out.println("You lose...");
                        customer.setMoney(customer.getMoney() - betAmount);
                    } 
                    else {System.out.println("It is a tie!");}
                    break;
                case 2:
                    playing = false;
                    System.out.println("Thanks for playing!");
                    return;
                default:
                    System.err.println("Invalid option. Please try again.");
                    break;
            }
        }
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
        stock.add(new Manga("YoRHa: Pearl Harbor Descent Record", 11.25));

        Customer customer = new Customer(50);
        Scanner kb = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n========================================");
            System.out.println("           MANGA SHOP & CASINO          ");
            System.out.println("========================================");
            System.out.println("Current Money: " + customer.getMoney() + " EUR");
            System.out.println("[1] Buy Mangas (Shop)");
            System.out.println("[2] Sell Mangas (Second-Hand)");
            System.out.println("[3] Games Zone (Casino)");
            System.out.println("[0] Leave ");
            System.out.print("Choose an option: ");
            choice = kb.nextInt();
            kb.nextLine();

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("\n---MANGA SHOP--- (money: " + customer.getMoney() + " EUR)");
                        if (stock.isEmpty()) {
                            System.out.println("Sold out! Nothing left to buy.");
                            break;
                        }

                        for (Manga m : stock) {
                            System.out.println("- " + m);
                        }

                        System.out.print("Type the full title of the manga you want (or 'back' to return): ");
                        String buyInput = kb.nextLine().trim();

                        if (buyInput.equalsIgnoreCase("back")) {
                            break;
                        }

                        int buyIndex = -1;
                        for (int i = 0; i < stock.size(); i++) {
                            if (stock.get(i).getTitle().equalsIgnoreCase(buyInput)) {
                                buyIndex = i;
                                break;
                            }
                        }

                        if (buyIndex == -1) {
                            System.err.println("The manga is not in the shop.");
                        } else if (customer.buy(stock.get(buyIndex))) {
                            stock.remove(buyIndex);
                            System.out.println("Purchase done!");
                        } else {
                            System.err.println("Not enough money!");
                        }
                    }
                    break;

                case 2:
                    ArrayList<Manga> inv = customer.getInventory();
                    if (inv.isEmpty()) {
                        System.out.println("Your inventory is empty!");
                        break;
                    }

                    System.out.println("---INVENTlORY---");
                    customer.showInventory();
                    System.out.print("Write the title of the manga you want to sell (or 'back' to return): ");
                    String sellInput = kb.nextLine().trim();

                    if (sellInput.equalsIgnoreCase("back")) {
                        break;
                    }

                    int sellIndex = -1;
                    for (int i = 0; i < inv.size(); i++) {
                        if (inv.get(i).getTitle().equalsIgnoreCase(sellInput)) {
                            sellIndex = i;
                            break;
                        }
                    }

                    if (sellIndex == -1) {
                        System.err.println("You don't have that manga in your inventory.");
                    } else {
                        Manga mangaToSell = inv.get(sellIndex);
                        double refund = Math.round(mangaToSell.getPrice() * 0.80 * 100) / 100.0;
                        inv.remove(sellIndex);
                        customer.addMoney(refund);
                        stock.add(mangaToSell);

                        System.out.println("Sell successful! You have received your refund!");
                        System.out.println("you will recieve 80% of what you have spent since its now a second-hand manga.");
                    }
                    break;

                case 3:
                    System.out.println("\n---CASINO---");
                    System.out.println("Welcome to the casino");

                    int game;
                    do {
                        System.out.println("Select a game:");
                        System.out.println("   1.Coin game.");
                        System.out.println("   2.Dice game.");
                        System.out.println("   0.Exit.");
                        System.out.print("Choose a game: ");
                        game = kb.nextInt();

                        if(game == 1) {
                            playCoinGame(customer, kb);
                        }
                        else if(game == 2) {
                            playDiceGame(customer, kb);
                        }
                        else if(game != 0) {
                            System.err.println("Invalid option. Try again");
                        }
                    } while(game != 0);
                    break;

                case 0:
                    System.out.println("\nThank for vistiting the manga shop and casino! We hope to see you soon!");
                    customer.showInventory();
                    break;

                default:
                    System.err.println("Invalid option, please choose an option between the numbers 0 and 3");
                    break;
            }
        } while (choice != 0);
        kb.close();
    }
}