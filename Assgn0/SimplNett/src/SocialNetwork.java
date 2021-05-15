
/**
 * Name: Anurat Bhattacharya
 * Roll: 19CS10071
 * Java Assignment-0
 * Date 13/1/2020
 */
import java.text.DateFormatSymbols;
import java.util.*;

interface Printable {
    void Print();

    void PrintLinkedNodes();
}

class SocialNetwork {
    private ArrayList<Individual> Indv;
    private ArrayList<Group> Grps;
    private ArrayList<Business> Biz;
    private ArrayList<Organisation> Orgs;

    // Constructor
    SocialNetwork() {
        Indv = new ArrayList<Individual>();
        Grps = new ArrayList<Group>();
        Biz = new ArrayList<Business>();
        Orgs = new ArrayList<Organisation>();
    }

    // Searching for Individual
    public Individual Indvsrc(String Name) {

        for (Individual itr : Indv) {
            if (itr.getName().equals(Name)) {
                return itr;
            }
        }
        return null;
    }

    // Searching for Business
    public Business Bizsrc(String Name) {

        for (int i = 0; i < Biz.size(); i++) {
            if (Biz.get(i).getName().equals(Name)) {
                return Biz.get(i);
            }
        }
        return null;
    }

    // Searching for Organisation
    public Organisation Orgsrc(String Name) {

        for (int i = 0; i < Orgs.size(); i++) {
            if (Orgs.get(i).getName().equals(Name)) {
                return Orgs.get(i);
            }
        }
        return null;
    }

    // Searching for Group
    public Group Grpsrc(String Name) {

        for (int i = 0; i < Grps.size(); i++) {
            if (Grps.get(i).getName().equals(Name)) {
                return Grps.get(i);
            }
        }
        return null;
    }

    // To create and Individual and add it to Indv(list of all Individuals)
    public Individual createIndv(String Name, int day, int month, int year) {
        Individual newIndv = new Individual(Name, day, month, year);
        Indv.add(newIndv);
        return newIndv;
    }

    // TO create Business
    public Business createBiz(String Name, double x, double y, ArrayList<Individual> Own, ArrayList<Individual> Cust) {
        Business newBiz = new Business(Name, x, y);
        newBiz.setOwners(Own);
        newBiz.setCust(Cust);
        Biz.add(newBiz);
        for (int i = 0; i < Own.size(); i++) {
            Own.get(i).addOwnedBiz(newBiz);
        }
        for (int i = 0; i < Cust.size(); i++) {
            Cust.get(i).addCustBiz(newBiz);
        }
        return newBiz;
    }

    // To create a Group
    public Group createGrps(String Name, ArrayList<Individual> IndMemList, ArrayList<Business> BizMemList) {

        Group newGrps = new Group(Name);
        newGrps.setIndMem(IndMemList);
        newGrps.setBizMem(BizMemList);
        for (int i = 0; i < IndMemList.size(); i++) {
            IndMemList.get(i).addGroup(newGrps);
        }
        for (int i = 0; i < BizMemList.size(); i++) {
            BizMemList.get(i).addGrp(newGrps);
        }
        Grps.add(newGrps);
        return newGrps;
    }

    // To create an Organisation
    public Organisation createOrg(String Name, double x, double y, ArrayList<Individual> MemList) {
        Organisation newOrg = new Organisation(Name, x, y);
        newOrg.setMem(MemList);
        Orgs.add(newOrg);
        for (int i = 0; i < MemList.size(); i++) {
            MemList.get(i).addOrg(newOrg);
        }
        return newOrg;
    }

    // Wrapper func for inserting an Individual
    public Individual insertIndv(Scanner ip) {
        System.out.printf("Enter Name: ");
        String nme = ip.next();
        System.out.printf("Enter Date of Birth as day,month and year space separated: ");
        int day, month, year;
        day = ip.nextInt();
        month = ip.nextInt();
        year = ip.nextInt();
        return createIndv(nme, day, month, year);
    }

    // Wrapper func for insert Individual with name already inputted
    public Individual insertIndv(Scanner ip, String nme) {
        System.out.printf("Enter Date of Birth as day,month and year space separated: ");
        int day, month, year;
        day = ip.nextInt();
        month = ip.nextInt();
        year = ip.nextInt();
        return createIndv(nme, day, month, year);
    }

