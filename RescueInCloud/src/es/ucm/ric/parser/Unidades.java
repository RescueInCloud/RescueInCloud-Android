/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ucm.ric.parser;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


//public enum Unidades {mg,dg,kg,kilos,gramos,litros,l,dl,cl,ml,A('mmol/l')};

public enum Unidades {
	  // Idiomatic Java names. You could ignore those if you really want,
	  // and overload the constructor to have a parameterless one which calls
	  // name() if you really want.
	  MG("mg"),DC("dg"),KG("kg"),KILOS("kilos"),GRAMOS("gramos"),LITROS("litros"),L("l"),DL("dl"),CL("cl"),ML("ml"),
	  MMOL1("mmol/l");

	  private static final Map<String, Unidades> nameToValueMap;

	  static {
	    nameToValueMap = new HashMap<String, Unidades>();
	    for (Unidades unidades : EnumSet.allOf(Unidades.class)) {
	      nameToValueMap.put(unidades.friendlyName, unidades);
	    }
	  }

	  private final String friendlyName;

	  private Unidades(String friendlyName) {
	    this.friendlyName = friendlyName;
	  }

	  public String getFriendlyName() {
	    return friendlyName;
	  }

	  public static Unidades fromFriendlyName(String friendlyName) {
	    return nameToValueMap.get(friendlyName);
	  }
	}