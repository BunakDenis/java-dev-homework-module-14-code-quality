package global.goit.edu.tictactoe;

import java.util.Scanner;

public class TicTacToe {

    private static byte input;
    private static byte rand;
    private static byte i;
    private static boolean boxEmpty = false;
    private static boolean boxAvailable = false;
    private static byte winner = 0;
    private static char box[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static String parting = "Good Bye!";
    private static Scanner scan = new Scanner(System.in);

    public static void play() {

        System.out.println("Enter box number to select. Enjoy!\n");

        while (true) {
            presentationGame();

            emptyTheBox();

            if (winner == 1) {
                System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");

                if (isContinueGame()) {
                    TicTacToe.play();
                } else {
                    System.out.println(parting);
                    break;
                }
            } else if (winner == 2) {
                System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");

                if (isContinueGame()) {
                    TicTacToe.play();
                } else {
                    System.out.println(parting);
                    break;
                }
            } else if (winner == 3) {
                System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");

                if (isContinueGame()) {
                    TicTacToe.play();
                } else {
                    System.out.println(parting);
                    break;
                }
            }

            while (true) {
                input = scan.nextByte();
                if (input > 0 && input < 10) {
                    if (box[input - 1] == 'X' || box[input - 1] == 'O')
                        System.out.println("That one is already in use. Enter another.");
                    else {
                        box[input - 1] = 'X';
                        break;
                    }
                } else
                    System.out.println("Invalid input. Enter again.");
            }

            if ((box[0] == 'X' && box[1] == 'X' && box[2] == 'X') || (box[3] == 'X' && box[4] == 'X' && box[5] == 'X')
                    || (box[6] == 'X' && box[7] == 'X' && box[8] == 'X')
                    || (box[0] == 'X' && box[3] == 'X' && box[6] == 'X')
                    || (box[1] == 'X' && box[4] == 'X' && box[7] == 'X')
                    || (box[2] == 'X' && box[5] == 'X' && box[8] == 'X')
                    || (box[0] == 'X' && box[4] == 'X' && box[8] == 'X')
                    || (box[2] == 'X' && box[4] == 'X' && box[6] == 'X')) {
                winner = 1;
                continue;
            }

            boxAvailable = false;
            for (i = 0; i < 9; i++) {
                if (box[i] != 'X' && box[i] != 'O') {
                    boxAvailable = true;
                    break;
                }
            }

            if (boxAvailable == false) {
                winner = 3;
                continue;
            }

            while (true) {
                rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
                if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                    box[rand - 1] = 'O';
                    break;
                }
            }

            if ((box[0] == 'O' && box[1] == 'O' && box[2] == 'O') || (box[3] == 'O' && box[4] == 'O' && box[5] == 'O')
                    || (box[6] == 'O' && box[7] == 'O' && box[8] == 'O')
                    || (box[0] == 'O' && box[3] == 'O' && box[6] == 'O')
                    || (box[1] == 'O' && box[4] == 'O' && box[7] == 'O')
                    || (box[2] == 'O' && box[5] == 'O' && box[8] == 'O')
                    || (box[0] == 'O' && box[4] == 'O' && box[8] == 'O')
                    || (box[2] == 'O' && box[4] == 'O' && box[6] == 'O')) {
                winner = 2;
            }
        }
    }

    private static void emptyTheBox() {
        if (!boxEmpty) {
            for (i = 0; i < 9; i++)
                box[i] = ' ';
            boxEmpty = true;
        }
    }

    private static void presentationGame() {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

    private static boolean isContinueGame() {
        String inputAnswer;
        String questionToPlayer = "Enter Yes(Y) or No (N)";

        while (true) {
            System.out.println("\n");

            switch (winner) {
                case 1:
                    System.out.println("Do you want to try again?");
                    System.out.println(questionToPlayer);
                    break;

                case 2:
                    System.out.println("Do you want revenge?");
                    System.out.println(questionToPlayer);
                    break;

                case 3:
                    System.out.println("Do you want to win the game?");
                    System.out.println(questionToPlayer);
                    break;

                default:
                    break;
            }

            while (true) {
                inputAnswer = scan.next().toLowerCase();

                if (inputAnswer.equals("y")) {
                    System.out.println("\n");
                    restartGame();
                    return true;
                } else if (inputAnswer.equals("n")) {
                    return false;
                } else {
                    System.out.println("\n");
                    System.out.println("Invalid input. Enter again.");
                }
            }
        }
    }

    //Обнуление всех значений для новой игры
    private static void restartGame() {
        boxEmpty = false;
        boxAvailable = false;
        winner = 0;
        char[] boxForRestart = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

        System.arraycopy(boxForRestart, 0, box, 0, box.length);
    }
}