    // Wrapper func for insert Business
    public Business insertBiz(Scanner ip) {
        System.out.printf("Enter Business Name: ");
        String nme = ip.next();
        System.out.printf("Enter Location x and y coordinates space separated: ");
        double x = ip.nextDouble();
        double y = ip.nextDouble();
        System.out.printf("Enter number of Owners:");
        int ownNo = ip.nextInt();
        ArrayList<Individual> OwnList = new ArrayList<Individual>();// current list of owners

        for (int i = 0; i < ownNo; i++) {
            System.out.printf("Enter Owner %d name: ", i + 1);
            String currName = ip.next();
            Individual currOwner = Indvsrc(currName);
            if (currOwner == null) {
                System.out.printf(
                        "%s does not exist,Do you want to add current Individual,press 1 to confirm,0 to continue: ",
                        currName);
                int flg = ip.nextInt();
                if (flg == 0) {
                    continue;
                }
                Individual currIndv = insertIndv(ip, currName);
                OwnList.add(currIndv);

            } else {
                OwnList.add(currOwner);
            }

        }

        System.out.printf("Enter number of Customers:");
        int custNo = ip.nextInt();
        ArrayList<Individual> CustList = new ArrayList<Individual>();// current list of owners
        for (int i = 0; i < custNo; i++) {
            System.out.printf("Enter Customer %d name: ", i + 1);
            String currName = ip.next();
            Individual currCust = Indvsrc(currName);
            if (currCust == null) {
                System.out.printf(
                        "%s does not exist,Do you want to add current Individual,press 1 to confirm,0 to continue: ",
                        currName);
                int flg = ip.nextInt();
                if (flg == 0)
                    continue;
                Individual currIndv = insertIndv(ip, currName);
                CustList.add(currIndv);

            } else {
                CustList.add(currCust);
            }

        }
        return createBiz(nme, x, y, OwnList, CustList);
    }

    // Wrapper func for insert Business with name already inputted
    public Business insertBiz(Scanner ip, String nme) {
        System.out.printf("Enter Location x and y coordinates space separated: ");
        double x = ip.nextDouble();
        double y = ip.nextDouble();
        System.out.printf("Enter number of Owners:");
        int ownNo = ip.nextInt();
        ArrayList<Individual> OwnList = new ArrayList<Individual>();// current list of owners

        for (int i = 0; i < ownNo; i++) {
            System.out.printf("Enter Owner %d name: ", i + 1);
            String currName = ip.next();
            Individual currOwner = Indvsrc(currName);
            if (currOwner == null) {
                System.out.printf(
                        "%s does not exist,Do you want to add current Individual,press 1 to confirm,0 to continue: ",
                        currName);
                int flg = ip.nextInt();
                if (flg == 0)
                    continue;

                Individual currIndv = insertIndv(ip, currName);
                OwnList.add(currIndv);

            } else {
                OwnList.add(currOwner);
            }

        }

        System.out.printf("Enter number of Customers:");
        int custNo = ip.nextInt();
        ArrayList<Individual> CustList = new ArrayList<Individual>();// current list of owners
        for (int i = 0; i < custNo; i++) {
            System.out.printf("Enter Customer %d name: ", i + 1);
            String currName = ip.next();
            Individual currCust = Indvsrc(currName);
            if (currCust == null) {
                System.out.printf(
                        "%s does not exist,Do you want to add current Individual,press 1 to confirm,0 to continue: ",
                        currName);
                int flg = ip.nextInt();
                if (flg == 0)
                    continue;

                Individual currIndv = insertIndv(ip, currName);
                CustList.add(currIndv);

            } else {
                CustList.add(currCust);
            }

        }
        return createBiz(nme, x, y, OwnList, CustList);
    }

    // Wrapper func for insert Organisation
    public Group insertGrp(Scanner ip) {
        System.out.printf("Enter Group Name: ");
        String nme = ip.next();
        // Add Individual Members(can be 0 as well)
        System.out.printf("Enter number of Individual Members:");
        int IndmemNo = ip.nextInt();
        ArrayList<Individual> IndMemList = new ArrayList<Individual>();// current list of Members
        for (int i = 0; i < IndmemNo; i++) {
            System.out.printf("Enter Member %d name: ", i + 1);
            String currName = ip.next();
            Individual currMem = Indvsrc(currName);
            if (currMem == null) {
                System.out.printf(
                        "%s does not exist,Do you want to add current Individual,press 1 to confirm,0 to continue: ",
                        currName);
                int flg = ip.nextInt();
                if (flg == 0)
                    continue;
                Individual currIndv = insertIndv(ip, currName);
                IndMemList.add(currIndv);

            } else {
                IndMemList.add(currMem);
            }
        }
        // Add Biz. Members can be 0 as well
        System.out.printf("Enter number of Business Members:");
        int BizmemNo = ip.nextInt();
        ArrayList<Business> BizMemList = new ArrayList<Business>();// current list of Members
        for (int i = 0; i < BizmemNo; i++) {
            System.out.printf("Enter Business %d name: ", i + 1);
            String currName = ip.next();// Business name input
            Business currMem = Bizsrc(currName);// Searching for input business
            if (currMem == null) {
                System.out.printf(
                        "%s does not exist,Do you want to add current Business,press 1 to confirm,0 to continue: ",
                        currName);
                int flg = ip.nextInt();
                if (flg == 0)
                    continue;
                Business currBiz = insertBiz(ip, currName);
                if (currBiz != null) {
                    BizMemList.add(currBiz);
                }

            } else {
                BizMemList.add(currMem);
            }
        }
        return createGrps(nme, IndMemList, BizMemList);
    }

