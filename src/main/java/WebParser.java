import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.concurrent.ConcurrentSkipListSet;


public class WebParser {
    private final String url;
    private ConcurrentSkipListSet<String> linkList;

    public WebParser(String url) {
        this.url = url;
    }

    public ConcurrentSkipListSet<String> parse() {
        Document doc;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Thread.sleep((long)(Math.random() * 1000));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String regex = url + "(.*)";
        Elements links = doc.select("a[href]");
        Element link;
        HashSet<String> linkSet = new HashSet<>();
        for (int i = 0; i < links.size(); i++) {
            link = links.get(i);
            String link1 = link.attr("abs:href");
            if (link1.matches(regex) && link1.charAt(link1.length() - 1) != '#') {
                linkSet.add(link.attr("abs:href"));
            }
        }
        linkList = new ConcurrentSkipListSet<>(linkSet);
        return linkList;
    }

}
