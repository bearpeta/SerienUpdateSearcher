/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updater;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Node;

/**
 *
 * @author Alessandro
 */
public class Controller {

    private static Controller instance = null;

    protected Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public String getfromInput(String input, String searchedString) {
        Pattern pattern = Pattern.compile(searchedString);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group();
        }

        return "False regex";
    }

    public TreeMap<String, String> deleteDuplicate(List<Episode> episodeList) {
        TreeMap<String, String> map = new TreeMap<>();
        String mapKey;
        for (Episode ep : episodeList) {
            mapKey = ep.getLanguage() + "." + ep.getTitle() + "." + ep.getEpisodeNumber();

            if (!map.containsKey(mapKey)) {
                map.put(mapKey, ep.getEpisodeName());
            } else {
                if (map.get(mapKey).equals(" ")) {
                    map.put(mapKey, ep.getEpisodeName());
                }
            }
        }
        return map;
    }

    public boolean equalToSerie(Node titleNode, List<String> list) {
        for (String serie : list) {
            if (titleNode.getNodeValue().toLowerCase().contains(serie.toString().toLowerCase())) {
                return true;

            }
        }
        return false;
    }

    public List<String> replaceSpace(List series) {
        List<String> tempSeries = new ArrayList<>(series);
        series.clear();
        String tempSerie;

        for (int i = 0; i < tempSeries.size(); i++) {
            if (!tempSeries.isEmpty()) {
                tempSerie = (String) tempSeries.get(i).toString().replace(' ', '.');
                series.add(tempSerie);
            }
        }
        return series;
    }
}
