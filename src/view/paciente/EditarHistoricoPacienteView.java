/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.paciente;

import model.bean.CepBean;
import model.bean.PacienteBean;
import controller.PacienteController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.jtable.EditarHistoricoPacienteTableModel;
import util.MaskFormatTextUtil;
import util.WebServicesUtil;

/**
 *
 * @author vande
 */
public class EditarHistoricoPacienteView extends javax.swing.JDialog {

    private CepBean cep = new CepBean();
    private static PacienteBean paciente;
    public static EditarHistoricoPacienteTableModel tablemodelHistorico = null;

    /**
     * Creates new form EditarHistoricoPaciente
     */
    public EditarHistoricoPacienteView(java.awt.Frame parent, boolean modal, int pacienteCodigo) {
        super(parent, modal);
        paciente = new PacienteBean();
        this.paciente.setPacienteCodigo(pacienteCodigo);
        this.paciente = PacienteController.searchPacienteByCodigo(this.paciente.getPacienteCodigo());
        initComponents();
        presetForm();
        defTableHistorico();
    }

    /**
     * Método que define o tamanho das colunas da tabela de pacientes.
     */
    private void setColumnHistoricoTable() {
        jtHistorico.getColumnModel().getColumn(0).setPreferredWidth(30);
        jtHistorico.getColumnModel().getColumn(1).setPreferredWidth(30);
        jtHistorico.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtHistorico.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtHistorico.getColumnModel().getColumn(3).setPreferredWidth(100);
    }
    
    /**
     * Popula o tabela de histórico de agendamentos do paciente.
     */
    private void defTableHistorico(){
        this.tablemodelHistorico = new EditarHistoricoPacienteTableModel(paciente);
        jtHistorico.setModel(tablemodelHistorico);
        setColumnHistoricoTable();
    }
    
    /**
     * Preencher campos de endereco com dados gerados a partir do CEP.
     */
    private void presetCep() {
        try {
            cep = WebServicesUtil.searchCepUtil(txtCep.getText());
            txtLogradouro.setText(cep.getLogradouro());
            txtBairro.setText(cep.getBairro());
            txtCidade.setText(cep.getCidade());
            jcbUf.setSelectedItem(cep.getUf());
        } catch (Exception ex) {
            resetCep();
        }
    }

    /**
     * Reseta valores dos campos de endereço do formulário.
     */
    private void resetCep() {
        txtLogradouro.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        jcbUf.setSelectedIndex(0);
    }

