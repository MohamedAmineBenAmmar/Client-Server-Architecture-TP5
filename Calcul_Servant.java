import Calcul.calcul_montantsPOA;

public class Calcul_Servant extends calcul_montantsPOA {

    @Override
    public double calcul_ttc(double ht, double tva) {
        return ht * (1+tva);
    }
}
