package API;

import API.Enums.TipoAlvo;
import API.Enums.TipoEntidade;
import API.Enums.TipoItem;
import API.MapExtension;
import Collections.Lists.ArrayUnorderedList;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ImportExport<T> {

    private static String currentWorkingDir = System.getProperty("user.dir");
    protected static final String MISSOES_FILE_PATH = currentWorkingDir + "/src/Files/missoes.json";

    public ArrayUnorderedList<Missao> importMissoes() {
        ArrayUnorderedList<Missao> missoes = new ArrayUnorderedList<>();

        try {
            // Abrir o arquivo JSON
            FileReader reader = new FileReader(MISSOES_FILE_PATH);

            // Fazer o parsing do JSON
            JSONParser parser = new JSONParser();
            JSONArray missoesArray = (JSONArray) parser.parse(reader);

            // Iterar sobre cada missão no array
            for (Object obj : missoesArray) {
                JSONObject missaoObj = (JSONObject) obj;

                // Extrair os dados da missão
                String codMissao = (String) missaoObj.get("cod-missao");
                long versao = (long) missaoObj.get("versao");

                // Construir o grafo (mapa)
                JSONArray mapaArray = (JSONArray) missaoObj.get("mapa");
                MapaExtension<Divisao> mapa = new MapaExtension<>();

                // Criar as divisões e adicionar ao grafo
                for (Object div : mapaArray) {
                    String nomeDivisao = (String) div;
                    Divisao divisao = new Divisao(nomeDivisao);
                    mapa.addVertex(divisao);
                }

                // Adicionar conexões (ligações) ao grafo
                JSONArray ligacoesArray = (JSONArray) missaoObj.get("ligacoes");
                for (Object ligacaoObj : ligacoesArray) {
                    JSONArray ligacao = (JSONArray) ligacaoObj;
                    String origem = (String) ligacao.get(0);
                    String destino = (String) ligacao.get(1);

                    Divisao origemDivisao = mapa.encontrarDivisaoPorNome(origem);
                    Divisao destinoDivisao = mapa.encontrarDivisaoPorNome(destino);

                    if (origemDivisao != null && destinoDivisao != null) {
                        mapa.addEdge(origemDivisao, destinoDivisao);
                    }
                }

                // Configurar as divisões de entrada e saída
                JSONArray entradasSaidasArray = (JSONArray) missaoObj.get("entradas-saidas");
                for (Object entradaSaida : entradasSaidasArray) {
                    String nomeDivisao = (String) entradaSaida;
                    Divisao divisao = mapa.encontrarDivisaoPorNome(nomeDivisao);
                    if (divisao != null) {
                        divisao.setEntradaSaida(true);
                    }
                }

                // Adicionar os inimigos às divisões
                JSONArray inimigosArray = (JSONArray) missaoObj.get("inimigos");
                for (Object inimigoObj : inimigosArray) {
                    JSONObject inimigoJSON = (JSONObject) inimigoObj;
                    String nomeInimigo = (String) inimigoJSON.get("nome");
                    long poder = (long) inimigoJSON.get("poder");
                    String divisaoNome = (String) inimigoJSON.get("divisao");

                    Divisao divisao = mapa.encontrarDivisaoPorNome(divisaoNome);
                    if (divisao != null) {
                        // Criar inimigo com tipo padrão
                        Inimigo inimigo = new Inimigo(nomeInimigo, 100, (int) poder, divisaoNome, TipoEntidade.INIMIGO);

                        divisao.getPessoasPresentes().addToRear(inimigo);
                        divisao.setNumPessoasPresentes(divisao.getNumPessoasPresentes() + 1);
                    }
                }

                // Adicionar os itens às divisões
                JSONArray itensArray = (JSONArray) missaoObj.get("itens");
                for (Object itemObj : itensArray) {
                    JSONObject itemJSON = (JSONObject) itemObj;
                    String divisaoNome = (String) itemJSON.get("divisao");
                    String tipoItemStr = (String) itemJSON.get("tipo");

                    // Verifica se é "pontos-recuperados" ou "pontos-extra"
                    int pontos = 0;
                    if (itemJSON.containsKey("pontos-recuperados")) {
                        pontos = ((Long) itemJSON.get("pontos-recuperados")).intValue();
                    } else if (itemJSON.containsKey("pontos-extra")) {
                        pontos = ((Long) itemJSON.get("pontos-extra")).intValue();
                    }

                    Divisao divisao = mapa.encontrarDivisaoPorNome(divisaoNome);
                    if (divisao != null) {
                        // Criar item com o tipo apropriado
                        TipoItem tipoItem = TipoItem.valueOf(tipoItemStr.toUpperCase().replace(" ", "_"));
                        Item item = new Item(divisaoNome, pontos, tipoItem);

                        divisao.getItensPresentes().addToRear(item);
                        divisao.setNumItensPresentes(divisao.getNumItensPresentes() + 1);
                    }
                }

                // Extrair o Alvo
                JSONObject alvoObj = (JSONObject) missaoObj.get("alvo");
                String divisaoAlvo = (String) alvoObj.get("divisao");
                String tipoAlvo = (String) alvoObj.get("tipo");
                Alvo alvo = new Alvo(divisaoAlvo, TipoAlvo.valueOf(tipoAlvo));

                // Criar a missão e adicionar à lista
                Missao missao = new Missao(codMissao, (int) versao, mapa, alvo);
                missoes.addToRear(missao);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return missoes;
    }

}
