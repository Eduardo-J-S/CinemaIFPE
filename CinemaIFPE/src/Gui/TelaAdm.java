package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAdm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	//-------CRIANDO A TELA
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdm frame = new TelaAdm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public TelaAdm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//-------CRIANDO OS LABELS
		JLabel cineifPaulista = new JLabel("CineIF Paulista");
		cineifPaulista.setForeground(new Color(63, 164, 13, 236));
		cineifPaulista.setFont(new Font("Sitka Heading", Font.BOLD | Font.ITALIC, 62));
		cineifPaulista.setBounds(235, 11, 445, 132);
		contentPane.add(cineifPaulista);
		
		JLabel usuario = new JLabel("Usuário:");
		usuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usuario.setBounds(274, 474, 81, 29);
	    contentPane.add(usuario);
	      
	    JLabel senha = new JLabel("Senha:");
	    senha.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    senha.setBounds(285, 525, 66, 29);
	    contentPane.add(senha);
	      
	    JLabel mensagem = new JLabel("       Mensagem");
	    mensagem.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    mensagem.setBounds(369, 576, 141, 25);
	    contentPane.add(mensagem);
	      
	    JLabel administrador = new JLabel("ADMINISTRADOR");
	    administrador.setFont(new Font("Tahoma", Font.BOLD, 18));
	    administrador.setBounds(344, 438, 163, 25);
	    contentPane.add(administrador);
		
	    //-------CRIANDO OS BOTÕES
	    JButton voltar = new JButton("Voltar");
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial voltar = new TelaInicial();
		        voltar.setVisible(true);
		        dispose();
			}
		});
		voltar.setFont(new Font("Sitka Heading", Font.PLAIN, 15));
		voltar.setBounds(10, 22, 75, 35);
		contentPane.add(voltar);
	
		
		JButton help = new JButton("Help");
		help.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	JOptionPane.showMessageDialog(null, "Essa tela é restrita ao administrador. Ele pode fazer o Login e ter acesso" +
	        "as suas funções de trabalho.", "Help", JOptionPane.QUESTION_MESSAGE);
	      	}
	      });
		help.setFont(new Font("Sitka Heading", Font.PLAIN, 15));

		help.setBounds(880, 41, 66, 29);
		contentPane.add(help);
		
		JButton entrar = new JButton("Entrar");
		entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		entrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		entrar.setBounds(379, 612, 117, 23);
		contentPane.add(entrar);
		
		
		
		//--------CRIANDO AS CAIXAS DE TEXTO
		textField = new JTextField();
	    textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    textField.setBounds(355, 474, 163, 29);
	    contentPane.add(textField);
	    textField.setColumns(10);
	      
	    passwordField = new JPasswordField();
	    passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    passwordField.setBounds(355, 525, 163, 29);
	    contentPane.add(passwordField);
	    
	    
		
		 //-------CRIANDO A IMAGEM IFPE	
	     ImageIcon logo_ifpe = new ImageIcon((getClass().getResource("/midia/ifpe.png")));
	     JLabel ifpe = new JLabel(logo_ifpe);
	     getContentPane().add(ifpe);
	      ifpe.setBounds(285,130,284,281);
	      
	}
}
//