"""
AULA 01 - TIPOS PRIMITIVOS EM PYTHON
=====================================

Disciplina: Estrutura de Dados
Curso: Engenharia de Software

Antes de estudar estruturas de dados (listas, pilhas, filas, árvores...),
precisamos entender os "tijolos" a partir dos quais tudo é construído:
os tipos primitivos (também chamados de tipos básicos ou built-in).

Em Python, os tipos primitivos mais importantes são:

    int      -> números inteiros            (ex: 42, -7, 0)
    float    -> números de ponto flutuante  (ex: 3.14, -0.5, 2.0)
    complex  -> números complexos           (ex: 2 + 3j)
    bool     -> valores lógicos             (True ou False)
    str      -> texto (cadeia de caracteres)(ex: "ola", 'mundo')
    NoneType -> ausência de valor           (None)

Execute este arquivo para ver os exemplos em ação:

    python3 tipos-primitivos.py

Dica: leia os comentários com calma. Cada seção explica um conceito e
mostra o resultado no terminal.
"""


def titulo(texto: str) -> None:
    """Imprime um título de seção para organizar a saída no terminal."""
    print()
    print("=" * 70)
    print(texto.center(70)) 
    print("=" * 70)


# ---------------------------------------------------------------------------
# 1. TIPAGEM DINÂMICA E A FUNÇÃO type()
# ---------------------------------------------------------------------------
titulo("1. Tipagem dinâmica e a função type()")

# Em Python NÃO declaramos o tipo da variável. O interpretador descobre o tipo
# a partir do valor atribuído. Isso se chama "tipagem dinâmica".
idade = 20
altura = 1.75
nome = "Maria"
aprovado = True
nota_final = None

# A função type() nos diz qual é o tipo de um valor.
print("idade       =", idade, "      ->", type(idade))
print("altura      =", altura, "    ->", type(altura))
print("nome        =", nome, "   ->", type(nome))
print("aprovado    =", aprovado, "    ->", type(aprovado))
print("nota_final  =", nota_final, "    ->", type(nota_final))

# Importante: a variável é apenas um "rótulo" que aponta para um valor.
# A mesma variável pode apontar para valores de tipos diferentes ao longo
# do programa (embora isso não seja uma boa prática).
x = 10
print("\nx =", x, "->", type(x))
x = "agora sou texto"
print("x =", x, "->", type(x))

# Python é "fortemente tipado": ele NÃO converte tipos automaticamente
# quando isso não faz sentido. Somar int com str gera erro.
try:
    resultado = 1 + "1"
except TypeError as erro:
    print("\nSomar int com str gera TypeError:", erro)


# ---------------------------------------------------------------------------
# 2. int - NÚMEROS INTEIROS
# ---------------------------------------------------------------------------
titulo("2. int - números inteiros")

# Inteiros representam números sem parte decimal, positivos ou negativos.
positivo = 42
negativo = -7
zero = 0
print("positivo:", positivo, "| negativo:", negativo, "| zero:", zero)

# DETALHE IMPORTANTE: em Python o int tem precisão arbitrária.
# Em linguagens como C ou Java, um int tem tamanho fixo (32 ou 64 bits) e
# "estoura" quando passa do limite. Em Python, o único limite é a memória.
fatorial_de_30 = 1
for i in range(1, 31):
    fatorial_de_30 *= i
print("30! =", fatorial_de_30)
print("2 ** 100 =", 2 ** 100)

# Podemos usar underline (_) para separar milhares e facilitar a leitura.
populacao_brasil = 203_000_000
print("populacao_brasil:", populacao_brasil)

