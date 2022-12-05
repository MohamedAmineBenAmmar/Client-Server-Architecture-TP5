import Calcul.calcul_montants;
import Calcul.calcul_montantsHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Properties;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) {
        try {
            // Reading data from the user
            Scanner scanner  = new Scanner(System.in);

            System.out.print("Donner HT: ");
            double ht = scanner.nextDouble();

            System.out.print("Donner TVA: ");
            double tva = scanner.nextDouble();

            // create and initialize the ORB
            Properties p = new Properties();
            p.put("org.omg.CORBA.ORBInitialPort", "1500");
            p.put("org.omg.CORBA.ORBInitialHost", "localhost");
            ORB orb = ORB.init(args, p);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            calcul_montants href = calcul_montantsHelper.narrow(ncRef.resolve_str("Calcul-TTC"));

            double ans = href.calcul_ttc(ht, tva);
            System.out.println("The output of the remote method is: " + ans);

        } catch (InvalidName | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName | NotFound invalidName) {
            invalidName.printStackTrace();
        }

    }

}
