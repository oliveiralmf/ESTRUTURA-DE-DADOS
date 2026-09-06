"use strict";

/*
AULA 01 - TIPOS PRIMITIVOS EM JAVASCRIPT
=========================================

Disciplina: Estrutura de Dados
Curso: Engenharia de Software

Antes de estudar estruturas de dados (listas, pilhas, filas, árvores...),
precisamos entender os "tijolos" a partir dos quais tudo é construído:
os tipos primitivos.

Em JavaScript existem exatamente 7 tipos primitivos:

    number    -> números (inteiros E decimais)   (ex: 42, -7, 3.14)
    bigint    -> inteiros de precisão arbitrária (ex: 12345678901234567890n)
    string    -> texto (cadeia de caracteres)    (ex: "olá", 'mundo', `oi`)
    boolean   -> valores lógicos                 (true ou false)
    undefined -> valor ainda não definido
    null      -> ausência intencional de valor
    symbol    -> identificador único             (ex: Symbol("id"))

Tudo o que NÃO é primitivo é um objeto (arrays, funções, datas, etc.).

Execute este arquivo para ver os exemplos em ação:

    node tipos-primitivos.js

Dica: leia os comentários com calma. Cada seção explica um conceito e
mostra o resultado no terminal. Sempre que houver uma diferença importante
em relação ao Python (veja tipos-primitivos.py), ela será destacada.
*/

function titulo(texto) {
  console.log();
  console.log("=".repeat(70));
  console.log(texto);
  console.log("=".repeat(70));
}


// ---------------------------------------------------------------------------
// 1. TIPAGEM DINÂMICA E O OPERADOR typeof
// ---------------------------------------------------------------------------
titulo("1. Tipagem dinâmica e o operador typeof");

// Em JavaScript também NÃO declaramos o tipo da variável. Usamos let (ou const)
// e o interpretador descobre o tipo a partir do valor atribuído.
let idade = 20;
let altura = 1.75;
let nome = "Maria";
let aprovado = true;
let notaFinal;          // declarada, mas sem valor -> undefined
let telefone = null;    // ausência intencional de valor

// O operador typeof nos diz qual é o tipo de um valor (como uma string).
console.log(`idade     = ${idade}        -> ${typeof idade}`);
console.log(`altura    = ${altura}      -> ${typeof altura}`);
console.log(`nome      = ${nome}     -> ${typeof nome}`);
console.log(`aprovado  = ${aprovado}      -> ${typeof aprovado}`);
console.log(`notaFinal = ${notaFinal} -> ${typeof notaFinal}`);
console.log(`telefone  = ${telefone}      -> ${typeof telefone}  <- (!) veja a seção 6`);

// A variável é apenas um "rótulo": pode apontar para tipos diferentes ao longo
// do programa (embora isso não seja uma boa prática).
let x = 10;
console.log(`\nx = ${x} -> ${typeof x}`);
x = "agora sou texto";
console.log(`x = ${x} -> ${typeof x}`);

// DIFERENÇA IMPORTANTE em relação ao Python: JavaScript é "fracamente tipado".
// Em vez de gerar erro, ele tenta CONVERTER os tipos sozinho (coerção).
console.log(`\n1 + "1" = ${1 + "1"}  <- não dá erro: o número virou texto!`);
console.log(`typeof (1 + "1") -> ${typeof (1 + "1")}`);
// Isso é fonte de muitos bugs. Voltaremos à coerção na seção 8.


// ---------------------------------------------------------------------------
// 2. number - NÚMEROS (INTEIROS E DECIMAIS)
// ---------------------------------------------------------------------------
titulo("2. number - números (inteiros e decimais)");

// DIFERENÇA IMPORTANTE: JavaScript tem UM ÚNICO tipo numérico. Não existe a
// separação entre int e float do Python. Todo number é um "float" de 64 bits
// (padrão IEEE 754), o mesmo formato do float do Python.
console.log(`typeof 42   -> ${typeof 42}`);
console.log(`typeof 3.14 -> ${typeof 3.14}`);
console.log(`42 === 42.0 -> ${42 === 42.0}  <- são exatamente o mesmo valor`);