    // Wrapper func for insert Organisation with name already inputted
    public Organisation insertOrg(Scanner ip) {
        System.out.printf("Enter Organisation Name: ");
        String nme = ip.next();
        System.out.printf("Enter Location x and y coordinates space separated: ");
        double x = ip.nextDouble();
        double y = ip.nextDouble();
        System.out.printf("Enter number of Members:");
        int memNo = ip.nextInt();
        ArrayList<Individual> MemList = new ArrayList<Individual>();// current list of Members
        for (int i = 0; i < memNo; i++) {
            System.out.printf("Enter Member %d name: ", i + 1);
            String currName = ip.next();
            Individual currMem = Indvsrc(currName);
            if (currMem == null) {
                System.out.printf(
                        "%s does not exist,Do you want to add current Individual,press 1 to confirm,0 to continue: ",
                        currName);
                int flg = ip.nextInt();
                if (flg == 0)
                    continue;

                Individual currIndv = insertIndv(ip, currName);
                MemList.add(currIndv);

            } else {
                MemList.add(currMem);
            }

        }
        return createOrg(nme, x, y, MemList);
    }

    // To add Group members
    public void addGrpMem(Scanner ip) {
        System.out.printf("Enter Group name: ");
        String GrpName = ip.next();
        Group G = Grpsrc(GrpName);
        if (G == null) {
            System.out.printf("NO group with name %s\n", GrpName);
            return;
        }
        System.out.printf("Enter i for Individual,b for adding Business member: ");
        String tp = ip.next();
        if (tp.equals("i")) {
            System.out.printf("Enter Individual name:");
            String nme = ip.next();
            Individual I = Indvsrc(nme);
            if (I == null) {
                System.out.printf("No Individual with name %s.Do you want to create one,Enter 1 to confirm,0 else: ");
                int flg = ip.nextInt();
                if (flg == 0) {
                    return;
                }
                Individual newI = insertIndv(ip, nme);
                newI.getUpLink().add(G);
                G.getDownLink().add(newI);
                return;
            } else {
                I.getUpLink().add(G);
                G.getDownLink().add(I);
            }            
        } else if (tp.equals("b")) {
            System.out.printf("Enter Business name:");
            String nme = ip.next();
            Business B = Bizsrc(nme);
            if (B == null) {
                System.out.printf("No Business with name %s.Do you want to create one,Enter 1 to confirm,0 else: ",
                        nme);
                int flg = ip.nextInt();
                if (flg == 0) {
                    return;
                }
                Business newB = insertBiz(ip, nme);
                newB.getUpLink().add(G);
                G.getDownLink().add(newB);
                return;
            } else {
                B.getUpLink().add(G);
                G.getDownLink().add(B);
            }
        } else {
            System.out.printf("Invalid input!Enter as per menu.Aborting current process\n");
            return;
        }

    }

    // To add Organisation Members
    public void addOrgMem(Scanner ip) {
        System.out.printf("Enter Organisation name: ");
        String OrgName = ip.next();
        Organisation O = Orgsrc(OrgName);
        if (O == null) {
            System.out.printf("NO organisation with name %s\n", OrgName);
            return;
        }

        System.out.printf("Enter Individual name:");
        String nme = ip.next();
        Individual I = Indvsrc(nme);
        if (I == null) {
            System.out.printf("No Individual with name %s.Do you want to create one,Enter 1 to confirm,0 else: ", nme);
            int flg = ip.nextInt();
            if (flg == 0) {
                return;
            }
            Individual newI = insertIndv(ip, nme);
            newI.getUpLink().add(O);
            O.getDownLink().add(newI);
            return;
        } else {
            I.getUpLink().add(O);
            O.getDownLink().add(I);
        }

    }

    // To add Business Customers
    public void addBizCust(Scanner ip) {
        System.out.printf("Enter Business name: ");
        String BizName = ip.next();
        Business B = Bizsrc(BizName);
        if (B == null) {
            System.out.printf("NO group with name %s\n", BizName);
            return;
        }
        System.out.printf("Enter Individual Customer name:");
        String nme = ip.next();
        Individual I = Indvsrc(nme);
        if (I == null) {
            System.out.printf("No Individual with name %s.Do you want to create one,Enter 1 to confirm,0 else: ", nme);
            int flg = ip.nextInt();
            if (flg == 0) {
                return;
            }
            Individual newI = insertIndv(ip, nme);
            newI.getUpLink().add(B);
            B.getDownLink().add(newI);
            return;
        } else {
            I.getUpLink().add(B);
            B.getDownLink().add(I);
        }

    }

