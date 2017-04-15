package example.multithreading.impl.executorService;

/**
 * Created by govind.bhone on 4/14/2017.
 */
public interface LinkHandler {

    /**
     * Places the link in the queue
     * @param link
     * @throws Exception
     */
    void queueLink(String link) throws Exception;

    /**
     * Returns the number of visited links
     * @return
     */
    int size();

    /**
     * Checks if the link was already visited
     * @param link
     * @return
     */
    boolean visited(String link);

    /**
     * Marks this link as visited
     * @param link
     */
    void addVisited(String link);
}