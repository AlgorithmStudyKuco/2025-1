import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ13460 {
    enum Cell {
        RED, BLUE, WALL, BLANK, HOLE
    }
    enum TiltResult {
        RED, BLUE, NONE
    }
    static int numRow, numColumn;
    static int answer = Integer.MAX_VALUE;
    static Map<String, Integer> memo = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        numRow = Integer.parseInt(tokenizer.nextToken());
        numColumn = Integer.parseInt(tokenizer.nextToken());
        Cell[][] board = new Cell[numRow][numColumn];
        for (int i = 0; i < numRow; i++) {
            String line = reader.readLine();
            for (int j = 0; j < line.length(); j++) {
                board[i][j] = parseCell(line.charAt(j));
            }
        }

        for (int dir = 0; dir < 4; dir++) {
            dfs(dir, deepCopyBoard(board), 1);
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }

    private static void dfs(int nextDirection, Cell[][] board, int count) {
        if (count >= 11 || count > answer) return;

        TiltResult result = tilt(nextDirection, board);

        String hash = hash(board);
        if (memo.getOrDefault(hash, Integer.MAX_VALUE) < count) {
            return;
        }
        memo.put(hash, count);

        if (result == TiltResult.RED) {
            answer = Math.min(answer, count);
            return;
        } else if (result == TiltResult.BLUE) {
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            dfs(dir, deepCopyBoard(board), count + 1);
        }
    }

    private static TiltResult tilt(int direction, Cell[][] board) {
        switch (direction) {
            case 0: return tiltUp(board);
            case 1: return tiltLeft(board);
            case 2: return tiltRight(board);
            case 3: return tiltDown(board);
            default: return null;
        }
    }

    private static TiltResult tiltUp(Cell[][] board) {
        TiltResult result = TiltResult.NONE;

        for (int i = numRow - 1; i > 0; i--) {
            for (int j = 0; j < numColumn; j++) {
                if (board[i][j] == Cell.RED && board[i-1][j] == Cell.HOLE && result == TiltResult.NONE) {
                    board[i][j] = Cell.BLANK;
                    result = TiltResult.RED;
                } else if (board[i][j] == Cell.BLUE && board[i-1][j] == Cell.HOLE) {
                    board[i][j] = Cell.BLANK;
                    result = TiltResult.BLUE;
                }
                if (board[i][j] == Cell.RED || board[i][j] == Cell.BLUE) {
                    if (board[i-1][j] == Cell.BLUE || board[i-1][j] == Cell.RED) {
                        if (board[i-2][j] == Cell.BLANK) {
                            board[i-2][j] = board[i-1][j];
                            board[i-1][j] = board[i][j];
                            board[i][j] = Cell.BLANK;
                        } else if (board[i-2][j] == Cell.HOLE) {
                            board[i-2][j] = Cell.BLANK;
                            board[i-1][j] = Cell.BLANK;
                            result = TiltResult.BLUE;
                        }
                    } else if (board[i-1][j] == Cell.BLANK) {
                        board[i-1][j] = board[i][j];
                        board[i][j] = Cell.BLANK;
                    }
                }
            }
        }

        return result;
    }

    private static TiltResult tiltLeft(Cell[][] board) {
        TiltResult result = TiltResult.NONE;

        for (int j = numColumn - 1; j > 0; j--) {
            for (int i = 0; i < numRow; i++) {
                if (board[i][j] == Cell.RED && board[i][j-1] == Cell.HOLE && result == TiltResult.NONE) {
                    board[i][j] = Cell.BLANK;
                    result = TiltResult.RED;
                } else if (board[i][j] == Cell.BLUE && board[i][j-1] == Cell.HOLE) {
                    board[i][j] = Cell.BLANK;
                    result = TiltResult.BLUE;
                }
                if (board[i][j] == Cell.RED || board[i][j] == Cell.BLUE) {
                    if (board[i][j-1] == Cell.BLUE || board[i][j-1] == Cell.RED) {
                        if (board[i][j-2] == Cell.BLANK) {
                            board[i][j-2] = board[i][j-1];
                            board[i][j-1] = board[i][j];
                            board[i][j] = Cell.BLANK;
                        } else if (board[i][j-2] == Cell.HOLE) {
                            board[i][j-2] = Cell.BLANK;
                            board[i][j-1] = Cell.BLANK;
                            result = TiltResult.BLUE;
                        }
                    } else if (board[i][j-1] == Cell.BLANK) {
                        board[i][j-1] = board[i][j];
                        board[i][j] = Cell.BLANK;
                    }
                }
            }
        }

        return result;
    }

    private static TiltResult tiltRight(Cell[][] board) {
        TiltResult result = TiltResult.NONE;

        for (int j = 0; j < numColumn - 1; j++) {
            for (int i = 0; i < numRow; i++) {
                if (board[i][j] == Cell.RED && board[i][j+1] == Cell.HOLE && result == TiltResult.NONE) {
                    board[i][j] = Cell.BLANK;
                    result = TiltResult.RED;
                } else if (board[i][j] == Cell.BLUE && board[i][j+1] == Cell.HOLE) {
                    board[i][j] = Cell.BLANK;
                    result = TiltResult.BLUE;
                }
                if (board[i][j] == Cell.RED || board[i][j] == Cell.BLUE) {
                    if (board[i][j+1] == Cell.BLUE || board[i][j+1] == Cell.RED) {
                        if (board[i][j+2] == Cell.BLANK) {
                            board[i][j+2] = board[i][j+1];
                            board[i][j+1] = board[i][j];
                            board[i][j] = Cell.BLANK;
                        } else if (board[i][j+2] == Cell.HOLE) {
                            board[i][j+2] = Cell.BLANK;
                            board[i][j+1] = Cell.BLANK;
                            result = TiltResult.BLUE;
                        }
                    } else if (board[i][j+1] == Cell.BLANK) {
                        board[i][j+1] = board[i][j];
                        board[i][j] = Cell.BLANK;
                    }
                }
            }
        }

        return result;
    }

    private static TiltResult tiltDown(Cell[][] board) {
        TiltResult result = TiltResult.NONE;

        for (int i = 0; i < numRow - 1; i++) {
            for (int j = 0; j < numColumn; j++) {
                if (board[i][j] == Cell.RED && board[i+1][j] == Cell.HOLE && result == TiltResult.NONE) {
                    board[i][j] = Cell.BLANK;
                    result = TiltResult.RED;
                } else if (board[i][j] == Cell.BLUE && board[i+1][j] == Cell.HOLE) {
                    board[i][j] = Cell.BLANK;
                    result = TiltResult.BLUE;
                }
                if (board[i][j] == Cell.RED || board[i][j] == Cell.BLUE) {
                    if (board[i+1][j] == Cell.BLUE || board[i+1][j] == Cell.RED) {
                        if (board[i+2][j] == Cell.BLANK) {
                            board[i+2][j] = board[i+1][j];
                            board[i+1][j] = board[i][j];
                            board[i][j] = Cell.BLANK;
                        } else if (board[i+2][j] == Cell.HOLE) {
                            board[i+2][j] = Cell.BLANK;
                            board[i+1][j] = Cell.BLANK;
                            result = TiltResult.BLUE;
                        }
                    } else if (board[i+1][j] == Cell.BLANK) {
                        board[i+1][j] = board[i][j];
                        board[i][j] = Cell.BLANK;
                    }
                }
            }
        }

        return result;
    }

    private static Cell parseCell(char ch) {
        switch (ch) {
            case '.' : return Cell.BLANK;
            case '#' : return Cell.WALL;
            case 'R' : return Cell.RED;
            case 'B' : return Cell.BLUE;
            case 'O' : return Cell.HOLE;
            default: return null;
        }
    }

    private static Cell[][] deepCopyBoard(Cell[][] original) {
        Cell[][] copied = new Cell[numRow][numColumn];
        for (int i = 0; i < numRow; i++) {
            copied[i] = original[i].clone();
        }
        return copied;
    }

    private static String hash(Cell[][] board) {
        int redRow = 0, redCol = 0, blueRow = 0, blueCol = 0;
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numColumn; j++) {
                if (board[i][j] == Cell.RED) {
                    redRow = i; redCol = j;
                } else if (board[i][j] == Cell.BLUE) {
                    blueRow = i; blueCol = j;
                }
            }
        }

        return "" + redRow + redCol + blueRow + blueCol;
    }
}
