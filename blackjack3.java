import java.util.Scanner;


public class blackjack3 {
    static boolean winningBet = false;
    static int money = 1000;
    static boolean gambling = true;
    static int playerCount = 0;
    static int houseCount = 0;
    static boolean start = false;
    static boolean turn = true;
    static boolean houseTurn = true;
    public static void main(String[] args){
        int[]deck = {1,2,3,4,5,6,7,8,9,10,10,10,11};
        Scanner in = new Scanner(System.in);
        while(gambling) {
            if(money==0){
                gambling = false;
            }
            playerCount = 0;
            houseCount = 0;
            start = false;
            turn = true;
            houseTurn = true;
            System.out.println("current balance: " + money);
            System.out.println("place bet");
            String gamba = in.next();
            if(Integer.parseInt(gamba)>money){
                gamba = money +"";
            }
            if(Integer.parseInt(gamba) == 0){
                gambling = false;
                }
            takeBet(gamba);
            while (!start) {
                if(Integer.parseInt(gamba) == 0){
                    start = true;
                }
                startgame(deck);
                System.out.println("Player sum is " + playerCount);
                System.out.println("House sum is " + houseCount);
                start = true;
            }
            while (turn) {
                if(Integer.parseInt(gamba) == 0){
                    turn = false;
                }
                System.out.println("hit or stand?");
                askPlayer(deck, in);
                System.out.println("Player sum is " + playerCount);
                checkWin(gamba);
            }
            while (houseTurn) {
                if(Integer.parseInt(gamba) == 0){
                    houseTurn = false;
                }
                house(deck, gamba);
                System.out.println("House sum is " + houseCount);
                checkWin(gamba);
            }
        }
    }


    public static void startgame(int[]deck){
        startPlayer(deck);
        startPlayer(deck);
        startHouse(deck);
    }


    public static int startPlayer(int[]deck){
        int card = deck[(int)(Math.random()*13)];
        if(card == 11 && playerCount >10){
            card = 1;
        }
        return playerCount += card;
    }


    public static int startHouse(int[]deck){
        int card = deck[(int)(Math.random()*13)];
        if(card == 11 && houseCount>10){
            card = 1;
        }
        return houseCount+= card;
    }
    public static void askPlayer(int[]deck, Scanner in){
        String play = in.next();
        if(play.equalsIgnoreCase("hit")){
            playerCount += deck[(int)(Math.random()*13)];
        } else if (play.equalsIgnoreCase("stand")) {
            turn = false;

        }
        else{
            System.out.println("Operation not recognized");
        }
    }
    public static void checkWin(String gamba){
        if(playerCount >21){
            System.out.println("Bust!");
            turn = false;
            System.out.println("balance: "+money);
        } else if (houseCount>21) {
            System.out.println("You win!");
            processBet(gamba);
            houseTurn = false;
            System.out.println("balance: "+money);
        }
    }
    public static void house(int[]deck, String gamba){
        if(houseCount<playerCount&&playerCount<=21){
            houseCount += deck[(int)(Math.random()*13)];}
        else if (houseCount == playerCount) {
            System.out.println("Tie!");
            houseTurn = false;
            money += Integer.parseInt(gamba);
            System.out.println("balance: "+money);
        } else if (houseCount>playerCount && houseCount<=21) {
            System.out.println("The house wins!");
            houseTurn = false;
        } else if (playerCount>21) {
            houseTurn = false;
        }
    }
    public static void takeBet(String gamba){
        int gambaValue = Integer.parseInt(gamba);
        money -= gambaValue;
    }
    public static void processBet(String gamba){
        money += Integer.parseInt(gamba)*2;
    }
}
