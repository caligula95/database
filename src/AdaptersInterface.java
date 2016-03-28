import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AdaptersInterface {
	AdaptersData adaptersData = new AdaptersData();

	private JFrame frame;
	private JTextField textId;
	private JTextField textName;
	private JTextField textDescription;
	private JTextField textCount;
	private JTextField textSold;
	JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdaptersInterface window = new AdaptersInterface();
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
	public AdaptersInterface() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 514, 336);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(24, 29, 46, 14);
		panel.add(lblId);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(24, 75, 46, 14);
		panel.add(lblName);
		
		JLabel lblDescription = new JLabel("description");
		lblDescription.setBounds(24, 124, 79, 14);
		panel.add(lblDescription);
		
		JLabel lblCount = new JLabel("count");
		lblCount.setBounds(24, 169, 46, 14);
		panel.add(lblCount);
		
		textId = new JTextField();
		textId.setBounds(96, 26, 137, 20);
		panel.add(textId);
		textId.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(96, 72, 137, 20);
		panel.add(textName);
		textName.setColumns(10);
		
		textDescription = new JTextField();
		textDescription.setBounds(96, 121, 137, 20);
		panel.add(textDescription);
		textDescription.setColumns(10);
		
		textCount = new JTextField();
		textCount.setBounds(96, 166, 137, 20);
		panel.add(textCount);
		textCount.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addAdapter();
			}
		});
		btnAdd.setBounds(24, 226, 89, 23);
		panel.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remove();
			}
		});
		btnRemove.setBounds(123, 226, 89, 23);
		panel.add(btnRemove);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeAdapters();
			}
		});
		btnChange.setBounds(229, 226, 89, 23);
		panel.add(btnChange);
		
		JLabel lblSold = new JLabel("sold");
		lblSold.setBounds(24, 201, 46, 14);
		panel.add(lblSold);
		
		textSold = new JTextField();
		textSold.setBounds(96, 197, 137, 20);
		panel.add(textSold);
		textSold.setColumns(10);
		
		 comboBox = new JComboBox();
		comboBox.setBounds(319, 72, 134, 20);
		panel.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showAdapters();
			}
		});
		
		JLabel label = new JLabel("\u0412\u044B\u0431\u043E\u0440 \u0442\u043E\u0432\u0430\u0440\u0430 \u043F\u043E \u0438\u043C\u0435\u043D\u0438");
		label.setBounds(312, 45, 141, 14);
		panel.add(label);
	}
	public void addAdapter() {
		Adapters adapters = new Adapters(Integer.parseInt(textId.getText()),
				textName.getText(), 
				textDescription.getText(),
				Integer.parseInt(textCount.getText()));
		try {
			adaptersData.addAdapter(adapters);
			refreshNameList();
			comboBox.setSelectedItem(String.valueOf(textName.getText()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void showAdapters() {
		try {
			String productName = (String)comboBox.getSelectedItem();
			if (productName!=null) {
			List<Adapters> adapters = adaptersData.getAdaptersByName(productName);
			for (int i = 0; i < adapters.size(); i++) {
			textId.setText(adapters.get(i).getIdAdapter()+""); 
			textName.setText(adapters.get(i).getTitle());
			textDescription.setText(adapters.get(i).getDescription());
			textCount.setText(adapters.get(i).getCount()+"");
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void changeAdapters() {
		try {
			Adapters adapterChanged = null;
			 adapterChanged = new  Adapters(Integer.parseInt(textId.getText()),
				textName.getText(), 
				textDescription.getText(),
				Integer.parseInt(textCount.getText())-Integer.parseInt(textSold.getText()));
			 adaptersData.changeAdapter(adapterChanged);
			 textCount.setText(Integer.parseInt(textCount.getText())-Integer.parseInt(textSold.getText())+"");
			 textSold.setText("");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void refreshNameList() {
		try {
			List<String> nameList = adaptersData.getAdaptersName();
			comboBox.removeAllItems();
			for (String nameL : nameList) {
				comboBox.addItem(nameL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void remove() {
		try {
			adaptersData.removeProduct(textName.getName());
			refreshNameList();
			showAdapters();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