    // To add owners to Biz
    public void addBizOwner(Scanner ip) {
        System.out.printf("Enter Business name: ");
        String BizName = ip.next();
        Business B = Bizsrc(BizName);
        if (B == null) {
            System.out.printf("NO group with name %s\n", BizName);
            return;
        }
        System.out.printf("Enter Individual Owner name:");
        String nme = ip.next();
        Individual I = Indvsrc(nme);
        if (I == null) {
            System.out.printf("No Individual with name %s.Do you want to create one,Enter 1 to confirm,0 else: ", nme);
            int flg = ip.nextInt();
            if (flg == 0) {
                return;
            }
            Individual newI = insertIndv(ip, nme);
            newI.getDownLink().add(B);
            B.getUpLink().add(newI);
            return;
        } else {
            I.getDownLink().add(B);
            B.getUpLink().add(I);
        }
    }

    // For Deleting Groups
    public void delGrp(Scanner ip) {

        System.out.printf("Enter name of Group: ");
        String nme = ip.next();
        Group currGrp = Grpsrc(nme);
        if (currGrp == null) {
            System.out.printf("Sorry no Group with name %s\n", nme);
            return;
        }
        for (node itr : currGrp.getDownLink()) {
            itr.getUpLink().remove(currGrp);
        }
        for (node itr : currGrp.getUpLink()) {
            itr.getDownLink().remove(currGrp);
        }
        Grps.remove(currGrp);
    }

    // For Deleting Organisations
    public void delOrg(Scanner ip) {
        System.out.printf("Enter name of Organisation: ");
        String nme = ip.next();
        Organisation currOrg = Orgsrc(nme);
        if (currOrg == null) {
            System.out.printf("Sorry no Organisation with name %s\n", nme);
            return;
        }
        for (node itr : currOrg.getDownLink()) {
            itr.getUpLink().remove(currOrg);
        }
        for (node itr : currOrg.getUpLink()) {
            itr.getDownLink().remove(currOrg);
        }
        Orgs.remove(currOrg);
    }

    // For deleting Biz
    public void delBiz(Scanner ip) {
        System.out.printf("Enter name of Business: ");
        String nme = ip.next();
        Business currBiz = Bizsrc(nme);
        if (currBiz == null) {
            System.out.printf("Sorry no Business with name %s\n", nme);
            return;
        }
        for (node itr : currBiz.getDownLink()) {
            itr.getUpLink().remove(currBiz);
        }
        for (node itr : currBiz.getUpLink()) {
            itr.getDownLink().remove(currBiz);
        }
        Biz.remove(currBiz);
    }

    // For deleting Individuals
    public void delIndv(Scanner ip) {

        System.out.printf("Enter name of Individual: ");
        String nme = ip.next();
        Individual currIndv = Indvsrc(nme);
        if (currIndv == null) {
            System.out.printf("Sorry no Individual with name %s\n", nme);
            return;
        }
        for (node itr : currIndv.getDownLink()) {
            // Owned businesses
            itr.getUpLink().remove(currIndv);
        }
        for (node itr : currIndv.getUpLink()) {
            itr.getDownLink().remove(currIndv);
        }
        Indv.remove(currIndv);
    }

    // Checking equality wrt DOB
    public boolean equals(node A, Calendar d) {
        Calendar currD = ((Individual) A).getDOB();
        int d1 = currD.get(Calendar.DAY_OF_MONTH);
        int d2 = d.get(Calendar.DAY_OF_MONTH);
        int m1 = currD.get(Calendar.MONTH);
        int m2 = d.get(Calendar.MONTH);
        int y1 = currD.get(Calendar.YEAR);
        int y2 = d.get(Calendar.YEAR);
        return (d1 == d2 && m1 == m2 && y1 == y2);
    }

    // searching all nodes with some name
    public void srcAll(String nme) {
        System.out.printf("Searching for all nodes with name, %s\n", nme);
        int flg = 0;
        for (Individual itr : Indv) {
            if (itr.getName().equals(nme)) {
                System.out.printf("Individual : ");
                itr.Print();
                flg = 1;
            }
        }
        for (Business itr : Biz) {
            if (itr.getName().equals(nme)) {
                System.out.printf("Business  : ");
                itr.Print();
                flg = 1;
            }
        }
        for (Group itr : Grps) {
            if (itr.getName().equals(nme)) {
                System.out.printf("Group : ");
                itr.Print();
                flg = 1;
            }
        }
        for (Organisation itr : Orgs) {
            if (itr.getName().equals(nme)) {
                System.out.printf("Organisation : ");
                itr.Print();
                flg = 1;
            }
        }
        if (flg == 0) {
            System.out.println("No such node found!");
        }
    }

