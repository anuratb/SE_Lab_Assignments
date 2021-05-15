
/**
 * Name: Anurat Bhattacharya
 * Roll: 19CS10071
 * Java Assignment-0
 * Date 13/1/2020
 */
import java.util.*;
import java.awt.geom.Point2D;

class Organisation extends node implements Printable {
	private Point2D loc;

	public Organisation(String Name, double x, double y) {// Constructor
		super(Name);
		loc = new Point2D.Double(x, y);
		loc.setLocation(x, y);
	}

	public void setMem(ArrayList<Individual> Mem) {// For Setting Members

		this.getDownLink().addAll(Mem);
	}

	public void Print() {// For Printing
		System.out.printf("Id: %d Name of Individual: %s\n", getId(), getName());
		System.out.printf("Location : ( %f ,%f )\n", loc.getX(), loc.getY());
		System.out.printf("Created on: %s\n", getDateofCreation().getTime());
		System.out.println();
	}

	public void PrintLinkedNodes() {// For Printing all Linked nodes
		if (this.getDownLink().size() == 0) {
			System.out.printf("No linked nodes to current indiviual(id = %d)\n\n", getId());
			return;
		}
		if (this.getDownLink().size() > 0) {
			System.out.println("Printing all individual members ");
			for (node itr : this.getDownLink()) {
				System.out.printf("Individual Member Description \n");
				((Individual) itr).Print();
			}
		}
		System.out.println();

	}
}