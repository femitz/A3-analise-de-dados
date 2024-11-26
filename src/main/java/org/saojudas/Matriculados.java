package org.saojudas;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Matriculados {
    private String pathBase;
    private String arquivo;


    public Matriculados(String pathBase, String arquivo) {
        this.pathBase = pathBase;
        this.arquivo = arquivo;
    }

    public void fase8() throws IOException {
        FileInputStream instream = new FileInputStream(pathBase + File.separator + arquivo +  ".csv");
        FileWriter fileWriter = new FileWriter(new File(pathBase + File.separator + arquivo + "_02" + ".csv"));

        InputStreamReader reader = new InputStreamReader(instream);
        BufferedReader br = new BufferedReader(reader);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        //*----------------- Defini ao da linha do arquivo de entrada  -----------------
        String linha;
        linha = br.readLine();

        String UF = null;
        String Numeros = null;

        //*----------------- Defini ao das variaveis de controle de lidos e gravados -----------------
        int Lidos = 0;
        int Gravados = 0;

        //*----------------- Leitura de todos os registros (linhas) do arquivo de entrada ------------

        while (linha != null) {
            Lidos++;

            if ((linha.substring(0, linha.length()).equals("ACRE;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("ALAGOAS;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("AMAZONAS;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("AMAPA;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("BAHIA;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("DISTRITO FEDERAL;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("CEARA;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("ESPIRITO SANTO;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("GOIAS;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("MARANHAO;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("MATO GROSSO;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("MATO GROSSO DO SUL;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("MINAS GERAIS;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("PARA;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("PARAIBA;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("PARANA;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("PERNAMBUCO;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("PIAUI;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("RIO DE JANEIRO;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("RIO GRANDE DO NORTE;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("RIO GRANDE DO SUL;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("RONDONIA;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("RORAIMA;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("SANTA CATARINA;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("SAO PAULO;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("SERGIPE;;;;;;;;;;;;"))
                    || (linha.substring(0, linha.length()).equals("TOCANTINS;;;;;;;;;;;;"))) {

                //*----------------- Leitura de blocos de 5 em 5 linhas -----------------
                for (int i = 0; i < 6; i++) {
                    if (i == 0 || i == 5) {
                        if (i == 0) //*----------------- Para a primeira linha grava a UF (Estado)-----------
                        {
                            UF = linha;
                        } else if (i == 5) {
                            //*----------------- Para a quinta linha grava os valores -----------
                            Numeros = linha; // Estadual + Municipal ; Numeros
                            bufferedWriter.write(UF + ";" + Numeros);
                            Gravados++;
                            bufferedWriter.newLine();
                        }
                    }
                    linha = br.readLine();
                }
            }
            linha = br.readLine();
        }
        //*----------------- Encerramento do tratamento do arquivo -----------
        bufferedWriter.close();
        fileWriter.close();
        reader.close();
        br.close();
        instream.close();
        //*------------------------ Exibe os controles  -----------

        System.out.println("--------- Fase 8 ---------------");
        System.out.println("Registros lidos    = " + Lidos);
        System.out.println("Registros gravados = " + Gravados);
        System.out.println("Arquivo base: " + arquivo);
    }


    public void fase9 () throws IOException {
        int Lidos = 0;
        int Gravados = 0;
        FileInputStream instream = new FileInputStream(pathBase + File.separator + arquivo + "_02"+ ".csv");
        FileWriter fileWriter = new FileWriter(new File(pathBase + File.separator + arquivo + "_03" + ".csv"));

        InputStreamReader reader = new InputStreamReader(instream);
        BufferedReader br = new BufferedReader(reader);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String linha;
        linha = br.readLine();
        int Matriculados = 0;
        int j = 0;

        while (linha != null) {
            String[] campos = linha.split(";");

            for (j = 15; j < 25; j++) {
                String result = campos[j].replaceAll("\\p{Punct}", "");
                Matriculados = Matriculados + Integer.parseInt(result);
            }

            Lidos++;
            linha = br.readLine();
            bufferedWriter.write(campos[0] + ";" + Matriculados);
            Gravados++;
            Matriculados = 0;
            bufferedWriter.newLine();

        }
        bufferedWriter.close();
        fileWriter.close();
        reader.close();
        br.close();
        instream.close();

        System.out.println("--------- Fase 9 ---------------");
        System.out.println("Registros lidos    = " + Lidos);
        System.out.println("Registros gravados = " + Gravados);
        System.out.println("Arquivo base: " + arquivo);
    }


    public void fase10 () throws IOException {
        String str;
        ArrayList<String> nomes = new ArrayList<String>();

        BufferedReader in = new BufferedReader(new FileReader(pathBase + File.separator + arquivo + "_03" +".csv"));

        int Gravados10 = 0;

        while ((str = in.readLine()) != null) {
            nomes.add(str);
        }
        in.close();

        Collections.sort(nomes);

        BufferedWriter out = new BufferedWriter(new FileWriter(pathBase + File.separator + arquivo + "_04" +".csv"));
        for (int i = 0; i < nomes.size(); i++) {
            out.write(nomes.get(i));
            Gravados10 = Gravados10 + 1;
            out.newLine();
        }
        out.close();

        System.out.println("--------- Fase 10 ---------------");
        System.out.println("Registros gravados = " + Gravados10);
        System.out.println("Arquivo base: " + arquivo);
    }


    public void fase11() throws IOException {
        FileInputStream instream = new FileInputStream(pathBase + File.separator + arquivo + "_04" +".csv");
        FileWriter fileWriter = new FileWriter(new File(pathBase + File.separator + arquivo + "_05" +".csv"));

        InputStreamReader reader = new InputStreamReader(instream);
        BufferedReader br = new BufferedReader(reader);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String linha;
        linha = br.readLine();

        int Lidos = 0;
        int Gravados = 0;

        String Estado_anterior = null;
        int Valor_anterior = 0;

        while (linha != null) {
            String[] campos = linha.split(";");
            if (campos[0].equals(Estado_anterior)) {
                if (Integer.parseInt(campos[1]) > Valor_anterior) {
                    bufferedWriter.write(campos[0] + ";" + campos[1]);
                    Gravados = Gravados + 1;
                    bufferedWriter.newLine();
                    Lidos = Lidos + 1;
                    linha = br.readLine();
                } else {
                    Estado_anterior = campos[0];
                    Valor_anterior = Integer.parseInt(campos[1]);
                    Lidos = Lidos + 1;
                    linha = br.readLine();
                }
            } else {
                bufferedWriter.write(campos[0] + ";" + campos[1]);
                Gravados = Gravados + 1;
                bufferedWriter.newLine();
                Estado_anterior = campos[0];
                Valor_anterior = Integer.parseInt(campos[1]);
                Lidos = Lidos + 1;
                linha = br.readLine();
            }
        }
        bufferedWriter.close();
        fileWriter.close();
        reader.close();
        br.close();
        instream.close();

        System.out.println("--------- Fase 11 ---------------");
        System.out.println("Registros lidos    = " + Lidos);
        System.out.println("Registros gravados = " + Gravados);
        System.out.println("Arquivo base: " + arquivo);
    }

    public void fase12 () throws IOException {
        int Gravados = 0;

        String str;
        ArrayList<String> nomes = new ArrayList<String>();

        BufferedReader in = new BufferedReader(new FileReader(pathBase + File.separator + arquivo + "_05" +".csv"));

        while ((str = in.readLine()) != null) {
            nomes.add(str);
        }
        in.close();

        Collections.sort(nomes, Collections.reverseOrder());

        BufferedWriter out = new BufferedWriter(new FileWriter(pathBase + File.separator + arquivo + "_06" +".csv"));
        for (int i = 0; i < nomes.size(); i++) {
            out.write(nomes.get(i));
            Gravados = Gravados + 1;
            out.newLine();
        }
        out.close();

        System.out.println("--------- Fase 12 ---------------");
        System.out.println("Registros gravados = " + Gravados);
        System.out.println("Arquivo base: " + arquivo);
    }

    public void fase13 () throws IOException {
        FileInputStream instream = new FileInputStream(pathBase + File.separator + arquivo + "_06" +".csv");
        FileWriter fileWriter = new FileWriter(new File(pathBase + File.separator + arquivo + "_07" +".csv"));

        InputStreamReader reader = new InputStreamReader(instream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String linha;
        linha = bufferedReader.readLine();

        int Lidos = 0;
        int Gravados = 0;

        String Estado_anterior = null;
        int Valor_anterior = 0;

        while (linha != null) {
            String[] campos = linha.split(";");
            if (campos[0].equals(Estado_anterior)) {
                if (Integer.parseInt(campos[1]) > Valor_anterior) {
                    bufferedWriter.write(campos[0] + ";" + campos[1]);
                    Gravados = Gravados + 1;
                    bufferedWriter.newLine();
                    Lidos = Lidos + 1;
                    linha = bufferedReader.readLine();
                } else {
                    Estado_anterior = campos[0];
                    Valor_anterior = Integer.parseInt(campos[1]);
                    Lidos = Lidos + 1;
                    linha = bufferedReader.readLine();
                }
            } else {
                bufferedWriter.write(campos[0] + ";" + campos[1]);
                Gravados = Gravados + 1;
                bufferedWriter.newLine();
                Estado_anterior = campos[0];
                Valor_anterior = Integer.parseInt(campos[1]);
                Lidos = Lidos + 1;
                linha = bufferedReader.readLine();
            }
        }
        bufferedWriter.close();
        fileWriter.close();
        reader.close();
        instream.close();

        System.out.println("--------- Fase 13 ---------------");
        System.out.println("Registros lidos    = " + Lidos);
        System.out.println("Registros gravados = " + Gravados);
        System.out.println("Arquivo base: " + arquivo);
    }

    public void fase14 () throws IOException {
        FileInputStream instream = new FileInputStream(pathBase + File.separator + arquivo + "_07" +".csv");
        FileWriter fileWriter = new FileWriter(new File(pathBase + File.separator + arquivo + "_08" +".csv"));

        InputStreamReader reader = new InputStreamReader(instream);
        BufferedReader br = new BufferedReader(reader);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String linha;
        linha = br.readLine();

        int Lidos = 0;
        int Gravados = 0;

        String[] UF_Estado = new String[27];

        UF_Estado[0] = "AC ACRE";
        UF_Estado[1] = "AL ALAGOAS";
        UF_Estado[2] = "AM AMAZONAS";
        UF_Estado[3] = "AP AMAPA";
        UF_Estado[4] = "BA BAHIA";
        UF_Estado[5] = "DF DISTRITO FEDERAL";
        UF_Estado[6] = "CE CEARA";
        UF_Estado[7] = "ES ESPIRITO SANTO";
        UF_Estado[8] = "GO GOIAS";
        UF_Estado[9] = "MA MARANHAO";
        UF_Estado[10] = "MT MATO GROSSO";
        UF_Estado[11] = "MS MATO GROSSO DO SUL";
        UF_Estado[12] = "MG MINAS GERAIS";
        UF_Estado[13] = "PA PARA";
        UF_Estado[14] = "PB PARAIBA";
        UF_Estado[15] = "PR PARANA";
        UF_Estado[16] = "PE PERNAMBUCO";
        UF_Estado[17] = "PI PIAUI";
        UF_Estado[18] = "RJ RIO DE JANEIRO";
        UF_Estado[19] = "RN RIO GRANDE DO NORTE";
        UF_Estado[20] = "RS RIO GRANDE DO SUL";
        UF_Estado[21] = "RO RONDONIA";
        UF_Estado[22] = "RR RORAIMA";
        UF_Estado[23] = "SC SANTA CATARINA";
        UF_Estado[24] = "SP SAO PAULO";
        UF_Estado[25] = "SE SERGIPE";
        UF_Estado[26] = "TO TOCANTINS";

        while (linha != null) {
            Lidos = Lidos + 1;
            String[] campos = linha.split(";");
            for (int i = 0; i < 27; i++) {
                if (campos[0].equals(UF_Estado[i].substring(3, UF_Estado[i].length()))) {
                    campos[0] = UF_Estado[i].substring(0, 2);
                    bufferedWriter.write(campos[0] + ";" + campos[1]);
                    Gravados = Gravados + 1;
                    bufferedWriter.newLine();
                }
            }
            linha = br.readLine();
        }

        Gravados = Gravados + 1;

        bufferedWriter.close();
        fileWriter.close();
        reader.close();
        br.close();
        instream.close();

        System.out.println("--------- Fase 14 ---------------");
        System.out.println("Registros lidos    = " + Lidos);
        System.out.println("Registros gravados = " + Gravados);
        System.out.println("Arquivo base: " + arquivo);
    }

    public void fase15 () throws IOException {
        int Gravados = 0;

        String str;
        ArrayList<String> nomes = new ArrayList<String>();

        BufferedReader in = new BufferedReader(new FileReader(pathBase + File.separator + arquivo + "_08" +".csv"));

        while ((str = in.readLine()) != null) {
            nomes.add(str);
        }
        in.close();

        Collections.sort(nomes);

        BufferedWriter out = new BufferedWriter(new FileWriter(pathBase + File.separator + arquivo + "_09" +".csv"));
        for (int i = 0; i < nomes.size(); i++) {
            out.write(nomes.get(i));
            Gravados = Gravados + 1;
            out.newLine();
        }
        out.close();

        System.out.println("--------- Fase 15 ---------------");
        System.out.println("Registros gravados = " + Gravados);
        System.out.println("Arquivo base: " + arquivo);
    }
}