// Literais: _ para separar milhares
const populacaoBrasil = 203_000_000;

// Operações aritméticas:
const a = 17;
const b = 5;
console.log(`\n${a} + ${b}  = ${a + b}`);    // adição
console.log(`${a} - ${b}  = ${a - b}`);      // subtração
console.log(`${a} * ${b}  = ${a * b}`);      // multiplicação
console.log(`${a} / ${b}  = ${a / b}`);      // divisão
console.log(`${a} % ${b}  = ${a % b}`);      // resto da divisão (módulo)
console.log(`${a} ** ${b} = ${a ** b}`);     // potenciação

// DIFERENÇA: não existe o operador // de divisão inteira. Use Math.trunc.
console.log(`Math.trunc(${a} / ${b}) = ${Math.trunc(a / b)}  <- divisão inteira`);

// DIFERENÇA: o resto (%) tem o sinal do DIVIDENDO, não do divisor como em Python.
console.log(`-17 % 5 = ${-17 % 5}  (em Python seria 3)`);

// Valores especiais: Infinity, -Infinity e NaN (Not a Number).
console.log(`\n1 / 0 = ${1 / 0}  <- não gera erro (em Python seria ZeroDivisionError)`);
console.log(`-1 / 0 = ${-1 / 0}`);
console.log(`0 / 0 = ${0 / 0}`);
console.log(`"abc" * 2 = ${"abc" * 2}`);
console.log(`typeof NaN -> ${typeof NaN}  <- NaN é um number!`);
console.log(`NaN === NaN -> ${NaN === NaN}  <- NaN é diferente de tudo, inclusive dele mesmo`);
console.log(`Number.isNaN(NaN) -> ${Number.isNaN(NaN)}  <- a forma correta de testar`);

// Arredondamento:
console.log(`\nMath.round(2.5) = ${Math.round(2.5)} | Math.round(3.5) = ${Math.round(3.5)}  <- .5 sobe (Python vai para o par)`);
console.log(`Math.round(-2.5) = ${Math.round(-2.5)}  <- cuidado com negativos`);
console.log(`Math.floor(3.7) = ${Math.floor(3.7)} | Math.ceil(3.2) = ${Math.ceil(3.2)} | Math.trunc(-3.7) = ${Math.trunc(-3.7)}`);
console.log(`(3.14159).toFixed(2) = ${(3.14159).toFixed(2)}`);


// ---------------------------------------------------------------------------
// 3. bigint - INTEIROS DE PRECISÃO ARBITRÁRIA
// ---------------------------------------------------------------------------
titulo("3. bigint - inteiros de precisão arbitrária");

// O sufixo n cria um bigint. Ele não tem limite de tamanho (como o int do Python).
const grande = 9007199254740993n;
console.log(`typeof 42n -> ${typeof 42n}`);
console.log(`9007199254740993n = ${grande}  <- agora o último dígito está certo`);
console.log(`2n ** 100n = ${2n ** 100n}`);

let fatorialDe30 = 1n;
for (let i = 1n; i <= 30n; i++) {
  fatorialDe30 *= i;
}
console.log(`30! = ${fatorialDe30}`);

// DETALHE: bigint e number NÃO se misturam. É preciso converter explicitamente.
try {
  const soma = 1n + 1;
  console.log(soma);
} catch (erro) {
  console.log(`\n1n + 1 gera TypeError: ${erro.message}`);
}
console.log(`1n + BigInt(1) = ${1n + BigInt(1)}`);
console.log(`Number(10n) + 1 = ${Number(10n) + 1}`);

// A divisão de bigint descarta a parte decimal (como o // do Python):
console.log(`7n / 2n = ${7n / 2n}`);

// Comparações: == compara só o valor; === compara valor E tipo.
console.log(`1n == 1  -> ${1n == 1}`);
console.log(`1n === 1 -> ${1n === 1}`);


// ---------------------------------------------------------------------------
// 4. boolean - VALORES LÓGICOS
// ---------------------------------------------------------------------------
titulo("4. boolean - valores lógicos");