    // Searching all Individuals by DOB
    public void srcAll(Calendar d) {
        String[] monthNames = new DateFormatSymbols().getMonths();
        System.out.printf("Searching for all Individuals with Birth Day, %d %s %d\n\n", d.get(Calendar.DAY_OF_MONTH),
                monthNames[d.get(Calendar.MONTH)], d.get(Calendar.YEAR));
        int flg = 0;
        for (Individual itr : Indv) {
            if (equals(itr, d)) {
                System.out.printf("Individual : ");
                itr.Print();
                flg = 1;
            }
        }
        if (flg == 0) {
            System.out.printf("Sorry no nodes with DOB  %d %s %d\n\n", d.get(Calendar.DAY_OF_MONTH),
                    monthNames[d.get(Calendar.MONTH)], d.get(Calendar.YEAR));
        }

    }

    // Searching by type
    public void srcAllByType(String tp) {

        if (tp.equals("i")) {
            if (Indv.size() == 0) {
                System.out.println("No Individuals present!");
                return;
            }
            System.out.println("Printing all Individuals");

            for (Individual itr : Indv) {
                System.out.printf("Individual : ");
                itr.Print();
            }
        } else if (tp.equals("b")) {
            if (Biz.size() == 0) {
                System.out.println("No Businesses present!");
                return;
            }
            System.out.println("Printing all Businesses");
            for (Business itr : Biz) {
                System.out.printf("Business : ");
                itr.Print();
            }
        } else if (tp.equals("o")) {
            if (Orgs.size() == 0) {
                System.out.println("No Organisations present!");
                return;
            }
            System.out.println("Printing all Organisations");
            for (Organisation itr : Orgs) {
                System.out.printf("Organisation : ");
                itr.Print();
            }
        } else if (tp.equals("g")) {
            if (Grps.size() == 0) {
                System.out.println("No Groups present!");
                return;
            }
            System.out.println("Printing all Groups");
            for (Group itr : Grps) {
                System.out.printf("Group : ");
                itr.Print();
            }
        }
    }

    // To Print all nodes
    public void printAll() {
        if (Indv.size() + Biz.size() + Orgs.size() + Grps.size() == 0) {
            System.out.println("Sorry!No Nodes in the network");
            return;
        }
        System.out.println("Printing All Nodes of network\n");
        if (Indv.size() > 0) {
            System.out.println("Printing All Individuals\n");
            for (Individual itr : Indv) {
                System.out.print("Individual Descrip: ");
                itr.Print();
                System.out.println();
            }
        }
        if (Biz.size() > 0) {
            System.out.println("Printing All Businesses\n");
            for (Business itr : Biz) {
                System.out.print("Business Descrip: ");
                itr.Print();
                System.out.println();
            }
        }
        if (Orgs.size() > 0) {
            System.out.println("Printing All Organisations\n");
            for (Organisation itr : Orgs) {
                System.out.print("Organisation Descrip: ");
                itr.Print();
                System.out.println();
            }
        }
        if (Grps.size() > 0) {
            System.out.println("Printing All Groups\n");
            for (Group itr : Grps) {
                System.out.print("Group Descrip: ");
                itr.Print();
                System.out.println();
            }
        }

    }

    // To post Content
    void postContent(Scanner ip, String tp, String nme) {

        if (tp.equals("i")) {
            Individual I = Indvsrc(nme);
            if (I == null) {
                System.out.printf("Sorry!No Individual with name %s\n", nme);
                return;
            }
            System.out.printf("Enter new Content: ");
            String currContent = ip.next();
            I.getContent().add(currContent);
        } else if (tp.equals("b")) {
            Business B = Bizsrc(nme);
            if (B == null) {
                System.out.printf("Sorry!No Business with name %s\n", nme);
                return;
            }
            System.out.printf("Enter new Content: ");
            String currContent = ip.next();
            B.getContent().add(currContent);
        } else if (tp.equals("o")) {
            Organisation O = Orgsrc(nme);
            if (O == null) {
                System.out.printf("Sorry!No Organisation with name %s\n", nme);
                return;
            }
            System.out.printf("Enter new Content: ");
            String currContent = ip.next();
            O.getContent().add(currContent);
        } else if (tp.equals("g")) {
            Group G = Grpsrc(nme);
            if (G == null) {
                System.out.printf("Sorry!No Group with name %s\n", nme);
                return;
            }
            System.out.printf("Enter new Content: ");
            String currContent = ip.next();
            G.getContent().add(currContent);
        }
    }

