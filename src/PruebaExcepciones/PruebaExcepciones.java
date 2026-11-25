package PruebaExcepciones;

/**
 * Esta clase denominada PruebaExcepciones demuestra la captura y manejo 
 * de excepciones aritméticas e ilícitas usando bloques try, catch y finally.
 */
public class PruebaExcepciones {

    public static String ejecutarPrueba1() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("Ingresando al primer try\n");
            int cociente = 10000 / 0; // Provoca ArithmeticException
            sb.append("Resultado: ").append(cociente).append("\n");
        } catch (ArithmeticException e) {
            sb.append("División por cero capturada (ArithmeticException)\n");
        } catch (Exception e) {
            sb.append("Ocurrió una excepción general\n");
        } finally {
            sb.append("Ingresando al primer finally\n");
        }
        return sb.toString();
    }

    public static String ejecutarPrueba2() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("Ingresando al segundo try\n");
            String texto = null;
            sb.append(texto.toUpperCase()); // Provoca NullPointerException
        } catch (ArithmeticException e) {
            sb.append("División por cero\n");
        } catch (Exception e) {
            sb.append("Ocurrió una excepción general (NullPointerException)\n");
        } finally {
            sb.append("Ingresando al segundo finally\n");
        }
        return sb.toString();
    }
}
