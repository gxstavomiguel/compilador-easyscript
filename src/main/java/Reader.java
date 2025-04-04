import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
    public static void main(String args[]){
        String txtUrl = "E:\\_ESTUDOS\\curso-java\\compilador\\src\\main\\resources\\linguagem.txt";

        ArrayList<Map<Integer, String>> tokensElexema = new ArrayList<>();
        Map<String, Integer> tabelaTokens = new HashMap<>();

        tabelaTokens.put("program", 1);
        tabelaTokens.put("const", 2);
        tabelaTokens.put("var", 3);
        tabelaTokens.put("procedure", 4);
        tabelaTokens.put("init", 5);
        tabelaTokens.put("exit", 6);
        tabelaTokens.put("print", 7);
        tabelaTokens.put("if", 8);
        tabelaTokens.put("then", 9);
        tabelaTokens.put("else", 10);
        tabelaTokens.put("for", 11);
        tabelaTokens.put("to", 12);
        tabelaTokens.put("do", 13);
        tabelaTokens.put("while", 14);
        tabelaTokens.put("read", 15);
        tabelaTokens.put("ident", 16);
        tabelaTokens.put("nint", 17);
        tabelaTokens.put("nreal", 18);
        tabelaTokens.put("literal", 19);
        tabelaTokens.put("vstring", 20);
        tabelaTokens.put(";", 21);
        tabelaTokens.put(".", 22);
        tabelaTokens.put(",", 23);
        tabelaTokens.put(":", 24);
        tabelaTokens.put("=", 25);
        tabelaTokens.put(":=", 26);
        tabelaTokens.put("{", 27);
        tabelaTokens.put("}", 28);
        tabelaTokens.put("(", 29);
        tabelaTokens.put(")", 30);
        tabelaTokens.put("<>", 31);
        tabelaTokens.put("<", 32);
        tabelaTokens.put(">", 33);
        tabelaTokens.put("<=", 34);
        tabelaTokens.put(">=", 35);
        tabelaTokens.put("+", 36);
        tabelaTokens.put("-", 37);
        tabelaTokens.put("*", 38);
        tabelaTokens.put("/", 39);

        try (BufferedReader tentaLer = new BufferedReader(new FileReader(txtUrl))) {
            String linha;
            int numLinha = 1;

            while ((linha = tentaLer.readLine()) != null) {
                String regex = "\"[^\"]*\"|:=|<=|>=|<>|[a-zA-Z_][a-zA-Z0-9_]*|\\d+|\\S";
                Matcher matcher = Pattern.compile(regex).matcher(linha);

                while (matcher.find()) {
                    String parte = matcher.group();
                    Map<Integer, String> mapsForTokens = new HashMap<>();

                    if (tabelaTokens.containsKey(parte)) {
                        mapsForTokens.put(tabelaTokens.get(parte), parte);
                    } else if (parte.matches("\"[^\"]*\"")) {
                        mapsForTokens.put(997, parte);
                    } else if (parte.matches("\\d+")) {
                        mapsForTokens.put(999, parte);
                    } else if (parte.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
                        mapsForTokens.put(998, parte);
                    } else {
                        mapsForTokens.put(996, parte);
                    }

                    tokensElexema.add(mapsForTokens);
                }
                numLinha++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map<Integer, String> token : tokensElexema) {
            for (Map.Entry<Integer, String> entry : token.entrySet()){
                System.out.println("[Token: " + entry.getKey() + ", Lexema: " + entry.getValue() + "]");
            }
        }
    }
}
