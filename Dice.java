public class Dice {

    private int num;

    public Dice() {
        this.num = 1;
    }

    public int roll() {
        this.num = (int) (Math.random() * 6) + 1;
        return this.num;
    }

    public int getNum() {
        return this.num;
    }

    @Override
    public String toString() {
        return String.valueOf(this.num);
    }
}