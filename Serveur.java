
import Calcul.calcul_montants;
import Calcul.calcul_montantsHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Properties;

public class Serveur {

    public static void main(String[] args) {
        try {
            // Initialisation de l'ORB
            // Properties p = new Properties();
            // p.put("org.omg.CORBA.ORBInitialPort", 1500);
            // p.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
            // ORB orb = ORB.init(args, p);
            ORB orb = ORB.init(args, null);
            // Récupérer la référence du RootPOA et activer le POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // Créer un servant (instance de classe d'implémentation) et l'enregistrer avec l'ORB
            Calcul_Servant calculImpl = new Calcul_Servant();


            /* *** DEBUT INHERITANCE MODEL (vous pouvez vous référer aux chapitres précédents pour utiliser le modèle Tie Delegation Model) *** */

            // Récupérer une référence du servant
            org.omg.CORBA.Object servantRef = rootpoa.servant_to_reference(calculImpl);

            calcul_montants service = calcul_montantsHelper.narrow(servantRef);

            /* *** FIN INHERITANCE MODEL *** */

            // Récupérer la référence du service de nommage
            org.omg.CORBA.Object nsRef = orb.resolve_initial_references("NameService");
            NamingContextExt nce = NamingContextExtHelper.narrow(nsRef);

            // Créer un nom pour le service et ajouter le service
            NameComponent nc[] = nce.to_name("Calcul-TTC");
            nce.rebind(nc, service);

            // Démarrer le service et attendre les requêtes des clients
            System.out.println("Server ready and waiting ...");
            orb.run(); // En attente de nouveaux clients CORBA

        } catch (Exception e){
            System.err.println(e);
        }

        System.out.println("Exiting ...");

    }
}
