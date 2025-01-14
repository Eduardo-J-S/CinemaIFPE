package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Core.EnviarEmail;
import Core.ControlePoltrona;
import Core.Filme;
import Core.FormatTft;
import Core.FormatarEmail;
import Core.FormatedTextfield;
import Core.Lanche;
import Core.Pagamento;
import Core.Sessao;
import Database.Conexao;
import Database.UpdatePoltronas;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;

public class GuiPagamento extends JFrame {

    private JPanel contentPane;
    private JButton btnDebito;
    private JButton btnCredito;
    private JButton pagar;
    private JLabel lblEmail;
    private JLabel lblCpf;
    private JTextField textCvv;
    private JLabel lblNCartao;
    private  JLabel lblCvv;
    private JLabel lblDe; 
    private JLabel lblNomeTitular;
    private JLabel lblTipo;
    private JLabel ifpe;
    private String tipo;
    private JLabel lblConfirmacao;
    private  JFormattedTextField textCpf;
    private JFormattedTextField textNCartao;
    private JFormattedTextField textEmail;
    private JFormattedTextField textNomeTitular;
    private String quantidadeLanche = "";
    private String total = "";
    
    private String email;
    private String nome;



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GuiPagamento frame = new GuiPagamento();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void telaInicial() {
    	try {
			Thread.sleep(2000);
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.setVisible(true);
			dispose();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
    }
    

    public GuiPagamento() {
    	setTitle("Pagamento");
    	setIconImage(Toolkit.getDefaultToolkit().getImage(HubADM.class.getResource("ifpe.png")));
    	setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setSize(1000,720);
        setLocationRelativeTo(null);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        pagar = new JButton("PAGAR");
        pagar.setVisible(false);
        pagar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UpdatePoltronas upPoltrona = new UpdatePoltronas();   
            	ControlePoltrona control = new ControlePoltrona();
            	EnviarEmail correio = new EnviarEmail();
            	Filme filme = new Filme();
            	Sessao sessao = new Sessao();
            	Lanche lanche = new Lanche(); 
            	SelecaoPoltronas poltronas = new SelecaoPoltronas();
            
            	try {
					filme.pegarFilmes(TelaInicial.idFilme);
					sessao.pegarSessao(TelaInicial.idFilme);
				} catch (ClassNotFoundException e2) {
					lblConfirmacao.setText("Reinicie o Sistema.");
				} catch (SQLException e2) {		
					lblConfirmacao.setText("Erro de Conexão. Reinicie o sistema.");
				}
            	
            	//PEGANDO O HORÁRIO DA COMPRA PARA JOGAR NO EMAIL
            	DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
            	LocalDateTime tempo = LocalDateTime.now();
            	String format = formatter.format(tempo);	
	        	
            	try {
                    Pagamento.validacao(textEmail.getText(), textCpf.getText(), textNCartao.getText(), textNomeTitular.getText(), textCvv.getText());
                    control.setPreco(0);
                    
                           
                    
                    if(TelaLanche.getQtdLanche1() > 0) {
                    	lanche.pegarLanche(1);
                    	quantidadeLanche += "\n" + lanche.getNomeLanche() + " x " + TelaLanche.getQtdLanche1();
                    	
                    	EnviarEmail.setMensagem(quantidadeLanche);
                    }
                   
                    if(TelaLanche.getQtdLanche2() > 0) {
                    	lanche.pegarLanche(2);
                    	
                    	quantidadeLanche += "\n" + lanche.getNomeLanche() + " x " + TelaLanche.getQtdLanche2();
                    	EnviarEmail.setMensagem(quantidadeLanche);
                    }
                    
                    if(TelaLanche.getQtdLanche3() > 0) {
                    	lanche.pegarLanche(3);
                    	quantidadeLanche += "\n" + lanche.getNomeLanche() + " x " + TelaLanche.getQtdLanche3();
                    	EnviarEmail.setMensagem(quantidadeLanche);
                    }
                    if(TelaLanche.getQtdLanche4() > 0) {
                    	lanche.pegarLanche(4);
                    	quantidadeLanche += "\n" + lanche.getNomeLanche() + " x " + TelaLanche.getQtdLanche4();
                    	EnviarEmail.setMensagem(quantidadeLanche);
                    }
                    if(TelaLanche.getQtdLanche5() > 0) {
                    	lanche.pegarLanche(5);
                    	quantidadeLanche += "\n" + lanche.getNomeLanche() + " x " + TelaLanche.getQtdLanche5();
                    	EnviarEmail.setMensagem(quantidadeLanche);
                    }
                    if(TelaLanche.getQtdLanche5() > 0 || TelaLanche.getQtdLanche4() > 0 || TelaLanche.getQtdLanche3() > 0 || TelaLanche.getQtdLanche2() > 0
                            ||TelaLanche.getQtdLanche1() > 0) {
                            	
                                total = "Prezado(a)  " + textNomeTitular.getText() + ", Aqui está o seu comprovante: " + "\n\n" + "Lanches:" +  quantidadeLanche 
                            	+ "\n\n" + "Detalhes:" + "\n" + "Tipo de Pagamento: " + tipo  + "\n" +
                                "Valor Lanche: " + "R$:" + Lanche.getPrecoTotal() + "\n" + "Valor Ingresso: " + Filme.getValorTotal() + "\n" +
                            	"Valor Total: " + "R$:" + (Lanche.getPrecoTotal() + Filme.getValorTotal())  + "\n\n" + "Filme selecionado: " + filme.getNome() + "\n" + "Sala: " + 
                                sessao.getSala_numeroSala() + "\n" + "Sessão: " + sessao.getSessao()+ "\n" + "Poltrona selecionada(s): " +
                            	SelecaoPoltronas.getPoltronaSelecionada()  + "\n" + "Pagamento efetuado às " + format; 
                                EnviarEmail.setMensagem(total);    
                         }
                            	//SE NÃO TIVER LANCHE
                    else {
                            	
                           total = "Prezado(a)  " + textNomeTitular.getText() + ", Aqui está o seu comprovante: " + "\n\n" + "Detalhes:" + "\n" +
                           "Tipo de Pagamento: " + tipo  + "\n" + "Valor Ingresso: " + Filme.getValorTotal() + "\n" +
                           "Valor Total: " + "R$:" + (Filme.getValorTotal()) + "\n\n" + "Filme selecionado: " + filme.getNome() + "\n" + "Sala: " + 
                           sessao.getSala_numeroSala() + "\n" + "Sessão: " + sessao.getSessao()+ "\n" + "Poltrona selecionada(s): " +
                           SelecaoPoltronas.getPoltronaSelecionada()  + "\n" + "Pagamento efetuado às " + format; 
                           EnviarEmail.setMensagem(total);     	
                      }
                    
                    
                    EnviarEmail.emitirNota(textEmail.getText());
                  
                    
                    nome = textNomeTitular.getText();
                    
                    lblConfirmacao.setText("");
                    
                    JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso. O seu comprovante" + "\n" +
                    "será enviado para o email informado.",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    
                    
                    
                    TelaInicial tl = new TelaInicial();
                    tl.setVisible(true);

                    dispose();

                    ifpe.setVisible(false);
                    
                	
            		
             		if(SelecaoPoltronas.getA1Set() == "1") {
                		upPoltrona.editarPoltronas("A1", TelaInicial.getIdFilme(), SelecaoPoltronas.getA1Set());
                	}
             			SelecaoPoltronas.setA1Set("0");
                	if(SelecaoPoltronas.getA2Set() == "1") {
                		upPoltrona.editarPoltronas("A2", TelaInicial.getIdFilme(), SelecaoPoltronas.getA2Set());
                	}
                	SelecaoPoltronas.setA2Set("0");
                	if(SelecaoPoltronas.getA3Set() == "1") {
                		upPoltrona.editarPoltronas("A3", TelaInicial.getIdFilme(), SelecaoPoltronas.getA3Set());
                	}
                	SelecaoPoltronas.setA3Set("0");
                	if(SelecaoPoltronas.getA4Set() == "1") {
                		upPoltrona.editarPoltronas("A4", TelaInicial.getIdFilme(), SelecaoPoltronas.getA4Set());
                	}
                	SelecaoPoltronas.setA4Set("0");
                	if(SelecaoPoltronas.getA5Set() == "1") {
                		upPoltrona.editarPoltronas("A5", TelaInicial.getIdFilme(), SelecaoPoltronas.getA5Set());
                	}
                	SelecaoPoltronas.setA5Set("0");
                	if(SelecaoPoltronas.getB1Set() == "1") {
                		upPoltrona.editarPoltronas("B1", TelaInicial.getIdFilme(), SelecaoPoltronas.getB1Set());
               		}
               		SelecaoPoltronas.setB1Set("0");
               		if(SelecaoPoltronas.getB2Set() == "1") {
               			upPoltrona.editarPoltronas("B2", TelaInicial.getIdFilme(), SelecaoPoltronas.getB2Set());
               		}
               		SelecaoPoltronas.setB2Set("0");
               		if(SelecaoPoltronas.getB3Set() == "1") {
               			upPoltrona.editarPoltronas("B3", TelaInicial.getIdFilme(), SelecaoPoltronas.getB3Set());
               		}
               		SelecaoPoltronas.setB3Set("0");
               		if(SelecaoPoltronas.getB4Set() == "1") {
               			upPoltrona.editarPoltronas("B4", TelaInicial.getIdFilme(), SelecaoPoltronas.getB4Set());
               		}
               		SelecaoPoltronas.setB4Set("0");
               		if(SelecaoPoltronas.getB5Set() == "1") {
               			upPoltrona.editarPoltronas("B5", TelaInicial.getIdFilme(), SelecaoPoltronas.getB5Set());                		
                	SelecaoPoltronas.setB5Set("0");
               		}
                	if(SelecaoPoltronas.getC1Set() == "1") {
               			upPoltrona.editarPoltronas("C1", TelaInicial.getIdFilme(), SelecaoPoltronas.getC1Set());
               		}
               		SelecaoPoltronas.setC1Set("0");
               		if(SelecaoPoltronas.getC2Set() == "1") {
               			upPoltrona.editarPoltronas("C2", TelaInicial.getIdFilme(), SelecaoPoltronas.getC2Set());
               		}
               		SelecaoPoltronas.setC2Set("0");
               		if(SelecaoPoltronas.getC3Set() == "1") {
               			upPoltrona.editarPoltronas("C3", TelaInicial.getIdFilme(), SelecaoPoltronas.getC3Set());
               		}
               		SelecaoPoltronas.setC3Set("0");
               		if(SelecaoPoltronas.getC4Set() == "1") {
               			upPoltrona.editarPoltronas("C4", TelaInicial.getIdFilme(), SelecaoPoltronas.getC4Set());
               		}
               		SelecaoPoltronas.setC4Set("0");
               		if(SelecaoPoltronas.getC5Set() == "1") {
               			upPoltrona.editarPoltronas("C5", TelaInicial.getIdFilme(), SelecaoPoltronas.getC5Set());
                	}
                	SelecaoPoltronas.setC5Set("0");
               		if(SelecaoPoltronas.getD1Set() == "1") {
               			upPoltrona.editarPoltronas("D1", TelaInicial.getIdFilme(), SelecaoPoltronas.getD1Set());
               		}
               		SelecaoPoltronas.setD1Set("0");
               		if(SelecaoPoltronas.getD2Set() == "1") {
               			upPoltrona.editarPoltronas("D2", TelaInicial.getIdFilme(), SelecaoPoltronas.getD2Set());
               		}
               		SelecaoPoltronas.setD2Set("0");
               		if(SelecaoPoltronas.getD3Set() == "1") {
               			upPoltrona.editarPoltronas("D3", TelaInicial.getIdFilme(), SelecaoPoltronas.getD3Set());
               		}
               		SelecaoPoltronas.setD3Set("0");
               		if(SelecaoPoltronas.getD4Set() == "1") {
               			upPoltrona.editarPoltronas("D4", TelaInicial.getIdFilme(), SelecaoPoltronas.getD4Set());
               		}
               		SelecaoPoltronas.setD4Set("0");
                	if(SelecaoPoltronas.getD5Set() == "1") {
                		upPoltrona.editarPoltronas("D5", TelaInicial.getIdFilme(), SelecaoPoltronas.getD5Set());
                	}
                	SelecaoPoltronas.setD5Set("0");
                	
                	
                	
                	
                	
                	SelecaoPoltronas.getPoltronaSelecionada().clear();
					TelaLanche.setQtdLanche1(0);
					TelaLanche.setQtdLanche2(0);
					TelaLanche.setQtdLanche3(0);
					TelaLanche.setQtdLanche4(0);
					TelaLanche.setQtdLanche5(0);
                	
					}catch(AddressException e1){
						lblConfirmacao.setText("Email Inválido");
					}catch(MessagingException e1){
						lblConfirmacao.setText("Email Inválido");
					}
            		catch(RuntimeException run){
						lblConfirmacao.setText(run.getMessage());
					} catch (ClassNotFoundException e1) {
						lblConfirmacao.setText("Erro");
					} catch (SQLException e1) {
					 	lblConfirmacao.setText("Erro ao conectar. Tente novamente");
					}
            	}
        	});
        
        pagar.setBounds(439, 598, 89, 35);
        contentPane.add(pagar);
        

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            		
            	SelecaoPoltronas.resetarPoltronaSelecionada();
            	TelaLanche.setQtdLanche1(0);
				TelaLanche.setQtdLanche2(0);
				TelaLanche.setQtdLanche3(0);
				TelaLanche.setQtdLanche4(0);
				TelaLanche.setQtdLanche5(0);

                TelaLanche lanche = new TelaLanche();
                lanche.setVisible(true);
                
                dispose();
            }
        });  
        btnVoltar.setBackground(new Color(240, 240, 240));
        btnVoltar.setIcon(new ImageIcon(TelaTodosFilmes.class.getResource("retornar.png")));
        btnVoltar.setBorderPainted(false);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setBounds(10, 22, 66, 40);
        btnVoltar.setText("");
        contentPane.add(btnVoltar);
        
        JLabel cineifPaulista = new JLabel("CineIF Paulista");
	  	cineifPaulista.setHorizontalAlignment(SwingConstants.CENTER);
	  	cineifPaulista.setForeground(new Color(63, 164, 13, 236));
	  	cineifPaulista.setFont(new Font("Sitka Heading", Font.BOLD | Font.ITALIC, 42));
	  	cineifPaulista.setBounds(20, 22, 954, 85);
	  	contentPane.add(cineifPaulista);
        
        JLabel lblNewLabel = new JLabel("PAGAMENTO");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
        lblNewLabel.setBounds(415, 195, 166, 35);
        contentPane.add(lblNewLabel);
        
        JLabel lblSelecione = new JLabel("SELECIONE A FORMA");
        lblSelecione.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelecione.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblSelecione.setBounds(21, 236, 192, 44);
        contentPane.add(lblSelecione);
        
        btnDebito = new JButton("Débito");
        btnDebito.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		lblNomeTitular.setVisible(true);
        		textNomeTitular.setVisible(true);
        		lblEmail.setVisible(true);
        		textEmail.setVisible(true);
        		lblCvv.setVisible(true);
        		textCvv.setVisible(true);
        		lblNCartao.setVisible(true);
        		textNCartao.setVisible(true);
        		lblCpf.setVisible(true);
        		textCpf.setVisible(true);
        		tipo = "Débito";
        		lblTipo.setText(tipo);
        		pagar.setVisible(true);
        		ifpe.setVisible(false);
        		
        		textNomeTitular.setText("");
                textEmail.setText("");
                textCvv.setText("");
                textNCartao.setText("");
                textCpf.setText("");
        
        		
        	}
        });
        btnDebito.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnDebito.setBounds(71, 432, 89, 23);
        contentPane.add(btnDebito);
        
        btnCredito = new JButton("Crédito");
        btnCredito.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnCredito.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		lblNomeTitular.setVisible(true);
        		textNomeTitular.setVisible(true);
        		lblEmail.setVisible(true);
        		textEmail.setVisible(true);
        		lblCvv.setVisible(true);
        		textCvv.setVisible(true);
        		lblNCartao.setVisible(true);
        		textNCartao.setVisible(true);
        		lblCpf.setVisible(true);
        		textCpf.setVisible(true);
        		pagar.setVisible(true);
        		tipo = "Crédito";
        		lblTipo.setText(tipo);
        		ifpe.setVisible(false);
        		
        		textNomeTitular.setText("");
                textEmail.setText("");
                textCvv.setText("");
                textNCartao.setText("");
                textCpf.setText("");
        		
        	}
        });
        btnCredito.setBounds(71, 488, 89, 23);
        contentPane.add(btnCredito);
        
        JLabel lblNewLabel_2 = new JLabel();
        lblNewLabel_2.setIcon(new ImageIcon(InfoFilme.class.getResource("cartao.png")));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2.setBounds(92, 386, 43, 35);
        contentPane.add(lblNewLabel_2);
        
        JButton help = new JButton("");
		help.setIcon(new ImageIcon(TelaInicial.class.getResource("ponto-de-interrogacao.png")));
		help.setBorderPainted(false);
		help.setFocusPainted(false);
		help.setBackground(new Color(240, 240, 240));
		help.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		 JOptionPane.showMessageDialog(null, "Nessa tela, você pode optar pelas formas de pagamento disponíveis e " + "\n" +
	      	"realizar o pagamento.",
	                    "Help", JOptionPane.QUESTION_MESSAGE);
	      	}
	     });
  		help.setFont(new Font("Sitka Heading", Font.PLAIN, 15));
  		help.setBounds(909, 30, 65, 32);
  		contentPane.add(help);
		
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.setBounds(749, 308, 212, 189);
        contentPane.add(panel_1);
        
        JLabel lblTotal = new JLabel("TOTAL GERAL:          R$" + (Lanche.getPrecoTotal() + Filme.getValorTotal()) );
        lblTotal.setBounds(10, 153, 199, 25);
        panel_1.add(lblTotal);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 12));
        
        JLabel lblLanche = new JLabel("TOTAL LANCHES:     R$" + Lanche.getPrecoTotal() );
        lblLanche.setBounds(10, 84, 199, 25);
        panel_1.add(lblLanche);
        lblLanche.setFont(new Font("Arial", Font.BOLD, 12));
        
        JLabel lblIngresso = new JLabel();
        lblIngresso.setBounds(10, 11, 308, 25);
        panel_1.add(lblIngresso);
        lblIngresso.setText("TOTAL INGRESSO:    R$" + Filme.getValorTotal());
        lblIngresso.setFont(new Font("Arial", Font.BOLD, 12));
        
        JLabel lblNewLabel_3 = new JLabel("Custos");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_3.setBounds(820, 257, 112, 33);
        contentPane.add(lblNewLabel_3);
        
        lblDe = new JLabel("DE");
        lblDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblDe.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblDe.setBounds(21, 277, 192, 44);
        contentPane.add(lblDe);
        
        JLabel lblPagamento = new JLabel("PAGAMENTO:");
        lblPagamento.setHorizontalAlignment(SwingConstants.CENTER);
        lblPagamento.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblPagamento.setBounds(21, 311, 192, 44);
        contentPane.add(lblPagamento);
        	
        
        lblEmail = new JLabel("Email:");
        lblEmail.setVisible(false);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEmail.setBounds(366, 309, 43, 18);
        contentPane.add(lblEmail);
        
        
        lblCpf = new JLabel("CPF:");
        lblCpf.setVisible(false);
        lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblCpf.setBounds(366, 356, 30, 18);
        contentPane.add(lblCpf);
        
        
        lblNCartao = new JLabel("N° Cartão");
        lblNCartao.setVisible(false);
        lblNCartao.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNCartao.setBounds(334, 410, 75, 18);
        contentPane.add(lblNCartao);
        
        
        
        textCvv = new JTextField();
        textCvv.setDocument(new FormatTft(3, FormatTft.TipoEntrada.CVV));
        textCvv.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {	
			char c = e.getKeyChar();
			if(!Character.isDigit(c)) {
				e.consume();
				}
			}
		});
        textCvv.setVisible(false);
        textCvv.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textCvv.setColumns(10);
        textCvv.setBounds(415, 507, 156, 20);
        contentPane.add(textCvv);
        
     
        lblCvv = new JLabel("(CVV):");
        lblCvv.setVisible(false);
        lblCvv.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblCvv.setBounds(355, 508, 44, 18);
        contentPane.add(lblCvv);
        
     
        lblNomeTitular = new JLabel("Nome Titular:");
        lblNomeTitular.setVisible(false);
        lblNomeTitular.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNomeTitular.setBounds(317, 463, 92, 18);
        contentPane.add(lblNomeTitular);
        
        lblTipo = new JLabel("");
        lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipo.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblTipo.setBounds(426, 257, 102, 23);
        contentPane.add(lblTipo);
        
       
        
        ImageIcon logo_ifpe = new ImageIcon((getClass().getResource("ifpe.png")));
        ifpe = new JLabel(logo_ifpe);
        ifpe.setFont(new Font("Tahoma", Font.PLAIN, 15));
        getContentPane().add(ifpe);
        ifpe.setBounds(344,270,284,281);
        
        lblConfirmacao = new JLabel("");
        lblConfirmacao.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblConfirmacao.setHorizontalAlignment(SwingConstants.CENTER);
        lblConfirmacao.setBounds(366, 562, 241, 23);
        contentPane.add(lblConfirmacao);
        
        
        textCpf = new JFormattedTextField();
        textCpf.setDocument(new FormatTft(11, FormatTft.TipoEntrada.CPF));
        textCpf.addKeyListener(new KeyAdapter() {
			
		@Override
		public void keyTyped(KeyEvent e) {
		  	char c = e.getKeyChar();
			if(!Character.isDigit(c)) {
				e.consume();
				}	
			}
		});
        textCpf.getText().length();
        
        textCpf.setVisible(false);
        textCpf.setBounds(415, 357, 156, 20);
        contentPane.add(textCpf);
        
        
        
        textNCartao = new JFormattedTextField();
        textNCartao.setDocument(new FormatTft(16, FormatTft.TipoEntrada.NCARTAO));
        textNCartao.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyTyped(KeyEvent e) {
    		  	char c = e.getKeyChar();
    			if(!Character.isDigit(c)) {
    				e.consume();
    				}	
    			}
    		});
        textNCartao.setVisible(false);
        textNCartao.setBounds(415, 411, 156, 20);
        contentPane.add(textNCartao);
        
        
        
        
        
        textEmail = new JFormattedTextField();
        FormatarEmail email = new FormatarEmail(100);
        textEmail.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        	    char c = e.getKeyChar();

        	  if(!Character.isAlphabetic(c) && Character.isSpaceChar(c)) {
        	      e.consume();
        	      }
        	  }
        });
        textEmail.setDocument(email);
        textEmail.setVisible(false);
        textEmail.setBounds(415, 308, 156, 20);
        contentPane.add(textEmail);
        
      
      textNomeTitular = new JFormattedTextField();
      textNomeTitular.setDocument(new FormatTft(30, FormatTft.TipoEntrada.NOME));
      textNomeTitular.addKeyListener(new KeyAdapter() {
  		@Override
  		public void keyTyped(KeyEvent e) {
  		  	char c = e.getKeyChar();
  		  
  			if(!Character.isAlphabetic(c) && !Character.isWhitespace(c)) {
  				e.consume();
  				}	
  			}
  		});
      
      textNomeTitular.setVisible(false);
      textNomeTitular.setBounds(415, 464, 156, 20);
      contentPane.add(textNomeTitular);
  }
}