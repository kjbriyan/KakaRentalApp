package id.kakarental.store.Helper;

public class FormatRp {

    public static String getFomat(String nilai){
        String format = "Rp." + String.format("%,.0f",Double.parseDouble(nilai));
        return format;
    }

    private static String getFomat2(Double nilai){
        //   "Rp." + String.format("%,.2f", Double.parseDouble(i.getHargaBeli())))
        String format = "Rp." + String.format("%,.0f",nilai);
        return format;
    }

    public static String getTotal(String nilai, int banyak){
        Double hasil = Double.parseDouble(nilai) * banyak;

        String hasilNiali = getFomat2(hasil);

        return hasilNiali;

    }
}
