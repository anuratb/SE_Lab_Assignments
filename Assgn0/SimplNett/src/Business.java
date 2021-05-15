
/**
 * Name: Anurat Bhattacharya
 * Roll: 19CS10071
 * Java Assignment-0
 * Date 13/1/2020
 */
import java.util.*;

import java.awt.geom.Point2D;

class Business extends node implements Printable {
    private Point2D loc;

    // Customer Indv:DownLink
    // Owner Indv,Group : UpLink
    public Business(String Name, double x, double y) {// Constructor
        super(Name);
        loc = new Point2D.Double(x, y);
    }

    public void addGrp(Group G) {// For adding itself as a meber to a Group
        this.getUpLink().add(G);
    }

    public void setOwners(ArrayList<Individual> Own) {// Setting Owner List
        this.getUpLink().addAll(Own);
    }

    public void setCust(ArrayList<Individual> Cust) {// SettingCustomer List
        this.getDownLink().addAll(Cust);
    }

    public void Print() {// For Printing
        System.out.printf("Id: %d Name of Business: %s\n", getId(), getName());
        System.out.printf("Location : ( %f ,%f )\n", loc.getX(), loc.getY());
        System.out.printf("Created on: %s\n", getDateofCreation().getTime());
        System.out.println();
    }

    public void PrintLinkedNodes() {// For Printing Linked nodes
        if (this.getDownLink().size() + this.getUpLink().size() == 0) {
            System.out.printf("No linked nodes to current business(id = %d)\n\n", getId());
            return;
        }
        System.out.printf("Printing all linked nodes to Business %s\n", this.getName());
        for (node itr : this.getDownLink()) {
            if (itr instanceof Individual) {
                System.out.printf("Customer : ");
                ((Individual) itr).Print();
            }
        }
        for (node itr : this.getUpLink()) {
            if (itr instanceof Individual) {
                System.out.printf("Owner : ");
                ((Individual) itr).Print();
            } else if (itr instanceof Group) {
                System.out.printf("Member of Group: ");
                ((Group) itr).Print();
            }
        }
        System.out.println();
    }
}