package com.vpinfra.core.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URL;

/**
 * XML解析引擎.
 * 
 * @author 尹俊峰
 * @date 2016年8月22日
 * @since 2.1.1
 */
public final class XmlUtil {

    private static final LogUtil logger = LogUtil.newInstance(DateUtil.class);

    private static String encoding = "UTF-8";

    private XmlUtil() {
        
    }

    /**
     * 使用SaxReader读取XML文件.
     *
     * @param filePath 文件路径
     * @return Document
     * @throws DocumentException
     */
    public static Document createDoc(final String filePath) throws DocumentException {
        SAXReader xmlReader = new SAXReader();
        File file = new File(filePath);
        return xmlReader.read(file);
    }

    /**
     * 使用SaxReader读取XML文件.
     * 
     * @param url
     * @return Document
     * @throws DocumentException
     */
    public static Document createDoc(final URL url) throws DocumentException {
        SAXReader xmlReader = new SAXReader();
        return xmlReader.read(url);
    }

    /**
     * 将磁盘文件解析成Document对象.
     * 
     * @param file File：磁盘文件
     * @return Document
     * @throws DocumentException
     */
    public static Document createDoc(final File file) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(file);
    }

    /**
     * 使用InputStream读取XML文件.
     * 
     * @param is
     * @return Document
     * @throws DocumentException
     */
    public static Document createDoc(final InputStream is) throws DocumentException {
        SAXReader xmlReader = new SAXReader();
        return xmlReader.read(is);
    }

    /**
     * createXml( String StrOXML).
     * 
     * @param strOfXml 构造XML字符串
     * @param encoding
     * @return Document
     * @throws UnsupportedEncodingException
     * @throws DocumentException 
     */
    public static Document createDoc(final String strOfXml, final String encoding) 
            throws UnsupportedEncodingException, DocumentException {
        InputStream inputStream = new ByteArrayInputStream(strOfXml.getBytes(encoding));
        SAXReader saxReader = new SAXReader();

        return saxReader.read(inputStream);
    }

    /**
     * createXmlByStrInMem( String StrOXML).
     * 
     * @param strOXML 构造XML字符串
     * @return Document
     * @throws DocumentException
     */
    public static Document strToDoc(final String strOXML) throws DocumentException {
        return DocumentHelper.parseText(strOXML);
    }

    /**
     * 将Docment对象解析成脚本格式， 返回的是中文编码脚本.
     * 
     * @param doc Document
     * @return String
     */
    public static String doc2Str(final Document doc) {
        if (null == doc) {
            return null;
        }
        return doc.asXML();
    }

    /**
     * @param element
     * @return
     */
    public static Element getRecursionElement(final Element element) {
        if (null != element && !element.elements().isEmpty()) {
            return getRecursionElement(element);
        }
        return element;
    }

    /**
     * 使用xsd校验xml文件.
     * 
     * @param xsdFileName
     * @param doc
     * @return
     * @throws SAXException 
     * @throws ParserConfigurationException 
     */
    public static ErrorHandler validateXMLByXSD(final String xsdFileName, final Document doc) 
            throws ParserConfigurationException, SAXException {
        XMLErrorHandler errorHandler = new XMLErrorHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();

        factory.setValidating(true);
        factory.setNamespaceAware(true);

        SAXParser parser = factory.newSAXParser();

        Document xmlDocument = doc;
        parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                           "http://www.w3.org/2001/XMLSchema");
        parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", "file:" + xsdFileName);

        SAXValidator validator = new SAXValidator(parser.getXMLReader());

        validator.setErrorHandler(errorHandler);

        validator.validate(xmlDocument);

        if (errorHandler.getErrors().hasContent()) {
        	logger.warn("XSD validate failed !");

        } else {
        	logger.info("Good! XSD validate success!");
        }
        return errorHandler;
    }

    /**
     * 把Document写到指定路径的xml文件.
     * 
     * @param doc Document：要输出的Document对象
     * @param filePath String：输出的文件路径
     */
    public static void write(final Document doc, final String filePath) throws IOException {
        write(doc, new File(filePath));
    }

    /**
     * 把Document写到指定路径的xml文件.
     * 
     * @param doc
     * @param filePath
     * @param format
     * @throws IOException
     */
    public static void write(final Document doc, final String filePath, final OutputFormat format) throws IOException {
        write(doc, new File(filePath), format);
    }

    /**
     * 把Document写到指定路径的xml文件.
     * 
     * @param doc Document：要输出的Document对象
     * @param file File：输出的文件路径
     */
    public static void write(final Document doc, final File file) throws IOException {
        write(doc, new FileOutputStream(file));
    }

    /**
     * 把Document写到指定路径的xml文件.
     * 
     * @param doc
     * @param file
     * @param format
     * @throws IOException
     */
    public static void write(final Document doc, final File file, final OutputFormat format) throws IOException {
        write(doc, new FileOutputStream(file), format);
    }

    /**
     * 把Document写到输出流.
     * 
     * @param doc
     * @param outputStream
     * @throws IOException
     */
    public static void write(final Document doc, final OutputStream outputStream) throws IOException {
        if (doc == null) {
            return;
        }
        OutputFormat format = new OutputFormat();
        format.setEncoding(encoding);

        XMLWriter xmlWriter = new XMLWriter(outputStream, format);

        xmlWriter.write(doc);
        outputStream.close();
        xmlWriter.close();
    }

    /**
     * 把Document写到输出流.
     * 
     * @param doc
     * @param outputStream
     * @param format
     * @throws IOException
     */
    public static void
            write(final Document doc, final OutputStream outputStream, final OutputFormat format) throws IOException {
        if (doc == null) {
            return;
        }
        XMLWriter xmlWriter = new XMLWriter(outputStream, format);
        xmlWriter.write(doc);
        outputStream.close();
        xmlWriter.close();
    }

    /**
     * xml文件根据xsl样式表文件生成html文件.
     * 
     * @param xmlFile File
     * @param htmlFile File
     * @param xslFile File
     * @throws TransformerException 
     * @throws FileNotFoundException 
     */
    public static void xmlToHtml(final File xmlFile, final File htmlFile, final File xslFile) 
            throws FileNotFoundException, TransformerException{
        TransformerFactory tFactory = TransformerFactory.newInstance();

        Transformer transformer = tFactory.newTransformer(new StreamSource(new FileInputStream(xslFile)));

        transformer.transform(new StreamSource(new FileInputStream(xmlFile)),
                              new StreamResult(new FileOutputStream(htmlFile)));
    }

    /**
     * Document根据xsl样式表文件生成html文件.
     * 
     * @param doc Document
     * @param htmlFile File
     * @param xslFile File
     * @throws TransformerException 
     * @throws FileNotFoundException 
     */
    public static void xmlToHtml(final Document doc, final File htmlFile, final File xslFile) 
            throws FileNotFoundException, TransformerException {
        if (doc == null) {
            return;
        }

        String xmlStr = doc2Str(doc);
        InputStream inputStream = new ByteArrayInputStream(xmlStr.getBytes());

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource(new FileInputStream(xslFile)));

        transformer.transform(new StreamSource(inputStream), new StreamResult(new FileOutputStream(htmlFile)));
    }

}