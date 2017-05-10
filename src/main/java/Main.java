import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(Parser.getMostFrequentlyWords("https://thinkprogress.org/rural-tennesseans-internet-government-5853341d3c0c"));
    }
}
