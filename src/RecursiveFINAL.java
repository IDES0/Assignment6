/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, <NAME1> and <NAME2),
 *  this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID:
 *  email address:
 *  Grader name:
 *  Section number:
 *
 *  Student 2
 *  UTEID:
 *  email address:
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Various recursive methods to be implemented.
 * Given shell file for CS314 assignment.
 */
public class RecursiveFINAL {

    /**
     * Problem 1: convert a base 10 int to binary recursively.
     *   <br>pre: n != Integer.MIN_VALUE<br>
     *   <br>post: Returns a String that represents N in binary.
     *   All chars in returned String are '1's or '0's.
     *   Most significant digit is at position 0
     *   @param n the base 10 int to convert to base 2
     *   @return a String that is a binary representation of the parameter n
     */
    public static String getBinary(int n) {
        if (n == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "getBinary. n cannot equal "
                    + "Integer.MIN_VALUE. n: " + n);
        }
        int r = n % 2;
        n = n / 2;
        if (n == 0) {
            return r + "";
        }
        return getBinary(n) + Math.abs(r);
    }

    /**
     * Problem 2: reverse a String recursively.<br>
     *   pre: stringToRev != null<br>
     *   post: returns a String that is the reverse of stringToRev
     *   @param stringToRev the String to reverse.
     *   @return a String with the characters in stringToRev
     *   in reverse order.
     */
    public static String revString(String stringToRev) {
        if (stringToRev == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "revString. parameter may not be null.");
        }
        return (stringToRev.length() == 1) ?
                stringToRev : revString(stringToRev.substring(1)) + stringToRev.charAt(0);
    }

    /**
     * Problem 3: Returns the number of elements in data
     * that are followed directly by value that is
     * double that element.
     * pre: data != null
     * post: return the number of elements in data
     * that are followed immediately by double the value
     * @param data The array to search.
     * @return The number of elements in data that
     * are followed immediately by a value that is double the element.
     */
    public static int nextIsDouble(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "data. parameter may not be null.");
        }
        // TODO helper method; ask Pavan for what he meant
        return (data.length == 1) ? 0 : isDouble(data[0], data[1]) +
                nextIsDouble(Arrays.copyOfRange(data, 1, data.length));
    }
    private static int isDouble(int num1, int num2) {
        return (num1 * 2 == num2) ? 1 : 0;
    }

    /**  Problem 4: Find all combinations of mnemonics
     * for the given number.<br>
     *   pre: number != null, number.length() > 0,
     *   all characters in number are digits<br>
     *   post: see tips section of assignment handout
     *   @param number The number to find mnemonics for
     *   @return An ArrayList<String> with all possible mnemonics
     *   for the given number
     */
    public static ArrayList<String> listMnemonics(String number) {
        if (number == null ||  number.length() == 0 || !allDigits(number)) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "listMnemonics");
        }
        ArrayList<String> results = new ArrayList<>(); // to store the mnemonics
        recursiveMnemonics(results, "", number);
        return results;
    }

    /*
     * Helper method for listMnemonics
     * mnemonics stores completed mnemonics
     * mneominicSoFar is a partial (possibly complete) mnemonic
     * digitsLeft are the digits that have not been used
     * from the original number.
     */
    private static void recursiveMnemonics(ArrayList<String> mnemonics,
                                           String mnemonicSoFar, String digitsLeft) {
        if (digitsLeft.length() == 0) {
            mnemonics.add(mnemonicSoFar);
        } else {
            String words = digitLetters(digitsLeft.charAt(0));
            for (int i = 0; i < words.length(); i++) {
                mnemonicSoFar += words.charAt(i);
                recursiveMnemonics(mnemonics, mnemonicSoFar, digitsLeft.substring(1));
                mnemonicSoFar = mnemonicSoFar.substring(0, mnemonicSoFar.length() - 1); // TODO like this
            }
        }

    }

    /* Static code blocks are run once when this class is loaded.
     * Here we create an unmoddifiable list to use with the phone
     * mnemonics method.
     */
    private static final List<String> LETTERS_FOR_NUMBER; static {
        String[] letters = {"0", "1", "ABC",
                "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
        ArrayList<String> lettersAsList = new ArrayList<>();
        for (String s : letters) {
            lettersAsList.add(s);
        }
        LETTERS_FOR_NUMBER = Collections.unmodifiableList(lettersAsList);
    }

    /* helper method for recursiveMnemonics
     * pre: ch is a digit '0' through '9'
     * post: return the characters associated with
     * this digit on a phone keypad
     */
    private static String digitLetters(char ch) {
        if (ch < '0' || ch > '9') {
            throw new IllegalArgumentException("parameter "
                    + "ch must be a digit, 0 to 9. Given value = " + ch);
        }
        int index = ch - '0';
        return LETTERS_FOR_NUMBER.get(index);
    }

    /* helper method for listMnemonics
     * pre: s != null
     * post: return true if every character in s is
     * a digit ('0' through '9')
     */
    private static boolean allDigits(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "allDigits. String s cannot be null.");
        }
        boolean allDigits = true;
        int i = 0;
        while (i < s.length() && allDigits) {
            allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
            i++;
        }
        return allDigits;
    }

    /**
     * Problem 5: Draw a Sierpinski Carpet.
     * @param size the size in pixels of the window
     * @param limit the smallest size of a square in the carpet.
     */
    public static void drawCarpet(int size, int limit) {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,size,size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);
    }

    /* Helper method for drawCarpet
     * Draw the individual squares of the carpet.
     * @param g The Graphics object to use to fill rectangles
     * @param size the size of the current square
     * @param limit the smallest allowable size of squares
     * @param x the x coordinate of the upper left corner of the current square
     * @param y the y coordinate of the upper left corner of the current square
     */
    private static void drawSquares(Graphics g, int size, int limit, double x, double y) {
        // TODO check with Pavan if everything matches
        // TODO get rid of MNs
        if (size >= limit) {
            int sqLength = size / 3;
            for (int i = 1; i <= 9; i++) {
                if (i == 5) {
                    g.fillRect((int) x, (int) y, sqLength, sqLength);
                    x += sqLength;
                } else {
                    drawSquares(g, sqLength, limit, x, y);
                    x += sqLength;
                }
                if (i % 3 == 0) {
                    x -= (sqLength * 3);
                    y += sqLength;
                }
            }
        }
    }

    /**
     * Problem 6: Determine if water at a given point
     * on a map can flow off the map.
     * <br>pre: map != null, map.length > 0,
     * map is a rectangular matrix, 0 <= row < map.length,
     * 0 <= col < map[0].length
     * <br>post: return true if a drop of water starting at the location
     * specified by row, column can reach the edge of the map,
     * false otherwise.
     * @param map The elevations of a section of a map.
     * @param row The starting row of a drop of water.
     * @param col The starting column of a drop of water.
     * @return return true if a drop of water starting at the location
     * specified by row, column can reach the edge of the map, false otherwise.
     */
    public static boolean canFlowOffMap(int[][] map, int row, int col) {
        if (map == null || map.length == 0 || !isRectangular(map)
                || !inbounds(row, col, map)) {
            throw new IllegalArgumentException("Failed precondition: " + "canFlowOffMap");
        }
        if (row == 0 || col == 0 || row == map.length - 1 || col == map[0].length - 1) {
            return true;
        } else {
            int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Down, Up, Right, Left
            for (int[] move : moves) {
                if (map[row][col] > map[row + move[0]][col + move[1]] &&
                        canFlowOffMap(map, row + move[0], col + move[1])) {
                    return true;
                }
            }
        }
        return false;
    }

    /* helper method for canFlowOfMap - CS314 students you should not have to
     * call this method,
     * pre: mat != null,
     */
    private static boolean inbounds(int r, int c, int[][] mat) {
        if (mat == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "inbounds. The 2d array mat may not be null.");
        }
        return r >= 0 && r < mat.length && mat[r] != null
                && c >= 0 && c < mat[r].length;
    }

    /*
     * helper method for canFlowOfMap - CS314 students you should not have to
     * call this method,
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     */
    private static boolean isRectangular(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "inbounds. The 2d array mat may not be null "
                    + "and must have at least 1 row.");
        }
        boolean correct = true;
        final int numCols = mat[0].length;
        int row = 0;
        while (correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == numCols);
            row++;
        }
        return correct;
    }

    /**
     * Problem 7: Find the minimum difference possible between teams
     * based on ability scores. The number of teams may be greater than 2.
     * The goal is to minimize the difference between the team with the
     * maximum total ability and the team with the minimum total ability.
     * <br> pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * <br> post: return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability.
     * @param numTeams the number of teams to form.
     * Every team must have at least one member
     * @param abilities the ability scores of the people to distribute
     * @return return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability. The return value will be greater than or equal to 0.
     */
    public static int minDifference(int numTeams, int[] abilities) {
        if (numTeams < 2 || abilities == null || abilities.length < numTeams) {
            throw new IllegalArgumentException("Failed precondition: " + "minDifference");
        }
        return getMinDifference(numTeams, abilities, 0, new int[2][numTeams],
                Integer.MAX_VALUE);
    }

    private static int getMinDifference(int numTeams, int[] abilities, int index,
                                        int[][] lineUp, int best) {
        if (index == abilities.length) {
            if (checkIfInvalid(lineUp)) {
                return Integer.MAX_VALUE;
            } else {
                return calculateMaxMinDelta(lineUp);
            }
        }
        for (int i = 0; i < numTeams; i++) {
            lineUp[0][i] += abilities[index];
            lineUp[1][i]++;
            int minDiffOfNext = getMinDifference(numTeams, abilities, index + 1, lineUp, best);
            if (minDiffOfNext < best) {
                best = minDiffOfNext;
            }
            lineUp[0][i] -= abilities[index];
            lineUp[1][i]--;
        }
        return best;
    }

    private static int calculateMaxMinDelta(int[][] lineUp) {
        // TODO okay since teams are >= 2
        // Wack, TA check
        int max = lineUp[0][0];
        int min = lineUp[0][0];
        for (int i = 1; i < lineUp[0].length; i++) {
            if (lineUp[0][i] > max) {
                max = lineUp[0][i];
            } else if (lineUp[0][i] < min) {
                min = lineUp[0][i];
            }
        }
        return max - min;
    }

    private static boolean checkIfInvalid(int[][] lineUp) {
        for (int[] ints : lineUp) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Problem 8: Maze solver.
     * <br>pre: board != null
     * <br>pre: board is a rectangular matrix
     * <br>pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*'
     * <br>pre: there is a single 'S' character present
     * <br>post: rawMaze is not altered as a result of this method.
     * Return 2 if it is possible to escape the maze after
     * collecting all the coins.
     * Return 1 if it is possible to escape the maze
     * but without collecting all the coins.
     * Return 0 if it is not possible
     * to escape the maze. More details in the assignment handout.
     * @param rawMaze represents the maze we want to escape.
     * rawMaze is not altered as a result of this method.
     * @return per the post condition
     */
    public static int canEscapeMaze(char[][] rawMaze) {
        int numRows = rawMaze.length;
        int numCols = rawMaze[0].length;
        int startRow = -1;
        int startCol = -1;
        int numCoins = 0;

        boolean[][] visited = new boolean[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (rawMaze[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                } else if (rawMaze[i][j] == '$') {
                    numCoins++;
                }
            }
        }

        return explore(rawMaze, visited, numCoins, startRow, startCol);
    }

    private int explore(char[][] maze, boolean[][] visited, int numCoins, int row, int col,
                        int numRows, int numCols) {

        visited[row][col] = true;

        if (maze[row][col] == '$') {
            numCoins--;
        }

        if (maze[row][col] == 'E' && numCoins == 0) {
            // Found an exit cell with all coins collected
            return 2;
        }






    }
}