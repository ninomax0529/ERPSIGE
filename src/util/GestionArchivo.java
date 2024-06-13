package util;

//import encapsulado.Configuracion;
//import encapsulado.ConfiguracionCC;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class GestionArchivo {

    static File archivo = null;

    public static String unidad[];
    public static String adjunto;

    public static String mensaje = " LA UNIDAD DE ARCHIVOS ADJUNTOS NO ESTA MONTADA \n"
            + " FAVOR LLAMAR A SOPORTE TECNICO ";

    public GestionArchivo() {
    }

    public static File guardarArchivo() {
        try {
            String extencion = "";
            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            file.showOpenDialog(null);
            archivo = file.getSelectedFile();
            String NameArchivo = archivo.getName();
            if (archivo != null) {
                //limitar solo pdf
                /*   int i = NameArchivo.lastIndexOf('.');
                if (i > 0) {
                    extencion = NameArchivo.substring(i + 1);
                }

                if (!(extencion == "pdf")) {
                    JOptionPane.showMessageDialog(null,
                            "Su archivo no se ha guardado",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
                // JOptionPane.showMessageDialog(this, "Solo puede cargar arcrivos pdf");*/

                FileWriter save = new FileWriter(archivo + ".txt");
                save.close();
                /*  JOptionPane.showMessageDialog(null,
                        "Archivo Cargado Exitosamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);*/
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Su archivo no se ha guardado",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        return archivo;
    }

    public static Double numeroSinComa(String cadena) {

        String resultado = "";

        for (int a = 0; a < cadena.length(); a++) {

            if (cadena.charAt(a) != ',') {

                resultado += cadena.charAt(a);
            }
        }

        return Double.parseDouble(resultado);
    }

    public static String[] listaUnidades() {

        //Lista de unidades
        File[] unidades = File.listRoots();

        String unidadDisco[] = new String[unidades.length];

        for (int i = 0; i < unidades.length; i++) {

            String nombreUnidad = FileSystemView.getFileSystemView().getSystemDisplayName(unidades[i]);

            String tipoUnidad = FileSystemView.getFileSystemView().getSystemTypeDescription(unidades[i]);

            if (FileSystemView.getFileSystemView().isFloppyDrive(unidades[i])) {

                nombreUnidad = "Unidad de Disquete (A:)";

            }

            if (nombreUnidad.equalsIgnoreCase("")) {
                nombreUnidad = tipoUnidad;
            }

            unidadDisco[i] = nombreUnidad;
        }

        return unidadDisco;
    }

    public static Boolean existeUnidadAdjunto() {

        boolean adjuntoMontado = false;
        unidad = listaUnidades();
        
        for (String unidad1 : unidad) {

//            if (unidad1.trim().contains("adjuntos") && unidad1.trim().contains("172.20.0.7")) {
//            if (unidad1.trim().contains(confgAdjunto.getNombre().split("/")[1]) && unidad1.trim().contains(confgadIp.getNombre())) {
//                adjunto = unidad1;
//                adjuntoMontado = true;
//                break;
//            }
        }

        return adjuntoMontado;
    }

  
    public static void main(String[] args) {

        for (String arg : listaUnidades()) {
            System.out.println("Lista Unidad " + arg);
        }

        System.out.println(existeUnidadAdjunto());
//        ConfiguracionCC conf = Utilvale.getConfiguracion(3);
//        System.out.println("Nombre " + conf.getNombre() + " codigo " + conf.getCodigo());
    }

}
