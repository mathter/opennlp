///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2000 Jason Baldridge
// 
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
//////////////////////////////////////////////////////////////////////////////

package opennlp.common.hylo;

import opennlp.common.synsem.*;
import opennlp.common.unify.*;

public class HyloVar extends HyloFormula implements Variable {
    
    private final String _name;
    private final int _index;

    public HyloVar (String name) {
	this(name, 0);
    }

    protected HyloVar (String name, int index) {
	_name = name;
	_index = index;
    }
    
    public String name () {
	return _name;
    }
    
    public Variable uniqueCopy (int index) {
	return new HyloVar(_name, index);
    }

    public boolean occurs (Variable var) {
	return equals(var);
    }

    public boolean equals (Object o) {
	if (o instanceof HyloVar
	    && _name == ((HyloVar)o)._name
	    && _index == ((HyloVar)o)._index) {
	    return true;
	} else {
	    return false;
	}
    }
    
    public Object unify (Object o, Substitution sub) throws UnifyFailure {
	if (o instanceof LF) {
	    if (((LF)o).occurs(this)) {
		throw new UnifyFailure();
	    }
	    sub.makeSubstitution(this, o);
	    return o;
	}
	else {
	    throw new UnifyFailure();
	}
    }

    public String toString () {	
	return _name+_index;
    }

}