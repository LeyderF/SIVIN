/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import conexion.Conexion;
import controlador.Ctrl_RegistrarVenta;
import java.awt.Dimension;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.CabeceraVenta;
import modelo.DetalleVenta;

public class InterFacturacion extends javax.swing.JInternalFrame {

    private DefaultTableModel modeloDatosProductos;
    //lista para el detalle de venta de los productos
    ArrayList<DetalleVenta> listaProductos = new ArrayList<>();
    private DetalleVenta producto;

    private int idCliente = 0;

    private int idProducto = 0;
    private String nombre = "";
    private int cantidadProductoBBDD = 0;
    private double precioUnitario = 0.0;
    private int porcentajeIva = 0;

    private int cantidad = 0;  //cantidad productos a comprar
    private double subtotal = 0.0;  //precio
    private double descuento = 0.0;
    private double iva = 0.0;
    private double totalPagar = 0.0;

    //variables calculos
    private double subtotalGeneral = 0.0;
    private double descuentoGeneral = 0.0;
    private double ivaGeneral = 0.0;
    private double totalPagarGeneral = 0.0;

    private int auxIdDetalle = 1;

    public InterFacturacion() {
        initComponents();

        this.setSize(new Dimension(800, 600));
        this.setTitle("Facturacion");

        //cargar clientes en la vista - cargar productos
        this.CargarComboClientes();
        this.CargarComboProductos();
        this.inicializarTablaProducto();

        txt_efectivo.setEnabled(false);
        jButton_calcular_cambio.setEnabled(false);

        txt_subtotal.setText("0.0");
        txt_iva.setText("0.0");
        txt_descuento.setText("0.0");
        txt_total_pagar.setText("0.0");

        //insertar imagen en nuestro jlabel
        ImageIcon wallpaper = new ImageIcon("src/img/fondo3.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(800, 600, WIDTH));
        jLabel_wallpaper.setIcon(icono);
        this.repaint();
    }

    //Metodo para inicializar tabla de productos
    private void inicializarTablaProducto() {
        modeloDatosProductos = new DefaultTableModel();
        //añadir columnas 
        modeloDatosProductos.addColumn("N°");
        modeloDatosProductos.addColumn("Nombre");
        modeloDatosProductos.addColumn("Cantidad");
        modeloDatosProductos.addColumn("P. Unitario");
        modeloDatosProductos.addColumn("Subtotal");
        modeloDatosProductos.addColumn("Descuento");
        modeloDatosProductos.addColumn("IVA");
        modeloDatosProductos.addColumn("Total");
        modeloDatosProductos.addColumn("Accion");
        //agregar dtaos de modelo tabla
        this.jTable_productos.setModel(modeloDatosProductos);
    }

    //Metodo presentar informacion en tabla
    private void ListaTablaProductos() {
        this.modeloDatosProductos.setRowCount(listaProductos.size());
        for (int i = 0; i < listaProductos.size(); i++) {
            this.modeloDatosProductos.setValueAt(i + 1, i, 0);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getNombre(), i, 1);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getCantidad(), i, 2);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getPrecioUnitario(), i, 3);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getSubTotal(), i, 4);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getDescuento(), i, 5);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getIva(), i, 6);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getTotalPagar(), i, 7);
            this.modeloDatosProductos.setValueAt("Eliminar", i, 8);
        }
        //Añadir jTable
        jTable_productos.setModel(modeloDatosProductos);
    }

    //Metodo agregar clientes 
    private void CargarComboClientes() {
        Connection cn = (Connection) Conexion.conectar();
        String sql = "select * from tb_cliente";
        Statement st;

        try {
            st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            jComboBox_cliente.removeAllItems();
            jComboBox_cliente.addItem("Seleccione cliente");
            while (rs.next()) {
                jComboBox_cliente.addItem(rs.getString("nombre") + " " + rs.getString("apellido"));

            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error sl cargar cliente " + e);
        }
    }

    //Metodo cargar productos
    private void CargarComboProductos() {
        Connection cn = (Connection) Conexion.conectar();
        String sql = "select * from tb_producto";
        Statement st;

        try {
            st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            jComboBox_producto.removeAllItems();
            jComboBox_producto.addItem("Seleccione producto");
            while (rs.next()) {
                jComboBox_producto.addItem(rs.getString("nombre"));

            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error sl cargar producto " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox_cliente = new javax.swing.JComboBox<>();
        jComboBox_producto = new javax.swing.JComboBox<>();
        txt_cliente_buscar = new javax.swing.JTextField();
        txt_cantidad = new javax.swing.JTextField();
        jButton_buscar_cliente = new javax.swing.JButton();
        jButton_añadir_producto = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_productos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_subtotal = new javax.swing.JTextField();
        txt_descuento = new javax.swing.JTextField();
        txt_iva = new javax.swing.JTextField();
        txt_total_pagar = new javax.swing.JTextField();
        txt_efectivo = new javax.swing.JTextField();
        txt_cambio = new javax.swing.JTextField();
        jButton_calcular_cambio = new javax.swing.JButton();
        jButton_RegistrarVenta = new javax.swing.JButton();
        jLabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Facturación");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Cliente:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, -1));

        jLabel3.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Producto:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 80, -1));

        jLabel4.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Cantidad:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 80, -1));

        jComboBox_cliente.setBackground(new java.awt.Color(204, 204, 204));
        jComboBox_cliente.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jComboBox_cliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione cliente:", "Item 2", "Item 3", "Item 4" }));
        jComboBox_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_clienteActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 170, -1));

        jComboBox_producto.setBackground(new java.awt.Color(204, 204, 204));
        jComboBox_producto.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jComboBox_producto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione producto:", "Item 2", "Item 3", "Item 4" }));
        jComboBox_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_productoActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 170, -1));

        txt_cliente_buscar.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        getContentPane().add(txt_cliente_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 40, 100, -1));

        txt_cantidad.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        getContentPane().add(txt_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 80, -1));

        jButton_buscar_cliente.setBackground(new java.awt.Color(204, 204, 204));
        jButton_buscar_cliente.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jButton_buscar_cliente.setText("Buscar");
        jButton_buscar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_buscar_clienteActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_buscar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 75, -1));

        jButton_añadir_producto.setBackground(new java.awt.Color(204, 204, 204));
        jButton_añadir_producto.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jButton_añadir_producto.setText("Añadir");
        jButton_añadir_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_añadir_productoActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_añadir_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 80, 75, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_productosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_productos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 750, 200));
        jScrollPane1.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 770, 220));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Subtotal:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 90, -1));

        jLabel6.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Descuento:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, -1));

        jLabel7.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("IVA:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 90, -1));

        jLabel8.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Total a pagar:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 90, -1));

        jLabel9.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Efectivo:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 90, -1));

        jLabel10.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Cambio:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 90, -1));

        txt_subtotal.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        txt_subtotal.setEnabled(false);
        txt_subtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_subtotalActionPerformed(evt);
            }
        });
        jPanel2.add(txt_subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 120, -1));

        txt_descuento.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        txt_descuento.setEnabled(false);
        txt_descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descuentoActionPerformed(evt);
            }
        });
        jPanel2.add(txt_descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 120, -1));

        txt_iva.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        txt_iva.setEnabled(false);
        txt_iva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ivaActionPerformed(evt);
            }
        });
        jPanel2.add(txt_iva, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 120, -1));

        txt_total_pagar.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        txt_total_pagar.setEnabled(false);
        txt_total_pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_total_pagarActionPerformed(evt);
            }
        });
        jPanel2.add(txt_total_pagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 120, -1));

        txt_efectivo.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        txt_efectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_efectivoActionPerformed(evt);
            }
        });
        jPanel2.add(txt_efectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 120, -1));

        txt_cambio.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        txt_cambio.setEnabled(false);
        txt_cambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cambioActionPerformed(evt);
            }
        });
        jPanel2.add(txt_cambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 120, -1));

        jButton_calcular_cambio.setBackground(new java.awt.Color(51, 153, 255));
        jButton_calcular_cambio.setFont(new java.awt.Font("Inter", 1, 10)); // NOI18N
        jButton_calcular_cambio.setForeground(new java.awt.Color(0, 0, 0));
        jButton_calcular_cambio.setText("Calcular cambio");
        jButton_calcular_cambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_calcular_cambioActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_calcular_cambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 130, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, 380, 210));

        jButton_RegistrarVenta.setBackground(new java.awt.Color(0, 153, 255));
        jButton_RegistrarVenta.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jButton_RegistrarVenta.setForeground(new java.awt.Color(0, 0, 0));
        jButton_RegistrarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/impresora.png"))); // NOI18N
        jButton_RegistrarVenta.setText("Registrar venta");
        jButton_RegistrarVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_RegistrarVenta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_RegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RegistrarVentaActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_RegistrarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 150, 90));

        jLabel_wallpaper.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_wallpaper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_wallpaperMouseClicked(evt);
            }
        });
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_clienteActionPerformed

    private void jComboBox_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_productoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_productoActionPerformed

    private void txt_subtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_subtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_subtotalActionPerformed

    private void txt_descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descuentoActionPerformed

    private void txt_ivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ivaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ivaActionPerformed

    private void txt_total_pagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_total_pagarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_total_pagarActionPerformed

    private void txt_efectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_efectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_efectivoActionPerformed

    private void txt_cambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cambioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cambioActionPerformed

    private void jButton_calcular_cambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_calcular_cambioActionPerformed
        if (!txt_efectivo.getText().isEmpty()) {
            //validar que no ingrese otros caracteres no numericos 
            boolean validacion = validarDouble(txt_efectivo.getText());
            if (validacion == true) {
                //validad efectivo > 0
                double efc = Double.parseDouble(txt_efectivo.getText().trim());
                double top = Double.parseDouble(txt_total_pagar.getText().trim());

                if (efc < top) {
                    JOptionPane.showMessageDialog(null, "El dinero no es suficiente");
                } else {
                    double cambio = (efc - top);
                    double cambi = (double) Math.round(cambio * 100) / 100;
                    String camb = String.valueOf(cambi);
                    txt_cambio.setText(camb);
                }

            } else {
                JOptionPane.showMessageDialog(null, "No se admiten caracteres no numericos");

            }

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese el total de efectivo para calcular el cambio");
        }
    }//GEN-LAST:event_jButton_calcular_cambioActionPerformed

    private void jButton_buscar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_buscar_clienteActionPerformed
        String clienteBuscar = txt_cliente_buscar.getText().trim();
        Connection cn = (Connection) Conexion.conectar();
        String sql = "select nombre, apellido from tb_cliente where cedula = '" + clienteBuscar + "'";
        Statement st;

        try {
            st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                jComboBox_cliente.setSelectedItem(rs.getString("nombre") + " " + rs.getString("apellido"));
            } else {
                jComboBox_cliente.setSelectedItem("Selecione cliente");
                JOptionPane.showMessageDialog(null, "Cedula incorrecta, no se a encontrado el cliente");
            }
            txt_cliente_buscar.setText("");
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar el cliente" + e);
        }
    }//GEN-LAST:event_jButton_buscar_clienteActionPerformed

    private void jButton_añadir_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_añadir_productoActionPerformed
            String combo = this.jComboBox_producto.getSelectedItem().toString();
        //validar seleccione producto
        if (combo.equalsIgnoreCase("Seleccione producto")) {
            JOptionPane.showMessageDialog(null, "Seleccione un producto");
        } else {
            //validad ingrese cantidad

            if (!txt_cantidad.getText().isEmpty()) {
                //validar ingrese numeros 
                boolean validacion = validar(txt_cantidad.getText());

                if (validacion == true) {
                    //validar cantidad > 0

                    if (Integer.parseInt(txt_cantidad.getText()) > 0) {
                        cantidad = Integer.parseInt(txt_cantidad.getText());
                        this.DatosDelProducto();
                        //validar la cantidad de productos seleciionado no sea mayor al stock
                        if (cantidad <= cantidadProductoBBDD) {
                            subtotal = precioUnitario * cantidad;
                            totalPagar = subtotal + iva + descuento;

                            subtotal = (double) Math.round(subtotal * 100) / 100; //redondear
                            iva = (double) Math.round(iva * 100) / 100; //redondear
                            
                            descuento = (double) Math.round(descuento * 100) / 100; //redondear
                            totalPagar = (double) Math.round(totalPagar * 100) / 100; //redondear

                            //nuevo producto
                            producto = new DetalleVenta(auxIdDetalle, 1, idProducto, nombre, Integer.parseInt(txt_cantidad.getText()), precioUnitario, subtotal, descuento, iva, totalPagar, 1);
                            listaProductos.add(producto);
                            JOptionPane.showMessageDialog(null, "Producto agregado");
                            auxIdDetalle++;
                            txt_cantidad.setText(""); //limpiar campo 
                            //volver a cargar combo productos
                            this.CargarComboProductos();
                            this.CalcularTotalPagar();
                            txt_efectivo.setEnabled(true);
                            jButton_calcular_cambio.setEnabled(true);

                        } else {
                            JOptionPane.showMessageDialog(null, "La cantidad suprera el stock");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad no puede ser 0 o menor a 0");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "En la cantidad no se admiten caracteres no numericos ");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una cantidad");
            }
        }
        this.ListaTablaProductos();
    }//GEN-LAST:event_jButton_añadir_productoActionPerformed


    private void jLabel_wallpaperMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_wallpaperMouseClicked

    }//GEN-LAST:event_jLabel_wallpaperMouseClicked

    
    private void jTable_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_productosMouseClicked
        int fila_point = jTable_productos.rowAtPoint(evt.getPoint());
        int columna_point = 0;
        int idArrayList = 0;

        if (fila_point > -1) {
            idArrayList = (int) modeloDatosProductos.getValueAt(fila_point, columna_point);
        }
        int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro a eliminar el producto?");
        //opciones de  confir dialog - (si = 0, no = 1, cancelar = 2, close = -1
        switch (opcion) {
            case 0 -> {
                //si
                listaProductos.remove(idArrayList = 1);
                this.CalcularTotalPagar();
                this.ListaTablaProductos();
            }
            case 1 -> {
            }
            default -> {
            }
        }
        //no
        
    }//GEN-LAST:event_jTable_productosMouseClicked

    private void jButton_RegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RegistrarVentaActionPerformed

        CabeceraVenta cabeceraVenta = new CabeceraVenta();
        DetalleVenta detalleVenta = new DetalleVenta();
        Ctrl_RegistrarVenta controlVenta = new Ctrl_RegistrarVenta();

        String fechaActual;
        Date date = new Date();
        fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);

        if (!jComboBox_cliente.getSelectedItem().equals("Seleccione cliente:")) {
            if (!listaProductos.isEmpty()) {

                //Metodo obtener id del cliente
                this.ObtenerIdCliente();
                //Registrar cabecera 
                cabeceraVenta.setIdCabeceraVenta(0);
                cabeceraVenta.setIdCliente(idCliente);
                cabeceraVenta.setValorPagar(Double.parseDouble(txt_total_pagar.getText()));
                cabeceraVenta.setFechaVenta(fechaActual);
                cabeceraVenta.setEstado(1);

                if (controlVenta.guardar(cabeceraVenta)) {
                    JOptionPane.showMessageDialog(null, "Venta registrada");

                    //guardar detalle
                    for (DetalleVenta elemento : listaProductos) {
                        if (controlVenta.guardarDetalle(detalleVenta)) {
                            //System.out.println("Detalle de venta registrado");

                            txt_subtotal.setText("0.0");
                            txt_descuento.setText("0.0");
                            txt_iva.setText("0.0");
                            txt_total_pagar.setText("0.0");
                            txt_efectivo.setText("0.0");
                            txt_cambio.setText("0.0");
                            auxIdDetalle = 1;

                            this.CargarComboClientes();
                            this.RestarStockProductos(elemento.getIdProducto(), elemento.getCantidad());

                        } else {
                            JOptionPane.showMessageDialog(null, "Error al guardar detalle venta");
                        }
                        detalleVenta.setIdDetalleVenta(0);
                        detalleVenta.setIdCabeceraVenta(0);
                        detalleVenta.setIdProducto(elemento.getIdProducto());
                        detalleVenta.setCantidad(elemento.getCantidad());
                        detalleVenta.setPrecioUnitario(elemento.getPrecioUnitario());
                        detalleVenta.setSubTotal(elemento.getSubTotal());
                        detalleVenta.setDescuento(elemento.getDescuento());
                        detalleVenta.setIva(elemento.getIva());
                        detalleVenta.setTotalPagar(elemento.getTotalPagar());
                        detalleVenta.setEstado(1);

                    }
                    //vaciamos la lista
                    listaProductos.clear();
                    ListaTablaProductos();

                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar cabecera de venta");
                }

            } else {
                JOptionPane.showMessageDialog(null, "¡Seleccione un producto!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "¡Seleccione un cliente!");
        }
    }//GEN-LAST:event_jButton_RegistrarVentaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_RegistrarVenta;
    private javax.swing.JButton jButton_añadir_producto;
    private javax.swing.JButton jButton_buscar_cliente;
    private javax.swing.JButton jButton_calcular_cambio;
    private javax.swing.JComboBox<String> jComboBox_cliente;
    private javax.swing.JComboBox<String> jComboBox_producto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_productos;
    private javax.swing.JTextField txt_cambio;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_cliente_buscar;
    private javax.swing.JTextField txt_descuento;
    private javax.swing.JTextField txt_efectivo;
    private javax.swing.JTextField txt_iva;
    private javax.swing.JTextField txt_subtotal;
    private javax.swing.JTextField txt_total_pagar;
    // End of variables declaration//GEN-END:variables

    //validar efectivo
    private boolean validarDouble(String valor) {
        try {
            double num = Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Metodo para validar usuario solo ingrese numeros 
    private boolean validar(String valor) {
        try {
            int num = Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //mostrar datos del producto
    private void DatosDelProducto() {
        try {
            String sql = "select * from tb_producto where nombre = '" + this.jComboBox_producto.getSelectedItem() + "' ";
            Connection cn = (Connection) Conexion.conectar();
            Statement st;
            st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                idProducto = rs.getInt("idProducto");
                nombre = rs.getString("nombre");
                cantidadProductoBBDD = rs.getInt("cantidad");
                precioUnitario = rs.getDouble("precio");
                iva = rs.getDouble("PorcentajeIVA");
                this.CalcularIva(precioUnitario, iva);

            }
        } catch (SQLException e) {
            System.out.println("Error al obtener datos del produco");
        }
    }

    //Calcular iva
    private double CalcularIva(double precio, double piva) {
        int p_iva = (int) piva;
        switch (p_iva) {
            case 0 -> iva = 0.0;
            case 5 -> iva = (precio * cantidad) * 0.05;
            case 19 -> iva = (precio * cantidad) * 0.19;
            default -> {
            }
        }
        return iva;
    }

    //metodo para calcular total
    private void CalcularTotalPagar() {
        subtotalGeneral = 0;
        descuentoGeneral = 0;
        ivaGeneral = 0;
        totalPagarGeneral = 0;

        for (DetalleVenta elemento : listaProductos) {
            subtotalGeneral += elemento.getSubTotal();
            descuentoGeneral += elemento.getDescuento();
            ivaGeneral += elemento.getIva();
            totalPagarGeneral += elemento.getTotalPagar();
        }
        //redondear
        subtotalGeneral = (double) Math.round(subtotalGeneral * 100) / 100;
        descuentoGeneral = (double) Math.round(descuentoGeneral * 100) / 100;
        ivaGeneral = (double) Math.round(ivaGeneral * 100) / 100;
        totalPagarGeneral = (double) Math.round(totalPagarGeneral * 100) / 100;

        //mostrar datos
        txt_subtotal.setText(String.valueOf(subtotalGeneral));
        txt_descuento.setText(String.valueOf(descuentoGeneral));
        txt_iva.setText(String.valueOf(ivaGeneral));
        txt_total_pagar.setText(String.valueOf(totalPagarGeneral));

    }

    //metodo obtener id cliente
    private void ObtenerIdCliente() {
        try {
            String sql = "select * from tb_cliente where concat(nombre, ' ', apellido) = '" + this.jComboBox_cliente.getSelectedItem() + "'";
            Connection cn = (Connection) Conexion.conectar();
            Statement st;
            st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                idCliente = rs.getInt("idCliente");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener id del cliente: " + e);
        }
    }

    //Metodo restar stock
    private void RestarStockProductos(int idProducto, int cantidad) {
        int cantidadProductosBaseDatos = 0;
        try {
            Connection cn;
            cn = Conexion.conecta();
            String sql = "select idProducto, cantidad from tb_producto where idProducto = '" + idProducto + "'";
            Statement st;
            st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cantidadProductosBaseDatos = rs.getInt("cantidad");
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al restar cantidad:" + e);
        }

        try {
            Connection cn = Conexion.conecta();
            PreparedStatement consulta = (PreparedStatement) cn.prepareStatement("UPDATE tb_producto SET cantidad = ? WHERE idProducto = ?");
            int cantidadNueva = cantidadProductosBaseDatos - cantidad;
            consulta.setInt(1, cantidadNueva);
            consulta.setInt(2, idProducto); 

            if (consulta.executeUpdate() > 0) {
                System.out.println("Todo bien");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al restar cantidad: " + e);
        }
    }

}
