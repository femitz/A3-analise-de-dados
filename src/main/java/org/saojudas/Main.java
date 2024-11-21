package org.saojudas;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import static javax.swing.JOptionPane.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.sql.*;

public class Main {

    /*
     * --------------------------------------------------------------------------
     * Fase 1
     * Data : 03 de outubro de 2024
     * Objetivo : ETC - NÃºmero de Habitantes por Estado
     * Fase 1 : Tratamento do arquivo de entrada: C:\\D/Habitantes_UF_01.csv
     * Tratamento do arquivo de saida: C:\\D/Habitantes_UF_02.csv
     * --------------------------------------------------------------------------
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {

        String basePath = System.getProperty("user.dir");
        String inputFilePath = basePath + File.separator + "Habitantes_UF_01.csv";
        String outputFilePath = basePath + File.separator + "Habitantes_UF_02.csv";

        FileInputStream instream = new FileInputStream(inputFilePath);
        InputStreamReader reader = new InputStreamReader(instream);
        BufferedReader br = new BufferedReader(reader);

        FileWriter fileWriter = new FileWriter(outputFilePath);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        //*----------------- DefiniÃ§Ã£o da linha do arquivo de entrada  -------------------------------
        String linha;
        linha = br.readLine();

        //*----------------- DefiniÃ§Ã£o das variaveis de controle de lidos e gravados -----------------
        int Lidos = 0;
        int Gravados = 0;

        //*----------------- Leitura de todos os registros (linhas) do arquivo de entrada ------------
        while (linha != null) {
            Lidos = Lidos + 1;
            String[] campos = linha.split(";");

            if ((campos[0].equals("Acre"))
                    || (campos[0].equals("Alagoas"))
                    || (campos[0].equals("Amazonas"))
                    || (campos[0].equals("Amapa"))
                    || (campos[0].equals("Bahia"))
                    || (campos[0].equals("Distrito Federal"))
                    || (campos[0].equals("Ceara"))
                    || (campos[0].equals("Espirito Santo"))
                    || (campos[0].equals("Goias"))
                    || (campos[0].equals("Maranhao"))
                    || (campos[0].equals("Mato Grosso"))
                    || (campos[0].equals("Mato Grosso do Sul"))
                    || (campos[0].equals("Minas Gerais"))
                    || (campos[0].equals("Para"))
                    || (campos[0].equals("Paraiba"))
                    || (campos[0].equals("Parana"))
                    || (campos[0].equals("Pernambuco"))
                    || (campos[0].equals("Piaui"))
                    || (campos[0].equals("Rio de Janeiro"))
                    || (campos[0].equals("Rio Grande do Norte"))
                    || (campos[0].equals("Rio Grande do Sul"))
                    || (campos[0].equals("Rondonia"))
                    || (campos[0].equals("Roraima"))
                    || (campos[0].equals("Santa Catarina"))
                    || (campos[0].equals("Sao Paulo"))
                    || (campos[0].equals("Sergipe"))
                    || (campos[0].equals("Tocantins"))) {

                bufferedWriter.write(campos[0] + ";" + campos[1]);
                Gravados = Gravados + 1;
                bufferedWriter.newLine();

            }
            linha = br.readLine();
        }
        //*----------------- Encerramento do tratamento do arquivo -----------
        bufferedWriter.close();
        fileWriter.close();

        //*------------------------ Exibe os controles  ----------------------
        System.out.println("--------- Fase 1 ---------------");
        System.out.println("Registros lidos    = " + Lidos);
        System.out.println("Registros gravados = " + Gravados);

        /*
    --------------------------------------------------------------------------
    Fase 2
    Data        :   03 de outubro de 2024
    Objetivo    :   ETC - ClassificaÃ§Ã£o alfabÃ©tica pelo nome do Estado
    Fase 1      :   Tratamento do arquivo de entrada: C:\\D/Habitantes_UF_02.csv
                    Tratamento do arquivo de saida: C:\\D/Habitantes_UF_03.csv
    --------------------------------------------------------------------------
         */
        int Gravados2 = 0;

        String str2;
        ArrayList<String> nomes2 = new ArrayList<String>();

        BufferedReader in2 = new BufferedReader(new FileReader(basePath + File.separator + "Habitantes_UF_02.csv"));

        while ((str2 = in2.readLine()) != null) {
            nomes2.add(str2);
        }
        in2.close();

        Collections.sort(nomes2);

        BufferedWriter out2 = new BufferedWriter(new FileWriter(basePath + File.separator + "Habitantes_UF_03.csv"));        
        for (int i = 0; i < nomes2.size(); i++) {
            out2.write(nomes2.get(i));
            Gravados2 = Gravados2 + 1;
            out2.newLine();
        }
        out2.close();

        System.out.println("--------- Fase 2 ---------------");
        System.out.println("Registros gravados = " + Gravados2);

        /*
    --------------------------------------------------------------------------
    Fase 3
    Data        :   03 de outubro de 2024
    Objetivo    :   ETC - TransformaÃ§Ã£o de nome de estado para UF
    Fase 1      :   Tratamento do arquivo de entrada: C:\\D/Habitantes_UF_03.csv
                    Tratamento do arquivo de saida: C:\\D/Habitantes_UF_04.csv
    --------------------------------------------------------------------------
         */
        FileInputStream instream3 = new FileInputStream(basePath + File.separator + "Habitantes_UF_03.csv");
        FileWriter fileWriter3 = new FileWriter(new File(basePath + File.separator + "Habitantes_UF_04.csv"));

        InputStreamReader reader3 = new InputStreamReader(instream3);
        BufferedReader br3 = new BufferedReader(reader3);

        BufferedWriter bufferedWriter3 = new BufferedWriter(fileWriter3);

        String linha3;
        linha3 = br3.readLine();

        int Lidos3 = 0;
        int Gravados3 = 0;

        String[] UF_Estado = new String[27];

