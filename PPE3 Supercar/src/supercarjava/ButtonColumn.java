import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * La classe ButtonColumn fournit un moteur de rendu et un éditeur qui ressemble à un
 * JButton. Le moteur de rendu et l'éditeur seront ensuite utilisés pour une colonne spécifiée dans
 * la table. Le TableModel contiendra la chaîne à afficher sur le bouton.
 *
 * Le bouton peut être appelé par un clic de souris ou en appuyant sur la barre d'espace lorsque
 * la cellule a le focus. En option, un mnémonique peut être défini pour appeler le bouton.
 * Lorsque le bouton est appelé, l'action fournie est appelée. La source de la
 * L'action sera la table. La commande d'action contiendra la ligne de modèle
 * numéro du bouton sur lequel vous avez cliqué.
 *
 */
public class ButtonColumn extends AbstractCellEditor
		implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener {
	private JTable table;
	private Action action;
	private int mnemonic;
	private Border originalBorder;
	private Border focusBorder;

	private JButton renderButton;
	private JButton editButton;
	private Object editorValue;
	private boolean isButtonColumnEditor;

	/**
	 * Créez le ButtonColumn à utiliser comme moteur de rendu et éditeur. Le moteur de rendu et
	 * l'éditeur sera automatiquement installé sur la TableColumn du spécifié
	 * colonne.
	 *
	 * @param table la table contenant le rendu / éditeur de bouton
	 * @param action l'Action à invoquer lorsque le bouton est appelé
	 * @param colonne la colonne à laquelle le rendu / éditeur de bouton est ajouté
	 */
	public ButtonColumn(JTable table, Action action, int column) {
		this.table = table;
		this.action = action;

		renderButton = new JButton();
		editButton = new JButton();
		editButton.setFocusPainted(false);
		editButton.addActionListener(this);
		originalBorder = editButton.getBorder();
		setFocusBorder(new LineBorder(Color.BLUE));

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer(this);
		columnModel.getColumn(column).setCellEditor(this);
		table.addMouseListener(this);
	}

	/**
	 * Obtenir la couleur de premier plan du bouton lorsque la cellule a le focus
	 *
	 * @return la couleur de premier plan
	 */
	public Border getFocusBorder() {
		return focusBorder;
	}

	/**
	 * La couleur de premier plan du bouton lorsque la cellule a le focus
	 *
	 * @param focusBorder la couleur de premier plan
	 */
	public void setFocusBorder(Border focusBorder) {
		this.focusBorder = focusBorder;
		editButton.setBorder(focusBorder);
	}

	public int getMnemonic() {
		return mnemonic;
	}

	/**
	 * le mnémonique pour activer le bouton lorsque la cellule a le focus
	 *
	 * @param mnémonique le mnémonique
	 */
	public void setMnemonic(int mnemonic) {
		this.mnemonic = mnemonic;
		renderButton.setMnemonic(mnemonic);
		editButton.setMnemonic(mnemonic);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (value == null) {
			editButton.setText("");
			editButton.setIcon(null);
		} else if (value instanceof Icon) {
			editButton.setText("");
			editButton.setIcon((Icon) value);
		} else {
			editButton.setText(value.toString());
			editButton.setIcon(null);
		}

		this.editorValue = value;
		return editButton;
	}

	@Override
	public Object getCellEditorValue() {
		return editorValue;
	}

//
//  Implement TableCellRenderer interface
//
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected) {
			renderButton.setForeground(table.getSelectionForeground());
			renderButton.setBackground(table.getSelectionBackground());
		} else {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		}

		if (hasFocus) {
			renderButton.setBorder(focusBorder);
		} else {
			renderButton.setBorder(originalBorder);
		}

//		renderButton.setText( (value == null) ? "" : value.toString() );
		if (value == null) {
			renderButton.setText("");
			renderButton.setIcon(null);
		} else if (value instanceof Icon) {
			renderButton.setText("");
			renderButton.setIcon((Icon) value);
		} else {
			renderButton.setText(value.toString());
			renderButton.setIcon(null);
		}

		return renderButton;
	}

//
//  Implement ActionListener interface
//
	/*
	 * Le bouton a été enfoncé. Arrêtez l'édition et appelez l'action personnalisée
	 */
	public void actionPerformed(ActionEvent e) {
		int row = table.convertRowIndexToModel(table.getEditingRow());
		fireEditingStopped();

		// effectue l'action 

		ActionEvent event = new ActionEvent(table, ActionEvent.ACTION_PERFORMED, "" + row);
		action.actionPerformed(event);
	}

//
//  Implement MouseListener interface
//
	/*
	 * Lorsque la souris est enfoncée, l'éditeur est appelé. Si vous faites ensuite glisser la
	 * souris vers une autre cellule avant de la relâcher, l'éditeur est toujours actif. être
	 * sûr que l'édition est arrêtée lorsque la souris est relâchée.
	 */
	public void mousePressed(MouseEvent e) {
		if (table.isEditing() && table.getCellEditor() == this)
			isButtonColumnEditor = true;
	}

	public void mouseReleased(MouseEvent e) {
		if (isButtonColumnEditor && table.isEditing())
			table.getCellEditor().stopCellEditing();

		isButtonColumnEditor = false;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}