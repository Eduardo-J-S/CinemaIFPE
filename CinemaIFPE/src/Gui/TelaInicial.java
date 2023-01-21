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
import Database.Conexao;


public class TelaInicial extends JFrame {
	Filme filme = new Filme();
	
	

	//-------CRIANDO A TELA
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public TelaInicial() throws Exception {
		
		setTitle("CineIF Paulista");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/Midia/ifpe.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 720);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//-------CRIANDO OS LABELS
		JLabel cineifPaulista = new JLabel("CineIF Paulista");
		cineifPaulista.setForeground(new Color(63, 164, 13, 236));
		cineifPaulista.setFont(new Font("Sitka Heading", Font.BOLD | Font.ITALIC, 62));
		cineifPaulista.setBounds(304, 11, 445, 132);
		contentPane.add(cineifPaulista);
		
		JLabel emDestaque = new JLabel("Em Destaque:");
		emDestaque.setFont(new Font("Tahoma", Font.BOLD, 22));
		emDestaque.setBounds(402, 208, 171, 43);
	    contentPane.add(emDestaque);
	      
	      

		JButton help = new JButton("Help");
		help.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		 JOptionPane.showMessageDialog(null, "Nessa tela, o cliente pode optar por acessar algum filme " +
	                     "diretamente (os destaques) ou então ele pode acessar uma aba com todos os filmes.\n O administrador pode acessar a " +
	                     "sua tela para ter acesso as suas funcionalidades.", "Help", JOptionPane.QUESTION_MESSAGE);
	      
	      	}
	      });
		help.setFont(new Font("Sitka Heading", Font.PLAIN, 15));
		help.setBounds(897, 25, 66, 29);
		contentPane.add(help);
		
		JButton administrador = new JButton("Administrador");
		administrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAdm telalogin = new TelaAdm();
		        telalogin.setVisible(true);
		        dispose();
			}
		});

		administrador.setFont(new Font("Sitka Heading", Font.PLAIN, 15));
		administrador.setBounds(827, 635, 136, 23);
		contentPane.add(administrador);
		
		JButton verTodosFilmes = new JButton("Ver Todos os Filmes");
		verTodosFilmes.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		TelaDeFilmes telalogin = new TelaDeFilmes();
		        telalogin.setVisible(true);
		        dispose();
	      	}
	      });
		verTodosFilmes.setFont(new Font("Sitka Heading", Font.PLAIN, 15));
		verTodosFilmes.setBounds(737, 203, 171, 29);
	      contentPane.add(verTodosFilmes);
	      
	      
	  //-------CRIANDO A IMAGEM IFPE	
      ImageIcon logo_ifpe = new ImageIcon((getClass().getResource("/midia/ifpe.png")));
      JLabel ifpe = new JLabel(logo_ifpe);
      getContentPane().add(ifpe);
      ifpe.setBounds(0,0,284,281);
      
      JLabel Filme1 = new JLabel("");
      Filme1.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
      		TelaAdm adm = new TelaAdm();
      		adm.setVisible(true);
      		dispose();
      		
      	}
      });
      String buscarfoto1 = new Filme().buscarCartaz(1);
      Filme1.setBounds(70, 316, 214, 255);
      String nomedoarquivo1 = buscarfoto1;
  		Filme1.setIcon(new ImageIcon(nomedoarquivo1));
  		Filme1.setText(buscarfoto1);
  		contentPane.add(Filme1);
     
      
      JLabel Filme2 = new JLabel("");
      Filme2.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        	TelaAdm adm = new TelaAdm();
        	adm.setVisible(true);
        	dispose();
        }
       });
      String buscarfoto2 = new Filme().buscarCartaz(2);
      Filme2.setBounds(373, 316, 214, 255);
      String nomedoarquivo2 = buscarfoto2;
      Filme2.setIcon(new ImageIcon(nomedoarquivo2));
      Filme2.setText(buscarfoto2);
		contentPane.add(Filme2);
      
      JLabel Filme3 = new JLabel("aaaa");
      Filme3.addMouseListener(new MouseAdapter() {
        @Override
         public void mouseClicked(MouseEvent e) {
        	TelaAdm adm = new TelaAdm();
        	adm.setVisible(true);
        	dispose();
        	
        }
       });
      Filme3.setBounds(694, 316, 214, 255);
      String buscarfoto3 = new Filme().buscarCartaz(3);
      String nomedoarquivo3 = buscarfoto3;
      Filme3.setIcon(new ImageIcon(nomedoarquivo3));
      Filme3.setText(buscarfoto3);
		contentPane.add(Filme3);
		
		
		
		JLabel nomeFilme1 = new JLabel();
		nomeFilme1.setFont(new Font("Tahoma", Font.BOLD, 15));
		filme.pegarFilmes(1);
		nomeFilme1.setText(filme.getNome());
		nomeFilme1.setBounds(70, 570, 199, 27);
		contentPane.add(nomeFilme1);
		
	
		JLabel nomeFilme2 = new JLabel("NomeFilme2");
		nomeFilme2.setFont(new Font("Tahoma", Font.BOLD, 15));
		filme.pegarFilmes(2);
		nomeFilme2.setText(filme.getNome());
		nomeFilme2.setBounds(373, 570, 199, 27);
		contentPane.add(nomeFilme2);
		
		JLabel nomeFilme3 = new JLabel("");
		nomeFilme3.setFont(new Font("Tahoma", Font.BOLD, 15));
		filme.pegarFilmes(3);
		nomeFilme3.setText(filme.getNome());
		nomeFilme3.setBounds(737, 570, 199, 27);
		contentPane.add(nomeFilme3);
      
      }
}