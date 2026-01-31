package iuh.fit.adapter;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import iuh.fit.legacy.XmlSystem;
import org.springframework.stereotype.Component;

@Component
public class XmlToJsonAdapter implements JsonService {

    private final XmlSystem xmlSystem;
    private final ObjectMapper jsonMapper;
    private final XmlMapper xmlMapper;

    public XmlToJsonAdapter() {
        this.xmlSystem = new XmlSystem();
        this.jsonMapper = new ObjectMapper();
        this.xmlMapper = new XmlMapper();
    }

    @Override
    public String sendJson(String jsonRequest) throws Exception {

        String xmlRequest = jsonToXml(jsonRequest);

        String xmlResponse = xmlSystem.processXml(xmlRequest);

        return xmlToJson(xmlResponse);
    }


    private String jsonToXml(String json) throws Exception {
        JsonNode node = jsonMapper.readTree(json);

        String rootName = node.fieldNames().next();
        JsonNode rootNode = node.get(rootName);

        return xmlMapper
                .writer()
                .withRootName(rootName)
                .writeValueAsString(rootNode);
    }


        private String xmlToJson(String xml) throws Exception {
        JsonNode node = xmlMapper.readTree(xml.getBytes());
        return jsonMapper.writeValueAsString(node);
    }
}
