package com.example.lab_5;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Parser {
    static ArrayList<String> curencyList = new ArrayList<>();
    public static void parsing() {
        Log.d(Constants.PARSER_TAG, "method parsing called");
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    Log.d(Constants.PARSER_TAG, "method startElement called");
                    if (localName.equals("Cube") && attributes.getLength() == 2) {
                        HashMap<String, String> currenyNode = new HashMap<>();
                        for (int i = 0; i < attributes.getLength(); i++) {
                            String attrName = attributes.getQName(i);
                            String attrVal = attributes.getValue(i);
                            currenyNode.put(attrName, attrVal);
                        }
                        curencyList.add(String.format("%s: %s", currenyNode.get("currency"), currenyNode.get("rate")));
                    }
                }
            };
            InputStream istream = DataLoader.downlowdUrl("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
            parser.parse(istream, handler);

        } catch (ParserConfigurationException e) {
            Log.d(Constants.PARSER_TAG, e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d(Constants.PARSER_TAG, e.getMessage());
            e.printStackTrace();
        } catch (SAXException e) {
            Log.d(Constants.PARSER_TAG, e.getMessage());
            e.printStackTrace();
        }
    }
    public static ArrayList<String> getList(){
        Log.d(Constants.PARSER_TAG, "method getList called");
        return curencyList;
    }
}
