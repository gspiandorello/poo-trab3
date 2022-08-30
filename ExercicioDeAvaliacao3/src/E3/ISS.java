package E3;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ISS implements Comparable<ISS>{
    private String indSN;
    private int item;
    private String descricaoItem;
    private double subItem;
    private String descricaoSubItem;
    private double valorISS;
    private Date data;
    private Date dataExtracao;

    public ISS(){

    }

    public ISS(String indSN, int item, String descricaoItem, double subItem, String descricaoSubItem, double valorISS,
               Date data, Date dataExtracao){
        this.indSN = indSN;
        this.item = item;
        this.descricaoItem = descricaoItem;
        this.subItem = subItem;
        this.descricaoSubItem = descricaoSubItem;
        this.valorISS = valorISS;
        this.data = data;
        this.dataExtracao = dataExtracao;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return "\n" + "ISS{" +
                "indSN='" + indSN + '\'' +
                ", item=" + item +
                ", descricaoItem='" + descricaoItem + '\'' +
                ", subItem=" + subItem +
                ", descricaoSubItem='" + descricaoSubItem + '\'' +
                ", valorISS=" + valorISS +
                ", data=" + getData() +
                ", dataUpload=" + formatter.format(dataExtracao) +
                '}';
    }

    public String toCsv() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return  "\"" + indSN + "\"" + ";" + "\"" + item + "\"" + ";" + "\"" + descricaoItem + "\"" + ";" + "\"" +
                subItem + "\"" + ";" + "\"" + descricaoSubItem + "\"" + ";" + "\"" + valorISS +"\"" +  ";"
                + "\"" + getData() + "\"" + ";" + "\"" + formatter.format(dataExtracao) + "\"" + ";";
    }

    @Override
    public int compareTo(ISS iss){
        return getData().compareTo(iss.getData());
    }

    public String getData(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return formatter.format(data);
    }

    public String getDataSemHora(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(data);
    }
}
