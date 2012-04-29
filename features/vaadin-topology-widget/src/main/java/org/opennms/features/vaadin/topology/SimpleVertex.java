/**
 * 
 */
package org.opennms.features.vaadin.topology;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name="vertex")
abstract public class SimpleVertex {
	String m_id;
	int m_x;
	int m_y;
	boolean m_selected;
	String m_icon;
	SimpleGroup m_parent = null;
	List<SimpleEdge> m_edges = new ArrayList<SimpleEdge>();
	private int m_semanticZoomLevel = -1;
	
	public SimpleVertex() {}
	
	public SimpleVertex(String id) {
		m_id = id;
	}

	public SimpleVertex(String id, int x, int y) {
		m_id = id;
		m_x = x;
		m_y = y;
	}
	
	public SimpleGroup getParent() {
		return m_parent;
	}
	
	public void setParent(SimpleGroup parent) {
		if (m_parent != null) {
			m_parent.removeMember(this);
		}
		m_parent = parent;
		if (m_parent != null) {
			m_parent.addMember(this);
		}
	}
	
	public abstract boolean isLeaf();
	
	public boolean isRoot() {
		return m_parent == null;
	}

	@XmlID
	public String getId() {
		return m_id;
	}

	public void setId(String id) {
		m_id = id;
	}

	public int getX() {
		return m_x;
	}

	public void setX(int x) {
		m_x = x;
	}

	public int getY() {
		return m_y;
	}

	public void setY(int y) {
		m_y = y;
	}

	public boolean isSelected() {
		return m_selected;
	}

	public void setSelected(boolean selected) {
		m_selected = selected;
	}

	public String getIcon() {
		return m_icon;
	}

	public void setIcon(String icon) {
		m_icon = icon;
	}

	@XmlTransient
	List<SimpleEdge> getEdges() {
		return m_edges;
	}
	
	void addEdge(SimpleEdge edge) {
		m_edges.add(edge);
	}
	
	void removeEdge(SimpleEdge edge) {
		m_edges.remove(edge);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_id == null) ? 0 : m_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleVertex other = (SimpleVertex) obj;
		if (m_id == null) {
			if (other.m_id != null)
				return false;
		} else if (!m_id.equals(other.m_id))
			return false;
		return true;
	}
	
	public int getSemanticZoomLevel() {
		return m_semanticZoomLevel >= 0
				? m_semanticZoomLevel
				: m_parent == null 
				? 0 
				: m_parent.getSemanticZoomLevel() + 1;
	}
	
	
}