    // To get all Content posted by some node with name nme and type tp
    void getAllContent(String tp, String nme) {
        if (tp.equals("i")) {
            Individual I = Indvsrc(nme);
            if (I == null) {
                System.out.printf("Sorry!No individual with name %s\n", nme);
                return;
            }
            if (I.getContent().size() == 0) {
                System.out.printf("Sorry no content Available");
                return;
            }
            System.out.printf("Content Posted by %s:\n", nme);
            int i = 0;
            for (String str : I.getContent()) {
                System.out.printf("%d. %s\n", i + 1, str);
            }
        } else if (tp.equals("b")) {
            Business B = Bizsrc(nme);
            if (B == null) {
                System.out.printf("Sorry!No Business with name %s\n", nme);
                return;
            }
            if (B.getContent().size() == 0) {
                System.out.printf("Sorry no content Available");
                return;
            }
            System.out.printf("Content Posted by %s:\n", nme);
            int i = 0;
            for (String str : B.getContent()) {
                System.out.printf("%d. %s\n", i + 1, str);
            }
        } else if (tp.equals("o")) {
            Organisation O = Orgsrc(nme);
            if (O == null) {
                System.out.printf("Sorry!No Organisation with name %s\n", nme);
                return;
            }
            if (O.getContent().size() == 0) {
                System.out.printf("Sorry no content Available");
                return;
            }
            System.out.printf("Content Posted by %s:\n", nme);
            int i = 0;
            for (String str : O.getContent()) {
                System.out.printf("%d. %s\n", i + 1, str);
            }
        } else if (tp.equals("g")) {
            Group G = Grpsrc(nme);
            if (G == null) {
                System.out.printf("Sorry!No Group with name %s\n", nme);
                return;
            }
            if (G.getContent().size() == 0) {
                System.out.printf("Sorry no content Available");
                return;
            }
            System.out.printf("Content Posted by %s:\n", nme);
            int i = 0;
            for (String str : G.getContent()) {
                System.out.printf("%d. %s\n", i + 1, str);
            }
        }
    }

    // To get all content posted by some node A
    public void getAllContent(node A) {
        if (A instanceof Individual) {
            System.out.printf("Content Posted by Individual, %s:\n", A.getName());
        } else if (A instanceof Business) {
            System.out.printf("Content Posted by Business, %s:\n", A.getName());
        } else if (A instanceof Organisation) {
            System.out.printf("Content Posted by Organisation, %s:\n", A.getName());
        } else if (A instanceof Group) {
            System.out.printf("Content Posted by Group, %s:\n", A.getName());
        }
        if (A.getContent().size() == 0) {
            System.out.println("No Content Available!");
            return;
        }
        int i = 0;
        for (String str : A.getContent()) {
            System.out.printf("%d. %s\n", i + 1, str);
        }

    }

    // For getting all nodes linked to current node (Including both Uplinks and
    // Downlinks)
    public void getAllLinkedContent(Scanner ip, String tp, String nme) {
        if (tp.equals("i")) {
            Individual I = Indvsrc(nme);
            if (I == null) {
                System.out.printf("Sorry!No individual with name %s\n", nme);
                return;
            }
            if (I.getUpLink().size() + I.getDownLink().size() == 0) {
                System.out.printf("Sorry No linked nodes to Individual %s\n", nme);
                return;
            }
            System.out.printf("Content of all Linked nodes of %s:\n", nme);
            for (node itr : I.getUpLink()) {
                getAllContent(itr);
            }
            for (node itr : I.getDownLink()) {
                getAllContent(itr);
            }

        } else if (tp.equals("b")) {
            Business B = Bizsrc(nme);
            if (B == null) {
                System.out.printf("Sorry!No Business with name %s\n", nme);
                return;
            }
            if (B.getUpLink().size() + B.getDownLink().size() == 0) {
                System.out.printf("Sorry No linked nodes to Business %s\n", nme);
                return;
            }
            System.out.printf("Content of all Linked nodes of  %s:\n", nme);
            for (node itr : B.getUpLink()) {
                getAllContent(itr);
            }
            for (node itr : B.getDownLink()) {
                getAllContent(itr);
            }
        } else if (tp.equals("o")) {
            Organisation O = Orgsrc(nme);
            if (O == null) {
                System.out.printf("Sorry!No Organisation with name %s\n", nme);
                return;
            }
            if (O.getUpLink().size() + O.getDownLink().size() == 0) {
                System.out.printf("Sorry No linked nodes to Organisation %s\n", nme);
                return;
            }
            System.out.printf("Content of all Linked nodes of  %s:\n", nme);
            for (node itr : O.getUpLink()) {
                getAllContent(itr);
            }
            for (node itr : O.getDownLink()) {
                getAllContent(itr);
            }
        } else if (tp.equals("g")) {
            Group G = Grpsrc(nme);
            if (G == null) {
                System.out.printf("Sorry!No Group with name %s\n", nme);
                return;
            }
            if (G.getUpLink().size() + G.getDownLink().size() == 0) {
                System.out.printf("Sorry No linked nodes to Group %s\n", nme);
                return;
            }
            System.out.printf("Content of Linked nodes of %s:\n", nme);
            for (node itr : G.getUpLink()) {
                getAllContent(itr);
            }
            for (node itr : G.getDownLink()) {
                getAllContent(itr);
            }
        }
    }

