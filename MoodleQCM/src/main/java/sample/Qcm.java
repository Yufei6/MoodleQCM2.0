package sample;


public class Qcm extends QuestionStorage {
    private String path;
    // private static int name_default_nomber = 0;

    public Qcm(){
        super();
        // changeName("Qcm defaut" + name_default_nomber);
        // name_default_nomber++;
    }

    public Qcm(String path, SuperBank super_bank){
        super(path, super_bank);
    }

    public void save(){
        super.save(false);
    }

    public void Export(String xml_path, String name_for_xml, boolean isBank){
        super.Export(xml_path, name_for_xml, false);
    }


    public String getPath() {
        return path;
    }
}
