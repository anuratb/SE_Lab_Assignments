
/**
 * Name: Anurat Bhattacharya
 * Roll: 19CS10071
 * Java Assignment-0
 * Date 13/1/2020
 */
import java.text.DateFormatSymbols;
import java.util.*;

class Individual extends node implements Printable {
    private Calendar BirthDay;
    // Biz Cust,Org Mem,Grp Mem : UpLink
    // Biz Own :DownLink

    public Individual(String Name, int day, int month, int year) {
        super(Name);
        BirthDay = Calendar.getInstance();
        BirthDay.set(year, month - 1, day);// 0 indexed months
    }

    public Calendar getDOB() {
        return BirthDay;
    }

    public void addOwnedBiz(Business B) {
        this.getDownLink().add(B);
    }

    public void addGroup(Group G) {
        this.getUpLink().add(G);
    }

    public void addOrg(Organisation Org) {
        this.getUpLink().add(Org);
    }

    public void addCustBiz(Business B) {
        this.getUpLink().add(B);
    }

    public void Print() {// For Printing Current Indv
        System.out.printf("Id: %d Name of Individual: %s\n", getId(), getName());
        String[] monthNames = new DateFormatSymbols().getMonths();
        System.out.printf("Date of Birth: %d %s %d\n", BirthDay.get(Calendar.DAY_OF_MONTH),
                monthNames[BirthDay.get(Calendar.MONTH)], BirthDay.get(Calendar.YEAR));
        System.out.printf("Created on: %s\n\n", getDateofCreation().getTime());
    }

    public ArrayList<Business> getBizOwn() {// For getting Owned Businesses List
        ArrayList<Business> OwnBiz = new ArrayList<Business>();
        for (node itr : this.getDownLink()) {
            if (itr instanceof Business) {
                OwnBiz.add((Business) itr);
            }
        }
        return OwnBiz;
    }

    public ArrayList<Business> getBizCust() {// For getting list of Businesses of which it is a customer
        ArrayList<Business> CustBiz = new ArrayList<Business>();
        for (node itr : this.getUpLink()) {
            if (itr instanceof Business) {
                CustBiz.add((Business) itr);
            }
        }
        return CustBiz;
    }

    public ArrayList<Organisation> getOrg() {// getting all organisations it is a membr of
        ArrayList<Organisation> OrgMem = new ArrayList<Organisation>();
        for (node itr : this.getUpLink()) {
            if (itr instanceof Organisation) {
                OrgMem.add((Organisation) itr);
            }
        }
        return OrgMem;
    }

    public ArrayList<Group> getGrp() {// getting all groups it is a member of
        ArrayList<Group> MemGroup = new ArrayList<Group>();
        for (node itr : this.getDownLink()) {
            if (itr instanceof Group) {
                MemGroup.add((Group) itr);
            }
        }
        return MemGroup;
    }

    public void PrintLinkedNodes() {// For Printing all Linked Nodes
        if (this.getUpLink().size() + this.getDownLink().size() == 0) {
            System.out.printf("No linked nodes to current indiviual(id = %d)\n\n", getId());
            return;
        }
        System.out.printf("Printing all nodes Connected to Organisation,%s\n", this.getName());
        for (node itr : this.getDownLink()) {
            if (itr instanceof Business) {
                System.out.printf("Owned Business: ");
                ((Business) itr).Print();
            }
        }
        for (node itr : this.getUpLink()) {
            if (itr instanceof Business) {
                System.out.printf("Customer of Business : ");
                ((Business) itr).Print();
            } else if (itr instanceof Organisation) {
                System.out.printf("Member of Organisation: ");
                ((Organisation) itr).Print();
            } else if (itr instanceof Group) {
                System.out.printf("Member of Group: ");
                ((Group) itr).Print();
            }
        }
        System.out.println();

    }
}