
/**
 * Name: Anurat Bhattacharya
 * Roll: 19CS10071
 * Java Assignment-0
 * Date 13/1/2020
 */
import java.util.*;

class Group extends node implements Printable {
    // DownLink IndMem;
    // Downlink BizMem;

    public Group(String Name) {// Constructor
        super(Name);
    }

    public void setIndMem(ArrayList<Individual> IndMemList) {// Settingindividual Members
        this.getDownLink().addAll(IndMemList);
    }

    public void setBizMem(ArrayList<Business> BizMemList) {// Setting Group Members
        this.getDownLink().addAll(BizMemList);
    }

    public void Print() {// Printing all nodes
        System.out.printf("Id: %d Name of Group: %s\n", getId(), getName());
        System.out.printf("Created on: %s\n", getDateofCreation().getTime());
        System.out.println();
    }

    public void PrintLinkedNodes() {// Printing all Linked nodes
        if (this.getDownLink().size() == 0) {
            System.out.printf("No linked nodes to current group(id = %d)\n\n", getId());
            return;
        }
        if (this.getDownLink().size() > 0) {
            System.out.printf("Printing all Members of %s \n", this.getName());
            for (node itr : this.getDownLink()) {
                if (itr instanceof Individual) {
                    System.out.printf("Individual Member :\n");
                    ((Individual) itr).Print();
                } else if ((itr instanceof Business)) {
                    System.out.printf("Business Member :\n");
                    ((Business) itr).Print();
                }

            }
        }
        System.out.println();

    }
}