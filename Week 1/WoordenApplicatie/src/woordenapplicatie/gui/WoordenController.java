package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {

    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Heb je dan geen hoedje meer\n"
            + "Maak er één van bordpapier\n"
            + "Eén, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "En als het hoedje dan niet past\n"
            + "Zetten we 't in de glazenkas\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier";

    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }

    @FXML
    private void aantalAction(ActionEvent event) {
        String[] words = (taInput.getText().replace("\n", " ")).split(" ");
        taOutput.setText("Totaal aantal woorden: " + String.valueOf(words.length));

        Set<String> uniqueWords = new HashSet();

        for (String word : words) {
            uniqueWords.add(word.toLowerCase().replace(",", ""));
        }
        taOutput.appendText("\nAantal verschillende woorden: " + String.valueOf(uniqueWords.size()));
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        String[] words = (taInput.getText().replace("\n", " ")).split(" ");
        Set<String> uniqueWords = new TreeSet(Collections.reverseOrder());
        for (String word : words) {
            uniqueWords.add(word.toLowerCase().replace(",", ""));
        }
        taOutput.setText("Unieke woorden:");
        for (String w : uniqueWords) {
            taOutput.appendText("\n" + w);
        }
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        String[] words = (taInput.getText().replace("\n", " ")).split(" ");
        Map<String, Integer> freqWords = new TreeMap();

        for (String word : words) {
            String w = word.toLowerCase().replace(",", "");
            if (freqWords.containsKey(w)) {
                Integer i = freqWords.get(w);
                i++;
                freqWords.put(w, i);
            } else {
                freqWords.put(w, 1);
            }
        }
        taOutput.setText("Frequentie woorden:");
        freqWords = sortByValue(freqWords);
        for (String key : freqWords.keySet()) {
            taOutput.appendText("\n" + key + "\t" + freqWords.get(key));
        }

    }

    public Map sortByValue(Map unsortedMap) {
        Map sortedMap = new TreeMap(new ValueComparator(unsortedMap));
        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }

    class ValueComparator implements Comparator {

        Map map;

        public ValueComparator(Map map) {
            this.map = map;
        }

        public int compare(Object keyA, Object keyB) {
            Comparable valueA = (Comparable) map.get(keyA);
            Comparable valueB = (Comparable) map.get(keyB);
            return valueA.compareTo(valueB);
        }
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
