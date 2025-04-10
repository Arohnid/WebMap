import java.io.FileOutputStream;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "https://www.youtube.com/";
        LinkTree linkTree = new LinkTree(url);
        String path = "WebMap/data/WebMap.txt";
        new ForkJoinPool().invoke(new LinkTreeRecursive(linkTree));

        FileOutputStream stream = new FileOutputStream(path);
        String mapFile = createMap(linkTree, 0);
        stream.write(mapFile.getBytes());
        stream.flush();
        stream.close();
    }

    private static String createMap(LinkTree tree, int tabs) {
        String tab = String.join("", Collections.nCopies(tabs, "\t"));
        StringBuilder result = new StringBuilder(tab + tree.getUrl());
        tree.getTreeChildren().forEach(e -> result.append("\n").append(createMap(e, tabs + 1)));
        return result.toString();
    }
}
