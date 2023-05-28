package entities;
import java.text.SimpleDateFormat;
import java.util.*;

public class covid implements Comparable<covid> {
    public String regiao;
    public String estado;
    public String municipio;
    public int coduf;
    public int codmun;
    public int codRegiaoSaude;
    public String nomeRegiaoSaude;
    public Date data;
    public int semanaEpi;
    public int populacaoTCU2019;
    public int casosNovos;
    public int obitosNovos;
    public int Recuperadosnovos;
    public int emAcompanhamentoNovos;
    public int interior_metropolitana;
    @Override
    public String toString()
    {
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        return String.format(
            "\n{\n\t\"regiao\": \"%s\",\n\t\"estado\": \"%s\",\n\t\"municipio\": \"%s\","+
            "\n\t\"coduf\": \"%d\",\n\t\"codmun\": \"%d\","+
            "\n\t\"nomeRegiaoSaude\": \"%s\",\n\t\"data\": \"%s\",\n\t\"semanaEpi\": \"%d\","+
            "\n\t\"populacaoTCU2019\": \"%d\",\n\t\"casosNovos\": \"%d\",\n\t\"obitosNovos\": \"%d\","+
            "\n\t\"Recuperadosnovos\": \"%d\",\n\t\"emAcompanhamentoNovos\": \"%d\",\n\t\"interior_metropolitana\": \"%d\"\n}\n", 
        regiao,
        estado,
        municipio,
        coduf,
        codmun,
        nomeRegiaoSaude,
        s.format(data),
        semanaEpi,
        populacaoTCU2019,
        casosNovos,
        obitosNovos,
        Recuperadosnovos,
        emAcompanhamentoNovos,
        interior_metropolitana
        );
    }
    public covid
    (
        String regiao,
        String estado,
        String municipio,
        String coduf,
        String codmun,
        String codRegiaoSaude,
        String nomeRegiaoSaude,
        String data,
        String semanaEpi,
        String populacaoTCU2019,
        String casosNovos,
        String obitosNovos,
        String Recuperadosnovos,
        String emAcompanhamentoNovos,
        String interior_metropolitana
    ) 
    {
        try
        {
            SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
            this.regiao = regiao;
            this.estado = estado;
            this.municipio = municipio;
            this.coduf = Integer.parseInt(coduf);
            this.codmun = Integer.parseInt(codmun);
            this.codRegiaoSaude = Integer.parseInt(codRegiaoSaude);
            this.nomeRegiaoSaude = nomeRegiaoSaude;
            this.data = s.parse(data);
            this.semanaEpi = Integer.parseInt(semanaEpi);
            this.populacaoTCU2019 = Integer.parseInt(populacaoTCU2019);
            this.casosNovos = Integer.parseInt(casosNovos);
            this.obitosNovos = Integer.parseInt(obitosNovos);
            this.Recuperadosnovos = Integer.parseInt(Recuperadosnovos);
            this.emAcompanhamentoNovos = Integer.parseInt(emAcompanhamentoNovos);
            this.interior_metropolitana = Integer.parseInt(interior_metropolitana);
        }
        catch (Exception e) {
        }
    }

    // --------------------------------------------------------------------------------------------------------------------

    // Os objetos referentes a cada linha do .csv acima, mas não todos, só vi
    // necessidade desses

    // --------------------------------------------------------------------------------------------------------------------

    // A ideia é conforme for inserindo cada item dentro da árvore
    // ir fazendo a comparação com esse compareTo e ir ordenando conforme cada item
    // for entrando;
    // Por enquanto ordenamos na seguinte ordem
    // siglaEstado > cidade > regSaude;
    // Se necessário adicionamos mais etapas na ordenação ou alteramos a ordem da
    // ordenação feita atualmente

    // --------------------------------------------------------------------------------------------------------------------

    // Tenho uma ideia que acho legal a gente fazer também, mas recomendo olharmos
    // com o Kleber antes é
    // darmos a opção de o usuário selecionar por qual(is) campo(s) ele quer ordenar
    // a arvore
    // acho que seria uma ótima ideia
    // public void orderBy(int[] numeracaoCampo /*1,2,3,4 orderm dos campos*/ ) { }

    // --------------------------------------------------------------------------------------------------------------------

    @Override
    public int compareTo(covid v) {
        // não sei se tinha que ser this.compare('o')
        // ou o.compare('this')
        // tem que ver qual é a comparação certa
        int cmp = this.estado.compareTo(v.estado);
        if (cmp == 0) {
            cmp = this.municipio.compareTo(v.municipio);
            return cmp;
        }
        return cmp;
    }
    

}
