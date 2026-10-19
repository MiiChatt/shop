public class Coin {

    private String result;

    public Coin() {
        this.result = "heads";
    }
    public String flip() {
        int random = (int) (Math.random() * 2);
        if (random == 1) {
            this.result = "Heads";
        }
        else {
            this.result = "Tails";
        }
        return this.result;
    }
    public String getResult() {
        return this.result;
    }
}