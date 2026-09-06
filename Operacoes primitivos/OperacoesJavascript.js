// =====================================================
// Código simples em Javascript de soma, multiplicação e valor boleano.
// Segue a mesma linha de lógica do escrito em python, mas com sintaxe diferente.
// =====================================================

const a = 8;
const b = 3;
const x = 2.5;
let resultadoPositivo;

const soma = a + b;
const multiplicacao = x * a;
resultadoPositivo = soma > 0;

console.log("Soma: " + soma);
console.log("Multiplicacao: " + multiplicacao);
console.log("Resultado positivo? " + resultadoPositivo);

// =====================================================
// Uma array criada em Javascript para soma dos números na const "numeros"
// =====================================================

const numeros = [1, 2, 3, 4];
const somaArray = numeros[0] + numeros[1] + numeros[2] + numeros[3];

console.log("Soma do array: " + somaArray);