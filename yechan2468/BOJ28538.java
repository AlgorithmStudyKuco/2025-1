import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ28538 {
    static int n;
    static Point[] originalPoints;
    static Point[] points;

    public static void main(String[] args) throws IOException {
        readInput();

        points = Arrays.copyOf(originalPoints, n);

        Node kdTree = buildKDTree(0, n - 1, 0);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            FindResult result = new FindResult(-1, Integer.MAX_VALUE);
            kdTree.findClosest(originalPoints[i], result);
            builder.append(result.id).append(" ");
        }

        System.out.println(builder);
    }

    private static Node buildKDTree(int start, int end, int depth) {
        if (start > end) return null;

        Comparator<Point> comparator = isTargetAxisX(depth) ?
                Comparator.comparingInt(p -> p.x) :
                Comparator.comparingInt(p -> p.y);

        Arrays.sort(points, start, end + 1, comparator);

        int mid = start + (end - start) / 2;

        Node node = new Node(points[mid], depth);

        node.left = buildKDTree(start, mid - 1, depth + 1);
        node.right = buildKDTree(mid + 1, end, depth + 1);

        return node;
    }

    private static class Node {
        Point curr;
        Node left = null, right = null;
        int depth;

        public Node(Point p, int depth) {
            this.curr = p;
            this.depth = depth;
        }

        private void findClosest(Point query, FindResult best) {
            if (curr.id != query.id) {
                int dist = getDistance(curr, query);
                if (dist < best.minDistance) {
                    best.minDistance = dist;
                    best.id = curr.id;
                }
            }

            Node nearChild = isTargetChildLeft(query) ? left : right;
            Node farChild = (nearChild == left) ? right : left;

            if (nearChild != null) nearChild.findClosest(query, best);

            long axisDist = isTargetAxisX(depth)
                    ? Math.abs(query.x - curr.x)
                    : Math.abs(query.y - curr.y);

            if (farChild != null && axisDist < best.minDistance) {
                farChild.findClosest(query, best);
            }
        }

        private boolean isTargetChildLeft(Point p) {
            if (isTargetAxisX(depth)) {
                return p.x <= curr.x;
            } else {
                return p.y <= curr.y;
            }
        }
    }

    private static boolean isTargetAxisX(int depth) {
        return depth % 2 == 0;
    }

    private static int getDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    private static class Point {
        public int id, x, y;

        public Point(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    private static class FindResult {
        int id;
        int minDistance;

        public FindResult(int id, int minDistance) {
            this.id = id;
            this.minDistance = minDistance;
        }
    }

    private static void readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        originalPoints = new Point[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            originalPoints[i] = new Point(i + 1, x, y);
        }
    }
}