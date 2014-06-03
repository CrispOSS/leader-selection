package com.github.crisposs.leaderselection;

import abs.api.Behavior;

/**
 * @author Behrooz Nobakht
 */
public interface VoteCollector extends Behavior {

	public default Object respond(Object message) {
		return collect((Vote) message);
	}

	Object collect(Vote v);

}