    // To check validity of inputted type
    public boolean chkType(String tp) {
        if (!(tp.equals("i") || tp.equals("b") || tp.equals("o") || tp.equals("g"))) {
            return false;
        }
        return true;
    }

    // Searching all nodes by Content
    public void srcByContent(String cont) {
        System.out.printf("Searching By Content ,\"%s\"\n\n", cont);
        int flg = 0;
        for (Individual I : Indv) {
            if (I.getContent().contains(cont)) {
                System.out.printf("Individual: ");
                I.Print();
                flg = 1;
            }
        }
        for (Business B : Biz) {
            if (B.getContent().contains(cont)) {
                System.out.printf("Business: ");
                B.Print();
                flg = 1;
            }
        }
        for (Organisation O : Orgs) {
            if (O.getContent().contains(cont)) {
                System.out.printf("Organisation: ");
                O.Print();
                flg = 1;
            }
        }
        for (Group G : Grps) {
            if (G.getContent().contains(cont)) {
                System.out.printf("Group: ");
                G.Print();
                flg = 1;
            }
        }
        if (flg == 0) {
            System.out.printf("Sorry! No node with content \"%s\" \n", cont);
        }
    }

    // Prointing Menu
    public void printMenu() {
        System.out.println("Menu:");
        System.out.println("1.Enter 1 to create an entity");
        System.out.println("2.Enter 2 to add Customer of a Business");
        System.out.println("3.Enter 3 to add Owner of a Business");
        System.out.println("4.Enter 4 to add Member of a Group");
        System.out.println("5.Enter 5 to add Member of an Organisation");
        System.out.println("6.Enter 6 to delete entity");
        System.out.println("7.Enter 7 to search for an entity");
        System.out.println("8.Enter 8 to print all entities connected to some entity");
        System.out.println("9.Enter 9 to Print All nodes");
        System.out.println("10.Eneter 10 to post content");
        System.out.println("11.Enter 11 to get all content posted by an entity");
        System.out.println("12.Enter 12 to print content of all nodes linked to some node ");
        System.out.println("13.Enter 13 to search by Content: ");
        System.out.println("14.Enter 14 to display Menu again");
        System.out.println("15.Enter 0 to exit ");
    }

