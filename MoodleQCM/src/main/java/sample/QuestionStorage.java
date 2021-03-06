package sample;


import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;


public abstract class QuestionStorage{
    private Set<Question> list_question;
    private String name, path;
    public SuperBank super_bank;


    public QuestionStorage(){
        list_question = new HashSet<Question>();
//        name = "QuestionStorage defaut";
//        name should initialiser in Qcm and Bank
    }

    public QuestionStorage(String xml_path, SuperBank super_bank_0){

        path = xml_path;
        super_bank = super_bank_0;
        list_question = new HashSet<Question>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document= builder.parse(new File(xml_path));
            Element racine = document.getDocumentElement();
            Element name_0 = (Element)racine.getElementsByTagName("name").item(0);

            name = name_0.getTextContent();
            final NodeList list_Id = racine.getElementsByTagName("question_id");
            final int nbIDsElements = list_Id.getLength();
            for(int i =  0; i<nbIDsElements; i++) {
                final Element Id = (Element) list_Id.item(i);
                Question new_question = new Question(super_bank.find(Id.getTextContent()));
                list_question.add(new_question);
            }
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WrongQuestionTypeException e) {
            e.printStackTrace();
        }
    }







    public void save(boolean isBank){
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.newDocument();
            Element racine = null;
            Comment commentaire = null;
            if(isBank){
                racine = document.createElement("Bank");
                commentaire = document.createComment("Question Bank");
            }
            else{
                racine = document.createElement("Qcm");
                commentaire = document.createComment("Question Qcm");
            }
            document.appendChild(racine);
            racine.appendChild(commentaire);
            final Element name_0 = document.createElement("name");
            final Element question_id_list = document.createElement("question_id_list");
            racine.appendChild(name_0);
            racine.appendChild(question_id_list);
            name_0.appendChild(document.createTextNode(name));

            for (Question q:list_question) {
                final Element question_id = document.createElement("question_id");
                question_id_list.appendChild(question_id);
                question_id.appendChild(document.createTextNode(q.getID()+""));
            }
            Calendar c = Calendar.getInstance();
            final Element date = document.createElement("date");
            racine.appendChild(date);
            date.appendChild(document.createTextNode(c.get(Calendar.DATE)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR)));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult sortie = new StreamResult(new File(path));
            transformer.setOutputProperty(OutputKeys.VERSION,"1.0");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            transformer.transform(source, sortie);
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }
    }


 public void Import(String xml_path){
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        try {
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document= builder.parse(new File(xml_path));
//            Element racine = document.getDocumentElement();
//            Element name_0 = (Element)racine.getElementsByTagName("name");
//
//            name = name_0.getTextContent();
//            final NodeList list_Id = racine.getElementsByTagName("question_id_list");
//            final int nbIDsElements = list_Id.getLength();
//            for(int i =  0; i<nbIDsElements; i++) {
//                final Element Id = (Element) list_Id.item(i);
//                Question new_question = new Question(super_bank.find(Id.getTextContent()));
//                list_question.add(new_question);
//            }
//        } catch (ParserConfigurationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }catch (SAXException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (WrongQuestionTypeException e) {
//            e.printStackTrace();
//        }
    }




    public void Export(String xml_path, String name_for_xml, boolean isBank){
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(xml_path));
            Element racine = null;
            Comment commentaire = null;
            if(isBank){
                racine = document.createElement("Bank");
                commentaire = document.createComment("Question Bank");
            }
            else{
                racine = document.createElement("Qcm");
                commentaire = document.createComment("Question Qcm");
            }
            document.appendChild(racine);
            racine.appendChild(commentaire);
            final Element name_0 = document.createElement(name_for_xml);
            final Element question_list = document.createElement("question_list");
            racine.appendChild(name_0);
            racine.appendChild(question_list);
            name_0.appendChild(document.createTextNode(name));

            for (Question q:list_question) {
                final Element question = document.createElement("question");
                question_list.appendChild(q.getQuestionXml());
//                question.appendChild(document.createTextNode(q.getQuestionXml()));           //Question getter pour Exporter
            }
            Calendar c = Calendar.getInstance();
            final Element date = document.createElement("date");
            racine.appendChild(date);
            date.appendChild(document.createTextNode(c.get(Calendar.DATE)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR)));

        }
        catch (final ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch(final SAXException e) {
            e.printStackTrace();
        }
        catch(final IOException e) {
            e.printStackTrace();
        }

    }

    public void addQuestion(Question question){
        list_question.add(question);
    }

    public void deleteQuestion(Question question){
        list_question.remove(question);
    }


    public void changeName(String name_0){
        name = name_0;
    }

    public String getName(){
        return name;
    }


    public String getPath(){
        return path;
    }



}
