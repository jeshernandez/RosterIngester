package com.rosteringester.usps;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

/**
 * Created by jeshernandez on 07/11/2017.
 */
public class USPSXML {


public String[] readXML(String xmlInput) {

    // TODO - refactor this class into re-usable XML

    String[] returnAddress = new String[5];

    try {

        System.out.println(xmlInput);
        //File fXmlFile = new File(xmlInput);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new ByteArrayInputStream(xmlInput.getBytes("UTF-8")));

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();

        //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("Address");

        //System.out.println("----------------------------");

        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);

            //System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                //Element eElement = doc.getElementById("1");



                if(eElement.getElementsByTagName("Address2").item(0) == null) {
                    returnAddress[i] = eElement.getElementsByTagName("Description").item(0).getTextContent();
                    //System.out.println("ERRROR >>> " + des);
                } else {
                     System.out.println("Address : " + eElement.getElementsByTagName("Address2").item(0).getTextContent());
                     System.out.println("City : " + eElement.getElementsByTagName("City").item(0).getTextContent());
                     System.out.println("ZipCode : " + eElement.getElementsByTagName("Zip5").item(0).getTextContent());
                     System.out.println("City : " + eElement.getElementsByTagName("City").item(0).getTextContent());
                    returnAddress[i] = eElement.getElementsByTagName("Address2").item(0).getTextContent();

                }


            }
        } // End for-loop

    } catch (Exception e) {
        e.printStackTrace();
    }
    //System.out.println(xmlInput);

    return returnAddress;
} // end of raedXML method


} // End of USPSXML Class