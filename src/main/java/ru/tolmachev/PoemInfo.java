package ru.tolmachev;

/**
 * Created by sunlight on 01.09.14.
 */
public class PoemInfo {

    private String text;
    private String name;
    private String author;
    private int year;
    private long likesAmount;


    public PoemInfo(String text, String name, String author, int year, long likesAmount) {
        this.text = text;
        this.name = name;
        this.author = author;
        this.year = year;
        this.likesAmount = likesAmount;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public long getLikesAmount() {
        return likesAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PoemInfo poemInfo = (PoemInfo) o;

        if (likesAmount != poemInfo.likesAmount) return false;
        if (year != poemInfo.year) return false;
        if (author != null ? !author.equals(poemInfo.author) : poemInfo.author != null) return false;
        if (name != null ? !name.equals(poemInfo.name) : poemInfo.name != null) return false;
        if (text != null ? !text.equals(poemInfo.text) : poemInfo.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (int) (likesAmount ^ (likesAmount >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PoemInfo{" +
                "text='" + text + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", likesAmount=" + likesAmount +
                '}';
    }
}
