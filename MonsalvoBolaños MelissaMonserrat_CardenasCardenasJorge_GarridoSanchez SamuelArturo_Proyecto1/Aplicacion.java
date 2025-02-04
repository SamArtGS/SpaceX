
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.*;

public class Aplicacion extends javax.swing.JFrame {
    String ruta_archivo_ordenar;

    public Aplicacion() {
        initComponents();
        
    }
    private void initComponents() {
        setIconImage(new ImageIcon("IMG_1804.jpg").getImage());

        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane1.setBackground(Color.white);
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cmbMetodo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbCampo = new javax.swing.JComboBox<>();
        abrirArchivo = new javax.swing.JButton();
        ruta_archivo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnOrdenar = new javax.swing.JButton();



        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.disabledShadow"));
        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ordenamiento de Archivos - Sort It Tista");

        jLabel2.setText("MÉTODO: ");

        cmbMetodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Mezcla Equilibrada", "Radix","Polifase","No lo haga compa"}));



        jLabel3.setText("ORDEN");

        cmbCampo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente", "Descendente" }));



        abrirArchivo.setText("Abrir Archivo");
        abrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirArchivoActionPerformed(evt);
            }
        });

        //ruta_archivo.setBackground(new java.awt.Color(255, 255, 255));

        btnOrdenar.setText("Ordenar");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt,cmbMetodo,ruta_archivo_ordenar,cmbCampo);
            }
        });
        File file = new File(".");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(abrirArchivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ruta_archivo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(43, 43, 43)
                        .addComponent(cmbMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel3)
                        .addGap(46, 46, 46)
                        .addComponent(cmbCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(btnOrdenar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(abrirArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ruta_archivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cmbCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(btnOrdenar)
                .addGap(66, 66, 66))
        );
        pack();
        setLocationRelativeTo(null);
        
    }// </editor-fold>//GEN-END:initComponents

    private void abrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirArchivoActionPerformed
        JFileChooser openFileChooser = new JFileChooser();
        openFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        openFileChooser.setMultiSelectionEnabled(false);
        openFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
        int seleccion = openFileChooser.showOpenDialog(null);
        if(seleccion == JFileChooser.APPROVE_OPTION){
            File file = openFileChooser.getSelectedFile();
            //File dest = new File(Paths.get(".").toAbsolutePath().normalize().toString());
            //File dest = new File("/Users/samuelarturogarridosanchez/Documents");
            ruta_archivo_ordenar = openFileChooser.getSelectedFile().getAbsolutePath();
            try{
                Path origenPath = FileSystems.getDefault().getPath(file.toString());
                Path destinoPath = FileSystems.getDefault().getPath(new File(".").getCanonicalPath() +"/"+file.getName());
                System.out.println(destinoPath);
                Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
                ruta_archivo.setText(openFileChooser.getSelectedFile().getAbsolutePath());
            } catch(IOException e){
                
            }
            
        }
        else{
            ruta_archivo.setText("No se encontro el archivo");
        }
    }//GEN-LAST:event_abrirArchivoActionPerformed

    private void cmbCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCampoActionPerformed
    }

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt,javax.swing.JComboBox kind,String ruta,javax.swing.JComboBox kind2) {//GEN-FIRST:event_btnOrdenarActionPerformed
        try {
            String opcion = kind.getSelectedItem().toString();
            if(opcion.equals("Polifase")){
            String m = JOptionPane.showInputDialog("Ingresa la cantidad elementos por partición: ");
            Polifase poli = new Polifase(Integer.parseInt(m),ruta,kind2.getSelectedItem().toString());
            poli.sort(Integer.parseInt(m),ruta,kind2.getSelectedItem().toString());
            }
            if(opcion.equals("Mezcla Equilibrada")){
                if(kind2.getSelectedItem().toString().equals("Ascendente")){
                    MezclaEquilibrada sort = new MezclaEquilibrada(new File(ruta), true);
                }
                else{
                    MezclaEquilibrada sort = new MezclaEquilibrada(new File(ruta), false);
                }

            }
            if(opcion.equals("Radix")){
            RadixSort ras = new RadixSort(ruta,kind2.getSelectedItem().toString());
            ras.radix(ruta,kind2.getSelectedItem().toString());
            }


        } catch (Exception ex) {
            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Aplicacion().setVisible(true);
            }
        });
    }
    private javax.swing.JButton abrirArchivo;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JComboBox<String> cmbCampo;
    private javax.swing.JComboBox<String> cmbMetodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel ruta_archivo;
}
