import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe{
    static ArrayList<Integer> humanPositions = new ArrayList<Integer>();
    static ArrayList<Integer> computerPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        char[][] board = {{' ', '|', ' ', '|', ' '},
                        {'-', '+', '-', '+', '-'},
                        {' ', '|', ' ', '|', ' '},
                        {'-', '+', '-', '+', '-'},
                        {' ', '|', ' ', '|', ' '}};

        printBoard(board);

        while(true){
            System.out.print("Choose between 1-9: ");
            int humanOp = scan.nextInt();
            while(humanPositions.contains(humanOp) || computerPositions.contains(humanOp)){
                System.out.println("Position taken!");
                humanOp = scan.nextInt();
            }
            placePosition(board, humanOp, "human");

            String results = checkWin();
            if(results.length() > 0){
                System.out.println(results);
                break;
            }

            Random r = new Random();
            int computerOp = r.nextInt(9) + 1;
            while(humanPositions.contains(computerOp) || computerPositions.contains(computerOp)){
                computerOp = r.nextInt(9) + 1;
            }
            placePosition(board, computerOp, "computer");

            printBoard(board);

            results = checkWin();
            if(results.length() > 0){
                System.out.println(results);
                break;
            }
        }
    }

    public static void printBoard(char[][] board){
        for(char[] row : board){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePosition(char[][] board, int op, String player){
        char symbol = ' ';

        if(player.equals("human")){
            symbol = 'x';
            humanPositions.add(op);
        }else if(player.equals("computer")){
            symbol = 'o';
            computerPositions.add(op);
        }

        switch(op){
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
        }
    }

    public static String checkWin(){
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);

        List leftColumm = Arrays.asList(1, 4, 7);
        List midColumm = Arrays.asList(2, 5, 8);
        List rightColumm = Arrays.asList(3, 6, 9);

        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> win = new ArrayList<List>();

        win.add(topRow);
        win.add(midRow);
        win.add(bottomRow);
        win.add(leftColumm);
        win.add(midColumm);
        win.add(rightColumm);
        win.add(cross1);
        win.add(cross2);

        for(List l : win){
            if(humanPositions.containsAll(l)){
                return "Human wins!";
            }else if(computerPositions.containsAll(l)){
                return "Computer wins!";
            }else if(humanPositions.size() + computerPositions.size() == 9){
                return "Draw!";
            }
        }

        return "";
    }
}