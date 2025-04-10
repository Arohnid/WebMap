import java.util.concurrent.CopyOnWriteArrayList;

public class LinkTree {
    private String url;
    private CopyOnWriteArrayList<LinkTree> treeChildren;

    public LinkTree(String url) {
        this.url = url;
        treeChildren = new CopyOnWriteArrayList<>();
    }

    public void add(LinkTree child) {
        treeChildren.add(child);
    }

    public CopyOnWriteArrayList<LinkTree> getTreeChildren() {
        return treeChildren;
    }

    public String getUrl() {
        return url;
    }
}
