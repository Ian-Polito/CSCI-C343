public class Lab03 {

    public static void main(String[] args) {
        //Test MyCardSet implementation
        MyCardSet deck = new MyCardSet();
        System.out.println(deck.drawRandomCard());
        System.out.println(deck.drawRandomCard());

        MyCardSet deck2 = new MyCardSet();
        System.out.println(deck.drawRandomCard());
        System.out.println(deck.drawRandomCard());

        System.out.println("");

        //Test Tweet implementation
        Tweet t1 = new Tweet("Do or do not, there is no try", "Yoda");
        Tweet t2 = new Tweet("I'll be back","Arnold Shwarzenegger");
        Tweet t3 = new Tweet("Help, im trapped in this computer","unknown");

        t1.printContent();
        t2.printContent();
        t3.printContent();

        if (t2.contains("back")) {
            System.out.println("The string back is contained in tweet 2");
        } else {
            System.out.println("The string back is not contained in tweet 2");
        }
    }
}
