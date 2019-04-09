package it.polito.tdp.main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.model.Model;
import it.polito.tdp.model.Esame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class VotiNobelController {

	Model model;

	@FXML
	private Text txtTempo;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtInput;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnReset;

	@FXML
	void doCalcolaCombinazione(ActionEvent event) {
		long tiniziale = System.nanoTime();
		try {
			int numeroCrediti = Integer.parseInt(txtInput.getText());
			List<Esame> voti = model.calcolaSottoinsiemeEsami(numeroCrediti);
			for (Esame e : voti) {
				txtResult.appendText(e.getNomeCorso() + ", " + e.getCrediti() + ", " + e.getVoto() + "\n");
			}

		} catch (NumberFormatException e) {
			txtResult.setText("Inserire un numero di crediti > 0");
		}
		this.txtTempo.setText(""+(System.nanoTime()-tiniziale)/100000);
	}

	@FXML
	void doReset(ActionEvent event) {
		// reset the UI
		txtInput.clear();
		txtResult.clear();
	}

	@FXML
	void initialize() {
		assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'VotiNobel.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'VotiNobel.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'VotiNobel.fxml'.";
	}

	public void setModel(Model model) {

		this.model = model;
	}
}
