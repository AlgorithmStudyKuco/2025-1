import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ28538 {
    public static void main(String[] args) throws IOException {
        Point[] points = readInput();

        KDTree kdTree = new KDTree(points);

        StringBuilder builder = new StringBuilder();
        for (Point point : points) {
            int id = kdTree.findClosest(point);
            builder.append(id).append(" ");
        }

        System.out.println(builder);
    }

    private static class KDTree {
        Point[] points;
        private final Node head;
        private Point query;
        private FindResult best;

        public KDTree(Point[] points) {
            this.points = Arrays.copyOf(points, points.length);
            this.head = build(0, points.length - 1, 0);
        }

        public Node build(int start, int end, int depth) {
            if (start > end) return null;

            Comparator<Point> comparator = BOJ28538.isAxisX(depth) ?
                    Comparator.comparingInt(p -> p.x) :
                    Comparator.comparingInt(p -> p.y);

            Arrays.sort(points, start, end + 1, comparator);

            int mid = start + (end - start) / 2;

            Node node = new Node(points[mid], depth);

            node.left = build(start, mid - 1, depth + 1);
            node.right = build(mid + 1, end, depth + 1);

            return node;
        }

        public int findClosest(Point query) {
            this.query = query;
            this.best = new FindResult(-1, Integer.MAX_VALUE);

            findClosest(head);

            return best.id;
        }

        private void findClosest(Node curr) {
            if (curr.point.id != query.id) {
                updateBestUsingCurrent(curr);
            }

            Node nearChild = getTargetChild(curr);
            if (nearChild != null) {
                findClosest(nearChild);
            }

            Node farChild = (nearChild == curr.left) ? curr.right : curr.left;
            boolean isAbleToPrune = (farChild == null || getAxisDistance(curr) >= best.minDistance);
            if (!isAbleToPrune) {
                findClosest(farChild);
            }
        }

        private void updateBestUsingCurrent(Node curr) {
            int dist = getDistance(curr.point, query);
            if (dist < best.minDistance) {
                best.minDistance = dist;
                best.id = curr.point.id;
            }
        }

        private Node getTargetChild(Node curr) {
            if (isAxisX(curr)) {
                return query.x <= curr.point.x ? curr.left : curr.right;
            } else {
                return query.y <= curr.point.y ? curr.left : curr.right;
            }
        }

        private int getAxisDistance(Node curr) {
            return isAxisX(curr)
                    ? Math.abs(query.x - curr.point.x)
                    : Math.abs(query.y - curr.point.y);
        }

        private boolean isAxisX(Node curr) {
            return curr.depth % 2 == 0;
        }
    }

    private static boolean isAxisX(int depth) {
        return depth % 2 == 0;
    }

    private static int getDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    private static class Node {
        Point point;
        Node left = null, right = null;
        int depth;

        public Node(Point p, int depth) {
            this.point = p;
            this.depth = depth;
        }
    }

    private static class Point {
        public final int id, x, y;

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

    private static Point[] readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            points[i] = new Point(i + 1, x, y);
        }

        return points;
    }
}
