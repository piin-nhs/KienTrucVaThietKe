package iuh.fit.legacy;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class XmlSystem {

    public String processXml(String xmlRequest) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(
                    new ByteArrayInputStream(xmlRequest.getBytes(StandardCharsets.UTF_8))
            );

            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();
            String rootName = root.getNodeName();

            System.out.println("Hệ thống XML xử lý node: " + rootName);

            return """
                    <response>
                        <status>SUCCESS</status>
                        <message>XML processed for node: %s</message>
                    </response>
                    """.formatted(rootName);

        } catch (Exception e) {
            return """
                    <response>
                        <status>ERROR</status>
                        <message>Invalid XML format</message>
                    </response>
                    """;
        }
    }
}
