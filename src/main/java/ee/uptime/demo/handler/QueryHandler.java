package ee.uptime.demo.handler;

import ee.uptime.demo.model.Item;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.function.Consumer;


public class QueryHandler extends DefaultHandler {

    private boolean bItems = false;
    private boolean bItem = false;
    private boolean bItemAttributes = false;
    private boolean bTitle = false;
    private boolean bOfferSummary = false;
    private boolean bLowestNewPrice = false;
    private boolean bFormattedPrice = false;

    private Consumer<Item> onItem;

    private StringBuilder sb;
    private Item item;


    public QueryHandler() {//TODO remove
    }

    public QueryHandler(Consumer<Item> onItem) {
        this.onItem = onItem;
    }

    @Override
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("items")) {
            bItems = true;
        } else if (qName.equalsIgnoreCase("item") && bItems) {
            bItem = true;
        } else if (qName.equalsIgnoreCase("ItemAttributes") && bItem) {
            bItemAttributes = true;
        } else if (qName.equalsIgnoreCase("title") && bItemAttributes) {
            bTitle = true;
            sb = new StringBuilder();
            item = new Item();
        } else if (qName.equalsIgnoreCase("OfferSummary") && bItem) {
            bOfferSummary = true;
        } else if (qName.equalsIgnoreCase("LowestNewPrice") && bOfferSummary) {
            bLowestNewPrice = true;
        } else if (qName.equalsIgnoreCase("FormattedPrice") && bLowestNewPrice) {
            bFormattedPrice = true;
            sb = new StringBuilder();
        }
    }

    @Override
    public void characters(char ch[],
                           int start, int length) throws SAXException {
        if (bTitle) {
            sb.append(new String(ch, start, length));
        } else if (bFormattedPrice) {
            sb.append(new String(ch, start, length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (bTitle) {
            bTitle = false;
            item.setTitle(sb.toString());
        } else if (bFormattedPrice) {
            bFormattedPrice = false;
            bLowestNewPrice = false;
            bOfferSummary = false;
            bItemAttributes = false;
            bItem = false;
            item.setPrice(sb.toString());
            onItem.accept(item);
        }
    }
}