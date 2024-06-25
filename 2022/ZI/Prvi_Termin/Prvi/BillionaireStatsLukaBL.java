package hr.fer.oop.zi.z1;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BillionaireStats {

    // TODO: ovdje dodati potrebne materijale

    public static int countCountry(List<BillionaireDatum> dst, String country) {
        return (int) dst.stream()
                .filter((bil) -> bil.getCountry().equals(country))
                .count();
    }

    public static int countAgeRange(List<BillionaireDatum> dst, int lower, int upper){
        return (int) dst.stream()
                .filter((bil) -> {
                    if(bil.getAge() == null)
                        return false;

                    return bil.getAge() <= upper && bil.getAge() >= lower;
                })
                .count();
    }

    public static Map<Integer, Long> makeHistogram(List<BillionaireDatum> dst, int binWidth){
        int minWorth = dst.stream()
                .min((a,b) -> a.getWorth().compareTo(b.getWorth()))  //ILI Comparator.comparing(BillionaireDatum::getWorth)
                .map((bil) -> bil.getWorth()) //ILI BillionaireDatum::getWorth
                .get();

        Map<Integer, Long> hist = new TreeMap<>();

        dst.stream().forEach((bil) -> {
            int histValue = (bil.getWorth() - minWorth) / binWidth * binWidth + minWorth;

            if(!hist.containsKey(histValue))
                hist.put(histValue, 0l);

            Long temp = hist.get(histValue);
            temp++;
            hist.put(histValue, temp);
        });

        return hist;
    }

}
