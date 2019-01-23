package sample;


public class Bank extends QuestionStorage{
    // private static int name_default_nomber = 0;
    private static int number_default = 0;



    public Bank(){
        super();
        //path = "./Bank_default"+number_default+".xml";
        // changeName("Qcm defaut" + name_default_nomber);
        // name_default_nomber++;
    }

    public Bank(String path, SuperBank super_bank){
        super(path, super_bank);
    }

    public void save(){
        super.save(true);
    }

    public void Export(String xml_path, String name_for_xml, boolean isBank){
        super.Export(xml_path, name_for_xml, true);
    }



}
