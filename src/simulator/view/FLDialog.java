package simulator.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.apache.commons.cli.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;

import simulator.control.Controller;

public class FLDialog extends JDialog{
	
	private static final long serialVersionUID = 1L;
	
	private int _status;
	private Controller _ctrl;
	private String[] descs;
	private lawsTableModel _dataTableModel;
	private List<JSONObject> forcelaws;
	private JComboBox<String> ComBox;
	
	
	public FLDialog(Frame parent, Controller _ctrl) {
		super(parent, true);
		this._ctrl = _ctrl;
		initForceLaws();
		initGUI();
	}
	
	private class lawsTableModel extends AbstractTableModel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String [] columnNames = {"Key","Value","Description"};
		Object[][] _data;
		
		lawsTableModel() {
			changeLaw(forcelaws.get(0).getJSONObject("data"));
		}
		
		public void changeLaw(JSONObject lawData) {
			int size = lawData.length();
			Iterator<String> keys = lawData.keys();
    		int j = 0;
    		
    		_data = new Object[size][3];
    		
    		while(keys.hasNext()) {
    		    String key = keys.next();
    		    _data[j][0] = key;
    		    _data[j][2] = lawData.get(key);
            	j++;
    		}

    		update();
		}
		
		public void update() {
			fireTableStructureChanged();
		}
		@Override
		public boolean isCellEditable(int row, int column) {
			if(column == 1) {
				return true;
			}
			return false;
		}
		
		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}

		@Override
		public int getRowCount() {
			return _data == null ? 0 : _data.length;
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return _data[rowIndex][columnIndex];
		}

		@Override
		public void setValueAt(Object o, int rowIndex, int columnIndex) {
			_data[rowIndex][columnIndex] = o.toString();
		}
		
		public JSONObject getData() {
			JSONObject data = new JSONObject();
			
			for(int i = 0; i < _data.length; i++) {
				Object value = getValueAt(i,1);
				if(value != null && value != "") {
					String key = getValueAt(i,0).toString();
					if(key.equals("c")) {
						JSONArray vector = new JSONArray(value.toString());
						data.put(key, vector);
						
					}else {
						data.put(key, value);
					}
				}
					
			}
			
			return data;
		}
		
	}
	
	private void initGUI() {
		setTitle("Force Laws Selection");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);	
		this.setSize(700,600);
		
		JLabel info = new JLabel();
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setAlignmentX(CENTER_ALIGNMENT);
		info.setText("<html><p style=\"width:500px\">"+"Select a force law and provide values for the parameters in the value column"
				+ " (default values are used for the parameters with no value) "+"</p></html>");
		getContentPane().add(info);
		
		addTable(mainPanel);
		
		JPanel bottomPanel = new JPanel();
		JPanel comboPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		comboPanel.setLayout(new FlowLayout());
		buttonsPanel.setLayout(new FlowLayout());
		
		addComboBox(comboPanel);
		
		addButtons(buttonsPanel);
		
		bottomPanel.add(comboPanel,BorderLayout.PAGE_START);
		bottomPanel.add(buttonsPanel,BorderLayout.PAGE_END);
		mainPanel.add(bottomPanel);
		
		
		
		setPreferredSize(new Dimension(700, 300));
		pack();
		setResizable(false);
		setVisible(false);
	}
	
	private void addTable(JPanel mainPanel){
		_dataTableModel = new lawsTableModel();
		JTable dataTable = new JTable(_dataTableModel);
		
		dataTable.setAlignmentX(CENTER_ALIGNMENT);
		JScrollPane tabelScroll = new JScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(tabelScroll);
	}
	
	private void addComboBox(JPanel comboPanel) {
		JLabel forcl = new JLabel("Force Law: ");
		comboPanel.add(forcl);	
		
		
		ComBox = new JComboBox(descs);
		ComBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				JComboBox cb = (JComboBox)ev.getSource();
		        String law = (String)cb.getSelectedItem();
		        for(int i = 0; i < forcelaws.size(); i++) {
		        	if(forcelaws.get(i).get("desc").equals(law)) {
		        		JSONObject lawData = forcelaws.get(i).getJSONObject("data");
		        		_dataTableModel.changeLaw(lawData);
		        	}
		        }
			}
			
		});
		comboPanel.add(ComBox);
	}
	
	private void addButtons(JPanel buttonsPanel) {
		JButton cancelButton = new JButton("Cancel");
		JButton okButton = new JButton("OK");
		
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				_status = 0;
				FLDialog.this.setVisible(false);
			}
			
		});
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				JSONObject flawJson = new JSONObject(); 
				JSONObject dataJson = _dataTableModel.getData();
				
				flawJson.put("data", dataJson);
				
				String type = null;
				for(int i = 0; i < forcelaws.size(); i++) {
					if(forcelaws.get(i).get("desc").equals((String)ComBox.getSelectedItem())) {
						type = forcelaws.get(i).getString("type");
					}
				}
				flawJson.put("type", type);
				try {
					_ctrl.setForceLaws(flawJson);
					_status = 1;
					FLDialog.this.setVisible(false);
				}catch(Exception e){
					System.out.println(e);
				}
				
			}
			
		});
		
		buttonsPanel.add(cancelButton);
		buttonsPanel.add(okButton);
	}
	
	public int open() {
		setLocation(getParent().getLocation().x + 10, getParent().getLocation().y + 10);

		setVisible(true);
		return _status;
	}
	
	private void initForceLaws() {
		forcelaws = _ctrl.getForceLawsInfo();
		descs = new String[forcelaws.size()];
		
		for(int i = 0; i < forcelaws.size(); i++) {
			descs[i] = forcelaws.get(i).getString("desc");
		}
		
	}
}
