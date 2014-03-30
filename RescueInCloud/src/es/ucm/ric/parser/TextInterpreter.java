/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ucm.ric.parser;

import java.util.ArrayList;

/**
 *
 * @author Mila
 */
public abstract class TextInterpreter {
    
	public abstract String encontrarRelacionCantidad(String cadena);
	
    public abstract ArrayList<String> textInterpreterparseText(String cadena);
	
    public abstract String toString();

}