// Só existem dois valores: true e false (em minúsculas, diferente do Python).
const verdadeiro = true;
const falso = false;
console.log(`verdadeiro: ${verdadeiro} | falso: ${falso} -> ${typeof verdadeiro}`);

// Comparações produzem booleanos. Prefira SEMPRE === e !== (veja a seção 8).
console.log(`\n5 > 3     -> ${5 > 3}`);
console.log(`5 === 3   -> ${5 === 3}`);
console.log(`5 !== 3   -> ${5 !== 3}`);
console.log(`"a" < "b" -> ${"a" < "b"}  (strings comparam pela ordem dos caracteres)`);

// Operadores lógicos: && (e), || (ou), ! (não)
console.log(`\ntrue && false -> ${true && false}`);
console.log(`true || false -> ${true || false}`);
console.log(`!true         -> ${!true}`);

// DIFERENÇA: boolean NÃO é subclasse de number. Mas, por coerção, true vira 1
// e false vira 0 dentro de operações aritméticas.
console.log(`\ntrue + true = ${true + true}`);
const notas = [7, 4, 9, 5, 8];
console.log(`Quantas notas >= 7? ${notas.filter((nota) => nota >= 7).length}`);

// "Truthiness": qualquer valor pode ser avaliado como booleano.
// São FALSOS (falsy): false, 0, -0, 0n, "", null, undefined e NaN.
// TODO o resto é verdadeiro (truthy).
console.log(`\nBoolean(0) = ${Boolean(0)} | Boolean(42) = ${Boolean(42)}`);
console.log(`Boolean("") = ${Boolean("")} | Boolean("oi") = ${Boolean("oi")}`);
console.log(`Boolean(null) = ${Boolean(null)} | Boolean(undefined) = ${Boolean(undefined)}`);
console.log(`Boolean(NaN) = ${Boolean(NaN)}`);

// DIFERENÇA IMPORTANTE: arrays e objetos vazios são VERDADEIROS em JavaScript!
// Em Python, [] e {} são falsos.
console.log(`Boolean([]) = ${Boolean([])} | Boolean({}) = ${Boolean({})}  <- (!)`);
console.log(`Boolean("0") = ${Boolean("0")} | Boolean("false") = ${Boolean("false")}  <- string não vazia é sempre true`);

// Por isso, para testar se uma lista está vazia, verifique o length:
const listaDeTarefas = [];
if (listaDeTarefas.length === 0) {
  console.log("A lista está vazia (testamos o length, não a lista em si).");
}

// O atalho !! converte qualquer valor para boolean:
console.log(`!!"texto" = ${!!"texto"} | !!0 = ${!!0}`);


// ---------------------------------------------------------------------------
// 5. string - TEXTO (CADEIA DE CARACTERES)
// ---------------------------------------------------------------------------
titulo("5. string - texto");

// Strings podem usar aspas simples, duplas ou crases (template literals).
const simples = 'olá';
const duplas = "mundo";
const crase = `oi`;
console.log(`${simples} ${duplas} ${crase} -> ${typeof simples}`);

// Template literals (crases) aceitam expressões com ${...} e várias linhas.
// São o equivalente às f-strings do Python e a forma mais usada neste arquivo.
const aluno = "João";
const media = 8.456;
console.log(`\nO aluno ${aluno} tirou média ${media.toFixed(2)}`);

const multilinha = `Primeira linha
Segunda linha`;
console.log(multilinha);

// Caracteres de escape:
console.log("Tabulação:\tfeito | Quebra de linha:\nfeito | Aspas: \"feito\"");

// Operações básicas:
const frase = "estrutura de dados";
console.log(`\nfrase.length            = ${frase.length}`);                     // tamanho
console.log(`frase.toUpperCase()     = ${frase.toUpperCase()}`);               // maiúsculas
console.log(`frase.replace(...)      = ${frase.replace("dados", "informação")}`);
console.log(`frase.split(" ")        = ${JSON.stringify(frase.split(" "))}`);   // quebra em array
console.log(`["a","b","c"].join("-") = ${["a", "b", "c"].join("-")}`);
console.log(`frase.includes("dados") -> ${frase.includes("dados")}`);          // verifica se contém
console.log(`frase + "!"             = ${frase + "!"}`);                       // concatenação
console.log(`"ab".repeat(3)          = ${"ab".repeat(3)}`);                    // repetição

