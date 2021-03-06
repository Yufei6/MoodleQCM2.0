package sample;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;


public class BankStorage{
    private String name, path;
    private Set<Bank> list_bank;

    public BankStorage(){
        list_bank = new HashSet<Bank>();
        name = "BankStorage Defaut";
    }

    public BankStorage(String xml_path, SuperBank super_bank_0){
        path = xml_path;
        list_bank = new HashSet<Bank>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document= builder.parse(new File(xml_path));
            Element racine = document.getDocumentElement();
            Element name_0 = (Element)racine.getElementsByTagName("name");

            name = name_0.getTextContent();
            final NodeList list_bank_0 = racine.getElementsByTagName("bank_list");
            final int nb_banks_Elements = list_bank_0.getLength();
            for(int i = 0; i<nb_banks_Elements; i++) {
                final Element bank = (Element) list_bank_0.item(i);
                final Element path_bank = (Element) bank.getElementsByTagName("bank_path");
                Bank b = new Bank(path_bank.getTextContent(), super_bank_0);
                list_bank.add(b);
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
        }

    }



    public void save(String xml_path){
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(xml_path));

            final Element racine = document.createElement("BS");
            document.appendChild(racine);
            final Comment commentaire = document.createComment("List Bank");
            racine.appendChild(commentaire);
            final Element name_0 = document.createElement("name");
            final Element bank_name_list = document.createElement("bank_list");
            racine.appendChild(name_0);
            racine.appendChild(bank_name_list);
            name_0.appendChild(document.createTextNode(name));

            for (Bank bk:list_bank) {
                final Element bank = document.createElement("bank");
                bank_name_list.appendChild(bank);
                final Element bank_name = document.createElement("bank_name");
                final Element bank_path = document.createElement("bank_path");
                bank.appendChild(bank_name);
                bank.appendChild(bank_path);
                bank_name.appendChild(document.createTextNode(bk.getName()));
                bank_path.appendChild(document.createTextNode(bk.getPath()));
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

    public void changeName(String name_0){
        name = name_0;
    }


    public void addBank(Bank bk_0){
        list_bank.add(bk_0);
    }


    public void deleteBank(Bank bk_0){
        list_bank.remove(bk_0);
    }






}
