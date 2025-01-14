package Gui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Core.Filme;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import Database.Conexao;
import Database.CreateDatabase;
import Database.CreateInserts;

import javax.swing.SwingConstants;


public class TelaInicial extends JFrame{
	Filme filme = new Filme();
	static int idFilme;
	
	public static int getIdFilme() {
		return idFilme;
		
	}
	public static void setIdFilme(int valor) {
		idFilme = valor;
	}
	

	//-------CRIANDO A TELA
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateDatabase data = new CreateDatabase();
					CreateInserts insert = new CreateInserts();
					
					data.createBD();
					data.createTableFilme();
					insert.insertSala();
					
					TelaInicial telaInicial = new TelaInicial();
					telaInicial.setVisible(true);
					
				}catch(SQLIntegrityConstraintViolationException e) {
					TelaInicial telaInicial = new TelaInicial();
					telaInicial.setVisible(true);
					
				}catch(IndexOutOfBoundsException e1) {
					HubADM teste = new HubADM();
					teste.setVisible(true);
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TelaInicial() {
		
		
		setTitle("CineIF Paulista");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("ifpe.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 720);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//-------CRIANDO OS LABELS
		
		JLabel nomeFilme1 = new JLabel();
		nomeFilme1.setHorizontalAlignment(SwingConstants.CENTER);
		nomeFilme1.setFont(new Font("Tahoma", Font.BOLD, 15));
		try {
			filme.pegarFilmes(1);
		} catch (ClassNotFoundException e3) {
			nomeFilme1.setText("Erro. Reinicie o sistema.");
		} catch (SQLException e3) {
			nomeFilme1.setText("Erro. Reinicie o sistema.");
			
		}
		nomeFilme1.setText(filme.getNome());
		nomeFilme1.setBounds(70, 570, 199, 27);
		contentPane.add(nomeFilme1);
		
	
		JLabel nomeFilme2 = new JLabel("NomeFilme2");
		nomeFilme2.setHorizontalAlignment(SwingConstants.CENTER);
		nomeFilme2.setFont(new Font("Tahoma", Font.BOLD, 15));
		try {
			filme.pegarFilmes(2);
		} catch (ClassNotFoundException e2) {
			nomeFilme1.setText("Erro. Reinicie o sistema.");
		} catch (SQLException e2) {
			nomeFilme1.setText("Erro. Reinicie o sistema.");
		}
		nomeFilme2.setText(filme.getNome());
		nomeFilme2.setBounds(373, 570, 199, 27);
		contentPane.add(nomeFilme2);
		
		JLabel nomeFilme3 = new JLabel("");
		nomeFilme3.setHorizontalAlignment(SwingConstants.CENTER);
		nomeFilme3.setFont(new Font("Tahoma", Font.BOLD, 15));
		try {
			filme.pegarFilmes(3);
		} catch (ClassNotFoundException e1) {
			nomeFilme1.setText("Erro. Reinicie o sistema.");
		} catch (SQLException e1) {
			nomeFilme1.setText("Erro. Reinicie o sistema.");
		}
		nomeFilme3.setText(filme.getNome());
		nomeFilme3.setBounds(694, 570, 199, 27);
		contentPane.add(nomeFilme3);
      
      
		JLabel cineifPaulista = new JLabel("CineIF Paulista");
		cineifPaulista.setHorizontalAlignment(SwingConstants.CENTER);
		cineifPaulista.setForeground(new Color(63, 164, 13, 236));
		cineifPaulista.setFont(new Font("Sitka Heading", Font.BOLD | Font.ITALIC, 42));
		cineifPaulista.setBounds(10, 11, 964, 132);
		contentPane.add(cineifPaulista);
		
		JLabel emDestaque = new JLabel("Em Destaque:");
		emDestaque.setFont(new Font("Tahoma", Font.BOLD, 22));
		emDestaque.setBounds(402, 208, 171, 43);
	    contentPane.add(emDestaque);
	      
	      

		JButton help = new JButton("");
		help.setIcon(new ImageIcon(TelaInicial.class.getResource("ponto-de-interrogacao.png")));
		help.setBorderPainted(false);
		help.setFocusPainted(false);
		help.setBackground(new Color(240, 240, 240));
		help.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		 JOptionPane.showMessageDialog(null, "Nessa tela, o cliente pode optar por acessar algum filme " +
	                     "diretamente (os destaques) ou então ele pode acessar uma aba com todos os filmes.\n O administrador pode acessar a " +
	                     "sua tela para ter acesso as suas funcionalidades.", "Help", JOptionPane.QUESTION_MESSAGE);
	      
	      	}
	      });
		help.setFont(new Font("Sitka Heading", Font.PLAIN, 15));
		help.setBounds(909, 30, 65, 32);
		contentPane.add(help);
		
		JButton administrador = new JButton();
		administrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAdm telalogin = new TelaAdm();
		        telalogin.setVisible(true);
		        dispose();
			}
		});
		administrador.setIcon(new ImageIcon(TelaInicial.class.getResource("administrador.png")));
		administrador.setBorderPainted(false);
		administrador.setFocusPainted(false);
		administrador.setBackground(new Color(240, 240, 240));
		administrador.setFont(new Font("Sitka Heading", Font.PLAIN, 15));
		administrador.setBounds(907, 618, 56, 40);
		contentPane.add(administrador);
		
		JButton verTodosFilmes = new JButton();
		verTodosFilmes.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		TelaTodosFilmes telalogin = new TelaTodosFilmes();
		        telalogin.setVisible(true);
		        dispose();
	      	}
	      });
		verTodosFilmes.setIcon(new ImageIcon(TelaInicial.class.getResource("verTodososFilmes.png")));
		verTodosFilmes.setFocusPainted(false);
		verTodosFilmes.setBackground(new Color(240, 240, 240));
		verTodosFilmes.setFont(new Font("Sitka Heading", Font.PLAIN, 15));
		verTodosFilmes.setBounds(766, 187, 171, 51);
	      contentPane.add(verTodosFilmes);
	      
	      
	  //-------CRIANDO A IMAGEM IFPE	
      ImageIcon logo_ifpe = new ImageIcon((getClass().getResource("ifpe.png")));
      JLabel ifpe = new JLabel(logo_ifpe);
      getContentPane().add(ifpe);
      ifpe.setBounds(0,0,284,281);
     
      
      JLabel Filme1 = new JLabel();
      Filme1.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
      		setIdFilme(1);
      		InfoFilme infoFilme;
			infoFilme = new InfoFilme();
			infoFilme.setVisible(true);
      		dispose();
      	}
      });
      
      String buscarfoto1;
	try {
		buscarfoto1 = new Filme().buscarCartaz(1);
		Filme1.setBounds(70, 316, 214, 255);
	    String nomedoarquivo1 = buscarfoto1;
	    Filme1.setIcon(new ImageIcon(nomedoarquivo1));
	  	Filme1.setText(buscarfoto1);
	  	contentPane.add(Filme1);
	} catch (ClassNotFoundException e4) {
		Filme1.setText("Erro de conexão");
	} catch (SQLException e4) {
		Filme1.setText("Erro de conexão");
	}
      
     
      
      JLabel Filme2 = new JLabel("");
      Filme2.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        	setIdFilme(2);
			InfoFilme infoFilme = new InfoFilme();
	        infoFilme.setVisible(true);
        	dispose();
        }
       });
      String buscarfoto2;
	try {
		buscarfoto2 = new Filme().buscarCartaz(2);
		Filme2.setBounds(373, 316, 214, 255);
	    String nomedoarquivo2 = buscarfoto2;
	    Filme2.setIcon(new ImageIcon(nomedoarquivo2));
	    Filme2.setText(buscarfoto2);
	    contentPane.add(Filme2);
	} catch (ClassNotFoundException e4) {
		Filme2.setText("Erro de conexão");
	} catch (SQLException e4) {
		Filme2.setText("Erro de conexão");
	}
     
      
      JLabel Filme3 = new JLabel("aaaa");
      Filme3.addMouseListener(new MouseAdapter() {
        @Override
         public void mouseClicked(MouseEvent e) {
        	setIdFilme(3);
        	InfoFilme infoFilme = new InfoFilme();
        	infoFilme.setVisible(true);
        	dispose();
        	
        }
       });
      Filme3.setBounds(694, 316, 214, 255);
      String buscarfoto3;
	try {
		buscarfoto3 = new Filme().buscarCartaz(3);
		String nomedoarquivo3 = buscarfoto3;
	    Filme3.setIcon(new ImageIcon(nomedoarquivo3));
	    Filme3.setText(buscarfoto3);
		contentPane.add(Filme3);
	} catch (ClassNotFoundException e4) {
		Filme3.setText("Erro de conexão");
	} catch (SQLException e4) {
		Filme3.setText("Erro de conexão");
	}
     
	
		
	}	
}