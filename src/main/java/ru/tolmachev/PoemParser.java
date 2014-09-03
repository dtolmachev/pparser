package ru.tolmachev;


import com.sun.istack.internal.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

/**
 * Created by sunlight on 01.09.14.
 */
public class PoemParser {

    private static final int APP = 2159066;

    public PoemInfo parse(final String url) throws IOException {
        final Document doc = Jsoup.connect(url).get();
        final String poemName = getPoemName(doc);
        final String text = getText(doc);
        final String author = getAuthor(doc);
        final int year = getYear(doc);
        final long[] likes = likes(url);
        return new PoemInfo(text, poemName, author, year, likes);
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

    public long[] likes(final String url) throws IOException{
        final String vkApiUrl = "https://api.vk.com/method/likes.getList?type=sitepage&owner_id=1&item_id=1&page_url=" + url +"&filter=likes&offset=1&count=10000";
        final Element body = Jsoup.connect(vkApiUrl).ignoreContentType(true).get().body();
        final String json = body.text();
        final JSONObject obj = new JSONObject(json);
        final JSONObject resp = obj.getJSONObject("response");
        final int amount = resp.getInt("count");
        final long[] ids = new long[amount];

        final JSONArray arr = resp.getJSONArray("users");
        for (int i = 0; i < arr.length(); i++)
        {
            ids[i] = arr.getLong(i);
        }
        return ids;
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
