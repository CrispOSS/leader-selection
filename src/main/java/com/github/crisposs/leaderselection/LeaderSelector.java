package com.github.crisposs.leaderselection;

import abs.api.Behavior;

/**
 * @author Behrooz Nobakht
 */
public interface LeaderSelector extends Behavior {

	default Object respond(Object msg) {
		return selectLeader((Integer) msg);
	}

	Integer selectLeader(Integer n);

}
