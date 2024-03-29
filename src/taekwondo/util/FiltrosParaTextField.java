package taekwondo.util;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class FiltrosParaTextField {
	
	//only letter filter
	public static void setupTextFieldDocumentFilter(JTextField textField) {
        Document doc = textField.getDocument();
        if (doc instanceof AbstractDocument) {
            AbstractDocument abstractDoc = (AbstractDocument) doc;
            abstractDoc.setDocumentFilter(new DocumentFilter() {
                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    if (text.matches("^[a-zA-Z]*$")) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }
            });
        }
    }
	
	//only number filter
	public static void setupTextFieldDocumentFilterForNumbers(JTextField textField) {
        Document doc = textField.getDocument();
        if (doc instanceof AbstractDocument) {
            AbstractDocument abstractDoc = (AbstractDocument) doc;
            abstractDoc.setDocumentFilter(new DocumentFilter() {
                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    if (text.matches("^[0-9]*$")) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }
            });
        }
    }
	//email filter
	public static void setupTextFieldDocumentFilterForEmail(JTextField textField) {
        Document doc = textField.getDocument();
        if (doc instanceof AbstractDocument) {
            AbstractDocument abstractDoc = (AbstractDocument) doc;
            abstractDoc.setDocumentFilter(new DocumentFilter() {
                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                        throws BadLocationException {
                    // Permite los caracteres válidos para un correo electrónico
                    if (isValidEmailCharacter(text)) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }
            });
        }
    }
	
	//email validation
	private static boolean isValidEmailCharacter(String text) {
        // Utiliza una expresión regular para permitir caracteres válidos en un correo electrónico
        return text.matches("^[a-zA-Z0-9._@]*$");
    }

	//tournement name filter
	public static void setupTextFieldDocumentFilterTorneoNombre(JTextField tfNombre) {
		Document doc = tfNombre.getDocument();
        if (doc instanceof AbstractDocument) {
            AbstractDocument abstractDoc = (AbstractDocument) doc;
            abstractDoc.setDocumentFilter(new DocumentFilter() {
                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    if (text.matches("^[a-zA-Z0-9 ]*$")) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }
            });
        }
	}

}
