public class OperacoesJava {
    public static void main(String[] args) {

        System.out.println("=============================================");
        System.out.println("Codigo de soma, multiplicacao e checar valor portado em java.");
        System.out.println("=============================================");

        int a = 14;
        int b = 27;
        double x = 7.5;
        boolean resultadoPositivo;

        int soma = a + b;
        double multiplicacao = x * a;
        resultadoPositivo = soma > 0;

        System.out.println("Soma: " + soma);
        System.out.println("Multiplicacao: " + multiplicacao);
        System.out.println("Resultado positivo? " + resultadoPositivo);

        System.out.println("=============================================");
        System.out.println(".");
        System.out.println("=============================================");

        int[] numeros = {1, 3, 5, 7, 9, 11};
        int somaArray = numeros[0] + numeros[1] + numeros[2] + numeros[3] + numeros[4] + numeros[5];

        System.out.println("Soma do array: " + somaArray);
    }
}