// Indexação e fatiamento. Índices começam em 0.
//
//   e  s  t  r  u  t  u  r  a
//   0  1  2  3  4  5  6  7  8
const palavra = "estrutura";
console.log(`\npalavra[0]                  = ${palavra[0]}`);                   // primeiro caractere
console.log(`palavra.at(-1)              = ${palavra.at(-1)}`);                // último (índice negativo só com .at)
console.log(`palavra[palavra.length - 1] = ${palavra[palavra.length - 1]}`);   // forma clássica
console.log(`palavra.slice(0, 3)         = ${palavra.slice(0, 3)}`);           // do 0 até o 3 (exclusivo)
console.log(`palavra.slice(3)            = ${palavra.slice(3)}`);              // do 3 até o fim
console.log(`palavra.slice(-3)           = ${palavra.slice(-3)}`);             // os 3 últimos
// DIFERENÇA: não existe "passo" (::2) nem inversão (::-1) direto na string.
console.log(`invertida                   = ${[...palavra].reverse().join("")}`); // string -> array -> string

// DETALHE IMPORTANTE: strings são IMUTÁVEIS, como em Python.
// Com "use strict" (topo do arquivo) a tentativa gera erro; sem ele, falha em silêncio.
try {
  palavra[0] = "E";
} catch (erro) {
  console.log(`\nTentar alterar palavra[0] gera TypeError: ${erro.message}`);
}
const novaPalavra = "E" + palavra.slice(1);
console.log(`Forma correta (cria nova string): ${novaPalavra}`);

// Código numérico dos caracteres (Unicode):
console.log(`\n"A".charCodeAt(0) = ${"A".charCodeAt(0)} | String.fromCharCode(65) = ${String.fromCharCode(65)} | "a".charCodeAt(0) = ${"a".charCodeAt(0)}`);


// ---------------------------------------------------------------------------
// 6. undefined E null - AUSÊNCIA DE VALOR
// ---------------------------------------------------------------------------
titulo("6. undefined e null - ausência de valor");

// JavaScript tem DOIS valores para "nada", enquanto Python tem só o None:
//   undefined -> "ainda não foi definido" (o próprio JavaScript coloca)
//   null      -> "vazio de propósito"     (o programador coloca)
let semValor;
const vazio = null;
console.log(`semValor = ${semValor} -> ${typeof semValor}`);
console.log(`vazio    = ${vazio}      -> ${typeof vazio}  <- (!) bug histórico da linguagem`);
// typeof null retorna "object" por um erro na primeira versão do JavaScript,
// que nunca foi corrigido para não quebrar programas antigos.

// Onde undefined aparece sozinho:
const pessoa = { nome: "Ana" };
function semRetorno() {}
console.log(`\npessoa.idade (não existe) -> ${pessoa.idade}`);
console.log(`semRetorno() retorna      -> ${semRetorno()}`);

// Comparando: == considera os dois iguais; === diferencia.
console.log(`\nnull == undefined  -> ${null == undefined}`);
console.log(`null === undefined -> ${null === undefined}`);
console.log(`null === null      -> ${null === null}  <- a forma de testar null`);

// Como em Python, null e undefined são diferentes de 0, "" e false:
console.log(`null === 0 -> ${null === 0} | null === "" -> ${null === ""} | null === false -> ${null === false}`);

// O operador ?? devolve um valor padrão apenas se o da esquerda for null/undefined:
const apelido = undefined;
console.log(`\napelido ?? "sem apelido" -> ${apelido ?? "sem apelido"}`);
console.log(`0 ?? 10 -> ${0 ?? 10}  (0 não é null/undefined, então é mantido)`);
console.log(`0 || 10 -> ${0 || 10}  (|| troca qualquer valor falsy)`);


