# ==========================================================
# Operação simples utilizando da Linguagem Pyton para um comando de
# somar, multiplicar e verificar se o valor é positivo.
# Os tipos primitivos usados aqui foram: int, float e bool
# ==========================================================

num1 = 7 # int
num2 = 4 # int
numdc = 3.4 # float

soma = num1 + num2
mult = numdc * num2
resultado_positivo = soma > 0 # bool

print("Soma:", soma)
print("Multiplicacao:", mult)
print("Resultado positivo?", resultado_positivo)

# ==========================================================
# Uma lista simples em Python para somar numeros de acordo com seus respectivos índices
# ==========================================================

numeros = [1, 3, 5, 7, 9]
soma_lista = numeros[0] + numeros[1] + numeros[2] + numeros[3] + numeros[4]

print("Soma da lista", soma_lista)