    // Driver main function
    public static void main(String[] args) {
        int mode = 1;
        Scanner ip = new Scanner(System.in);       
        SocialNetwork S = new SocialNetwork();
        System.out.printf("Welcome to <--SimpNett-->, the Simplest Social Network\n");
        S.printMenu();
        while (mode > 0) {
            System.out.printf("Enter Mode(Enter 14 to Show Menu again): ");
            mode = ip.nextInt();// Inputting the mode
            if (mode == 0)// Exit
                break;
            if (mode == 1) {// Insert
                System.out.printf("Enter Type of Entity(i:Individual,b:Business,o:Organisation,g:Group)\n");
                String tp = ip.next();
                if (!S.chkType(tp)) {
                    System.out.println("Invalid Type of Entity entered Aborting Current Process\n");
                    continue;
                }
                if (tp.equals("i")) {
                    S.insertIndv(ip);
                } else if (tp.equals("b")) {
                    S.insertBiz(ip);
                } else if (tp.equals("o")) {
                    S.insertOrg(ip);
                } else if (tp.equals("g")) {
                    S.insertGrp(ip);
                }

            } else if (mode == 2) {// Add Customer to Business
                S.addBizCust(ip);
            } else if (mode == 3) {// Add Owner to Business
                S.addBizOwner(ip);
            } else if (mode == 4) {// Add member to group
                S.addGrpMem(ip);
            } else if (mode == 5) {// Add Org Member
                S.addOrgMem(ip);
            } else if (mode == 6) {// For deleting nodes
                System.out.printf("Enter Type of Entity to delete(i:Individual,b:Business,o:Organisation,g:Group)\n");
                String tp = ip.next();
                if (!S.chkType(tp)) {
                    System.out.println("Invalid Type of Entity entered Aborting Current Process\n");
                    continue;
                }
                if (tp.equals("i")) {
                    S.delIndv(ip);
                } else if (tp.equals("b")) {
                    S.delBiz(ip);
                } else if (tp.equals("o")) {
                    S.delOrg(ip);
                } else if (tp.equals("g")) {
                    S.delGrp(ip);
                }
            } else if (mode == 7) {// Searching by name ,type and DOB
                System.out.printf("Enter mode of search , type for search by type \n");
                System.out.printf("name for search by name, and date for seach by birth date:");
                String srcMode = ip.next();
                if (srcMode.equals("name")) {
                    System.out.printf("Enter Name: \n");
                    String nme = ip.next();
                    S.srcAll(nme);
                } else if (srcMode.equals("type")) {
                    System.out
                            .printf("Enter Type of Entity to search(i:Individual,b:Business,o:Organisation,g:Group)\n");
                    String tp = ip.next();
                    if (!S.chkType(tp)) {
                        System.out.println("Invalid Type of Entity entered Aborting Current Process\n");
                        continue;
                    }
                    S.srcAllByType(tp);

                } else if (srcMode.equals("date")) {
                    System.out.printf("Enter day month and year space separated: ");
                    int dy, m, y;
                    dy = ip.nextInt();
                    m = ip.nextInt();
                    y = ip.nextInt();
                    Calendar d = Calendar.getInstance();
                    d.set(y, m - 1, dy);
                    S.srcAll(d);
                }

            } else if (mode == 8) {// For Printing all Nodes Linked to a given node
                System.out
                        .printf("Enter type of node, i for Individual,b for Business,o for Organisation,g for Group\n");
                String tp = ip.next();
                System.out.printf("Enter the name of Node whose linked nodes are to be Printed: ");
                String nme = ip.next();
                if (!S.chkType(tp)) {
                    System.out.println("Invalid Type of Entity entered Aborting Current Process\n");
                    continue;
                }
                if (tp.equals("i")) {
                    Individual currIndv = S.Indvsrc(nme);
                    if (currIndv == null) {
                        System.out.printf("No Individual with name %s\n", nme);
                        continue;
                    }
                    currIndv.PrintLinkedNodes();
                } else if (tp.equals("b")) {
                    Business currBiz = S.Bizsrc(nme);
                    if (currBiz == null) {
                        System.out.printf("No Business with name %s\n", nme);
                        continue;
                    }
                    currBiz.PrintLinkedNodes();
                } else if (tp.equals("o")) {
                    Organisation currOrg = S.Orgsrc(nme);
                    if (currOrg == null) {
                        System.out.printf("No Organisation with name %s\n", nme);
                        continue;
                    }
                    currOrg.PrintLinkedNodes();
                } else if (tp.equals("g")) {
                    Group currGrps = S.Grpsrc(nme);
                    if (currGrps == null) {
                        System.out.printf("No Group with name %s\n", nme);
                        continue;
                    }
                    currGrps.PrintLinkedNodes();
                }

            } else if (mode == 9) {// For Printing all Nodes
                S.printAll();
            } else if (mode == 10) {// For Posting Content
                System.out
                        .printf("Enter type of node, i for Individual,b for Business,o for Organisation,g for Group\n");
                String tp = ip.next();
                if (!S.chkType(tp)) {
                    System.out.println("Invalid Type of Entity entered Aborting Current Process\n");
                    continue;
                }
                System.out.printf("Enter the name of Node whose content need to be posted: ");
                String nme = ip.next();
                S.postContent(ip, tp, nme);
            } else if (mode == 11) {// For vewing content of a node
                System.out
                        .printf("Enter type of node, i for Individual,b for Business,o for Organisation,g for Group\n");
                String tp = ip.next();
                if (!S.chkType(tp)) {
                    System.out.println("Invalid Type of Entity entered Aborting Current Process\n");
                    continue;
                }
                System.out.printf("Enter the name of Node whose content need to be Printed: ");
                String nme = ip.next();
                S.getAllContent(tp, nme);
            } else if (mode == 12) {// For veiwing Content of all nodes linked to a given node
                System.out
                        .printf("Enter type of node, i for Individual,b for Business,o for Organisation,g for Group\n");
                String tp = ip.next();
                if (!S.chkType(tp)) {
                    System.out.println("Invalid Type of Entity entered Aborting Current Process\n");
                    continue;
                }
                System.out.printf("Enter the name of Node whose content need to be Printed: ");
                String nme = ip.next();
                S.getAllLinkedContent(ip, tp, nme);
            } else if (mode == 13) {// To search all nodes by Content
                System.out.printf("Enter content to search: ");
                String cont = ip.next();
                S.srcByContent(cont);
            } else if (mode == 14) {// To Print Menu
                S.printMenu();
            } else {// Invalid Input of Mode
                System.out.println("Invalid Mode Entered!Enter valid mode as written in the menu.\n");
            }

        }
    }

}