    /**
     * Válidar o formulário.
     *
     * @return
     */
    private boolean checkForm() {
        if (txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome não pode estar vázio.");
            return false;
        }
        if (!txtNascimento.getText().equalsIgnoreCase("  /  /    ")) {
            if (!MaskFormatTextUtil.validarData(txtNascimento.getText())) {
                JOptionPane.showMessageDialog(null, "Data de nasicmento inválida.");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data de nascimento não pode estar vázio.");
            return false;
        }
        if (jcbSexo.getSelectedIndex() == -1 || ((String) jcbSexo.getSelectedItem()).equals("")) {
            JOptionPane.showMessageDialog(null, "Sexo não pode estar vázio.");
            return false;
        }
        if (!txtEmail.getText().isEmpty()) {
            if (!MaskFormatTextUtil.checkEmail(txtEmail.getText())) {
                JOptionPane.showMessageDialog(null, "E-mail não válido.");
                return false;
            }
        }
        if (!txtTelefone.getText().equalsIgnoreCase("(  )    -    ")) {
            if (txtTelefone.getText().length() != 13) {
                JOptionPane.showMessageDialog(null, "O campo de telefone deve conter 10 digitos númericos.");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Telefone não pode estar vázio.");
            return false;
        }
        if (!txtCelular.getText().equalsIgnoreCase("(  )     -    ")) {
            if (txtCelular.getText().length() > 14) {
                JOptionPane.showMessageDialog(null, "O campo de celular deve conter 11 digitos númericos.");
                return false;
            }
        }
        if (!txtCep.getText().equalsIgnoreCase("     -   ")) {
            if (txtCep.getText().length() != 9) {
                JOptionPane.showMessageDialog(null, "O CEP deve conter 8 digitos.");
                return false;
            }
        }
        if (!txtDum.getText().equalsIgnoreCase("  /  /    ")) {
            if (!MaskFormatTextUtil.validarData(txtDum.getText())) {
                JOptionPane.showMessageDialog(null, "O data do DUM é inválida.");
                return false;
            }
        }
        return true;
    }

    /**
     * Setar o formulário com as informações do paciente.
     */
    private void presetForm() {
        txtNome.setText(paciente.getNome());
        txtNascimento.setText(MaskFormatTextUtil.dataBr(paciente.getDataNascimento()));
        jcbSexo.setSelectedItem((String) MaskFormatTextUtil.sexoBySigla(paciente.getSexo()));
        txtProfissao.setText(paciente.getProfissao());
        txtEmail.setText(paciente.getEmail());
        txtTelefone.setText(paciente.getTelefone());
        txtCelular.setText(paciente.getCelular());
        txtCep.setText(paciente.getCep());
        txtLogradouro.setText(paciente.getLogradouro());
        txtNumeroResidencia.setText(paciente.getNumeroResidencia());
        txtBairro.setText(paciente.getBairro());
        txtCidade.setText(paciente.getCidade());
        jcbUf.setSelectedItem((String) paciente.getUf());
        txtAltura.setText(MaskFormatTextUtil.changeDotByComma(paciente.getAltura() + ""));
        txtPeso.setText(MaskFormatTextUtil.changeDotByComma(paciente.getPeso() + ""));
        jcbTipoSanguineo.setSelectedItem((String) paciente.getTipoSanguineo());
        txtDum.setText(MaskFormatTextUtil.dataBr(paciente.getDum()));
        txtPressaoArterial.setText(paciente.getPressaoArterial());
        txtAlergia.setText(paciente.getAlergia());
        txtGestacao.setText(paciente.getGestacao());
        txtDeficiencia.setText(paciente.getDeficiencia());
        jtaObservacao.setText(paciente.getObservacao());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpNovoPaciente = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNascimento = new javax.swing.JFormattedTextField();
        jcbSexo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtProfissao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCep = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtLogradouro = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jcbUf = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtNumeroResidencia = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtAltura = new javax.swing.JFormattedTextField();
        txtPeso = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        jcbTipoSanguineo = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtDum = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        txtPressaoArterial = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtAlergia = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtGestacao = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtDeficiencia = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaObservacao = new javax.swing.JTextArea();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtHistorico = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cila's System - Informações e histórico de paciente");
        setResizable(false);

        jLabel1.setText("INFORMAÇÕES DO PACIENTE:");

        jLabel2.setText("Nome completo:");

        jLabel3.setText("Data de nascimento:");

        try {
            txtNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jcbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "","Feminino", "Masculino"}));

        jLabel4.setText("Sexo:");

        jLabel5.setText("Profissão:");

        jLabel6.setText("E-mail:");

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel7.setText("Telefone:");

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel8.setText("Celular:");

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCepFocusLost(evt);
            }
        });
        txtCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCepActionPerformed(evt);
            }
        });

        jLabel9.setText("CEP:");

        jLabel10.setText("Logradouro:");

        jLabel11.setText("Bairro:");

        jLabel12.setText("Cidade:");

        jcbUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"","AC", "AL", "AP", "AM",
            "BA", "CE", "DF", "ES",
            "GO", "MA", "MT", "MS",
            "MG", "PA", "PB", "PR",
            "PE", "PI", "RJ", "RN",
            "RS", "RO", "RR", "SC",
            "SP", "SE", "TO"}));

jLabel13.setText("UF:");

jLabel14.setText("Nº");

jLabel15.setText("DADOS CLÍNICOS:");

jLabel16.setText("Altura:");

txtAltura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

txtPeso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

jLabel17.setText("Peso:");

