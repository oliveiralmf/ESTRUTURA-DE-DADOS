/*
AULA 01 - TIPOS PRIMITIVOS EM JAVA
===================================

Disciplina: Estrutura de Dados
Curso: Engenharia de Software

Antes de estudar estruturas de dados (listas, pilhas, filas, árvores...),
precisamos entender os "tijolos" a partir dos quais tudo é construído:
os tipos primitivos.

Java tem exatamente 8 tipos primitivos, divididos em 4 grupos:

    Inteiros:        byte, short, int, long
    Ponto flutuante: float, double
    Caractere:       char
    Lógico:          boolean

Tudo o que NÃO é primitivo é um objeto (tipo por referência): String, arrays,
as classes que você criar, etc. String não é primitivo, mas é tão essencial
que também será tratada aqui.

Execute este arquivo para ver os exemplos em ação (Java 17 ou superior):

    java TiposPrimitivos.java

Dica: leia os comentários com calma. Cada seção explica um conceito e
mostra o resultado no terminal. Sempre que houver uma diferença importante
em relação ao Python ou ao JavaScript, ela será destacada.
*/

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class TiposPrimitivos {

    static void titulo(String texto) {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println(texto);
        System.out.println("=".repeat(70));
    }

    @SuppressWarnings({ "null", "unused" })
    public static void main(String[] args) {

        // -------------------------------------------------------------------
        // 1. TIPAGEM ESTÁTICA E DECLARAÇÃO DE VARIÁVEIS
        // -------------------------------------------------------------------
        titulo("1. Tipagem estática e declaração de variáveis");

        // DIFERENÇA FUNDAMENTAL: Java é ESTATICAMENTE tipado. Toda variável
        // precisa declarar seu tipo, e esse tipo nunca muda. O compilador
        // verifica tudo ANTES de o programa rodar.
        int idade = 20;
        double altura = 1.75;
        char inicial = 'M';
        boolean aprovado = true;
        String nome = "Maria";

        System.out.println("int     idade    = " + idade);
        System.out.println("double  altura   = " + altura);
        System.out.println("char    inicial  = " + inicial);
        System.out.println("boolean aprovado = " + aprovado);
        System.out.println("String  nome     = " + nome + "   <- String é um objeto, não um primitivo");

        // Tentar mudar o tipo de uma variável NÃO COMPILA:
        //   idade = "vinte";          // erro: incompatible types: String cannot be converted to int
        // Em Python e JavaScript isso é permitido. Em Java, o erro aparece antes de rodar.

        // Como em JavaScript, o + com String CONCATENA (o número vira texto):
        System.out.println("\n1 + \"1\"     = " + (1 + "1") + "   <- concatena, como em JavaScript");
        System.out.println("1 + 1 + \"1\" = " + (1 + 1 + "1") + "   <- soma primeiro, depois concatena");
        System.out.println("\"1\" + 1 + 1 = " + ("1" + 1 + 1) + "  <- avalia da esquerda para a direita");

        // Desde o Java 10, "var" deixa o compilador DEDUZIR o tipo pelo valor.
        // A variável continua estaticamente tipada: o tipo é fixado na declaração.
        var contador = 0;          // contador é int
        var mensagem = "olá";      // mensagem é String
        //   contador = "texto";    // continua sendo erro de compilação
        System.out.println("\nvar contador = " + contador + " (int) | var mensagem = " + mensagem + " (String)");

        // Variáveis locais PRECISAM ser inicializadas antes do uso:
        //   int semValor;
        //   System.out.println(semValor);   // erro: variable semValor might not have been initialized


        // -------------------------------------------------------------------
        // 2. INTEIROS - byte, short, int E long
        // -------------------------------------------------------------------
        titulo("2. Inteiros - byte, short, int e long");

        // Cada tipo inteiro tem um TAMANHO FIXO em bits e, portanto, uma faixa
        // de valores limitada. É diferente do int do Python, que é ilimitado.
        byte valorByte = 127;                     //  8 bits
        short valorShort = 32_000;                // 16 bits
        int valorInt = 2_000_000_000;             // 32 bits
        long valorLong = 9_000_000_000_000L;      // 64 bits (o sufixo L é obrigatório fora da faixa do int)
        System.out.printf("byte %d | short %d | int %d | long %d%n", valorByte, valorShort, valorInt, valorLong);

        System.out.printf("%nbyte : %2d bits, de %d a %d%n", Byte.SIZE, Byte.MIN_VALUE, Byte.MAX_VALUE);
        System.out.printf("short: %2d bits, de %d a %d%n", Short.SIZE, Short.MIN_VALUE, Short.MAX_VALUE);
        System.out.printf("int  : %2d bits, de %d a %d%n", Integer.SIZE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.printf("long : %2d bits, de %d a %d%n", Long.SIZE, Long.MIN_VALUE, Long.MAX_VALUE);
        // Na prática: use int por padrão e long quando precisar de números grandes.
        // byte e short aparecem em situações específicas (arquivos, redes, economia de memória).

        // DETALHE IMPORTANTE: OVERFLOW. Quando um valor passa do limite, ele "dá a
        // volta" e vira negativo, SEM ERRO. É um dos bugs mais clássicos de Java.
        int maximo = Integer.MAX_VALUE;
        System.out.printf("%nInteger.MAX_VALUE     = %d%n", maximo);
        System.out.printf("Integer.MAX_VALUE + 1 = %d  <- overflow: virou negativo!%n", maximo + 1);
        // Para detectar overflow, use Math.addExact, que lança exceção:
        try {
            Math.addExact(maximo, 1);
        } catch (ArithmeticException erro) {
            System.out.println("Math.addExact(MAX_VALUE, 1) lança ArithmeticException: " + erro.getMessage());
        }

        // Operações aritméticas:
        int a = 17;
        int b = 5;
        System.out.printf("%n%d + %d  = %d%n", a, b, a + b);      // adição
        System.out.printf("%d - %d  = %d%n", a, b, a - b);        // subtração
        System.out.printf("%d * %d  = %d%n", a, b, a * b);        // multiplicação
        System.out.printf("%d / %d  = %d  <- (!) int / int é divisão INTEIRA: descarta a parte decimal%n", a, b, a / b);
        System.out.printf("%d %% %d  = %d%n", a, b, a % b);       // resto da divisão (módulo)
        System.out.printf("%d / %d.0 = %s  <- basta um dos lados ser decimal%n", a, b, a / (double) b);
        // DIFERENÇA: não existe o operador ** em Java. Use Math.pow (devolve double).
        System.out.printf("Math.pow(%d, %d) = %s%n", a, b, Math.pow(a, b));
        System.out.printf("(int) Math.pow(%d, %d) = %d%n", a, b, (int) Math.pow(a, b));

        // DIFERENÇA: como em JavaScript, o resto tem o sinal do DIVIDENDO.
        System.out.println("-17 % 5 = " + (-17 % 5) + "  (em Python seria 3)");
        System.out.println("-17 / 5 = " + (-17 / 5) + "  <- trunca em direção ao zero (em Python, -17 // 5 dá -4)");
        System.out.println("Math.floorMod(-17, 5) = " + Math.floorMod(-17, 5) + "  <- comportamento do Python");
        System.out.println("Math.floorDiv(-17, 5) = " + Math.floorDiv(-17, 5));

        // Para inteiros de PRECISÃO ARBITRÁRIA (como o int do Python), use BigInteger:
        BigInteger fatorialDe30 = BigInteger.ONE;
        for (int n = 1; n <= 30; n++) {
            fatorialDe30 = fatorialDe30.multiply(BigInteger.valueOf(n));
        }
        System.out.println("\n30! com BigInteger   = " + fatorialDe30);
        System.out.println("2^100 com BigInteger = " + BigInteger.TWO.pow(100));


        // -------------------------------------------------------------------
        // 3. PONTO FLUTUANTE - float E double
        // -------------------------------------------------------------------
        titulo("3. Ponto flutuante - float e double");

        // Dois tipos: float (32 bits) e double (64 bits). Use double por padrão.
        // O double do Java é o MESMO formato do float do Python e do number do JavaScript.
        float precoFloat = 19.99f;       // o sufixo f é OBRIGATÓRIO: sem ele, o literal é double
        double precoDouble = 19.99;
        System.out.println("float  19.99f -> " + precoFloat);
        System.out.println("double 19.99  -> " + precoDouble);
        //   float erro = 19.99;   // NÃO COMPILA: possible lossy conversion from double to float

        // float tem cerca de 7 dígitos de precisão; double tem cerca de 15.
        float umTercoF = 1.0f / 3;
        double umTercoD = 1.0 / 3;
        System.out.println("1/3 em float : " + umTercoF);
        System.out.println("1/3 em double: " + umTercoD);

        // Notação científica:
        System.out.println("\n1.5e3 = " + 1.5e3 + " | 2.5e-4 = " + 2.5e-4);

        // DETALHE IMPORTANTE: floats NÃO são exatos (o mesmo problema das outras linguagens).
        System.out.println("\n0.1 + 0.2 = " + (0.1 + 0.2));
        System.out.println("0.1 + 0.2 == 0.3 ? " + (0.1 + 0.2 == 0.3));
        System.out.println("Math.abs((0.1 + 0.2) - 0.3) < 1e-9 ? " + (Math.abs((0.1 + 0.2) - 0.3) < 1e-9));

        // Para dinheiro e cálculos exatos, use BigDecimal (com String no construtor!):
        BigDecimal soma = new BigDecimal("0.1").add(new BigDecimal("0.2"));
        System.out.println("new BigDecimal(\"0.1\").add(new BigDecimal(\"0.2\")) = " + soma);

        // Valores especiais: divisão por zero com double NÃO lança exceção...
        System.out.println("\n1.0 / 0 = " + (1.0 / 0) + "  <- não lança exceção");
        System.out.println("0.0 / 0 = " + (0.0 / 0) + "  <- Not a Number");
        System.out.println("Double.isNaN(0.0 / 0) -> " + Double.isNaN(0.0 / 0));
        // ...mas com int lança! (comportamento do Python)
        try {
            int zero = 0;
            System.out.println(1 / zero);
        } catch (ArithmeticException erro) {
            System.out.println("1 / 0 com int lança ArithmeticException: " + erro.getMessage());
        }

        // Arredondamento:
        System.out.println("\nMath.round(2.5) = " + Math.round(2.5) + " | Math.round(3.5) = " + Math.round(3.5) + "  <- .5 sobe (Python vai para o par)");
        System.out.println("Math.round(-2.5) = " + Math.round(-2.5) + "  <- cuidado com negativos");
        System.out.println("Math.floor(3.7) = " + Math.floor(3.7) + " | Math.ceil(3.2) = " + Math.ceil(3.2) + "  <- devolvem double");
        System.out.println("(int) 3.99 = " + (int) 3.99 + "  <- o cast apenas TRUNCA");
        System.out.printf("String.format(\"%%.2f\", 3.14159) = %.2f%n", 3.14159);
        // (printf e String.format respeitam o idioma do sistema: em um sistema em
        //  português, o resultado acima pode aparecer como 3,14)


        // -------------------------------------------------------------------
        // 4. char - CARACTERE
        // -------------------------------------------------------------------
        titulo("4. char - caractere");

        // char guarda UM caractere, entre aspas SIMPLES. Internamente é um número
        // de 16 bits sem sinal (0 a 65.535): o código Unicode (UTF-16) do caractere.
        char letra = 'A';
        char acentuada = 'é';
        char unicode = '\u0041';      // também é 'A', escrito pelo código
        System.out.println("letra = " + letra + " | acentuada = " + acentuada + " | '\\u0041' = " + unicode);
        System.out.printf("char: %d bits, de %d a %d%n", Character.SIZE, (int) Character.MIN_VALUE, (int) Character.MAX_VALUE);

        // DIFERENÇA: Python e JavaScript não têm char; usam strings de tamanho 1.
        // Em Java, 'A' (char) e "A" (String) são coisas DIFERENTES.

        // Como char é um número, podemos fazer aritmética com ele:
        System.out.println("\n(int) 'A' = " + (int) 'A' + "  <- código Unicode (como ord('A') no Python)");
        System.out.println("(char) 65 = " + (char) 65 + "  <- de volta ao caractere (como chr(65))");
        System.out.println("'A' + 1 = " + ('A' + 1) + "  <- vira int!");
        System.out.println("(char) ('A' + 1) = " + (char) ('A' + 1) + "  <- precisa de cast para voltar a char");
        char proxima = letra;
        proxima++;
        System.out.println("letra++ -> " + proxima);

        // Isso permite, por exemplo, percorrer o alfabeto:
        StringBuilder alfabeto = new StringBuilder();
        for (char c = 'a'; c <= 'e'; c++) {
            alfabeto.append(c);
        }
        System.out.println("for (char c = 'a'; c <= 'e'; c++) -> " + alfabeto);

        // A classe Character tem utilitários:
        System.out.println("\nCharacter.isDigit('7')     -> " + Character.isDigit('7'));
        System.out.println("Character.isLetter('7')    -> " + Character.isLetter('7'));
        System.out.println("Character.toUpperCase('b') -> " + Character.toUpperCase('b'));


        // -------------------------------------------------------------------
        // 5. boolean - VALORES LÓGICOS
        // -------------------------------------------------------------------
        titulo("5. boolean - valores lógicos");

        // Só existem dois valores: true e false (em minúsculas, como em JavaScript).
        boolean verdadeiro = true;
        boolean falso = false;
        System.out.println("verdadeiro: " + verdadeiro + " | falso: " + falso);

        // Comparações produzem booleanos:
        System.out.println("\n5 > 3  -> " + (5 > 3));
        System.out.println("5 == 3 -> " + (5 == 3));
        System.out.println("5 != 3 -> " + (5 != 3));
        // (para comparar Strings NÃO use ==; veja a seção 6)

        // Operadores lógicos: && (e), || (ou), ! (não)
        System.out.println("\ntrue && false -> " + (true && false));
        System.out.println("true || false -> " + (true || false));
        System.out.println("!true         -> " + (!true));

        // DIFERENÇA IMPORTANTE: em Java, boolean NÃO é número e NÃO existe "truthiness".
        //   int n = true + true;      // NÃO COMPILA
        //   if (1) { ... }            // NÃO COMPILA: int cannot be converted to boolean
        //   if (lista) { ... }        // NÃO COMPILA
        // A condição de um if/while precisa ser EXATAMENTE um boolean.
        int[] listaDeTarefas = {};
        if (listaDeTarefas.length == 0) {
            System.out.println("\nA lista está vazia (a condição precisa ser um boolean explícito).");
        }
        String texto = "";
        if (texto.isEmpty()) {
            System.out.println("O texto está vazio (texto.isEmpty(), e não \"if (texto)\").");
        }

        // Para contar quantos itens satisfazem uma condição, precisamos de um laço
        // (não existe o truque de somar booleanos do Python):
        int[] notas = {7, 4, 9, 5, 8};
        int aprovadas = 0;
        for (int nota : notas) {
            if (nota >= 7) {
                aprovadas++;
            }
        }
        System.out.println("Quantas notas >= 7? " + aprovadas);

        // Curto-circuito: && para na primeira condição falsa; || para na primeira verdadeira.
        // Isso evita erros como dividir por zero ou acessar null:
        int divisor = 0;
        boolean seguro = divisor != 0 && 10 / divisor > 1;
        System.out.println("divisor != 0 && 10 / divisor > 1 -> " + seguro + "  (a divisão nem foi executada)");


        // -------------------------------------------------------------------
        // 6. String - TEXTO (UM OBJETO, NÃO UM PRIMITIVO)
        // -------------------------------------------------------------------
        titulo("6. String - texto (um objeto, não um primitivo)");

        // String usa aspas DUPLAS (aspas simples são só para char).
        String simples = "olá";
        String mundo = "mundo";
        System.out.println(simples + " " + mundo);

        // Texto em várias linhas com text blocks (Java 15+):
        String multilinha = """
                Primeira linha
                Segunda linha""";
        System.out.println(multilinha);

        // Caracteres de escape:
        System.out.println("Tabulação:\tfeito | Quebra de linha:\nfeito | Aspas: \"feito\"");

        // Formatação: o equivalente das f-strings é String.format (ou printf).
        String aluno = "João";
        double media = 8.456;
        System.out.println(String.format("%nO aluno %s tirou média %.2f", aluno, media));
        System.out.println("O aluno %s tirou média %.2f".formatted(aluno, media) + "  <- .formatted() é o mesmo (Java 15+)");

        // Operações básicas:
        String frase = "estrutura de dados";
        System.out.println("\nfrase.length()          = " + frase.length());
        System.out.println("frase.toUpperCase()     = " + frase.toUpperCase());
        System.out.println("frase.replace(...)      = " + frase.replace("dados", "informação"));
        System.out.println("frase.split(\" \")        = " + Arrays.toString(frase.split(" ")));
        System.out.println("String.join(\"-\", ...)   = " + String.join("-", "a", "b", "c"));
        System.out.println("frase.contains(\"dados\") -> " + frase.contains("dados"));
        System.out.println("frase + \"!\"             = " + frase + "!");
        System.out.println("\"ab\".repeat(3)          = " + "ab".repeat(3));

        // Indexação e fatiamento. Índices começam em 0.
        //
        //   e  s  t  r  u  t  u  r  a
        //   0  1  2  3  4  5  6  7  8
        String palavra = "estrutura";
        System.out.println("\npalavra.charAt(0)        = " + palavra.charAt(0) + "  <- devolve um char");
        System.out.println("palavra.charAt(length-1) = " + palavra.charAt(palavra.length() - 1) + "  <- não há índice negativo");
        System.out.println("palavra.substring(0, 3)  = " + palavra.substring(0, 3));    // do 0 até o 3 (exclusivo)
        System.out.println("palavra.substring(3)     = " + palavra.substring(3));       // do 3 até o fim
        System.out.println("invertida                = " + new StringBuilder(palavra).reverse());
        //   palavra[0]   // NÃO COMPILA: colchetes são só para arrays

        // DETALHE IMPORTANTE: String é IMUTÁVEL. Não existe forma de alterar um
        // caractere "no lugar"; toda "modificação" cria uma nova String.
        String novaPalavra = "E" + palavra.substring(1);
        System.out.println("\"E\" + palavra.substring(1) = " + novaPalavra + "  (palavra continua sendo " + palavra + ")");
        // Para montar Strings dentro de laços, use StringBuilder (mutável e eficiente).

        // DETALHE MUITO IMPORTANTE: comparar Strings com == é um ERRO CLÁSSICO.
        // == verifica se são o MESMO OBJETO na memória; equals compara o CONTEÚDO.
        String s1 = "java";
        String s2 = "java";
        String s3 = new String("java");
        System.out.println("\ns1 == s2      -> " + (s1 == s2) + "   <- por sorte: literais iguais vão para o \"pool\" de Strings");
        System.out.println("s1 == s3      -> " + (s1 == s3) + "  <- new String criou outro objeto");
        System.out.println("s1.equals(s3) -> " + s1.equals(s3) + "   <- SEMPRE use equals para comparar conteúdo");
        System.out.println("s1.equalsIgnoreCase(\"JAVA\") -> " + s1.equalsIgnoreCase("JAVA"));
        System.out.println("\"a\".compareTo(\"b\") = " + "a".compareTo("b") + "  <- negativo: \"a\" vem antes (ordem alfabética)");


        // -------------------------------------------------------------------
        // 7. null E VALORES PADRÃO
        // -------------------------------------------------------------------
        titulo("7. null e valores padrão");

        // null significa "nenhum objeto". Só variáveis de tipo REFERÊNCIA (String,
        // arrays, objetos) aceitam null. Primitivos NUNCA podem ser null.
        String semNome = null;
        System.out.println("String semNome = null -> " + semNome);
        //   int semValor = null;    // NÃO COMPILA: incompatible types

        // Chamar um método em null lança NullPointerException, o erro mais famoso do Java:
        try {
            System.out.println(semNome.length());
        } catch (NullPointerException erro) {
            System.out.println("semNome.length() lança NullPointerException (não há objeto para chamar o método)");
        }
        // Sempre teste antes: if (semNome != null && ...)

        // VALORES PADRÃO: atributos de classe e posições de arrays recebem um valor
        // inicial automático quando não são inicializados:
        int[] inteiros = new int[3];
        double[] decimais = new double[2];
        boolean[] logicos = new boolean[2];
        char[] caracteres = new char[2];
        String[] textos = new String[2];
        System.out.println("\nnew int[3]     -> " + Arrays.toString(inteiros) + "      (0)");
        System.out.println("new double[2]  -> " + Arrays.toString(decimais) + "     (0.0)");
        System.out.println("new boolean[2] -> " + Arrays.toString(logicos) + " (false)");
        System.out.println("new char[2]    -> " + Arrays.toString(caracteres).replace("\u0000", "\\u0000") + " (o caractere nulo)");
        System.out.println("new String[2]  -> " + Arrays.toString(textos) + "   (null)");
        // Lembre: variáveis LOCAIS (dentro de métodos) NÃO recebem valor padrão e
        // precisam ser inicializadas antes do uso (seção 1).


        // -------------------------------------------------------------------
        // 8. CONVERSÃO ENTRE TIPOS (CASTING)
        // -------------------------------------------------------------------
        titulo("8. Conversão entre tipos (casting)");

        // WIDENING (alargamento): de um tipo menor para um maior é AUTOMÁTICO,
        // pois não há perda de informação:  byte -> short -> int -> long -> float -> double
        int inteiro = 42;
        long longo = inteiro;         // int -> long, automático
        double decimal = inteiro;     // int -> double, automático
        System.out.println("int 42 -> long " + longo + " | -> double " + decimal);

        // NARROWING (estreitamento): de um tipo maior para um menor exige CAST
        // explícito, (tipo), porque PODE haver perda de informação.
        double pi = 3.99;
        int piTruncado = (int) pi;
        System.out.println("(int) 3.99 = " + piTruncado + "  <- perdeu a parte decimal");
        //   int erro = pi;   // NÃO COMPILA: possible lossy conversion from double to int

        // Perda de dados de verdade: o valor não cabe e "dá a volta":
        int grande = 300;
        byte pequeno = (byte) grande;
        System.out.println("(byte) 300 = " + pequeno + "  <- 300 não cabe em 8 bits (máximo 127)");
        long enorme = 3_000_000_000L;
        System.out.println("(int) 3_000_000_000L = " + (int) enorme + "  <- não cabe em 32 bits");

        // String -> número: métodos parse das classes wrapper (seção 9).
        System.out.println("\nInteger.parseInt(\"42\")     = " + Integer.parseInt("42"));
        System.out.println("Double.parseDouble(\"3.14\") = " + Double.parseDouble("3.14"));
        System.out.println("Integer.parseInt(\"1010\", 2) = " + Integer.parseInt("1010", 2) + "  <- o segundo argumento é a base");
        try {
            Integer.parseInt("abc");
        } catch (NumberFormatException erro) {
            System.out.println("Integer.parseInt(\"abc\") lança NumberFormatException: " + erro.getMessage());
        }
        // Integer.parseInt("42px") também lança exceção (diferente do parseInt do JavaScript).

        // Número -> String:
        System.out.println("\nString.valueOf(123)   = \"" + String.valueOf(123) + "\"");
        System.out.println("Integer.toString(123) = \"" + Integer.toString(123) + "\"");
        System.out.println("123 + \"\"              = \"" + (123 + "") + "\"  <- atalho comum: concatenar com vazio");

        // Cuidado clássico: dados digitados pelo usuário SEMPRE chegam como String.
        //
        //   Scanner teclado = new Scanner(System.in);
        //   String idadeTexto = teclado.nextLine();           // String, ex: "20"
        //   int idadeNumero = Integer.parseInt(idadeTexto);   // agora é int: 20
        //   int idadeDireto = teclado.nextInt();              // ou leia direto como int
        //
        // (deixado em comentário para o programa rodar sem interação)


        // -------------------------------------------------------------------
        // 9. CLASSES WRAPPER E AUTOBOXING (Integer, Double...)
        // -------------------------------------------------------------------
        titulo("9. Classes wrapper e autoboxing (Integer, Double...)");

        // Cada primitivo tem uma classe "embrulho" (wrapper) correspondente:
        //   byte -> Byte, short -> Short, int -> Integer, long -> Long,
        //   float -> Float, double -> Double, char -> Character, boolean -> Boolean
        // Elas servem para usar primitivos onde só objetos são aceitos (coleções
        // como ArrayList, por exemplo) e trazem utilitários (parseInt, MAX_VALUE...).

        // AUTOBOXING: a conversão primitivo <-> wrapper é automática.
        Integer caixa = 42;         // int -> Integer (boxing)
        int semCaixa = caixa;       // Integer -> int (unboxing)
        System.out.println("Integer caixa = 42 -> " + caixa + " | int semCaixa = caixa -> " + semCaixa);
        System.out.println("caixa.getClass().getSimpleName() -> " + caixa.getClass().getSimpleName());
        // Só agora dá para "perguntar o tipo" em tempo de execução: primitivos não
        // têm getClass(), só objetos. (O tipo de um primitivo é decidido na compilação.)

        // Wrappers ACEITAM null (primitivos não):
        Integer indefinido = null;
        System.out.println("Integer indefinido = null -> " + indefinido);
        //   int erro = indefinido;   // compila, mas lança NullPointerException ao rodar!

        // ARMADILHA: comparar wrappers com == compara REFERÊNCIAS, não valores.
        // Java guarda em cache os Integer de -128 a 127 (o Python faz o mesmo de -5 a 256).
        Integer c1 = 100;
        Integer c2 = 100;
        Integer g1 = 1000;
        Integer g2 = 1000;
        System.out.println("\nInteger 100 == 100   -> " + (c1 == c2) + "   <- valores pequenos vêm do cache");
        System.out.println("Integer 1000 == 1000 -> " + (g1 == g2) + "  <- objetos diferentes!");
        System.out.println("g1.equals(g2)        -> " + g1.equals(g2) + "   <- use equals (ou compare os int)");


        // -------------------------------------------------------------------
        // 10. VALOR VS. REFERÊNCIA, final E IDENTIDADE
        // -------------------------------------------------------------------
        titulo("10. Valor vs. referência, final e identidade");

        // Primitivos são copiados por VALOR: a cópia é independente do original.
        int original = 10;
        int copia = original;
        copia = 99;
        System.out.println("int copia = original; copia = 99 -> original = " + original + ", copia = " + copia);

        // Objetos (arrays, Strings, etc.) são acessados por REFERÊNCIA: copiar a
        // variável copia o "endereço", e os dois nomes apontam para o mesmo objeto.
        int[] lista1 = {1, 2, 3};
        int[] lista2 = lista1;
        lista2[0] = 99;
        System.out.println("int[] lista2 = lista1; lista2[0] = 99 -> lista1 = " + Arrays.toString(lista1) + "  <- mudou também!");
        System.out.println("lista1 == lista2 -> " + (lista1 == lista2) + "  <- mesmo objeto (como o \"is\" do Python)");
        int[] lista3 = {99, 2, 3};
        System.out.println("lista1 == lista3 -> " + (lista1 == lista3) + " | Arrays.equals(lista1, lista3) -> " + Arrays.equals(lista1, lista3));

        // final: a variável não pode ser REATRIBUÍDA (como o const do JavaScript).
        final int limite = 10;
        //   limite = 20;   // NÃO COMPILA: cannot assign a value to final variable limite
        final int[] fixo = {1, 2};
        fixo[0] = 100;     // permitido! final protege a REFERÊNCIA, não o conteúdo
        System.out.println("\nfinal int limite = 10 -> " + limite + "  (reatribuir não compila)");
        System.out.println("final int[] fixo; fixo[0] = 100 -> " + Arrays.toString(fixo) + "  <- o conteúdo do array pode mudar");

        // Não existe id() como no Python, mas System.identityHashCode dá uma
        // aproximação: o mesmo objeto sempre devolve o mesmo número.
        System.out.println("\nidentityHashCode(lista1) = " + System.identityHashCode(lista1));
        System.out.println("identityHashCode(lista2) = " + System.identityHashCode(lista2) + "  <- igual: mesmo objeto");
        System.out.println("identityHashCode(lista3) = " + System.identityHashCode(lista3) + "  <- diferente");


        // -------------------------------------------------------------------
        // RESUMO
        // -------------------------------------------------------------------
        titulo("RESUMO");
        System.out.println("""

                | Tipo    | Bits | Faixa de valores                 | Padrão   | Exemplo      |
                |---------|------|----------------------------------|----------|--------------|
                | byte    |   8  | -128 a 127                       | 0        | (byte) 100   |
                | short   |  16  | -32.768 a 32.767                 | 0        | (short) 1000 |
                | int     |  32  | -2.147.483.648 a 2.147.483.647   | 0        | 42           |
                | long    |  64  | aprox. -9,2e18 a 9,2e18          | 0L       | 42L          |
                | float   |  32  | aprox. +-3,4e38 (~7 dígitos)     | 0.0f     | 3.14f        |
                | double  |  64  | aprox. +-1,8e308 (~15 dígitos)   | 0.0      | 3.14         |
                | char    |  16  | 0 a 65.535 (códigos Unicode)     | '\\u0000' | 'A'          |
                | boolean |   -  | true ou false                    | false    | true         |

                String NÃO é primitivo: é um objeto imutável. Compare com equals, nunca com ==.

                Comparação rápida:

                  Python              | JavaScript          | Java
                  --------------------|---------------------|-------------------------------------
                  tipagem dinâmica    | tipagem dinâmica    | tipagem ESTÁTICA (declara o tipo)
                  int ilimitado       | number + bigint     | byte/short/int/long fixos + BigInteger
                  float               | number              | float e double
                  str de tamanho 1    | string de tamanho 1 | char (numérico, 16 bits)
                  None                | undefined e null    | null (só para objetos)
                  bool([]) é False    | Boolean([]) é true  | if exige boolean (senão não compila)
                  -17 % 5 == 3        | -17 % 5 === -2      | -17 % 5 == -2
                  17 / 5 == 3.4       | 17 / 5 === 3.4      | 17 / 5 == 3 (divisão inteira!)
                  == compara valor    | === compara valor   | == compara valor (primitivos) ou
                  is compara identid. | (objetos: referên.) |    referência (objetos); use equals

                Próximos passos: na próxima aula veremos as COLEÇÕES do Java
                (arrays, ArrayList, HashSet e HashMap), que são a base de todas as
                estruturas de dados que estudaremos na disciplina.
                """);
    }
}
