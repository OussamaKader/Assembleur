package TestProj2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;
import java.awt.Font;

public class AOProject_Partie_2 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean valide=true;
	private JTable table;
	private JTable RAM;
	private JTextField PC;
	private JTextField A;
	private JTextField B;
	private JTextField X;
	private JTextField Y;
	private JTextField U;
	private JTextField S;
	private JTextField DP;
	private JTextField CCR;
	String[][] ROM = null;
	String[] columnNames = { "adress", "code" };
	String[][] RAM_ = null;
	String[] nom = { "address", "valeur" };

	public AOProject_Partie_2() {
		getContentPane().setBackground(SystemColor.control);
		setTitle("MOT6809");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 583);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 224, 149);
		getContentPane().add(scrollPane);

		JScrollPane scrollPane_RAM = new JScrollPane();
		scrollPane_RAM.setSize(224, 149);
		scrollPane_RAM.setLocation(244, 10);
		scrollPane.setBounds(10, 10, 224, 149);
		getContentPane().add(scrollPane_RAM);

		JTextArea code_espace = new JTextArea();
		code_espace.setBounds(699, 11, 181, 293);
		getContentPane().add(code_espace);

		PC = new JTextField();
		PC.setEditable(false);
		PC.setBounds(39, 169, 212, 19);
		getContentPane().add(PC);
		PC.setColumns(10);

		A = new JTextField();
		A.setEditable(false);
		A.setColumns(10);
		A.setBounds(122, 198, 129, 19);
		getContentPane().add(A);

		B = new JTextField();
		B.setEditable(false);
		B.setColumns(10);
		B.setBounds(122, 227, 129, 19);
		getContentPane().add(B);

		X = new JTextField();
		X.setEditable(false);
		X.setColumns(10);
		X.setBounds(39, 256, 212, 19);
		getContentPane().add(X);

		Y = new JTextField();
		Y.setEditable(false);
		Y.setColumns(10);
		Y.setBounds(39, 285, 212, 19);
		getContentPane().add(Y);

		U = new JTextField();
		U.setEditable(false);
		U.setColumns(10);
		U.setBounds(39, 314, 212, 19);
		getContentPane().add(U);

		S = new JTextField();
		S.setEditable(false);
		S.setColumns(10);
		S.setBounds(39, 343, 212, 19);
		getContentPane().add(S);

		DP = new JTextField();
		DP.setEditable(false);
		DP.setColumns(10);
		DP.setBounds(122, 372, 129, 19);
		getContentPane().add(DP);

		CCR = new JTextField();
		CCR.setEditable(false);
		CCR.setColumns(10);
		CCR.setBounds(122, 401, 129, 19);
		getContentPane().add(CCR);

		JLabel pc = new JLabel("PC");
		pc.setBounds(10, 172, 45, 13);
		getContentPane().add(pc);

		JLabel a = new JLabel("A");
		a.setBounds(100, 198, 27, 13);
		getContentPane().add(a);

		JLabel b = new JLabel("B");
		b.setBounds(100, 227, 27, 13);
		getContentPane().add(b);

		JLabel x = new JLabel("X");
		x.setBounds(10, 259, 27, 13);
		getContentPane().add(x);

		JLabel y = new JLabel("Y");
		y.setBounds(10, 288, 27, 13);
		getContentPane().add(y);

		JLabel u = new JLabel("U");
		u.setBounds(10, 317, 27, 13);
		getContentPane().add(u);

		JLabel s = new JLabel("S");
		s.setBounds(10, 346, 27, 13);
		getContentPane().add(s);

		JLabel dp = new JLabel("DP");
		dp.setBounds(90, 375, 27, 13);
		getContentPane().add(dp);

		JLabel ccr = new JLabel("CCR");
		ccr.setBounds(90, 404, 27, 13);
		getContentPane().add(ccr);

		JButton COMPILE = new JButton("COMPILE");
		COMPILE.setFont(new Font("Tahoma", Font.BOLD, 11));
		COMPILE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cont = code_espace.getText();
				if (cont.isEmpty()) {
					JFrame Frame = new JFrame("Écriture dans un fichier");
					Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					Frame.setSize(400, 300);
					Frame.getContentPane().setLayout(new BorderLayout());
					JOptionPane.showMessageDialog(Frame, "Veuillez entrer du code avant de compiler.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Compelation.init_Rom();
				Execution.init_Ram();
				File_class.FILE(code_espace.getText());
				Compelation.Rom[Compelation.adress][1]="3F";
				ROM = Compelation.Rom;
				table = new JTable();
				table.setModel(new DefaultTableModel(ROM, columnNames));
				scrollPane.setViewportView(table);
			}
		});
		COMPILE.setBounds(118, 501, 110, 21);
		getContentPane().add(COMPILE);

		JButton pas = new JButton("PAS A PAS");
		pas.setFont(new Font("Tahoma", Font.BOLD, 11));
		pas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (valide&&Execution.detectMode()) {
					RAM_ = Execution.RAM;
					RAM = new JTable();
					RAM.setModel(new DefaultTableModel(RAM_, nom));
					scrollPane_RAM.setViewportView(RAM);
					PC.setText( Execution.RAM[Execution.address][0]);
					A.setText(new String(Execution.A));
					B.setText(new String(Execution.B));
					X.setText(new String(Execution.X));
					Y.setText(new String(Execution.Y));
					U.setText(new String(Execution.U));
					S.setText(new String(Execution.S));
					DP.setText(new String(Execution.DP));
					CCR.setText(new String(Execution.CCR));
				}
			}
		});
		pas.setBounds(240, 501, 119, 21);
		getContentPane().add(pas);

		JButton EXECUTE = new JButton("EXECUTE");
		EXECUTE.setFont(new Font("Tahoma", Font.BOLD, 11));
		EXECUTE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while (valide&&Execution.detectMode()) {
					RAM_ = Execution.RAM;
					RAM = new JTable();
					RAM.setModel(new DefaultTableModel(RAM_, nom));
					scrollPane_RAM.setViewportView(RAM);
					PC.setText( Execution.RAM[Execution.address][0]);
					A.setText(new String(Execution.A));
					B.setText(new String(Execution.B));
					X.setText(new String(Execution.X));
					Y.setText(new String(Execution.Y));
					U.setText(new String(Execution.U));
					S.setText(new String(Execution.S));
					DP.setText(new String(Execution.DP));
					CCR.setText(new String(Execution.CCR));
				}
			}

		});
		EXECUTE.setBounds(367, 501, 103, 21);
		getContentPane().add(EXECUTE);

		JButton SAVE = new JButton("SAVE");
		SAVE.setFont(new Font("Tahoma", Font.BOLD, 11));
		SAVE.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFrame Frame = new JFrame("Écriture dans un fichier");
				Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Frame.setSize(400, 300);
				Frame.getContentPane().setLayout(new BorderLayout());

				String cont = code_espace.getText();
				if (cont.isEmpty()) {
					JOptionPane.showMessageDialog(Frame, "Veuillez entrer du code avant de sauvegarder.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				JFileChooser fileChooser = new JFileChooser();
				int option = fileChooser.showSaveDialog(Frame);
				if (option == JFileChooser.APPROVE_OPTION) {
					String filePath = fileChooser.getSelectedFile().getAbsolutePath();
					saveToFile(filePath, cont);
				}
			}
		});
		SAVE.setBounds(689, 501, 100, 21);
		getContentPane().add(SAVE);
		JButton Ram_Ecrir = new JButton("Ram_Ecrir");
		Ram_Ecrir.setFont(new Font("Tahoma", Font.BOLD, 11));
		Ram_Ecrir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setBounds(450, 300, 250, 195);
				frame.getContentPane().setLayout(null);
				frame.setVisible(true);
				JTextField Valeur = new JTextField();
				Valeur.setBounds(120, 77, 65, 19);
				frame.getContentPane().add(Valeur);
				Valeur.setColumns(10);
				
				JTextField adress = new JTextField();
				adress.setBounds(45, 77, 65, 19);
				frame.getContentPane().add(adress);
				adress.setColumns(10);
				
				JLabel ADDRESS = new JLabel("ADDRESS");
				ADDRESS.setBounds(45, 58, 45, 13);
				frame.getContentPane().add(ADDRESS);
				
				JLabel VALEUR = new JLabel("VALEUR");
				VALEUR.setBounds(125, 54, 45, 13);
				frame.getContentPane().add(VALEUR);
				
				JButton Ecrir = new JButton("Ecrir");
				Ecrir.setBounds(85, 106, 85, 21);
				frame.getContentPane().add(Ecrir);
				Ecrir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean test=true;
						String add=adress.getText().trim();
						String val=Valeur.getText().trim();
						if(add.length()!=4){
							ERROR("address invalid");
							valide=true;
							test=false;
						}
						 if(val.length()!=2) {
							ERROR("valeur invalid");
							valide=true;
						}
						try {
							int value=Integer.parseInt(val.substring(0,1), 16);
							value=Integer.parseInt(val, 16);
							if(value<0) {
								ERROR("valeur invalid");
								valide=true;
								test=false;
							}
							if(add.charAt(0)=='-'||add.charAt(0)=='+') {
								ERROR(" invalid address");
								valide=true;
								test=false;
							}
							int add_int=Integer.parseInt(add, 16);
							if(add_int>6000) {
								ERROR("la plus grand address est 1770H");
								valide=true;
								test=false;
							}
							if(test) {
								Execution.RAM[add_int][1]=val;
								RAM_ = Execution.RAM;
								ERROR("Ecritur bien fait");
								valide=true;
								RAM = new JTable();
								RAM.setModel(new DefaultTableModel(RAM_, nom));
								scrollPane_RAM.setViewportView(RAM);
								frame.dispose();
							}
							
						}catch(Exception e1) {
							ERROR("Input invald");
							valide=true;
							test=false;
						}
						
					}
				});
			}
		});
		Ram_Ecrir.setBounds(591, 501, 91, 21);
		getContentPane().add(Ram_Ecrir);
		
		JButton RESET = new JButton("RESET");
		RESET.setFont(new Font("Tahoma", Font.BOLD, 11));
		RESET.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Compelation.init_Rom();
				Execution.init_Ram();
				Execution.address = 0;
				Execution.A[0] = '0';
				Execution.A[1] = '0';
				Execution.B[0] = '0';
				Execution.B[1] = '0';
				Execution.PC[0] = '0';
				Execution.PC[1] = '0';
				Execution.PC[2] = '0';
				Execution.PC[3] = '0';
				Execution.S[0] = '0';
				Execution.S[1] = '0';
				Execution.S[2] = '0';
				Execution.S[3] = '0';
				Execution.X[0] = '0';
				Execution.X[1] = '0';
				Execution.X[2] = '0';
				Execution.X[3] = '0';
				Execution.U[0] = '0';
				Execution.U[1] = '0';
				Execution.U[2] = '0';
				Execution.U[3] = '0';
				Execution.Y[0] = '0';
				Execution.Y[1] = '0';
				Execution.Y[2] = '0';
				Execution.Y[3] = '0';
				Execution.DP[0] = '0';
				Execution.DP[1] = '0';
				Execution.CCR[0] = '0';
				Execution.CCR[1] = '0';
				Execution.CCR[2] = '0';
				Execution.CCR[3] = '0';
				Execution.CCR[4] = '0';
				Execution.CCR[5] = '0';
				Execution.CCR[6] = '0';
				Execution.CCR[7] = '0';

				RAM_ = Execution.RAM;
				RAM = new JTable();
				RAM.setModel(new DefaultTableModel(RAM_, nom));
				scrollPane_RAM.setViewportView(RAM);
				PC.setText( Execution.RAM[Execution.address][0]);
				A.setText(new String(Execution.A));
				B.setText(new String(Execution.B));
				X.setText(new String(Execution.X));
				Y.setText(new String(Execution.Y));
				U.setText(new String(Execution.U));
				S.setText(new String(Execution.S));
				DP.setText(new String(Execution.DP));
				CCR.setText(new String(Execution.CCR));

			}
		});
		RESET.setBounds(478, 501, 105, 21);
		getContentPane().add(RESET);
		
		JButton explore =new JButton("OUVRIR");
		explore.setFont(new Font("Tahoma", Font.BOLD, 11));
		explore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame Frame = new JFrame();
				Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Frame.setSize(400, 300);
				Frame.getContentPane().setLayout(new BorderLayout());

				JFileChooser fileChooser = new JFileChooser();
				int option = fileChooser.showSaveDialog(Frame);
				if (option == JFileChooser.APPROVE_OPTION) {
					String filePath = fileChooser.getSelectedFile().getAbsolutePath();
					String contenu=readFromFile(filePath);
					code_espace.setText(contenu);
					
				}
			}
		});
		explore.setBounds(10, 501, 98, 21);
		getContentPane().add(explore);
		
		JButton eface = new JButton("efacer");
		eface.setFont(new Font("Tahoma", Font.BOLD, 11));
		eface.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				code_espace.setText("");
			}
		});
		eface.setBounds(799, 501, 100, 21);
		getContentPane().add(eface);
		
		JPanel panel = new JPanel() {
//			protected void paintComponent(Graphics g) {
//			super.paintComponent(g);
//			ImageIcon bgImage = new ImageIcon(getClass().getResource("/image/"));
//			g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(),this);
//		}
		};
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(0, 0, 890, 546);
		getContentPane().add(panel);
		
		
		}
	

	public static void saveToFile(String filePath, String content) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(content);
			JOptionPane.showMessageDialog(null, "Le fichier a été sauvegardé avec succès.", "Succès",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur lors de l’écriture dans le fichier : " + e.getMessage(),
					"Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void ERROR(String error_nome) {
		valide=false;
		JOptionPane.showMessageDialog(null, error_nome, "Succès",
				JOptionPane.INFORMATION_MESSAGE);
		return;
	}
	public static String readFromFile(String filePath) {
		try (BufferedReader Read = new BufferedReader(new FileReader(filePath))) {
			String line,contenu="";
			while((line= Read.readLine())!=null) {
			contenu=contenu+line;
			contenu+="\n";}
			return contenu;
		} catch (IOException e) {

			JOptionPane.showMessageDialog(null, "Erreur lors de l’écriture dans le fichier : " + e.getMessage(),
					"Erreur", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				AOProject_Partie_2 frame = new AOProject_Partie_2();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
