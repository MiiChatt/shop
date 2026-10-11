import java.util.ArrayList;

class Customer {
    private double money;
    private ArrayList<Manga> inventory;

    public Customer(double initialMoney) {
        this.money = initialMoney;
        this.inventory = new ArrayList<>();
    }
     public double getMoney() {
        return money;
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
