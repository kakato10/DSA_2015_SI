package com.java.sda.homework.bookkeeper;

import java.util.HashSet;
import java.util.LinkedList;

public class Unit {

	private String name;
	private LinkedList<Integer> soldiers;
	private Unit nextConnectionToUnit;
	private LinkedList<Unit> prevConToUnits;

	private void detachFrom(Unit unit) {
		unit.getSoldiers().removeAll(this.soldiers);
		this.setNextConnectionToUnit(null);
		unit.getPrevConToUnits().remove(this);
	}

	private boolean checkId(int indexOfId, Unit unit) {
		if (indexOfId == unit.getSoldiers().size() - 1) {
			return true;
		}
		int before = unit.getSoldiers().get(indexOfId - 1);
		int after = unit.getSoldiers().get(indexOfId + 1);
		for (Unit iUnit : unit.getPrevConToUnits()) {
			if (iUnit.getSoldiers().contains(before)
					&& iUnit.getSoldiers().contains(after)) {
				return false;
			}
		}
		return true;
	}

	public Unit() {
		this(null, new LinkedList<Integer>(), null, new LinkedList<Unit>());
	}

	public Unit(String name) {
		this(name, new LinkedList<Integer>(), null, new LinkedList<Unit>());
	}

	public Unit(String name, LinkedList<Integer> soldiers) {
		this(name, soldiers, null, new LinkedList<Unit>());
	}

	public Unit(String name, LinkedList<Integer> soldiers,
			Unit nextConnectionToUnit) {
		this(name, soldiers, nextConnectionToUnit, new LinkedList<Unit>());
	}

	public Unit(String name, LinkedList<Integer> soldiers,
			Unit nextConnectionToUnit, LinkedList<Unit> prevConsToUnits) {
		this.name = name;
		this.soldiers = soldiers;
		this.nextConnectionToUnit = nextConnectionToUnit;
		this.prevConToUnits = prevConsToUnits;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Integer> getSoldiers() {
		return soldiers;
	}

	public void setSoldiers(LinkedList<Integer> soldiers) {
		this.soldiers = soldiers;
	}

	public Unit getNextConnectionToUnit() {
		return nextConnectionToUnit;
	}

	public void setNextConnectionToUnit(Unit nextConnectionToUnit) {
		this.nextConnectionToUnit = nextConnectionToUnit;
	}

	public LinkedList<Unit> getPrevConToUnits() {
		return prevConToUnits;
	}

	public void setPrevConToUnits(LinkedList<Unit> prevConToUnits) {
		this.prevConToUnits = prevConToUnits;
	}

	public boolean isEmptyUnit() {
		if (this.getSoldiers().isEmpty()) {
			return true;
		}
		return false;
	}

	public void attachedTo(Unit unit) {
		if (this.getNextConnectionToUnit() != null) {
			this.detachFrom(this.getNextConnectionToUnit());
		}
		if (unit.getSoldiers().size() == 0
				|| !unit.getPrevConToUnits().isEmpty()) {
			this.setNextConnectionToUnit(unit);
			unit.getPrevConToUnits().add(this);
			unit.getSoldiers().addAll(this.getSoldiers());
		}
	}

	public void attachedAfter(Unit unit, Integer soldierId) {
		if (this.getNextConnectionToUnit() != null) {
			this.detachFrom(this.getNextConnectionToUnit());
		}
		if (unit.getSoldiers().size() == 0
				|| (!unit.getPrevConToUnits().isEmpty() && this.checkId(unit
						.getSoldiers().indexOf(soldierId), unit))) {
			this.setNextConnectionToUnit(unit);
			unit.getPrevConToUnits().add(this);
			unit.getSoldiers().addAll(
					unit.getSoldiers().indexOf(soldierId) + 1,
					this.getSoldiers());
		}
	}

	public void removeSoldiers(LinkedList<Integer> soldiersId) {
		HashSet<Integer> tmp = new HashSet<Integer>(this.getSoldiers());
		tmp.removeAll(soldiersId); // Linear operation
		this.setSoldiers(new LinkedList<Integer>(tmp));
		for (Unit iUnit : this.getPrevConToUnits()) {
			iUnit.removeSoldiers(soldiersId);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (Integer soldier : this.getSoldiers()) {
			sb.append(soldier);
			sb.append(",");
			sb.append(" ");
		}
		if (sb.length() > 1) {
			sb.deleteCharAt(sb.length() - 1);
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		return sb.toString();
	}

	public String getUnitContent() {
		return this.toString();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Unit)) {
			return false;
		}

		Unit that = (Unit) obj;
		return (this.getName().equals(that.getName()))
				&& (this.getSoldiers().equals(that.getSoldiers()));
	}
}