# Operações aritméticas com inteiros:
a, b = 17, 5
print(f"\n{a} + {b}  =", a + b)     # adição
print(f"{a} - {b}  =", a - b)       # subtração
print(f"{a} * {b}  =", a * b)       # multiplicação
print(f"{a} / {b}  =", a / b)       # divisão -> SEMPRE retorna float!
print(f"{a} // {b} =", a // b)      # divisão inteira (descarta a parte decimal)
print(f"{a} % {b}  =", a % b)       # resto da divisão (módulo)
print(f"{a} ** {b} =", a ** b)      # potenciação

# Cuidado: a divisão inteira arredonda "para baixo" (em direção a -infinito),
# o que surpreende com números negativos.
print("-17 // 5 =", -17 // 5, "(e não -3!)")
print("-17 % 5  =", -17 % 5, "(o resto tem o sinal do divisor)")


# ---------------------------------------------------------------------------
# 3. float - NÚMEROS DE PONTO FLUTUANTE
# ---------------------------------------------------------------------------
titulo("3. float - números de ponto flutuante")

# Floats representam números com parte decimal.
pi = 3.14159
temperatura = -3.5
inteiro_como_float = 2.0   # o ".0" já faz ser float
print("pi:", pi, "| temperatura:", temperatura, "| 2.0 ->", type(inteiro_como_float))

# Notação científica: 1.5e3 significa 1.5 x 10^3
print("1.5e3  =", 1.5e3)
print("2.5e-4 =", 2.5e-4)

# DETALHE IMPORTANTE: floats NÃO são exatos!
# O computador armazena floats em binário),
# e muitos números decimais não têm representação binária exata.
print("\n0.1 + 0.2 =", 0.1 + 0.2)
print("0.1 + 0.2 == 0.3 ?", 0.1 + 0.2 == 0.3)

# Por isso, NUNCA compare floats com == diretamente. Use uma tolerância:
import math
print("math.isclose(0.1 + 0.2, 0.3) ?", math.isclose(0.1 + 0.2, 0.3))

# Ou, quando precisar de exatidão (ex: dinheiro), use o módulo decimal:
from decimal import Decimal
print("Decimal('0.1') + Decimal('0.2') =", Decimal("0.1") + Decimal("0.2"))

# Floats têm um limite de tamanho (diferente do int):
import sys
print("\nMaior float possível:", sys.float_info.max)
print("Passou do limite vira infinito:", 1e308 * 10)
print("float('inf') e float('nan') também existem:", float("inf"), float("nan"))

# Arredondamento:
print("\nround(3.14159, 2) =", round(3.14159, 2))
print("round(2.5) =", round(2.5), "| round(3.5) =", round(3.5),
      "  <- arredonda para o par mais próximo (banker's rounding)")
print("int(3.99) =", int(3.99), "  <- int() apenas TRUNCA, não arredonda")
print("math.floor(3.7) =", math.floor(3.7), "| math.ceil(3.2) =", math.ceil(3.2))


# ---------------------------------------------------------------------------
# 4. complex - NÚMEROS COMPLEXOS
# ---------------------------------------------------------------------------
titulo("4. complex - números complexos (menos comum)")

# Python tem suporte nativo a números complexos. A parte imaginária usa "j".
z = 2 + 3j
print("z =", z, "->", type(z))
print("parte real:", z.real, "| parte imaginária:", z.imag)
print("conjugado:", z.conjugate())
print("(1 + 2j) * (3 - 1j) =", (1 + 2j) * (3 - 1j))
# Você raramente usará complex em estrutura de dados, mas é bom saber que existe.


# ---------------------------------------------------------------------------
# 5. bool - VALORES LÓGICOS
# ---------------------------------------------------------------------------
titulo("5. bool - valores lógicos")

# Só existem dois valores: True e False (com a primeira letra MAIÚSCULA).
verdadeiro = True
falso = False
print("verdadeiro:", verdadeiro, "| falso:", falso, "->", type(verdadeiro))

# Comparações produzem booleanos:
print("\n5 > 3   ->", 5 > 3)
print("5 == 3  ->", 5 == 3)
print("5 != 3  ->", 5 != 3)
print("'a' < 'b' ->", "a" < "b", "  (strings comparam pela ordem dos caracteres)")

# Operadores lógicos: and, or, not
print("\nTrue and False ->", True and False)
print("True or False  ->", True or False)
print("not True       ->", not True)

# DETALHE IMPORTANTE: bool é uma SUBCLASSE de int.
# True vale 1 e False vale 0. Isso permite truques como contar quantos
# itens satisfazem uma condição usando sum().
print("\nTrue + True =", True + True)
print("isinstance(True, int) ->", isinstance(True, int))
notas = [7, 4, 9, 5, 8]
print("Quantas notas >= 7?", sum(nota >= 7 for nota in notas))

# "Truthiness": qualquer valor pode ser avaliado como booleano.
# São considerados FALSOS: 0, 0.0, "", None, e coleções vazias ([], {}, ()).
# Todo o resto é considerado VERDADEIRO.
print("\nbool(0) =", bool(0), "| bool(42) =", bool(42))
print("bool('') =", bool(""), "| bool('oi') =", bool("oi"))
print("bool(None) =", bool(None))
print("bool([]) =", bool([]), "| bool([1, 2]) =", bool([1, 2]))

# Isso é muito usado em condicionais:
lista_de_tarefas = []
if not lista_de_tarefas:
    print("A lista está vazia (avaliada como False no if).")


# ---------------------------------------------------------------------------
# 6. str - TEXTO (CADEIA DE CARACTERES)
# ---------------------------------------------------------------------------
titulo("6. str - texto")

# Strings podem usar aspas simples ou duplas. Não há diferença.
simples = 'olá'
duplas = "mundo"
print(simples, duplas, "->", type(simples))

# Aspas triplas permitem texto em várias linhas:
multilinha = """Primeira linha
Segunda linha"""
print(multilinha)

# Caracteres de escape:
print("Tabulação:\tfeito | Quebra de linha:\nfeito | Aspas: \"feito\"")

# f-strings: a forma moderna de montar textos com variáveis (Python 3.6+):
aluno = "João"
media = 8.456
print(f"\nO aluno {aluno} tirou média {media:.2f}")   # :.2f -> 2 casas decimais

# Operações básicas:
frase = "estrutura de dados"
print("\nlen(frase)          =", len(frase))          # tamanho
print("frase.upper()       =", frase.upper())         # maiúsculas
print("frase.title()       =", frase.title())         # Primeira Letra De Cada Palavra
print("frase.replace(...)  =", frase.replace("dados", "informação"))
print("frase.split()       =", frase.split())         # quebra em lista de palavras
print("'-'.join([...])     =", "-".join(["a", "b", "c"]))
print("'dados' in frase    ->", "dados" in frase)     # verifica se contém
print("frase + '!'         =", frase + "!")           # concatenação
print("'ab' * 3            =", "ab" * 3)              # repetição

# Indexação e fatiamento (slicing) - conceito ESSENCIAL para estrutura de dados.
# Índices começam em 0. Índices negativos contam a partir do fim.
#
#   e  s  t  r  u  t  u  r  a
#   0  1  2  3  4  5  6  7  8
#  -9 -8 -7 -6 -5 -4 -3 -2 -1
palavra = "estrutura"
print("\npalavra[0]    =", palavra[0])      # primeiro caractere
print("palavra[-1]   =", palavra[-1])       # último caractere
print("palavra[0:3]  =", palavra[0:3])      # do índice 0 até o 3 (exclusivo)
print("palavra[3:]   =", palavra[3:])       # do índice 3 até o fim
print("palavra[:3]   =", palavra[:3])       # do início até o 3 (exclusivo)
print("palavra[::2]  =", palavra[::2])      # pulando de 2 em 2
print("palavra[::-1] =", palavra[::-1])     # invertida!

# DETALHE IMPORTANTE: strings são IMUTÁVEIS.
# Você não pode alterar um caractere "no lugar". Toda "modificação"
# cria uma nova string.
try:
    palavra[0] = "E"
except TypeError as erro:
    print("\nTentar alterar palavra[0] gera TypeError:", erro)

nova_palavra = "E" + palavra[1:]
print("Forma correta (cria nova string):", nova_palavra)

# Cada caractere tem um código numérico (Unicode). ord() e chr() convertem.
print("\nord('A') =", ord("A"), "| chr(65) =", chr(65), "| ord('a') =", ord("a"))


# ---------------------------------------------------------------------------
# 7. None - AUSÊNCIA DE VALOR
# ---------------------------------------------------------------------------
titulo("7. None - ausência de valor")

# None representa "nada", "vazio", "ainda não definido".
# É o único valor do tipo NoneType.
resultado = None
print("resultado =", resultado, "->", type(resultado))

# Use "is" (e não "==") para comparar com None:
print("resultado is None ->", resultado is None)

# None é diferente de 0, de "" e de False, embora todos sejam "falsy":
print("None == 0     ->", None == 0)
print("None == ''    ->", None == "")
print("None == False ->", None == False)

# Funções que não retornam nada explicitamente retornam None:
def sem_retorno():
    pass

print("sem_retorno() retorna:", sem_retorno())


# ---------------------------------------------------------------------------
# 8. CONVERSÃO ENTRE TIPOS (CASTING)
# ---------------------------------------------------------------------------
titulo("8. Conversão entre tipos (casting)")

# Usamos o nome do tipo como função para converter:
print("int('42')      =", int("42"), "->", type(int("42")))
print("int(3.99)      =", int(3.99), "  (trunca)")
print("float('3.14')  =", float("3.14"))
print("float(7)       =", float(7))
print("str(123)       =", repr(str(123)), "  (repr mostra as aspas)")
print("bool(0)        =", bool(0))
print("int(True)      =", int(True))

# Conversões inválidas geram ValueError:
try:
    int("abc")
except ValueError as erro:
    print("\nint('abc') gera ValueError:", erro)

# Cuidado clássico: input() SEMPRE retorna str!
# Se você pedir um número ao usuário, precisa converter.
#
#   idade = input("Sua idade: ")        # idade é str, ex: "20"
#   idade = int(input("Sua idade: "))   # agora idade é int: 20
#
# (deixado em comentário para o script rodar sem interação)


# ---------------------------------------------------------------------------
# 9. isinstance() - VERIFICANDO TIPOS DA FORMA CORRETA
# ---------------------------------------------------------------------------
titulo("9. isinstance() - verificando tipos")

# type(x) == int funciona, mas isinstance() é a forma recomendada,
# pois também considera herança (lembre: bool herda de int).
valor = 10
print("isinstance(10, int)         ->", isinstance(valor, int))
print("isinstance(10, float)       ->", isinstance(valor, float))
print("isinstance(10, (int, float)) ->", isinstance(valor, (int, float)),
      "  (aceita uma tupla de tipos)")
print("isinstance(True, int)       ->", isinstance(True, int), "  (bool herda de int)")
print("type(True) == int           ->", type(True) == int, "  (type() é mais restrito)")


# ---------------------------------------------------------------------------
# 10. IMUTABILIDADE E IDENTIDADE - id(), is e ==
# ---------------------------------------------------------------------------
titulo("10. Imutabilidade e identidade (id, is, ==)")

# Todos os tipos primitivos vistos aqui (int, float, complex, bool, str, None)
# são IMUTÁVEIS: uma vez criado, o valor não muda. Quando "alteramos" uma
# variável, na verdade fazemos ela apontar para um NOVO objeto.
n = 5
print("n =", n, "| id(n) =", id(n))
n = n + 1
print("n = n + 1 -> n =", n, "| id(n) =", id(n), "  <- endereço diferente: é outro objeto")

# "==" compara VALOR; "is" compara IDENTIDADE (se é o mesmo objeto na memória).
# (usamos int("1000") para garantir que sejam dois objetos separados na memória)
a = int("1000")
b = int("1000")
print("\na == b ->", a == b, "  (mesmo valor)")
print("a is b ->", a is b, "  (objetos distintos, mesmo tendo o mesmo valor)")

# Python guarda em cache os inteiros pequenos (-5 a 256), por isso:
c = 100
d = 100
print("c is d (100) ->", c is d, "  (inteiros pequenos são reutilizados)")
# Regra prática: use "==" para comparar valores e "is" apenas para None.

# Tamanho em memória (em bytes) - só por curiosidade:
print("\nsys.getsizeof(0)        =", sys.getsizeof(0))
print("sys.getsizeof(2**100)   =", sys.getsizeof(2 ** 100), "  <- int cresce conforme o valor")
print("sys.getsizeof(3.14)     =", sys.getsizeof(3.14))
print("sys.getsizeof('')       =", sys.getsizeof(""))
print("sys.getsizeof('python') =", sys.getsizeof("python"))


# ---------------------------------------------------------------------------
# RESUMO
# ---------------------------------------------------------------------------
titulo("RESUMO")
print("""
| Tipo     | Exemplo        | Mutável? | Observação principal                        |
|----------|----------------|----------|---------------------------------------------|
| int      | 42, -7, 0      | Não      | Precisão arbitrária (não "estoura")         |
| float    | 3.14, 2.0, 1e3 | Não      | Impreciso: 0.1 + 0.2 != 0.3                 |
| complex  | 2 + 3j         | Não      | Raramente usado                             |
| bool     | True, False    | Não      | Subclasse de int (True == 1)                |
| str      | "olá", 'oi'    | Não      | Sequência indexável; suporta slicing        |
| NoneType | None           | Não      | Ausência de valor; compare com "is"         |

Próximos passos: na próxima aula veremos as COLEÇÕES nativas do Python
(list, tuple, set e dict), que são a base de todas as estruturas de dados
que estudaremos na disciplina.
""")
