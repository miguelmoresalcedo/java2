import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sudoku extends Principal {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JButton btnNewButton;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JButton btnNewButton_1;
	private JButton btnReset;
	private JPanel panel_3;
	private JPanel panel_4;
	static int N = 4;

	public static int getN() {
		return N;
	}

	public static void setN(int n) {
		N = n;
	}

	// sample input
	public static int grid[][] = {  { 2, 0, 0, 0}, //
									{ 0, 3, 0, 4}, //
									{ 3, 0, 0, 1}, //
									{ 4, 0, 3, 0} };
	
	// matriz creada con la solucion del sudoku para compararlo en la version manual
	public static int grid_solve[][] = {  { 2, 4, 1, 3}, //
										  { 1, 3, 2, 4}, //
			                              { 3, 2, 4, 1}, //
			                              { 4, 1, 3, 2} };


	public static int[][] getGrid() {
		return grid;
	}

	public static void setGrid(int[][] grid) {
		Sudoku.grid = grid;
	}
	static boolean isValid(Cell cell, int value) {

		if (grid[cell.row][cell.col] != 0) {
			throw new RuntimeException("Cannot call for cell which already has a value");
		}

		// if v present row, return false
		for (int c = 0; c < 4; c++) {
			if (grid[cell.row][c] == value)
				return false;
		}

		// if v present in col, return false
		for (int r = 0; r < 4; r++) {
			if (grid[r][cell.col] == value)
				return false;
		}

		// if v present in grid, return false

		// to get the grid we should calculate (x1,y1) (x2,y2)
		int x1 = 2 * (cell.row / 2);
		int y1 = 2 * (cell.col / 2);
		int x2 = x1 + 1;
		int y2 = y1 + 1;

		for (int x = x1; x <= x2; x++)
			for (int y = y1; y <= y2; y++)
				if (grid[x][y] == value)
					return false;

		// if value not present in row, col and bounding box, return true
		return true;
	}

	// simple function to get the next cell
	// read for yourself, very simple and straight forward
	static Cell getNextCell(Cell cur) {

		int row = cur.row;
		int col = cur.col;

		// next cell => col++
		col++;

		// if col > 8, then col = 0, row++
		// reached end of row, got to next row
		if (col > 3) {
			// goto next line
			col = 0;
			row++;
		}

		// reached end of matrix, return null
		if (row > 3)
			return null; // reached end

		Cell next = new Cell(row, col);
		return next;
	}

	// everything is put together here
	// very simple solution
	// must return true, if the soduku is solved, return false otherwise
	public boolean solve(Cell cur) {

		// if the cell is null, we have reached the end
		if (cur == null)
			return true;

		// if grid[cur] already has a value, there is nothing to solve here,
		// continue on to next cell
		if (grid[cur.row][cur.col] != 0) {
			// return whatever is being returned by solve(next)
			// i.e the state of soduku's solution is not being determined by
			// this cell, but by other cells
			return solve(getNextCell(cur));
		}

		// this is where each possible value is being assigned to the cell, and
		// checked if a solutions could be arrived at.

		// if grid[cur] doesn't have a value
		// try each possible value
		for (int i = 1; i <= 4; i++) {
			// check if valid, if valid, then update
			boolean valid = isValid(cur, i);

			if (!valid) // i not valid for this cell, try other values
				continue;

			// assign here
			grid[cur.row][cur.col] = i;

			// continue with next cell
			boolean solved = solve(getNextCell(cur));
			// if solved, return, else try other values
			if (solved)
				return true;
			else
				grid[cur.row][cur.col] = 0; // reset
			// continue with other possible values
		}

		// if you reach here, then no value from 1 - 9 for this cell can solve
		// return false
		return false;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sudoku window = new Sudoku();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the application.
	 */
	public Sudoku() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 515, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 4, 0, 0));
		
		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setBackground(SystemColor.activeCaption);
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("2");
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBackground(SystemColor.activeCaption);
		textField_5.setEnabled(false);
		textField_5.setEditable(false);
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setText("3");
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBackground(SystemColor.activeCaption);
		textField_7.setText("4");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setEnabled(false);
		textField_7.setEditable(false);
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBackground(SystemColor.activeCaption);
		textField_9.setEnabled(false);
		textField_9.setEditable(false);
		textField_9.setText("3");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField_10);
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField_11);
		textField_11.setColumns(10);
		
		textField_12 = new JTextField();
		textField_12.setBackground(SystemColor.activeCaption);
		textField_12.setEnabled(false);
		textField_12.setEditable(false);
		textField_12.setText("1");
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField_12);
		textField_12.setColumns(10);
		
		textField_13 = new JTextField();
		textField_13.setBackground(SystemColor.activeCaption);
		textField_13.setText("4");
		textField_13.setHorizontalAlignment(SwingConstants.CENTER);
		textField_13.setEditable(false);
		textField_13.setEnabled(false);
		panel.add(textField_13);
		textField_13.setColumns(10);
		
		textField_14 = new JTextField();
		textField_14.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField_14);
		textField_14.setColumns(10);
		
		textField_15 = new JTextField();
		textField_15.setBackground(SystemColor.activeCaption);
		textField_15.setEnabled(false);
		textField_15.setEditable(false);
		textField_15.setText("3\n");
		textField_15.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField_15);
		textField_15.setColumns(10);
		
		textField_16 = new JTextField();
		textField_16.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField_16);
		textField_16.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		btnNewButton = new JButton("SOLUCIÓN\nAUTOMÁTICA");
		btnNewButton.setBackground(Color.GRAY);
		panel_1.add(btnNewButton);
		
		btnNewButton_1 = new JButton("COMPROBAR");
		panel_1.add(btnNewButton_1);
		
		btnReset = new JButton("RESET");
		panel_1.add(btnReset);
		
		panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Redimensionar");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(6, 6, 141, 23);
		panel_3.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("No redimensionar");
		rdbtnNewRadioButton_1.setBounds(6, 26, 141, 23);
		panel_3.add(rdbtnNewRadioButton_1);
		
		panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Mostrar o no sombreado");
		chckbxNewCheckBox.setBounds(6, 0, 188, 23);
		panel_4.add(chckbxNewCheckBox);
		
		JButton btnNewButton_2 = new JButton("SALIR");
		btnNewButton_2.setBounds(0, 26, 117, 29);
		panel_4.add(btnNewButton_2);
		
		//EVENTOS

		
		//REDIMENSION
		
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNewRadioButton_1.setSelected(false);
				frame.setResizable(true);
			}
		});
		
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNewRadioButton.setSelected(false);
				frame.setResizable(false);
			}
		});
		
		//BOTON SALIR
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// SOMBREADO
		
		chckbxNewCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(chckbxNewCheckBox.isSelected()){
				textField.setBackground(SystemColor.windowBorder);
				textField_5.setBackground(SystemColor.windowBorder);
				textField_7.setBackground(SystemColor.windowBorder);
				textField_9.setBackground(SystemColor.windowBorder);
				textField_12.setBackground(SystemColor.windowBorder);
				textField_13.setBackground(SystemColor.windowBorder);
				textField_15.setBackground(SystemColor.windowBorder);
				
				}
				else{
					textField.setBackground(SystemColor.WHITE);
					textField_5.setBackground(SystemColor.WHITE);
					textField_7.setBackground(SystemColor.WHITE);
					textField_9.setBackground(SystemColor.WHITE);
					textField_12.setBackground(SystemColor.WHITE);
					textField_13.setBackground(SystemColor.WHITE);
					textField_15.setBackground(SystemColor.WHITE);
				}
				}
		});
		
		
		//RESET
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_6.setText("");
				textField_10.setText("");
				textField_11.setText("");
				textField_14.setText("");
				textField_16.setText("");
				
				
			}
		});
		//auto
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dentro de la matriz recorra los elementos y lo resuelva automaticamente con el algoritmo
				Sudoku prueba = new Sudoku();
				
				boolean solved = prueba.solve(new Cell(0, 0));
				if (!solved) {
					System.out.println("SUDOKU cannot be solved.");
					return;
				}
				
				
				/*creo un entero llamado text y le paso la posicion que quiera de la matriz, y luego elijo el textfield que yo 
				quiera y paso el valor que esta en String a entero, mediante valueof y le digo que variable quiero en ese texfield
				*/
				printGrid(prueba.getGrid());
				 int text1 = grid[0][1] ;
				 int text2 = grid[0][2] ;
				 int text3 = grid[0][3] ;
				 int text4 = grid[1][0] ;
				 int text5 = grid[1][2] ;
				 int text6 = grid[2][1] ;
				 int text7 = grid[2][2] ;
				 int text8 = grid[3][1] ;
				 int text9 = grid[3][3] ;
				textField_1.setText(String.valueOf(text1));
				textField_2.setText(String.valueOf(text2));
				textField_3.setText(String.valueOf(text3));
				textField_4.setText(String.valueOf(text4));
				textField_6.setText(String.valueOf(text5));
				textField_10.setText(String.valueOf(text6));
				textField_11.setText(String.valueOf(text7));
				textField_14.setText(String.valueOf(text8));
				textField_16.setText(String.valueOf(text9));
			}
				
			
		});
		
		//manual
		/* Para la version manual el procedimiento es igual que para el auto con la diferencia que en vez de pintar en el textfield lo 
		 * que hace es coger el valor que haya en el textfield(el que haya metido el usuario) y lo compara con otra matriz 
		 * creada con la solucion, entonces si cada texfield es igual al valor que le corresponde salta un aviso de que es correcto 
		 * sino salta un mensaje diciendo que no lo es*/
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int text1 = grid_solve[0][1] ;
				 int text2 = grid_solve[0][2] ;
				 int text3 = grid_solve[0][3] ;
				 int text4 = grid_solve[1][0] ;
				 int text5 = grid_solve[1][2] ;
				 int text6 = grid_solve[2][1] ;
				 int text7 = grid_solve[2][2] ;
				 int text8 = grid_solve[3][1] ;
				 int text9 = grid_solve[3][3] ;
				if(textField_1.getText().equals(String.valueOf(text1)) &&
						textField_2.getText().equals(String.valueOf(text2))&&
								textField_3.getText().equals(String.valueOf(text3))&&
										textField_4.getText().equals(String.valueOf(text4))&&
												textField_6.getText().equals(String.valueOf(text5))&&
														textField_10.getText().equals(String.valueOf(text6))&&
																textField_11.getText().equals(String.valueOf(text7))&&
																		textField_14.getText().equals(String.valueOf(text8))&&
																				textField_16.getText().equals(String.valueOf(text9))){
					JOptionPane.showMessageDialog(null, "CORRECTO","Nice", JOptionPane.PLAIN_MESSAGE);
				}
						
				else{
					
					JOptionPane.showMessageDialog(null, "Nº incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
