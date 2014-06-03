package com.github.crisposs.leaderselection;

import java.io.Serializable;

/**
 * @author Behrooz Nobakht
 */
public class Vote implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int nodeId;
	private final int selectedLeader;

	public Vote(int nodeId, int selectedLeader) {
		this.nodeId = nodeId;
		this.selectedLeader = selectedLeader;
	}

	public int getNodeId() {
		return nodeId;
	}

	public int getSelectedLeader() {
		return selectedLeader;
	}

	@Override
	public String toString() {
		return "(node=" + nodeId + ", vote=" + selectedLeader + ")";
	}

	@Override
	public int hashCode() {
		return 31 * nodeId + selectedLeader;
	}

}
