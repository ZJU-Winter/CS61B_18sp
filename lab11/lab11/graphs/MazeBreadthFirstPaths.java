package lab11.graphs;

import java.util.PriorityQueue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int source;
    private int target;
    private boolean targetFound = false;
    private Maze maze;
    private PriorityQueue<Entry> pq;

    private class Entry implements Comparable<Entry> {
        Integer index;
        Integer distance;

        Entry(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Entry other) {
            return this.distance.compareTo(other.distance);
        }
    }

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        this.maze = m;
        pq = new PriorityQueue<>();
        source = maze.xyTo1D(sourceX, sourceY);
        target = maze.xyTo1D(targetX, targetY);
        distTo[source] = 0;
        edgeTo[source] = source;
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs() {
        pq.add(new Entry(source, distTo[source]));
        marked[source] = true;
        announce();
        if (target == source) {
            targetFound = true;
        }
        if (targetFound) {
            return;
        }
        while (!pq.isEmpty() && !targetFound) {
            Entry v = pq.poll();
            for (int w : maze.adj(v.index)) {
                if (marked[w]) {
                    continue;
                }
                relax(v.index, w);
                pq.add(new Entry(w, distTo[w]));
            }
        }
    }

    private void relax(int v, int w) {
        if (w == target) {
            targetFound = true;
        }
        marked[w] = true;
        edgeTo[w] = v;
        distTo[w] = distTo[v] + 1;
        announce();
    }

    @Override
    public void solve() {
        bfs();
    }
}

