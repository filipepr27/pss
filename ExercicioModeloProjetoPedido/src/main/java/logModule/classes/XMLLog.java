package logModule.classes;

import classes.Pedido;
import logModule.interfaces.ILog;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLLog implements ILog {

    private static final String URL = "log.xml";
    @Override
    public void escrever(String nomeUsuario, String nomeOperacao, Pedido pedido) {
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        int codigoPedido = pedido.getCodigo();
        String nomeCliente = pedido.getCliente().getNome();
        String mensagemLog = String.format("NOME_USUARIO: %s; Data (DD/MM/YYYY): %s; Hora (HH:MM:SS): %s; " +
                        "codigo_pedido: %s; Nome de Operação: %s; Nome_Cliente: %s",nomeUsuario, data, hora,
                codigoPedido, nomeOperacao, nomeCliente);

        salvaXMLArquivo(nomeUsuario, mensagemLog);
    }

    private void salvaXMLArquivo(String nomeUsuario, String mensagemLog){
        try {
            File xmlFile = new File(URL);

            Document doc;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder docBuilder = factory.newDocumentBuilder();

            if(xmlFile.exists()){
                doc = docBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();
            } else {
                doc = docBuilder.newDocument();
                Element root = doc.createElement("Logs");
                doc.appendChild(root);
            }

            Element log = doc.createElement("Log");

            Element usuario = doc.createElement("NomeUsuario");
            usuario.appendChild(doc.createTextNode(nomeUsuario));
            log.appendChild(usuario);

            Element mensagem = doc.createElement("MensagemLog");
            mensagem.appendChild(doc.createTextNode(mensagemLog));
            log.appendChild(mensagem);

            doc.getDocumentElement().appendChild(log);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

            System.out.println("Arquivo XML criado com sucesso!");

        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Erro ao salvar log em XML: " + e.getMessage());
        } catch (IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
