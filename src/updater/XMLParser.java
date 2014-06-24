/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updater;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;

/**
 *
 * @author ap
 */
public class XMLParser {

    private static XMLParser instance = null;
    
    private final String sourceURL;
    private List<String> serieList;
    private final List<Episode> listEpisode;
    private final Document doc;
    private final Controller controller;
    
    protected XMLParser() throws ParserConfigurationException, IOException, SAXException {
        controller = Controller.getInstance();
        sourceURL = "http://serienjunkies.org/xml/feeds/episoden.xml";
        
        listEpisode = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        doc = db.parse(sourceURL);
        doc.getDocumentElement().normalize();
    }

    public static XMLParser getInstance() {
        if (instance == null) {
            try {
                instance = new XMLParser();
            } catch (    ParserConfigurationException | IOException | SAXException ex) {
                Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }

    public void readXML(List serienList) throws ParserConfigurationException, IOException, SAXException {

        this.serieList = serienList;
        this.serieList= controller.replaceSpace(serieList);
        

        // loop through each item
        NodeList items = doc.getElementsByTagName("item");
        for (int i = 0; i < items.getLength(); i++) {
            Node n = items.item(i);
            if (n.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element e = (Element) n;

            Node titleNode = e.getElementsByTagName("title").item(0).getChildNodes().item(0);
            Node timeNode = e.getElementsByTagName("pubDate").item(0).getChildNodes().item(0);
            Node urlNode = e.getElementsByTagName("link").item(0).getChildNodes().item(0);

            if (controller.equalToSerie(titleNode, serieList)) {
                listEpisode.add(new Episode(titleNode.getNodeValue(), timeNode.getNodeValue(), urlNode.getNodeValue()));
            }
        }
        
    }
    
    public List<Episode> getXMLResult() {
        Collections.sort(listEpisode);
        return listEpisode;
    }
}
