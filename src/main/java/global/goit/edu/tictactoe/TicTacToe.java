package global.goit.edu.tictactoe;

import java.util.Scanner;

public class TicTacToe {

    private int i;
    private boolean boxEmpty = false;
    private boolean boxAvailable = false;
    private byte winner = 0;
    private char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final String PARTING = "Good Bye!";
    private static final char PLAYER_MOVE = 'X';
    private static final char COMPUTER_MOVE = 'O';
    private static final Scanner scan = new Scanner(System.in);
    private static final TicTacToe game = new TicTacToe();

    public void play() {

        System.out.println("Enter box number to select. Enjoy!\n");

        while (true) {
            presentationGame();

            emptyTheBox();

            if (checkingWinner()) break;

            checkingPlayerMove();

            if (checkingForWinner(PLAYER_MOVE)
            || checkingIfDraw()) continue;

            checkingComputerMove();

            checkingForWinner(COMPUTER_MOVE);
        }
    }

    private boolean checkingForWinner(char move) {
        if ((box[0] == move && box[1] == move && box[2] == move) || (box[3] == move && box[4] == move && box[5] == move)
                || (box[6] == move && box[7] == move && box[8] == move)
                || (box[0] == move && box[3] == move && box[6] == move)
                || (box[1] == move && box[4] == move && box[7] == move)
                || (box[2] == move && box[5] == move && box[8] == move)
                || (box[0] == move && box[4] == move && box[8] == move)
                || (box[2] == move && box[4] == move && box[6] == move)) {

            if (move == PLAYER_MOVE) {
                winner = 1;
                return true;
            } else if (move == COMPUTER_MOVE) {
                winner = 2;
                return true;
            }
        }
        return false;
    }

    private void checkingComputerMove() {
        while (true) {
            byte rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box[rand - 1] != PLAYER_MOVE && box[rand - 1] != COMPUTER_MOVE) {
                box[rand - 1] = COMPUTER_MOVE;
                break;
            }
        }
    }

    private boolean checkingIfDraw() {
        boxAvailable = false;
        for (i = 0; i < 9; i++) {
            if (box[i] != PLAYER_MOVE && box[i] != COMPUTER_MOVE) {
                boxAvailable = true;
                break;
            }
        }

        if (boxAvailable == false) {
            winner = 3;
            return true;
        }
        return false;
    }

    private void checkingPlayerMove() {
        while (true) {
            byte input = scan.nextByte();
            if (input > 0 && input < 10) {
                if (box[input - 1] == PLAYER_MOVE || box[input - 1] == COMPUTER_MOVE)
                    System.out.println("That one is already in use. Enter another.");
                else {
                    box[input - 1] = PLAYER_MOVE;
                    break;
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    private boolean checkingWinner() {
        if (winner == 1) {
            System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");

            if (isContinueGame()) {
                game.play();
            } else {
                System.out.println(PARTING);
                return true;
            }
        } else if (winner == 2) {
            System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");

            if (isContinueGame()) {
                game.play();
            } else {
                System.out.println(PARTING);
                return true;
            }
        } else if (winner == 3) {
            System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");

            if (isContinueGame()) {
                game.play();
            } else {
                System.out.println(PARTING);
                return true;
            }
        }
        return false;
    }

    private void emptyTheBox() {
        if (!boxEmpty) {
            for (i = 0; i < 9; i++)
                box[i] = ' ';
            boxEmpty = true;
        }
    }

    private void presentationGame() {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

    private boolean isContinueGame() {
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
                    System.exit(0);
                    return false;
                } else {
                    System.out.println("\n");
                    System.out.println("Invalid input. Enter again.");
                }
            }
        }
    }

    //Обнуление всех значений для новой игры
    private void restartGame() {
        boxEmpty = false;
        boxAvailable = false;
        winner = 0;
        char[] boxForRestart = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

        System.arraycopy(boxForRestart, 0, box, 0, box.length);
    }
}