// ---------------------------------------------------------------------------
// 7. symbol - IDENTIFICADOR ÚNICO
// ---------------------------------------------------------------------------
titulo("7. symbol - identificador único (menos comum)");

// Cada Symbol() é único, mesmo com a mesma descrição. É usado principalmente
// como chave "escondida" de propriedades de objetos.
const s1 = Symbol("id");
const s2 = Symbol("id");
console.log(`typeof s1 -> ${typeof s1}`);
console.log(`s1 === s2 -> ${s1 === s2}  <- descrições iguais, símbolos diferentes`);
console.log(`s1.toString() = ${s1.toString()}`);
// Você raramente usará symbol em estrutura de dados, mas é bom saber que existe.


// ---------------------------------------------------------------------------
// 8. CONVERSÃO ENTRE TIPOS (CASTING E COERÇÃO)
// ---------------------------------------------------------------------------
titulo("8. Conversão entre tipos (casting e coerção)");

// Conversão EXPLÍCITA: usamos String(), Number(), Boolean() e BigInt().
console.log(`String(123)      = ${JSON.stringify(String(123))}  (JSON.stringify mostra as aspas)`);
console.log(`(123).toString() = ${JSON.stringify((123).toString())}`);
console.log(`Number("3.14")   = ${Number("3.14")}`);
console.log(`Number("42")     = ${Number("42")}`);
console.log(`Number("")       = ${Number("")}  <- (!) string vazia vira 0`);
console.log(`Number("abc")    = ${Number("abc")}  <- não gera erro, vira NaN`);
console.log(`Number(true)     = ${Number(true)}`);
console.log(`Number(null)     = ${Number(null)} | Number(undefined) = ${Number(undefined)}`);
console.log(`Boolean(0)       = ${Boolean(0)}`);

// parseInt e parseFloat leem o número do INÍCIO da string e ignoram o resto:
console.log(`\nparseInt("42px")    = ${parseInt("42px")}`);
console.log(`parseInt("3.99")    = ${parseInt("3.99")}  <- trunca`);
console.log(`parseFloat("3.99m") = ${parseFloat("3.99m")}`);
console.log(`parseInt("abc")     = ${parseInt("abc")}`);
console.log(`parseInt("1010", 2) = ${parseInt("1010", 2)}  <- o segundo argumento é a base`);

// Conversão IMPLÍCITA (coerção): o JavaScript converte sozinho, e nem sempre
// como você espera. Esta é a maior armadilha da linguagem.
console.log(`\n"5" + 1   = ${"5" + 1}  <- + com string CONCATENA`);
console.log(`"5" - 1   = ${"5" - 1}  <- - converte para número`);
console.log(`"5" * "2" = ${"5" * "2"}`);
console.log(`true + 1  = ${true + 1}`);
console.log(`"1" == 1  -> ${"1" == 1}  <- == converte antes de comparar`);
console.log(`"1" === 1 -> ${"1" === 1}  <- === NÃO converte: compara tipo e valor`);
console.log(`0 == ""   -> ${0 == ""} | 0 == "0" -> ${0 == "0"} | "" == "0" -> ${"" == "0"}  <- (!) nem transitivo é`);
// REGRA: use sempre === e !==. Deixe == de lado.

// Cuidado clássico: dados digitados pelo usuário SEMPRE chegam como string.
// No navegador, prompt() devolve string. No Node, readline também.
//
//   const idade = prompt("Sua idade: ");          // idade é string, ex: "20"
//   const idade = Number(prompt("Sua idade: "));  // agora idade é number: 20
//
// (deixado em comentário para o script rodar sem interação)


// ---------------------------------------------------------------------------
// 9. VERIFICANDO TIPOS DA FORMA CORRETA
// ---------------------------------------------------------------------------
titulo("9. Verificando tipos da forma correta");

// typeof funciona bem para primitivos, com a exceção do null.
console.log(`typeof 10        -> ${typeof 10}`);
console.log(`typeof "10"      -> ${typeof "10"}`);
console.log(`typeof null      -> ${typeof null}  <- armadilha: teste com valor === null`);
console.log(`typeof []        -> ${typeof []}  <- arrays também são objetos`);
console.log(`typeof {}        -> ${typeof {}}`);
console.log(`typeof (() => 1) -> ${typeof (() => 1)}`);

