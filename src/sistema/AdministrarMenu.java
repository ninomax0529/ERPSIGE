/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import manejo.menuModulo.ManejoMenuModulo;
import manejo.menuModulo.ManejoModulo;
import manejo.menuModulo.ManejoRolMenuModulo;
import modelo.MenuModulo;
import modelo.RolMenuModulo;

/**
 *
 * @author maximilianoa-te
 */
public class AdministrarMenu {

    public static AdministrarMenu adm = null;

    public AdministrarMenu() {

    }

    public static AdministrarMenu getInstancia() {

        if (adm == null) {
            adm = new AdministrarMenu();
        }

        return adm;
    }

    public void agregarOpciones(VBox vbMenu, int modulo) {

        System.out.println("modulo : "+modulo);
        MenuModulo menuModulo;

        List<MenuModulo> listaMenuModulo = ManejoMenuModulo.getInstancia().getLista(modulo);
        int codigoModulo = ManejoModulo.getInstancia().getModulo(modulo).getCodigo();
        String nombreModulo = ManejoModulo.getInstancia().getModulo(modulo).getNombre();

        boolean actualizando = false;

        for (Node n : vbMenu.getChildren()) {

            if (!(n.getId() == null)) {

                menuModulo = new MenuModulo();

                menuModulo.setIdMenu(n.getId());
                menuModulo.setMenu(n.getAccessibleText());
                menuModulo.setModulo(codigoModulo);
                menuModulo.setNombreModulo(nombreModulo);

                for (MenuModulo memu : listaMenuModulo) {

                    if (n.getId().equals(memu.getIdMenu())) {

                        menuModulo = memu;
                        actualizando = true;
                        break;
                    }
                }

                if (actualizando) {

                    ManejoMenuModulo.getInstancia().actualizar(menuModulo);

                } else {

                    ManejoMenuModulo.getInstancia().salvar(menuModulo);
                }

            }
            n.setDisable(true);

        }
    }

    public void activarOpciones(VBox vbMenu, int modulo, int codigoRol) {

        if (codigoRol == 1) {//rol de administrador

            for (Node n : vbMenu.getChildren()) {
                n.setDisable(false);
            }

        } else {

            List<RolMenuModulo> listaRolMenuModulos = ManejoRolMenuModulo
                    .getInstancia().getRolMenuModulo(codigoRol, modulo);

            for (Node n : vbMenu.getChildren()) {

                if (!(n.getId() == null)) {

                    for (RolMenuModulo memu : listaRolMenuModulos) {

                        if (n.getId().equals(memu.getMenuModulo().getIdMenu())) {
                            n.setDisable(!memu.getHabilitado());

                        }
                    }

                }

            }
        }

    }

    public static void main(String[] args) {

    }
}
