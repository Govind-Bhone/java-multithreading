package example.multithreading.impl.executorService;

/**
 * Created by govind.bhone on 4/14/2017.
 */

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.HashSet;

public class ExecutorServiceWebCrawler implements LinkHandler {

    private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<String>());
    //    private final Collection<String> visitedLinks = Collections.synchronizedList(new ArrayList<String>());
    private String url;
    private ExecutorService execService;

    public ExecutorServiceWebCrawler(String startingURL, int maxThreads) {
        this.url = startingURL;
        execService = Executors.newFixedThreadPool(maxThreads);
    }

    @Override
    public void queueLink(String link) throws Exception {
        startNewThread(link);
    }

    @Override
    public int size() {
        return visitedLinks.size();
    }

    @Override
    public void addVisited(String s) {
        visitedLinks.add(s);
    }

    @Override
    public boolean visited(String s) {
        return visitedLinks.contains(s);
    }

    private void startNewThread(String link) throws Exception {
        execService.execute(new LinkFinder(link, this));
    }

    private void startCrawling() throws Exception {
        startNewThread(this.url);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        new ExecutorServiceWebCrawler("http://www.google.com", 64).startCrawling();
    }
}
