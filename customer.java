import java.util.ArrayList;

public class Customer {
    private double money;
    private ArrayList<Manga> inventory;
    private double bet;

    public Customer(double initialMoney) {
        this.money = initialMoney;
        this.inventory = new ArrayList<>();
    }
     public double getMoney() {
        return money;
     }
         public void setBet(double bet) {
        this.bet = bet;
    }
    public double getBet() {
        return this.bet;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public void addMoney(double amount) {
        this.money += amount;
    }
    public ArrayList<Manga> getInventory() {
        return inventory;
    }
    public boolean buy(Manga manga) {
        if (money >= manga.getPrice()) {
            money -= manga.getPrice();
            inventory.add(manga);
            return true;
        }
        return false;
     }
     public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("You haven't purchased any manga yet.");
        }
        else {
            System.out.println("Your purchased manga is:");
            for (Manga m : inventory) {
                System.out.println("- " + m);
            }
        }
     }
    }    
