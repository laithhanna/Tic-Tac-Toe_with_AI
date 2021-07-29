package tictactoe;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        while (true) {
            String[] players = gameOptions();
            if (players[0].equals("start")) {
                String[][] game = startGame();

                //player vs AI
                if (players[1].equals("user") && (players[2].equals("easy") || players[2].equals(("medium")) || players[2].equals("hard"))) {
                    if (players[2].equals("easy")) {
                        playerVsComputer(game, "user", "easy");
                    } else if (players[2].equals("medium")) {
                        playerVsComputer(game, "user", "medium");
                    } else if (players[2].equals("hard")) {
                        playerVsComputer(game,"user", "hard");
                    }

                }
                //player vs player
                else if (players[1].equals("user") && players[2].equals("user")) {
                    playerVsPlayer(game, "user", "user");
                }
                //AI vs player
                else if ((players[1].equals("easy") || players[1].equals("medium") || players[1].equals("hard")) && players[2].equals(("user"))) {
                    if (players[1].equals("easy")) {
                        computerVsPlayer(game, "easy", "user");
                    } else if (players[1].equals("medium")) {
                        computerVsPlayer(game, "medium", "user");
                    }else if (players[1].equals("hard")) {
                        computerVsPlayer(game, "hard", "user");
                    }
                }
                //AI vs AI
                else if ((players[1].equals("easy") || players[1].equals("medium") || players[1].equals("hard")) && (players[2].equals("easy") || players[2].equals("medium")) || players[2].equals("hard")) {
                    if (players[1].equals("easy") && players[2].equals("easy")) {
                        computerVsComputer(game, "easy", "easy");
                    } else if (players[1].equals("easy") && players[2].equals("medium")) {
                        computerVsComputer(game, "easy", "medium");
                    } else if (players[1].equals("easy") && players[2].equals("hard")) {
                        computerVsComputer(game, "easy", "hard");
                    } else if (players[1].equals("medium") && players[2].equals("easy")) {
                        computerVsComputer(game, "medium", "easy");
                    } else if (players[1].equals("medium") && players[2].equals("medium")) {
                        computerVsComputer(game, "medium", "medium");
                    } else if (players[1].equals("medium") && players[2].equals("hard")) {
                        computerVsComputer(game, "medium", "hard");
                    }  else if (players[1].equals("hard") && players[2].equals("easy")) {
                        computerVsComputer(game, "hard", "easy");
                    } else if (players[1].equals("hard") && players[2].equals("medium")) {
                        computerVsComputer(game, "hard", "medium");
                    } else if (players[1].equals("hard") && players[2].equals("hard")) {
                        computerVsComputer(game, "hard", "hard");
                    }
                }
            } else if (players[0].equals("exit")) {
                break;
            }
        }
    }
    public static String[] gameOptions() {
        /**
         * this method starts/end the game
         * if start is chosen, then you will pick who will be playing in the game
         * and the difficulty of the AI, if chosen.
         * you can choose: player vs player, player vs AI, AI vs player and AI vs AI
         * finally, this method checks whether the input is valid.
         */
        Scanner scanner = new Scanner(System.in);
        String[] options = new String[3];
        while (true) {
            System.out.print("Input command: ");
            options = scanner.nextLine().trim().split(" ");
            if (options[0].equals("exit")) {
                return options;
            }
            if (options.length != 3) {
                System.out.println("Bad parameters!");
            } else if (options[0].equals("start")) {
                if (options[1].equals("user") || options[1].equals("easy") || options[1].equals("medium") || options[1].equals("hard")) {
                    if (options[2].equals("user") || options[2].equals("easy") || options[2].equals("medium") || options[2].equals("hard")) {
                        return options;
                    } else {
                        System.out.println("Bad parameters!");
                    }
                } else {
                    System.out.println("Bad parameters!");
                }
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }
    public static String[][] startGame() {
        System.out.println("---------\n|       |\n|       |\n|       |\n---------"); //print empty board
        String[][] game = new String[3][3];
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[i].length; j++) {
                game[i][j] = " ";
            }
        }
        return game;
    } //this method does not change
    public static void userInput(String[][] game, int position) {
        /**
         * this method is responsible for the movement of the player.
         * it checks whether the player performs a valid move: if yes it performs it, but if not
         * it ask the user for another valid movement
         * this method also checks whether the player will start (player will be X [position = 1]) or
         * if player plays second and thus the player will be "O" [position = 2]
         */
        Scanner scanner = new Scanner(System.in);
        while(true) {
            int row;
            int column;
            System.out.print("Enter the coordinates: ");
            //scanner.nextLine(); is used before each continue statement to clear the buffer everytime we iterate through the loop
            //when we use scanner.next/.nextInt...etc we need to clear the buffer using scanner.nextLine before we use
            //scanner.next/.nextInt...etc again
            if (scanner.hasNextInt()) {
                row = scanner.nextInt();
            } else {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
                continue;
            }
            if (scanner.hasNextInt()) {
                column = scanner.nextInt();
            } else {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
                continue;
            }
            if ((row < 1 || row > 3) || (column < 1 || column > 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
                scanner.nextLine();
                continue;
            }

            if (game[row - 1][column - 1].equals(" ")) {
                if (position == 1) {
                    game[row - 1][column - 1] = "X";
                    break;
                } else if (position == 2) {
                    game[row - 1][column - 1] = "O";
                    break;
                }
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                scanner.nextLine();
            }
        }
    } //this method does not change
    public static void displayResults(String[][] board) {
        System.out.println("---------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    } //this method does not change
    public static void easyLevel(String[][] game, int position) {
        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        boolean nextTurn = false;
        while (!nextTurn) {
            int row = random.nextInt(3);
            int column = random.nextInt(3);
            if (game[row][column].equals(" ")) {
                if (position == 1) {
                    game[row][column] = "X";
                    nextTurn = true;
                } else if (position == 2) {
                    game[row][column] = "O";
                    nextTurn = true;
                }
            }
        }
    }
    public static boolean status(String[][] game) {
        boolean state = true;
        //lets analyze the game
        //lets check for 3 consecutive symbols in a row in horizontal direction
        int counterX = 0;
        int counterO = 0;

        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[i].length; j++) {
                if (game[i][j].equals("X") && counterX != 3) {
                    counterX++;
                } else if (game[i][j].equals("O") && counterX != 3) {
                    counterO++;
                }
            }
            if (counterX == 3) {
                System.out.println("X wins");
                state = false;
                break;
            } else if (counterO == 3) {
                System.out.println("O wins");
                state = false;
                break;
            }
            counterX = 0;
            counterO = 0;
        }
        //check for 3 consecutive symbols in vertical direction
        if (state) {

            int temp = 0;
            counterX = 0;
            counterO = 0;
            while (temp < 3) {
                for (int i = 0; i < game.length; i++) {
                    if (game[i][temp].equals("X")) {
                        counterX++;
                    } else if (game[i][temp].equals("O")) {
                        counterO++;
                    }
                }
                if (counterX == 3) {
                    System.out.println("X wins");
                    state = false;
                    break;
                } else if (counterO == 3) {
                    System.out.println("O wins");
                    state = false;
                    break;
                }
                temp++;
                counterX = 0;
                counterO = 0;
            }
        }
        //check the left diagonal
        if (state){
            counterX = 0;
            counterO = 0;
            int left = 0;
            for (int i = 0; i < game.length; i++) {
                if (game[i][left].equals("X")) {
                    counterX++;
                }
                else if (game[i][left].equals("O")) {
                    counterO++;
                }
                left++;
            }
            if (counterX == 3) {
                System.out.println("X wins");
                state = false;
            } else if (counterO == 3) {
                System.out.println("O wins");
                state = false;
            }

        }
        //check the right diagonal
        if (state){
            counterX = 0;
            counterO = 0;
            int right = game.length - 1;;
            for (int i = 0; i < game.length; i++) {
                if (game[i][right].equals("X")) {
                    counterX++;
                }
                else if (game[i][right].equals("O")) {
                    counterO++;
                }
                right--;
            }
            if (counterX == 3) {
                System.out.println("X wins");
                state = false;
            } else if (counterO == 3) {
                System.out.println("O wins");
                state = false;
            }

        }
        //check for draw
        //we already checked if someone wins so if the program runs to this point
        //either it is a draw (if there is no empty space)
        //or game not finished case(if here are at least one empty space)
        boolean empty = false;
        if (state) {
            for (int i = 0; i < game.length; i++) {
                for (int j = 0; j < game[i].length; j++) {
                    if (game[i][j].equals(" ")) {
                        //Game not finished
                        empty = true;
                        break;
                    }
                }
                if (empty) {
                    break;
                }
            }
            //finally
            if (empty == false) {
                System.out.println("Draw");
                return false;
            }
        }
        if (state == false) {
            return false;
        } else {
            return true;
        }
    } //this method does not change
    public static void playerVsComputer(String[][] game, String player1, String player2) {
        if (player1.equals("user") && (player2.equals("easy"))|| player2.equals("medium") || player2.equals("hard")) {
            int cnt = 0;
            boolean computer = true;
            while (cnt < 9) {
                userInput(game, 1); //position 1 means that the user will start and thus user will be "X"
                displayResults(game);
                if (cnt >= 1) {
                    if (status(game)) {
                        if (player2.equals("easy")) {
                            easyLevel(game, 2); //position 2 means "O"
                        } else if (player2.equals("medium")) {
                            mediumLevel(game, 2);
                        } else if (player2.equals("hard")) {
                            bestMove(game, 2);
                        }
                        displayResults(game);
                        computer = false;
                        if (!status(game)) {
                            break;
                        }

                    } else {
                        break;
                    }
                }
                if (computer) {
                    if (player2.equals("easy")) {
                        easyLevel(game, 2);
                    } else if (player2.equals("medium")) {
                        mediumLevel(game, 2);
                    } else if (player2.equals("hard")) {
                        bestMove(game, 2);
                    }
                    displayResults(game);
                }
                cnt++;
                computer = true;
            }
        }
    }
    public static void playerVsPlayer(String[][] game, String player1, String player2) {
        if (player1.equals("user") && player2.equals("user")) {
            int cnt = 0;
            boolean secondPlayer = true;
            while (cnt < 9) {
                userInput(game, 1); //position 1 means that the user will start and thus user will be "X"
                displayResults(game);
                if (cnt >= 1) {
                    if (status(game)) {
                        userInput(game, 2); //position 2 means that the second user will be "O"
                        displayResults(game);
                        secondPlayer = false;
                        if (!status(game)) {
                            break;
                        }

                    } else {
                        break;
                    }
                }
                if (secondPlayer) {
                    userInput(game, 2);
                    displayResults(game);
                }
                cnt++;
                secondPlayer = true;
            }
        }
    } //this method does not change
    public static void computerVsPlayer(String[][] game, String player1, String player2) {
        if ((player1.equals("easy") || player1.equals("medium") || player1.equals("hard")) && player2.equals("user")) {
            int cnt = 0;
            boolean secondPlayer = true;
            while (cnt < 9) {
                if (player1.equals("easy")) {
                    easyLevel(game, 1);
                } else if (player1.equals("medium")) {
                    mediumLevel(game, 1);
                } else if (player1.equals("hard")) {
                    bestMove(game, 1);
                }
                displayResults(game);
                if (cnt >= 1) {
                    if (status(game)) {
                        userInput(game, 2);
                        displayResults(game);
                        secondPlayer = false;
                        if (!status(game)) {
                            break;
                        }

                    } else {
                        break;
                    }
                }
                if (secondPlayer) {
                    userInput(game, 2);
                    displayResults(game);
                }
                cnt++;
                secondPlayer = true;
            }
        } /*else if (player1.equals("medium") && player2.equals("user")) {
            int cnt = 0;
            boolean secondPlayer = true;
            while (cnt < 9) {
                mediumLevel(game, 1);
                displayResults(game);
                if (cnt >= 2) {
                    if (status(game)) {
                        userInput(game, 2);
                        displayResults(game);
                        secondPlayer = false;
                        if (!status(game)) {
                            break;
                        }

                    } else {
                        break;
                    }
                }
                if (secondPlayer) {
                    userInput(game, 2);
                    displayResults(game);
                }
                cnt++;
                secondPlayer = true;
            }
        }*/
    }
    public static void computerVsComputer(String[][] game, String player1, String player2) {
        if ((player1.equals("easy") || player1.equals("medium") || player1.equals("hard")) && (player2.equals("easy") || player2.equals("medium") || player2.equals("hard"))) {
            int cnt = 0;
            boolean computer2 = true;
            while (cnt < 9) {
                if (player1.equals("easy")) {
                    easyLevel(game, 1);
                } else if (player1.equals("medium")) {
                    mediumLevel(game, 1);
                } else if (player1.equals("hard")) {
                    bestMove(game, 1);
                }
                displayResults(game);
                if (cnt >= 1) {
                    if (status(game)) {
                        if (player2.equals("easy")) {
                            easyLevel(game, 2);
                        } else if (player2.equals("medium")) {
                            mediumLevel(game, 2);
                        } else if (player2.equals("hard")) {
                            bestMove(game, 2);
                        }
                        displayResults(game);
                        computer2 = false;
                        if (!status(game)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (computer2) {
                    if (player2.equals("easy")) {
                        easyLevel(game, 2);
                    } else if (player2.equals("medium")) {
                        mediumLevel(game, 2);
                    } else if (player2.equals("hard")) {
                        bestMove(game, 2);
                    }
                    displayResults(game);
                }
                cnt++;
                computer2 = true;
            }
        }
    }
    public static void mediumLevel(String[][] game, int position) {
        System.out.println("Making move level \"medium\"");
        Random random = new Random();
        boolean nextTurn = false;
        while (!nextTurn) {
            if (position == 1) {
                if (checkTwoInHorizontal(game, 1)) {
                    nextTurn = true;
                } else if (checkTwoInVertical(game, 1)) {
                    nextTurn = true;
                } else if (checkTwoInDiagonal(game, 1)) {
                    nextTurn = true;
                }else if (preventTwoInHorizontal(game, 1)) {
                    nextTurn = true;
                } else if (preventTwoInVertical(game, 1)) {
                    nextTurn = true;
                } else if (preventTwoInDiagonal(game, 1)) {
                    nextTurn = true;
                } else {
                    boolean next = false;
                    while(!next) {
                        int row = random.nextInt(3);
                        int column = random.nextInt(3);
                        if (game[row][column].equals(" ")) {
                            game[row][column] = "X";
                            nextTurn = true;
                            next = true;
                        }
                    }
                }
            } else if (position == 2) {
                if (checkTwoInHorizontal(game, 2)) {
                    nextTurn = true;
                } else if (checkTwoInVertical(game, 2)) {
                    nextTurn = true;
                } else if (checkTwoInDiagonal(game, 2)) {
                    nextTurn = true;
                }else if (preventTwoInHorizontal(game, 2)) {
                    nextTurn = true;
                } else if (preventTwoInVertical(game, 2)) {
                    nextTurn = true;
                } else if (preventTwoInDiagonal(game, 2)) {
                    nextTurn = true;
                } else {
                    boolean next = false;
                    while(!next) {
                        int row = random.nextInt(3);
                        int column = random.nextInt(3);
                        if (game[row][column].equals(" ")) {
                            game[row][column] = "O";
                            nextTurn = true;
                            next = true;
                        }
                    }
                }
            }
        }
    }
    public static boolean checkTwoInHorizontal(String[][] game, int position) {
        /**
         * this method will check whether the AI can with with one
         * more move in the horizontal direction
         */
        boolean state = false;
        if (position == 1) {
            for (int i = 0; i < 3; i++) {
                if (game[i][0].equals("X") && game[i][1].equals("X") && game[i][2].equals(" ")) {
                    game[i][2] = "X";
                    state = true;
                    return true;
                } else if (game[i][0].equals("X") && game[i][1].equals(" ") && game[i][2].equals("X")) {
                    game[i][1] = "X";
                    state = true;
                    return true;
                } else if (game[i][0].equals(" ") && game[i][1].equals("X") && game[i][2].equals("X")) {
                    game[i][0] = "X";
                    state = true;
                    return true;
                }
            }
        } else if (position == 2) {
            for (int i = 0; i < 3; i++) {
                if (game[i][0].equals("O") && game[i][1].equals("O") && game[i][2].equals(" ")) {
                    game[i][2] = "O";
                    state = true;
                    return true;
                } else if (game[i][0].equals("O") && game[i][1].equals(" ") && game[i][2].equals("O")) {
                    game[i][1] = "O";
                    state = true;
                    return true;
                } else if (game[i][0].equals(" ") && game[i][1].equals("O") && game[i][2].equals("O")) {
                    game[i][0] = "O";
                    state = true;
                    return true;
                }
            }
        }
        return state;
    }
    public static boolean checkTwoInVertical(String[][] game, int position) {
        /**
         * this method will check whether the AI can with with one
         * more move in the vertical direction
         */
        boolean state = false;
        if (position == 1) {
            for (int i = 0; i < 3; i++) {
                if (game[0][i].equals("X") && game[1][i].equals("X") && game[2][i].equals(" ")) {
                    game[2][i] = "X";
                    state = true;
                    return true;
                } else if (game[0][i].equals("X") && game[1][i].equals(" ") && game[2][i].equals("X")) {
                    game[1][i] = "X";
                    state = true;
                    return true;
                } else if (game[0][i].equals(" ") && game[1][i].equals("X") && game[2][i].equals("X")) {
                    game[0][i] = "X";
                    state = true;
                    return true;
                }
            }
        } else if (position == 2) {
            for (int i = 0; i < 3; i++) {
                if (game[0][i].equals("O") && game[1][i].equals("O") && game[2][i].equals(" ")) {
                    game[2][i] = "O";
                    state = true;
                    return true;
                } else if (game[0][i].equals("O") && game[1][i].equals(" ") && game[2][i].equals("O")) {
                    game[1][i] = "O";
                    state = true;
                    return true;
                } else if (game[0][i].equals(" ") && game[1][i].equals("O") && game[2][i].equals("O")) {
                    game[0][i] = "O";
                    state = true;
                    return true;
                }
            }
        }
        return state;
    }
    public static boolean checkTwoInDiagonal(String[][] game, int position) {
        /**
         * this method will check whether the AI can with with one
         * more move in the diagonal direction
         */
        boolean state = false;
        if (position == 1) {
                if (game[0][0].equals("X") && game[1][1].equals("X") && game[2][2].equals(" ")) {
                    game[2][2] = "X";
                    state = true;
                    return true;
                } else if (game[0][0].equals("X") && game[1][1].equals(" ") && game[2][2].equals("X")) {
                    game[1][1] = "X";
                    state = true;
                    return true;
                } else if (game[0][0].equals(" ") && game[1][1].equals("X") && game[2][2].equals("X")) {
                    game[0][0] = "X";
                    state = true;
                    return true;
                } else if (game[0][2].equals("X") && game[1][1].equals("X") && game[2][0].equals(" ")) {
                game[2][0] = "X";
                state = true;
                return true;
            } else if (game[0][2].equals("X") && game[1][1].equals(" ") && game[2][0].equals("X")) {
                game[1][1] = "X";
                state = true;
                return true;
            } else if (game[0][2].equals(" ") && game[1][1].equals("X") && game[2][0].equals("X")) {
                game[0][2] = "X";
                state = true;
                return true;
            }
        } else if (position == 2) {
            if (game[0][0].equals("O") && game[1][1].equals("O") && game[2][2].equals(" ")) {
                game[2][2] = "O";
                state = true;
                return true;
            } else if (game[0][0].equals("O") && game[1][1].equals(" ") && game[2][2].equals("O")) {
                game[1][1] = "O";
                state = true;
                return true;
            } else if (game[0][0].equals(" ") && game[1][1].equals("O") && game[2][2].equals("O")) {
                game[0][0] = "O";
                state = true;
                return true;
            } else if (game[0][2].equals("O") && game[1][1].equals("O") && game[2][0].equals(" ")) {
                game[2][0] = "O";
                state = true;
                return true;
            } else if (game[0][2].equals("O") && game[1][1].equals(" ") && game[2][0].equals("O")) {
                game[1][1] = "O";
                state = true;
                return true;
            } else if (game[0][2].equals(" ") && game[1][1].equals("O") && game[2][0].equals("O")) {
                game[0][2] = "O";
                state = true;
                return true;
            }
        }
        return state;
    }
    public static boolean preventTwoInHorizontal(String[][] game, int position) {
        /**
         * this method will check if the opponent can win in the next move
         * in the horizontal direction and if yes the AI will prevent it
         */
        boolean state = false;
        if (position == 1) {
            for (int i = 0; i < 3; i++) {
                if (game[i][0].equals("O") && game[i][1].equals("O") && game[i][2].equals(" ")) {
                    game[i][2] = "X";
                    state = true;
                    return true;
                } else if (game[i][0].equals("O") && game[i][1].equals(" ") && game[i][2].equals("O")) {
                    game[i][1] = "X";
                    state = true;
                    return true;
                } else if (game[i][0].equals(" ") && game[i][1].equals("O") && game[i][2].equals("O")) {
                    game[i][0] = "X";
                    state = true;
                    return true;
                }
            }
        } else if (position == 2) {
            for (int i = 0; i < 3; i++) {
                if (game[i][0].equals("X") && game[i][1].equals("X") && game[i][2].equals(" ")) {
                    game[i][2] = "O";
                    state = true;
                    return true;
                } else if (game[i][0].equals("X") && game[i][1].equals(" ") && game[i][2].equals("X")) {
                    game[i][1] = "O";
                    state = true;
                    return true;
                } else if (game[i][0].equals(" ") && game[i][1].equals("X") && game[i][2].equals("X")) {
                    game[i][0] = "O";
                    state = true;
                    return true;
                }
            }
        }
        return state;
    }
    public static boolean preventTwoInVertical(String[][] game, int position) {
        /**
         * this method will check if the opponent can win in the next move
         * in the vertical direction and if yes the AI will prevent it
         */
        boolean state = false;
        if (position == 1) {
            for (int i = 0; i < 3; i++) {
                if (game[0][i].equals("O") && game[1][i].equals("O") && game[2][i].equals(" ")) {
                    game[2][i] = "X";
                    state = true;
                    return true;
                } else if (game[0][i].equals("O") && game[1][i].equals(" ") && game[2][i].equals("O")) {
                    game[1][i] = "X";
                    state = true;
                    return true;
                } else if (game[0][i].equals(" ") && game[1][i].equals("O") && game[2][i].equals("O")) {
                    game[0][i] = "X";
                    state = true;
                    return true;
                }
            }
        } else if (position == 2) {
            for (int i = 0; i < 3; i++) {
                if (game[0][i].equals("X") && game[1][i].equals("X") && game[2][i].equals(" ")) {
                    game[2][i] = "O";
                    state = true;
                    return true;
                } else if (game[0][i].equals("X") && game[1][i].equals(" ") && game[2][i].equals("X")) {
                    game[1][i] = "O";
                    state = true;
                    return true;
                } else if (game[0][i].equals(" ") && game[1][i].equals("X") && game[2][i].equals("X")) {
                    game[0][i] = "O";
                    state = true;
                    return true;
                }
            }
        }
        return state;
    }
    public static boolean preventTwoInDiagonal(String[][] game, int position) {
        /**
         * this method will check if the opponent can win in the next move
         * in the diagonal direction and if yes the AI will prevent it
         */
        boolean state = false;
        if (position == 1) {
            if (game[0][0].equals("O") && game[1][1].equals("O") && game[2][2].equals(" ")) {
                game[2][2] = "X";
                state = true;
                return true;
            } else if (game[0][0].equals("O") && game[1][1].equals(" ") && game[2][2].equals("O")) {
                game[1][1] = "X";
                state = true;
                return true;
            } else if (game[0][0].equals(" ") && game[1][1].equals("O") && game[2][2].equals("O")) {
                game[0][0] = "X";
                state = true;
                return true;
            } else if (game[0][2].equals("O") && game[1][1].equals("O") && game[2][0].equals(" ")) {
                game[2][0] = "X";
                state = true;
                return true;
            } else if (game[0][2].equals("O") && game[1][1].equals(" ") && game[2][0].equals("O")) {
                game[1][1] = "X";
                state = true;
                return true;
            } else if (game[0][2].equals(" ") && game[1][1].equals("O") && game[2][0].equals("O")) {
                game[0][2] = "X";
                state = true;
                return true;
            }
        } else if (position == 2) {
            if (game[0][0].equals("X") && game[1][1].equals("X") && game[2][2].equals(" ")) {
                game[2][2] = "O";
                state = true;
                return true;
            } else if (game[0][0].equals("X") && game[1][1].equals(" ") && game[2][2].equals("X")) {
                game[1][1] = "O";
                state = true;
                return true;
            } else if (game[0][0].equals(" ") && game[1][1].equals("X") && game[2][2].equals("X")) {
                game[0][0] = "O";
                state = true;
                return true;
            } else if (game[0][2].equals("X") && game[1][1].equals("X") && game[2][0].equals(" ")) {
                game[2][0] = "O";
                state = true;
                return true;
            } else if (game[0][2].equals("X") && game[1][1].equals(" ") && game[2][0].equals("X")) {
                game[1][1] = "O";
                state = true;
                return true;
            } else if (game[0][2].equals(" ") && game[1][1].equals("X") && game[2][0].equals("X")) {
                game[0][2] = "O";
                state = true;
                return true;
            }
        }
        return state;
    }//below is new code
    public static boolean equals3(String a, String b, String c) {
        return a.equals(b) && b.equals(c) && !a.equals(" ");
    }
    public static boolean available(String[][] game) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game[i][j].equals(" ")) {
                    return true;
                }
            }
        }
        return false;
    }
    public static String checkWinner(String[][] game) {
        /*
        player1 = X
        player2 = O
        the function will return either "X" or "O" or "draw" or null if the game is yet to be continued
        */
        String winner = null;
        // horizontal
        for (int i = 0; i < 3; i++) {
            if (equals3(game[i][0], game[i][1], game[i][2])) {
                winner = game[i][0];
                return winner;
            }
        }

        // Vertical
        for (int i = 0; i < 3; i++) {
            if (equals3(game[0][i], game[1][i], game[2][i])) {
                winner = game[0][i];
                return winner;
            }
        }

        // Left Diagonal
        if (equals3(game[0][0], game[1][1], game[2][2])) {
            winner = game[0][0];
            return winner;
        }
        //Right Diagonal
        if (equals3(game[0][2], game[1][1], game[2][0])) {
            winner = game[0][2];
            return winner;
        }

        if (winner == null && !available(game)) {
            return "Draw";
        } else {
            return winner;
        }
    }
    public static void bestMove(String[][] game, int position) {
        System.out.println("Making move level \"hard\"");
        int[] move = new int[2];
        if (position == 1) { // since position = 1 then this player is maximizer
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (game[i][j].equals(" ")) {
                        game[i][j] = "X";
                        int score = minimax(game, 0, false);
                        game[i][j] = " ";
                        if (score > bestScore) {
                            bestScore = score;
                            move[0] = i;
                            move[1] = j;
                        }
                    }
                }
            }
            game[move[0]][move[1]] = "X";
        } else if (position == 2) { // since position = 2 then this player is minimizer
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (game[i][j].equals(" ")) {
                        game[i][j] = "O";
                        int score = minimax(game, 0, true);
                        game[i][j] = " ";
                        if (score < bestScore) {
                            bestScore = score;
                            move[0] = i;
                            move[1] = j;
                        }
                    }
                }
            }
            game[move[0]][move[1]] = "O";
        }
    }
    public static int minimax(String[][] game, int depth, boolean isMaximizing) {
        String result = checkWinner(game);
        if (result != null) {
            if (result.equals("X")) {
                return 1;
            } else if (result.equals("O")) {
                return -1;
            } else if (result.equals("Draw")) {
                return 0;
            }
        }
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Is the spot available?
                    if (game[i][j].equals(" ")) {
                        game[i][j] = "X";
                        bestScore = Math.max(bestScore, minimax(game, depth + 1, false));
                        game[i][j] = " "; //to undo the move we just did
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (game[i][j].equals(" ")) {
                        game[i][j] = "O";
                        bestScore = Math.min(bestScore, minimax(game, depth + 1, true));
                        game[i][j] = " ";
                    }
                }
            }
            return bestScore;
        }
    }
}

