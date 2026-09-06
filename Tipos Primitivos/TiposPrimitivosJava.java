public class TiposPrimitivosJava {
    public static void main(String[] args) {
        int inteiro = 10;
        double decimal = 10.5;
        char caractere = 'A';
        boolean booleano = true;
        long numeroGrande = 8000000000L;

        // Java nao permite chamar .getClass() direto para pegar a classe de um tipo primitivo
        // Por isso, é indicado o tipo manualmente, do jeito que foi declarado:

        System.out.println("inteiro é do tipo: int");
        System.out.println("decimal é do tipo: double");
        System.out.println("caractere é do tipo: char");
        System.out.println("booleano é do tipo: boolean");
        System.out.println("numeroGrande é do tipo: long");
    }
}