        UF_Estado[0] = "AC Acre";
        UF_Estado[1] = "AL Alagoas";
        UF_Estado[2] = "AM Amazonas";
        UF_Estado[3] = "AP Amapa";
        UF_Estado[4] = "BA Bahia";
        UF_Estado[5] = "DF Distrito Federal";
        UF_Estado[6] = "CE Ceara";
        UF_Estado[7] = "ES Espirito Santo";
        UF_Estado[8] = "GO Goias";
        UF_Estado[9] = "MA Maranhao";
        UF_Estado[10] = "MT Mato Grosso";
        UF_Estado[11] = "MS Mato Grosso do Sul";
        UF_Estado[12] = "MG Minas Gerais";
        UF_Estado[13] = "PA Para";
        UF_Estado[14] = "PB Paraiba";
        UF_Estado[15] = "PR Parana";
        UF_Estado[16] = "PE Pernambuco";
        UF_Estado[17] = "PI Piaui";
        UF_Estado[18] = "RJ Rio de Janeiro";
        UF_Estado[19] = "RN Rio Grande do Norte";
        UF_Estado[20] = "RS Rio Grande do Sul";
        UF_Estado[21] = "RO Rondonia";
        UF_Estado[22] = "RR Roraima";
        UF_Estado[23] = "SC Santa Catarina";
        UF_Estado[24] = "SP Sao Paulo";
        UF_Estado[25] = "SE Sergipe";
        UF_Estado[26] = "TO Tocantins";

        while (linha3 != null) {
            Lidos3 = Lidos3 + 1;
            String[] campos = linha3.split(";");
            for (int i = 0; i < 27; i++) {
                if (campos[0].equals(UF_Estado[i].substring(3, UF_Estado[i].length()))) {
                    campos[0] = UF_Estado[i].substring(0, 2);
                    bufferedWriter3.write(campos[0] + ";" + campos[1]);
                    Gravados3 = Gravados3 + 1;
                    bufferedWriter3.newLine();
                }
            }
            linha3 = br3.readLine();
        }
        Gravados3 = Gravados3 + 1;
        bufferedWriter3.close();
        fileWriter3.close();

        System.out.println("--------- Fase 3 ---------------");
        System.out.println("Registros lidos    = " + Lidos3);
        System.out.println("Registros gravados = " + Gravados3);

        /*
    --------------------------------------------------------------------------
    Fase 4
    Data        :   03 de outubro de 2024
    Objetivo    :   Totalizar os habitantes do paÃ­s
    Fase 1      :   Tratamento do arquivo de entrada: C:\\D/Habitantes_UF_04.csv
                    Tratamento do arquivo de saida: C:\\D/Habitantes_UF_05.csv
    --------------------------------------------------------------------------
         */
        FileInputStream instream4 = new FileInputStream(basePath + File.separator + "Habitantes_UF_04.csv");
        FileWriter fileWriter4 = new FileWriter(new File(basePath + File.separator + "Habitantes_UF_05.csv"));

        InputStreamReader reader4 = new InputStreamReader(instream4);
        BufferedReader br4 = new BufferedReader(reader4);

        BufferedWriter bufferedWriter4 = new BufferedWriter(fileWriter4);

        String linha4;
        linha4 = br4.readLine();

        int Lidos4 = 0;
        int Gravados4 = 0;
        int Total_Habitantes = 0;

        while (linha4 != null) {
            Lidos4 = Lidos4 + 1;
            String[] campos = linha4.split(";");
            String result = campos[1].replaceAll("\\p{Punct}", "");
            Total_Habitantes = Total_Habitantes + Integer.parseInt(result);
            bufferedWriter4.write(campos[0] + ";" + result);
            Gravados4 = Gravados4 + 1;
            bufferedWriter4.newLine();
            linha4 = br4.readLine();
        }

        bufferedWriter4.write("ZZ" + ";" + Total_Habitantes);
        Gravados4 = Gravados4 + 1;
        bufferedWriter4.newLine();

        bufferedWriter4.close();
        fileWriter4.close();

        System.out.println("--------- Fase 4 ---------------");
        System.out.println("Registros lidos    = " + Lidos4);
        System.out.println("Registros gravados = " + Gravados4);

        /*
    --------------------------------------------------------------------------
    Fase 5
    Data        :   03 de outubro de 2024
    Objetivo    :   ETC - ClassificaÃ§Ã£o alfabÃ©tica pela UF (Descendente)
    Fase 1      :   Tratamento do arquivo de entrada: C:\\D/Habitantes_UF_05.csv
                    Tratamento do arquivo de saida: C:\\D/Habitantes_UF_06.csv
    --------------------------------------------------------------------------
         */
        int Gravados5 = 0;

        String str5;
        ArrayList<String> nomes5 = new ArrayList<String>();

        BufferedReader in5 = new BufferedReader(new FileReader(basePath + File.separator + "Habitantes_UF_05.csv"));

        while ((str5 = in5.readLine()) != null) {
            nomes5.add(str5);
        }
        in5.close();

        Collections.sort(nomes5, Collections.reverseOrder());

        BufferedWriter out6 = new BufferedWriter(new FileWriter(basePath + File.separator + "Habitantes_UF_06.csv"));
        for (int i = 0; i < nomes5.size(); i++) {
            out6.write(nomes5.get(i));
            Gravados5 = Gravados5 + 1;
            out6.newLine();
        }
        out6.close();

        System.out.println("--------- Fase 5 ---------------");
        System.out.println("Registros gravados = " + Gravados5);

        /*
    --------------------------------------------------------------------------
    Fase 6
    Data        :   03 de outubro de 2024
    Objetivo    :   Calcular o percentual de habitantes da UF versus PaÃ­s
    Fase 1      :   Tratamento do arquivo de entrada: C:\\D/Habitantes_UF_06.csv
                    Tratamento do arquivo de saida: C:\\D/Habitantes_UF_07.csv
    --------------------------------------------------------------------------
         */
        FileInputStream instream6 = new FileInputStream(basePath + File.separator + "Habitantes_UF_06.csv");
        FileWriter fileWriter6 = new FileWriter(new File(basePath + File.separator + "Habitantes_UF_07.csv"));