// Testes específicos:
console.log(`\nArray.isArray([])           -> ${Array.isArray([])}`);
console.log(`Number.isInteger(10)        -> ${Number.isInteger(10)}`);
console.log(`Number.isInteger(10.0)      -> ${Number.isInteger(10.0)}  <- 10 e 10.0 são o mesmo number`);
console.log(`Number.isInteger(10.5)      -> ${Number.isInteger(10.5)}`);
console.log(`Number.isNaN("abc")         -> ${Number.isNaN("abc")}  <- só é true para o valor NaN em si`);
console.log(`Number.isNaN(Number("abc")) -> ${Number.isNaN(Number("abc"))}`);


// ---------------------------------------------------------------------------
// 10. IMUTABILIDADE, VALOR E REFERÊNCIA (const, let, ===)
// ---------------------------------------------------------------------------
titulo("10. Imutabilidade, valor e referência (const, let, ===)");

// Todos os 7 tipos primitivos são IMUTÁVEIS: o valor em si nunca muda.
// "Alterar" uma variável significa fazê-la apontar para um NOVO valor.

// let permite reatribuir; const NÃO permite.
let contador = 5;
contador = contador + 1;
console.log(`let contador -> ${contador}  (reatribuição permitida)`);

const limite = 10;
try {
  limite = 20;
} catch (erro) {
  console.log(`const limite = 20 gera TypeError: ${erro.message}`);
}
// var é a forma antiga (anterior a 2015) e deve ser evitada: ela ignora blocos {}
// e causa bugs sutis. Use const por padrão e let só quando precisar reatribuir.

// Primitivos são comparados por VALOR. Objetos são comparados por REFERÊNCIA
// (o "endereço" na memória), parecido com o "is" do Python.

// DIFERENÇA: não existe id() em JavaScript. A identidade de um objeto só é
// observável comparando com ===.

// Object.is é como ===, mas trata os casos especiais NaN e -0 de forma exata:
console.log(`\nNaN === NaN -> ${NaN === NaN} | Object.is(NaN, NaN) -> ${Object.is(NaN, NaN)}`);
console.log(`0 === -0    -> ${0 === -0} | Object.is(0, -0)     -> ${Object.is(0, -0)}`);

// ---------------------------------------------------------------------------
// RESUMO
// ---------------------------------------------------------------------------
titulo("RESUMO");
console.log(`
| Tipo      | Exemplo             | typeof       | Observação principal                        |
|-----------|---------------------|--------------|---------------------------------------------|
| number    | 42, 3.14, NaN       | "number"     | Único tipo numérico; exato só até 2^53 - 1  |
| bigint    | 42n, 2n ** 100n     | "bigint"     | Precisão arbitrária; não mistura com number |
| string    | "olá", 'oi', \`oi\`   | "string"     | Imutável; use crases para interpolar        |
| boolean   | true, false         | "boolean"    | [] e {} são truthy (diferente do Python)    |
| undefined | undefined           | "undefined"  | "Ainda não definido"; a linguagem coloca    |
| null      | null                | "object" (!) | "Vazio de propósito"; o programador coloca  |
| symbol    | Symbol("id")        | "symbol"     | Identificador único; raramente usado        |

Comparação rápida com Python:

  Python                   | JavaScript
  -------------------------|-----------------------------------
  int e float separados    | number (um só) + bigint
  True / False             | true / false
  None                     | undefined e null
  ==  (valor)              | === (valor e tipo); evite ==
  is  (identidade)         | === em objetos compara referência
  -17 % 5 == 3             | -17 % 5 === -2
  bool([]) é False         | Boolean([]) é true
  1 + "1" gera TypeError   | 1 + "1" vira "11"

Próximos passos: na próxima aula veremos as COLEÇÕES nativas do JavaScript
(Array, Object, Set e Map), que são a base de todas as estruturas de dados
que estudaremos na disciplina.
`);
