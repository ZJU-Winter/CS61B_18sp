package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.LinkedList;
import java.util.List;

public class Solver {
    private int totalMoves;
    private SearchNode answer;

    private class SearchNode implements Comparable<SearchNode> {
        WorldState worldState;
        int moves;
        int priority;
        SearchNode prev;

        SearchNode(WorldState worldstate) {
            this.worldState = worldstate;
            this.moves = 0;
            this.prev = null;
            this.priority = worldstate.estimatedDistanceToGoal() + this.moves;
        }

        SearchNode(WorldState worldstate, int moves, SearchNode prev) {
            this.worldState = worldstate;
            this.moves = moves;
            this.prev = prev;
            this.priority = worldstate.estimatedDistanceToGoal() + this.moves;
        }

        @Override
        public int compareTo(SearchNode other) {
            return this.priority > other.priority ? 1 : -1;
        }
    }

    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>();
        pq.insert(new SearchNode(initial));
        while (true) {
            SearchNode cur = pq.delMin();
            if (cur.worldState.isGoal()) {
                totalMoves = cur.moves;
                answer = cur;
                break;
            }
            for (WorldState neighborState : cur.worldState.neighbors()) {
                if (cur.prev == null) {
                    SearchNode neighborNode = new SearchNode(neighborState, cur.moves + 1, cur);
                    pq.insert(neighborNode);
                    //System.out.println("WORD:" + neighborNode.worldState
                    // +
                    // " VALUE:" + neighborNode.priority);
                } else if (!neighborState.equals(cur.prev.worldState)) {
                    SearchNode neighborNode = new SearchNode(neighborState, cur.moves + 1, cur);
                    pq.insert(neighborNode);
                    //System.out.println("WORD:" + neighborNode.worldState
                    // +
                    // " VALUE:" +neighborNode.priority);
                }
            }
        }
    }

    public int moves() {
        return totalMoves;
    }

    public Iterable<WorldState> solution() {
        List<WorldState> solution = new LinkedList<>();
        SearchNode ptr = answer;
        while (ptr != null) {
            solution.add(0, ptr.worldState);
            ptr = ptr.prev;
        }
        return solution;
    }
}
