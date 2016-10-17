package visual.auxiliares;


import datos.ResumenClase;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

public class MyList extends JList {

    public MyList(ListModel modelo) {
        super(modelo);
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    DefaultListModel model = (DefaultListModel) MyList.this.getModel();
                    int selected = MyList.this.getSelectedIndex();
                    ResumenClase aux = (ResumenClase) model.getElementAt(selected);
                    new DialogoDatos(aux.detalles());
                }
            }
        };
        this.addMouseListener(mouseListener);
    }
}
