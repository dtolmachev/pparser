package ru.tolmachev;


import com.sun.istack.internal.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by sunlight on 01.09.14.
 */
public class PoemParser {

    public PoemInfo parse(final String url) throws IOException {
        final Document doc = Jsoup.connect(url).get();
        final String poemName = getPoemName(doc);
        final String text = getText(doc);
        final String author = getAuthor(doc);
        final int year = getYear(doc);
        ////final long ll = likes(doc);
        return new PoemInfo(text, poemName, author, year, 0L);
    }

    public String getPoemName(final Document doc) {
        return getContent("div > h3", doc);
    }

    public String getText(final Document doc) {
        return getContent("div > pre", doc);
    }

    public String getAuthor(final Document doc) {
        return getContent("span > a", doc);
    }

    public int getYear(final Document doc) {
        String yearAsStr = getContent("pre > i", doc);
        if(yearAsStr == null) {
            return -1;
        }
        return Integer.valueOf(yearAsStr);
    }

    public long likes(final Document doc) {
        final Elements elems = doc.select("div > iframe");
        final Element parent = elems.first();
        final Elements elem = parent.getElementsByAttribute("src");
        return 0;
    }

    @Nullable
    private String getContent(final String relPath, final Document doc) {
        final Elements elems = doc.select(relPath);
        final Element parent = elems.first();
        if(parent == null) {
            return null;
        }

        final Node n = parent.childNode(0);
        return n.toString();
    }

    public static void main(String[] args) throws Exception {
        final PoemParser pp = new PoemParser();
        final PoemInfo pi = pp.parse("http://slova.org.ru/severianin/barbarisovaya_poeza/");
        System.out.println(pi.toString());
    }
}
