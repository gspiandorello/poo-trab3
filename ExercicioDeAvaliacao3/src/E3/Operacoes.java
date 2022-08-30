package E3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class Operacoes {

    private ArrayList<ISS> listaISS = new ArrayList<ISS>();
    private ArrayList<ISS> ultimaPesquisa = new ArrayList<ISS>();

    public void leArquivoTexto(String nomeArquivo) throws IOException, ParseException {
        Path path = Paths.get(nomeArquivo);
        BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset());
        String line = null;
        reader.readLine();
        while((line = reader.readLine()) != null){
            line = line.replace("\"", "'");
            String[] temp = line.split(",(?=(?:[^']*'[^']*')*[^']*$)");
            String indSN = temp[0];
            String item = temp[1];
            String descricaoItem = temp[2];
            String subItem = temp[3];
            String descricaoSubItem = temp[4];
            String valorISS = temp[5];
            String data = temp[6];
            String dataExtracao = temp[7];

            item = item.replace("'", "");
            descricaoItem = descricaoItem.replace("'", "");
            subItem = subItem.replace("'", "");
            descricaoSubItem = descricaoSubItem.replace("'", "");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = new Date();
            date = formatter.parse(data);
            Date dateExtracao = new Date();
            dateExtracao = formatter.parse(dataExtracao);


            ISS iss = new ISS(indSN, Integer.parseInt(item), descricaoItem, Double.parseDouble(subItem), descricaoSubItem,
            Double.parseDouble(valorISS), date, dateExtracao);
            listaISS.add(iss);
        }

        ultimaPesquisa = listaISS;
    }

    public ArrayList<ISS> consultaTodosDados(){
        return listaISS;
    }

    public ArrayList<ISS> pesquisaPorData(String data) {
        ArrayList<ISS> temporario = new ArrayList<ISS>();

        for (ISS iss : listaISS) {
            if (iss.getDataSemHora().equals(data)) {
                temporario.add(iss);
            }
        }
        ultimaPesquisa = temporario;
        return temporario;
    }

    public void salvaArquivoTexto(String nomeArquivo) throws IOException{
        Path path = Paths.get(nomeArquivo+".csv");
        PrintWriter pw = new PrintWriter(Files.newBufferedWriter(path, Charset.defaultCharset()));
        for (ISS iss : ultimaPesquisa) {
            pw.println(iss.toCsv());
        }
        pw.close();
    }

    public ArrayList<ISS> retornaListaCrescente(){
        Collections.sort(listaISS);
        ultimaPesquisa = listaISS;
        return listaISS;
    }

    public ArrayList<ISS> retornaListaDecrescente(){
        Collections.sort(listaISS);
        Collections.reverse(listaISS);
        ultimaPesquisa = listaISS;
        return listaISS;
    }
}