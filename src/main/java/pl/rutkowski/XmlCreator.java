package pl.rutkowski;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XmlCreator {

    private final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

    public void saveXmlFile(Document doc, String fileName) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        String fileNameXml = fileName.replace(".csv", ".xml");
        StreamResult file = new StreamResult(new File("/Users/adrian/Desktop/Xml/" + fileNameXml));
        try {
            transformer.transform(source, file);
        } catch (TransformerException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Document createXml(List<Book> bookList) {
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new RuntimeException(ex);
        }
        Document doc = dBuilder.newDocument();
        Element rootElement = doc.createElement("Books");
        doc.appendChild(rootElement);
        for (Book book : bookList) {
            rootElement.appendChild(createBookElement(doc, book.title(), book.author(), book.isbn13(), book.pages()));
        }
        return doc;
    }

    private Node createBookElement(Document doc, String title, String author, String isbn13, String pages) {
        Element book = doc.createElement("Book");
        book.appendChild(createBookElements(doc, "Title", title));
        book.appendChild(createBookElements(doc, "Author", author));
        book.appendChild(createBookElements(doc, "ISBN13", isbn13));
        book.appendChild(createBookElements(doc, "Pages", pages));
        return book;
    }

    private Node createBookElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }


}
