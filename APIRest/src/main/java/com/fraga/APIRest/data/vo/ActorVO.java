package com.fraga.APIRest.data.vo;

import java.io.Serializable;
import java.util.Objects;

public class ActorVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;	
	
	public ActorVO() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActorVO other = (ActorVO) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	@Override
	public String toString() {
		return "ActorVO [id=" + id + ", name=" + name + "]";
	}
	
}