jcbTipoSanguineo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "","A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"}));

jLabel18.setText("Tipo sanguíneo:");

try {
    txtDum.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    txtDum.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtDumActionPerformed(evt);
        }
    });

    jLabel19.setText("DUM:");

    jLabel20.setText("Pressão arterial:");

    jLabel21.setText("Alergias:");

    jLabel22.setText("Gestações:");

    jLabel23.setText("Deficiências:");

    jLabel24.setText("Observações:");

    jtaObservacao.setColumns(20);
    jtaObservacao.setRows(5);
    jScrollPane1.setViewportView(jtaObservacao);

    btnSalvar.setText("SALVAR ALTERAÇÕES");
    btnSalvar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnSalvarActionPerformed(evt);
        }
    });

    btnExcluir.setText("EXCLUIR CADASTRO");
    btnExcluir.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnExcluirActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jpNovoPacienteLayout = new javax.swing.GroupLayout(jpNovoPaciente);
    jpNovoPaciente.setLayout(jpNovoPacienteLayout);
    jpNovoPacienteLayout.setHorizontalGroup(
        jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpNovoPacienteLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                    .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addComponent(txtLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel14)
                        .addComponent(txtNumeroResidencia, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11)
                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jcbUf, 0, 1, Short.MAX_VALUE)
                        .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addGap(0, 79, Short.MAX_VALUE))))
                .addComponent(jSeparator1)
                .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                    .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16)
                                .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17)
                                .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jcbTipoSanguineo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18)))
                        .addComponent(jLabel1)
                        .addComponent(jLabel15))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtDum, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(txtPressaoArterial)))
                .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                    .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtAlergia, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel22)
                        .addComponent(txtGestacao, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtDeficiencia)
                        .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                            .addComponent(jLabel23)
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addComponent(jScrollPane1)
                .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                    .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                            .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addComponent(jLabel6))
                    .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                                    .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel4)
                                            .addGap(86, 86, 86)
                                            .addComponent(jLabel5)))
                                    .addGap(33, 33, 33)
                                    .addComponent(jLabel9)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                                    .addComponent(txtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jcbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtProfissao))))
                        .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                                    .addComponent(txtCelular)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                    .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel24)
                        .addGroup(jpNovoPacienteLayout.createSequentialGroup()
                            .addComponent(btnSalvar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnExcluir)))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jpNovoPacienteLayout.setVerticalGroup(
        jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpNovoPacienteLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(jLabel3)
                .addComponent(jLabel4)
                .addComponent(jLabel5))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jcbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(jLabel7)
                .addComponent(jLabel8)
                .addComponent(jLabel9))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCelular, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel10)
                .addComponent(jLabel11)
                .addComponent(jLabel12)
                .addComponent(jLabel13)
                .addComponent(jLabel14))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jcbUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtNumeroResidencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel15)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel16)
                .addComponent(jLabel17)
                .addComponent(jLabel18)
                .addComponent(jLabel19)
                .addComponent(jLabel20))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jcbTipoSanguineo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtDum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtPressaoArterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel21)
                .addComponent(jLabel22)
                .addComponent(jLabel23))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtAlergia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtGestacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtDeficiencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel24)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpNovoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnSalvar)
                .addComponent(btnExcluir))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("Informações do paciente", jpNovoPaciente);

    jScrollPane2.setViewportView(jtHistorico);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
            .addContainerGap())
    );

    jTabbedPane1.addTab("Histórico de consultas", jPanel1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jTabbedPane1)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jTabbedPane1)
    );

    pack();
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCepFocusLost
        // TODO add your handling code here:
        presetCep();
    }//GEN-LAST:event_txtCepFocusLost

    private void txtCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCepActionPerformed

    private void txtDumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDumActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if (checkForm()) {
            PacienteBean pac = new PacienteBean();
            Date dataBr = null;
            String dataUs;
            try {
                pac.setPacienteCodigo(paciente.getPacienteCodigo());
                pac.setPessoaCodigo(paciente.getPacienteCodigo());
                pac.setNome(txtNome.getText());

                dataBr = new SimpleDateFormat("dd/MM/yyyy").parse(txtNascimento.getText());
                dataUs = MaskFormatTextUtil.dataUs(dataBr);
                pac.setDataNascimento(new SimpleDateFormat("yyyy-MM-dd").parse(dataUs));

                pac.setSexo(MaskFormatTextUtil.sexoByGenero((String) jcbSexo.getSelectedItem()));
                pac.setProfissao(txtProfissao.getText());
                pac.setEmail(txtEmail.getText());
                pac.setTelefone(MaskFormatTextUtil.onlyNumbers(txtTelefone.getText()));
                pac.setCelular(MaskFormatTextUtil.onlyNumbers(txtCelular.getText()));
                pac.setCep(MaskFormatTextUtil.onlyNumbers(txtCep.getText()));
                pac.setLogradouro(txtLogradouro.getText());
                pac.setNumeroResidencia(txtNumeroResidencia.getText());
                pac.setBairro(txtBairro.getText());
                pac.setCidade(txtCidade.getText());
                pac.setUf((String) jcbUf.getSelectedItem());
                if (!txtAltura.getText().isEmpty()) {
                    pac.setAltura(MaskFormatTextUtil.changeCommaByDot(txtAltura.getText()));
                } else {
                    pac.setAltura(0.0);
                }
                if (!txtPeso.getText().isEmpty()) {
                    pac.setPeso(MaskFormatTextUtil.changeCommaByDot(txtPeso.getText()));
                } else {
                    pac.setPeso(0.0);
                }
                pac.setTipoSanguineo((String) jcbTipoSanguineo.getSelectedItem());

                if (!txtDum.getText().equalsIgnoreCase("  /  /    ")) {
                    dataBr = new SimpleDateFormat("dd/MM/yyyy").parse(txtDum.getText());
                    dataUs = MaskFormatTextUtil.dataUs(dataBr);
                    pac.setDum(new SimpleDateFormat("yyyy-MM-dd").parse(dataUs));
                } else {
                    pac.setDum(null);
                }

                pac.setPressaoArterial(txtPressaoArterial.getText());
                pac.setAlergia(txtAlergia.getText());
                pac.setGestacao(txtGestacao.getText());
                pac.setDeficiencia(txtDeficiencia.getText());
                pac.setObservacao(jtaObservacao.getText());
                PacienteController.updatePaciente(pac);
            } catch (ParseException ex) {
                System.err.println("Erro ao converter data");
                Logger.getLogger(CadastrarPacienteView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NumberFormatException ex) {
                System.err.println("Erro de conversão de string para numérico");
                Logger.getLogger(CadastrarPacienteView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza qiue deseja excluir este paciente?");
        switch (confirm) {
            case JOptionPane.YES_OPTION:
                PacienteController.deletePaciente(paciente.getPacienteCodigo());
                this.setVisible(false);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditarHistoricoPacienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarHistoricoPacienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarHistoricoPacienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarHistoricoPacienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarHistoricoPacienteView dialog = new EditarHistoricoPacienteView(new javax.swing.JFrame(), true, paciente.getPacienteCodigo());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> jcbSexo;
    private javax.swing.JComboBox<String> jcbTipoSanguineo;
    private javax.swing.JComboBox<String> jcbUf;
    private javax.swing.JPanel jpNovoPaciente;
    private javax.swing.JTable jtHistorico;
    private javax.swing.JTextArea jtaObservacao;
    private javax.swing.JTextField txtAlergia;
    private javax.swing.JFormattedTextField txtAltura;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtDeficiencia;
    private javax.swing.JFormattedTextField txtDum;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGestacao;
    private javax.swing.JTextField txtLogradouro;
    private javax.swing.JFormattedTextField txtNascimento;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumeroResidencia;
    private javax.swing.JFormattedTextField txtPeso;
    private javax.swing.JTextField txtPressaoArterial;
    private javax.swing.JTextField txtProfissao;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
