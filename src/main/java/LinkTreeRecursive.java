import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveAction;

public class LinkTreeRecursive extends RecursiveAction {
    private final LinkTree linkTree;
    private CopyOnWriteArrayList<String> linksList = new CopyOnWriteArrayList<>();

    public LinkTreeRecursive(LinkTree linkTree) {
        this.linkTree = linkTree;
    }

    @Override
    protected void compute() {
        linksList.add(linkTree.getUrl());
        WebParser parser = new WebParser(linkTree.getUrl());
        ConcurrentSkipListSet<String> stringList = parser.parse();
        for (String string : stringList){
            if (!linksList.contains(string)){
                linksList.add(string);
                linkTree.add(new LinkTree(string));
            }
        }
        List<LinkTreeRecursive> taskList = new ArrayList<>();
        for (LinkTree child : linkTree.getTreeChildren()){
            LinkTreeRecursive task = new LinkTreeRecursive(child);
            task.fork();
            taskList.add(task);
            System.out.println(child.getUrl());
        }
        for (LinkTreeRecursive task : taskList){
            task.join();
        }
    }
}