        InputStreamReader reader6 = new InputStreamReader(instream6);
        BufferedReader br6 = new BufferedReader(reader6);

        BufferedWriter bufferedWriter6 = new BufferedWriter(fileWriter6);

        int Lidos6 = 0;
        int Gravados6 = 0;
        double Percentual = 0.0;

        String linha6;
        linha6 = br6.readLine();

        String[] campos = linha6.split(";");
        Total_Habitantes = Integer.parseInt(campos[1]);
        linha6 = br6.readLine();

        while (linha6 != null) {
            Lidos6 = Lidos6 + 1;
            campos = linha6.split(";");
            Percentual = Double.parseDouble(campos[1]) / Total_Habitantes;
            bufferedWriter6.write(campos[0] + ";" + campos[1] + ";" + Percentual);
            Gravados6 = Gravados6 + 1;
            bufferedWriter6.newLine();
            linha6 = br6.readLine();
        }

        bufferedWriter6.close();
        fileWriter6.close();

        System.out.println("--------- Fase 6 ---------------");
        System.out.println("Registros lidos    = " + Lidos6);
        System.out.println("Registros gravados = " + Gravados6);

        /*
    --------------------------------------------------------------------------
    Fase 7
    Data        :   03 de outubro de 2024
    Objetivo    :   ETC - ClassificaÃ§Ã£o alfabÃ©tica pela UF
    Fase 1      :   Tratamento do arquivo de entrada: C:\\D/Habitantes_UF_07.csv
                    Tratamento do arquivo de saida: C:\\D/Habitantes_UF_08.csv
    --------------------------------------------------------------------------
         */
        int Gravados7 = 0;

        String str7;
        ArrayList<String> nomes7 = new ArrayList<String>();

        BufferedReader in7 = new BufferedReader(new FileReader(basePath + File.separator + "Habitantes_UF_07.csv"));

        while ((str7 = in7.readLine()) != null) {
            nomes7.add(str7);
        }
        in7.close();

        Collections.sort(nomes7);

        BufferedWriter out8 = new BufferedWriter(new FileWriter(basePath + File.separator + "Habitantes_UF_08.csv"));
        for (int i = 0; i < nomes7.size(); i++) {
            out8.write(nomes7.get(i));
            Gravados7 = Gravados7 + 1;
            out8.newLine();
        }
        out8.close();

        System.out.println("--------- Fase 7 ---------------");
        System.out.println("Registros gravados = " + Gravados7);

        /*
    --------------------------------------------------------------------------
    Fase 8
    Data        :   21 de outubro de 2024
    Objetivo    :   Calcular o percentual de habitantes da UF versus PaÃ­s
    Fase 1      :   Tratamento do arquivo de entrada: C:\\D/Habitantes_UF_06.csv
                    Tratamento do arquivo de saida: C:\\D/Habitantes_UF_07.csv
    --------------------------------------------------------------------------
         */
        FileInputStream instream8 = new FileInputStream(basePath + File.separator + "Matriculados_UF_01.csv");
        FileWriter fileWriter8 = new FileWriter(new File(basePath + File.separator + "Matriculados_UF_02.csv"));

        InputStreamReader reader8 = new InputStreamReader(instream8);
        BufferedReader br8 = new BufferedReader(reader8);

        BufferedWriter bufferedWriter8 = new BufferedWriter(fileWriter8);

        //*----------------- Defini ao da linha do arquivo de entrada  -----------------
        String linha8;
        linha8 = br8.readLine();

        String UF = null;
        String Numeros = null;

        //*----------------- Defini ao das variaveis de controle de lidos e gravados -----------------
        int Lidos8 = 0;
        int Gravados8 = 0;

