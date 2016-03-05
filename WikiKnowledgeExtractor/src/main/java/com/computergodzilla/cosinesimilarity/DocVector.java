package com.computergodzilla.cosinesimilarity;

import java.util.Map;

import org.apache.commons.math.linear.OpenMapRealVector;
import org.apache.commons.math.linear.RealVectorFormat;

/**
 *
 * @author Mubin
 */
public class DocVector {

	public Map terms;
	public OpenMapRealVector vector;

	public DocVector(Map terms) {
		this.terms = terms;
		this.vector = new OpenMapRealVector(terms.size());
	}

	public void setEntry(String term, int freq) {
		if (terms.containsKey(term)) {
			int pos = (int) terms.get(term);
			vector.setEntry(pos, freq);
		}
	}

	public void normalize() {
		double sum = vector.getL1Norm();
		vector = (OpenMapRealVector) vector.mapDivide(sum);
	}

	@Override
	public String toString() {
		RealVectorFormat formatter = new RealVectorFormat();
		return formatter.format(vector);
	}
}