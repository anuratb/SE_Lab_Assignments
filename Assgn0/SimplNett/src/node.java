
/**
 * Name: Anurat Bhattacharya
 * Roll: 19CS10071
 * Java Assignment-0
 * Date 13/1/2020
 */
import java.util.*;

class node {
    static int key = 0;
    private int id;
    private String name;
    private Calendar date;
    private Set<String> content;
    private ArrayList<node> upLink;
    private ArrayList<node> downLink;

    // Constructor for node
    public node(String Name) {
        id = key + 1;
        key++;// Allotting unique id
        name = new String(Name);
        date = Calendar.getInstance();
        content = new HashSet<String>();
        upLink = new ArrayList<node>();
        downLink = new ArrayList<node>();
    }

    // getting methods
    public ArrayList<node> getUpLink() {
        return upLink;
    }

    public ArrayList<node> getDownLink() {
        return downLink;
    }

    public int getId() {
        return id;
    }

    public Set<String> getContent() {
        return content;
    }

    public String getName() {
        return new String(name);
    }

    public Calendar getDateofCreation() {
        return date;
    }

    // setting methods
    public void setName(String nme) {
        name = nme;
    }

    public void setDownLink(ArrayList<node> A) {
        downLink = A;
    }
}