        //*----------------- Leitura de todos os registros (linhas) do arquivo de entrada ------------
        while (linha8 != null) {
            Lidos8 = Lidos8 + 1;
            if ((linha8.substring(0, linha8.length()).equals("ACRE;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("ALAGOAS;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("AMAZONAS;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("AMAPA;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("BAHIA;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("DISTRITO FEDERAL;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("CEARA;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("ESPIRITO SANTO;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("GOIAS;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("MARANHAO;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("MATO GROSSO;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("MATO GROSSO DO SUL;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("MINAS GERAIS;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("PARA;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("PARAIBA;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("PARANA;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("PERNAMBUCO;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("PIAUI;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("RIO DE JANEIRO;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("RIO GRANDE DO NORTE;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("RIO GRANDE DO SUL;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("RONDONIA;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("RORAIMA;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("SANTA CATARINA;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("SAO PAULO;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("SERGIPE;;;;;;;;;;;;"))
                    || (linha8.substring(0, linha8.length()).equals("TOCANTINS;;;;;;;;;;;;"))) {

                //*----------------- Leitura de blocos de 5 em 5 linhas -----------------                
                for (int i = 0; i < 6; i++) {
                    if (i == 0 || i == 5) {
                        if (i == 0) //*----------------- Para a primeira linha grava a UF (Estado)-----------                
                        {
                            UF = linha8;
                        } else if (i == 5) {
                            //*----------------- Para a quinta linha grava os valores -----------                
                            Numeros = linha8; // Estadual + Municipal ; Numeros
                            bufferedWriter8.write(UF + ";" + Numeros);
                            Gravados8 = Gravados8 + 1;
                            bufferedWriter8.newLine();
                        }
                    }
                    linha8 = br8.readLine();
                }
            }
            linha8 = br8.readLine();
        }
        //*----------------- Encerramento do tratamento do arquivo -----------
        bufferedWriter8.close();
        fileWriter8.close();
        //*------------------------ Exibe os controles  -----------

        System.out.println("--------- Fase 8 ---------------");
        System.out.println("Registros lidos    = " + Lidos8);
        System.out.println("Registros gravados = " + Gravados8);

        /*
    --------------------------------------------------------------------------
    Fase 9
    Data        :   4 de novembro de 2024
    Objetivo    :   ETC - Elimina os pontos e totaliza os Matriculados (Anexo I)
    Fase 9      :   Tratamento do arquivo de entrada: C:\\D/Matriculados_UF_02.csv
                    Tratamento do arquivo de saida: C:\\D/Matriculados_UF_03.csv
    --------------------------------------------------------------------------
         */
        int Lidos9 = 0;
        int Gravados9 = 0;
        FileInputStream instream9 = new FileInputStream(basePath + File.separator + "Matriculados_UF_02.csv");
        FileWriter fileWriter9 = new FileWriter(new File(basePath + File.separator + "Matriculados_UF_03.csv"));

        InputStreamReader reader9 = new InputStreamReader(instream9);
        BufferedReader br9 = new BufferedReader(reader9);

        BufferedWriter bufferedWriter9 = new BufferedWriter(fileWriter9);

        String linha9;
        linha9 = br9.readLine();
        int Matriculados = 0;
        int j = 0;

        while (linha9 != null) {
            String[] campos9 = linha9.split(";");

            for (j = 15; j < 25; j++) {
                String result = campos9[j].replaceAll("\\p{Punct}", "");
                Matriculados = Matriculados + Integer.parseInt(result);
            }

            Lidos9 = Lidos9 + 1;
            linha9 = br9.readLine();
            bufferedWriter9.write(campos9[0] + ";" + Matriculados);
            Gravados9 = Gravados9 + 1;
            Matriculados = 0;
            bufferedWriter9.newLine();

        }
        bufferedWriter9.close();
        fileWriter9.close();

        System.out.println("--------- Fase 9 ---------------");
        System.out.println("Registros lidos    = " + Lidos9);
        System.out.println("Registros gravados = " + Gravados9);

        /*
    --------------------------------------------------------------------------
    Fase 10
    Data        :   4 de novembro de 2024
    Objetivo    :   ETC - Classificar o arquivo pela UF (Anexo I)
    Fase 10     :   Tratamento do arquivo de entrada: C:\\D/Matriculados_UF_03.csv
                    Tratamento do arquivo de saida: C:\\D/Matriculados_UF_04.csv
    --------------------------------------------------------------------------
         */
        String str;
        ArrayList<String> nomes = new ArrayList<String>();

        BufferedReader in = new BufferedReader(new FileReader(basePath + File.separator + "Matriculados_UF_03.csv"));

        int Gravados10 = 0;
        
        while ((str = in.readLine()) != null) {
            nomes.add(str);
        }
        in.close();
        
        Collections.sort(nomes);
        
        BufferedWriter out = new BufferedWriter(new FileWriter(basePath + File.separator + "Matriculados_UF_04.csv"));
        for (int i = 0; i < nomes.size(); i++) {
            out.write(nomes.get(i));
            Gravados10 = Gravados10 + 1;
            out.newLine();
        }
        out.close();

        System.out.println("--------- Fase 10 ---------------");
        System.out.println("Registros gravados = " + Gravados10);

        /*
    --------------------------------------------------------------------------
    Fase 11
    Data        :   4 de novembro de 2024
    Objetivo    :   ETC - Filtar os nomes de cidades com nome de estados (Anexo I)
    Fase 11     :   Tratamento do arquivo de entrada: C:\\D/Matriculados_UF_04.csv
                    Tratamento do arquivo de saida: C:\\D/Matriculados_UF_05.csv
    --------------------------------------------------------------------------
         */
        FileInputStream instream11 = new FileInputStream(basePath + File.separator + "Matriculados_UF_04.csv");
        FileWriter fileWriter11 = new FileWriter(new File(basePath + File.separator + "Matriculados_UF_05.csv"));

        InputStreamReader reader11 = new InputStreamReader(instream11);
        BufferedReader br11 = new BufferedReader(reader11);

        BufferedWriter bufferedWriter11 = new BufferedWriter(fileWriter11);

        String linha11;
        linha11 = br11.readLine();

        int Lidos11 = 0;
        int Gravados11 = 0;

        String Estado_anterior = null;
        int Valor_anterior = 0;

        while (linha11 != null) {
            String[] campos11 = linha11.split(";");
            if (campos11[0].equals(Estado_anterior)) {
                if (Integer.parseInt(campos11[1]) > Valor_anterior) {
                    bufferedWriter11.write(campos11[0] + ";" + campos11[1]);
                    Gravados11 = Gravados11 + 1;
                    bufferedWriter11.newLine();
                    Lidos11 = Lidos11 + 1;
                    linha11 = br11.readLine();
                } else {
                    Estado_anterior = campos11[0];
                    Valor_anterior = Integer.parseInt(campos11[1]);
                    Lidos11 = Lidos11 + 1;
                    linha11 = br11.readLine();
                }
            } else {
                bufferedWriter11.write(campos11[0] + ";" + campos11[1]);
                Gravados11 = Gravados11 + 1;
                bufferedWriter11.newLine();
                Estado_anterior = campos11[0];
                Valor_anterior = Integer.parseInt(campos11[1]);
                Lidos11 = Lidos11 + 1;
                linha11 = br11.readLine();
            }
        }
        bufferedWriter11.close();
        fileWriter11.close();

        System.out.println("--------- Fase 11 ---------------");
        System.out.println("Registros lidos    = " + Lidos11);
        System.out.println("Registros gravados = " + Gravados11);

        /*
    --------------------------------------------------------------------------
    Fase 12
    Data        :   4 de novembro de 2024
    Objetivo    :   ETC - Classifica por estado - descendente (Anexo I)
    Fase 12     :   Tratamento do arquivo de entrada: C:\\D/Matriculados_UF_05.csv
                    Tratamento do arquivo de saida: C:\\D/Matriculados_UF_06.csv
    --------------------------------------------------------------------------
         */
        int Gravados12 = 0;

        String str12;
        ArrayList<String> nomes12 = new ArrayList<String>();

        BufferedReader in12 = new BufferedReader(new FileReader(basePath + File.separator + "Matriculados_UF_05.csv"));

        while ((str12 = in12.readLine()) != null) {
            nomes12.add(str12);
        }
        in12.close();

        Collections.sort(nomes12, Collections.reverseOrder());

        BufferedWriter out12 = new BufferedWriter(new FileWriter(basePath + File.separator + "Matriculados_UF_06.csv"));
        for (int i = 0; i < nomes12.size(); i++) {
            out12.write(nomes12.get(i));
            Gravados12 = Gravados12 + 1;
            out12.newLine();
        }
        out12.close();

        System.out.println("--------- Fase 12 ---------------");
        System.out.println("Registros gravados = " + Gravados12);

        /*
    --------------------------------------------------------------------------
    Fase 13
    Data        :   4 de novembro de 2024
    Objetivo    :   ETC - Filtra os nomes de cidades versus estados (Anexo I)
    Fase 13     :   Tratamento do arquivo de entrada: C:\\D/Matriculados_UF_06.csv
                    Tratamento do arquivo de saida: C:\\D/Matriculados_UF_07.csv
    --------------------------------------------------------------------------
         */
        FileInputStream instream13 = new FileInputStream(basePath + File.separator + "Matriculados_UF_06.csv");
        FileWriter fileWriter13 = new FileWriter(new File(basePath + File.separator + "Matriculados_UF_07.csv"));
        
        InputStreamReader reader13 = new InputStreamReader(instream13);
        BufferedReader br13 = new BufferedReader(reader13);
        
        BufferedWriter bufferedWriter13 = new BufferedWriter(fileWriter13);

        String linha13;
        linha13 = br13.readLine();

        int Lidos13 = 0;
        int Gravados13 = 0;

        String Estado_anterior13 = null;
        int Valor_anterior13 = 0;

        while (linha13 != null) {
            String[] campos13 = linha13.split(";");
            if (campos13[0].equals(Estado_anterior13)) {
                if (Integer.parseInt(campos13[1]) > Valor_anterior13) {
                    bufferedWriter13.write(campos13[0] + ";" + campos13[1]);
                    Gravados13 = Gravados13 + 1;
                    bufferedWriter13.newLine();
                    Lidos13 = Lidos13 + 1;
                    linha13 = br13.readLine();
                } else {
                    Estado_anterior13 = campos13[0];
                    Valor_anterior13 = Integer.parseInt(campos13[1]);
                    Lidos13 = Lidos13 + 1;
                    linha13 = br13.readLine();
                }
            } else {
                bufferedWriter13.write(campos13[0] + ";" + campos13[1]);
                Gravados13 = Gravados13 + 1;
                bufferedWriter13.newLine();
                Estado_anterior13 = campos13[0];
                Valor_anterior13 = Integer.parseInt(campos13[1]);
                Lidos13 = Lidos13 + 1;
                linha13 = br13.readLine();
            }
        }
        bufferedWriter13.close();
        fileWriter13.close();

        System.out.println("--------- Fase 13 ---------------");
        System.out.println("Registros lidos    = " + Lidos13);
        System.out.println("Registros gravados = " + Gravados13);

        /*
    --------------------------------------------------------------------------
    Fase 14
    Data        :   05 de novembro de 2024
    Objetivo    :   ETC - Transformação de nome de estado para UF (Anexo I)
    Fase 14     :   Tratamento do arquivo de entrada: C:\\D/Matriculados_UF_07.csv
                    Tratamento do arquivo de saida: C:\\D/Matriculados_UF_08.csv
    --------------------------------------------------------------------------
         */
        FileInputStream instream14 = new FileInputStream(basePath + File.separator + "Matriculados_UF_07.csv");
        FileWriter fileWriter14 = new FileWriter(new File(basePath + File.separator + "Matriculados_UF_08.csv"));

        InputStreamReader reader14 = new InputStreamReader(instream14);
        BufferedReader br14 = new BufferedReader(reader14);

        BufferedWriter bufferedWriter14 = new BufferedWriter(fileWriter14);

        String linha14;
        linha14 = br14.readLine();

        int Lidos14 = 0;
        int Gravados14 = 0;

        String[] UF_Estado14 = new String[27];

        UF_Estado14[0] = "AC ACRE";
        UF_Estado14[1] = "AL ALAGOAS";
        UF_Estado14[2] = "AM AMAZONAS";
        UF_Estado14[3] = "AP AMAPA";
        UF_Estado14[4] = "BA BAHIA";
        UF_Estado14[5] = "DF DISTRITO FEDERAL";
        UF_Estado14[6] = "CE CEARA";
        UF_Estado14[7] = "ES ESPIRITO SANTO";
        UF_Estado14[8] = "GO GOIAS";
        UF_Estado14[9] = "MA MARANHAO";
        UF_Estado14[10] = "MT MATO GROSSO";
        UF_Estado14[11] = "MS MATO GROSSO DO SUL";
        UF_Estado14[12] = "MG MINAS GERAIS";
        UF_Estado14[13] = "PA PARA";
        UF_Estado14[14] = "PB PARAIBA";
        UF_Estado14[15] = "PR PARANA";
        UF_Estado14[16] = "PE PERNAMBUCO";
        UF_Estado14[17] = "PI PIAUI";
        UF_Estado14[18] = "RJ RIO DE JANEIRO";
        UF_Estado14[19] = "RN RIO GRANDE DO NORTE";
        UF_Estado14[20] = "RS RIO GRANDE DO SUL";
        UF_Estado14[21] = "RO RONDONIA";
        UF_Estado14[22] = "RR RORAIMA";
        UF_Estado14[23] = "SC SANTA CATARINA";
        UF_Estado14[24] = "SP SAO PAULO";
        UF_Estado14[25] = "SE SERGIPE";
        UF_Estado14[26] = "TO TOCANTINS";

        while (linha14 != null) {
            Lidos14 = Lidos14 + 1;
            String[] campos14 = linha14.split(";");
            for (int i = 0; i < 27; i++) {
                if (campos14[0].equals(UF_Estado14[i].substring(3, UF_Estado14[i].length()))) {
                    campos14[0] = UF_Estado14[i].substring(0, 2);
                    bufferedWriter14.write(campos14[0] + ";" + campos14[1]);
                    Gravados14 = Gravados14 + 1;
                    bufferedWriter14.newLine();
                }
            }
            linha14 = br14.readLine();
        }

        Gravados14 = Gravados14 + 1;
        bufferedWriter14.close();
        fileWriter14.close();

        System.out.println("--------- Fase 14 ---------------");
        System.out.println("Registros lidos    = " + Lidos14);
        System.out.println("Registros gravados = " + Gravados14);

        /*
    --------------------------------------------------------------------------
    Fase 15
    Data        :   5 de novembro de 2024
    Objetivo    :   ETC - Classifica por UF (Anexo I)
    Fase 15     :   Tratamento do arquivo de entrada: C:\\D/Matriculados_UF_08.csv
                    Tratamento do arquivo de saida: C:\\D/Matriculados_UF_09.csv
    --------------------------------------------------------------------------
         */
        int Gravados15 = 0;

        String str15;
        ArrayList<String> nomes15 = new ArrayList<String>();

        BufferedReader in15 = new BufferedReader(new FileReader(basePath + File.separator + "Matriculados_UF_08.csv"));

        while ((str15 = in15.readLine()) != null) {
            nomes15.add(str15);
        }
        in15.close();

        Collections.sort(nomes15);

        BufferedWriter out15 = new BufferedWriter(new FileWriter(basePath + File.separator + "Matriculados_UF_09.csv"));
        for (int i = 0; i < nomes15.size(); i++) {
            out15.write(nomes15.get(i));
            Gravados15 = Gravados15 + 1;
            out15.newLine();
        }
        out15.close();

        System.out.println("--------- Fase 15 ---------------");
        System.out.println("Registros gravados = " + Gravados15);

        /*
    --------------------------------------------------------------------------
    Fase 16
    Data        :   5 de novembro de 2024
    Objetivo    :   ETC - Merge Habitantes e Matriculados por UF (Total)
    Fase 16     :   Tratamento do arquivo de entrada: C:\\D/Matriculados_UF_09.csv
                                                      C:\\D/Habitantes_UF_08.csv
                    Tratamento do arquivo de saida: C:\\D/Total_UF_01.csv
    --------------------------------------------------------------------------
         */
        FileInputStream instream16A = new FileInputStream(basePath + File.separator + "Matriculados_UF_09.csv");
        FileInputStream instream16B = new FileInputStream(basePath + File.separator + "Habitantes_UF_08.csv");
        FileWriter fileWriter16 = new FileWriter(new File(basePath + File.separator + "Total_UF_01.csv"));

        InputStreamReader reader16A = new InputStreamReader(instream16A);
        InputStreamReader reader16B = new InputStreamReader(instream16B);

        BufferedReader br16A = new BufferedReader(reader16A);
        BufferedReader br16B = new BufferedReader(reader16B);

        BufferedWriter bufferedWriter16 = new BufferedWriter(fileWriter16);

        String linha16A;
        String linha16B;
        linha16A = br16A.readLine();
        linha16B = br16B.readLine();

        int Lidos16A = 0;
        int Lidos16B = 0;
        int Gravados16 = 0;

        int Total_Habitantes16 = 0;
        int Total_Matriculados16 = 0;

        while (linha16A != null) {
            String[] campos16A = linha16A.split(";");
            String[] campos16B = linha16B.split(";");
            bufferedWriter16.write(campos16B[0] + ";" + campos16B[1] + ";" + campos16A[1]);
            Total_Habitantes16 = Total_Habitantes16 + Integer.parseInt(campos16B[1]);
            Total_Matriculados16 = Total_Matriculados16 + Integer.parseInt(campos16A[1]);

            Gravados16 = Gravados16 + 1;
            bufferedWriter16.newLine();

            Lidos16A = Lidos16A + 1;
            Lidos16B = Lidos16B + 1;
            linha16A = br16A.readLine();
            linha16B = br16B.readLine();
        }

        bufferedWriter16.write("Total" + ";" + Total_Habitantes16 + ";" + Total_Matriculados16);
        bufferedWriter16.close();
        fileWriter16.close();

        System.out.println("--------- Fase 16 ---------------");
        System.out.println("Registros lidos - Matriculados = " + Lidos16A);
        System.out.println("Registros lidos - Habitantes   = " + Lidos16B);
        System.out.println("Registros gravados = " + Gravados16);

        /*
    --------------------------------------------------------------------------
    Fase 17
    Data        :   5 de novembro de 2024
    Objetivo    :   ETC - Geração do percentual ponderado (Total)
    Fase 17     :   Tratamento do arquivo de entrada: "C:\\D/Total_UF_01.csv"
                    Tratamento do arquivo de saida: "C:\\D/Total_UF_02.csv"
    --------------------------------------------------------------------------
         */
        FileInputStream instream17 = new FileInputStream(basePath + File.separator + "Total_UF_01.csv");
        FileWriter fileWriter17 = new FileWriter(new File(basePath + File.separator + "Total_UF_02.csv"));

        InputStreamReader reader17 = new InputStreamReader(instream17);
        BufferedReader br17 = new BufferedReader(reader17);

        BufferedWriter bufferedWriter17 = new BufferedWriter(fileWriter17);

        String linha17;
        linha17 = br17.readLine();

        double nHabitantes = 0.0;
        double nMatriculados = 0.0;
        double Percentual17 = 0.00;
        double Total_Para_Rateio = 0.00;
        int Lidos17 = 0;
        int Gravados17 = 0;

        while (linha17 != null) {
            String[] campos17 = linha17.split(";");
            nHabitantes = Double.parseDouble(campos17[1]);
            nMatriculados = Double.parseDouble(campos17[2]);
            Percentual17 = ((nMatriculados / nHabitantes));
            if (!campos17[0].equals("Total")) {
                Total_Para_Rateio = Total_Para_Rateio + Percentual17;
            }
            bufferedWriter17.write(campos17[0] + ";" + campos17[1] + ";" + campos17[2] + ";" + Percentual17);
            Gravados17 = Gravados17 + 1;
            bufferedWriter17.newLine();
            Lidos17 = Lidos17 + 1;
            linha17 = br17.readLine();
        }

        bufferedWriter17.write("Total_Para_Rateio" + ";" + Total_Para_Rateio);
        Gravados17 = Gravados17 + 1;

        bufferedWriter17.close();
        fileWriter17.close();

        System.out.println("--------- Fase 17 ---------------");
        System.out.println("Registros lidos    = " + Lidos17);
        System.out.println("Registros gravados = " + Gravados17);

        /*
    --------------------------------------------------------------------------
    Fase 19
    Data        :   5 de novembro de 2024
    Objetivo    :   Classificação por UF - descendente (Total)
    Fase 19     :   Tratamento do arquivo de entrada: "C:\\D/Total_UF_01.csv"
                    Tratamento do arquivo de saida: "C:\\D/Total_UF_02.csv"
    --------------------------------------------------------------------------
         */
        int Gravados18 = 0;

        String str18;
        ArrayList<String> nomes18 = new ArrayList<String>();

        BufferedReader in18 = new BufferedReader(new FileReader(basePath + File.separator + "Total_UF_02.csv"));

        while ((str18 = in18.readLine()) != null) {
            nomes18.add(str18);
        }
        in18.close();

        Collections.sort(nomes18, Collections.reverseOrder());

        BufferedWriter out18 = new BufferedWriter(new FileWriter(basePath + File.separator + "Total_UF_03.csv"));
        for (int i = 0; i < nomes18.size(); i++) {
            out18.write(nomes18.get(i));
            Gravados18 = Gravados18 + 1;
            out18.newLine();
        }
        out18.close();

        System.out.println("--------- Fase 18 ---------------");
        System.out.println("Registros gravados = " + Gravados18);

        /*
    --------------------------------------------------------------------------
    Fase 20
    Data        :   5 de novembro de 2024
    Objetivo    :   ETC - Calcula o percentual ponderado por UF (Total)
    Fase 20     :   Tratamento do arquivo de entrada: "C:\\D/Total_UF_03.csv"
                    Tratamento do arquivo de saida: "C:\\D/Total_UF_04.csv"
    --------------------------------------------------------------------------
         */
        FileInputStream instream20 = new FileInputStream(basePath + File.separator + "Total_UF_03.csv");
        FileWriter fileWriter20 = new FileWriter(new File(basePath + File.separator + "Total_UF_04.csv"));

        InputStreamReader reader20 = new InputStreamReader(instream20);
        BufferedReader br20 = new BufferedReader(reader20);

        BufferedWriter bufferedWriter20 = new BufferedWriter(fileWriter20);

        int Lidos20 = 0;
        int Gravados20 = 0;

        String linha20;
        linha20 = br20.readLine();
        Lidos20 = Lidos20 + 1;
        String[] campos20 = linha20.split(";");
        double Total_Para_Rateio2 = Double.parseDouble(campos20[1]);
        double Percentual2 = 0.00;

        linha20 = br20.readLine();

        while (linha20 != null) {
            campos20 = linha20.split(";");
            nMatriculados = Double.parseDouble(campos20[3]);
            Percentual2 = ((nMatriculados / Total_Para_Rateio2));
            bufferedWriter20.write(campos20[0] + ";"
                    + campos20[1] + ";"
                    + campos20[2] + ";"
                    + campos20[3] + ";"
                    + Percentual2);
            Gravados20 = Gravados20 + 1;
            bufferedWriter20.newLine();
            Lidos20 = Lidos20 + 1;
            linha20 = br20.readLine();
        }

        bufferedWriter20.close();
        fileWriter20.close();

        System.out.println("--------- Fase 20 ---------------");
        System.out.println("Registros lidos    = " + Lidos20);
        System.out.println("Registros gravados = " + Gravados20);

        /*
    --------------------------------------------------------------------------
    Fase 21
    Data        :   5 de novembro de 2024
    Objetivo    :   ETC - Classificação por UF (Total)
    Fase 21     :   Tratamento do arquivo de entrada: "C:\\D/Total_UF_04.csv"
                    Tratamento do arquivo de saida: "C:\\D/Total_UF_05.csv"
    --------------------------------------------------------------------------
         */
        int Gravados21 = 0;

        String str21;
        ArrayList<String> nomes21 = new ArrayList<String>();

        BufferedReader in21 = new BufferedReader(new FileReader(basePath + File.separator + "Total_UF_04.csv"));

        while ((str21 = in21.readLine()) != null) {
            nomes21.add(str21);
        }
        in21.close();
        
        Collections.sort(nomes21);
        
        BufferedWriter out21 = new BufferedWriter(new FileWriter(basePath + File.separator + "Total_UF_05.csv"));
        for (int i = 0; i < nomes21.size(); i++) {
            out21.write(nomes21.get(i));
            Gravados21 = Gravados21 + 1;
            out21.newLine();
        }
        out21.close();

        System.out.println("--------- Fase 21 ---------------");
        System.out.println("Registros gravados = " + Gravados21);

        /*
    --------------------------------------------------------------------------
    Fase 22
    Data        :   5 de novembro de 2024
    Objetivo    :   ETC - Obtém o valor para ratear e gera os valores por UF (Total)
    Fase 22     :   Tratamento do arquivo de entrada: "C:\\D/Total_UF_05.csv"
                    Tratamento do arquivo de saida: "C:\\D/Total_UF_06.csv"
    --------------------------------------------------------------------------
         */
        FileInputStream instream22 = new FileInputStream(basePath + File.separator + "Total_UF_05.csv");
        FileWriter fileWriter22 = new FileWriter(new File(basePath + File.separator + "Total_UF_06.csv"));

        InputStreamReader reader22 = new InputStreamReader(instream22);
        BufferedReader br22 = new BufferedReader(reader22);

        BufferedWriter bufferedWriter22 = new BufferedWriter(fileWriter22);

        String linha22;
        linha22 = br22.readLine();

        double Total_Valor = 0.00;
        double Rateio = 0.00;

        int Lidos22 = 0;
        int Gravados22 = 0;

        String S_Valor = showInputDialog(null, "Qual o valor a ser rateado:", "Valor", 3);
        double Valor = Double.parseDouble(S_Valor);

        while (linha22 != null) {
            String[] campos22 = linha22.split(";");
            if (!campos22[0].equals("Total")) {
                Rateio = Double.parseDouble(campos22[4]) * Valor;
                bufferedWriter22.write(campos22[0] + ";"
                        + campos22[1] + ";"
                        + campos22[2] + ";"
                        + campos22[3] + ";"
                        + campos22[4] + ";"
                        + Rateio);
            } else {
                bufferedWriter22.write(campos22[0] + ";"
                        + campos22[1] + ";"
                        + campos22[2] + ";"
                        + campos22[3]);
            }

            Gravados22 = Gravados14 + 1;
            bufferedWriter22.newLine();

            Lidos22 = Lidos22 + 1;
            linha22 = br22.readLine();
        }

        bufferedWriter22.close();
        fileWriter22.close();

        System.out.println("--------- Fase 22 ---------------");
        System.out.println("Registros lidos    = " + Lidos22);
        System.out.println("Registros gravados = " + Gravados22);

        /*
    --------------------------------------------------------------------------
    Fase 23
    Data        :   6 de novembro de 2024
    Objetivo    :   ETC - Elimina os pontos (Total)
    Fase 23     :   Tratamento do arquivo de entrada: "C:\\D/Total_UF_06.csv"
                    Tratamento do arquivo de saida: "C:\\D/Total_UF_07.csv"
    --------------------------------------------------------------------------
         */
        int Lidos23 = 0;
        int Gravados23 = 0;

        FileInputStream instream23 = new FileInputStream(basePath + File.separator + "Total_UF_06.csv");
        FileWriter fileWriter23 = new FileWriter(new File(basePath + File.separator + "Total_UF_07.csv"));

        InputStreamReader reader23 = new InputStreamReader(instream23);
        BufferedReader br23 = new BufferedReader(reader23);

        BufferedWriter bufferedWriter23 = new BufferedWriter(fileWriter23);

        String linha23 = null;
        linha23 = br23.readLine();

        Locale localeBR = new Locale("pt", "BR");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(localeBR);

        while (linha23 != null) {
            String campos23[] = linha23.split(";");
            linha23 = campos23[0] + ";" + campos23[1] + ";" + campos23[2] + ";";
            if (!campos23[0].equals("Total")) {
                for (int i = 3; i < 6; i++) {
                    double dValor = Double.parseDouble(campos23[i]);
                    linha23 = linha23 + dValor + ";";
                }
            }
            bufferedWriter23.write(linha23);

            Lidos23 = Lidos23 + 1;
            linha23 = br23.readLine();
            Gravados23 = Gravados23 + 1;
            bufferedWriter23.newLine();

        }

        bufferedWriter23.close();
        fileWriter23.close();

        System.out.println("--------- Fase 23 ---------------");
        System.out.println("Registros lidos      = " + Lidos23);
        System.out.println("Registros gravados   = " + Gravados23);

        /*
    --------------------------------------------------------------------------
    Fase 24
    Data        :   6 de novembro de 2024
    Objetivo    :   ETC - Carga dos dados na Base de Dados (Total)
    Fase 24     :   Tratamento do arquivo de entrada: "C:\\D/Total_UF_07.csv"
    --------------------------------------------------------------------------
         */
//        int Lidos24 = 0;
//        int Gravados24 = 0;
//
//        FileInputStream instream24 = new FileInputStream("C:\\Users\\822157942\\Downloads\\AnaliseDeDados-main\\AnaliseDeDados-main\\Número de habitantes por Unidades da Federação/Total_UF_07.csv");
//        InputStreamReader reader24 = new InputStreamReader(instream24);
//        BufferedReader br24 = new BufferedReader(reader24);
//
//        String linha24;
//        linha24 = br24.readLine();
//
//        Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\822157942\\Downloads\\AnaliseDeDados-main\\AnaliseDeDados-main\\Número de habitantes por Unidades da Federação/Base_UF.accdb");
//        Statement stmt = con.createStatement();
//
//        while (linha24 != null) {
//            String campos24[] = linha24.split(";");
//            if (!campos24[0].equals("Total")) {
//                String Base_UF = campos24[0];
//                int Base_Habitantes = Integer.parseInt(campos24[1]);
//                int Base_Matriculados = Integer.parseInt(campos24[2]);;
//                double Base_Percentua24A = Double.parseDouble(campos24[3]);
//                double Base_Percentua24B = Double.parseDouble(campos24[4]);
//                double Base_Rateio = Double.parseDouble(campos24[5]);
//                stmt.executeUpdate("insert into Tabela_UF "
//                        + "(UF, "
//                        + "Habitantes,"
//                        + "Matriculados, "
//                        + "Percentual1, "
//                        + "Percentual2,"
//                        + "Rateio) values "
//                        + "('" + Base_UF
//                        + "','" + Base_Habitantes
//                        + "', '" + Base_Matriculados
//                        + "', '" + Base_Percentua24A
//                        + "', '" + Base_Percentua24B
//                        + "', '" + Base_Rateio + "' )");
//                Gravados24 = Gravados24 + 1;
//            }
//            Lidos24 = Lidos24 + 1;
//            linha24 = br24.readLine();
//        }
//
//        con.close();
//        System.out.println("--------- Fase 24 ---------------");
//        System.out.println("Registros lidos    = " + Lidos24);
//        System.out.println("Registros gravados = " + Gravados24);

    }
}
